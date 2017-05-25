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
import com.google.gwt.user.client.rpc.AsyncCallback;
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
	  private ArrayList<String> stocks = new ArrayList<String>();
	
	public void onModuleLoad(){
		
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
	        public void onClick(ClickEvent event) {
	        	CreateProjektmarktplatz.createProjektmarktplatz();
	        }
	      });
	}
	

}
