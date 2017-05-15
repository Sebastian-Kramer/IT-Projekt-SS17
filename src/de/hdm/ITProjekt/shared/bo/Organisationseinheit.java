package de.hdm.ITProjekt.shared.bo;



public class Organisationseinheit extends BusinessObject{
	private static final long serialVersionUID = 1L;

	private int ID = 0;	
	private String vorname = "";
	private String nachname = "";
	private String straße = "";
	private int hausnummer = 0;
	private int plz = 0;
	private String ort = "";
	private int Partnerprofil_ID = 0;
	
	
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
	public String getStraße() {
		return straße;
	}
	public void setStraße(String straße) {
		this.straße = straße;
	}
	public int getHausnummer() {
		return hausnummer;
	}
	public void setHausnummer(int hausnummer) {
		this.hausnummer = hausnummer;
	}
	public int getPlz() {
		return plz;
	}
	public void setPlz(int plz) {
		this.plz = plz;
	}
	public String getOrt() {
		return ort;
	}
	public void setOrt(String ort) {
		this.ort = ort;
	}
	public int getPartnerprofil_ID() {
		return Partnerprofil_ID;
	}
	public void setPartnerprofil_ID(int partnerprofil_ID) {
		Partnerprofil_ID = partnerprofil_ID;
	}
	
	public String toString(){
		return "\n" + "ID: " + ID + "\n" + "Vorname: " + vorname + "\n" + "Nachname: " 
				+ vorname + "\n" + "Straße: " + straße + "\n" + "Hausnummer: " + hausnummer
				+ "\n" + "Postleitzahl: " + plz + "\n" + "Wohnort: " + ort + "\n" 
				+ "Diese Organisationseinheit gehört zu diesem Partnerprofil: " + Partnerprofil_ID;
	}
	public boolean equals(Object o) {

	    if (o != null && o instanceof Organisationseinheit) {
	    	Organisationseinheit c = (Organisationseinheit) o;
	      try {
	        return super.equals(c);
	      }
	      catch (IllegalArgumentException e) {
	        return false;
	      }
	    }
	    return false;
	  }
	
	}
	

	
	



