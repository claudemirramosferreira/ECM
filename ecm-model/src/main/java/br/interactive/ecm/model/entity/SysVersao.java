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
@Table(name = "sys_versao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SysVersao.findAll", query = "SELECT s FROM SysVersao s"),
    @NamedQuery(name = "SysVersao.findByIdSysVersao", query = "SELECT s FROM SysVersao s WHERE s.idSysVersao = :idSysVersao"),
    @NamedQuery(name = "SysVersao.findByTxVersao", query = "SELECT s FROM SysVersao s WHERE s.txVersao = :txVersao")})
public class SysVersao extends AbstractEntity {
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_sys_versao")
    private Long idSysVersao;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "tx_versao")
    private String txVersao;

    public SysVersao() {
    }

    public SysVersao(Long idSysVersao) {
        this.idSysVersao = idSysVersao;
    }

    public SysVersao(Long idSysVersao, String txVersao) {
        this.idSysVersao = idSysVersao;
        this.txVersao = txVersao;
    }

    @Override
    public Long getId() {
        return idSysVersao;
    }
    
    public Long getIdSysVersao() {
        return idSysVersao;
    }

    public void setIdSysVersao(Long idSysVersao) {
        this.idSysVersao = idSysVersao;
    }

    public String getTxVersao() {
        return txVersao;
    }

    public void setTxVersao(String txVersao) {
        this.txVersao = txVersao;
    }

    
}
