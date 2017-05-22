package de.hdm.ITProjekt.shared.bo;

import java.util.Date;

public class Bewerbung extends BusinessObject {
	private static final long serialVersionUID = 1L;
	
	private int ID = 0; 
	private String bewerbungstext = "";
	private Date erstelldatum = null;
	private int Ausschreibungs_ID = 0;
	private int Orga_ID = 0;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getBewerbungstext() {
		return bewerbungstext;
	}
	public void setBewerbungstext(String bewerbungstext) {
		this.bewerbungstext = bewerbungstext;
	}
	public Date getErstelldatum() {
		return erstelldatum;
	}
	public void setErstelldatum(Date erstelldatum) {
		this.erstelldatum = erstelldatum;
	}
	public int getAusschreibungs_ID() {
		return Ausschreibungs_ID;
	}
	public void setAusschreibungs_ID(int ausschreibungs_ID) {
		Ausschreibungs_ID = ausschreibungs_ID;
	}
	public int getOrga_ID() {
		return Orga_ID;
	}
	public void setOrga_ID(int orga_ID) {
		Orga_ID = orga_ID;
	}
	
	public String toString(){
		return "\n" + "ID: " + ID + "\n" + "Text: " + bewerbungstext + "\n"
				+ "erstelldatum: " + erstelldatum
				+ "\n" + "gehört zur Ausschreibung: " + Ausschreibungs_ID + "\n" 
				+ "Und wurde von folgender Person erstellt: " + Orga_ID;
	}
	
	

}
