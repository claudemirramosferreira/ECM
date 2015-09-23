package br.interactive.ecm.model.entity;

import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "USUARIO")
@NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
public class Usuario extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "generatorUsuario", strategy = "foreign", parameters = @Parameter(name = "property", value = "pessoa"))
    @GeneratedValue(generator = "generatorUsuario")
    @Column(name = "ID_USUARIO", insertable = false, updatable = false)
    private Long id;

    @Column(name = "CS_STATUS", nullable = false)
    @NotNull
    private Integer csStatus;

    @Column(name = "DT_DATA_ACESSO")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dtDataAcesso;

    @Column(name = "TX_LOCALE")
    private String txLocale;

    @Column(name = "TX_LOGIN")
    private String txLogin;

    @Column(name = "TX_MENSAGEM", length = 255)
    private String txMensagem;

    @Column(name = "TX_SENHA")
    private String txSenha;

    @Column(name = "TX_KEYCLOAK_ID")
    private String txKeycloakId;

    //bi-directional one-to-one association to Pessoa
    @OneToOne
    @JoinColumn(name = "ID_USUARIO")
    private Pessoa pessoa;

//    //bi-directional many-to-one association to UsuarioPerfil
//    @OneToMany(mappedBy = "usuario", cascade = {CascadeType.ALL})
//    @Cascade({
//        org.hibernate.annotations.CascadeType.SAVE_UPDATE,
//        //org.hibernate.annotations.CascadeType.DETACH,
//        org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
//    private Set<UsuarioPerfil> usuarioPerfils = new HashSet<UsuarioPerfil>();
//
//    //bi-directional many-to-one association to UsuarioSenha
//    @OneToMany(mappedBy = "usuario", cascade = {CascadeType.ALL})
//    private List<UsuarioSenha> usuarioSenhas = new ArrayList<UsuarioSenha>();
//
//    //bi-directional many-to-one association to UsuarioStatus
//    @OneToMany(mappedBy = "usuario", cascade = {CascadeType.ALL})
//    private List<UsuarioStatus> usuarioStatus = new ArrayList<>();

    @Column(name = "NB_TENTATIVAS")
    private Short nbTentativas;

    @Column(name = "DT_DATA_TENTATIVA_LOGIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dtDataTentativaLogin;

    public Usuario() {
    }

    public Usuario(Long id, String txLogin, String txSenha, Integer csStatus, Calendar dtDataAcesso, Short nbTentativas, Calendar dtDataTentativaLogin) {
        this.id = id;
        this.csStatus = csStatus;
        this.dtDataAcesso = dtDataAcesso;
        this.txLogin = txLogin;
        this.txSenha = txSenha;
        this.nbTentativas = nbTentativas;
        this.dtDataTentativaLogin = dtDataTentativaLogin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long idUsuario) {
        this.id = idUsuario;
    }

    public Integer getCsStatus() {
        return csStatus;
    }

    public void setCsStatus(Integer csStatus) {
        this.csStatus = csStatus;
    }

    public Calendar getDtDataAcesso() {
        return dtDataAcesso;
    }

    public void setDtDataAcesso(Calendar dtDataAcesso) {
        this.dtDataAcesso = dtDataAcesso;
    }

    public String getTxLocale() {
        return txLocale;
    }

    public void setTxLocale(String txLocale) {
        this.txLocale = txLocale;
    }

    public String getTxLogin() {
        return txLogin;
    }

    public void setTxLogin(String txLogin) {
        this.txLogin = txLogin;
    }

    public String getTxMensagem() {
        return txMensagem;
    }

    public void setTxMensagem(String txMensagem) {
        this.txMensagem = txMensagem;
    }

    public String getTxSenha() {
        return txSenha;
    }

    public void setTxSenha(String txSenha) {
        this.txSenha = txSenha;
    }

    public String getTxKeycloakId() {
        return txKeycloakId;
    }

    public void setTxKeycloakId(String txKeycloakId) {
        this.txKeycloakId = txKeycloakId;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Short getNbTentativas() {
        return nbTentativas;
    }

    public void setNbTentativas(Short nbTentativas) {
        this.nbTentativas = nbTentativas;
    }

    public Calendar getDtDataTentativaLogin() {
        return dtDataTentativaLogin;
    }

    public void setDtDataTentativaLogin(Calendar dtDataTentativaLogin) {
        this.dtDataTentativaLogin = dtDataTentativaLogin;
    }

}
