package de.hdm.ITProjekt.shared.bo;

public class Person extends Organisationseinheit{
	private static final long serialVersionUID = 1L;
	private String vorname;
	private String nachname;
	private String anrede;
	private int ID = 0;
	private final static String orgeinheit = "Person"; 
	
	public Person(){
	}
	public Person(String vorname, String nachname, String anrede){
		this.vorname = vorname;
		this.nachname = nachname;
		this.anrede = anrede;
	}
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
	
	
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
