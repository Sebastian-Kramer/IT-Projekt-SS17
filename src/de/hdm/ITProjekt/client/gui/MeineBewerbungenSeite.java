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
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.client.Showcase;
import de.hdm.ITProjekt.client.gui.ProjektmarktplatzSeite.getProjektmarktplatzAusDB;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatz;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;
import de.hdm.ITProjekt.shared.bo.Ausschreibung;
import de.hdm.ITProjekt.shared.bo.Bewerbung;
import de.hdm.ITProjekt.shared.bo.Organisationseinheit;
import de.hdm.ITProjekt.shared.bo.Person;
import de.hdm.ITProjekt.shared.bo.Unternehmen;
import de.hdm.ITProjekt.shared.bo.Projekt;
public class MeineBewerbungenSeite extends Showcase {
	
	private static ClickHandler currentClickHandler = null;
	private static ClickEvent currentClickEvent = null;
	
	AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
	
	CellTable<Bewerbung> ct_alleBewerbungen = new CellTable<Bewerbung>();
	CellTable<HybridAusschreibungBewerbung> ct_meineBewerbungen = new CellTable<HybridAusschreibungBewerbung>();
	Vector<HybridAusschreibungBewerbung> meineBewerbungen = new Vector<HybridAusschreibungBewerbung>();
	
	
	
	
	HorizontalPanel hpanel_bewerbung = new HorizontalPanel();
	VerticalPanel vpanel = new VerticalPanel();
	
	final SingleSelectionModel<HybridAusschreibungBewerbung> ssm = new SingleSelectionModel<>();
	private Person person;
	
	Button bewerbungAnzeigen_button = new Button ("Bewerbung anzeigen");
	Button bewerbungLoeschen_button	= new Button ("Bewerbung zur�ckziehen");
	
