function Usuario($resource, ecmRest) {
    var path = ecmRest + "rest/usuario/";
    return $resource(path, {}, {
        buscarUsuarios: {
            method: "GET",
            url: path + "buscarUsuarios"
        },
        alterarSenha: {
            method: "POST",
            url: path + "alterarSenha"
        }
    });
}
;