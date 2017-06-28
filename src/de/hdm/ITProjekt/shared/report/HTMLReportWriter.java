package de.hdm.ITProjekt.shared.report;

import java.io.Serializable;
import java.util.Vector;

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
	    
		StringBuffer result = new StringBuffer();
	    
		result.append("<html><head><title></title></head><body>");
		return result.toString();
	  }

	  /**
	   * Trailer-Text produzieren.
	   * 
	   * @return Text
	   */
	  public String getTrailer() {
	    return   "</body></html>";
	  }
	  
	  /**
	   * Liest die Ergebnisse der aufgerufenen Prozessierungsmethode.
	   * @return String in HTML-Format.
	   */
	  public String getReportText() {
		 return this.getHeader() + this.reportText + this.getTrailer();
	}

	  /**
	   * Prozessieren des übergebenen Reports und Ablage im Zielformat. Ein Auslesen
	   * des Ergebnisses kann später mittels <code>getReportText()</code> erfolgen.
	   * 
	   * @param r der zu prozessierende Report
	   */

	  public void process(AllAusschreibungenReport r) {
		// Zunächst löschen wir das Ergebnis vorhergehender Prozessierungen.
		    this.resetReportText();

		    /*
		     * In diesen Buffer schreiben wir während der Prozessierung sukzessive
		     * unsere Ergebnisse.
		     */
		    StringBuffer result = new StringBuffer();

		    /*
		     * Nun werden Schritt für Schritt die einzelnen Bestandteile des Reports
		     * ausgelesen und in Text-Form übersetzt.
		     */
		    
		  	result.append("<H3>" + r.getTitel() + "</H3>");
		  	result.append("<table style=\"width:400px;border:1px solid silver\"><tr>");
		  	result.append("</tr><tr><td></td><td>" + r.getErstelldatum().toString()
		  	        + "</td></tr></table>");
		    
		  	 Vector<Row> rows = r.getRows();
		     result.append("<table style=\"width:400px\">");
		     
		     for (int i = 0; i < rows.size(); i++) {
		         Row row = rows.elementAt(i);
		         result.append("<tr>");
		         for (int k = 0; k < row.getColumnsSize(); k++) {
		           if (i == 0) {
		        	
		             result.append("<td style=\"background:silver;font-weight:bold\">" + row.getColumnByIndex(k)
		                 + "</td>");
		             
		           }
		           else {
		             if (i > 1) {
		               result.append("<td style=\"border-top:1px solid silver\">"
		                   + row.getColumnByIndex(k) + "</td>");
		             }
		             else {
		               result.append("<td valign=\"top\">" + row.getColumnByIndex(k) + "</td>");
		             }
		           }
		         }
		         result.append("</tr>");
		       }

		       result.append("</table>");
		       
		       /*
		        * Zum Schluss wird unser Arbeits-Buffer in einen String umgewandelt und der
		        * reportText-Variable zugewiesen. Dadurch wird es möglich, anschließend das
		        * Ergebnis mittels getReportText() auszulesen.
		        */
		       this.reportText = result.toString();
	  }

		    
		   	 
	
	  
	  public void process(AllAusschreibungenByPartnerprofilReport r) {
		//Löschen des Ergebnisses der vorherigen Prozessierung
		  this.resetReportText();
		  
		  StringBuffer result = new StringBuffer();

		  
		  /*
		     * Die einzelnen Bestandteile des Reports werden
		     * ausgelesen und in HTML-Form übersetzt.
		     */
		  	result.append("<H2>" + r.getTitel() + "</H2>");
		  	result.append("<table style=\"width:400px;border:1px solid silver\"><tr>");
		  	result.append("</tr><tr><td></td><td>" + r.getErstelldatum().toString()
		  	        + "</td></tr></table>");
		  	
		  
		  	
		  	 Vector<Row> rows = r.getRows();
		     result.append("<table style=\"width:400px\">");
		     
		     for (int i = 0; i < rows.size(); i++) {
		         Row row = rows.elementAt(i);
		         result.append("<tr>");
		         for (int k = 0; k < row.getColumnsSize(); k++) {
		           if (i == 0) {
		        	
		             result.append("<td style=\"background:silver;font-weight:bold\">" + row.getColumnByIndex(k)
		                 + "</td>");
		             
		           }
		           else {
		             if (i > 1) {
		               result.append("<td style=\"border-top:1px solid silver\">"
		                   + row.getColumnByIndex(k) + "</td>");
		             }
		             else {
		               result.append("<td valign=\"top\">" + row.getColumnByIndex(k) + "</td>");
		             }
		           }
		         }
		         result.append("</tr>");
		       }

		       result.append("</table>");
		       
		       /*
		        * Der Arbeits-Buffer wird am Ende in einen String umgewandelt und der
		        * reportText-Variable zugewiesen. Das
		        * Ergebnis kann dann mittels getReportText() ausgelesen werden.
		        */
		       this.reportText = result.toString();
	  }
	  
	  public void process(ProjektverflechtungReport r){
	  
		  // Zunächst löschen wir das Ergebnis vorhergehender Prozessierungen.
		    this.resetReportText();

		    /*
		     * In diesen Buffer schreiben wir während der Prozessierung sukzessive
		     * unsere Ergebnisse.
		     */
		    StringBuffer result = new StringBuffer();

			  /*
		     * Die einzelnen Bestandteile des Reports werden
		     * ausgelesen und in HTML-Form übersetzt.
		     */
		    result.append("<H1>" + r.getTitel() + "</H1>");
		    result.append("<table><tr>");
		    result.append("</tr><tr><td></td><td>" + r.getErstelldatum().toString()
		        + "</td></tr></table>");

		    /*
		     * Da ProjektverflechtungenReport ein CompositeReport ist, enthält r
		     * eine Menge von Teil-Reports. 
		     * Das Ergebnis des jew. Aufrufs fügen wir dem Buffer hinzu.
		     */
		    for (int i = 0; i < r.getSubReportsSize(); i++) {
		      /*
		       * AlleBewerbungenAufEineAusschreibungDesUsers wird als Typ der SubReports vorausgesetzt.
		       * Sollte dies in einer erweiterten Form des Projekts nicht mehr gelten,
		       * so müsste hier eine detailliertere Implementierung erfolgen.
		       */
		    
		      this.processSimpleReport(r.getSubReportByIndex(i));

		      result.append(this.reportText + "\n");


		      this.resetReportText();
		    }

		       /*
		        * Der Arbeits-Buffer wird am Ende in einen String umgewandelt und der
		        * reportText-Variable zugewiesen. Das
		        * Ergebnis kann dann mittels getReportText() ausgelesen werden.
		        */
		    this.reportText = result.toString();
	  }
	  
	  public void process(FanInFanOutReport r){
		    // Zunächst löschen wir das Ergebnis vorhergehender Prozessierungen.
		    this.resetReportText();

		    /*
		     * In diesen Buffer schreiben wir während der Prozessierung sukzessive
		     * unsere Ergebnisse.
		     */
		    StringBuffer result = new StringBuffer();

			  /*
		     * Die einzelnen Bestandteile des Reports werden
		     * ausgelesen und in HTML-Form übersetzt.
		     */
		    result.append("<H2>" + r.getTitel() + "</H2>");
		    result.append("<table><tr>");
		    result.append("</tr><tr><td></td><td>" + r.getErstelldatum().toString()
		        + "</td></tr></table>");

		    /*
		     * Da ProjektverflechtungenReport ein CompositeReport ist, enthält r
		     * eine Menge von Teil-Reports
		     *  Das Ergebnis des jew. Aufrufs fügen wir dem Buffer hinzu.
		     */
		    for (int i = 0; i < r.getSubReportsSize(); i++) {

		    
		      this.processSimpleReport(r.getSubReportByIndex(i));

		      result.append(this.reportText + "\n");

		      /*
		       * Nach jeder Übersetzung eines Teilreports und anschließendem Auslesen
		       * sollte die Ergebnisvariable zurückgesetzt werden.
		       */
		      this.resetReportText();
		    }

		       /*
		        * Der Arbeits-Buffer wird am Ende in einen String umgewandelt und der
		        * reportText-Variable zugewiesen. Das
		        * Ergebnis kann dann mittels getReportText() ausgelesen werden.
		        */
		    this.reportText = result.toString();
	  }
	  
	  
	  public void process(AllBeteiligungenToProjectReport r) {
		  
		  //Löschen des Ergebnisses der vorherigen Prozessierung
		  this.resetReportText();
		  

		    /*
		     * In diesen Buffer schreiben wir während der Prozessierung sukzessive
		     * unsere Ergebnisse.
		     */
		  StringBuffer result = new StringBuffer();
		  
		  
		  /*
		     * Die einzelnen Bestandteile des Reports werden
		     * ausgelesen und in HTML-Form übersetzt.
		     */
		  	result.append("<H1>" + r.getTitel() + "</H1>");
		  	result.append("<table style=\"width:400px;border:1px solid silver\"><tr>");
		  	result.append("</tr><tr><td></td><td>" + r.getErstelldatum().toString()
		  	        + "</td></tr></table>");
		  	
		  	
		  	 Vector<Row> rows = r.getRows();
		     result.append("<table style=\"width:400px\">");
		     
		     for (int i = 0; i < rows.size(); i++) {
		         Row row = rows.elementAt(i);
		         result.append("<tr>");
		         for (int k = 0; k < row.getColumnsSize(); k++) {
		           if (i == 0) {
		             result.append("<td style=\"background:silver;font-weight:bold\">" + row.getColumnByIndex(k)
		                 + "</td>");
		           }
		           else {
		             if (i > 1) {
		               result.append("<td style=\"border-top:1px solid silver\">"
		                   + row.getColumnByIndex(k) + "</td>");
		             }
		             else {
		               result.append("<td valign=\"top\">" + row.getColumnByIndex(k) + "</td>");
		             }
		           }
		         }
		         result.append("</tr>");
		       }

		       result.append("</table>");
		       
		       /*
		        * Der Arbeits-Buffer wird am Ende in einen String umgewandelt und der
		        * reportText-Variable zugewiesen. Das
		        * Ergebnis kann dann mittels getReportText() ausgelesen werden.
		        */
		       this.reportText = result.toString();
	  }
	  
	  
	  public void process(AllBewerbungenByAusschreibungReport r){
		  

			  //Löschen des Ergebnisses der vorherigen Prozessierung
			  this.resetReportText();
			  

			    /*
			     * In diesen Buffer schreiben wir während der Prozessierung sukzessive
			     * unsere Ergebnisse.
			     */
			  StringBuffer result = new StringBuffer();
			  
			  
			  /*
			     * Die einzelnen Bestandteile des Reports werden
			     * ausgelesen und in HTML-Form übersetzt.
			     */
			  	result.append("<H3>" + r.getTitel() + "</H3>");
			  	result.append("<table style=\"width:400px;border:1px solid silver\"><tr>");
			  	result.append("</tr><tr><td></td><td>" + r.getErstelldatum().toString()
			  	        + "</td></tr></table>");
			  	
			  	
			  	 Vector<Row> rows = r.getRows();
			     result.append("<table style=\"width:400px\">");
			     
			     for (int i = 0; i < rows.size(); i++) {
			         Row row = rows.elementAt(i);
			         result.append("<tr>");
			         for (int k = 0; k < row.getColumnsSize(); k++) {
			           if (i == 0) {
			             result.append("<td style=\"background:silver;font-weight:bold\">" + row.getColumnByIndex(k)
			                 + "</td>");
			           }
			           else {
			             if (i > 1) {
			               result.append("<td style=\"border-top:1px solid silver\">"
			                   + row.getColumnByIndex(k) + "</td>");
			             }
			             else {
			               result.append("<td valign=\"top\">" + row.getColumnByIndex(k) + "</td>");
			             }
			           }
			         }
			         result.append("</tr>");
			       }

			       result.append("</table>");
			       
			       /*
			        * Der Arbeits-Buffer wird am Ende in einen String umgewandelt und der
			        * reportText-Variable zugewiesen. Das
			        * Ergebnis kann dann mittels getReportText() ausgelesen werden.
			        */
			       this.reportText = result.toString();
	  }	  
	  
	  
	  public void process(AllBewerbungenToOneAusschreibungReport r) {
		  //Löschen des Ergebnisses der vorherigen Prozessierung
		  this.resetReportText();
		  

		    /*
		     * In diesen Buffer schreiben wir während der Prozessierung sukzessive
		     * unsere Ergebnisse.
		     */
		  StringBuffer result = new StringBuffer();
		  
		  
		  /*
		     * Die einzelnen Bestandteile des Reports werden
		     * ausgelesen und in HTML-Form übersetzt.
		     */
		  	result.append("<H3>" + r.getTitel() + "</H3>");
		  	result.append("<table style=\"width:400px;border:1px solid silver\"><tr>");
		  	result.append("</tr><tr><td></td><td>" + r.getErstelldatum().toString()
		  	        + "</td></tr></table>");
		  	
		  	
		  	 Vector<Row> rows = r.getRows();
		     result.append("<table style=\"width:400px\">");
		     
		     for (int i = 0; i < rows.size(); i++) {
		         Row row = rows.elementAt(i);
		         result.append("<tr>");
		         for (int k = 0; k < row.getColumnsSize(); k++) {
		           if (i == 0) {
		             result.append("<td style=\"background:silver;font-weight:bold\">" + row.getColumnByIndex(k)
		                 + "</td>");
		           }
		           else {
		             if (i > 1) {
		               result.append("<td style=\"border-top:1px solid silver\">"
		                   + row.getColumnByIndex(k) + "</td>");
		             }
		             else {
		               result.append("<td valign=\"top\">" + row.getColumnByIndex(k) + "</td>");
		             }
		           }
		         }
		         result.append("</tr>");
		       }

		       result.append("</table>");
		       
		       /*
		        * Der Arbeits-Buffer wird am Ende in einen String umgewandelt und der
		        * reportText-Variable zugewiesen. Das
		        * Ergebnis kann dann mittels getReportText() ausgelesen werden.
		        */
		       this.reportText = result.toString();
	  } 
	 
	  
	  public void process(AllBewerbungenWithAusschreibungenReport r) {
	  
		  //Löschen des Ergebnisses der vorherigen Prozessierung
		  this.resetReportText();
		  

		 
		  StringBuffer result = new StringBuffer();
		  
		  
		  /*
		     * Die einzelnen Bestandteile des Reports werden
		     * ausgelesen und in HTML-Form übersetzt.
		     */
		  	result.append("<H1>" + r.getTitel() + "</H1>");
		  	result.append("<table style=\"width:400px;border:1px solid silver\"><tr>");
		  	result.append("</tr><tr><td></td><td>" + r.getErstelldatum().toString()
		  	        + "</td></tr></table>");
		  	
		  	
		  	 Vector<Row> rows = r.getRows();
		     result.append("<table style=\"width:400px\">");
		     
		     for (int i = 0; i < rows.size(); i++) {
		         Row row = rows.elementAt(i);
		         result.append("<tr>");
		         for (int k = 0; k < row.getColumnsSize(); k++) {
		           if (i == 0) {
		             result.append("<td style=\"background:silver;font-weight:bold\">" + row.getColumnByIndex(k)
		                 + "</td>");
		           }
		           else {
		             if (i > 1) {
		               result.append("<td style=\"border-top:1px solid silver\">"
		                   + row.getColumnByIndex(k) + "</td>");
		             }
		             else {
		               result.append("<td valign=\"top\">" + row.getColumnByIndex(k) + "</td>");
		             }
		           }
		         }
		         result.append("</tr>");
		       }

		       result.append("</table>");
		       
		       /*
		        * Der Arbeits-Buffer wird am Ende in einen String umgewandelt und der
		        * reportText-Variable zugewiesen. Das
		        * Ergebnis kann dann mittels getReportText() ausgelesen werden.
		        */
		       this.reportText = result.toString();
	  }
	  
	  public void process(FanIn r){
		  
		  //Löschen des Ergebnisses der vorherigen Prozessierung
		  this.resetReportText();
		  

		    /*
		     * In diesen Buffer schreiben wir während der Prozessierung sukzessive
		     * unsere Ergebnisse.
		     */
		  StringBuffer result = new StringBuffer();
		  
		  
		  /*
		     * Die einzelnen Bestandteile des Reports werden
		     * ausgelesen und in HTML-Form übersetzt.
		     */
		  	result.append("<H3>" + r.getTitel() + "</H3>");
		  	result.append("<table style=\"width:400px;border:1px solid silver\"><tr>");
		  	result.append("</tr><tr><td></td><td>" + r.getErstelldatum().toString()
		  	        + "</td></tr></table>");
		  	
		  	
		  	 Vector<Row> rows = r.getRows();
		     result.append("<table style=\"width:400px\">");
		     
		     for (int i = 0; i < rows.size(); i++) {
		         Row row = rows.elementAt(i);
		         result.append("<tr>");
		         for (int k = 0; k < row.getColumnsSize(); k++) {
		           if (i == 0) {
		             result.append("<td style=\"background:silver;font-weight:bold\">" + row.getColumnByIndex(k)
		                 + "</td>");
		           }
		           else {
		             if (i > 1) {
		               result.append("<td style=\"border-top:1px solid silver\">"
		                   + row.getColumnByIndex(k) + "</td>");
		             }
		             else {
		               result.append("<td valign=\"top\">" + row.getColumnByIndex(k) + "</td>");
		             }
		           }
		         }
		         result.append("</tr>");
		       }

		       result.append("</table>");
		       
		       /*
		        * Der Arbeits-Buffer wird am Ende in einen String umgewandelt und der
		        * reportText-Variable zugewiesen. Das
		        * Ergebnis kann dann mittels getReportText() ausgelesen werden.
		        */
		       this.reportText = result.toString();
	  }
	  
	  public void process(FanOut r){
		  
		  //Löschen des Ergebnisses der vorherigen Prozessierung
		  this.resetReportText();
		  

		    /*
		     * In diesen Buffer schreiben wir während der Prozessierung sukzessive
		     * unsere Ergebnisse.
		     */
		  StringBuffer result = new StringBuffer();
		  
		  
		  /*
		     * Die einzelnen Bestandteile des Reports werden
		     * ausgelesen und in HTML-Form übersetzt.
		     */
		  	result.append("<H3>" + r.getTitel() + "</H3>");
		  	result.append("<table style=\"width:400px;border:1px solid silver\"><tr>");
		  	result.append("</tr><tr><td></td><td>" + r.getErstelldatum().toString()
		  	        + "</td></tr></table>");
		  	
		  	
		  	 Vector<Row> rows = r.getRows();
		     result.append("<table style=\"width:400px\">");
		     
		     for (int i = 0; i < rows.size(); i++) {
		         Row row = rows.elementAt(i);
		         result.append("<tr>");
		         for (int k = 0; k < row.getColumnsSize(); k++) {
		           if (i == 0) {
		             result.append("<td style=\"background:silver;font-weight:bold\">" + row.getColumnByIndex(k)
		                 + "</td>");
		           }
		           else {
		             if (i > 1) {
		               result.append("<td style=\"border-top:1px solid silver\">"
		                   + row.getColumnByIndex(k) + "</td>");
		             }
		             else {
		               result.append("<td valign=\"top\">" + row.getColumnByIndex(k) + "</td>");
		             }
		           }
		         }
		         result.append("</tr>");
		       }

		       result.append("</table>");
		       
		       /*
		        * Der Arbeits-Buffer wird am Ende in einen String umgewandelt und der
		        * reportText-Variable zugewiesen. Das
		        * Ergebnis kann dann mittels getReportText() ausgelesen werden.
		        */
		       this.reportText = result.toString();
	  }
	  
	  public void processSimpleReport(Report report){
		  
		  SimpleReport r = (SimpleReport)report;
		  
		  //Löschen des Ergebnisses der vorherigen Prozessierung
		  this.resetReportText();
		  

		    /*
		     * In diesen Buffer schreiben wir während der Prozessierung sukzessive
		     * unsere Ergebnisse.
		     */
		  StringBuffer result = new StringBuffer();
		  
		  
		  /*
		     * Die einzelnen Bestandteile des Reports werden
		     * ausgelesen und in HTML-Form übersetzt.
		     */
		  	result.append("<H1>" + r.getTitel() + "</H1>");
		  	result.append("<table style=\"width:400px;border:1px solid silver\"><tr>");
		  	result.append("</tr><tr><td></td><td>" + r.getErstelldatum().toString()
		  	        + "</td></tr></table>");
		  	
		  	
		  	 Vector<Row> rows = r.getRows();
		     result.append("<table style=\"width:400px\">");
		     
		     for (int i = 0; i < rows.size(); i++) {
		         Row row = rows.elementAt(i);
		         result.append("<tr>");
		         for (int k = 0; k < row.getColumnsSize(); k++) {
		           if (i == 0) {
		             result.append("<td style=\"background:silver;font-weight:bold\">" + row.getColumnByIndex(k)
		                 + "</td>");
		           }
		           else {
		             if (i > 1) {
		               result.append("<td style=\"border-top:1px solid silver\">"
		                   + row.getColumnByIndex(k) + "</td>");
		             }
		             else {
		               result.append("<td valign=\"top\">" + row.getColumnByIndex(k) + "</td>");
		             }
		           }
		         }
		         result.append("</tr>");
		       }

		       result.append("</table>");
		       
		       /*
		        * Der Arbeits-Buffer wird am Ende in einen String umgewandelt und der
		        * reportText-Variable zugewiesen. Das
		        * Ergebnis kann dann mittels getReportText() ausgelesen werden.
		        */
		       this.reportText = result.toString();
	  }
	  
