package br.interactive.ecm.gerais.rest;

import br.interactive.ecm.model.entity.SysVersao;
import br.interactive.ecm.response.Response;
import br.interactive.ecm.gerais.service.SysVersaoService;
import br.interactive.ecm.model.dto.gerais.SysVersaoDTO;
import br.interactive.ecm.response.GridResponse;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Servico Rest de {@link SysVersao}.
 */
@Path("/sysVersao")
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class SysVersaoResource {

    /**
     * Service de SysVersao.
     */
    @Inject
    private SysVersaoService service;

    /**
     * Obtem a versão do sistema
     * @return String
     */
    @Path("currentVersion")
    @GET
    public Response<SysVersaoDTO> getCurrentVersion() {
        return new Response<SysVersaoDTO>(service.getCurrentVersion());
    }
    
    
    /**
     * Obtem a versão do sistema
     * @return String
     */
    @Path("allVersions")
    @GET
    public GridResponse<SysVersaoDTO> getVersaoSistema() {
        return service.getAllVersion();
    }
    
}
