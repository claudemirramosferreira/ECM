function TipoDocumento($resource, ecmRest) {
    var path = ecmRest + "rest/usuario";
    return $resource(path, {}, {
        buscarPorDescricao: {
            method: "GET",
            url: path + "/"
        },
//        buscarTodos: {
//            method: "GET",
//            url: path + "/"
//        },
        buscarPorUserName: {
            method: "GET",
            url: path + "/userName"
        },
        salvar: {
            method: "POST",
            url: path + "/"
        },
        remover: {
            method: "DELETE",
            url: path + "/userName"
        },
        ativarOuInativar: {
            method: "PUT",
            url: path + "/userName"
        }
    });
}