package de.hdm.ITProjekt.shared.bo;

public class Person extends Organisationseinheit{
	private String vorname;
	private String nachname;
	private String anrede;
	private final static String orgeinheit = "Person"; 
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	public String getNachname() {
		return nachname;
	}
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}
	public String getAnrede() {
		return anrede;
	}
	public void setAnrede(String anrede) {
		this.anrede = anrede;
	}
	public static String getOrgeinheit() {
		return orgeinheit;
	}
	

}
