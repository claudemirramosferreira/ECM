package br.interactive.ecm.model.dto.seguranca;

import br.com.interactive.alfresco.model.Usuario;

import lombok.Getter;
import lombok.Setter;

/**
 * @author claudemir
 */
public class UsuarioDTO {

    @Getter @Setter
    private String userName;

    @Getter @Setter
    private String firstName;

    @Getter @Setter
    private String lastName;

    @Getter @Setter
    private String email;

    @Getter @Setter
    private String ticket;

    public UsuarioDTO() { }

    public UsuarioDTO(String userName, String firstName, String lastName, String email) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public UsuarioDTO convertToUsuarioDTO(Usuario usuario) {
        this.userName = usuario.getUserName();
        this.firstName = usuario.getFirstName();
        this.lastName = usuario.getLastName();
        this.email = usuario.getEmail();

        return this;
    }

}
