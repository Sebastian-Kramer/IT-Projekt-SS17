package de.hdm.ITProjekt.shared.bo;

import java.util.Date;

public class Projekt extends BusinessObject {
	
	private static final long serialVersionUID = 1L;
	
	
	private int ID = 0; 
	private String name = "";
	private String beschreibung = "";
	private Date startdatum;
	private Date enddatum;
	
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public Date getStartdatum() {
		return startdatum;
	}

	public void setStartdatum(Date startdatum) {
		this.startdatum = startdatum;
	}

	public Date getEnddatum() {
		return enddatum;
	}

	public void setEnddatum(Date enddatum) {
		this.enddatum = enddatum;
	}

	public String toString(){
		return "ID: " + ID + "\n" + "Projektname: " + name + "\n" + "Beschreibung: " 
				+ beschreibung+ "\n" + "Startdatum: " + startdatum + "Enddatum: " + enddatum;
	}
	
	public boolean equals(Object o) {

	    if (o != null && o instanceof Projektmarktplatz) {
	      Projektmarktplatz c = (Projektmarktplatz) o;
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


