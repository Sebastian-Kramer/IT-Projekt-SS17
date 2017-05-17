package de.hdm.ITProjekt.shared.bo;

import java.util.Date;

public class Beteiligung extends BusinessObject {
	
	private static final long serialVersionUID = 1L;
	
	private int ID = 0; 
	private String umfang = "";
	private Date startdatum = null;
	private Date enddatum = null;
	private int Projekt_ID = 0;
	private int Orga_ID = 0;
	
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getUmfang() {
		return umfang;
	}
	public void setUmfang(String umfang) {
		this.umfang = umfang;
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
	public int getProjekt_ID() {
		return Projekt_ID;
	}
	public void setProjekt_ID(int projekt_ID) {
		Projekt_ID = projekt_ID;
	}
	public int getOrga_ID() {
		return Orga_ID;
	}
	public void setOrga_ID(int orga_ID) {
		Orga_ID = orga_ID;
	}
	
	public String toString(){
		return "\n" + "ID: " + ID + "\n" + "umfang: " + umfang + "\n"
				+ "startdatum: " + startdatum + "\n" + "enddatum: " + enddatum
				+ "\n" + "gehört zum Projekt: " + Projekt_ID + "\n" 
				+ "Und wurde von folgender Person erstellt: " + Orga_ID;
	}
	
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