//	  public void process1(AllBewerbungenByAusschreibungReport r){
//		    // Zunächst löschen wir das Ergebnis vorhergehender Prozessierungen.
//		    this.resetReportText();
//
//		    /*
//		     * In diesen Buffer schreiben wir während der Prozessierung sukzessive
//		     * unsere Ergebnisse.
//		     */
//		    StringBuffer result = new StringBuffer();
//
//		    /*
//		     * Nun werden Schritt für Schritt die einzelnen Bestandteile des Reports
//		     * ausgelesen und in HTML-Form übersetzt.
//		     */
//		    result.append("<H2>" + r.getTitel() + "</H2>");
//		    result.append("<table><tr>");
//		    result.append("</tr><tr><td></td><td>" + r.getErstelldatum().toString()
//		        + "</td></tr></table>");
//
//		    /*
//		     * Da AlleBewerbungenAufEigeneAusschreibungenReport ein CompositeReport ist, enthält r
//		     * eine Menge von Teil-Reports des Typs AlleBewerbungenAufEineAusschreibungDesUsers. Für
//		     * jeden dieser Teil-Reports rufen wir process
//		     * auf. Das Ergebnis des jew. Aufrufs fügen wir dem Buffer hinzu.
//		     */
//		    for (int i = 0; i < r.getSubReportsSize(); i++) {
//		      /*
//		       * AlleBewerbungenAufEineAusschreibungDesUsers wird als Typ der SubReports vorausgesetzt.
//		       * Sollte dies in einer erweiterten Form des Projekts nicht mehr gelten,
//		       * so müsste hier eine detailliertere Implementierung erfolgen.
//		       */
//		    	AllBewerbungenToOneAusschreibungReport subReport = (AllBewerbungenToOneAusschreibungReport) r
//		          .getSubReportByIndex(i);
//
//		      this.process(subReport);
//
//		      result.append(this.reportText + "\n");
//
//		      /*
//		       * Nach jeder Übersetzung eines Teilreports und anschließendem Auslesen
//		       * sollte die Ergebnisvariable zurückgesetzt werden.
//		       */
//		      this.resetReportText();
//		    }
//
//		    /*
//		     * Zum Schluss wird unser Arbeits-Buffer in einen String umgewandelt und der
//		     * reportText-Variable zugewiesen. Dadurch wird es möglich, anschließend das
//		     * Ergebnis mittels getReportText() auszulesen.
//		     */
//		    this.reportText = result.toString();
//		  }

}

