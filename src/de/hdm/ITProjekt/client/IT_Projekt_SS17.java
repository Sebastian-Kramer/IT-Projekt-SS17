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
//	  private Button projektmarktplatz = new Button("Projektmarktplatz");
	  /**
	   * Da diese Klasse die Implementierung des Interface <code>EntryPoint</code>
	   * zusichert, benötigen wir eine Methode
	   * <code>public void onModuleLoad()</code>. Diese ist das GWT-Pendant der
	   * <code>main()</code>-Methode normaler Java-Applikationen.
	   */
	  @Override
	public void onModuleLoad() {
		  Homeseite startseite = new Homeseite();
		 
		  mainPanel.add(addPanel);
		  mainPanel.add(startseite);
		  RootPanel.get("Details").add(mainPanel);
		  RootPanel.get("Navigator").add(new Menubar());
		
		
//	 
		 
		  
		 
		  
//		    /*
//		     * Wir bereiten nun die Erstellung eines bescheidenen Navigators vor, der
//		     * einige Schaltfl�chen (Buttons) f�r die Ausf�hrung von Unterprogrammen
//		     * enthalten soll.
//		     * 
//		     * Die jeweils ausgef�hrten Unterprogramme sind Demonstratoren
//		     * exemplarischer Anwendungsf�lle des Systems. Auf eine professionelle
//		     * Gestaltung der Benutzungsschnittstelle wurde bewusst verzichtet, um den
//		     * Blick nicht von den wesentlichen Funktionen abzulenken. Eine
//		     * exemplarische GUI-Realisierung findet sich separat.
//		     * 
//		     * Die Demonstratoren werden nachfolgend als Showcase bezeichnet. Aus diesem
//		     * Grund existiert auch eine Basisklasse f�r s�mtliche Showcase-Klassen
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
//		     * zugeh�rigen HTML-Datei zugewiesen und erh�lt so seinen Darstellungsort.
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
//		     * Unter welchem Namen k�nnen wir den Button durch die CSS-Datei des
//		     * Projekts formatieren?
//		     */
//		     goToProjektmarktplatz.setStylePrimaryName("gotoprojektmarktplatz-menubutton");
//
//		    /*
//		     * Hinzuf�gen des Buttons zum VerticalPanel.
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
//			         * F�r die Ausgaben haben wir ein separates DIV-Element namens "Details"
//			         * in die zugeh�rige HTML-Datei eingef�gt. Bevor wir den neuen Showcase
//			         * dort einbetten, l�schen wir vorsichtshalber s�mtliche bisherigen
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
