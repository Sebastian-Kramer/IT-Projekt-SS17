package de.hdm.ITProjekt.client.gui;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.ClickableTextCell;
import com.google.gwt.cell.client.FieldUpdater;
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
import com.google.gwt.user.client.ui.ListBox;
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
import de.hdm.ITProjekt.shared.bo.Bewertung;
import de.hdm.ITProjekt.shared.bo.Organisationseinheit;
import de.hdm.ITProjekt.shared.bo.Person;
import de.hdm.ITProjekt.shared.bo.Projekt;
import de.hdm.ITProjekt.shared.bo.Projektmarktplatz;
import de.hdm.ITProjekt.shared.bo.Unternehmen;

public class AlleBewerbungenFromAuschreibung extends Showcase{
	
	AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
	
	private Button anzeigen = new Button("Bewerbung anzeigen");
	
	private Anchor zurstartseite = new Anchor("Startseite");
	private Anchor zuprojektmarktplaetze = new Anchor("/Projektmarktplätze");
	private Anchor zuprojekte = new Anchor("/Projekte");
	private Anchor projektverwaltung = new Anchor("/Projektverwaltung");
	private Label zuausschreibung = new Label("/Stellenausschreibungen");

	private VerticalPanel vp_bew = new VerticalPanel();
	private FlexTable ft_navi = new FlexTable();
	private HorizontalPanel hp_bew = new HorizontalPanel();
	private HorizontalPanel hp_bew1 = new HorizontalPanel();
	private HorizontalPanel hpanelnavigator = new HorizontalPanel();  
	
	private CellTable<Bewerbung> ct_bewerbungen = new CellTable<Bewerbung>();
	ButtonCell bewertung_abgeben = new ButtonCell();
	
	private FlexTable form = new FlexTable();
	
	final SingleSelectionModel<Bewerbung> ssm_bew = new SingleSelectionModel<>();
	
	private Projektmarktplatz pmp = new Projektmarktplatz();
	private Projekt pro = new Projekt();
	private Ausschreibung selectedAusschreibung;
	private Person angemeldetePerson;
	private Bewerbung b = null;
	private Vector<Bewertung> bewe;
	
	public AlleBewerbungenFromAuschreibung(Ausschreibung a, Person p){
		this.selectedAusschreibung = a;
		this.angemeldetePerson = p;
	}
	public AlleBewerbungenFromAuschreibung(Ausschreibung a, Person p, Projekt pr){
		this.selectedAusschreibung = a;
		this.angemeldetePerson = p;
		this.pro = pr;
	}
	public AlleBewerbungenFromAuschreibung(Ausschreibung a, Person p, Projekt pr, Projektmarktplatz pm){
		this.selectedAusschreibung = a;
		this.angemeldetePerson = p;
		this.pro = pr;
		this.pmp = pm;
	}

	@Override
	protected String getHeadlineText() {
		
		return "<h3> Dies sind alle abgegebenen Bewerbungen der ausgewählten Ausschreibung </h3>";
	}

	@Override
	protected void run() {
		
		RootPanel.get("Details").setWidth("100%");
		ct_bewerbungen.setWidth("100%");
		ct_bewerbungen.setSelectionModel(ssm_bew);
		
		
		zurstartseite.setStylePrimaryName("navigationanchor");
		zuprojektmarktplaetze.setStylePrimaryName("navigationanchor");
		zuprojekte.setStylePrimaryName("navigationanchor");
		projektverwaltung.setStylePrimaryName("navigationanchor");
		zuausschreibung.setStylePrimaryName("navigationanchor");
		
		anzeigen.setStylePrimaryName("myprofil-button");

		
		ft_navi.setWidget(0, 0, zurstartseite);
		ft_navi.setWidget(0, 1, zuprojektmarktplaetze);
		ft_navi.setWidget(0, 2, zuprojekte);
		ft_navi.setWidget(0, 3, projektverwaltung);
		ft_navi.setWidget(0, 4, zuausschreibung);
		ft_navi.setCellPadding(10);
		hpanelnavigator.add(ft_navi);
		vp_bew.add(ct_bewerbungen);
		this.add(vp_bew);
		this.add(hpanelnavigator);
		
		
		vp_bew.setSpacing(10);
		
		anzeigen.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				DialogBoxDetailsBewerbung dialogbox = new DialogBoxDetailsBewerbung(ssm_bew.getSelectedObject());
				dialogbox.center();
				
			}
			
		});
		
//		form.setWidget(0, 1, bewertung);
//		form.setWidget(0, 0, ct_bewerbungen);
//			
//		hp_bew.add(anzeigen);
//		hp_bew.add(zurueck);
//		hp_bew1.add(form);
//		this.add(hp_bew);
//		this.add(hp_bew1);
		
		
		
		if (selectedAusschreibung.getOrga_ID() == angemeldetePerson.getID()){
			Window.alert("Sie haben die Ausschreibung angelegt, können alle Bewerbungen einsehen "
					+ "und entsprechende Bewertungen abgeben");
			
			ssm_bew.addSelectionChangeHandler(new Handler() {
			
				@Override
				public void onSelectionChange(SelectionChangeEvent event) {
					
						hp_bew.add(anzeigen);		
						
				}
			});
			
			

			form.setWidget(0, 0, ct_bewerbungen);
			
			hp_bew1.add(form);
			this.add(vp_bew);
			this.add(hp_bew);
			this.add(hp_bew1);	
			vp_bew.add(ct_bewerbungen);
			
			
		}else{
			Window.alert("Leider kann nur der Stellenausschreibende die Bewerbungen sehen");
//			form.setWidget(0, 0, ct_bewerbungen);
//			hp_bew.add(zurueck);
//			hp_bew1.add(form);
//			this.add(hp_bew);
//			this.add(hp_bew1);
//			
//			Column<Bewerbung, String> text = 
//					new Column<Bewerbung, String>(new ClickableTextCell()){
//
//				@Override
//				public String getValue(Bewerbung object) {
//					// TODO Auto-generated method stub
//					return object.getBewerbungstext();
//				}
//			
//			};
//			Column<Bewerbung, String> erstelldatum = 
//					new Column<Bewerbung, String>(new ClickableTextCell()){
//
//				@Override
//				public String getValue(Bewerbung object) {
//					// TODO Auto-generated method stub
//					return object.getErstelldatum().toString();
//				}
//		
//			};
//			
//			ct_bewerbungen.addColumn(text, "Bewerbungstext"); 
//			ct_bewerbungen.addColumn(erstelldatum, "Einreichungsdatum");
//			
//			
//			
//			filltablebewerbung();
		}
		
