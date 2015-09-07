function TipoDocumento($resource, ecmRest) {
    var path = ecmRest + "rest/tipoDocumento";
    return $resource(path, {}, {
        buscarPorDescricao: {
            method: "GET",
            url: path + "/"
        },
//        buscarTodos: {
//            method: "GET",
//            url: path + "/"
//        },
        buscarPorId: {
            method: "GET",
            url: path + "/id"
        },
        salvar: {
            method: "POST",
            url: path + "/"
        },
        remover: {
            method: "DELETE",
            url: path + "/id"
        },
        ativarOuInativar: {
            method: "PUT",
            url: path + "/id"
        }
    });
}