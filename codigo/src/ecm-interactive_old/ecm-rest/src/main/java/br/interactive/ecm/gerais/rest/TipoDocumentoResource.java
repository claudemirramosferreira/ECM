package br.interactive.ecm.gerais.rest;

import br.interactive.ecm.gerais.service.TipoDocumentoService;
import br.interactive.ecm.message.Message;
import br.interactive.ecm.model.dto.gerais.TipoDocumentoDTO;
import br.interactive.ecm.model.entity.TipoDocumento;
import br.interactive.ecm.response.GridResponse;
import br.interactive.ecm.response.Response;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Servico Rest de {@link TipoDocumento}.
 */
@Path("/tipoDocumento")
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class TipoDocumentoResource {

    /**
     * Service de TipoDocumento.
     */
    @Inject
    private TipoDocumentoService service;

    /**
     * Obtem todos os TipoDocumentos.
     * @return String
     */
    @GET
    public GridResponse<TipoDocumentoDTO> getAll() {
        return service.getAll();
    }
    
    @GET
    @Path("/{descricao}")
    public GridResponse<TipoDocumentoDTO> getByDescricao(
                    @PathParam("descricao") String descricao,
                    @QueryParam("situacao") String situacao,
                    @QueryParam("limit") int limit,
                    @QueryParam("page") int page) {
        final int[] range = {page, limit};
        return service.getByDescricao(descricao, situacao, range);
    }
    
    @GET
    @Path("/{id}")
    public Response<TipoDocumentoDTO> getById(@PathParam ("id") Long id) {
        return null;
//        return service.getAll();    // FIXME continue
    }
    
    @POST   // FIXME tipo retorno
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response<Message> salvar(TipoDocumento tipoDocumento) {
        return new Response<>(service.salvar(tipoDocumento));
    }
    
    @DELETE   // FIXME tipo retorno
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response<Message> remover(@PathParam ("id") Long id) {
        return new Response<>(service.remover(id));
    }
    
    @PUT
    @Path("/{id}")
    public Response<Message> ativarInativar(@PathParam ("id") Long id) {
        return new Response<>(service.ativarInativar(id));    // FIXME continue
    }
    
    
    
}
