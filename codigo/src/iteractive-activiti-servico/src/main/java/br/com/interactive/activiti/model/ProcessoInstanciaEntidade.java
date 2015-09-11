package br.com.interactive.activiti.model;

import java.util.ArrayList;
import java.util.List;

import flexjson.JSONDeserializer;

public class ProcessoInstanciaEntidade {

	private List<ProcessoInstancia> data;

	public List<ProcessoInstancia> getData() {
		return data;
	}

	public void setData(List<ProcessoInstancia> data) {
		this.data = data;
	}

	public static ProcessoInstanciaEntidade fromJsonToObject(String json) {
		return new JSONDeserializer<ProcessoInstanciaEntidade>()
				.use(null, ProcessoInstanciaEntidade.class)
				.use("data", ArrayList.class).deserialize(json);
	}
}