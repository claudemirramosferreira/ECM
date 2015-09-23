package br.interactive.ecm.resource.seguranca;

import br.interactive.ecm.model.dto.seguranca.LoginDTO;
import br.interactive.ecm.model.entity.Usuario;
import br.interactive.ecm.response.Response;
import br.interactive.ecm.service.seguranca.UsuarioService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/usuario")
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class UsuarioResource {

    @Inject
    private UsuarioService usuarioService;

    @POST
    @Path("login")
    public Response<LoginDTO> login(LoginDTO loginDTO, @Context HttpServletRequest req) {
        return new Response<>(usuarioService.login(loginDTO, req));
    }

    @GET
    @Path("logout")
    public boolean logout(@Context HttpServletRequest req) {
        return usuarioService.logout(req);
    }

}
