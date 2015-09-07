/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.interactive.ecm.model.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author robson.ramos
 */
@Entity
@Table(name = "tipo_documento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoDocumento.findAll", query = "SELECT t FROM TipoDocumento t"),
    @NamedQuery(name = "TipoDocumento.findByIdTipoDocumento", query = "SELECT t FROM TipoDocumento t WHERE t.idTipoDocumento = :idTipoDocumento"),
    @NamedQuery(name = "TipoDocumento.findByTxTipoDocumento", query = "SELECT t FROM TipoDocumento t WHERE t.txTipoDocumento = :txTipoDocumento"),
    @NamedQuery(name = "TipoDocumento.findByCsAtivo", query = "SELECT t FROM TipoDocumento t WHERE t.csAtivo = :csAtivo")})
public class TipoDocumento extends AbstractEntity {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_tipo_documento")
    private Long idTipoDocumento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "tx_tipo_documento")
    private String txTipoDocumento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cs_ativo")
    private boolean csAtivo;

    public TipoDocumento() {
    }

    public TipoDocumento(Long idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public TipoDocumento(Long idTipoDocumento, String txTipoDocumento, boolean csAtivo) {
        this.idTipoDocumento = idTipoDocumento;
        this.txTipoDocumento = txTipoDocumento;
        this.csAtivo = csAtivo;
    }
    
    @Override
    public Long getId() {
        return idTipoDocumento;
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

    public boolean getCsAtivo() {
        return csAtivo;
    }

    public void setCsAtivo(boolean csAtivo) {
        this.csAtivo = csAtivo;
    }
    
}
