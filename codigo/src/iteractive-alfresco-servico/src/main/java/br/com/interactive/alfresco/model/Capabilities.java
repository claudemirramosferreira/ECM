package br.com.interactive.alfresco.model;

import java.io.Serializable;

import flexjson.JSONSerializer;

public class Capabilities implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3961884422834146146L;
	
	private boolean isMutable;
	private boolean isGuest;
	private boolean isAdmin;
	
	public boolean isMutable() {
		return isMutable;
	}
	public void setMutable(boolean isMutable) {
		this.isMutable = isMutable;
	}
	public boolean isGuest() {
		return isGuest;
	}
	public void setGuest(boolean isGuest) {
		this.isGuest = isGuest;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}	
	
	public String toJson() {
        return new JSONSerializer().exclude("*.class").serialize(this);
    }
}
