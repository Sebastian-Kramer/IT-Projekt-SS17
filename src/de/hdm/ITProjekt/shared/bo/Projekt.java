package de.hdm.ITProjekt.shared.bo;

import java.util.Date;

public class Projekt extends BusinessObject {
	
	private static final long serialVersionUID = 1L;
	
	/*
	 * eindeutige Identifikationsnummer einer Instanz der Klasse
	 */
	
	private int ID = 0; 
	
	/*
	 * Name des Projekts
	 */
	
	private String name = "";
	
	/*
	 * Beschreibung des Projekts
	 */
	
	private String beschreibung = "";
	
	/*
	 * Startdatum des Projekts
	 */
	
	private Date startdatum;
	
	/*
	 * Enddatum des Projekts
	 */
	
	private Date enddatum;
	
	/*
	 * Realisierung der Beziehung zu einem Projektmarktplatz mittels Fremdschlüssel
	 */
	
	private int Projektmarktplatz_ID = 0;
	
	/*
	 * Realisierung einer Beziehung zu einem Projektleiter mittels Fremdschlüssel
	 */
	
	private int Projektleiter_ID = 1;
	
	/*
	 * Auslesen der ID
	 * @return ID
	 */
	
	public int getID() {
		return ID;
	}
	
	/*
	 * Setzen der ID
	 * @param id 
	 */

	public void setID(int iD) {
		ID = iD;
	}
	
	/*
	 * Auslesen des Namens
	 * @return name
	 */

	public String getName() {
		return name;
	}
	
	/*
	 * setzen des Namens
	 * @param name
	 */

	public void setName(String name) {
		this.name = name;
	}
	
	/*
	 * Auslesen der Beschreibung
	 * @return beschreibung
	 */

	public String getBeschreibung() {
		return beschreibung;
	}
	
	/*
	 * Setzen der Beschreibung
	 * @param beschreibung
	 */

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	
	/*
	 * Auslesen des Startdatums
	 * @return Startdatum
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
	 * Auslesen des Fremdschlüssels Projektmarktplatz ID
	 * @param Projektmarktplatz_ID
	 */
	
	public int getProjektmarktplatz_ID() {
		return Projektmarktplatz_ID;
	}
	
	/*
	 * Setzen des Fremdschlüssels Projektmarktplatz_ID
	 */

	public void setProjektmarktplatz_ID(int projektmarktplatz_ID) {
		Projektmarktplatz_ID = projektmarktplatz_ID;
	}
	
	/*
	 * Auslesen des Fremdschlüssels Projektleiter ID
	 * @return Projektleiter_ID
	 */

	public int getProjektleiter_ID() {
		return Projektleiter_ID;
	}

	/*
	 * Setzen des Fremdschlüssels Projektleiter ID	
	 * @param Projekteiter_ID
	 */
	
	public void setProjektleiter_ID(int projektleiter_ID) {
		Projektleiter_ID = projektleiter_ID;
	}
	
	/*
	 * Zum Testen der Klasse in der Konsole
	 * @return "\n" + "ID: " + ID + "\n" + "Projektname: " + name + "\n" + "Beschreibung: " 
	 * 		+ beschreibung+ "\n" + "Startdatum: " + startdatum+ "\n" + "Enddatum: " + enddatum;
	 * 
	 */

	public String toString(){
		return "\n" + "ID: " + ID + "\n" + "Projektname: " + name + "\n" + "Beschreibung: " 
				+ beschreibung+ "\n" + "Startdatum: " + startdatum+ "\n" + "Enddatum: " + enddatum;
	}
	
	


	public boolean equals(Object o) {

	    if (o != null && o instanceof Projekt) {
	      Projekt c = (Projekt) o;
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


