package de.hdm.ITProjekt.shared.bo;



public class Organisationseinheit extends BusinessObject{
	
	private static final long serialVersionUID = 1L;

	private String strasse;
	private int hausnummer;
	private int plz;
	private String ort;
	private int Partnerprofil_ID = 0;
	
//	private Integer Partnerprofil_ID = 0;
	
	
	public String getStrasse() {
		return strasse;
	}
	public void setStrasse(String strasse) {
		this.strasse = strasse;
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
	
//	public Integer getPartnerprofil_ID() {
//		return Partnerprofil_ID;
//	}
//	public void setPartnerprofil_ID(Integer partnerprofil_ID) {
//		if(partnerprofil_ID==0){
//			this.Partnerprofil_ID=null;
//		}else{
//			this.Partnerprofil_ID = partnerprofil_ID;	
//		}
//	}
	

	//	public String toString(){
//		return "\n" + "ID: " + ID + "\n" + "strasse: " + strasse + "\n" + "Hausnummer: " + hausnummer
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
	

	
	



