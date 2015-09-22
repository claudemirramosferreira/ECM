/* global TOKEN, angular */

function InfoInicial($http, $location, $rootScope, $cookies, ecmRest) {

    /**
     * Caso ocorra erro, remove seu login
     * 
     */
    function erroLogin() {
        $cookies.remove('TOKEN');
        $location.path("/autenticar/usuario");
    }

    /**
     * Obtem o usuário logado
     * 
     */
    function obterUsuario() {
        $.ajax({
            method: "GET",
            url: ecmRest + 'rest/usuario/getUser',
            cache: false,
            async: false,
            headers: {'Authorization': $cookies.get('TOKEN')},
            success: function (retorno) {
                if (retorno && retorno.element) {
                    var ecmUser = retorno.element;
                    ecmUser.primeiroNome = (ecmUser.nome.split(" "))[0];
                    $rootScope.ecmUser = ecmUser;
                }
            }
        });
    }

    /**
     * Obtem o menu e serviços do usuário logado
     * 
     */
//    function obterMenu() {
//
//        $.ajax({
//            method: "GET",
//            url: ecmRest + "rest/menu/acesso",
//            cache: false,
//            async: false,
//            headers: {'Authorization': $cookies.get('TOKEN')},
//            success: function (resposta) {
//                var
//                        menu = resposta.element,
//                        menuPrincipal = [],
//                        menuSeguranca = [],
//                        menuAlterarSenha = {idMenu: null, id: "mod_seguranca.administrador.alterarsenha",
//                            tipo: "o", titulo: "Alterar Senha", uri: "/ecm/index.html#/seguranca/alterar-senha",
//                            csMaster: null};
//
//                angular.forEach(menu, function (v) {
//                    if (v.tipo === "s") {
//                        menuSeguranca.push(v);
//                    } else {
//                        menuPrincipal.push(v);
//                    }
//                });
//
//                if (menuSeguranca.length === 0) {
//                    var subs = menuAlterarSenha;
//                    menuSeguranca.push(subs);
//                    $rootScope.menuSeguranca = menuSeguranca;
//                } else {
//                    menuSeguranca[0].subs.unshift(menuAlterarSenha);
//                    $rootScope.menuSeguranca = menuSeguranca[0].subs;
//                }
//
//                $rootScope.menuPrincipal = menuPrincipal;
//                //gerando a permissão do usuário
//                function permissionCreate(o) {
//
//                    var permission = '';
//                    if (o.subs) {
//                        permission = o.subs.map(permissionCreate).join('');
//                    }
//
//                    var url = o.uri.replace(/[\/]{0,1}ehs2\/(index.html){0,1}\#\//g, '').replace(/\/$/g, '');
//
//                    return '[' + url + '][' + url + '/novo][' + url + '/editar][' + url + '/cadastro][' + url + '/avaliacao/form]' + permission;
//
//                }
//
//                $rootScope.permissaoUsuario = menu.map(permissionCreate).join('') + '[acesso-negado]' + '[seguranca/alterar-senha]';
//
//            }
//
//        });

//        $http.get(ecmRest + 'rest/menu/acesso')
//                .success(function (resposta) {
//
//
//                }
//                );
//    }

    /**
     * Verifica se o usário ainda está conectado
     * 
     */
    function verificarAutenticacao() {
        $http.get(ecmRest + 'rest/usuario/isConnected')
                .success(function (response) {
                    if (response === false) {
                        $http.defaults.headers.common.Authorization = null;
                        $cookies.remove('TOKEN');
                        $location.path("/autenticar/usuario");
                    } else {
//                        obterMenu();
                        // FIXME: carregar menu
                    }
                }).error(erroLogin);

        obterUsuario();
    }

    /**
     * Verificação geral das informações do usuário logado
     */
    this.verificar = function () {
        var TOKEN = $cookies.get('TOKEN');
        if (TOKEN) {
            $http.defaults.headers.common.Authorization = TOKEN;
            verificarAutenticacao();

            /* FIXME: obter a versao do sistema
            $http.get(ecmRest + 'rest/sysVersao').success(function (resposta) {
                $rootScope.versao = resposta.element;
            });
            */
            
        } else {
            $location.path("/autenticar/usuario");
        }
    }
    ;
}
