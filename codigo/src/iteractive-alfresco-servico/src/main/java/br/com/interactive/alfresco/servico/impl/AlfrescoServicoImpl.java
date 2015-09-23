package br.com.interactive.alfresco.servico.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.rmi.RemoteException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.interactive.alfresco.model.Grupo;
import br.com.interactive.alfresco.model.GrupoAlfresco;
import br.com.interactive.alfresco.model.Usuario;
import br.com.interactive.alfresco.model.UsuarioEntidade;
import br.com.interactive.alfresco.model.UsuarioGrupo;
import br.com.interactive.alfresco.model.UsuarioGrupoEntidade;
import br.com.interactive.alfresco.servico.AlfrescoServico;
import br.com.interactive.alfresco.util.CustomHttpState;
import br.com.interactive.alfresco.util.ParseXML;
import br.com.interactive.alfresco.util.XMLUtils;
import flexjson.JSONDeserializer;

@Service("alfrescoServicoImpl")
public class AlfrescoServicoImpl implements AlfrescoServico, Serializable {

	private static String uuidNovaPastaAUX = null;
	/**
	 * 
	 * 
	 */
	private static final long serialVersionUID = 5052764109894971794L;

	@Autowired
	@Qualifier("restTemplateAlfresco")
	private RestTemplate restTemplate;

	@Autowired
	private CustomHttpState httpState;

	@Autowired
	private Properties alfrescoproperties;

	@Autowired
	@Qualifier("credentialsAlfresco")
	private UsernamePasswordCredentials credentialsAlfresco;

