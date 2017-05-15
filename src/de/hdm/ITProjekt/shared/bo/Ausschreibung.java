package de.hdm.ITProjekt.shared.bo;

import java.util.Date;

public class Ausschreibung extends BusinessObject {
	
	private static final long serialVersionUID = 1L;
	
	private int ID = 0; 
	private String ausschreibungstext = "";
	private String bezeichnung = "";
	private Date datum;
	
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getAusschreibungstext() {
		return ausschreibungstext;
	}
	public void setAusschreibungstext(String ausschreibungstext) {
		this.ausschreibungstext = ausschreibungstext;
	}
	public String getBezeichnung() {
		return bezeichnung;
	}
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	public Date getDatum() {
		return datum;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
	}
	
	public String toString(){
		return "\n" + "ID: " + ID + "\n" + "Ausschreibungstext: " + ausschreibungstext + "\n"
				+ "Bezeichnung: " + bezeichnung + "\n" + "Datum: " + datum;
	}
	
	public boolean equals(Object o) {

	    if (o != null && o instanceof Ausschreibung) {
	      Ausschreibung c = (Ausschreibung) o;
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
