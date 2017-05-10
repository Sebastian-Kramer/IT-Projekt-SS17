package de.hdm.ITProjekt.shared.bo;

public class Unternehmen extends Organisationseinheit{
	private static final long serialVersionUID = 1L;
	private String name;
	private String Firmensitz;
	private final static String orgeinheit = "Unternehmen"; 
	
	public static String getOrgeinheit() {
		return orgeinheit;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFirmensitz() {
		return Firmensitz;
	}
	public void setFirmensitz(String firmensitz) {
		Firmensitz = firmensitz;
	}
	
}
