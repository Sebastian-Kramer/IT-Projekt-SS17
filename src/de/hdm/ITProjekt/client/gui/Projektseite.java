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
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
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
import de.hdm.ITProjekt.shared.bo.Beteiligung;


public class Projektseite extends Showcase{

	AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
	
	private Button createStelle = new Button("Stellenausschreibung anlegen");
	private Button detailsButton = new Button("Stellendetails anzeigen");
	private Button alleBewerbungen = new Button("Bewerbungen anzeigen");
//	private Button showausschreibung = new Button("Stellenausschreibung anzeigen");
	private Button back = new Button("Zurück zum Projektmarktplatz");
	
	private Anchor zurstartseite = new Anchor("Startseite");
	private Anchor zuprojektmarktplaetze = new Anchor("/Projektmarktplätze");
	private Anchor zuprojekte = new Anchor("/Projekte");
	private Label projektverwaltung = new Label("/Projektverwaltung");
	private HorizontalPanel hpanelnavigator = new HorizontalPanel();  
	private FlexTable ft_navi = new FlexTable();
	
	VerticalPanel vp_projekt = new VerticalPanel();
	HorizontalPanel hp_projekt = new HorizontalPanel();
	VerticalPanel vp_projekt2 = new VerticalPanel();
	
	CellTable<Ausschreibung> ct_projektausschreibungen = new CellTable<Ausschreibung>();
	CellTable<Person> ct_teilnehmer = new CellTable<Person>();
	
	final SingleSelectionModel<Ausschreibung> ssm = new SingleSelectionModel<>();
	
	private Person person = new Person();
	private Projekt selectedProjekt = new Projekt();
	private Ausschreibung a1 = new Ausschreibung();
	private Projektmarktplatz projektmarktplatz = new Projektmarktplatz();
	
	public Projektseite(){
		
	}
	
	public Projektseite(Projekt selectedObject, Person person){
		this.selectedProjekt = selectedObject;
		this.person = person;
		
	}
	public Projektseite(Projekt selectedObject, Person person, Projektmarktplatz projektmarktplatz){
		this.selectedProjekt = selectedObject;
		this.person = person;
		this.projektmarktplatz = projektmarktplatz;
	}
	
	@Override
	protected String getHeadlineText() {
		return "<h2>" + selectedProjekt.getName() +  "</h2>";
	}

	@Override
	protected void run() {
		
		
		zurstartseite.setStylePrimaryName("navigationanchor");
		zuprojektmarktplaetze.setStylePrimaryName("navigationanchor");
		zuprojekte.setStylePrimaryName("navigationanchor");
		projektverwaltung.setStylePrimaryName("navigationanchor");
		ft_navi.setWidget(0, 0, zurstartseite);
		ft_navi.setWidget(0, 1, zuprojektmarktplaetze);
		ft_navi.setWidget(0, 2, zuprojekte);
		ft_navi.setWidget(0, 3, projektverwaltung);
		ft_navi.setCellPadding(10);
		hpanelnavigator.add(ft_navi);
		zurstartseite.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Showcase showcase = new Homeseite();
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
			}
		});
		zuprojektmarktplaetze.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Showcase showcase = new ProjektmarktplatzSeite(person);
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
			}
		});
		zuprojekte.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Showcase showcase = new Projekte(projektmarktplatz, person);
	        	RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
			}
		});
		
		RootPanel.get("Details").setWidth("100%");
		ct_projektausschreibungen.setWidth("100%");
		ct_teilnehmer.setWidth("100%");
		
		createStelle.setStylePrimaryName("myprofil-button");
		detailsButton.setStylePrimaryName("myprofil-button");
		alleBewerbungen.setStylePrimaryName("myprofil-button");
		back.setStylePrimaryName("myprofil-button");
		
		vp_projekt.add(ct_projektausschreibungen);
		this.add(hpanelnavigator);
		this.add(hp_projekt);
		this.append("<br><h3>Die offenen Stellen des Projekts</h3></br>");
		this.add(vp_projekt);
		
		vp_projekt2.add(ct_teilnehmer);
		this.append("<br><h3>Die Teilnehmer des Projekts</h3></br>");
		this.add(vp_projekt2);
		
		
		hp_projekt.add(detailsButton);
		if(person.getID()== selectedProjekt.getProjektleiter_ID()){
		hp_projekt.add(alleBewerbungen);
		hp_projekt.add(createStelle);
		}
		hp_projekt.add(back);
//		hp_projekt.add(showausschreibung);
		
		ct_projektausschreibungen.setSelectionModel(ssm);
		
		
			createStelle.addClickHandler(new ClickHandler(){

				@Override
				public void onClick(ClickEvent event) {
					if(person.getID() == selectedProjekt.getProjektleiter_ID()){
					DialogBoxAusschreibungAnlegen dialogBox = new DialogBoxAusschreibungAnlegen(selectedProjekt, person);
					dialogBox.center();
					
					}else{
						Window.alert("Nur der Projektleiter kann Stellenausschreibungen für dieses Projekt anlegen!");
					}
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
					Showcase showcase = new AlleBewerbungenFromAuschreibung(a1, person, selectedProjekt, projektmarktplatz);
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
	
	
	Column<Person, String> name =
			new Column<Person, String>(new ClickableTextCell()){

				@Override
				public String getValue(Person object) {
					
					return object.getVorname() + " " + object.getName();
				}
				
			};
			
			Column<Person, String> email = 
					new Column<Person, String>(new ClickableTextCell()){

						@Override
						public String getValue(Person object) {
							// TODO Auto-generated method stub
							return object.getEmail();
						}
				
			};
			
			ct_teilnehmer.addColumn(name, "Name");
			ct_teilnehmer.addColumn(email, "E-Mail");
	
	filltableauschreibungen();
	filltableteilnehmer();
	
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
//			Window.alert("Alle Auschreibungen für diese Projekt wurden geladen");
			
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

	
	private void filltableteilnehmer(){
		((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
		 if (adminService == null) {
	      adminService = GWT.create(AdministrationProjektmarktplatz.class);
	    }
		 adminService.getAllBeteiligungen(new AsyncCallback<Vector<Beteiligung>>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Vector<Beteiligung> result) {
				for(Beteiligung b : result){
					if(b.getProjekt_ID()==selectedProjekt.getID()){
						adminService.getPersonByID(b.getOrga_ID(), new AsyncCallback<Vector<Person>>(){

							@Override
							public void onFailure(Throwable caught) {
								Window.alert("das war nix");
								
							}

							@Override
							public void onSuccess(Vector<Person> result) {
//								Window.alert("funktioniert");
								ct_teilnehmer.setRowData(0, result);
								ct_teilnehmer.setRowCount(result.size(), true);
								
							}
							
						});
					}
				}
				
			}
			 
		 });
	}
}
