
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
            'AxelSoft',
            'ui.mask',
            '720kb.tooltips',
            'ngFileUpload'
        ]);

ecmApp.config([
    '$routeProvider',
    '$httpProvider',
    function ($routeProvider, $httpProvider) {

        $routeProvider
                .when('/', {templateUrl: 'views/default/home-alert.html'})
                .when('/acesso-negado', {templateUrl: 'views/default/acesso-negado.html'})

                //autenticacao
                .when('/autenticar/usuario',                {templateUrl: 'views/autenticar/usuario.html'})
                //seguranca
                .when('/seguranca/alterar-senha',           {templateUrl: 'views/seguranca/alterar-senha/consulta.html'})

                // gerais
                .when('/gerais/tipo-documento',             {templateUrl: 'views/gerais/tipo-documento/consulta.html'})
                .when('/gerais/tipo-documento/:acao',       {templateUrl: 'views/gerais/tipo-documento/cadastro.html'})

                //nao encontrada
                .otherwise({
                    redirectTo: '/404.html'
                });
        $httpProvider.interceptors.push('AuthInterceptor');

    }
])
        .config(['$translateProvider', '$httpProvider', function ($translateProvider, $httpProvider) {

                $translateProvider.useSanitizeValueStrategy(null);
                $translateProvider.preferredLanguage('pt_Br');
                $translateProvider.useStaticFilesLoader({
                    prefix: 'languages/',
                    suffix: '.json'
                });

            }])

        .run(function ($http, $location, $rootScope, $cookies, InfoInicial) {
            $http.defaults.headers.common.Authorization = null;
            InfoInicial.verificar();
            $rootScope.carregandoScreen = 0;

        });


//Urls dos modulos--------------------------------------------------------------
var port = location.port;
port = (/8383/.test(port) ? 8080 : port);

var urlBase = location.protocol + '//' + location.hostname + ':' + port + '/';

ecmApp.constant('urlBase', urlBase);
ecmApp.constant('ecmRest', urlBase + 'ecm-rest/');

//Service-----------------------------------------------------------------------

ecmApp.service('AuthInterceptor', AuthInterceptor);
ecmApp.service('Home', Home);
ecmApp.service('Auth', Auth);
ecmApp.service('InfoInicial', InfoInicial);


//GERAIS
ecmApp.service('TipoDocumento', TipoDocumento);

//SEGURANCA
ecmApp.service('Usuario', Usuario);

//SEGURANCA CONTROLLERS---------------------------------------------------------
ecmApp.controller("HomeCtrl", ['$injector', '$scope', HomeCtrl]);
ecmApp.controller("TopCtrl", ['$injector', '$scope', TopCtrl]);
ecmApp.controller("AuthCtrl", ['$injector', '$scope', AuthCtrl]);


//GERAIS
ecmApp.controller("TipoDocumentoConsultaCtrl", ['$injector', '$scope', TipoDocumentoConsultaCtrl]);
ecmApp.controller("TipoDocumentoCadastroCtrl", ['$injector', '$scope', TipoDocumentoCadastroCtrl]);


//------------------------------------------------------------------------------
//DIRETIVAS------------------------------------------------------------------------------
ecmApp.directive('requiredField', RequiredField);
ecmApp.directive('onLastRepeat', OnLastRepeat);
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
ecmApp.directive("maskedInput", MaskedInput);


//FILTROS------------------------------------------------------------------------------
ecmApp.filter("cpfFormato", [CpfFormato]);
ecmApp.filter("currencyFormat", [CurrencyFormatFilter]);


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
