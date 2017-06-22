package de.hdm.ITProjekt.shared.bo;



public class Organisationseinheit extends BusinessObject{
	
	private static final long serialVersionUID = 1L;
	
	/*
	 * Straße der Organisatoinseinheit
	 */

	private String strasse;
	
	/*
	 * Hausnummer der Organisationseinheit
	 */
	
	private int hausnummer;
	
	/*
	 * Pustleitzahl der Organisationseinheit
	 */
	
	private int plz;
	
	/*
	 * Ort der Organisationseinheit
	 */
	
	private String ort;
	/*
	 * Realisierung einer Beziehung zu Partnerprofil mittels Fremdschlüssel
	 */


	private Integer Partnerprofil_ID = 0;

	
//	private Integer Partnerprofil_ID = 0;
	
	/*
	 * Auslesen der Straße
	 * @return strasse
	 */
	
	public String getStrasse() {
		return strasse;
	}
	
	/*
	 * Setzen der Straße
	 * @param strasse
	 */
	
	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}
	
	/*
	 * Auslesen der Hausnummer
	 * @return hausnummer
	 */
	
	public int getHausnummer() {
		return hausnummer;
	}
	
	/*
	 * setzen der Hausnummer
	 * @param hausnummer
	 */
	
	public void setHausnummer(int hausnummer) {
		this.hausnummer = hausnummer;
	}
	
	/*
	 * Auslesen der Postleitzahl
	 * @return plz
	 */
	
	public int getPlz() {
		return plz;
	}
	
	/*
	 * Setzen der Postleitzahl
	 * @param plz
	 */
	
	public void setPlz(int plz) {
		this.plz = plz;
	}
	
	/*
	 * Auslesen es Ortes
	 * @return ort
	 */
	
	public String getOrt() {
		return ort;
	}
	
	/*
	 * Setzen des Ortes
	 * @param ort
	 */
	
	public void setOrt(String ort) {
		this.ort = ort;
	}
	
//		return "\n" + "ID: " + ID + "\n" + "strasse: " + strasse + "\n" + "Hausnummer: " + hausnummer
//				+ "\n" + "Postleitzahl: " + plz + "\n" + "Wohnort: " + ort + "\n" 
//				+ "Diese Organisationseinheit gehört zu diesem Partnerprofil: " + Partnerprofil_ID;
//	}
	
	/*
	 * Es wird �berpr�ft ob ein Objekt ungleich NULL ist und gecastet werden kann
	 * instanceof �berpr�ft ob o zuweisungskompatibel zu BusinessObject ist
	 * @return true
	 * @return false wenn keine Gleichheit besteht
	 */
	
	public Integer getPartnerprofil_ID() {
		return Partnerprofil_ID;
	}
	
	public void setPartnerprofil_ID(Integer partnerprofil_ID) {
		if (partnerprofil_ID == 0){
			this.Partnerprofil_ID = null;
		}else{
			this.Partnerprofil_ID = partnerprofil_ID;
		}
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
	

	
	



