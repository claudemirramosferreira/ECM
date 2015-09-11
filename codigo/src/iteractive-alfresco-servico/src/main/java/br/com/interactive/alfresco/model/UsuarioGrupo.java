package br.com.interactive.alfresco.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

/**
 * Classe responsavel por representar o objto json retornado pelo alfesco.
 * Representa o usuario de um grupo
 * 
 * @author ELIMARCOSAROUCA
 */
public class UsuarioGrupo {

	private String authorityType; // "USER"
	private String shortName; // "admin"
	private String fullName; // "Administrator "
	private String displayName; // "Administrator "
	
	public String getAuthorityType() {
		return authorityType;
	}
	public void setAuthorityType(String authorityType) {
		this.authorityType = authorityType;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	public static Collection<UsuarioGrupo> fromJsonArrayToListUsuarioGrupo(String json) {
		return new JSONDeserializer<List<UsuarioGrupo>>().use(null, ArrayList.class).deserialize(json);
	}	
	
	public static UsuarioGrupo fromJsonToUsuarioGrupo(String json) {
		return new JSONDeserializer<UsuarioGrupo>().use( null, UsuarioGrupo.class).deserialize(json);
	}
	
	public String toJson() {
        return new JSONSerializer().exclude("*.class").serialize(this);
    }
	
}
