package br.interactive.ecm.gerais.service;

import br.interactive.ecm.message.Message;
import br.interactive.ecm.model.dao.gerais.TipoDocumentoDAO;
import br.interactive.ecm.model.dto.gerais.TipoDocumentoDTO;
import br.interactive.ecm.model.entity.TipoDocumento;
import br.interactive.ecm.response.GridResponse;
import javax.ejb.Stateless;

import javax.inject.Inject;

/**
 * Service de TipoDocumento.
 *
 * @author robson.ramos
 */
@Stateless
public class TipoDocumentoService {

    
    @Inject
    private TipoDocumentoDAO dao;
    
    public GridResponse<TipoDocumentoDTO> getAll() {
        return dao.getAll();
    }
    
    public Message salvar(TipoDocumento tipoDocumento) {
        return null;        // FIXME continuar
    }
    
    public Message remover(Long id) {
//        return dao.remove(id);        // FIXME continuar
        return null;
    }
    
    
    public Message ativarInativar(Long id) {
//        return dao.remove(id);        // FIXME continuar
        return null;
    }
    
}
