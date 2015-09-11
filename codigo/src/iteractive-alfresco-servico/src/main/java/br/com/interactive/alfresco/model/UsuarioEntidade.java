package br.com.interactive.alfresco.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import flexjson.JSONDeserializer;
import flexjson.transformer.DateTransformer;

/**
 * Classe responsavel por representar o objto json retornado pelo alfesco.
 * Representa a lista de usuarios de um grupo
 * 
 * @author ELIMARCOSAROUCA
 */
public class UsuarioEntidade {

	private List<Usuario> people;

	public List<Usuario> getPeople() {
		return people;
	}

	public void setPeople(List<Usuario> people) {
		this.people = people;
	}

	public static UsuarioEntidade fromJsonToUsuarioEntidade(String json) {
		return new JSONDeserializer<UsuarioEntidade>()
				.use(null, UsuarioEntidade.class)
				.use("people", ArrayList.class)
				.use(Date.class,
						new DateTransformer("yyyy-MM-dd'T'HH:mm:ss.SSSz"))
				.deserialize(json);
	}
}
