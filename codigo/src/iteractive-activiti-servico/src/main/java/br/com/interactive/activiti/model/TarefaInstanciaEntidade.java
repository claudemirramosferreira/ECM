package br.com.interactive.activiti.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import flexjson.JSONDeserializer;
import flexjson.transformer.DateTransformer;

public class TarefaInstanciaEntidade {

	private List<TarefaInstancia> data;
	private int total;
	private int start;
	private String sort;
	private String order;
	private int size;

	public List<TarefaInstancia> getData() {
		return data;
	}

	public void setData(List<TarefaInstancia> data) {
		this.data = data;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public static TarefaInstanciaEntidade fromJsonToTarefaInstanciaEntidade(
			String json) {
		return new JSONDeserializer<TarefaInstanciaEntidade>()
				.use(null, TarefaInstanciaEntidade.class)
				.use("data", ArrayList.class)
				.use(Date.class,
						new DateTransformer("yyyy-MM-dd'T'HH:mm:ss.SSSz"))
				.deserialize(json);
	}
}
