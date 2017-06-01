//package de.hdm.ITProjekt.client;
//
//import com.google.gwt.core.client.EntryPoint;
//import com.google.gwt.user.client.ui.Button;
//import com.google.gwt.user.client.ui.FlexTable;
//import com.google.gwt.user.client.ui.HorizontalPanel;
//import com.google.gwt.user.client.ui.Label;
//import com.google.gwt.user.client.ui.RootPanel;
//import com.google.gwt.user.client.ui.TextBox;
//import com.google.gwt.user.client.ui.VerticalPanel;
//
//public class IT_Projekt_SS17 implements EntryPoint {
//  private VerticalPanel mainPanel = new VerticalPanel();
//  private FlexTable projekttabelle = new FlexTable();
//  private HorizontalPanel addPanel = new HorizontalPanel();
//  private TextBox newSymbolTextBox = new TextBox();
//  private Button addStockButton = new Button("Erstellen");
//  private Label lastUpdatedLabel = new Label();
//
//  /**
//   * Entry point method.
//   */
//  public void onModuleLoad() {
//    // TODO Create table for stock data.
//	    projekttabelle.setText(0, 0, "Organisationseinheit");
//	    projekttabelle.setText(0, 1, "Berufserfahrung");
//	    projekttabelle.setText(0, 2, "Variable3");
//	    projekttabelle.setText(0, 3, "Variable4");
//	    
//    // TODO Assemble Add Stock panel.
//	    addPanel.add(newSymbolTextBox);
//	    addPanel.add(addStockButton);
//    // TODO Assemble Main panel.
//	    mainPanel.add(projekttabelle);
//	    mainPanel.add(addPanel);
//	    mainPanel.add(lastUpdatedLabel);
//    // TODO Associate the Main panel with the HTML host page.
//	    RootPanel.get("projektListe").add(mainPanel);
//    // TODO Move cursor focus to the input box.
//	    newSymbolTextBox.setFocus(true);
//	    
//  }
//}

package de.hdm.ITProjekt.client;

import java.awt.Menu;
import java.util.ArrayList;
import java.util.Vector;

import org.mortbay.log.Log;

import com.google.gwt.core.client.*;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.ITProjekt.server.AdministrationProjektmarktplatzImpl;
import de.hdm.ITProjekt.server.db.ProjektmarktplatzMapper;
//import de.hdm.ITProjekt.server.db.TestStart;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatz;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;
//import de.hdm.thies.bankProjekt.shared.ReportGeneratorAsync;
import de.hdm.ITProjekt.shared.bo.Projektmarktplatz;
import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.client.ProjektmarktplatzSeite;
import de.hdm.ITProjekt.client.Showcase;
//import de.hdm.thies.bankProjekt.client.BankProjekt.SetBankCallback;




/**
 * Entry-Point-Klasse des Projekts <b>BankProjekt</b>.
 */

public class IT_Projekt_SS17 implements EntryPoint {
	
	  private VerticalPanel mainPanel = new VerticalPanel();
//	  private FlexTable projekttabelle = new FlexTable(); // muss celltable werden!
	  private HorizontalPanel addPanel = new HorizontalPanel();
	  private TextBox newSymbolTextBox = new TextBox();
	  private Button addProjektButton = new Button("Hinzufuegen");
	  private Button removeStockButton = new Button("x");
	  private Label lastUpdatedLabel = new Label();
	  private ArrayList<String> projektmarktplaetze = new ArrayList<String>();
	  
