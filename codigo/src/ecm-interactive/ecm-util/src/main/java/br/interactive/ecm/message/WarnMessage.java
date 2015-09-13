package br.interactive.ecm.message;

import lombok.Getter;
import lombok.Setter;

public class WarnMessage implements Message {
    
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
    
    public WarnMessage( String desc ) {
    	description = desc;
    }
    
    public WarnMessage( String desc, String targ ) {
    	description = desc;
    	target = targ;
    }
	
    @Override
	public Type getType() {
		return Type.WARN;
	}

	@Override
	public String getTarget() {
		return target;
	}
    
}
