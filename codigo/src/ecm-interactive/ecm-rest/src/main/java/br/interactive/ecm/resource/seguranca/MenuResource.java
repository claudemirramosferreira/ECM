package br.interactive.ecm.resource.seguranca;

import br.interactive.ecm.model.dto.seguranca.MenuDTO;
import br.interactive.ecm.response.Response;
import br.interactive.ecm.service.seguranca.MenuService;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/menu")
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class MenuResource {

    @Inject
    private MenuService servico;

//    @GET
//    @Path("/acesso")
//    public Response<List<MenuDTO>> getAcessoUsuario(@Context HttpServletRequest req) {
//        return new Response<>(servico.getMenuByUsuario(req));
//    }

}
