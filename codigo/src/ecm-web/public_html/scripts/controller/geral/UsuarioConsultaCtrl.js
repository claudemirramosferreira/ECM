
/* global CoreCtrl */

function UsuarioConsultaCtrl() {
    CoreCtrl.apply(this, arguments);
    
    this.scope("pesquisa", {descricao: "", situacao: ""});

    this.scope("situacoes", [
        {id: "", descricao: "todos.registros"},
        {id: "T", descricao: "ativo"},
        {id: "F", descricao: "inativo"}]);

    this.service = this.inject("Usuario");
    this.abrirPagina();
}

$.extend(UsuarioConsultaCtrl.prototype, CoreCtrl.prototype);

UsuarioConsultaCtrl.prototype.abrirPagina = function () {
    this.buscarPorDescricao();
};

UsuarioConsultaCtrl.prototype.buscarPorDescricao = function (pagina) {
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

UsuarioConsultaCtrl.prototype.buscarPorDescricaoSucesso = function (retorno) {
    this.scope("mensagemNenhumRegistro", "");
    this.scope("itens", retorno.element);
    this.scope("pagina").totalItem = retorno.totalProperty;
};

UsuarioConsultaCtrl.prototype.buscarPorDescricaoErro = function (erro) {
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


UsuarioConsultaCtrl.prototype.cadastro = function () {
    this.$location.path("/geral/usuario/cadastro");
};


UsuarioConsultaCtrl.prototype.editar = function (id) {
    this.$location.path("/geral/usuario/editar").search({id: id});
};


UsuarioConsultaCtrl.prototype.excluirConfirmar = function (item) {
    this.service.remover({id: item.id}, this.excluirResultado.bind(this), this.excluirError.bind(this));
};

UsuarioConsultaCtrl.prototype.excluirResultado = function (response) {
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

UsuarioConsultaCtrl.prototype.excluirError = function (response) {
    this.showAlert(response.data.messages[0].description, "danger");
};

UsuarioConsultaCtrl.prototype.modificarSituacao = function (item) {
    this.service.ativarOuInativar({id: item.id});
};