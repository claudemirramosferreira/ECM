package br.interactive.ecm.resource.seguranca;

import br.interactive.ecm.message.InfoMessage;
import br.interactive.ecm.message.Message;
import br.interactive.ecm.model.dto.seguranca.LoginDTO;
import br.interactive.ecm.model.dto.seguranca.UsuarioSimplesDTO;
import br.interactive.ecm.model.entity.Usuario;
import br.interactive.ecm.response.Response;
import br.interactive.ecm.service.seguranca.UsuarioService;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * ServiÃ§o Rest que prover informaÃ§Ãµes de {@link Usuario}.
 *
 * @author robson.ramos
 */
@Path("/usuario")
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class UsuarioResource {

    /**
     * Facade de usuario.
     */
    @Inject
    private UsuarioService usuarioFacade;

    @Inject
    private HttpServletRequest httpServletRequest;

    @Path("/pesquisa-status-cpf")
    @GET
    public Response<String> getStatusCfp(@QueryParam("cpf") String cpf) {
        return usuarioFacade.getStatusCfp(cpf);
    }

    /**
     * Faz a validacao da senha
     *
     * @param usuario
     * @return Response<>
     */
    @GET
    @Path("/recuperarSenha")
    public Response<Message> recuperarSenha(@QueryParam("usuario") String usuario) {
        Response<Message> resposta = new Response<>();
        Message message = new InfoMessage("msg.usuario.senhaenviada");        
        
//        try {
//        EmailDTO remetente = usuarioFacade.buscarInfoEmail();
//        EmailDTO email = usuarioFacade.recuperarSenha(usuario);    
//        email.setTxRemetente(remetente.getTxRemetente());
//
//        String hostname = usuarioFacade.obterHost(httpServletRequest);
//        ResteasyClient client = new ResteasyClientBuilder().build();
//        ResteasyWebTarget target = client.target(hostname + "/ehs-email/rest/enviar");
//       
//        javax.ws.rs.core.Response response = target.request(MediaType.APPLICATION_JSON).post( Entity.entity(email, "application/json"));
//
//        if(response.getStatus()==204){            
//            resposta.setElement(message);           
//        }else{
//         message = new ErrorMessage("msg.usuario.erroaoenviaremail");
//         resposta.setElement(message);           
//        }
//        
//        } catch (Exception ex) {
//            Logger.getLogger(UsuarioResource.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        return resposta;
    }


    /**
     * Faz a validacao de login ja existente
     *
     * @param cpf , login
     * @param login
     */
    @GET
    @Path("/valida-login")
    public void validaLogin(@QueryParam("cpf") String cpf, @QueryParam("login") String login) {
        usuarioFacade.validarLogin(cpf, login);
    }

    @POST
    @Path("login")
    public Response<LoginDTO> login(LoginDTO loginDTO, @Context HttpServletRequest req) {
        return new Response<>(usuarioFacade.login(loginDTO, req));
    }

    @GET
    @Path("isConnected")
    public boolean isConnected(@Context HttpServletRequest req) {
        return usuarioFacade.isConnected(req);
    }

    @GET
    @Path("getUser")
    public Response<LoginDTO> getUser(@Context HttpServletRequest req) {
        return new Response<>(usuarioFacade.isGetUser(req));
    }

    @GET
    @Path("logout")
    public boolean logout(@Context HttpServletRequest req) {
        return usuarioFacade.logout(req);
    }

    @POST
    @Path("alterarSenha")
    public Response<String> alterarSenha(LoginDTO loginDTO) {
//        return new Response<>(usuarioFacade.alterarSenha(loginDTO));
        return null;
    }

    @GET
    @Path("/buscarUsuariosByNome")
    public Response<List<UsuarioSimplesDTO>> getUsuarioByNome(@QueryParam("txNome") String txNome) {
        return new Response<>(usuarioFacade.getUsuarioByNome(txNome));
    }

}
