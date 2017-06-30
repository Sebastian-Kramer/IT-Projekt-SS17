package de.hdm.ITProjekt.shared.bo;

import java.util.Date;

/*
 * Jede Bewerbung die auf eine Stellenausschreibung abgegeben wurde kann vom Projektleiter bewertet werden.
 * Die Möglichkeite der Bewertung liegen zwischen 0.0 (sehr schlecht) und 1.0 (sehr gut).
 * Eine Bewertung enthält eine Stellungnahme und die Höhe der Bewertung, sowie ein Erstelldatum
 */

public class Bewertung extends BusinessObject {
	
	private static final long serialVersionUID = 1L;
	
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
	
	private Integer Beteiligungs_ID = 0;
	
	/*
	 * Realisieren der Beziehung zu einer Bewerbung mittels Fremdschlüssel
	 */
	
	private Integer Bewerbungs_ID = 0;

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

	public void setBeteiligungs_ID(Integer beteiligungs_ID) {
		if(beteiligungs_ID==0){
			this.Beteiligungs_ID=null;
		}else{
			this.Beteiligungs_ID=beteiligungs_ID;
		}
	}
	
	/*
	 * Auslesen des Fremdschlüssels Bewerbungs ID
	 * @return Bewerbungs_ID
	 */
	
	public Integer getBewerbungs_ID() {
		return Bewerbungs_ID;
	}
	
	/*
	 * Setzen des Fremdschlüssels Bewerbungs ID
	 * @param Bewerbungs_ID
	 */

	public void setBewerbungs_ID(Integer bewerbungs_ID) {
		if(bewerbungs_ID==0){
			this.Bewerbungs_ID=null;
		}else{
			this.Bewerbungs_ID=bewerbungs_ID;
		}
	}
	
	/*
	 * zum Testen der Mapper in der Konsole
	 * @return "\n" + "ID: " + ID + "\n" + "Stellungnahme: " + stellungnahme + "\n"
	 * 	+ "Beurteilung: " + bewertung + "\n" + "Diese Beteiligung gehört zu folgender Bewerbung: " + Bewerbungs_ID
	 * 		 + "\n";
	 */

	public String toString(){
		return "\n" + "Stellungnahme: " + stellungnahme + "\n"
				+ "Höhe der Bewertung: " + bewertung + "\n";
	}
	
}
