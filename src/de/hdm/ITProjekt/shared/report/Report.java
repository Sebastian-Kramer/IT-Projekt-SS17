package de.hdm.ITProjekt.shared.report;

import java.io.Serializable;
import java.util.Date;

public class Report implements Serializable {

	private static final long serialVersionUID = 1L;


	private String titel = "";
	

	private Date erstellungsdatum = null;
	
	

	private Paragraph header = null;

	

	public String getTitel() {
		return titel;
	}


	public void setTitel(String titel) {
		this.titel = titel;
	}


	public Date getErstellungsdatum() {
		return erstellungsdatum;
	}

	

	public void setErstellungsdatum(Date erstellungsdatum) {
		this.erstellungsdatum = erstellungsdatum;
	}

	// zurückgeben von Paragraph Objekt, dass die Kopfdaten beinhaltet
	public Paragraph getHeader() {
		return header;
	}


	public void setHeader(Paragraph header) {
		this.header = header;
}
	
}
