package br.interactive.ecm.message;

import lombok.Getter;
import lombok.Setter;

public class EmptyMessage implements Message {
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Detalhamento da mensagem.
     */
    @Getter @Setter
    private String description;
    
    private String target;
    
    public EmptyMessage( String desc ) {
    	description = desc;
    }
    
    public EmptyMessage( String desc, String targ ) {
    	description = desc;
    	target = targ;
    }
	
    @Override
	public Type getType() {
		return Type.EMPTY;
	}

	@Override
	public String getTarget() {
		return target;
	}
    
}
