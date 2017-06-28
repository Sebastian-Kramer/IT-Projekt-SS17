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
import de.hdm.ITProjekt.client.Menubar;
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
import de.hdm.ITProjekt.shared.bo.Team;
public class MeineBewerbungenSeite extends Showcase {
	
	private static ClickHandler currentClickHandler = null;
	private static ClickEvent currentClickEvent = null;
	
	AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
	
	CellTable<Bewerbung> ct_alleBewerbungen = new CellTable<Bewerbung>();
	CellTable<HybridAusschreibungBewerbung> ct_meineBewerbungen = new CellTable<HybridAusschreibungBewerbung>();
	CellTable<HybridAusschreibungBewerbung> ct_meineBewerbungenTeam = new CellTable<HybridAusschreibungBewerbung>();
	CellTable<HybridAusschreibungBewerbung> ct_meineBewerbungenUnternehmen = new CellTable<HybridAusschreibungBewerbung>();
	Vector<HybridAusschreibungBewerbung> meineBewerbungenPerson = new Vector<HybridAusschreibungBewerbung>();
	Vector<HybridAusschreibungBewerbung> meineBewerbungenTeam = new Vector<HybridAusschreibungBewerbung>();
	Vector<HybridAusschreibungBewerbung> meineBewerbungenUnternehmen = new Vector<HybridAusschreibungBewerbung>();
	
	
	
	HorizontalPanel hpanel_bewerbung = new HorizontalPanel();
	VerticalPanel vpanel = new VerticalPanel();
	
	final SingleSelectionModel<HybridAusschreibungBewerbung> ssm = new SingleSelectionModel<>();
	private Person person = null;
	private IdentitySelection is = null;
	private Menubar menubar = null;
	
	Button bewerbungAnzeigen_button = new Button ("Bewerbung anzeigen");
	Button bewerbungLoeschen_button	= new Button ("Bewerbung zur�ckziehen");
	
