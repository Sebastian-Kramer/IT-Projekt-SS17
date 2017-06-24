package de.hdm.ITProjekt.shared.bo;

import java.util.Date;

public class Bewerbung extends BusinessObject {
	
	private static final long serialVersionUID = 1L;
	
	/*
	 * eindeutige Identifikationsnummer einer Instanz der Klasse
	 */
	
	private int ID = 0; 
	
	/*
	 * Bewerbungstext der Bewerbung
	 */
	
	private String bewerbungstext = "";
	
	/*
	 * Erstellungsdatum der Bewerbung, das bei Erstellung jedes Objekts
	 * von Bewerbung das aktuelle Datum durch Null-Argument-Konstruktor der importierten Klasse Date setzt
	 */
	
	private Date erstelldatum = null;
	
	/*
	 * Realisierung der Beziehung einer Ausschriebung mittels Fremdschlüssel
	 */
	
	private Integer Ausschreibungs_ID = 0;
	
	/*
	 *  Realisierung der Beziehung einer Organisationseinheit mittels Fremdschlüssel
	 */
	
	private Integer Orga_ID = 0;
	
	/*
	 * Auslesen der ID
	 * @return ID 
	 */
	
	public int getID() {
		return ID;
	}
	
	/*
	 * Setzen der ID
	 * @param ID
	 */
	
	public void setID(int iD) {
		ID = iD;
	}
	
	/*
	 * Auslesen des Bewerbungstextes
	 * @return bewerbungstext
	 */
	
	public String getBewerbungstext() {
		return bewerbungstext;
	}

	/*
	 * Setzen des Bewerbungstextes
	 * @param bewerbungstext
	 */
	
	public void setBewerbungstext(String bewerbungstext) {
		this.bewerbungstext = bewerbungstext;
	}
	
	/*
	 * Auslesen des Erstellungsdatums
	 * @return erstelldatum
	 */
	
	public Date getErstelldatum() {
		return erstelldatum;
	}
	
	/*
	 * Setzen des Erstelldatums
	 * @param erstelldatum
	 */
	
	public void setErstelldatum(Date erstelldatum) {
		this.erstelldatum = erstelldatum;
	}
	
	/*
	 * Auslesen des Fremdschlüssels Ausschreibungs ID
	 * @return Ausschreibungs_ID
	 */
	
	public Integer getAusschreibungs_ID() {
		return Ausschreibungs_ID;
	}
	
	/*
	 * Setzen des Fremdschlüssels Ausschreibungs ID
	 * @param Ausschreibungs_ID
	 */
	
	public void setAusschreibungs_ID(Integer ausschreibungs_ID) {
		if(ausschreibungs_ID==0){
			this.Ausschreibungs_ID = null;
		}else{
			this.Ausschreibungs_ID = ausschreibungs_ID;
		}
	}
	
	/*
	 * Auslesen des Fremdschlüssels Organisations ID
	 * @return Orga_ID
	 */
	
	public Integer getOrga_ID() {
		return Orga_ID;
	}
	
	/*
	 * Setzen des Fremdschlüssels Organisations ID
	 * @param orga_ID
	 */
	
	public void setOrga_ID(Integer orga_ID) {
		if(orga_ID==0){
			this.Orga_ID = null;
		}else{
			this.Orga_ID = orga_ID;
		}
	}
	
	/*
	 * zum Testen der Mapper in der Konsole
	 * @return "\n" + "ID: " + ID + "\n" + "Ausschreibungstext: " + ausschreibungstext + "\n"
	 * 			+ "Bezeichnung: " + bezeichnung + "\n" + "Datum: " + datum;
	 */
	
	public String toString(){
		return "\n" + "ID: " + ID + "\n" + "Text: " + bewerbungstext + "\n"
				+ "erstelldatum: " + erstelldatum
				+ "\n" + "gehört zur Ausschreibung: " + Ausschreibungs_ID + "\n" 
				+ "Und wurde von folgender Person erstellt: " + Orga_ID;
	}
	
	

}
