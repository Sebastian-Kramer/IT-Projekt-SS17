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

import java.util.ArrayList;

import org.mortbay.log.Log;

import com.google.gwt.core.client.*;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.*;

import de.hdm.ITProjekt.server.db.ProjektmarktplatzMapper;
//import de.hdm.ITProjekt.server.db.TestStart;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatz;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;
//import de.hdm.thies.bankProjekt.shared.ReportGeneratorAsync;
import de.hdm.ITProjekt.shared.bo.Projektmarktplatz;



/**
 * Entry-Point-Klasse des Projekts <b>BankProjekt</b>.
 */

public class IT_Projekt_SS17 implements EntryPoint {
	
	  private VerticalPanel mainPanel = new VerticalPanel();
	  private FlexTable projekttabelle = new FlexTable();
	  private HorizontalPanel addPanel = new HorizontalPanel();
	  private TextBox newSymbolTextBox = new TextBox();
	  private Button addProjektButton = new Button("Hinzufuegen");
	  private Label lastUpdatedLabel = new Label();
	  private ArrayList<String> projektmarktplaetze = new ArrayList<String>();
	  private AdministrationProjektmarktplatzAsync adminService = GWT.create(AdministrationProjektmarktplatz.class);
	  
	  
	public void onModuleLoad(){
		System.out.println("clickhandler");
		// "projekttabelle" mit einer ID versehen, um mit css aufrufen zu können	
		projekttabelle.getElement().setId("tabelle-projektmarktplatz");
		
		// Tabelle erstellen	
		projekttabelle.setText(0, 0, "ID");
	    projekttabelle.setText(0, 1, "Bezeichnung");
	    
	    // Tabelle in "mainPanel" platzieren
	    mainPanel.add(projekttabelle);	
	    // Tabelle in HTML referenzieren
	    RootPanel.get("projektListe").add(mainPanel);
	    
	    // Button in den addPanel platzieren
	    addPanel.add(newSymbolTextBox);
	    addPanel.add(addProjektButton);
	    
	    // HorizontalPanel (addPanel) in den mainPanel platzieren
	    mainPanel.add(addPanel);
	 
   
	  addProjektButton.addClickHandler(new ClickHandler() {
	    	@Override
	        public void onClick(ClickEvent event) {
	    		doStuff();
	        }
		      });
	 	    
	}
	
	private void doStuff() {
	    // Initialize the service proxy.
		((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
	    if (adminService == null) {
	     
	      adminService = GWT.create(AdministrationProjektmarktplatz.class);
	    }

	     // Set up the callback object.
	    AsyncCallback<Projektmarktplatz> callback = new AsyncCallback<Projektmarktplatz>() {
	   
	      public void onFailure(Throwable caught) {
	        // TODO: Do something with errors.
	      }

//		@Override
//		public void onSuccess(String result) {
//			// TODO Auto-generated method stub
//			
//		}

			@Override
			public void onSuccess(Projektmarktplatz result) {
				
			
			}
		
	    };
	    addtabellensatze();
	     // Make the call to the stock price service.
	    adminService.addProjektmarktplatz(newSymbolTextBox.getValue(), callback);
	}
	private void addtabellensatze() {
	      final String symbol = newSymbolTextBox.getText().toUpperCase().trim();
	      newSymbolTextBox.setFocus(true);

	      // Stock code must be between 1 and 10 chars that are numbers, letters, or dots.
	      // Stock code must be between 1 and 10 chars that are numbers, letters, or dots.
	      if (!symbol.matches("^[0-9A-Z\\.]{1,10}$")) {
	        Window.alert("'" + symbol + "' is not a valid symbol.");
	        newSymbolTextBox.selectAll();
	        return;
	      }
	      newSymbolTextBox.setText("");

	      // TODO Don't add the stock if it's already in the table.
	        if (projektmarktplaetze.contains(symbol))
	        return;
	      // TODO Add the stock to the table
	        int row = projekttabelle.getRowCount();
	        projektmarktplaetze.add(symbol);
	        projekttabelle.setText(row, 0, symbol);
	      // TODO Add a button to remove this stock from the table.
	        Button removeStockButton = new Button("x");
	        removeStockButton.addClickHandler(new ClickHandler() {
	          public void onClick(ClickEvent event) {
	            int removedIndex = projektmarktplaetze.indexOf(symbol);
	            projektmarktplaetze.remove(removedIndex);
	            projekttabelle.removeRow(removedIndex + 1);
	          }
	        });
	        projekttabelle.setWidget(row, 3, removeStockButton);
	      

	        
	        // Get the stock price.
	      
	    }
}
