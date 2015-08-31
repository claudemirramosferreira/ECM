/* global TOKEN, angular */

function InfoInicial($http, $location, $rootScope, $cookies, ecmRest) {

    /**
     * Caso ocorra erro, remove seu login
     */
    function erroLogin() {
        $cookies.remove('TOKEN');
        $location.path("/auth/user");
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
                    var usuario = retorno.element;
                    usuario.primeiroNome = (usuario.nome.split(" "))[0];
                    $rootScope.usuario = usuario;
                }
            }
        });
    }

    /**
     * Obtem o menu e serviços do usuário logado
     * 
     */
    function obterMenu() {
        $http.get(ecmRest + 'rest/menu/acesso')
                .success(function (resposta) {
                    var
                            menu = resposta.element,
                            menuPrincipal = [],
                            menuSeguranca = [],
                            menuAlterarSenha = {idMenu: null, id: "mod_seguranca.administrador.alterarsenha",
                                tipo: "o", titulo: "Alterar Senha", uri: "/ecm/index.html#/seguranca/alterar-senha",
                                csMaster: null};

                    angular.forEach(menu, function (v) {
                        if (v.tipo === "s") {
                            menuSeguranca.push(v);
                        } else {
                            menuPrincipal.push(v);
                        }
                    });

                    //funcionamento esperado é que tenho apenas um menu segurança
                    if (!menuSeguranca.length) {
                        menuSeguranca = [{idMenu: null, id: "mod_seguranca",
                                tipo: "s", titulo: "SEGURANÇA", uri: null,
                                csMaster: null, subs:[]
                            }];
                    }

                    menuSeguranca[0].subs.unshift(menuAlterarSenha);
                    $rootScope.menuSeguranca = menuSeguranca[0].subs;

                    $rootScope.menuPrincipal = menuPrincipal;
                    //gerando a permissão do usuário
                    function permissionCreate(o) {

                        var permission = '';
                        if (o.subs) {
                            permission = o.subs.map(permissionCreate).join('');
                        }

                        var url = o.uri.replace(/[\/]{0,1}ecm\/(index.html){0,1}\#\//g, '').replace(/\/$/g, '');

                        return '[' + url + '][' + url + '/novo][' + url + '/editar]' + permission;

                    }

                    $rootScope.permissaoUsuario = menu.map(permissionCreate).join('') + '[acesso-negado]';

                }
                );
    }

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
                        obterMenu();
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

            $http.get(ecmRest + 'rest/sysVersao').success(function (resposta) {
                $rootScope.versao = resposta.element;
            });
        } else {
            $location.path("/autenticar/usuario");
        }
    }
    ;
}