	private void postConstructor() {
		if ("".equals(alfrescoproperties.getProperty("alfresco.repositorio.id"))) {
			String uri = alfrescoproperties.getProperty("alfresco.repositorio");

			ResponseEntity<String> response = restTemplate.getForEntity(uri,
					String.class);

			try {
				Document document = ParseXML.parseXMLToDocument(response
						.getBody());
				String repositorioId = null;
				if (document != null && document.getRootElement() != null)
					repositorioId = document
							.getRootElement()
							.selectSingleNode(
									"/service/app:workspace/atom:title")
							.getText();

				alfrescoproperties.setProperty("alfresco.repositorio.id",
						repositorioId);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}
	}

	public Usuario autenticarUsuario(String login, String senha) throws HttpClientErrorException, DocumentException {

		this.postConstructor();

		String ticket = null;
		try {
			String uri = alfrescoproperties
					.getProperty("alfresco.server.login.post");
			String json = "{\"username\":" + login + ",\"password\":" + senha
					+ "}";
			HttpHeaders headers = new HttpHeaders();
			headers.set("Content-type", "application/json");

			HttpEntity<String> request = new HttpEntity<String>(json, headers);
			ResponseEntity<String> response = restTemplate.postForEntity(uri,
					request, String.class);

			Map<String, Object> deserialized = new JSONDeserializer<Map<String, Map<String, Object>>>()
					.use("data.values", Object.class)
					.deserialize(response.getBody()).get("data");

			ticket = deserialized.get("ticket").toString();

			// Retorna excecao, impedindo que credenciais inválidas sejam
			// lancadas para realizar o chamado com o retorno dos dados do
			// usuario logado
		} catch (HttpClientErrorException e) {
			e.printStackTrace();
			throw new HttpClientErrorException(HttpStatus.FORBIDDEN);
		} catch (RestClientException e) {
			e.printStackTrace();
			throw new RestClientException(e.getMessage());
		}

		// Bloco responsavel por setar as credenciais do usuario no restTemplate
		UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(
				login, senha);
		httpState.setCredentials(credentials);

		Usuario usuario = this.getUsuarioAutenticado(login);
		usuario.setTicket(ticket);

		return usuario;
	}

	public void logout(String ticket) {
		try {
			URI u = new URI(MessageFormat.format(
					alfrescoproperties.getProperty("alfresco.server.ticket"),
					ticket));
			restTemplate.delete(u);
			httpState.limparCredenciais();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Usuario> getUsuariosPorGrupo(String nomeGrupo)
			throws RemoteException {
		String uri = MessageFormat.format(alfrescoproperties
				.getProperty("alfresco.server.usuarios.grupo"), nomeGrupo);
		ResponseEntity<String> response = restTemplate.getForEntity(uri,
				String.class);

		System.out.println(response);

		UsuarioGrupoEntidade usuarioEntidade = UsuarioGrupoEntidade
				.fromJsonToUsuarioGrupoEntidade(response.getBody());

		List<Usuario> usuarios = new ArrayList<Usuario>();

		for (UsuarioGrupo usuarioGrupo : usuarioEntidade.getData()) {
			Usuario usuario = this.getUsuarioAutenticado(usuarioGrupo
					.getShortName());
			usuarios.add(usuario);
		}
		return usuarios;
	}

	public List<UsuarioGrupo> getUsuariosPorGrupo2(String nomeGrupo)
			throws RemoteException {
		String uri = MessageFormat.format(alfrescoproperties
				.getProperty("alfresco.server.usuarios.grupo"), nomeGrupo);
		ResponseEntity<String> response = restTemplate.getForEntity(uri,
				String.class);
		UsuarioGrupoEntidade usuarioEntidade = UsuarioGrupoEntidade
				.fromJsonToUsuarioGrupoEntidade(response.getBody());

		return usuarioEntidade.getData();
	}

	public List<UsuarioGrupo> listarGrupos() throws RemoteException {
		String uri = alfrescoproperties.getProperty("alfresco.server.grupos");
		ResponseEntity<String> response = restTemplate.getForEntity(uri,
				String.class);
		UsuarioGrupoEntidade usuarioEntidade = UsuarioGrupoEntidade
				.fromJsonToUsuarioGrupoEntidade(response.getBody());
		return usuarioEntidade.getData();
	}

	public List<GrupoAlfresco> listarGruposPorUsuario(String userName) {
		String uri = MessageFormat.format(alfrescoproperties
				.getProperty("alfresco.server.grupos.usuario"), userName);
		ResponseEntity<String> response = restTemplate.getForEntity(uri,
				String.class);
		Usuario usuario = Usuario.fromJsonToUsuario(response.getBody());
		return usuario.getGroups();
	}

	public void atualizarArquivo(byte[] arquivo) {

	}

	@SuppressWarnings("unchecked")
	public String criarPasta(String site, String container, String nomePasta) {
		String uri = MessageFormat.format(
				alfrescoproperties.getProperty("alfresco.server.pasta.nova"),
				site, container);

		String json = "{\"name\": \"" + nomePasta + "\" }";

		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-type", "application/json");

		HttpEntity<String> request = new HttpEntity<String>(json, headers);

		ResponseEntity<String> response = restTemplate.postForEntity(uri,
				request, String.class);
		if (response.getStatusCode() == HttpStatus.OK)
			return ((java.util.HashMap<String, String>) new JSONDeserializer<Object>()
					.deserialize(response.getBody())).get("nodeRef");

		return null;
	}

	public String anexarArquivo(String parentUUID, String nomePasta,
			String destinationUUID, String descricao, String ticket, File file)
			throws HttpException, IOException {

		String uri = MessageFormat.format(
				alfrescoproperties.getProperty("alfresco.server.upload"),
				ticket);
		HttpClient client = new HttpClient();
		PostMethod mPost = new PostMethod(uri);

		if (parentUUID != null && nomePasta != null) {
			String uuidPasta = this.criarPasta(parentUUID, nomePasta);
			if (uuidPasta != null) {
				destinationUUID = uuidPasta;
				uuidNovaPastaAUX = uuidPasta;
			} else {
				destinationUUID = uuidNovaPastaAUX;
			}
		}

		Part[] parts = {
				new FilePart("filedata", file.getName(), file, null, null),
				new StringPart("filename", file.getName()),
				new StringPart("description", "" + descricao),
				new StringPart("aspects", "cm:versionable"),
				new StringPart("destination", destinationUUID), };

		mPost.setRequestEntity(new MultipartRequestEntity(parts, mPost
				.getParams()));
		client.executeMethod(mPost);

		Map<String, Object> deserialized = new JSONDeserializer<Map<String, Object>>()
				.deserialize(mPost.getResponseBodyAsString());

		String json = "{\"uuidPasta\":\"" + destinationUUID
				+ "\",\"nodeRef\":\"" + deserialized.get("nodeRef")
				+ "".toString() + "\"}";

		return json;
	}

	public String criarPasta(String parentUUID, String nomePasta) {

		String xml = XMLUtils.xmlCreateFolder(nomePasta);

		String uri = MessageFormat.format(alfrescoproperties
				.getProperty("alfresco.server.sites.children"), parentUUID);

		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-type", "application/atom+xml;type=entry");

		HttpEntity<String> request = new HttpEntity<String>(xml, headers);

		ResponseEntity<String> response = null;
		String uuid = null;
		try {
			response = restTemplate.postForEntity(uri, request, String.class);
			try {
				uuid = XMLUtils
						.getUUIDByElement(org.springframework.extensions.surf.util.XMLUtil
								.parse(response.getBody()).getRootElement());
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			return null;
		}

		return uuid;
	}

	public InputStream baixarArquivo(String nomeArquivo, String idDocument) {

		String uri = MessageFormat.format(
				alfrescoproperties.getProperty("alfresco.server.download"),
				alfrescoproperties.getProperty("alfresco.repositorio.id"),
				nomeArquivo, idDocument);
		ResponseEntity<byte[]> response = restTemplate.getForEntity(uri,
				byte[].class);

		byte[] documento = response.getBody();

		return new ByteArrayInputStream(documento);
	}

	public List<Usuario> getUsuarios() {
		String uri = alfrescoproperties.getProperty("alfresco.server.usuarios");
		System.out.println(uri);
		ResponseEntity<String> response = restTemplate.getForEntity(uri,
				String.class);
		UsuarioEntidade usuarioEntidade = UsuarioEntidade
				.fromJsonToUsuarioEntidade(response.getBody());
		return usuarioEntidade.getPeople();

	}

	public Usuario getUsuarioAutenticado(String userName) {
		String uri = MessageFormat.format(alfrescoproperties
				.getProperty("alfresco.server.usuario.autenticado"), userName,
				true);
		ResponseEntity<String> response = restTemplate.getForEntity(uri,
				String.class);
		return Usuario.fromJsonToUsuario(response.getBody());
	}

	public String getDocumentoPorMetadado(String metadado) {
		return null;
	}

	public String getExplorerSiteTree(String idSite) throws DocumentException {
		String uri = MessageFormat.format(
				alfrescoproperties.getProperty("alfresco.server.sites.tree"),
				idSite);

		ResponseEntity<String> response = restTemplate.getForEntity(uri,
				String.class);

		return response.getBody();
	}

	public String getExplorerChildren(String idSite) {
		String uri = MessageFormat.format(alfrescoproperties
				.getProperty("alfresco.server.sites.children"), idSite);

		ResponseEntity<String> response = restTemplate.getForEntity(uri,
				String.class);

		return response.getBody();
	}

	public String getConteudoNodeRef(String nodeRef) {

		if (nodeRef.contains("workspace://SpacesStore/")) {
			nodeRef = nodeRef.replaceAll("workspace://SpacesStore/", "");
		}

		String uri = MessageFormat
				.format(alfrescoproperties
						.getProperty("alfresco.server.descendentes.metadados"),
						nodeRef);

		ResponseEntity<String> response = restTemplate.getForEntity(uri,
				String.class);

		return response.getBody();
	}

	public UsuarioGrupo listarGrupoNome(String nome) {
		String uri = MessageFormat.format(alfrescoproperties
				.getProperty("alfresco.server.grupo.pesquisa.nome"), nome);

		System.out.println(uri);

		ResponseEntity<String> response = restTemplate.getForEntity(uri,
				String.class);
		Grupo grupo = Grupo.fromJsonToUsuarioGrupo(response.getBody());
		return grupo.getData();
	}
        
        public void incluir(Usuario usuario){
            incluirUsuario(usuario.getFirstName(), usuario.getLastName(), usuario.getEmail(), usuario.getUserName());
        }

	public void incluirUsuario(String firstName, String lastName, String email,
			String userName) {
		String uri = alfrescoproperties.getProperty("alfresco.server.usuarios");

		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-type", "application/json");

		String json = "{\"firstName\":\"" + firstName + "\",\"lastName\":\""
				+ lastName + "\",\"email\":\"" + email + "\",\"userName\":\""
				+ userName + "\"}";

		/*
		 * "{\"userName\":\"" + userName + "\",\"firstName\":\"" + firstName +
		 * "\",\"lastName\":\"" + lastName + "\",\"email\":\"" + email + "\"}";
		 */

		HttpEntity<String> request = new HttpEntity<String>(json, headers);

		System.out.println(uri);
		System.out.println(json);
		ResponseEntity<String> response = restTemplate.postForEntity(uri,
				request, String.class);

		System.out.println(response);

	}

	public void incluirGrupo(String shortName) {
		String uri = MessageFormat
				.format(alfrescoproperties
						.getProperty("alfresco.server.grupo.incluir"),
						shortName);

		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-type", "application/json");

		String json = "{\"shortName\":\"teste222\"}";

		HttpEntity<String> request = new HttpEntity<String>(json, headers);

		System.out.println(uri);
		ResponseEntity<String> response = restTemplate.postForEntity(uri,
				request, String.class);

		System.out.println(response);

	}

	public void incluirUsuarioGrupo(String nome, String grupo) {
		String uri = MessageFormat
				.format(alfrescoproperties
						.getProperty("alfresco.server.usuario.grupo"), grupo,
						nome);

		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-type", "application/json");

		String json = "{}";

		HttpEntity<String> request = new HttpEntity<String>(json, headers);

		System.out.println(uri);
		ResponseEntity<String> response = restTemplate.postForEntity(uri,
				request, String.class);

		System.out.println(response);

	}

	public void excluirUsuarioGrupo(String nome, String grupo) {

		String uri = MessageFormat
				.format(alfrescoproperties
						.getProperty("alfresco.server.usuario.grupo"), grupo,
						nome);

		HttpHeaders headers = new HttpHeaders();

		headers.set("Content-type", "application/json");

		Map<String, String> map = new HashMap<String, String>();

		restTemplate.delete(uri, map);

		System.out.println(uri);

	}
        
        public void alterar(Usuario usuario){
            //TODO corrigir o enable
            Boolean enable = true;
            editarUsuario(usuario.getUserName(), 
                    usuario.getFirstName(), 
                    usuario.getLastName(),
                    usuario.getEmail(), enable);
        }

	public void editarUsuario(String userName, String firstName,
			String lastName, String email, Boolean enable) {
		String uri = MessageFormat.format(
				alfrescoproperties.getProperty("alfresco.server.usuario"),
				userName);

		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-type", "application/json");

		String json = "{\"firstName\":\"" + firstName + "\",\"enabled\":\""
				+ enable + "\",\"lastName\":\"" + lastName + "\",\"email\":\""
				+ email + "\"}";

		/*
		 * String json = "{\"firstName\":\"" + firstName + "\",\"lastName\":\""
		 * + lastName + "\",\"email\":\"" + email + "\"}";
		 */

		HttpEntity<String> request = new HttpEntity<String>(json, headers);
		Map<String, String> map = new HashMap<String, String>();

		System.out.println(json);

		restTemplate.put(uri, request, map);

		System.out.println(uri);

	}

	public Usuario listarUsuarioNome(String userName) {

		String uri = MessageFormat.format(alfrescoproperties
				.getProperty("alfresco.server.usuario.pesquisa.nome"),
				userName, true);

		ResponseEntity<String> response = restTemplate.getForEntity(uri,
				String.class);

		return Usuario.fromJsonToUsuario(response.getBody());

	}

	public List<UsuarioGrupo> listarGruposLike(String shortName) {

		String uri = MessageFormat.format(alfrescoproperties
				.getProperty("alfresco.server.grupos.listar.like"), shortName);

		ResponseEntity<String> response = restTemplate.getForEntity(uri,
				String.class);

		UsuarioGrupoEntidade usuarioEntidade = UsuarioGrupoEntidade
				.fromJsonToUsuarioGrupoEntidade(response.getBody());
		return usuarioEntidade.getData();
	}

	public List<Usuario> listarUsuarioNomeLike(String userName)
			throws RemoteException {

		String uri = MessageFormat
				.format(alfrescoproperties
						.getProperty("alfresco.server.usuario.pesquisa.like"),
						userName);

		ResponseEntity<String> response = restTemplate.getForEntity(uri,
				String.class);

		UsuarioEntidade usuarioEntidade = UsuarioEntidade
				.fromJsonToUsuarioEntidade(response.getBody());

		return usuarioEntidade.getPeople();
	}

	public void deletarGrupoNome(String nome) {

		String uri = MessageFormat.format(alfrescoproperties
				.getProperty("alfresco.server.grupo.pesquisa.nome"), nome);

		HttpHeaders headers = new HttpHeaders();

		headers.set("Content-type", "application/json");

		Map<String, String> map = new HashMap<String, String>();

		restTemplate.delete(uri, map);

		System.out.println(uri);

	}

	public void habilitarUsuario(String userNome, Boolean enabled) {

		String uri = MessageFormat.format(
				alfrescoproperties.getProperty("alfresco.server.usuario"),
				userNome);

		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-type", "application/json");

		String json = "{\"disableAccount\":" + enabled + "}";

		HttpEntity<String> request = new HttpEntity<String>(json, headers);
		Map<String, String> map = new HashMap<String, String>();

		System.out.println(json);
		System.out.println("Habilitando usuário");

		restTemplate.put(uri, request, map);

	}

	public void atualizarSenha(String userName, String password) {

		// Sempre devera setar as credenciais default
		httpState.setCredentials(credentialsAlfresco);

		String uri = MessageFormat
				.format(alfrescoproperties
						.getProperty("alfresco.server.usuario.senha"), userName);

		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-type", "application/json");

		String json = "{\"newpw\":\"" + password + "\"}";

		HttpEntity<String> request = new HttpEntity<String>(json, headers);
		System.out.println(uri);

		ResponseEntity<String> response = restTemplate.postForEntity(uri,
				request, String.class);
		System.out.println(response);

	}
}
