/* global Home, AtividadeDirecionada, Avaliacao, AvaliacaoFormulario, Biomecanica, Demanda, MooreGarg, Posto, ProcessoInss, Produto, HomeCtrl, TopCtrl,  Atendimento, Beneficio, EntrevistaSocial, Gestacao, Visita, AtendimentoCtrl, BeneficioCtrl, EntrevistaSocialCtrl, GestacaoCtrl, VisitaCtrl, angular, AuthCtrl, EmpresaTerceira, EmpresaTerceiraConsultaCtrl, AssistenciaSocialAtendimentoCtrl, AssistenciaSocialBeneficioCtrl, AssistenciaSocialEntrevistaSocialCtrl, AssistenciaSocialVisitaCtrl, Auth, InfoInicial, AvaliacaoConsultaCtrl, DemandaErgonomicaCtrl, DemandaErgonomica, PerfilConsultarCtrl, ExtintorConsultaCtrl, Perfil, Extintor, AvaliacaoCadastroCtrl, EmpresaTerceiraCadastroCtrl, TipoExtintorConsultaCtrl, TipoExtintorCadastroCtrl, SegurancaPerfilItens, RequiredField, ExtintorCtrl, RecargaTesteHidrostaticoConsultaCtrl, MarcaExtintorCadastroCtrl, MarcaExtintorConsultaCtrl, HistoricoExtintores, Unidade, MarcaExtintor, Localizacao, TipoExtintor, AtividadeDirecionadaConsultaCtrl, AtividadeDirecionadaCtrl, RecargaTesteHidrostaticoCtrl, MotivoVisita, VisitaConsultaCtrl, GestacaoCadastroCtrl, PerfilGrupo, Email, UsuarioManutencao, UsuarioUndOrganizacional, UsuarioSolicitarCadastro, UnidadeOrganizacional, UsuarioStatus, AprovarUsuario, RemoverEspaco */
// AuthInterceptor,
var ecmApp = angular.module('ecmApp',
        [
            'ngSanitize',
            'mm.foundation',
            'ngRoute',
            'ngResource',
            'ngCookies',
            'ngResource',
            'pascalprecht.translate',
            'angucomplete-alt',
            'angular-md5',
            'ui.filters',
            'AxelSoft'
        ]);

ecmApp.config([
    '$routeProvider',
    '$httpProvider',
    function ($routeProvider, $httpProvider) {

        $routeProvider
                .when('/', {templateUrl: 'views/default/home.html'})
                .when('/acesso-negado', {templateUrl: 'views/default/acesso-negado.html'})

                //auth
                .when('/auth/user', {templateUrl: 'views/auth/user.html'})

                //nao encontrada
                .otherwise({
                    redirectTo: '/404.html'
                });

//        $httpProvider.interceptors.push('AuthInterceptor');   // FIXME 
    }
])
        .config(['$translateProvider', function ($translateProvider) {

                $translateProvider.useSanitizeValueStrategy(null);
                $translateProvider.preferredLanguage('pt_Br');
                $translateProvider.useStaticFilesLoader({
                    prefix: 'languages/',
                    suffix: '.json'
                });


            }])

        .run(function ($http, $location, $rootScope, $cookies) {    // , InfoInicial FIXME
            $http.defaults.headers.common.Authorization = null;
//            InfoInicial.verificar();
            $rootScope.carregandoScreen = 0;
        });


//Urls dos modulos--------------------------------------------------------------
var port = location.port;
port = (/8383/.test(port) ? 8080 : port);       // 8383 = porta de debug web do netbeans

var urlBase = location.protocol + '//' + location.hostname + ':' + port + '/';

// constantes dos modulos web (projetos rest)
ecmApp.constant('urlBase', urlBase);
ecmApp.constant('ecmRest', urlBase + 'ecm-rest/');

//------------ Services --------------------------------------------------------

//SECURITY
//ecmApp.service('Auth', Auth);
//ecmApp.service('AuthInterceptor', AuthInterceptor);
//ecmApp.service('InfoInicial', InfoInicial);
//ecmApp.service('Usuario', Usuario);



//----------- CONTROLLERS ------------------------------------------------------
ecmApp.controller("HomeCtrl", ['$injector', '$scope', HomeCtrl]);
ecmApp.controller("TopCtrl", ['$injector', '$scope', TopCtrl]);
//ecmApp.controller("AuthCtrl", ['$injector', '$scope', AuthCtrl]);


//------------------------------------------------------------------------------
//DIRETIVAS------------------------------------------------------------------------------
ecmApp.directive('requiredField', RequiredField);
ecmApp.directive('onLastRepeat', OnLastRepeat);
ecmApp.directive('maskedInput', MaskedInput);
ecmApp.directive('digits', Digits);
ecmApp.directive('fdatepicker', Fdatepicker);
ecmApp.directive('currencyFormat', CurrencyFormatDirective);
ecmApp.directive('selectFilter', SelectFilter);
ecmApp.directive('formatReal', FormatReal);
ecmApp.directive('sortable', Sortable);
ecmApp.directive('fdatepickerMesAno', FdatepickerMesAno);
ecmApp.directive('telefoneFormat', TelefoneFormat);
ecmApp.directive('removerEspaco', RemoverEspaco);
ecmApp.directive('ngEnter', NgEnter);
ecmApp.directive("disableAll", DisableAll);


//FILTROS------------------------------------------------------------------------------
ecmApp.filter("cpfFormato", [CpfFormato]);
ecmApp.filter("currencyFormat", [CurrencyFormatFilter]);

ecmApp.filter('filterCsTipo', function () {
    return function (items, v) {
        var filtered = [];
        for (var i = 0; i < items.length; i++) {
            var item = items[i];
            if (item.fatorRisco.csTipo === v) {
                filtered.push(item);
            }
        }
        return filtered;
    };
});


ecmApp.directive('ngFormatDate', function () {
    return {
        require: 'ngModel',
        link: function ($scope, $element, $attr, ngModel) {
            $scope.$watch($attr['ngModel'], function (input) {
                var formato = $attr.ngFormatDate;
                if (input && typeof input === "number") {
                    var dt = new Date(input);
                    var a = moment(dt).format(formato);
                    ngModel.$setViewValue(a);
                    ngModel.$render();
                }
                $element.fdatepicker({
                    language: 'pt-BR',
                    format: 'dd/mm/yyyy'
                });
                return input;
            });
        }
    };
});
