function TipoDocumentoCadastroCtrl() {
    CoreCtrl.apply(this, arguments);
    this.scope("acao", this.$routeParams.acao);

    this.scope("exibirBotaoAdicionar", true);

    this.scope("situacoes",
            [
                {id: true, descricao: "ativo"},
                {id: false, descricao: "inativo"}
            ]);


    this.scope("tipoDocumento", {
        id: null,
        txTipoDocumento: "",
        csAtivo: true
    });

    this.scope("listaTipoDocumento", []);

    this.scope("camposObrigatorios", {txTipoDocumento: ""});

    this.service = this.inject("TipoDocumento");

    this.abrirPagina();

    this.index = null;
}

$.extend(TipoDocumentoCadastroCtrl.prototype, CoreCtrl.prototype);

TipoDocumentoCadastroCtrl.prototype.abrirPagina = function () {
    if (this.scope("acao") === "editar") {
        var id = this.$location.search().id;
        this.service.get({id: id},
        function (retorno) {
            this.scope("exibirBotaoAdicionar", false);
            this.scope("tipoDocumento", retorno.element);
        }.bind(this),
                function () {
                    this.showAlert("nenhum.registro.encontrado", "danger");
                }.bind(this));
    } else {
        this.scope("exibirBotaoAdicionar", true);
    }
};


TipoDocumentoCadastroCtrl.prototype.validarTipoDocumentoDuplicado = function (pTipoDocumento) {
    var encontrou = false;
    var listaTipoDocumento = this.scope("listaTipoDocumento");
    
    angular.forEach(listaTipoDocumento, function (value, index) {
        if (angular.equals(value.txTipoDocumento.toLowerCase(), pTipoDocumento.txTipoDocumento.toLowerCase())) {
            encontrou = true;
        }
    });
    return encontrou;
};

TipoDocumentoCadastroCtrl.prototype.addTipoDocumento = function (pTipoDocumento) {
    this.cleanAlert();
    var encontrou = false;
    var tipoDocumento = angular.copy(pTipoDocumento);

    if (this.validarTipoDocumentoDuplicado(pTipoDocumento)) {
        this.showAlert("item.duplicado", "danger");
    } else {
        if (!tipoDocumento.txTipoDocumento) {
            this.scope("camposObrigatorios").txTipoDocumento = "campo.obrigatorio";
            encontrou = true;
        }

        if (!encontrou) {
            if (this.index === null) {
                this.scope("listaTipoDocumento").push(tipoDocumento);
            } else {
                this.scope("listaTipoDocumento")[this.index] = tipoDocumento;
                this.index = null;
            }
            this.scope("tipoDocumento", {csAtivo: true});
            this.limparMensagensCamposObrigatorios();
        }
    }

};


TipoDocumentoCadastroCtrl.prototype.editTipoDocumento = function (index) {
    var tipoDocumento = angular.copy(this.scope("listaTipoDocumento")[index]);
    this.scope("tipoDocumento", tipoDocumento);
    this.index = index;
};

TipoDocumentoCadastroCtrl.prototype.remover = function (index) {
    this.showModalExcluir();
    this.index = index;
};

TipoDocumentoCadastroCtrl.prototype.excluirConfirmar = function () {
    var listaTipoDocumento = this.scope("listaTipoDocumento");
    listaTipoDocumento.splice(this.index, 1);
    this.index = null;
};

TipoDocumentoCadastroCtrl.prototype.salvar = function (pTipoDocumento) {
    this.limparMensagensCamposObrigatorios();
    this.cleanAlert();

    if (this.scope("acao") === "cadastro") {
        if (this.scope("listaTipoDocumento").length <= 0) {
            this.showAlert("msg.informe.registro", "danger");
        } else {
            console.log(this.scope("listaTipoDocumento"));
            this.service.save(this.scope("listaTipoDocumento"),
                    this.salvarSucesso.bind(this),
                    this.salvarErro.bind(this)
                    );
        }
    } else {
        var tipoDocumento = angular.copy(pTipoDocumento);

        if (tipoDocumento.txTipoDocumento === "") {
            this.scope("camposObrigatorios").txTipoDocumento = "campo.obrigatorio";
        } else {
            this.service.alterar(tipoDocumento, this.alterarSucesso.bind(this), this.alterarErro.bind(this));
        }
    }
};

TipoDocumentoCadastroCtrl.prototype.salvarSucesso = function (retorno) {
    this.cleanAlert();
    this.showAlert("registro.salvo", "success");
    this.$timeout(this.cancelar.bind(this), 1000);
};

TipoDocumentoCadastroCtrl.prototype.salvarErro = function (retorno) {
    this.cleanAlert();
    this.showAlert("registro.duplicado", "danger");
};

TipoDocumentoCadastroCtrl.prototype.alterarSucesso = function (retorno) {
    this.cleanAlert();
    this.showAlert("registro.salvo", "success");
    this.$timeout(this.cancelar.bind(this), 1000);
};

TipoDocumentoCadastroCtrl.prototype.alterarErro = function (retorno) {
    this.cleanAlert();
    this.showAlert("registro.duplicado", "danger");
};

TipoDocumentoCadastroCtrl.prototype.limparMensagensCamposObrigatorios = function () {
    this.scope("camposObrigatorios", {});
};

TipoDocumentoCadastroCtrl.prototype.cancelar = function () {
    this.$location.path("/gerais/tipo-documento");
};

 