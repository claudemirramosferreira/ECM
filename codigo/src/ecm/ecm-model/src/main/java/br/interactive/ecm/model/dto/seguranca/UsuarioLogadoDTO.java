package br.interactive.ecm.model.dto.seguranca;

import br.interactive.ecm.model.entity.Usuario;
import java.io.Serializable;
import java.util.List;

/**
 * Representa o usuario logado no sistema.
 * @author robson.ramos
 */
public class UsuarioLogadoDTO implements Serializable {

    /**
     * Serial.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Nome da pessoa logada.
     */
    private String nome;

    private String senha;

    private String login;

    private boolean logged;

    private String dthrLogin;

    private Usuario usuarioLogado;

    private List<RoleDTO> permissions;

    private String token;

    private Long idUsuario;

    /**
     * Situacao do usuario.
     */
    private Short situacao;

    private long tentativasLogar;

    public UsuarioLogadoDTO() {
    }

    public UsuarioLogadoDTO(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public UsuarioLogadoDTO(String nome, String login, Short situacao, Long idUsuario, String dthrLogin) {
        this.nome = nome;
        this.login = login;
        this.situacao = situacao;
        this.idUsuario = idUsuario;
        this.dthrLogin = dthrLogin;
    }

    public UsuarioLogadoDTO(String nome, String login, Long idUsuario ) {
        this.nome = nome;
        this.login = login;
        this.idUsuario = idUsuario;
    }

    public boolean checkLogIn(String login, String senha) {
        return logged = login.equalsIgnoreCase(senha);
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public String getdthrLogin() {
        return dthrLogin;
    }

    public String getLogin() {
        return login;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public List<RoleDTO> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<RoleDTO> permissions) {
        this.permissions = permissions;
    }

    public Short getSituacao() {
        return situacao;
    }

    public void setSituacao(Short situacao) {
        this.situacao = situacao;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getTentativasLogar() {
        return tentativasLogar;
    }

    public void setTentativasLogar(long tentativasLogar) {
        this.tentativasLogar = tentativasLogar;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

}
