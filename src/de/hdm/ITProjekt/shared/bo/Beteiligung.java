package de.hdm.ITProjekt.shared.bo;

import java.util.Date;

public class Beteiligung extends BusinessObject {
	
	/*
	 * Eine Beteilgung besitzt verschiedene Attribute, die sich aus dem Umfang der Beteiligung, einem Start- und 
	 * Enddatum zusammensetzen. Die Beteiligung an einem Projekt erhält man bei einer guten Bewertung seiner Bewerbung
	 */
	
	private static final long serialVersionUID = 1L;
	
	/*
	 * Umfang der Tage an denen eine Organisationseinheit an einem Projekt beteiligt ist
	 */
	
	private String umfang = "";
	
	/*
	 * Datum an dem die Beteiligung beginnt
	 */
	
	private Date startdatum = null;
	
	/*
	 * Datum an dem die Beteiligung endet
	 */
	
	private Date enddatum = null;
	
	/*
	 * Fremdschlüsselbeteiligung zu jeweiligem Projekt
	 */
	
	private int Projekt_ID = 0;
	
	/*
	 * Fremdschlüsselbeteiligung zu jeweiliger Organisationseinheit
	 */
	
	private int Orga_ID = 0;
	
	/*Auslesen des Umfangs
	 * @return umfang
	 */
	
	public String getUmfang() {
		return umfang;
	}
	
	/*
	 * Setzen des Beteiligungsumfangs
	 * @param umfang
	 */
	
	public void setUmfang(String umfang) {
		this.umfang = umfang;
	}
	
	/*
	 * Auslesen des Startdatums
	 * @return startdatum
	 */
	
	public Date getStartdatum() {
		return startdatum;
	}
	
	/*
	 * Setzen des Startdatums
	 * @param startdatum
	 */
	
	public void setStartdatum(Date startdatum) {
		this.startdatum = startdatum;
	}
	
	/*
	 * Auslesen des Enddatums
	 * @return enddatum
	 */
	
	public Date getEnddatum() {
		return enddatum;
	}
	
	/*
	 * Setzen des Enddatums
	 * @param enddatum
	 */
	
	public void setEnddatum(Date enddatum) {
		this.enddatum = enddatum;
	}
	
	/*
	 * Auslesen des Fremdschlüssels des dazugehörigen Projekts
	 * @return des Fremdschlüssels
	 */
	
	public int getProjekt_ID() {
		return Projekt_ID;
	}
	
	/*
	 * Setzen des Fremdschlüssels des dazugehörigen Projekts
	 * @param der Fremdschlüssel
	 */
	
	public void setProjekt_ID(int projekt_ID) {
		Projekt_ID = projekt_ID;
	}
	
	/*
	 * Auslesen des Fremdschlüssels der dazugehörigen Organisatoinseinheit
	 *  @return Orga_ID
	 */
	
	public int getOrga_ID() {
		return Orga_ID;
	}
	
	/*
	 * Setzen des Fremdschlüssels der dazugehörigen Organisatoinseinheit
	 * @param orga_ID
	 */
	
	public void setOrga_ID(int orga_ID) {
		Orga_ID = orga_ID;
	}
	
//	public String toString(){
//		return "\n" + "ID: " + BusinessObjects.ID + "\n" + "umfang: " + umfang + "\n"
//				+ "startdatum: " + startdatum + "\n" + "enddatum: " + enddatum
//				+ "\n" + "gehört zum Projekt: " + Projekt_ID + "\n" 
//				+ "Und wurde von folgender Person erstellt: " + Orga_ID;
//	}
	
	/*
	 * 
	 * @param Object o
	 * @return super.equals(c)
	 * @return false
	 */
	
	public boolean equals(Object o) {

	    if (o != null && o instanceof Beteiligung) {
	      Beteiligung c = (Beteiligung) o;
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
