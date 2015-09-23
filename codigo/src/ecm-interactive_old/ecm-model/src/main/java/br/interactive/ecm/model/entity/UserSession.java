/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.interactive.ecm.model.entity;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user_session")
@NamedQueries({
    @NamedQuery(name = "UserSession.findAll", query = "SELECT u FROM UserSession u"),
    @NamedQuery(name = "UserSession.findByIdUserSession", query = "SELECT u FROM UserSession u WHERE u.idUserSession = :idUserSession"),
    @NamedQuery(name = "UserSession.findByTxLogin", query = "SELECT u FROM UserSession u WHERE u.txLogin = :txLogin"),
    @NamedQuery(name = "UserSession.findByTxToken", query = "SELECT u FROM UserSession u WHERE u.txToken = :txToken"),
    @NamedQuery(name = "UserSession.findByTxIpAdress", query = "SELECT u FROM UserSession u WHERE u.txIpAdress = :txIpAdress"),
    @NamedQuery(name = "UserSession.findByTxBrowser", query = "SELECT u FROM UserSession u WHERE u.txBrowser = :txBrowser"),
    @NamedQuery(name = "UserSession.findByDtStartOrRefreshSession", query = "SELECT u FROM UserSession u WHERE u.dtStartOrRefreshSession = :dtStartOrRefreshSession"),
    @NamedQuery(name = "UserSession.findByDtExpiredSession", query = "SELECT u FROM UserSession u WHERE u.dtExpiredSession = :dtExpiredSession")})
public class UserSession extends AbstractEntity {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_user_session")
    private Long idUserSession;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "tx_login")
    private String txLogin;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "tx_token")
    private String txToken;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "tx_ip_adress")
    private String txIpAdress;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "tx_browser")
    private String txBrowser;
    
    @Column(name = "dt_start_or_refresh_session")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtStartOrRefreshSession;
    
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

    
    public Long getIdUserSession() {
        return idUserSession;
    }

    public void setIdUserSession(Long idUserSession) {
        this.idUserSession = idUserSession;
    }

    public String getTxLogin() {
        return txLogin;
    }

    public void setTxLogin(String txLogin) {
        this.txLogin = txLogin;
    }

    public String getTxToken() {
        return txToken;
    }

    public void setTxToken(String txToken) {
        this.txToken = txToken;
    }

    public String getTxIpAdress() {
        return txIpAdress;
    }

    public void setTxIpAdress(String txIpAdress) {
        this.txIpAdress = txIpAdress;
    }

    public String getTxBrowser() {
        return txBrowser;
    }

    public void setTxBrowser(String txBrowser) {
        this.txBrowser = txBrowser;
    }

    public Date getDtStartOrRefreshSession() {
        return dtStartOrRefreshSession;
    }

    public void setDtStartOrRefreshSession(Date dtStartOrRefreshSession) {
        this.dtStartOrRefreshSession = dtStartOrRefreshSession;
    }

    public Date getDtExpiredSession() {
        return dtExpiredSession;
    }

    public void setDtExpiredSession(Date dtExpiredSession) {
        this.dtExpiredSession = dtExpiredSession;
    }

}
