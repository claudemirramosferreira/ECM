function TipoDocumento($resource, ecmRest) {
    var path = ecmRest + "rest/tipoDocumento";
    return $resource(path, {}, {
        buscarPorDescricaoOuSituacao: {
            method: "GET",
            url: path + "/buscarPorDescricaoOuSituacao"
        },
        ativarOuInativar: {
            method: "GET",
            url: path + "/ativarOuInativar"
        },
        buscarPorDescricao: {
            method: "GET",
            url: path + "/buscarPorDescricao"
        },
        alterar: {
            method: "PUT"
        },
        buscarTodos: {
            method: "GET",
            url: path + "/buscarTodos"
        }
    });
}