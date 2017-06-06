package de.hdm.ITProjekt.shared.bo;

public class Unternehmen extends Organisationseinheit{
	
	private static final long serialVersionUID = 1L;
	private String name;
	private String Firmensitz;
	private int UN_ID = 0;
	
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
	public int getUN_ID() {
		return UN_ID;
	}
	public void setUN_ID(int uN_ID) {
		UN_ID = uN_ID;
	}
	
	
	
}