	public MeineBewerbungenSeite(Person person){
		this.person = person;
	}
	

	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "Meine Bewerbungen";
	}

	@Override
	protected void run() {
		
		RootPanel.get("Details").setWidth("100%");
		ct_meineBewerbungen.setWidth("100%");
		
		vpanel.add(ct_meineBewerbungen);
		
		
		this.add(hpanel_bewerbung);
		this.add(vpanel);
		hpanel_bewerbung.add(bewerbungAnzeigen_button);
		hpanel_bewerbung.add(bewerbungLoeschen_button);
		
		ct_meineBewerbungen.setSelectionModel(ssm);
//		
//		Column<Bewerbung, String> erstelldatum =
//				new Column<Bewerbung, String>(new ClickableTextCell()){
//
//					@Override
//					public String getValue(Bewerbung object) {
//						
//						return object.getErstelldatum().toString();
//					}
//		};
		
		Column<HybridAusschreibungBewerbung, String> ausschreibungsbezeichnung =
				new Column<HybridAusschreibungBewerbung, String>(new ClickableTextCell()){

					@Override
					public String getValue(HybridAusschreibungBewerbung object) {
						
							
						return object.getAusschreibungsbezeichung(); 
						
					}
						
					
			
		};
		
		Column<HybridAusschreibungBewerbung, String> ausschreibender =
				new Column<HybridAusschreibungBewerbung, String>(new ClickableTextCell()){

					@Override
					public String getValue(HybridAusschreibungBewerbung object) {
						
						return object.getAusschreibender();
					}
			
		};
		
		Column<HybridAusschreibungBewerbung, String> erstellungsdatum =
				new Column<HybridAusschreibungBewerbung, String>(new ClickableTextCell()){

					@Override
					public String getValue(HybridAusschreibungBewerbung object) {
						// TODO Auto-generated method stub
						return object.getErstellungsdatum().toString();
					}
			
		};
		
		Column<HybridAusschreibungBewerbung, String> ablauffrist =
				new Column<HybridAusschreibungBewerbung, String>(new ClickableTextCell()){

					@Override
					public String getValue(HybridAusschreibungBewerbung object) {
						
						return object.getAblauffrist().toString();
								
					}
			
		};
		
		
		
		bewerbungAnzeigen_button.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				if(ssm != null){
				DialogBoxBewerbung dialogBox = new DialogBoxBewerbung(ssm.getSelectedObject().getBewerbungstext());
					int left = Window.getClientWidth() / 3;
					int top = Window.getClientHeight() / 3;
					dialogBox.setPopupPosition(left, top);
					dialogBox.center();
				}
			else{
//				if (ssm.getSelectedObject() == null){
					Window.alert("Bitte Bewerbung ausw�hlen");
//				}
				
			}
			}
		});	
		
			bewerbungLoeschen_button.addClickHandler(new ClickHandler(){

				@Override
			public void onClick(ClickEvent event) {
					if(ssm != null){
						Bewerbung ausgewaehlteBew = new Bewerbung();
						ausgewaehlteBew.setID(ssm.getSelectedObject().getBewerbungId());
						adminService.deleteBewerbung(ausgewaehlteBew, new AsyncCallback<Void>(){

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub
								Window.alert("L�schen fehlgeschlagen");
							}

							@Override
							public void onSuccess(Void result) {
								Window.alert("Bewerbung erfolgreich gel�scht");
								Showcase showcase = new MeineBewerbungenSeite(person);
								RootPanel.get("Details").clear();
								RootPanel.get("Details").add(showcase);
								
							}
							
						});
					}
					
					
							}
				});
		

		
	
		ct_meineBewerbungen.addColumn(ausschreibungsbezeichnung, "Ausschreibung");
		ct_meineBewerbungen.addColumn(ausschreibender, "Ausschreibender");
		ct_meineBewerbungen.addColumn(erstellungsdatum, "eingereicht am");
		ct_meineBewerbungen.addColumn(ablauffrist, "Frist endet am");
		ct_meineBewerbungen.setRowCount(meineBewerbungen.size(), true);
		ct_meineBewerbungen.setRowData(0, meineBewerbungen);
		
		((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
		 if (adminService == null) {
	      adminService = GWT.create(AdministrationProjektmarktplatz.class);
	    }
		adminService.findByPerson(person, new BewerbungAnzeigenCallback());
		
		
		
	}
	
	private void filltablebewerbungen(){
		
		((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
		 if (adminService == null) {
	      adminService = GWT.create(AdministrationProjektmarktplatz.class);
	    }		
		 
		 
		 AsyncCallback<Vector<Bewerbung>> callback = new AsyncCallback<Vector<Bewerbung>>(){

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Dumm gelaufen");
				
			}

			@Override
			public void onSuccess(Vector<Bewerbung> result) {
				ct_alleBewerbungen.setRowData(0, result);
				ct_alleBewerbungen.setRowCount(result.size(), true);
				
			}
			
		 };
		 adminService.findByPerson(person, callback);;
			 
		 }

	private void refreshList(){
		ct_meineBewerbungen.setRowCount(meineBewerbungen.size(), true);
		ct_meineBewerbungen.setRowData(0, meineBewerbungen);
		((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
		 if (adminService == null) {
	      adminService = GWT.create(AdministrationProjektmarktplatz.class);
	    }
		adminService.findByPerson(person, new BewerbungAnzeigenCallback());
//		((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
//		 if (adminService == null) {
//	      adminService = GWT.create(AdministrationProjektmarktplatz.class);
//	    }
//		 AsyncCallback<Vector<HybridAusschreibungBewerbung>> callback = new AsyncCallback<Vector <HybridAusschreibungBewerbung>>(){
//
//			@Override
//			public void onFailure(Throwable caught) {
//				Window.alert("Löschen hat nicht geklappt");
//				
//			}
//
//			@Override
//			public void onSuccess(Vector<HybridAusschreibungBewerbung> result) {
//				Window.alert("Ihre Bewerbung wird nun nicht mehr beruecksichtigt");
//				ct_meineBewerbungen.setRowData(0, result);
//				ct_meineBewerbungen.setRowCount(result.size(), true);
//				
//				
//				
//			}
//			
//			
//			 
//		 };
//		 adminService.findByPerson(person, callback);
	}	
	
	
	/*
	 * Innere Klasse mit Attributen die in CellTable angezeigt werden sollen wird angelegt
	 */
	
	private class HybridAusschreibungBewerbung{
		private int bewerbungId;
		private Date erstellungsdatum;
		private String ausschreibungsbezeichung;
		private String Team;
		private String Unternehmen;
//		private String bewerbungsstatus;
		public String ausschreibender;
		public Date ablauffrist;
		public String bewerbungstext;
		
		public int getBewerbungId() {
			return bewerbungId;
		}
		public void setBewerbungId(int bewerbungId) {
			this.bewerbungId = bewerbungId;
		}
		public Date getErstellungsdatum() {
			return erstellungsdatum;
		}
		public void setErstellungsdatum(Date erstellungsdatum) {
			this.erstellungsdatum = erstellungsdatum;
		}
		public String getAusschreibungsbezeichung() {
			return ausschreibungsbezeichung;
		}
		public void setAusschreibungsbezeichung(String ausschreibungsbezeichung) {
			this.ausschreibungsbezeichung = ausschreibungsbezeichung;
		}
		public String getTeam() {
			return Team;
		}
		public void setTeam(String team) {
			Team = team;
		}
		public String getUnternehmen() {
			return Unternehmen;
		}
		public void setUnternehmen(String unternehmen) {
			Unternehmen = unternehmen;
		}
		public String getAusschreibender() {
			return ausschreibender;
		}
		public void setAusschreibender(String ausschreibender) {
			this.ausschreibender = ausschreibender;
		}
		public Date getAblauffrist() {
			return ablauffrist;
		}
		public void setAblauffrist(Date ablauffrist) {
			this.ablauffrist = ablauffrist;
		}
		public String getBewerbungstext() {
			return bewerbungstext;
		}
		public void setBewerbungstext(String bewerbungstext) {
			this.bewerbungstext = bewerbungstext;
		}
		
		
		
	}
	
	/*
	 * Implementieren des Callbacks
	 */
	
	private class BewerbungAnzeigenCallback implements AsyncCallback<Vector<Bewerbung>>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Anzeigen der Bewerbung hat nicht funktioniert");
			
		}

		@Override
		public void onSuccess(Vector<Bewerbung> result) {
			
			for (int i=0;i<result.size();i++){
				final Bewerbung localBewerbung = result.get(i);
				final HybridAusschreibungBewerbung localHybrid = new HybridAusschreibungBewerbung();
				
				ClientsideSettings.getpmpVerwaltung().findByKey(result.get(i).getAusschreibungs_ID(), new AsyncCallback<Ausschreibung>(){

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Schritt 2 hat nicht geklappt");
						
					}

					@Override
					public void onSuccess(Ausschreibung result) {
						final Ausschreibung a = result;
						localHybrid.setAusschreibungsbezeichung(result.getBezeichnung());
						
						ClientsideSettings.getpmpVerwaltung().getProjektByID(result.getProjekt_ID(), new AsyncCallback<Projekt>(){

							@Override
							public void onFailure(Throwable caught) {
								Window.alert("Schritt 3 hat nicht geklappt");
								
							}

							@Override
							public void onSuccess(Projekt result) {
								ClientsideSettings.getpmpVerwaltung().getPersonbyID(a.getOrga_ID(), new AsyncCallback<Person>(){
									
									public Person ausschreibender;
									
									@Override
									public void onFailure(Throwable caught) {
										Window.alert("Schritt 4 hat nicht geklappt");
										
										
									}

									@Override
									public void onSuccess(Person result) {
										ausschreibender = result;
										meineBewerbungen.add(localHybrid);
										ct_meineBewerbungen.setRowCount(meineBewerbungen.size(), true);
										ct_meineBewerbungen.setRowData(0, meineBewerbungen);
										
									}
									
								});
								
								localHybrid.setBewerbungId(localBewerbung.getID());
								localHybrid.setErstellungsdatum(localBewerbung.getErstelldatum());
								localHybrid.setAusschreibungsbezeichung(a.getBezeichnung());
								localHybrid.setAusschreibender(result.getName());
								localHybrid.setAblauffrist(a.getDatum());
								localHybrid.setBewerbungstext(localBewerbung.getBewerbungstext());
								
								
								
							}
							
						}
					
								
								);
						
						
					}
					
				});
			
		}
			
		
	}
		
	
	
	
		
//	private void bewerbungLoeschen(){
//		bewerbungLoeschen_button.addClickHandler(new ClickHandler(){
//
//			@Override
//		public void onClick(ClickEvent event) {
//				if(ssm != null){
//					Bewerbung ausgewaehlteBew = new Bewerbung();
//					ausgewaehlteBew.setID(ssm.getSelectedObject().getID());
//					adminService.deleteBewerbung(ausgewaehlteBew, new AsyncCallback<Void>(){
//
//						@Override
//						public void onFailure(Throwable caught) {
//							// TODO Auto-generated method stub
//							Window.alert("L�schen fehlgeschlagen");
//						}
//
//						@Override
//						public void onSuccess(Void result) {
//							// TODO Auto-generated method stub
//							
//						}
//						
//					});
//				}
//				
//						}
//			});
//	
//		
//		
//		}
			
		}
}
		
	
	
	
	


