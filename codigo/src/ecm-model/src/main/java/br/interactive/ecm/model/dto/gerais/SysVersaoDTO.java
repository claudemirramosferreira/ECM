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
public class SysVersaoDTO {
    
    private String txVersao;

    public SysVersaoDTO(String txVersao) {
        this.txVersao = txVersao;
    }

    public String getTxVersao() {
        return txVersao;
    }

    public void setTxVersao(String txVersao) {
        this.txVersao = txVersao;
    }
    
}
