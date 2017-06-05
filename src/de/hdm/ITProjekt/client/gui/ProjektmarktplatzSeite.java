package de.hdm.ITProjekt.client.gui;

import java.util.Vector;

import com.google.gwt.core.client.*;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatz;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;
import de.hdm.ITProjekt.shared.bo.Projektmarktplatz;
import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.client.Showcase;

public class ProjektmarktplatzSeite extends Showcase{

	AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
	
	CellTable<Projektmarktplatz> ct_Projektmarktplaetze = new CellTable<Projektmarktplatz>();
	CellTable<Projektmarktplatz> ct_alleProjektmarktplaetze = new CellTable<Projektmarktplatz>();
	
	
	private TextBox projektbox = new TextBox();
	HorizontalPanel hpanel_projektmarktplatz = new HorizontalPanel();
	VerticalPanel vpanel = new VerticalPanel();
	
	// Buttons NUR erstellen
	Button deleteprojektmarktplatz = new Button("Projektmarktplatz Löschen");
	Button createprojektmarktplatz = new Button("Projektmarktplatz Anlegen");

	// Erlaubt, dass in der Tabelle nur eins ausgewählt werden darf
	
	final SingleSelectionModel<Projektmarktplatz> ssm = new SingleSelectionModel<Projektmarktplatz>();
	SingleSelectionModel<Projektmarktplatz> ssm_fremde = new SingleSelectionModel<Projektmarktplatz>();
	
	@Override
	protected String getHeadlineText() {
		return "Projektmarktplatz Suche";
	}

	@Override
	protected void run() {
		//Größe des "div" Containers, sprich der Seite
		RootPanel.get("Details").setWidth("100%");
		// Größe der Tablle im div Container, sprich der Seite
		ct_Projektmarktplaetze.setWidth("100%", true);
		// Größe der Tablle im div Container, sprich der Seite
		ct_alleProjektmarktplaetze.setWidth("100%", true);
		
		
		// Hinzufügen der Buttons und Textbox zum Panel
		hpanel_projektmarktplatz.add(createprojektmarktplatz);
		hpanel_projektmarktplatz.add(deleteprojektmarktplatz);
		hpanel_projektmarktplatz.add(projektbox);
		
		// Hinzufügen der Tabelle ins VerticalPanel
		vpanel.add(ct_Projektmarktplaetze);
//		hpanel_projektmarktplatz.add(ct_Projektmarktplaetze);
		vpanel.add(ct_alleProjektmarktplaetze);
				
		// In die seite laden
		this.add(hpanel_projektmarktplatz);
		this.add(vpanel);
		
		
		//Stylen der Buttons 
//		createprojektmarktplatz.setStylePrimaryName("navi-button");
//		deleteprojektmarktplatz.setStylePrimaryName("navi-button");
		
		
		ct_Projektmarktplaetze.setSelectionModel(ssm);
		ct_alleProjektmarktplaetze.setSelectionModel(ssm_fremde);
		
		
		// Was soll in der Tabelle angezeigt werden?		
		TextColumn<Projektmarktplatz> ProjektmarktplatzTabelleSpaltenName = new TextColumn<Projektmarktplatz>() {
			@Override
			public String getValue(Projektmarktplatz object) {
				return object.getBez();
			}
		};
	
		
		// Wie soll die Spalte (Column) heißen?
		ct_Projektmarktplaetze.addColumn(ProjektmarktplatzTabelleSpaltenName, "Alle Projektmarktplätze");
		ct_alleProjektmarktplaetze.addColumn(ProjektmarktplatzTabelleSpaltenName, "Die eigenen Projektmarktplätze");
	
		filltable();	
		loschenProjektmarktplatz();
		anlegenProjektmarktplatz();
			}
	

		// Beim ersten Mal laden der Seite, die Daten aus der Datenbank lesen
	
	private void filltable(){
	
		((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
		 if (adminService == null) {
	      adminService = GWT.create(AdministrationProjektmarktplatz.class);
	    }
		 AsyncCallback<Vector<Projektmarktplatz>> callback = new AsyncCallback<Vector<Projektmarktplatz>>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				Window.alert("Fehler beim Laden der Daten aus der Datenbank");
			}
			@Override
			public void onSuccess(Vector<Projektmarktplatz> result) {
				// TODO Auto-generated method stub
				ct_Projektmarktplaetze.setRowData(0, result);
				ct_Projektmarktplaetze.setRowCount(result.size(), true);
				
					
				}
			};
		adminService.getProjektmarktplatzAll(callback);
	}
	
	// Löschen aus der Datenbank und Tabelle
	
	
	// Fehler beim Löschen von Projektmarktplatz!!!!... Der Fehler liegt bei selectedObcejt!
	
	 private void loschenProjektmarktplatz(){
		deleteprojektmarktplatz.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				// "selectedobject" sprich die angewählte Zeile in der Tabelle wird instanziiert
				Projektmarktplatz selectedObject = ssm.getSelectedObject();
				if (selectedObject != null){
					((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
					 if (adminService == null) {
				      adminService = GWT.create(AdministrationProjektmarktplatz.class);
				    }
					 AsyncCallback<Projektmarktplatz> callback = new AsyncCallback<Projektmarktplatz>(){

						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
							Window.alert("Fehler beim Löschen");
							
						}

						@Override
						public void onSuccess(Projektmarktplatz result) {
							// TODO Auto-generated method stub
							Window.alert("Der Projektmarktplatz wurde erfolgreich gelöscht");
							refreshlist();
						}
						};
						adminService.deleteProjektmarktplatz(selectedObject, callback);
				}
}
		});
	 }
	 
	 // Liste erneuern, der Trigger ist das Löschen eines Projektmarktplatzes
	 private void refreshlist(){
		 ((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
		 if (adminService == null) {
	      adminService = GWT.create(AdministrationProjektmarktplatz.class);
	    }
		 AsyncCallback<Vector<Projektmarktplatz>> callback = new AsyncCallback<Vector<Projektmarktplatz>>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				Window.alert("Fehler beim Laden der Daten aus der Datenbank");
			}
			@Override
			public void onSuccess(Vector<Projektmarktplatz> result) {
				// TODO Auto-generated method stub
				ct_Projektmarktplaetze.setRowData(0, result	);
				ct_Projektmarktplaetze.setRowCount(result.size(), true);
				
					
				}
			};
		adminService.getProjektmarktplatzAll(callback);
		 
	 }
	 
	 
	 private void anlegenProjektmarktplatz(){
		 		createprojektmarktplatz.addClickHandler(new ClickHandler(){

							 			
		 			@Override
					public void onClick(ClickEvent event) {
						// TODO Auto-generated method stub
						
					
			    // Initialize the service proxy.
				((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
			    if (adminService == null) {
			     
			      adminService = GWT.create(AdministrationProjektmarktplatz.class);
			    }
			    
			     // Set up the callback object.
			    AsyncCallback<Projektmarktplatz> callback = new AsyncCallback<Projektmarktplatz>() {
			   
			      public void onFailure(Throwable caught) {
			        // TODO: Do something with errors.
			    	  Window.alert("onFailure");
			    }
				
					@Override
					public void onSuccess(Projektmarktplatz result) {
						filltable();  		
									}
				
			    };
			    
			     // Make the call to the stock price service.
				   adminService.addProjektmarktplatz(projektbox.getValue(), callback);
			    
	 }
});
}
}
	

