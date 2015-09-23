package br.interactive.ecm.model.entity;

import java.util.Calendar;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import org.hibernate.validator.constraints.Length;

/**
 * The persistent class for the SYS_VERSAO database table.
 *
 */
@Entity
@Table(name = "SYS_VERSAO")
@SequenceGenerator(name = "idSysVersao", sequenceName = "SYS_VERSAO_ID_SEQ")
public class SysVersao {

    private static final long serialVersionUID = 1L;

    @Getter @Setter
    @Id
    @Column(name = "ID_SYS_VERSAO", unique = true, nullable = true)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "idSysVersao")
    @NotNull
    private Long id;
    
    @Getter @Setter
    @Column(name = "DT_ATUALIZACAO")
    private Calendar dtAtualizacao;

    @Getter @Setter
    @Column(name = "TX_EXTERNA", length = 20)
    @Length(max = 20)
    @NotNull
    private String txExterna;

    @Getter @Setter
    @Column(name = "TX_INTERNA", length = 20)
    @Length(max = 20)
    @NotNull
    private String txInterna;

    @Getter @Setter
    @Column(name = "VERSAO")
    private BigDecimal versao;

    public SysVersao() {
    }


}
