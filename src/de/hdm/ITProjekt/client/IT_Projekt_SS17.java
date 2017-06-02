package de.hdm.ITProjekt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.ITProjekt.client.gui.Homeseite;
import de.hdm.ITProjekt.client.gui.ProjektmarktplatzSeite;

public class IT_Projekt_SS17 implements EntryPoint {
	
	 
	  private HorizontalPanel addPanel = new HorizontalPanel();
	  private VerticalPanel mainPanel = new VerticalPanel();
	  private Button projektmarktplatz = new Button("Projektmarktplatz");
	  /**
	   * Da diese Klasse die Implementierung des Interface <code>EntryPoint</code>
	   * zusichert, benÃ¶tigen wir eine Methode
	   * <code>public void onModuleLoad()</code>. Diese ist das GWT-Pendant der
	   * <code>main()</code>-Methode normaler Java-Applikationen.
	   */
	  @Override
	public void onModuleLoad() {
		  Homeseite startseite = new Homeseite();
		  addPanel.add(projektmarktplatz);
		  mainPanel.add(addPanel);
		  mainPanel.add(startseite);
		  RootPanel.get("Details").add(mainPanel);
		  
		
//	 
		  projektmarktplatz.addClickHandler(new ClickHandler() {
		      @Override
				public void onClick(ClickEvent event) {
		    	  Showcase showcase = new ProjektmarktplatzSeite();
		    	  
		    	  RootPanel.get("Details").clear();
		    	  RootPanel.get("Details").add(showcase);
			      }
			    });;
		  
		 
		  
//		    /*
//		     * Wir bereiten nun die Erstellung eines bescheidenen Navigators vor, der
//		     * einige Schaltflächen (Buttons) für die Ausführung von Unterprogrammen
//		     * enthalten soll.
//		     * 
//		     * Die jeweils ausgeführten Unterprogramme sind Demonstratoren
//		     * exemplarischer Anwendungsfälle des Systems. Auf eine professionelle
//		     * Gestaltung der Benutzungsschnittstelle wurde bewusst verzichtet, um den
//		     * Blick nicht von den wesentlichen Funktionen abzulenken. Eine
//		     * exemplarische GUI-Realisierung findet sich separat.
//		     * 
//		     * Die Demonstratoren werden nachfolgend als Showcase bezeichnet. Aus diesem
//		     * Grund existiert auch eine Basisklasse für sämtliche Showcase-Klassen
//		     * namens Showcase.
//		     */
//
//		    /*
//		     * Der Navigator ist als einspaltige Aneinanderreihung von Buttons
//		     * realisiert. Daher bietet sich ein VerticalPanel als Container an.
//		     */
//		    VerticalPanel navPanel = new VerticalPanel();
//
//		    /*
//		     * Das VerticalPanel wird einem DIV-Element namens "Navigator" in der
//		     * zugehörigen HTML-Datei zugewiesen und erhält so seinen Darstellungsort.
//		     */
//		    RootPanel.get("Navigator").add(navPanel);
//
//		    /*
//		     * Ab hier bauen wir sukzessive den Navigator mit seinen Buttons aus.
//		     */
//
//		    /*
//		     * Neues Button Widget erzeugen und eine Beschriftung festlegen.
//		     */
//		    final Button goToProjektmarktplatz = new Button("Projektmarktplatz");
//
//		    /*
//		     * Unter welchem Namen können wir den Button durch die CSS-Datei des
//		     * Projekts formatieren?
//		     */
//		     goToProjektmarktplatz.setStylePrimaryName("gotoprojektmarktplatz-menubutton");
//
//		    /*
//		     * Hinzufügen des Buttons zum VerticalPanel.
//		     */
//		    navPanel.add(goToProjektmarktplatz);
//		    
//		    goToProjektmarktplatz.addClickHandler(new ClickHandler() {
//
//				@Override
//				public void onClick(ClickEvent event) {
//					/*
//			         * Showcase instantiieren.
//			         */
//			        Showcase showcase = new ProjektmarktplatzSeite();
//
//			        /*
//			         * Für die Ausgaben haben wir ein separates DIV-Element namens "Details"
//			         * in die zugehörige HTML-Datei eingefügt. Bevor wir den neuen Showcase
//			         * dort einbetten, löschen wir vorsichtshalber sämtliche bisherigen
//			         * Elemente dieses DIV.
//			         */
//			        
//			        RootPanel.get("Details").clear();
//			        RootPanel.get("Details").add(showcase);
//				}
//			      
//		    });
//		    
//}
	  }
}
