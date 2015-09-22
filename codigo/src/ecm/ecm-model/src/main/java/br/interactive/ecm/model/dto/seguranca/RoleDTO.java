package br.interactive.ecm.model.dto.seguranca;

import java.io.Serializable;

public class RoleDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String permission;
	
	public RoleDTO () { }
	
	public RoleDTO ( String permission ) {
		this.permission = permission;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((permission == null) ? 0 : permission.hashCode());
		return result;
	}
	
	@Override
	public boolean equals( Object o ) {
		if ( o instanceof RoleDTO ) {
			return permission.equalsIgnoreCase(( ( RoleDTO ) o ).getPermission() );
		}
		return false; 
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

}
