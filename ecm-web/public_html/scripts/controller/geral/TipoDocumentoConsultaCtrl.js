
/* global CoreCtrl */

function TipoDocumentoConsultaCtrl() {
    CoreCtrl.apply(this, arguments);
    
    this.scope("pesquisa", {descricao: "", situacao: ""});

    this.scope("situacoes", [
        {id: "", descricao: "todos.registros"},
        {id: "T", descricao: "ativo"},
        {id: "F", descricao: "inativo"}]);

    this.service = this.inject("TipoDocumento");
    this.abrirPagina();
}

$.extend(TipoDocumentoConsultaCtrl.prototype, CoreCtrl.prototype);

TipoDocumentoConsultaCtrl.prototype.abrirPagina = function () {
    this.buscarPorDescricao();
};

TipoDocumentoConsultaCtrl.prototype.buscarPorDescricao = function (pagina) {
    if (pagina) {
        this.scope("pagina").page = pagina;
    }
    var desc = this.scope("pesquisa").descricao;
    var dados = {
//                descricao: this.scope("pesquisa").descricao,
                situacao: this.scope("pesquisa").situacao,
                page: this.scope("pagina").page,
                limit: this.scope("pagina").limit
            };
    this.service.buscarPorDescricao(
            desc, dados, this.buscarPorDescricaoSucesso.bind(this), this.buscarPorDescricaoErro.bind(this)
            );
};

TipoDocumentoConsultaCtrl.prototype.buscarPorDescricaoSucesso = function (retorno) {
    this.scope("mensagemNenhumRegistro", "");
    this.scope("itens", retorno.element);
    this.scope("pagina").totalItem = retorno.totalProperty;
};

TipoDocumentoConsultaCtrl.prototype.buscarPorDescricaoErro = function (erro) {
    console.log(erro);
    
    if (erro.data.messages[0].type !== "EMPTY") {
        angular.forEach(erro.data.messages[0].descriptionList,function(value,key) {
            this.showAlerts(value, erro.data.messages[0].type);
        });
    } else {
        this.scope('mensagemNenhumRegistro', erro.data.messages[0].description);
    }
    
    this.scope("itens", []);
    
};


TipoDocumentoConsultaCtrl.prototype.cadastro = function () {
    this.$location.path("/geral/tipo-documento/cadastro");
};


TipoDocumentoConsultaCtrl.prototype.editar = function (id) {
    this.$location.path("/geral/tipo-documento/editar").search({id: id});
};


TipoDocumentoConsultaCtrl.prototype.excluirConfirmar = function (item) {
    this.service.remover({id: item.id}, this.excluirResultado.bind(this), this.excluirError.bind(this));
};

TipoDocumentoConsultaCtrl.prototype.excluirResultado = function (response) {
    var paginaAnterior = this.scope("pagina").page - 1;
    if (paginaAnterior > 0) {
        var linhas = (paginaAnterior) * this.scope("pagina").limit;
        if ((this.scope("pagina").totalItem - 1) == linhas) {
            this.scope("pagina").page = paginaAnterior;
        }
    }
    
    this.buscarPorDescricaoOuSituacao();
    this.showAlert(response.messages[0].description, "success");
};

TipoDocumentoConsultaCtrl.prototype.excluirError = function (response) {
    this.showAlert(response.data.messages[0].description, "danger");
};

TipoDocumentoConsultaCtrl.prototype.modificarSituacao = function (item) {
    this.service.ativarOuInativar({id: item.id});
};