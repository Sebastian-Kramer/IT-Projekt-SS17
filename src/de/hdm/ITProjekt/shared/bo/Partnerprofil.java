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
	private String arbeitsgebiet;
	private int berufserfahrungsJahre;
	private static final long serialVersionUID = 1L;
	
	
	
	public Partnerprofil(){
	}
	
	public Partnerprofil(Date erstellungsdatum, Date aenderungsdatum, String arbeitsgebiet, int berufserfahrungsJahre ){
		this.erstellungsdatum = erstellungsdatum;
		this.aenderungsdatum = aenderungsdatum;
		this.arbeitsgebiet = arbeitsgebiet;
		this.berufserfahrungsJahre = berufserfahrungsJahre;
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

	public String getArbeitsgebiet() {
		return arbeitsgebiet;
	}

	public void setArbeitsgebiet(String arbeitsgebiet) {
		this.arbeitsgebiet = arbeitsgebiet;
	}

	public int getBerufserfahrungsJahre() {
		return berufserfahrungsJahre;
	}

	public void setBerufserfahrungsJahre(int berufserfahrungsJahre) {
		this.berufserfahrungsJahre = berufserfahrungsJahre;
	}
	
	

}