//		bewerten.addClickHandler(new ClickHandler(){
//
//			@Override
//			public void onClick(ClickEvent event) {
//				
//				((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
//				 
//				if (adminService == null) {
//				 AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
//				 }
//				adminService.getAllBewertungen(new getAllBewertungen());
//					
//					b = ssm_bew.getSelectedObject();
//					
//					Boolean vorhanden = false;
//					for (Bewertung bewertung : bewe){
//								
//					
//					if(bewertung.getBewerbungs_ID() == b.getID()){
//						Window.alert("Es wurde bereits eine Bewertung abgegeben");
//						Window.alert(bewertung.toString());
//						vorhanden = false;
//						break;
//					}
//					else if(bewertung.getBewerbungs_ID() != b.getID()){
//						vorhanden = true;
//
//					}
//					}
//					if(vorhanden == true){					
//					DialogBoxBewertung dialogBox  = new DialogBoxBewertung(b, selectedAusschreibung, angemeldetePerson);
//					dialogBox.center();}
//
//				
//			}
//			
//			
//		});
		Column<Bewerbung, String> text = 
				new Column<Bewerbung, String>(new ClickableTextCell()){

			@Override
			public String getValue(Bewerbung object) {
				// TODO Auto-generated method stub
				return object.getBewerbungstext();
			}
		
		};
		Column<Bewerbung, String> erstelldatum = 
				new Column<Bewerbung, String>(new ClickableTextCell()){

			@Override
			public String getValue(Bewerbung object) {
				// TODO Auto-generated method stub
				return object.getErstelldatum().toString();
			}
	
		};
		
		Column<Bewerbung, String> buttoncell =
				new Column<Bewerbung, String>(bewertung_abgeben){

					@Override
					public String getValue(Bewerbung object) {
						// TODO Auto-generated method stub
						return "Bewerbung bewerten";
					}
			
		};
		
		
		
		buttoncell.setFieldUpdater(new FieldUpdater<Bewerbung,String>(){

			@Override
			public void update(int index, Bewerbung object, String value) {
				object = ssm_bew.getSelectedObject();
				((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
				 
				if (adminService == null) {
				 AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
				 }
				adminService.getAllBewertungen(new getAllBewertungen());

				
				Boolean vorhanden = false;
				for (Bewertung bewertung : bewe){
							
				
				if(bewertung.getBewerbungs_ID() == object.getID()){
					Window.alert("Es wurde bereits eine Bewertung abgegeben");
					Window.alert(bewertung.toString());
					vorhanden = false;
					break;
				}
				else if(bewertung.getBewerbungs_ID() != object.getID()){
					vorhanden = true;

				}
				}
				if(vorhanden == true){					
				DialogBoxBewertung dialogBox  = new DialogBoxBewertung(object, selectedAusschreibung, angemeldetePerson);
				dialogBox.center();}

			
				}	
				
			}
			
		);
		
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
				Showcase showcase = new ProjektmarktplatzSeite(angemeldetePerson);
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
			}
		});
		zuprojekte.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Showcase showcase = new Projekte(pmp, angemeldetePerson);
	        	RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
			}
		});
		projektverwaltung.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Showcase showcase = new Projektseite(pro, angemeldetePerson);
	        	RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
			}
		});
		
		ct_bewerbungen.addColumn(text, "Bewerbungstext"); 
		ct_bewerbungen.addColumn(erstelldatum, "Einreichungsdatum");
		ct_bewerbungen.addColumn(buttoncell, "");
		
		filltablebewerbung();
		
		
		
	}	
	private void filltablebewerbung(){
		((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
		 if (adminService == null) {
	      adminService = GWT.create(AdministrationProjektmarktplatz.class);
	    }

		 adminService.findBewerbungByAusschreibungId(selectedAusschreibung.getID(), new allBewByAus());

	}
	
	
	private class allBewByAus implements AsyncCallback<Vector<Bewerbung>>{
	
		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Da ist etwas schief gegangen");
			
		}
	
		@Override
		public void onSuccess(Vector<Bewerbung> result) {
			ct_bewerbungen.setRowData(0, result);
			ct_bewerbungen.setRowCount(result.size(), true);
	
			Window.alert("Alle Bewerbung auf diese Ausschreibung wurden geladen");
			
		}
		
	}
	
	public class getAllBewertungen implements AsyncCallback<Vector<Bewertung>>{
	
		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Da ist etwas schief gegangen");
			
		}
	
		@Override
		public void onSuccess(Vector<Bewertung> result) {
			bewe = result;	
			
		}
		
	}










}
