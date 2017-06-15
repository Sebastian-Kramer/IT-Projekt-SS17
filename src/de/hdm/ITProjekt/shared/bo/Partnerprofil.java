package de.hdm.ITProjekt.shared.bo;

import java.util.Date;
import java.util.Vector;

/**
 * 
 * @author Mert
 * Klasse Partnerprofil mit einer verschachtelten Klasse "Eigenschaft". Verschachtelte Klasse, da
 * eine Aggregation besteht
 * 
 * Es wurden nur die Attribute, Vector, Konstruktor und Getter/Setter eingef�gt.
 * 
 */
public class Partnerprofil extends BusinessObject{
	
	private Date erstellungsdatum;
	private Date aenderungsdatum;
	private static final long serialVersionUID = 1L;
	
	
	
	public Partnerprofil(){
	}
	
	public Partnerprofil(Date erstellungsdatum, Date aenderungsdatum){
		this.erstellungsdatum = erstellungsdatum;
		this.aenderungsdatum = aenderungsdatum;
	}
	
	
	
	
public Date getErstellungsdatum() {
		return erstellungsdatum;
	}

	public void setErstellungsdatum(Date erstellungsdatum) {
		this.erstellungsdatum = erstellungsdatum;
	}

	public Date getAenderungsdatum() {
		return aenderungsdatum;
	}

	public void setAenderungsdatum(Date aenderungsdatum) {
		this.aenderungsdatum = aenderungsdatum;
	}


}
