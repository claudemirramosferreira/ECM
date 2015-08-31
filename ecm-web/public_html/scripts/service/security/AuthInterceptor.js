
function AuthInterceptor($q, $location, $rootScope, $cookies){
    return {
        
        request : function(config) {
            
            if(!$cookies.get('TOKEN') && !(/.*\/autenticar\/.*/g.test($location.path()))){
                $location.path("/auth/user");
            }
            
            if(/.*\/seguranca\/.*/g.test($location.path())){
                $rootScope.menuGeral = 2;
            }else{
                $rootScope.menuGeral = 1;
            }
            
            if(/.*\/autenticar\/.*/g.test($location.path())){
                $rootScope.externo = 'usuario';
            }else{
                $rootScope.externo = '';
            }
            
            if(/.*\/rest\/.*/g.test(config.url)){
                $rootScope.carregandoScreen++;
            }
            
            return config || $q.when(config);
        },
        
        requestError:function(rejection){
            $rootScope.carregandoScreen = 0;
            return $q.reject(rejection);
        },
        
        response : function(response) {
            if(/.*\/rest\/.*/g.test(response.config.url)){
                $rootScope.carregandoScreen--;
            }
            
            //controle de acesso
            var checkPermission = new RegExp('(\\[' + $location.path().replace(/^[\/]+/, '') + '\\]){1}', 'g');
            if (!/.*\/(views|rest)\/.*/g.test($location.path()) && !checkPermission.test($rootScope.permissaoUsuario)) {
//                $location.path('/acesso-negado');
//                return $q.reject(response);
            }
            
            return response || $q.when(response);
        },
        
        responseError: function(rejection) {
            $rootScope.carregandoScreen = 0;
            return $q.reject(rejection);
        }
        
    };
}