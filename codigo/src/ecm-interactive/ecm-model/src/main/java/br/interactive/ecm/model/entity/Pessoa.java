package br.interactive.ecm.model.entity;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Basic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import org.hibernate.validator.constraints.Email;

/**
 * The persistent class for the PESSOA database table.
 */
@Entity
@Table(name = "PESSOA")
@SequenceGenerator(name = "idPessoaSequence", sequenceName = "PESSOA_ID_SEQ", allocationSize = 1)
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idPessoaSequence")
    @Column(name = "ID_PESSOA", unique = true, nullable = false)
    @NotNull
    private Long id;

    @Getter @Setter
    @Basic(optional = false)
    @NotNull
    @Column(name = "DT_DATA_NASCIMENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dtDataNascimento;

    @Getter @Setter
    @Column(name = "CS_SEXO", nullable = false, length = 1)
    @NotNull
    private String csSexo;

    @Getter @Setter
    @Column(name = "TX_CELULAR", length = 15)
    private String txCelular;

    @Getter @Setter
    @Column(name = "TX_CPF", length = 11)
    private String txCpf;

    @Getter @Setter
    @Email
    @Column(name = "TX_EMAIL", length = 150)
    private String txEmail;

    @Getter @Setter
    @Column(name = "TX_NOME", nullable = false, length = 100)
    @NotNull
    private String txNome;

    @Getter @Setter
    @Column(name = "TX_RG", length = 20)
    private String txRg;


    public Pessoa() {
    }

}
