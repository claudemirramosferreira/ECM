/* global CoreCtrl, angular, Tools */

function AlterarSenhaCtrl() {
    CoreCtrl.apply(this, arguments);
    this.serviceUsuario = this.inject("Usuario");
    this.scope("usuario", {senhaAtual: null, novaSenha: null, confirmarSenha: null});
}

$.extend(AlterarSenhaCtrl.prototype, CoreCtrl.prototype);

AlterarSenhaCtrl.prototype.validarDados = function () {
    var dados = this.scope("usuario"), errosDados = {};

    if (!dados.senhaAtual) {
        errosDados.senhaAtual = "campo.obrigatorio";
    } else if (this.$rootScope.ecmUsuario.senha.toUpperCase() !== this.md5.createHash(dados.senhaAtual).toUpperCase()) {
        errosDados.senhaAtual = "senha.atual.nao.confere";
    }

    if (!dados.novaSenha) {
        errosDados.novaSenha = "campo.obrigatorio";
    } else if (dados.novaSenha.length < 6) {
        errosDados.novaSenha = "senha.seis.caracter";
    } else if (dados.senhaAtual && (dados.novaSenha.toUpperCase() === dados.senhaAtual.toUpperCase())) {
        errosDados.novaSenha = "nova.senha.igual.atual";
    }

    if (!dados.confirmarSenha) {
        errosDados.confirmarSenha = "campo.obrigatorio";
    }

    if (dados.confirmarSenha && dados.novaSenha) {
        if (dados.novaSenha !== dados.confirmarSenha) {
            errosDados.confirmarSenha = "nova.senha.confiram.diferentes";
        }
    }

    this.scope("errosDados", errosDados);
    return !this.hasValue(errosDados);
};

AlterarSenhaCtrl.prototype.salvar = function () {
    this.cleanAlert();
    if (this.validarDados()) {
        var dados = {id: this.$rootScope.ecmUsuario.id, senha: this.md5.createHash(this.scope("usuario").novaSenha).toUpperCase()};
        this.serviceUsuario.alterarSenha(dados,
                this.alterarSenhaSucesso.bind(this),
                this.alterarSenhaErro.bind(this)
                );
    }
};

AlterarSenhaCtrl.prototype.alterarSenhaSucesso = function (data) {
    this.showAlert("registro.salvo", "success");
    this.$rootScope.ecmUsuario.senha = this.md5.createHash(this.scope("usuario").novaSenha).toUpperCase();
    this.scope("usuario", {senhaAtual: null, novaSenha: null, confirmarSenha: null});
    this.$timeout(function () {
        this.$location.path("/").search({});
    }.bind(this), 2350);
};

AlterarSenhaCtrl.prototype.alterarSenhaErro = function (data) {
    this.showAlert("erro.registro.salvo", "success");
};