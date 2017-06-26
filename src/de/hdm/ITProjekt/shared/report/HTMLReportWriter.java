package de.hdm.ITProjekt.shared.report;

import java.io.Serializable;

public class HTMLReportWriter implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	   * Diese Variable wird mit dem Ergebnis einer Umwandlung (vgl.
	   * <code>process...</code>-Methoden) belegt. Format: Text
	   */
	  private String reportText = "";

	  /**
	   * Zurücksetzen der Variable <code>reportText</code>.
	   */
	  public void resetReportText() {
	    this.reportText = "";
	  }

		public String paragraphToHTML(Paragraph p) {
			if (p instanceof CompositeParagraph) {
			    return this.paragraphToHTML((CompositeParagraph) p);
			}
			else {
			    return this.paragraphToHTML((SimpleParagraph) p);
			}
		}	
		
		/**
		 * Umwadeln eines CompositeParagraphs in HTML.
		 * @param p der Paragraph.
		 * @return HTML-Text.
		 */
		 public String paragraphToHTML(CompositeParagraph p) {
			StringBuffer result = new StringBuffer();

			for (int i = 0; i < p.getParagraphsSize(); i++) {
			result.append("<p>" + p.getParagraphByIndex(i) + "</p>");
			}
			return result.toString();
		}	
		
		/**
		 * Umwandeln eines SimpleParagraphs in HTML.
		 * @param p der Paragraph.
		 * @return HTML Text.
		 */
		 public String paragraphToHTML(SimpleParagraph p) {
			return "<p>" + p.toString() + "</p>";
		}
		 
		 /**
		  * HTML Header Text erstellen.
		  * @return HTMl-Text.
		  */
	  /**
	   * Header-Text produzieren.
	   * 
	   * @return Text
	   */
	  public String getHeader() {
	    // Wir benötigen für Demozwecke keinen Header.
	    return "";
	  }

	  /**
	   * Trailer-Text produzieren.
	   * 
	   * @return Text
	   */
	  public String getTrailer() {
	    // Wir verwenden eine einfache Trennlinie, um das Report-Ende zu markieren.
	    return "___________________________________________";
	  }

	  /**
	   * Prozessieren des übergebenen Reports und Ablage im Zielformat. Ein Auslesen
	   * des Ergebnisses kann später mittels <code>getReportText()</code> erfolgen.
	   * 
	   * @param r der zu prozessierende Report
	   */

	  public void process(AllAusschreibungenByPartnerprofilReport r) {
	}
	  
	  public void process(AllAusschreibungenReport r){  
	  }

	  public void process(AllBeteiligungenToProjectReport r) {
	  }
	  
	  public void process(AllBewerbungenByAusschreibungReport r){
	  }
	  
	  public void process(AllBewerbungenByOrganisationseinheitReport r) {  
	  }
	  
	  public void process(AllBewerbungenToOneAusschreibungReport r) {
	  }
	  
	  public void process(AllBewerbungenWithAusschreibungenReport r) {
	  }
}
