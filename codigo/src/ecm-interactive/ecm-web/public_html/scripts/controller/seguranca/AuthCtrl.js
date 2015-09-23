/* global CoreCtrl, angular */
function AuthCtrl() {
    CoreCtrl.apply(this, arguments);

    this.scope("user", {});
    this.scope("userSolicitar", {cpf: ""});
    this.scope("mostrarBotao", false);
    this.scope("userRecuperar", {usuario: ""});

    this.service = this.inject('Auth');
//    this.serviceSolicitar = this.inject("UsuarioSolicitarCadastro");
    
    var statusUsuario = {
        0: "Cadastro solicitado.",
        1: "Cadastro ativo.",
        2: "Cadastro inativo.",
        3: "Bloqueado",
        4: "Senha regerada",
        5: "Cadastro rejeitado.",
        6: "Cadastro incorreto.",
        7: "Regerar senha"
    };

    this.scope("statusUsuario", statusUsuario);
    this.scope("recuperarSolicitando", false);    
}

$.extend(AuthCtrl.prototype, CoreCtrl.prototype);

/**
 * Faz a autênticação
 */
AuthCtrl.prototype.autenticarUsuario = function (){
    this.cleanAlert();
    this.scope('errorAutenticar', false);
    
    var user = angular.copy(this.scope('user'));
    var encontrou = false;

    if(!user.login || !user.senha){
        this.showAlert("Os campos usuário e senha são obrigatórios.");
        encontrou = true;
    }
    
    if(!encontrou){
        user.senha = this.md5.createHash(user.senha);
        this.service.login(user, this.autenticarUsuarioResultado.bind(this), this.autenticarUsuarioResultadoErro.bind(this));
    }    
};

/**
 * Resultado quando faz a autênticação
 * @param {object} resposta
 */
AuthCtrl.prototype.autenticarUsuarioResultado = function (resposta) {

    if (resposta && resposta.element && resposta.element.token) {

        var InfoInicial = this.inject("InfoInicial");

        this.$cookies.put('TOKEN', resposta.element.token);
        InfoInicial.verificar();
        this.$location.path('/').search();
    } else {
        this.autenticarUsuarioResultadoErro();
    }
};

/**
 * Quando a autêncitcação ocorre error
 */
AuthCtrl.prototype.autenticarUsuarioResultadoErro = function (d) {
    this.cleanAlert();
    this.scope("mostrarBotao", false);
    if (d.data && d.data.messages && d.data.messages[0] && d.data.messages[0].description) {
        this.showAlert(d.data.messages[0].description, "danger");
    } else if (d.data && d.data.messages && d.data.messages[0] && d.data.messages[0].descriptionList && d.data.messages[0].descriptionList.length > 0) {

        if (d.data.messages[0].descriptionList[0] === 'login.incorreto') {
            this.scope("mostrarBotao", true);
        }

        var texto = this.$translate.instant(d.data.messages[0].descriptionList[0]) + " Justificativa: " + d.data.messages[0].descriptionList[1];

        if (d.data.messages[0].descriptionList[2]) {
            this.scope("userSolicitar", {cpf: d.data.messages[0].descriptionList[2]});
        }

        this.showAlert(texto, "danger");
    } else {
        this.showAlert("Erro ao tentar solicitar.", "danger");
    }
    this.scope('errorAutenticar', true);
};


/**
 * Pesquisa do cpf para ver se o usuário está ok
 */
AuthCtrl.prototype.pesquisarStatusCpf = function () {
    this.scope("mostrarBotao", false);
    this.cleanAlert();
    if (this.scope("userSolicitar").cpf.length !== 0) {
        var
                userSolicitar = this.scope("userSolicitar"),
                cpf = userSolicitar.cpf.replace(/\D/g, ""),
                statusUsuario = this.scope("statusUsuario");

        this.cleanAlert();

//        this.serviceSolicitar
//                .pesquisarStatusCpf({cpf: userSolicitar.cpf.replace(/\D/g, "")},
//                function (d) {
//                    this.cleanAlert();
//                    var status = parseInt(d.element);
//                    if (status === 6) {
//                        this.$location.path("/autenticar/solicitar-cadastro").search({cpf: cpf});
//                    } else {
//                        this.showAlert(statusUsuario[status]);
//                    }
//                }.bind(this), function (d) {
//                    this.cleanAlert();
//                    this.$location.path("/autenticar/solicitar-cadastro").search({cpf: cpf});
//                }.bind(this));
    } else {
        this.showAlert("Campo obrigatório.");
    }
};


AuthCtrl.prototype.recuperarSenha = function () {
    this.scope("mostrarBotao", false);
    var usuario = this.scope("userRecuperar").usuario;

    this.cleanAlert();

    this.scope("recuperarSolicitando", true);

    this.service.recuperarSenha({usuario: usuario}, function (d) {
        this.cleanAlert();
        this.showAlert("Solicitação enviada com sucesso.", "success");
        this.scope("recuperarSolicitando", false);
    }.bind(this), function (d) {
        this.cleanAlert();
        if (d.data &&
                d.data.messages &&
                d.data.messages[0] &&
                d.data.messages[0].description &&
                d.data.messages[0].description) {
            this.showAlert(d.data.messages[0].description, "danger");
        } else {
            this.showAlert("Erro ao tentar solicitar.", "danger");
        }
        this.scope("recuperarSolicitando", false);
    }.bind(this));
};