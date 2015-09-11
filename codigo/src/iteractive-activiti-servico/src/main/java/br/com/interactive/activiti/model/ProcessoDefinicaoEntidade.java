package br.com.interactive.activiti.model;

import java.util.ArrayList;
import java.util.List;

import flexjson.JSONDeserializer;

public class ProcessoDefinicaoEntidade {

	private List<ProcessoDefinicao> data;

	public List<ProcessoDefinicao> getData() {
		return data;
	}

	public void setData(List<ProcessoDefinicao> data) {
		this.data = data;
	}

	public static ProcessoDefinicaoEntidade fromJsonToObject(String json) {
		return new JSONDeserializer<ProcessoDefinicaoEntidade>()
				.use(null, ProcessoDefinicaoEntidade.class)
				.use("data", ArrayList.class).deserialize(json);
	}
}
