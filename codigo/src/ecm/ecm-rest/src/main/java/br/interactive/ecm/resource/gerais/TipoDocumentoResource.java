package br.interactive.ecm.resource.gerais;

import br.interactive.ecm.message.Message;
import br.interactive.ecm.model.dto.gerais.TipoDocumentoDTO;
import br.interactive.ecm.model.entity.TipoDocumento;
import br.interactive.ecm.response.GridResponse;
import br.interactive.ecm.response.Response;
import br.interactive.ecm.service.gerais.TipoDocumentoService;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("tipoDocumento")
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class TipoDocumentoResource {

    @Inject
    private TipoDocumentoService impactoFacade;

    @GET
    @Path("buscarPorDescricaoOuSituacao")
    public GridResponse<TipoDocumentoDTO> buscarPorDescricaoOuSituacao(
            @QueryParam("descricao") String descricao,
            @QueryParam("situacao") String situacao,
            @QueryParam("limit") int limit,
            @QueryParam("page") int page) {
        final int[] range = {page, limit};
        GridResponse<TipoDocumentoDTO> response = impactoFacade.buscarPorDescricaoOuSituacao(descricao, situacao, range);
        return response;
    }

    @POST
    public Response<String> inserir(List<TipoDocumento> impactos) {
        return new Response<>(impactoFacade.inserir(impactos));
    }

    @DELETE
    public Response<Message> remover(@QueryParam("id") Long id) {
        return new Response<>(impactoFacade.remover(id));
    }

    @GET
    @Path("ativarOuInativar")
    public Response<Long> ativarOuInativar(@QueryParam("id") Long id) {
        return new Response<>(impactoFacade.ativarOuInativar(id));
    }

    @GET
    @Path("buscarPorDescricao")
    public Response<String> buscarPorDescricao(@QueryParam("descricao") String descricao) {
        return new Response<>(impactoFacade.buscarPorDescricao(descricao));
    }

    @GET
    public Response<TipoDocumentoDTO> buscarPorId(@QueryParam("id") Long id) {
        return new Response<>(impactoFacade.buscarPorId(id));
    }

    @PUT
    public Response<String> alterar(TipoDocumento impactoModel) {
        return new Response<>(impactoFacade.alterar(impactoModel));
    }

    @GET
    @Path("buscarTodos")
    public Response<List<TipoDocumentoDTO>> buscarTodos() {
        return new Response<>(impactoFacade.buscarTodos());
    }
}
