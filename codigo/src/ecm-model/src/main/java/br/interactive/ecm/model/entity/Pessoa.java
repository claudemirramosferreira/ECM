package br.interactive.ecm.model.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

@Entity
@Table(name = "PESSOA")
@SequenceGenerator(name = "idPessoaSequence", sequenceName = "pessoa_id_seq", allocationSize = 1)
public class Pessoa extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "idPessoaSequence")
    @Column(name = "ID_PESSOA", unique = true, nullable = false)
    @NotNull
    private Long id;

    /* 
     * o tipo de dados foi mudado por motivo de compatibilidade entre os bancos 
     *SqlServer, Oracle e PostgreSQL 
     */
    @Column(name = "CS_SEXO", nullable = false, length = 1)
    @NotNull
    private String csSexo;

    @Column(name = "DT_DATA_NASCIMENTO", nullable = false)
    @NotNull
    private Calendar dtDataNascimento;

    @Column(name = "ID_SETOR", nullable = true)
    private Long idSetor;

    @Column(name = "NB_ESTADO_EMISSOR")
    private Integer nbEstadoEmissor;

    @Column(name = "TX_CELULAR", length = 15)
    private String txCelular;

    @Column(name = "TX_CPF", length = 11)
    private String txCpf;

    @Email
    @Column(name = "TX_EMAIL", length = 150)
    private String txEmail;

    @Column(name = "TX_FONE_COMERCIAL", length = 15)
    private String txFoneComercial;

    @Column(name = "TX_FONE_RESIDENCIAL", length = 15)
    private String txFoneResidencial;

    @Column(name = "TX_NOME", nullable = false, length = 100)
    @NotNull
    private String txNome;

    @Column(name = "TX_REGISTRO_PROFISSIONAL", length = 15)
    private String txRegistroProfissional;

    @Column(name = "TX_RG", length = 20)
    private String txRg;

    //bi-directional one-to-one association to Usuario
    @OneToOne(mappedBy = "pessoa")
    private Usuario usuario;

    
    public Long getId() {
        return id;
    }

    public void setId(Long idPessoa) {
        this.id = idPessoa;
    }

    public String getCsSexo() {
        return csSexo;
    }

    public void setCsSexo(String csSexo) {
        this.csSexo = csSexo;
    }

    public Calendar getDtDataNascimento() {
        return dtDataNascimento;
    }

    public void setDtDataNascimento(Calendar dtDataNascimento) {
        this.dtDataNascimento = dtDataNascimento;
    }

    public Long getIdSetor() {
        return idSetor;
    }

    public void setIdSetor(Long idSetor) {
        this.idSetor = idSetor;
    }

    public Integer getNbEstadoEmissor() {
        return nbEstadoEmissor;
    }

    public void setNbEstadoEmissor(Integer nbEstadoEmissor) {
        this.nbEstadoEmissor = nbEstadoEmissor;
    }

    public String getTxCelular() {
        return txCelular;
    }

    public void setTxCelular(String txCelular) {
        this.txCelular = txCelular;
    }

    public String getTxCpf() {
        return txCpf;
    }

    public void setTxCpf(String txCpf) {
        this.txCpf = txCpf;
    }

    public String getTxEmail() {
        return txEmail;
    }

    public void setTxEmail(String txEmail) {
        this.txEmail = txEmail;
    }

    public String getTxFoneComercial() {
        return txFoneComercial;
    }

    public void setTxFoneComercial(String txFoneComercial) {
        this.txFoneComercial = txFoneComercial;
    }

    public String getTxFoneResidencial() {
        return txFoneResidencial;
    }

    public void setTxFoneResidencial(String txFoneResidencial) {
        this.txFoneResidencial = txFoneResidencial;
    }

    public String getTxNome() {
        return txNome;
    }

    public void setTxNome(String txNome) {
        this.txNome = txNome;
    }

    public String getTxRegistroProfissional() {
        return txRegistroProfissional;
    }

    public void setTxRegistroProfissional(String txRegistroProfissional) {
        this.txRegistroProfissional = txRegistroProfissional;
    }

    public String getTxRg() {
        return txRg;
    }

    public void setTxRg(String txRg) {
        this.txRg = txRg;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}