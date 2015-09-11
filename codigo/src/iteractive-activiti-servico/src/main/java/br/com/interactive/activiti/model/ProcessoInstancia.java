package br.com.interactive.activiti.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

/**
 * Classe responsavel por mapear as instancias de processo
 * TODO - Refatorar para que essa classe utilize a classe do Activiti
 * @author ELIMARCOSAROUCA
 *
 */
public class ProcessoInstancia {

	private String id;
	private String processInstanceId;
	private String url;
	private String businessKey;
	private boolean suspended;
	private String processDefinitionId;
	private String processDefinitionUrl;
	private String activityId;
	private Date startTime;
	private Date endTime;
	private Object variaveisProcesso;
	private List<Variaveis> variables;
	private ProcessoDefinicao processoDefinicao;
	private List<TarefaInstancia> tarefasInstancia;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getBusinessKey() {
		return businessKey;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}

	public boolean isSuspended() {
		return suspended;
	}

	public void setSuspended(boolean suspended) {
		this.suspended = suspended;
	}

	public String getProcessDefinitionId() {
		return processDefinitionId;
	}

	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	public String getProcessDefinitionUrl() {
		return processDefinitionUrl;
	}

	public void setProcessDefinitionUrl(String processDefinitionUrl) {
		this.processDefinitionUrl = processDefinitionUrl;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Object getVariaveisProcesso() {
		return variaveisProcesso;
	}

	public void setVariaveisProcesso(Object variaveisProcesso) {
		this.variaveisProcesso = variaveisProcesso;
	}

	public List<Variaveis> getVariables() {
		return variables;
	}
	
	public void setVariables(List<Variaveis> variables) {
		this.variables = variables;
	}	
	
	public ProcessoDefinicao getProcessoDefinicao() {
		return processoDefinicao;
	}

	public void setProcessoDefinicao(ProcessoDefinicao processoDefinicao) {
		this.processoDefinicao = processoDefinicao;
	}
	
	public List<TarefaInstancia> getTarefasInstancia() {
		return tarefasInstancia;
	}

	public void setTarefasInstancia(List<TarefaInstancia> tarefasInstancia) {
		this.tarefasInstancia = tarefasInstancia;
	}

	public static Collection<ProcessoInstancia> fromJsonArrayToListProcessoInstancia(String json) {
		return new JSONDeserializer<List<ProcessoInstancia>>().use(null, ArrayList.class).deserialize(json);
	}	
	
	public static ProcessoInstancia fromJsonToObject(String json) {
		return new JSONDeserializer<ProcessoInstancia>().use(null, ProcessoInstancia.class).use("variables", ArrayList.class).deserialize(json);
	}
	
	public String toJson() {
        return new JSONSerializer().exclude("*.class").exclude("activityId", "data", "description", "id", "processDefinitionUrl", "processoDefinicao",
        		 "suspended", "url", "variaveisProcesso", "processInstanceId", "tarefasInstancia").deepSerialize(this);
    }
}
