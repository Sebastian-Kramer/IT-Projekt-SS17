package de.hdm.ITProjekt.client.gui;

import java.util.Vector;

import com.google.gwt.cell.client.ClickableTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.*;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatz;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;
import de.hdm.ITProjekt.shared.bo.Projekt;
import de.hdm.ITProjekt.shared.bo.Projektmarktplatz;
import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.client.Showcase;

public class ProjektmarktplatzSeite extends Showcase{

	private static ClickHandler currentClickHandler = null;
	private static ClickEvent currentClickEvent = null;
		
	AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
	
	CellTable<Projektmarktplatz> ct_alleProjektmarktplaetze = new CellTable<Projektmarktplatz>();
	CellTable<Projektmarktplatz> ct_eigeneProjektmarktplaetze = new CellTable<Projektmarktplatz>();
	
	private Projektmarktplatz p1;
	
	private TextBox projektbox = new TextBox();
	HorizontalPanel hpanel_projektmarktplatz = new HorizontalPanel();
	VerticalPanel vpanel = new VerticalPanel();
	
	// Buttons NUR erstellen
	Button deleteprojektmarktplatz = new Button("Projektmarktplatz Löschen");
	Button createprojektmarktplatz = new Button("Projektmarktplatz Anlegen");

	// Erlaubt, dass in der Tabelle nur eins ausgewählt werden darf
	
//	private Projektmarktplatz p1 = new Projektmarktplatz();
	
	final SingleSelectionModel<Projektmarktplatz> ssm_projektmarktplatz_alle = new SingleSelectionModel<Projektmarktplatz>();
	final SingleSelectionModel<Projektmarktplatz> ssm_projektmarktplatz_eigene = new SingleSelectionModel<Projektmarktplatz>();
	
	@Override
	protected String getHeadlineText() {
		return "<h1>Projektmarktplatz Suche</h1>";
	}
	
