package br.com.interactive.activiti.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

import flexjson.JSONDeserializer;

public class Variaveis {

	private String name;
	private Object value;
	private String type;
	private String scope;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public static Variaveis fromJsonToObject(String json) {
		return new JSONDeserializer<Variaveis>().use(null, Variaveis.class).deserialize(json);
	}
	
	public static ArrayList<Variaveis> fromJsonArrayToVariaveis(String json) {
		return new JSONDeserializer<ArrayList<Variaveis>>().use(null, ArrayList.class).use("values", Variaveis.class).deserialize(json);
	} 
	
	public static List<Variaveis> fromJsonArrayToVariaveisList(String json) {
		List<Variaveis> variaveisLista = new ArrayList<Variaveis>();
		 
		try {
			JsonNode jsonNode = new ObjectMapper().readTree(json);
		    if (jsonNode != null) {
		    	Variaveis variaveis = null;
		    	for(int i=0; i<jsonNode.size();i++) {
	        		variaveis = new Variaveis();
                    variaveis.setName(jsonNode.get(i).get("name").getTextValue());
                    variaveis.setValue(jsonNode.get(i).get("value").getTextValue());
                    variaveis.setType(jsonNode.get(i).get("type").getTextValue());
                    variaveisLista.add(variaveis);
	        	}
	        }
	 	} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
			
	    return variaveisLista;
	}
}
