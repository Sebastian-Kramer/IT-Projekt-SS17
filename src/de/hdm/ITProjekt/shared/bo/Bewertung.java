package de.hdm.ITProjekt.shared.bo;
import java.util.Date;

public class Bewertung extends BusinessObject {
	
	private static final long serialVersionUID = 1L;
	
	private int ID = 0;
	private String stellungnahme = "";
	private double bewertung = 0;
	//private Date erstellungsdatum;
	private int Beteiligungs_ID = 0;
	private int Bewerbungs_ID = 0;
	
	
	
	
	public int getID() {
		return ID;
	}




	public void setID(int iD) {
		ID = iD;
	}




	public String getStellungnahme() {
		return stellungnahme;
	}




	public void setStellungnahme(String stellungnahme) {
		this.stellungnahme = stellungnahme;
	}




	public double getBewertung() {
		return bewertung;
	}




	public void setBewertung(double bewertung) {
		this.bewertung = bewertung;
	}




	public int getBeteiligungs_ID() {
		return Beteiligungs_ID;
	}




	public void setBeteiligungs_ID(int beteiligungs_ID) {
		Beteiligungs_ID = beteiligungs_ID;
	}




	public int getBewerbungs_ID() {
		return Bewerbungs_ID;
	}




	public void setBewerbungs_ID(int bewerbungs_ID) {
		Bewerbungs_ID = bewerbungs_ID;
	}




	public String toString(){
		return "\n" + "ID: " + ID + "\n" + "Stellungnahme: " + stellungnahme + "\n"
				+ "Beurteilung: " + bewertung + "\n" + "Diese Beteiligung gehört zu folgender Bewerbung: " + Bewerbungs_ID
				 + "\n";
	}
	
}