	  private AdministrationProjektmarktplatzAsync adminService = GWT.create(AdministrationProjektmarktplatz.class);
	  
	
		
	  
	  AdministrationProjektmarktplatzAsync projektmarktplatzverwaltung = ClientsideSettings.getPmpAdmin();
	  
	  
	  /**
	   * Da diese Klasse die Implementierung des Interface <code>EntryPoint</code>
	   * zusichert, benötigen wir eine Methode
	   * <code>public void onModuleLoad()</code>. Diese ist das GWT-Pendant der
	   * <code>main()</code>-Methode normaler Java-Applikationen.
	   */
	  @Override
	public void onModuleLoad() {
	   
	    /*
	     * Wir bereiten nun die Erstellung eines bescheidenen Navigators vor, der
	     * einige Schaltflächen (Buttons) für die Ausführung von Unterprogrammen
	     * enthalten soll.
	     * 
	     * Die jeweils ausgeführten Unterprogramme sind Demonstratoren
	     * exemplarischer Anwendungsfälle des Systems. Auf eine professionelle
	     * Gestaltung der Benutzungsschnittstelle wurde bewusst verzichtet, um den
	     * Blick nicht von den wesentlichen Funktionen abzulenken. Eine
	     * exemplarische GUI-Realisierung findet sich separat.
	     * 
	     * Die Demonstratoren werden nachfolgend als Showcase bezeichnet. Aus diesem
	     * Grund existiert auch eine Basisklasse für sämtliche Showcase-Klassen
	     * namens Showcase.
	     */

	    /*
	     * Der Navigator ist als einspaltige Aneinanderreihung von Buttons
	     * realisiert. Daher bietet sich ein VerticalPanel als Container an.
	     */
	    VerticalPanel navPanel = new VerticalPanel();

	    /*
	     * Das VerticalPanel wird einem DIV-Element namens "Navigator" in der
	     * zugehörigen HTML-Datei zugewiesen und erhält so seinen Darstellungsort.
	     */
	    RootPanel.get("Navigator").add(navPanel);

	    /*
	     * Ab hier bauen wir sukzessive den Navigator mit seinen Buttons aus.
	     */

	    /*
	     * Neues Button Widget erzeugen und eine Beschriftung festlegen.
	     */
	    final Button goToProjektmarktplatz = new Button("Projektmarktplatz");

	    /*
	     * Unter welchem Namen können wir den Button durch die CSS-Datei des
	     * Projekts formatieren?
	     */
	     goToProjektmarktplatz.setStylePrimaryName("gotoprojektmarktplatz-menubutton");

	    /*
	     * Hinzufügen des Buttons zum VerticalPanel.
	     */
	    navPanel.add(goToProjektmarktplatz);

	    /*
	     * Natürlich benötigt der Button auch ein Verhalten, wenn man mit der Maus
	     * auf ihn klickt. Hierzu registrieren wir einen ClickHandler, dessen
	     * onClick()-Methode beim Mausklick auf den zugehörigen Button aufgerufen
	     * wird.
	     */
	    goToProjektmarktplatz.addClickHandler(new ClickHandler() {
	      @Override
		public void onClick(ClickEvent event) {
	        /*
	         * Showcase instantiieren.
	         */
	        Showcase showcase = new ProjektmarktplatzSeite();

	        /*
	         * Für die Ausgaben haben wir ein separates DIV-Element namens "Details"
	         * in die zugehörige HTML-Datei eingefügt. Bevor wir den neuen Showcase
	         * dort einbetten, löschen wir vorsichtshalber sämtliche bisherigen
	         * Elemente dieses DIV.
	         */
	        RootPanel.get("Details").clear();
	        RootPanel.get("Details").add(showcase);
	      }
	    });;

	  
	  
/* onModuleLoad Auskommentiert
	

	public void onModuleLoad(){
		
		// "projekttabelle" mit einer ID versehen, um mit css aufrufen zu k�nnen	
//		projekttabelle.getElement().setId("tabelle-projektmarktplatz");

	      	    
		// Gr��e des div Containers
		
//		RootPanel.get("Details").setWidth("50%");
		
		projekttabelle.setWidth("100%", true);
		
		// Spaltenname auslesen 
		
		TextColumn<Projektmarktplatz> nameColumn = new TextColumn<Projektmarktplatz>(){

			@Override
			public String getValue(Projektmarktplatz object) {
				// TODO Auto-generated method stub
				return object.getBez();
			}
			
		};
		
		// Der Spalte einen Namen geben
		projekttabelle.addColumn(nameColumn, "Bezeichnung");
		
		// Eine Spalte ausw�hlen
		
//		
//		projekttabelle.setSelectionModel(selectionModel);
//		final MultiSelectionModel<Projektmarktplatz> selectionModel = new MultiSelectionModel<Projektmarktplatz>();
		final SingleSelectionModel<Projektmarktplatz> selectionModel = new SingleSelectionModel<>();
		projekttabelle.setSelectionModel(selectionModel);
	
	
		
	    
	    // Button in den addPanel platzieren
	   
//	    addPanel.add(newSymbolTextBox);
//	    addPanel.add(addProjektButton);
	   
	    
	    
	    // HorizontalPanel (addPanel) in den mainPanel platzieren
//	    mainPanel.add(addPanel);
	    mainPanel.add(projekttabelle);
	    RootPanel.get("projektListe").add(mainPanel);

	    
//	       datenauslesen();
   
	  addProjektButton.addClickHandler(new ClickHandler() {
	    	@Override
	        public void onClick(ClickEvent event) {
//	    		doStuff();
	    		
	        }
		      });
	 	    
	}

	*/
	
	/*
	// Auslesen aus der Datenbank
	
	
	
	
	private void datenauslesen(){
		((ServiceDefTarget)projektmarktplatzverwaltung).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
		
		projektmarktplatzverwaltung.getProjektmarktplatzAll(new GetPersonCallback());
		
		
		//	    if (adminService == null) {
//	     
//	      adminService = GWT.create(AdministrationProjektmarktplatz.class);
//	    }
//		
//	    AsyncCallback<Vector<Projektmarktplatz>> callback = new AsyncCallback<Vector<Projektmarktplatz>>(){
//
//			@Override
//			public void onFailure(Throwable caught) {
//				// TODO Auto-generated method stub
//				Window.alert("Fehler beim Laden der Daten aus der Datenbank");
//			}
//
//			@Override
//			public void onSuccess(Vector<Projektmarktplatz> result) {
//				// TODO Auto-generated method stub
//				projekttabelle.setRowCount(result.size(), true);
//				projekttabelle.setRowData(0, result);
//				
//			}
//	    	
//	    };
//	    adminService.getProjektmarktplatzAll(callback);
	}
	
	private class GetPersonCallback implements AsyncCallback<Vector<Projektmarktplatz>>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			Window.alert("Fehler beim Laden der Daten aus der Datenbank");
			
		}

		@Override
		public void onSuccess(Vector <Projektmarktplatz> result) {
			// TODO Auto-generated method stub
			Window.alert("on Success");
						
			projekttabelle.setRowCount(result.size());
			projekttabelle.setRowData(0, result);
		}
		
	}
	
	
	//Methode zum Hinzuf�gen von Projektmarktplatz in Datenbank
//	private void doStuff() {
//	    // Initialize the service proxy.
//		((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
//	    if (adminService == null) {
//	     
//	      adminService = GWT.create(AdministrationProjektmarktplatz.class);
//	    }
//	    
//	     // Set up the callback object.
//	    AsyncCallback<Projektmarktplatz> callback = new AsyncCallback<Projektmarktplatz>() {
//	   
//	      public void onFailure(Throwable caught) {
//	        // TODO: Do something with errors.
//	    }
//
////		@Override
////		public void onSuccess(String result) {
////			// TODO Auto-generated method stub
////			
////		}
//
//			@Override
//			public void onSuccess(Projektmarktplatz result) {
//				
//				 
//	    		int row = projekttabelle.getRowCount();
//	    	  	 projekttabelle.setText(row, 0, result.getBez());
//	    	
//	    		
//							}
//		
//	    };
//	    
//	     // Make the call to the stock price service.
//	    adminService.addProjektmarktplatz(newSymbolTextBox.getText(), callback);
//	    
//	   
//		
//		 
//	    // Tabellenspalteeinf�gen
////	    addtabellensatze();
//	}
//	private void addtabellensatze() {
//	      final String symbol = newSymbolTextBox.getText().toUpperCase().trim();
//	      newSymbolTextBox.setFocus(true);
//
//	      // Stock code must be between 1 and 10 chars that are numbers, letters, or dots.
//	      // Stock code must be between 1 and 10 chars that are numbers, letters, or dots.
//	      if (!symbol.matches("^[0-9A-Z\\.]{1,10}$")) {
//	        Window.alert("'" + symbol + "' is not a valid symbol.");
//	        newSymbolTextBox.selectAll();
//	        return;
//	      }
////	      newSymbolTextBox.setText("");
////
////	      // TODO Don't add the stock if it's already in the table.
////	        if (projektmarktplaetze.contains(symbol))
////	        return;
////	      // TODO Add the stock to the table
////	        int row = projekttabelle.getRowCount();
////	        projektmarktplaetze.add(symbol);
////	        projekttabelle.setText(row, 0, symbol);
//	      // TODO Add a button to remove this stock from the table.
//	    
//	        removeStockButton.addClickHandler(new ClickHandler() {
//	          public void onClick(ClickEvent event) {
//	            int removedIndex = projektmarktplaetze.indexOf(symbol);
//	            projektmarktplaetze.remove(removedIndex);
//	            projekttabelle.removeRow(removedIndex + 1);
//	          }
//	        });
////	        projekttabelle.setWidget(row, 3, removeStockButton);
//	      	     
//		}
//	
//	/*
//	 * Auslesen aus der Datenbank
//	 */
//			private void tabellebefullen(){
//				final String symbol = newSymbolTextBox.getText().toUpperCase().trim();
//				
//				((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
//			    if (adminService == null) {
//			     
//			      adminService = GWT.create(AdministrationProjektmarktplatz.class);
//			    }
//
//				AsyncCallback<Vector<Projektmarktplatz>> callback = new AsyncCallback<Vector<Projektmarktplatz>>() {
//					   
//				      public void onFailure(Throwable caught) {
//				        // TODO: Do something with errors.
//				    	  Window.alert("Fehler beim Laden der Daten in die Tabelle");
//				      }
//
////					@Override
////					public void onSuccess(String result) {
////						// TODO Auto-generated method stub
////						
////					}
//
//				      
//				      
//				     @Override
//				        	 public void onSuccess(Vector<Projektmarktplatz> result) {
//					    	 // TODO Auto-generated method stub
//					    	 if ( result != null){
////					    		 for (Projektmarktplatz c : result){ 	 // Daraus nur eine foreach schleife machen!!!
//					    			 for (int i = 0 ; i <= result.size() ; i++){
//					    		int row = projekttabelle.getRowCount();
//					    	  	 projekttabelle.setText(row, 0, result.elementAt(i).getBez());
//					    	  	
//					    	  
//					    	  
//					    	  	 // Button hinzuf�gen
//					    	  	 projekttabelle.setWidget(row, 3, removeStockButton);
//					    	  	 
//					    	  	 
////						    	 }
//					    			 
//					    		 }
//					    	 }
//					    	
//					    	 
//					    	 }
//						
//					};
//				    	
//				adminService.getProjektmarktplatzAll(callback);
//			
//			}	
//
//			private void datensatzLoeschen(){
//				
//				// final String symbol = newSymbolTextBox.getText().toUpperCase().trim();
//				((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
//			    if (adminService == null) {
//			     
//			      adminService = GWT.create(AdministrationProjektmarktplatz.class);
//			    }
//			
//			    AsyncCallback<Projektmarktplatz> callback = new AsyncCallback<Projektmarktplatz>(){
//
//					@Override
//					public void onFailure(Throwable caught) {
//						// TODO Auto-generated method stub
//						
//					}
//
//					@Override
//					public void onSuccess(Projektmarktplatz result) {
//						// TODO Auto-generated method stub
//						
//					}
//			    	
//			    };
////			    adminService.deleteProjektmarktplatz(removeStockButton., callback);
//					
//			};
//			
//			
//			
//			
//			
//			
////			  private void refreshProjektList() {
////				    // Initialize the service proxy.
////				    if (adminService == null) {
////				      adminService = GWT.create(AdministrationProjektmarktplatz.class);
////				    }
////					AsyncCallback<Vector<Projektmarktplatz>> callback = new AsyncCallback<Vector<Projektmarktplatz>>() {
////						   
////					      public void onFailure(Throwable caught) {
////					        // TODO: Do something with errors.
////					    	  Window.alert("Fehler beim Laden der Daten in die Tabelle");
////					      }
////
//////						@Override
//////						public void onSuccess(String result) {
//////							// TODO Auto-generated method stub
//////							
//////						}
////
////					     @Override
////					        	 public void onSuccess(Vector<Projektmarktplatz> result) {
////						    	 // TODO Auto-generated method stub
////						    	 if ( result != null){
////						    		 
////						    		int row = projekttabelle.getRowCount();
////						    	  	 projekttabelle.setText(row, 0, newSymbolTextBox.getValue());
////						    		 
////						    	 }
////					     }
////							
////					};
////						 adminService.getProjektmarktplatzAll(callback);	//Das komplette vom callback machen!
////
////			  }

}
}
