function Auth($resource, ecmRest) {
    
    var path = ecmRest + 'rest/usuario/';
    
    return $resource(path, {},{
        login:{
            method:'POST',
            url: path + 'login'
        },
        isConnected:{
            method:'GET',
            url: path + 'isConnected'
        },
        getUser:{
            method:'GET',
            url: path + 'getUser'
        },
        logout:{
            method:'GET',
            url: path + 'logout'
        },
        recuperarSenha:{
            method:'GET',
            url:path + 'recuperarSenha'
        }
    });
}