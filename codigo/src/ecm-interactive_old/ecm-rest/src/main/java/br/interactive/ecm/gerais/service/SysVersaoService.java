package br.interactive.ecm.gerais.service;

import br.interactive.ecm.model.dao.gerais.SysVersaoDAO;
import br.interactive.ecm.model.dto.gerais.SysVersaoDTO;
import br.interactive.ecm.response.GridResponse;
import javax.ejb.Stateless;

import javax.inject.Inject;

/**
 * Service de SysVersao.
 *
 * @author robson.ramos
 */
@Stateless
public class SysVersaoService {

    
    @Inject
    private SysVersaoDAO dao;
    
    
    public SysVersaoDTO getCurrentVersion() {
        return dao.getCurrentVersion();
    }
    
    public GridResponse<SysVersaoDTO> getAllVersion() {
        return dao.getAllVersion();
    }
    
}
