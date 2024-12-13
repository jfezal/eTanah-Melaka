package com.etanah.portal.pojo;

/**
 * @author Sudhakar
 *
 */
public class PortalUsers {

    private String name;
	private String noKp;
	private String email;
	
	public PortalUsers() {}
	
	public PortalUsers(String name,String noKp,String email){
		this.name=name;
		this.noKp=noKp;
		this.email=email;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNoKp() {
		return noKp;
	}
	public void setNoKp(String noKp) {
		this.noKp = noKp;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
