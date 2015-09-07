/* global CoreCtrl, angular */

function TipoDocumentoCadastroCtrl() {
    CoreCtrl.apply(this, arguments);
    this.scope("acao", this.$routeParams.acao);

    this.scope("tipoServico", {id: null, txTipoServico: "", csSituacao: true});

    this.scope("duplicado", false);

    this.scope("tipoServicoList", []);

    this.scope("camposObrigatorios", {txTipoServico: ""});

    this.service = this.inject("TipoServico");

    this.abrirPagina();
}

$.extend(TipoDocumentoCadastroCtrl.prototype, CoreCtrl.prototype);


TipoDocumentoCadastroCtrl.prototype.adicionarTipoServico = function () {
    this.cleanAlert();
    this.limparMensagensCamposObrigatorios();
    this.scope("duplicado", false);

    if (!this.scope("tipoServico").txTipoServico) {
        this.scope("camposObrigatorios").txTipoServico = "campo.obrigatorio";
    } else {

        this.validarDuplicacaoTela();

        if (this.scope("duplicado")) {
            this.showMsgDuplicado();

        } else {
            // valida duplicacao na base de dados
            this.validarDuplicacaoBanco();
        }
    }
};

TipoDocumentoCadastroCtrl.prototype.validarDuplicacao = function (retorno) {
    var tipoServ = retorno.element;
    this.scope("duplicado", tipoServ !== null);

    if (this.scope("duplicado")) {
        this.showMsgDuplicado();
    } else {
        this.adicionarItem();
    }
}

TipoDocumentoCadastroCtrl.prototype.adicionarItem = function () {
    this.scope("tipoServicoList").push(this.scope("tipoServico"));
    this.scope("tipoServico", {id: null, txTipoServico: "", csSituacao: true});
};

TipoDocumentoCadastroCtrl.prototype.validarDuplicacaoTela = function () {
    var duplicado = this.scope("duplicado");
    var txTipoServico = this.scope("tipoServico").txTipoServico;
    angular.forEach(this.scope("tipoServicoList"), function (item) {
        if (angular.equals(item.txTipoServico, txTipoServico)) {
            duplicado = true;
        }
    });
    this.scope("duplicado", duplicado);
};

TipoDocumentoCadastroCtrl.prototype.validarDuplicacaoBanco = function () {
    this.service.buscarPorIdEDescricao(
            {id: this.scope("tipoServico").id, txTipoServico: this.scope("tipoServico").txTipoServico},
    this.validarDuplicacao.bind(this)
            );
};

TipoDocumentoCadastroCtrl.prototype.showMsgDuplicado = function () {
    this.showAlert("tipo.servico.duplicado", "danger");
};

TipoDocumentoCadastroCtrl.prototype.excluirConfirmar = function (index) {
    this.scope("tipoServicoList").splice(index, 1);
}

TipoDocumentoCadastroCtrl.prototype.abrirPagina = function () {
    if (this.scope("acao") === "editar") {
        idTipoServico = this.$location.search().id;
        this.service.buscarPorId({id: idTipoServico},
        function (retorno) {
            this.scope("tipoServico", retorno.element);
        }.bind(this),
                function () {
                    this.showAlert("nenhum.registro.encontrado", "danger");
                }.bind(this));
    }
};

TipoDocumentoCadastroCtrl.prototype.salvar = function () {
    this.cleanAlert();
    if (this.scope("acao") === "editar") {
        this.scope("tipoServicoList", []);
        this.scope("tipoServicoList").push(this.scope("tipoServico"));
    }
    if (this.scope("tipoServicoList").length === 0) {
        this.showAlert("grid.sem.registro", "danger");
    } else {
        this.service.salvar(this.scope("tipoServicoList"), this.salvarSucesso.bind(this), this.salvarErro.bind(this));
    }
};


TipoDocumentoCadastroCtrl.prototype.salvarSucesso = function (retorno) {
    this.cleanAlert();
    this.showAlert("registro.salvo", "success");
    this.$timeout(this.cancelar.bind(this), 1000);
};

TipoDocumentoCadastroCtrl.prototype.salvarErro = function (retorno) {
    this.cleanAlert();
    this.showAlert("erro.registro.salvo", "danger");
    console.log(retorno);
};

TipoDocumentoCadastroCtrl.prototype.limparMensagensCamposObrigatorios = function () {
    this.scope("camposObrigatorios", {});
};

TipoDocumentoCadastroCtrl.prototype.cancelar = function () {
    this.$location.path('seguranca-trabalho/tipo-servico');
};