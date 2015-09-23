package br.interactive.ecm.model.dto.seguranca;

import java.util.Calendar;

public class UsuarioDTO_Old {

    private Long id;
    private Integer csStatus;
    private Calendar dtDataAcesso;
    private String txLocale;
    private String txLogin;
    private String txMensagem;
    private String txSenha;
    private String txKeycloakId;
    private PessoaDTO pessoa;
    private Short nbTentativas;
    private Calendar dtDataTentativaLogin;

    public UsuarioDTO_Old() {
    }

    public UsuarioDTO_Old(Long id, Integer csStatus, Calendar dtDataAcesso, String txLocale, String txLogin, String txMensagem, String txSenha, String txKeycloakId, PessoaDTO pessoa, Short nbTentativas, Calendar dtDataTentativaLogin) {
        this.id = id;
        this.csStatus = csStatus;
        this.dtDataAcesso = dtDataAcesso;
        this.txLocale = txLocale;
        this.txLogin = txLogin;
        this.txMensagem = txMensagem;
        this.txSenha = txSenha;
        this.txKeycloakId = txKeycloakId;
        this.pessoa = pessoa;
        this.nbTentativas = nbTentativas;
        this.dtDataTentativaLogin = dtDataTentativaLogin;
    }

    public UsuarioDTO_Old(Long id, PessoaDTO pessoa) {
        this.id = id;
        this.pessoa = pessoa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public PessoaDTO getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaDTO pessoa) {
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
