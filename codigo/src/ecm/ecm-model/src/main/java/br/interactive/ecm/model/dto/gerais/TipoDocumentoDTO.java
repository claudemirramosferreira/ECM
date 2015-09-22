package br.interactive.ecm.model.dto.gerais;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

public class TipoDocumentoDTO implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @Getter @Setter
    private Long id;
    
    @Getter @Setter
    private String txTipoDocumento;
    
    @Getter @Setter
    private Boolean csAtivo;

    public TipoDocumentoDTO(){ }

    public TipoDocumentoDTO(Long id, String txTipoDocumento, Boolean csAtivo) {
        this.id = id;
        this.txTipoDocumento = txTipoDocumento;
        this.csAtivo = csAtivo;
    }

}
