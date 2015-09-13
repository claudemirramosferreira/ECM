/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.interactive.ecm.model.dto.gerais;

/**
 *
 * @author robson.ramos
 */
public class TipoDocumentoDTO {
    
    private Long idTipoDocumento;

    private String txTipoDocumento;
    
    private boolean csAtivo;
    
    public TipoDocumentoDTO(Long idTipoDocumento, String txTipoDocumento, boolean csAtivo) {
        this.idTipoDocumento = idTipoDocumento;
        this.txTipoDocumento = txTipoDocumento;
        this.csAtivo = csAtivo;
    }

    public Long getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(Long idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public String getTxTipoDocumento() {
        return txTipoDocumento;
    }

    public void setTxTipoDocumento(String txTipoDocumento) {
        this.txTipoDocumento = txTipoDocumento;
    }

    public boolean isCsAtivo() {
        return csAtivo;
    }

    public void setCsAtivo(boolean csAtivo) {
        this.csAtivo = csAtivo;
    }
    
    
}
