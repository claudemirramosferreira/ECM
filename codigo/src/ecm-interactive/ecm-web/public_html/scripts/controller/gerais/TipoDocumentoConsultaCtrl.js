
function TipoDocumentoConsultaCtrl() {
    CoreCtrl.apply(this, arguments);
    this.scope("pagina", {page: 1, limit: 5, totalItem: 0});
    this.scope("itens");
    this.scope("mensagemNenhumRegistro");
    this.scope("pesquisa", {descricao: "", csAtivo: ""});

    this.scope("situacoes", [
        {id: "", descricao: "todos.registros"},
        {id: "T", descricao: "ativo"},
        {id: "F", descricao: "inativo"}]);

    this.service = this.inject("TipoDocumento");
    this.abrirPagina();
}

$.extend(TipoDocumentoConsultaCtrl.prototype, CoreCtrl.prototype);

TipoDocumentoConsultaCtrl.prototype.abrirPagina = function () {
    this.buscarPorDescricaoOuSituacao();
};

TipoDocumentoConsultaCtrl.prototype.buscarPorDescricaoOuSituacao = function (pagina) {
    this.cleanAlert();
    if (pagina) {
        this.scope('pagina').page = pagina;
    }
    
    this.service.buscarPorDescricaoOuSituacao(
            {
                descricao: this.scope("pesquisa").descricao,
                situacao: this.scope("pesquisa").situacao,
                page: this.scope("pagina").page,
                limit: this.scope("pagina").limit
            }, this.buscarPorDescricaoOuSituacaoSucesso.bind(this), this.buscarPorDescricaoOuSituacaoErro.bind(this)
            );
};

TipoDocumentoConsultaCtrl.prototype.buscarPorDescricaoOuSituacaoSucesso = function (retorno) {
    this.scope("mensagemNenhumRegistro", "");
    this.scope("itens", retorno.element);
    this.scope("pagina").totalItem = retorno.totalProperty;
};

TipoDocumentoConsultaCtrl.prototype.buscarPorDescricaoOuSituacaoErro = function (retorno) {
    this.scope("mensagemNenhumRegistro", retorno.data.messages[0].description);
    this.scope("itens", []);
    this.scope("pagina", {page: 1, limit: 5, totalItem: 0});
};

TipoDocumentoConsultaCtrl.prototype.cadastro = function () {
    this.$location.path("/gerais/tipo-documento/cadastro");
};

TipoDocumentoConsultaCtrl.prototype.editar = function (id) {
    this.$location.path("/gerais/tipo-documento/editar").search({id: id});
};

TipoDocumentoConsultaCtrl.prototype.excluirConfirmar = function (item) {
    this.service.delete({id: item.id}, this.excluirResultado.bind(this), this.excluirError.bind(this));
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
    this.showAlert(response.element.description, "success");
};

TipoDocumentoConsultaCtrl.prototype.excluirError = function (response) {
    this.showAlert(response.element.description, "danger");
};
TipoDocumentoConsultaCtrl.prototype.modificarSituacao = function (item) {
    this.service.ativarOuInativar({id: item.id});
};