	public MeineBewerbungenSeite(IdentitySelection is, Menubar menubar){
		this.is = is;
		this.menubar = menubar;
	}
	

	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "<h1>Meine Bewerbungen</h1>";
	}

	@Override
	protected void run() {
		is.activateOrgUnits();
		RootPanel.get("Details").setWidth("100%");
		ct_meineBewerbungen.setWidth("100%");
		
		vpanel.add(ct_meineBewerbungen);
		
		hpanel_bewerbung.add(bewerbungAnzeigen_button);
		hpanel_bewerbung.add(bewerbungLoeschen_button);
		
		this.add(hpanel_bewerbung);
		this.add(vpanel);
		
		ct_meineBewerbungen.setSelectionModel(ssm);

		
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
		
		Column<HybridAusschreibungBewerbung, String> projektname =
				new Column<HybridAusschreibungBewerbung, String>(new ClickableTextCell()){

					@Override
					public String getValue(HybridAusschreibungBewerbung object) {
						
						return object.getProjektname();
					}
			
		
			
		};
		Column<HybridAusschreibungBewerbung, String> status =
				new Column<HybridAusschreibungBewerbung, String>(new ClickableTextCell()){

					@Override
					public String getValue(HybridAusschreibungBewerbung object) {
						
						return object.getBewerbungsstatus();
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
								Showcase showcase = new MeineBewerbungenSeite(is, menubar);
								RootPanel.get("Details").clear();
								RootPanel.get("Details").add(showcase);
								
							}
							
						});
					}
					
					
							}
				});
		

		
		if(is.getSelectedIdentityAsObject() instanceof Person){	
		ct_meineBewerbungen.addColumn(ausschreibungsbezeichnung, "Ausschreibung");
		ct_meineBewerbungen.addColumn(ausschreibender, "Ausschreibender");
		ct_meineBewerbungen.addColumn(erstellungsdatum, "eingereicht am");
		ct_meineBewerbungen.addColumn(ablauffrist, "Bewerbungsfrist");
		ct_meineBewerbungen.addColumn(projektname, "Projekt");
		ct_meineBewerbungen.addColumn(status, "Bewerbungsstatus");
		ct_meineBewerbungen.setRowCount(meineBewerbungenPerson.size(), true);
		ct_meineBewerbungen.setRowData(0, meineBewerbungenPerson);
		
		((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
		 if (adminService == null) {
	      adminService = GWT.create(AdministrationProjektmarktplatz.class);
	    }
		 adminService.findByOrgaID(is.getSelectedIdentityAsObject().getID(), new BewerbungAnzeigenCallback() );
		
		}else if(is.getSelectedIdentityAsObject() instanceof Team){	
			ct_meineBewerbungen.addColumn(ausschreibungsbezeichnung, "Ausschreibung");
			ct_meineBewerbungen.addColumn(ausschreibender, "Ausschreibender");
			ct_meineBewerbungen.addColumn(erstellungsdatum, "eingereicht am");
			ct_meineBewerbungen.addColumn(ablauffrist, "Bewerbungsfrist");
			ct_meineBewerbungen.addColumn(projektname, "Projekt");
			ct_meineBewerbungen.addColumn(status, "Bewerbungsstatus");
			ct_meineBewerbungen.setRowCount(meineBewerbungenTeam.size(), true);
			ct_meineBewerbungen.setRowData(0, meineBewerbungenTeam);
			
			((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
			 if (adminService == null) {
		      adminService = GWT.create(AdministrationProjektmarktplatz.class);
		    }
			 adminService.findByOrgaID(is.getSelectedIdentityAsObject().getID(), new BewerbungAnzeigenCallback() );
			
			}else if(is.getSelectedIdentityAsObject() instanceof Unternehmen){	
				ct_meineBewerbungen.addColumn(ausschreibungsbezeichnung, "Ausschreibung");
				ct_meineBewerbungen.addColumn(ausschreibender, "Ausschreibender");
				ct_meineBewerbungen.addColumn(erstellungsdatum, "eingereicht am");
				ct_meineBewerbungen.addColumn(ablauffrist, "Bewerbungsfrist");
				ct_meineBewerbungen.addColumn(projektname, "Projekt");
				ct_meineBewerbungen.addColumn(status, "Bewerbungsstatus");
				
				ct_meineBewerbungen.setRowCount(meineBewerbungenUnternehmen.size(), true);
				ct_meineBewerbungen.setRowData(0, meineBewerbungenUnternehmen);
				
				((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
				 if (adminService == null) {
			      adminService = GWT.create(AdministrationProjektmarktplatz.class);
			    }
				 adminService.findByOrgaID(is.getSelectedIdentityAsObject().getID(), new BewerbungAnzeigenCallback() );
				
				}
		
		
		
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
		ct_meineBewerbungen.setRowCount(meineBewerbungenPerson.size(), true);
		ct_meineBewerbungen.setRowData(0, meineBewerbungenPerson);
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
		private String bewerbungsstatus;
		public String ausschreibender;
		public Date ablauffrist;
		public String bewerbungstext;
		public String projektname;
		
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
		public String getProjektname() {
			return projektname;
		}
		public void setProjektname(String projektname) {
			this.projektname = projektname;
		}
		public String getBewerbungsstatus() {
			return bewerbungsstatus;
		}
		public void setBewerbungsstatus(String bewerbungsstatus) {
			this.bewerbungsstatus = bewerbungsstatus;
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
									
									
									
									@Override
									public void onFailure(Throwable caught) {
										Window.alert("Schritt 4 hat nicht geklappt");
										
										
									}

									@Override
									public void onSuccess(Person result) {
										final Person p = result;
										localHybrid.setAusschreibender(p.getAnrede() + " " + p.getName() + " " + p.getVorname() );
										meineBewerbungenPerson.add(localHybrid);
										ct_meineBewerbungen.setRowCount(meineBewerbungenPerson.size(), true);
										ct_meineBewerbungen.setRowData(0, meineBewerbungenPerson);
										
										meineBewerbungenTeam.add(localHybrid);
										meineBewerbungenUnternehmen.add(localHybrid);
										
										
									}
									
								});
								
								localHybrid.setBewerbungId(localBewerbung.getID());
								localHybrid.setErstellungsdatum(localBewerbung.getErstelldatum());
								localHybrid.setAusschreibungsbezeichung(a.getBezeichnung());
								localHybrid.setAblauffrist(a.getDatum());
								localHybrid.setBewerbungstext(localBewerbung.getBewerbungstext());
								localHybrid.setProjektname(result.getName());
								localHybrid.setBewerbungsstatus(localBewerbung.getStatus());
							
								
								
								
								
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
		
	
	
	
	


