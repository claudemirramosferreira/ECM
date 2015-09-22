function Usuario($resource, ecmRest) {
    var path = ecmRest + "rest/usuario/";
    return $resource(path, {}, {
        buscarUsuarios: {
            method: "GET",
            url: path + "buscarUsuarios"
        },
        buscarUsuariosByNome: {
            method: "GET",
            url: path + "buscarUsuariosByNome"
        },
        alterarSenha: {
            method: "POST",
            url: path + "alterarSenha"
        }
    });
}
;