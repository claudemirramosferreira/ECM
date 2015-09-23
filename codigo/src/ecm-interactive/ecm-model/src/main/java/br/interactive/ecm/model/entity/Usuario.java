package br.interactive.ecm.model.entity;

import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "USUARIO")
public class Usuario extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Getter @Setter
    @Id
    @GenericGenerator(name = "generatorUsuario", strategy = "foreign", parameters = @Parameter(name = "property", value = "pessoa"))
    @GeneratedValue(generator = "generatorUsuario")
    @Column(name = "ID_USUARIO", insertable = false, updatable = false)
    private Long id;

    @Getter @Setter
    @Column(name = "CS_STATUS", nullable = false)
    @NotNull
    private Integer csStatus;

    @Getter @Setter
    @Column(name = "DT_DATA_ACESSO")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dtDataAcesso;

    @Getter @Setter
    @Column(name = "TX_LOGIN")
    private String txLogin;

    @Getter @Setter
    @Column(name = "TX_MENSAGEM", length = 255)
    private String txMensagem;

    @Getter @Setter
    @Column(name = "TX_SENHA")
    private String txSenha;


    @Getter @Setter
    @OneToOne
    @JoinColumn(name = "ID_USUARIO")
    private Pessoa pessoa;

    @Getter @Setter
    @Column(name = "NB_TENTATIVAS")
    private Short nbTentativas;

    @Getter @Setter
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

}
