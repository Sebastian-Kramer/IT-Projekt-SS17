package de.hdm.ITProjekt.client.gui;
//
//import java.util.Date;
//import java.util.Vector;
//
//import com.google.gwt.cell.client.ClickableTextCell;
//import com.google.gwt.core.client.GWT;
//import com.google.gwt.event.dom.client.ClickEvent;
//import com.google.gwt.event.dom.client.ClickHandler;
//import com.google.gwt.user.cellview.client.CellTable;
//import com.google.gwt.user.cellview.client.Column;
//import com.google.gwt.user.client.Window;
//import com.google.gwt.user.client.rpc.AsyncCallback;
//import com.google.gwt.user.client.rpc.ServiceDefTarget;
//import com.google.gwt.user.client.ui.Button;
//import com.google.gwt.user.client.ui.HorizontalPanel;
//import com.google.gwt.user.client.ui.RootPanel;
//import com.google.gwt.user.client.ui.TextBox;
//import com.google.gwt.user.client.ui.VerticalPanel;
//import com.google.gwt.view.client.SingleSelectionModel;
//
//import de.hdm.ITProjekt.client.ClientsideSettings;
//import de.hdm.ITProjekt.client.Showcase;
//import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatz;
//import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;
//import de.hdm.ITProjekt.shared.bo.Ausschreibung;
//import de.hdm.ITProjekt.shared.bo.Projekt;
//import de.hdm.ITProjekt.shared.bo.Projektmarktplatz;
//
//public class StellenausschreibungenSeite extends Showcase {
//
//	private static ClickHandler currentClickHandler = null;
//	private static ClickEvent currentClickEvent = null;
//	
//	AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
//	
//	CellTable<Ausschreibung> ct_alleAusschreibungen = new CellTable<Ausschreibung> ();
//	CellTable<Ausschreibung> ct_eigeneAusschreibungen = new CellTable<Ausschreibung>();
//
//	private TextBox ausschreibungbox = new TextBox();
//	HorizontalPanel hpanel_ausschreibung = new HorizontalPanel();
//	VerticalPanel vpanel = new VerticalPanel();
//	
//	//Buttons erstellen
//	Button deleteausschreibung = new Button("Stellenausschreibung entfernen");
//	Button createausschreibung = new Button("Stellenausschreibung anlegen");
//	
//	// Erlaubt das anklicken in Tabellen
//	
//	final SingleSelectionModel <Ausschreibung> ssm = new SingleSelectionModel<Ausschreibung>();
//	final SingleSelectionModel <Ausschreibung> ssm_fremde = new SingleSelectionModel<Ausschreibung>();
//
//	
//
//
//	@Override
//	protected String getHeadlineText() {
//		return "<h1>Stellenausschreibungen</h1>";
//	}
//
//	@Override
//	protected void run() {
//		
//		//Größe des "div" Containers, sprich der Seite
//		RootPanel.get("Details").setWidth("100%");
//		// Größe der Tablle im div Container, sprich der Seite
//		ct_alleAusschreibungen.setWidth("100%", true);
//		// Größe der Tablle im div Container, sprich der Seite
//		ct_eigeneAusschreibungen.setWidth("100%", true);
//		
//		// Hinzufügen der Buttons und Textbox zum Panel
//		hpanel_ausschreibung.add(createausschreibung);
//		hpanel_ausschreibung.add(deleteausschreibung);
//		hpanel_ausschreibung.add(ausschreibungbox);
//
//				
//		// Hinzufügen der Tabelle ins VerticalPanel
//		vpanel.add(ct_alleAusschreibungen);
////		hpanel_projektmarktplatz.add(ct_alleProjektmarktplaetze);
//		vpanel.add(ct_eigeneAusschreibungen);
//						
//		// In die seite laden
//		this.add(hpanel_ausschreibung);
//		this.add(vpanel);
//		
//		//Stylen der Button
//		
//		ct_alleAusschreibungen.setSelectionModel(ssm);
//		ct_eigeneAusschreibungen.setSelectionModel(ssm_fremde);
//		
//		Column <Ausschreibung, String> Bezeichnung =
//				new Column<Ausschreibung, String>(new ClickableTextCell()) {
//			
//			@Override
//			public String getValue(Ausschreibung object) {
//
//				return object.getBezeichnung();
//				
//			}	    
//};
//
//Column<Ausschreibung, String> Datum = 
//new Column<Ausschreibung, String>(new ClickableTextCell())  {
//	    
//	@Override
//	public String getValue(Ausschreibung object) {
//				
//	return object.getDatum().toString();
//	}
//};	
//
//Column <Ausschreibung, String> Stellenbeschreibung =
//new Column<Ausschreibung, String>(new ClickableTextCell()) {
//
//@Override
//public String getValue(Ausschreibung object) {
//
//return object.getAusschreibungstext();
//
//}
//};
//
//ct_alleAusschreibungen.addDomHandler(new ClickHandler()
//{
//    @Override
//    public void onClick(ClickEvent event)
//   
//    {
//if(ssm != null){
//    Showcase showcase = new StellenausschreibungenSeite(ssm.getSelectedObject());    	
//    RootPanel.get("Details").clear();
//	RootPanel.get("Details").add(showcase);
//}
//else {
//	Window.alert("FEHLEEER");
//}
//    }
//   
//}, ClickEvent.getType());
//
//ct_alleAusschreibungen.addColumn(Bezeichnung, "Bezeichnung");
//ct_alleAusschreibungen.addColumn(Datum, "Datum");
//ct_alleAusschreibungen.addColumn(Stellenbeschreibung, "Stellenbezeichnung");
//
//filltable();	
//loschenAusschreibung();
//anlegenAusschreibung();
//	}
//
//	private void filltable() {
//
//		((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
//		 if (adminService == null) {
//	      adminService = GWT.create(AdministrationProjektmarktplatz.class);
//	    }
//		 
//		 AsyncCallback<Vector<Ausschreibung>> callback = new AsyncCallback<Vector<Ausschreibung>>(){
//
//		 	@Override
//	public void onFailure(Throwable caught) {
//
//		Window.alert("Fehler beim Laden der Daten aus der Datenbank");
//	}
//
//	@Override
//	public void onSuccess(Vector<Ausschreibung> result) {
//		ct_alleAusschreibungen.setRowData(0, result);
//		ct_alleAusschreibungen.setRowCount(result.size(), true);		
//	}
//		 };
//		adminService.getAll(callback);
//
//	}
//	
//	// Löschen aus der Datenbank und Tabelle
//
//
//	private void loschenAusschreibung() {
//		deleteausschreibung.addClickHandler(new ClickHandler(){
//
//			@Override
//			public void onClick(ClickEvent event) {
//
//				Ausschreibung selectedObject = ssm.getSelectedObject();
//				if (selectedObject != null){
//					((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
//					 if (adminService == null) {
//				      adminService = GWT.create(AdministrationProjektmarktplatz.class);
//				    }
//					 AsyncCallback<Ausschreibung> callback = new AsyncCallback<Ausschreibung>(){
//
//						@Override
//						public void onFailure(Throwable caught) {
//							// TODO Auto-generated method stub
//							Window.alert("Fehler beim Löschen");
//							
//						}
//
//						@Override
//						public void onSuccess(Ausschreibung result) {
//							// TODO Auto-generated method stub
//							Window.alert("Die Stellenausschreibung wurde erfolgreich gelöscht");
//							refreshlist();
//						}
//					 };
//					 
//					 adminService.deleteAusschreibung(selectedObject, callback);
//					 }
//			}
//		});
//		
//					 }
//						private void refreshlist() {
//
//							 ((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
//							 if (adminService == null) {
//						      adminService = GWT.create(AdministrationProjektmarktplatz.class);
//						    }
//							 AsyncCallback<Vector<Ausschreibung>> callback = new AsyncCallback<Vector<Ausschreibung>>(){
//
//								@Override
//								public void onFailure(Throwable caught) {
//									// TODO Auto-generated method stub
//									Window.alert("Fehler beim Laden der Daten aus der Datenbank");
//								}
//								@Override
//								public void onSuccess(Vector<Ausschreibung> result) {
//									// TODO Auto-generated method stub
//									ct_alleAusschreibungen.setRowData(0, result	);
//									ct_alleAusschreibungen.setRowCount(result.size(), true);
//									
//										
//									}
//								};
//							adminService.getAll(callback);
//							 
//						 }
//
//
//	private void anlegenAusschreibung() {
//
//		createausschreibung.addClickHandler(new ClickHandler() {
//
//			@Override
//			public void onClick(ClickEvent event) {
//				
//				 // Initialize the service proxy.
//				((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
//			    if (adminService == null) {
//			     
//			      adminService = GWT.create(AdministrationProjektmarktplatz.class);
//			    }
//			    
//			     // Set up the callback object.
//			    AsyncCallback<Ausschreibung> callback = new AsyncCallback<Ausschreibung>() {
//			   
//			      public void onFailure(Throwable caught) {
//			        // TODO: Do something with errors.
//			    	  Window.alert("onFailure");
//			    }
//				
//					@Override
//					public void onSuccess(Ausschreibung result) {
//						filltable();  		
//									}
//				
//			    };
//			    
//			     // Make the call to the stock price service.
//				   adminService.addAusschreibung(ausschreibungbox.getValue(), callback);
//			    
//	 }
//});
//}
//	
//	 
//}
//	
//
//
//	
//
//
