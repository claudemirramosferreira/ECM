package br.com.interactive.alfresco.model;

import java.io.Serializable;

public class GrupoAlfresco implements Serializable {

	private static final long serialVersionUID = -412371279058065730L;

	private String itemName;

	private String displayName;

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
}