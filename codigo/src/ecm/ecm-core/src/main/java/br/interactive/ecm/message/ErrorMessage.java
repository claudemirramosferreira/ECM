package br.interactive.ecm.message;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Encapsula uma mensagem de excecao.
 * @author diego.coronel
 */
@ToString
public class ErrorMessage implements Message {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Endere�ada para um elemento.
     */
    @Getter @Setter
    private String target;
    
    /**
     * Detalhamento da mensagem.
     */
    @Getter @Setter
    private String description;
    
    @Getter @Setter
    private List<String> descriptionList;
    
    public ErrorMessage() {
        
    }
    
    public ErrorMessage( String d, String tar ) {
        description = d;
        target = tar;
    }
    
    public ErrorMessage( String d ) {
        description = d;
    }
	public ErrorMessage(List<String> msg) {
	    descriptionList = msg;
    }

    @Override
    public Type getType() {
            return Type.ERROR;
    }
    
}
