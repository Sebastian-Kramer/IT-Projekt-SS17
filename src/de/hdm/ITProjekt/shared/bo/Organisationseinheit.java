package de.hdm.ITProjekt.shared.bo;



public class Organisationseinheit extends BusinessObject{
	
	private static final long serialVersionUID = 1L;

	private String straße = "";
	private int hausnummer = 0;
	private int plz = 0;
	private String ort = "";
	
	private Integer Partnerprofil_ID = 0;
	
	
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
	public Integer getPartnerprofil_ID() {
		return Partnerprofil_ID;
	}
	public void setPartnerprofil_ID(Integer partnerprofil_ID) {
		if(partnerprofil_ID==0){
			this.Partnerprofil_ID=null;
		}else{
			this.Partnerprofil_ID = partnerprofil_ID;	
		}
	}
	
//	public String toString(){
//		return "\n" + "ID: " + ID + "\n" + "Straße: " + straße + "\n" + "Hausnummer: " + hausnummer
//				+ "\n" + "Postleitzahl: " + plz + "\n" + "Wohnort: " + ort + "\n" 
//				+ "Diese Organisationseinheit gehört zu diesem Partnerprofil: " + Partnerprofil_ID;
//	}
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
	

	
	



