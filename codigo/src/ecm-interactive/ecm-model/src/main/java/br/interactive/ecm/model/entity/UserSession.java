package br.interactive.ecm.model.entity;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_session")
public class UserSession extends AbstractEntity {
    
    private static final long serialVersionUID = 1L;
    
    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_user_session")
    private Long idUserSession;
    
    @Getter @Setter
    @Basic(optional = false)
    @NotNull
    @Column(name = "tx_login")
    private String txLogin;
    
    @Getter @Setter
    @Basic(optional = false)
    @NotNull
    @Column(name = "tx_token")
    private String txToken;
    
    @Getter @Setter
    @Basic(optional = false)
    @NotNull
    @Column(name = "tx_ip_adress")
    private String txIpAdress;
    
    @Getter @Setter
    @Basic(optional = false)
    @NotNull
    @Column(name = "tx_browser")
    private String txBrowser;
    
    @Getter @Setter
    @Column(name = "dt_start_or_refresh_session")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtStartOrRefreshSession;
    
    @Getter @Setter
    @Column(name = "dt_expired_session")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtExpiredSession;
    

    public UserSession() {
    }

    public UserSession(Long idUserSession) {
        this.idUserSession = idUserSession;
    }

    public UserSession(Long idUserSession, String txLogin, String txToken, String txIpAdress, String txBrowser) {
        this.idUserSession = idUserSession;
        this.txLogin = txLogin;
        this.txToken = txToken;
        this.txIpAdress = txIpAdress;
        this.txBrowser = txBrowser;
    }

    @Override
    public Long getId() {
        return idUserSession;
    }

}
