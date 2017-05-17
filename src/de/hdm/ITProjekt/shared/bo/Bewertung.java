package de.hdm.ITProjekt.shared.bo;
import java.util.Date;

public class Bewertung extends BusinessObject {
	private static final long serialVersionUID = 1L;
	private String text;
	private double wert;
	private Date erstellungsdatum;
	
	public Bewertung(){
	}
	
	public Bewertung(String text, double wert, Date erstellungsdatum){
		this.text= text;
		this.wert= wert;
		this.erstellungsdatum= erstellungsdatum;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public double getWert() {
		return wert;
	}

	public void setWert(double wert) {
		this.wert = wert;
	}

	public Date getErstellungsdatum() {
		return erstellungsdatum;
	}

	public void setErstellungsdatum(Date erstellungsdatum) {
		this.erstellungsdatum = erstellungsdatum;
	}

}