	@Override
	protected void run() {
		//Größe des "div" Containers, sprich der Seite
		RootPanel.get("Details").setWidth("100%");
		// Größe der Tablle im div Container, sprich der Seite
		ct_alleProjektmarktplaetze.setWidth("100%", true);
		// Größe der Tablle im div Container, sprich der Seite
		ct_eigeneProjektmarktplaetze.setWidth("100%", true);

		
		// Hinzufügen der Buttons und Textbox zum Panel
		hpanel_projektmarktplatz.add(createprojektmarktplatz);
		hpanel_projektmarktplatz.add(deleteprojektmarktplatz);
		hpanel_projektmarktplatz.add(projektbox);
		
		// Hinzufügen der Tabelle ins VerticalPanel
		vpanel.add(ct_alleProjektmarktplaetze);
//		hpanel_projektmarktplatz.add(ct_alleProjektmarktplaetze);
		vpanel.add(ct_eigeneProjektmarktplaetze);
				
		// In die seite laden
		this.add(hpanel_projektmarktplatz);
		this.add(vpanel);
		
		
		//Stylen der Buttons 
//		createprojektmarktplatz.setStylePrimaryName("navi-button");
//		deleteprojektmarktplatz.setStylePrimaryName("navi-button");
		
		
		ct_alleProjektmarktplaetze.setSelectionModel(ssm_projektmarktplatz_alle);
		ssm_projektmarktplatz_alle.addSelectionChangeHandler(new Handler() {
			
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				// TODO Auto-generated method stub
				p1 = ssm_projektmarktplatz_alle.getSelectedObject();
				Showcase showcase = new Projekte(p1);
	        	RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
			}
		});
		ct_eigeneProjektmarktplaetze.setSelectionModel(ssm_projektmarktplatz_eigene);
		
		
		// Was soll in der Tabelle angezeigt werden?		
//		TextColumn<Projektmarktplatz> ProjektmarktplatzTabelleSpaltenName = new TextColumn<Projektmarktplatz>() {
//			@Override
//			public String getValue(Projektmarktplatz object) {
//				return object.getBez();
//			}
//		};
		 Column<Projektmarktplatz, String> linkColumn = 
				    new Column<Projektmarktplatz, String>(new ClickableTextCell())  {
				    
						@Override
						public String getValue(Projektmarktplatz object) {
							// TODO Auto-generated method stub
							
							return object.getBez();
						}
						    
		 };
		
		
		 
//		ct_alleProjektmarktplaetze.addDomHandler(new ClickHandler()
//		    {
//		        @Override
//		        public void onClick(ClickEvent event)
//		       
//		        {
//		    if(p1 != null){
//		    	
//		    	Window.alert("ssm noch bef�llt");
////		    		Projektmarktplatz p_objekt = ssm_projektmarktplatz_eigene.getSelectedObject();
//		        	Showcase showcase = new Projekte(p1);
//		        	RootPanel.get("Details").clear();
//					RootPanel.get("Details").add(showcase);
//					
//		    }
//		    else {
//		    	Window.alert("FEHLEEER");
//		    }
//		        }
//		       
//		    }, ClickEvent.getType());
			
		
		// Wie soll die Spalte (Column) heißen?
//		ct_alleProjektmarktplaetze.addColumn(ProjektmarktplatzTabelleSpaltenName, "Alle Projektmarktplätze");
//		ct_eigeneProjektmarktplaetze.addColumn(ProjektmarktplatzTabelleSpaltenName, "Die eigenen Projektmarktplätze");
		ct_alleProjektmarktplaetze.addColumn(linkColumn, "Bezeichnung");
		((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
		 if (adminService == null) {
	      adminService = GWT.create(AdministrationProjektmarktplatz.class);
	    }
		adminService.getProjektmarktplatzAll(new getProjektmarktplatzAusDB());
		
//		filltable();	
//		loschenProjektmarktplatz();
		anlegenProjektmarktplatz();
			}
	

		// Beim ersten Mal laden der Seite, die Daten aus der Datenbank lesen
	
//	private void filltable(){
//	
//		((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
//		 if (adminService == null) {
//	      adminService = GWT.create(AdministrationProjektmarktplatz.class);
//	    }
//		 AsyncCallback<Vector<Projektmarktplatz>> callback = new AsyncCallback<Vector<Projektmarktplatz>>(){
//
//			@Override
//			public void onFailure(Throwable caught) {
//				Window.alert("Fehler beim Laden der Daten aus der Datenbank");
//			}
//			@Override
//			public void onSuccess(Vector<Projektmarktplatz> result) {
//				ct_alleProjektmarktplaetze.setRowData(0, result);
//				ct_alleProjektmarktplaetze.setRowCount(result.size(), true);
//				
//					
//				}
//			};
//		adminService.getProjektmarktplatzAll(callback);
//	}
	
	// Löschen aus der Datenbank und Tabelle

//	
//	 private void loschenProjektmarktplatz(){
//		deleteprojektmarktplatz.addClickHandler(new ClickHandler(){
//
//			@Override
//			public void onClick(ClickEvent event) {
//				// TODO Auto-generated method stub
//				// "selectedobject" sprich die angewählte Zeile in der Tabelle wird instanziiert
//				Projektmarktplatz selectedObject = ssm.getSelectedObject();
//				if (selectedObject != null){
//					((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
//					 if (adminService == null) {
//				      adminService = GWT.create(AdministrationProjektmarktplatz.class);
//				    }
//					 AsyncCallback<Projektmarktplatz> callback = new AsyncCallback<Projektmarktplatz>(){
//
//						@Override
//						public void onFailure(Throwable caught) {
//							// TODO Auto-generated method stub
//							Window.alert("Fehler beim Löschen");
//							
//						}
//
//						@Override
//						public void onSuccess(Projektmarktplatz result) {
//							// TODO Auto-generated method stub
//							Window.alert("Der Projektmarktplatz wurde erfolgreich gelöscht");
//							refreshlist();
//						}
//						};
//						adminService.deleteProjektmarktplatz(selectedObject, callback);
//				}
//}
//		});
//	 }
//	 
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
				ct_alleProjektmarktplaetze.setRowData(0, result);
				ct_alleProjektmarktplaetze.setRowCount(result.size(), true);
				
					
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
//						filltable();  		
									}
				
			    };
			    
			     // Make the call to the stock price service.
				   adminService.addProjektmarktplatz(projektbox.getValue(), callback);
			    
	 }
});
}
	
	public class getProjektmarktplatzAusDB implements AsyncCallback<Vector<Projektmarktplatz>>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			Window.alert("Fehler beim Laden der Daten aus der Datenbank");
		}

		@Override
		public void onSuccess(Vector<Projektmarktplatz> result) {
			// TODO Auto-generated method stub	
			ct_alleProjektmarktplaetze.setRowData(0, result);
			ct_alleProjektmarktplaetze.setRowCount(result.size(), true);
			
		}
		
	}
//	private void filltable(){
//	
//		((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
//		 if (adminService == null) {
//	      adminService = GWT.create(AdministrationProjektmarktplatz.class);
//	    }
//		 AsyncCallback<Vector<Projektmarktplatz>> callback = new AsyncCallback<Vector<Projektmarktplatz>>(){
//
//			@Override
//			public void onFailure(Throwable caught) {
//				Window.alert("Fehler beim Laden der Daten aus der Datenbank");
//			}
//			@Override
//			public void onSuccess(Vector<Projektmarktplatz> result) {
//				ct_alleProjektmarktplaetze.setRowData(0, result);
//				ct_alleProjektmarktplaetze.setRowCount(result.size(), true);
//				
//					
//				}
//			};
//		adminService.getProjektmarktplatzAll(callback);
//	}
	 
}
	

