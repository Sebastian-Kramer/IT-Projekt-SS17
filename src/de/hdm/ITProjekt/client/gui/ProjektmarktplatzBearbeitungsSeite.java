package de.hdm.ITProjekt.client.gui;

import java.util.Vector;

import com.google.gwt.cell.client.ClickableTextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.client.Showcase;
import de.hdm.ITProjekt.client.gui.ProjektmarktplatzSeite.getProjektmarktplatzAusDB;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatz;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;
import de.hdm.ITProjekt.shared.bo.Projektmarktplatz;

public class ProjektmarktplatzBearbeitungsSeite extends Showcase{

	private static ClickHandler currentClickHandler = null;
	private static ClickEvent currentClickEvent = null;
		
	AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
	
	CellTable<Projektmarktplatz> ct_alleProjektmarktplaetze = new CellTable<Projektmarktplatz>();
	
	HorizontalPanel hpanel_projektmarktplatz = new HorizontalPanel();
	VerticalPanel vpanel = new VerticalPanel();
	HorizontalPanel hpanel_funktionen = new HorizontalPanel();
	final SingleSelectionModel<Projektmarktplatz> ssm_alleProjektmarktplaetze = new SingleSelectionModel<Projektmarktplatz>();
	
	private Projektmarktplatz selectedObject_alleProjektmarktplaetze;
	
	Button bearbeitungsmodus_aktiv = new Button("Bearbeitungsansicht");
	Button projektmarktplatz_anlegen = new Button("Hinzuf�gen");
	Button projektmarktplatz_loeschen = new Button("L�schen");
	Button projektmarktplatz_aendern = new Button("�ndern");
	
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
				// Hinzufügen der Buttons und Textbox zum Panel
				hpanel_projektmarktplatz.add(bearbeitungsmodus_aktiv);
				hpanel_funktionen.add(projektmarktplatz_anlegen);
				hpanel_funktionen.add(projektmarktplatz_loeschen);
				hpanel_funktionen.add(projektmarktplatz_aendern);
				// Hinzufügen der Tabelle ins VerticalPanel
				vpanel.add(ct_alleProjektmarktplaetze);
				
				// In die seite laden
				this.add(hpanel_projektmarktplatz);
				this.add(hpanel_funktionen);
				this.add(vpanel);
				
				//Stylen der Buttons 
				bearbeitungsmodus_aktiv.setStylePrimaryName("bearbungsmodusaktiv-button");
//				projektmarktplatz_anlegen.setStylePrimaryName(style);
//				projektmarktplatz_loeschen.setStylePrimaryName(style);
//				projektmarktplatz_aendern.setStylePrimaryName(style);
				// "selectionmodel" aussuchen single oder multi
				ct_alleProjektmarktplaetze.setSelectionModel(ssm_alleProjektmarktplaetze);
				
				// "Columns" bef�llen
				Column<Projektmarktplatz, String> linkColumn = 
						    new Column<Projektmarktplatz, String>(new ClickableTextCell())  {
						    
								@Override
								public String getValue(Projektmarktplatz object) {
									// TODO Auto-generated method stub
									
									return object.getBez();
								}	    
				 };
				 bearbeitungsmodus_aktiv.addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
						Showcase showcase = new ProjektmarktplatzSeite();
						RootPanel.get("Details").clear();
						RootPanel.get("Details").add(showcase);
					}
				});
				 projektmarktplatz_anlegen.addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
						DialogBox DialogBoxProjektmarktplatzSeite = new DialogBoxProjektmarktplatzSeiteAnlegen();
						RootPanel.get("Details").clear();
						RootPanel.get("Details").add(DialogBoxProjektmarktplatzSeite);
					}
				});
				 ct_alleProjektmarktplaetze.addColumn(linkColumn, "Bezeichnung");
				 ((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
				 if (adminService == null) {
			      adminService = GWT.create(AdministrationProjektmarktplatz.class);
			    }
				adminService.getProjektmarktplatzAll(new getProjektmarktplatzAusDB());
				loschenProjektmarktplatz();
				saveProjektmarktplatz();
	}
	
	
	private void saveProjektmarktplatz(){
		
		projektmarktplatz_aendern.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Projektmarktplatz selectedObject = ssm_alleProjektmarktplaetze.getSelectedObject();
				if (selectedObject != null){
					DialogBox DialogBoxProjektmarktplatzSeiteAendern = new DialogBoxProjektmarktplatzSeiteAendern(selectedObject);
					RootPanel.get("Details").clear();
					RootPanel.get("Details").add(DialogBoxProjektmarktplatzSeiteAendern);
				}
			}
		});
	}
	
	
	
		private void loschenProjektmarktplatz(){

		 projektmarktplatz_loeschen.addClickHandler(new ClickHandler(){
			 
				@Override
				public void onClick(ClickEvent event) {
					// "selectedobject" sprich die angewählte Zeile in der Tabelle wird instanziiert
					Projektmarktplatz selectedObject = ssm_alleProjektmarktplaetze.getSelectedObject();
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
		 
//				 projektmarktplatz_loeschen.addClickHandler(new ClickHandler(){
//	
//				@Override
//				public void onClick(ClickEvent event) {
//					// "selectedobject" sprich die angewählte Zeile in der Tabelle wird instanziiert
//					Projektmarktplatz selectedObject = ssm_alleProjektmarktplaetze.getSelectedObject();
//					if (selectedObject != null){
//						((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
//						 if (adminService == null) {
//					      adminService = GWT.create(AdministrationProjektmarktplatz.class);
//					    }
//						adminService.deleteProjektmarktplatz(selectedObject, new deleteProjektmarktplatzausDB());
//						
//					}
//					refreshlist();
//			}
	 private void refreshlist(){
		 ((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
		 if (adminService == null) {
	      adminService = GWT.create(AdministrationProjektmarktplatz.class);
	    }
		 adminService.getProjektmarktplatzAll(new getProjektmarktplatzAusDB());
		 
	 }
		 
//	 });
//	 }
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
	public class deleteProjektmarktplatzausDB implements AsyncCallback<Projektmarktplatz>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			Window.alert("Fehler beim Löschen");
			
		}

		@Override
		public void onSuccess(Projektmarktplatz result) {
			// TODO Auto-generated method stub
			Window.alert("Der Projektmarktplatz wurde erfolgreich gelöscht");
			
		}
		
	}
	
	
}
