package de.hdm.ITProjekt.client.gui;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.cell.client.ClickableTextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.client.Showcase;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatz;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;
import de.hdm.ITProjekt.shared.bo.Ausschreibung;
import de.hdm.ITProjekt.shared.bo.Bewerbung;
import de.hdm.ITProjekt.shared.bo.Organisationseinheit;
import de.hdm.ITProjekt.shared.bo.Person;
import de.hdm.ITProjekt.shared.bo.Projekt;
import de.hdm.ITProjekt.shared.bo.Projektmarktplatz;
import de.hdm.ITProjekt.shared.bo.Unternehmen;

public class Projektseite extends Showcase{

	AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
	
	private Button createStelle = new Button("Stellenausschreibung anlegen");
	private Button detailsButton = new Button("Stellendetails anzeigen");
	private Button alleBewerbungen = new Button("Bewerbungen anzeigen");
//	private Button showausschreibung = new Button("Stellenausschreibung anzeigen");
	private Button back = new Button("Zurück zum Projektmarktplatz");
	
	
	VerticalPanel vp_projekt = new VerticalPanel();
	HorizontalPanel hp_projekt = new HorizontalPanel();
	
	CellTable<Ausschreibung> ct_projektausschreibungen = new CellTable<Ausschreibung>();
	
	final SingleSelectionModel<Ausschreibung> ssm = new SingleSelectionModel<>();
	
	private Person person = new Person();
	private Projekt selectedProjekt;
	private Ausschreibung a1;
	
	public Projektseite(){
		
	}
	
	public Projektseite(Projekt selectedObject, Person person){
		this.selectedProjekt = selectedObject;
		this.person = person;
	}
	
	
	@Override
	protected String getHeadlineText() {
		return "<h2> Projektverwaltung </h2> ";
	}

