package br.com.interactive.activiti.servico;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import br.com.interactive.activiti.model.ProcessoDefinicao;
import br.com.interactive.activiti.model.ProcessoInstancia;
import br.com.interactive.activiti.model.TarefaInstancia;
import br.com.interactive.activiti.model.Variaveis;

/**
 * Interface responsavel por listar os metodos de servico do Activiti
 * 
 * @author ELIMARCOSAROUCA
 *
 */
public interface ActivitiServico {

	/**
	 * Metodo responsavel por listar todas as tarefas de um determinado usuario
	 * @param userName
	 * @return
	 */
	List<TarefaInstancia> getTarefasUsuario(String userName);
	
	/**
	 * Metodo responsavel por listar todas as tarefas
	 * @return
	 */
	List<TarefaInstancia> getTodasTarefas();
	
	/**
	 * Metodo responsavel por listar todas as tarefas de um processo instancia
	 * @param idProcesso
	 * @return
	 */
	List<TarefaInstancia> getTarefasProcessoInstancia(String idProcesso);
	
	/**
	 * Metodo responsavel por listar o historicos das tarefas de um processo
	 * @param idProcesso
	 * @return List<TarefaInstancia>
	 */
	List<TarefaInstancia> getHistoricoTarefasProcessoInstancia(String idProcesso);
		
	/**
	 * Metodo responsavel por retornar todas as variaveis de tarefa
	 * @param idTarefa
	 * @return
	 */
	List<Variaveis> getVariaveisTarefa(String idTarefa);
	
	/**
	 * Metodo responsavel por retornar uma tarefa com as variaveis de tarefa e as variaveis de processo
	 * @param idTarefa
	 * @param includeTaskLocalVariables
	 * @param includeProcessVariables
	 * @return
	 */
	TarefaInstancia getTarefaInstanciaPorIdIncludeVariaveis(String idTarefa, boolean includeTaskLocalVariables, boolean includeProcessVariables);
	
	/**
	 * Metodo responsavel por assinar uma tarefa utilizando claim
	 * @param idTarefa
	 * @param userName
	 * @return
	 */
	String assinarTarefa(String idTarefa, String userName);
	
	/**
	 * Metodo responsavel por alterar o usuario responsavel pela tarefa utilizando a acao delegate
	 * @param idTarefa
	 * @param userName
	 * @return
	 */
	String alterarExecutor(String idTarefa, String userName);
	
	/**
	 * Metodo responsavel por finalizar uma tarefa
	 * @param idTarefa
	 * @param jsonVariaveis
	 * @return
	 */
	String completarTarefa(String idTarefa, String jsonVariaveis);
	
	/**
	 * Metodo responsavel por retornar o modelo de um processo instancia
	 * @param idProcessoDefinicao
	 * @return
	 */
	ProcessoDefinicao getProcessoDefinicao(String idProcessoDefinicao);
	
	/**
	 * Metodo responsavel por listar as definicoes de processo
	 * @return
	 */
	List<ProcessoDefinicao> getProcessosDefinicao();
	
	/**
	 * Metodo responsavel por retornar lista todos as instancias de processo
	 * @return
	 */
	List<ProcessoInstancia> getProcessosInstancia();
	
	/**
	 * @param processInstanceId
	 * Metodo responsavel por retornar a imagem (InputStream) do processo que esta em execução
	 * @return InputStream
	 */
	String getProcessoDiagrama(String processInstanceId);
	
	/**
	 * Metodo responsavel por retornar lista de instancia de processo de um determinado modelo de processo
	 * @param ProcessDefinitionId
	 * @return
	 */
	List<ProcessoInstancia> getProcessosInstancia(String processDefinitionId);
	
	/**
	 * Metodo reponsavel por listar as variaveis de processo
	 * @param idProcesso
	 * @return
	 */
	List<Variaveis> getVariaveisProcesso(String idProcesso);
	
	/**
	 * Metodo reponsavel por listar as variaveis de processo - PUT
	 * @param idProcesso
	 * @param json
	 * @return
	 */
	String atualizarVariaveis(String idProcesso, String json);
	
	/**
	 * Metodo responsavel por iniciar um processo
	 * @param json
	 * @return
	 */
	String iniciarInstanciaProcesso(String json);
	
	/**
	 * Metodo responsavel por iniciar um processo utilizando um mapa de parametros
	 * @param processoDefinicaoId
	 * @param businessKey
	 * @param paramsProcesso
	 * @return
	 */
	String iniciarInstanciaProcessoPorParametros(String processoDefinicaoId, String businessKey, Map<String, Object> paramsProcesso);
	
	/**
	 * Metodo responsavel por iniciar um processo utilizando a chave do processo definido e um mapa de parametros
	 * @param processoDefinicaoKey
	 * @param businessKey
	 * @param paramsProcesso
	 * @return
	 */
	String iniciarInstanciaProcessoPorParametrosByKey(String processoDefinicaoKey, String businessKey, Map<String, Object> paramsProcesso);
	
	/**
	 * Metodo responsavel por cancelar um processo instaciado
	 * @param processInstanceId
	 * @return
	 */
	String cancelarProcessoInstaciado(String processInstanceId);
	
	/**
	 * Metodo responsavel por filtrar processos por variaveis de processo e status (TODOS; PENDENTE; CONCLUIDO)
	 * @param variables
	 * @param status
	 * @return
	 */
	List<ProcessoInstancia> getProcessosInstanciaFiltroVariaveis(Map<String, String> variables, String status);
	
	/**
	 * Metodo responsavel por filtrar historico de processos por variaveis de processo e status (TODOS; PENDENTE; CONCLUIDO)
	 * @param variables
	 * @param status
	 * @return
	 */	
	List<ProcessoInstancia> getHistoricoProcessosFiltroVariaveis(Map<String, Object> variables, String status);
	
	/**
	 * Metodo responsavel por deserializar objetos salvos nas variaveis de instancia do processo 
	 * @param idProcesso
	 * @param variavelNome
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	Map<String, Object> getBinaryDataPorVariavelProcesso(String idProcesso, String variavelNome) throws IOException, ClassNotFoundException;
	
	/**
	 * Metodo responsavel por listar a ultima versao dos processos
	 * @return
	 */
	List<ProcessoDefinicao> getProcessosDefinicaoPorQueryLastVersion();
	
	/**
	 * Metodo responsavel por filtrar tarefas atraves de query nativa
	 * @param variables
	 * @param assignee
	 * @param processDefinitionKey
	 * @param isPendente
	 * @param idProcesso
	 * @return
	 * @throws ParseException 
	 */
	List<TarefaInstancia> getHistoricoTarefasPorVariaveis(Map<String, Object> variables, String assignee, String processDefinitionKey, Boolean isPendente, String idProcesso) throws ParseException;
	
	
	/**
	 * Metodo responsavel por lista os processos pendentes, concluido, cancelados ou todos
	 * @param variables
	 * @param status
	 * @return List<ProcessoInstancia>
	 * @throws ParseException
	 */
	List<ProcessoInstancia> getHistoricoProcessos(Map<String, Object> variables, String status) throws ParseException;
}
