package br.interactive.ecm.message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class BusinessMessage implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(BusinessMessage.class.getName());
	
	private List<Message> messages;

	private ResourceBundle resourceBundle;		// FIXME internacionalizar
	
	
	@PostConstruct
	public void init() {
		messages = new ArrayList<Message>();
		
		try {
			resourceBundle = ResourceBundle.getBundle("business_messages",new Locale("pt", "BR"));
		} catch ( MissingResourceException mre ) {
			LOGGER.warning("Não achou o business_messages: " + mre.getMessage() );
		}
	}
	
	public void info( String descricao, String target ) {
		messages.add( new InfoMessage( descricao, target ) );
	}
	
	public void info( String descricao ) {
		info( descricao, null );
	}
	
	public void warn( String descricao, String target ) {
		messages.add( new WarnMessage( descricao, target ) );
	}
	
	public void warn( String descricao ) {
		warn( descricao, null );
	}
	
	public void error( String descricao, String target ) {
		messages.add( new ErrorMessage( descricao, target ) );
	}
	
	public void error( String descricao ) {
		error( descricao, null );
	}
	
	public void add( Message msg ) {
		if ( msg != null ) {
			messages.add( msg );
		}
	}
	
	public boolean hasMessages() {
		return messages.size() > 0;
	}

	public List<Message> getMessages() {
		return messages;
	}
	
}