	@Override
	protected void run() {
		
		
		RootPanel.get("Details").setWidth("100%");
		ct_projektausschreibungen.setWidth("100%");
		
		createStelle.setStylePrimaryName("myprofil-button");
		detailsButton.setStylePrimaryName("myprofil-button");
		alleBewerbungen.setStylePrimaryName("myprofil-button");
		back.setStylePrimaryName("myprofil-button");
		
		vp_projekt.add(ct_projektausschreibungen);
		this.add(hp_projekt);
		this.add(vp_projekt);
		hp_projekt.add(createStelle);
		hp_projekt.add(detailsButton);
		hp_projekt.add(alleBewerbungen);
		hp_projekt.add(back);
//		hp_projekt.add(showausschreibung);
		
		ct_projektausschreibungen.setSelectionModel(ssm);
		
		
			createStelle.addClickHandler(new ClickHandler(){

				@Override
				public void onClick(ClickEvent event) {
					DialogBoxAusschreibungAnlegen dialogBox = new DialogBoxAusschreibungAnlegen(selectedProjekt, person);
					dialogBox.center();
					
					
				}
				
			});
			
			detailsButton.addClickHandler(new ClickHandler(){

				@Override
				public void onClick(ClickEvent event) {
					DialogBoxDetails dialogBox = new DialogBoxDetails(selectedProjekt, person);
					dialogBox.center();
					
				}
							
			});
			back.addClickHandler(new ClickHandler(){

				@Override
				public void onClick(ClickEvent event) {
					Showcase showcase = new ProjektmarktplatzSeite(person);
					RootPanel.get("Details").clear();
					RootPanel.get("Details").add(showcase);
					
				}
				
			});
			alleBewerbungen.addClickHandler(new ClickHandler(){

				@Override
				public void onClick(ClickEvent event) {
					a1 = ssm.getSelectedObject();
					Showcase showcase = new AlleBewerbungenFromAuschreibung(a1, person);
					RootPanel.get("Details").clear();
					RootPanel.get("Details").add(showcase);
			
				}
				
			});
			
//			showausschreibung.addClickHandler(new ClickHandler(){
//
//				@Override
//				public void onClick(ClickEvent event) {
//					// TODO Auto-generated method stub
//					if(ssm !=null){
//						((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
//						 if (adminService == null) {
//					      adminService = GWT.create(AdministrationProjektmarktplatz.class);
//					    }
//						 adminService.getPersonbyID(person.getID(), new getPersonByID());
//						a1 = ssm.getSelectedObject();
//						Window.alert("Bis hier geht es");
//						DialogBoxAusschreibung dialogBox = new DialogBoxAusschreibung(a1, person);
//						Window.alert("Bis hier geht es");
//						int left = Window.getClientHeight() / 3;
//						int top = Window.getClientWidth() / 3;
//						dialogBox.setPopupPosition(left, top);
//						dialogBox.center();
//					}
//					else{
//						Window.alert("Bitte Ausschreibung ausw�hlen");
//					}
//				}
//				
//			});
			
		
		
		Column<Ausschreibung, String> bezeichnung =
					new Column<Ausschreibung, String>(new ClickableTextCell()){

						@Override
						public String getValue(Ausschreibung object) {
						
							return object.getBezeichnung();
						}
			
		};
		Column<Ausschreibung, String> ablauffrist =
				new Column<Ausschreibung, String>(new ClickableTextCell()){

					@Override
					public String getValue(Ausschreibung object) {
						// TODO Auto-generated method stub
						return object.getDatum().toString();
					}
		
		};
		
//		TextColumn<Ausschreibung> projektverantwortlicher = new TextColumn<Ausschreibung>(){
//
//			@Override
//			public String getValue(Ausschreibung object) {
//				// TODO Auto-generated method stub
//				return object.getAusschreibungstext();
//			}
//			
//		};
	
		
	
	ct_projektausschreibungen.addColumn(bezeichnung, "Stellenbezeichnung");
	ct_projektausschreibungen.addColumn(ablauffrist, "Ablauffrist");

	
	
	
	filltableauschreibungen();
//	refreshList();
	
	}

	
	private void filltableauschreibungen(){
		((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
		 if (adminService == null) {
	      adminService = GWT.create(AdministrationProjektmarktplatz.class);
	    }
//		 adminService.getAlLAuscchreibungenBy(selectedProjekt.getID(), new AllAuschreibungenByProjekt());
		 adminService.findByProjekt(selectedProjekt, new AllAuschreibungenByProjekt());
//		 AsyncCallback<Vector<Ausschreibung>> callback = new AsyncCallback<Vector<Ausschreibung>>(){
//
//			@Override
//			public void onFailure(Throwable caught) {
//				Window.alert("Das hat nicht geklappt");
//				
//			}
//
//			@Override
//			public void onSuccess(Vector<Ausschreibung> result) {
//				
//				if(result != null){
//					ct_projektausschreibungen.setRowData(0, result);
//					ct_projektausschreibungen.setRowCount(result.size(), true);
//				}else{
//					Window.alert("Keine Ausschreibungen");
//				}
//				
//				
//			}
//			
//		};
//		adminService.findByProjekt(selectedProjekt, callback);
		 
	}
//	private void refreshList(){
//		((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
//		 if (adminService == null) {
//	      adminService = GWT.create(AdministrationProjektmarktplatz.class);
//	    }
//		 AsyncCallback<Vector<Ausschreibung>> callback = new AsyncCallback<Vector<Ausschreibung>>(){
//
//			@Override
//			public void onFailure(Throwable caught) {
//				Window.alert("Das erneute Laden der Ausschreibungen hat nicht funktioniert");
//				
//			}
//
//			@Override
//			public void onSuccess(Vector<Ausschreibung> result) {
//				ct_projektausschreibungen.setRowData(0, result);
//				ct_projektausschreibungen.setRowCount(result.size(), true);
//				
//			}
//			 
//		 };
//		 adminService.findByProjekt(selectedProjekt, callback);
//	}
	
	
	public class AllAuschreibungenByProjekt implements AsyncCallback<Vector<Ausschreibung>>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Das hat nicht geklappt");
			
		}

		@Override
		public void onSuccess(Vector<Ausschreibung> result) {
			ct_projektausschreibungen.setRowData(0, result);
			ct_projektausschreibungen.setRowCount(result.size(), true);
			Window.alert("Alle Auschreibungen für diese Projekt wurden geladen");
			
		}
		
	}
//	private class getPersonByID implements AsyncCallback<Person>{
//
//		@Override
//		public void onFailure(Throwable caught) {
//			Window.alert("Fehler Person laden");
//			
//		}
//
//		@Override
//		public void onSuccess(Person result) {
//			result = person;
//			Window.alert("Person wurde gefunden");
//			
//		}
//		
//	}

}
