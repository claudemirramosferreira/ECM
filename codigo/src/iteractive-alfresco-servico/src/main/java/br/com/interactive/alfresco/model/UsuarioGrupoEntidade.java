package br.com.interactive.alfresco.model;

import java.util.ArrayList;
import java.util.List;
import flexjson.JSONDeserializer;

/**
 * Classe responsavel por representar o objto json retornado pelo alfesco.
 * Representa a lista de usuarios de um grupo
 * 
 * @author ELIMARCOSAROUCA
 */
public class UsuarioGrupoEntidade {

	private List<UsuarioGrupo> data;

	public List<UsuarioGrupo> getData() {
		return data;
	}

	public void setData(List<UsuarioGrupo> data) {
		this.data = data;
	}
	
	public static UsuarioGrupoEntidade fromJsonToUsuarioGrupoEntidade(String json) {
		return new JSONDeserializer<UsuarioGrupoEntidade>().use(null, UsuarioGrupoEntidade.class).use("data", ArrayList.class).deserialize(json);
	}
}
