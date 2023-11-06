package com.wmanager.auth.enumerator;

public enum UserRole {
	
	ADMIN("admin"),
    USER("user"),
    SUPERVISOR("supervisor"),
    COLABORADOR("colaborador");

	 private String role;

	    UserRole(String role){
	        this.role = role;
	    }

	    public String getRole(){
	        return role;
	    }
	

}
