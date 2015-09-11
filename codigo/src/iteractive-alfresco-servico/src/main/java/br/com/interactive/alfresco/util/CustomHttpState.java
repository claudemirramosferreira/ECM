package br.com.interactive.alfresco.util;

import java.io.Serializable;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.auth.AuthScope;

public class CustomHttpState extends HttpState implements Serializable {
   
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void setCredentials(final Credentials credentials) {
        super.setCredentials(AuthScope.ANY, credentials);
    }
    
	/**
	 * Metodo responsavel por limpar as credenciais do usuario da sessao
	 */
	public void limparCredenciais(){
		this.clear();
		this.clearCredentials();	
		this.clearProxyCredentials();
		this.clearCookies();
	}

}
