package br.com.interactive.alfresco.model;

import flexjson.JSONDeserializer;

/**
 * Classe responsavel por representar o objto json retornado pelo alfesco.
 * Representa a lista de usuarios de um grupo
 * 
 * @author ELIMARCOSAROUCA
 */
public class Grupo {

	private UsuarioGrupo data;
	
	public static Grupo fromJsonToUsuarioGrupo(String json) {
		return new JSONDeserializer<Grupo>().use(null, Grupo.class).use("data", UsuarioGrupo.class).deserialize(json);
	}

	public UsuarioGrupo getData() {
		return data;
	}

	public void setData(UsuarioGrupo data) {
		this.data = data;
	}
}
