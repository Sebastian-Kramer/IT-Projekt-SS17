package de.hdm.ITProjekt.shared.bo;
import java.util.Date;

public class Bewertung extends BusinessObject {
	
	private static final long serialVersionUID = 1L;
	
	/*
	 * eindeutige Identifikationsnummer einer Instanz der Klasse
	 */
	
	private int ID = 0;
	
	/*
	 * Text der Stellungsnahme 
	 */
	
	private String stellungnahme = "";
	
	/*
	 * Score der Bewertung
	 */
	
	private double bewertung = 0.0;
	
	/*
	 * Erstelungsdatum der Bewertung
	 */
	
	private Date erstellungsdatum;
	
	/*
	 * Realisieren der Beziehung zu einer Beteiligung mittels Fremdschlüssel
	 */
	
	private int Beteiligungs_ID = 0;
	
	/*
	 * Realisieren der Beziehung zu einer Bewerbung mittels Fremdschlüssel
	 */
	
	private int Bewerbungs_ID = 0;
	
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
	 * Auslesen der Stellungnahme
	 * @return stellungnahme
	 */
	
	public String getStellungnahme() {
		return stellungnahme;
	}
	
	/*
	 * Setzen des Textes zu Stellungnahme
	 * @param stellungnahme
	 */
	
	public void setStellungnahme(String stellungnahme) {
		this.stellungnahme = stellungnahme;
	}

	/*
	 * Auslesen der Bewertung
	 * @return Bewertung
	 */

	public double getBewertung() {
		return bewertung;
	}
	
	/*
	 * Setzen der Bewertung
	 * @param Bewertung
	 */

	public void setBewertung(double bewertung) {
		this.bewertung = bewertung;
	}

	/*
	 * Auslesen des Fremdschlüssels der Beteiligungs ID
	 * @param Beteiligungs_ID
	 */
	
	public int getBeteiligungs_ID() {
		return Beteiligungs_ID;
	}
	
	/*
	 * Setzen des Fremdschlüssels Beteiligungs_ID
	 * @param Beteiligungs_ID
	 */

	public void setBeteiligungs_ID(int beteiligungs_ID) {
		Beteiligungs_ID = beteiligungs_ID;
	}
	
	/*
	 * Auslesen des Fremdschlüssels Bewerbungs ID
	 * @return Bewerbungs_ID
	 */
	
	public int getBewerbungs_ID() {
		return Bewerbungs_ID;
	}
	
	/*
	 * Setzen des Fremdschlüssels Bewerbungs ID
	 * @param Bewerbungs_ID
	 */

	public void setBewerbungs_ID(int bewerbungs_ID) {
		Bewerbungs_ID = bewerbungs_ID;
	}
	
	/*
	 * zum Testen der Mapper in der Konsole
	 * @return "\n" + "ID: " + ID + "\n" + "Stellungnahme: " + stellungnahme + "\n"
	 * 	+ "Beurteilung: " + bewertung + "\n" + "Diese Beteiligung gehört zu folgender Bewerbung: " + Bewerbungs_ID
	 * 		 + "\n";
	 */

	public String toString(){
		return "\n" + "\n" + "Stellungnahme: " + stellungnahme + "\n"
				+ "Höhe der Bewertung: " + bewertung + "\n";
	}
	
}
