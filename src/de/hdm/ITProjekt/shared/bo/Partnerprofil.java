package de.hdm.ITProjekt.shared.bo;

import java.util.Date;

import java.util.Vector;


/*
 * Realisierung eines Partnerprofils
 */

public class Partnerprofil extends BusinessObject{
	
	/*
	 * Erstellungsdatum des Partnerprofils
	 */
	
	private Date erstellungsdatum = new Date();
	
	/*
	 * Änderungsdatum des Partnerprofils
	 */
	
	private Date aenderungsdatum = null;
	
	private static final long serialVersionUID = 1L;
	

	/*
	 * Standartkonstruktor 
	 */
	
	public Partnerprofil(){
	}
	
	/* 
	 * Überladener Konstruktor
	 */
	
	public Partnerprofil(Date erstellungsdatum, Date aenderungsdatum){
		this.erstellungsdatum = erstellungsdatum;
		this.aenderungsdatum = aenderungsdatum;
	}
	
	/*
	 * Auslesen des Erstellungsdatums
	 * @return erstellungsdatum
	 */
	
public Date getErstellungsdatum() {
		return erstellungsdatum;
	}

/*
 * Setzen des Erstellungsdatums
 * @param erstellungsdatum
 */

	public void setErstellungsdatum(Date erstellungsdatum) {
		this.erstellungsdatum = erstellungsdatum;
	}
	
	/*
	 * Auslesen des Änderungsdatums
	 * @return aenderungsdatum
	 */

	public Date getAenderungsdatum() {
		return aenderungsdatum;
	}
	
	/*
	 * Setzen des Änderungsdatums
	 * @paramaenderungsdatum
	 */

	public void setAenderungsdatum(Date aenderungsdatum) {
		this.aenderungsdatum = aenderungsdatum;
	}


}
