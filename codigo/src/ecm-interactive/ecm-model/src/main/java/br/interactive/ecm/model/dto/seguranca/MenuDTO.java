package br.interactive.ecm.model.dto.seguranca;

import java.util.ArrayList;
import java.util.List;

public class MenuDTO implements Comparable<MenuDTO> {

    private Long idMenu;

    private String id;

    private String tipo;

    private String titulo;

    private String uri;

    private Boolean csMaster;

    private List<MenuDTO> subs = new ArrayList<MenuDTO>();

    public MenuDTO() {
    }

    public MenuDTO(String id, String tipo, String titulo, String uri, List<MenuDTO> subs) {
        this.id = id;
        this.tipo = tipo;
        this.titulo = titulo;
        this.uri = uri;
        this.subs = subs;
    }

    public MenuDTO(Long idMenu, String id, String titulo) {
        this.idMenu = idMenu;
        this.id = id;
        this.titulo = titulo;
    }

    public MenuDTO(Long idMenu, String id, String titulo, Boolean csMaster) {
        this.idMenu = idMenu;
        this.id = id;
        this.titulo = titulo;
        this.csMaster = csMaster;
    }

    public MenuDTO(Long idMenu, String id) {
        this.idMenu = idMenu;
        this.id = id;
    }

    public MenuDTO(Long idMenu) {
        this.idMenu = idMenu;
    }

    public MenuDTO(String id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime
                * result
                + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        MenuDTO other = (MenuDTO) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

    public String getId() {
        return id;
    }

    public Long getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Long idMenu) {
        this.idMenu = idMenu;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public List<MenuDTO> getSubs() {
        return subs;
    }

    public void setSubs(List<MenuDTO> subs) {
        this.subs = subs;
    }

    @Override
    public int compareTo(MenuDTO o) {
        return titulo.compareTo(o.titulo);
    }

    public Boolean getCsMaster() {
        return csMaster;
    }

    public void setCsMaster(Boolean csMaster) {
        this.csMaster = csMaster;
    }

}
