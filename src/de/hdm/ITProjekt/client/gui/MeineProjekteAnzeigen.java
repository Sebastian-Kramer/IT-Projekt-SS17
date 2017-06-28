package de.hdm.ITProjekt.client.gui;

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
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;

import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.client.Showcase;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatz;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;
import de.hdm.ITProjekt.shared.bo.Beteiligung;
import de.hdm.ITProjekt.shared.bo.Person;
import de.hdm.ITProjekt.shared.bo.Projekt;
import de.hdm.ITProjekt.shared.bo.Team;
import de.hdm.ITProjekt.shared.bo.Unternehmen;

public class MeineProjekteAnzeigen extends Showcase{


	AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
	
	CellTable<Projekt> ct_meineProjekte = new CellTable<Projekt>();
	CellTable<Projekt> ct_verwProjekte = new CellTable<Projekt>();
	CellTable<Projekt> ct_meineProjekteTeam = new CellTable<Projekt>();
	CellTable<Projekt> ct_meineProjekteUnternehmen = new CellTable<Projekt>();

	
	HorizontalPanel hpanel_projekte = new HorizontalPanel();
	VerticalPanel vpanel_projekte = new VerticalPanel();
	VerticalPanel vpanel_projekte2 = new VerticalPanel();
	
	Button projektmarktplatz = new Button("Projekte Suchen");
	Button delete_projekt = new Button("Projekt Löschen");
	Button show_projekt = new Button("Projekt anzeigen");
	
	private final SingleSelectionModel<Projekt> ssm_meineprojekt = new SingleSelectionModel<Projekt>();
	
	private Projekt projekt;
	private Person person;
	private IdentitySelection is = null;
//	
//	public MeineProjekteAnzeigen(Person person) {
//		this.person = person;
//	}
	
	public MeineProjekteAnzeigen(IdentitySelection is){
		this.is = is;
	}

	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "<h2> Meine Projekte </h2>";
	}

	@Override
	protected void run() {

		
		RootPanel.get("Details").setWidth("100%");
		ct_meineProjekte.setWidth("100%", true);
		ct_meineProjekte.setSelectionModel(ssm_meineprojekt);
		ct_verwProjekte.setWidth("100%", true);
		ct_verwProjekte.setSelectionModel(ssm_meineprojekt);
		hpanel_projekte.add(delete_projekt);
		hpanel_projekte.add(projektmarktplatz);
		hpanel_projekte.add(show_projekt);
		this.add(hpanel_projekte);
		
		
//		if(is.getSelectedIdentityAsObject() instanceof Person){
//		this.append("<br><h3>Meine Projekte als Teilnehmer</h3></br>");
//		vpanel_projekte.add(ct_meineProjekte);
//		this.add(vpanel_projekte);
//		}else if(is.getSelectedIdentityAsObject() instanceof Unternehmen){
//			this.append("<br><h3>Meine Projekte als Teilnehmer</h3></br>");
//			vpanel_projekte.add(ct_meineProjekteUnternehmen);
//			this.add(vpanel_projekte);
//		}else if(is.getSelectedIdentityAsObject() instanceof Team){
//			this.append("<br><h3>Meine Projekte als Teilnehmer</h3></br>");
//			vpanel_projekte.add(ct_meineProjekteTeam);
//			this.add(vpanel_projekte);
//		}
		
		Column<Projekt, String> projektname = 
			    new Column<Projekt, String>(new ClickableTextCell())  {
			    
					@Override
					public String getValue(Projekt object) {
						// TODO Auto-generated method stub
						
						return object.getName();
					}
			    };
			    
			    
	 Column<Projekt, String> startdatum = 
			    new Column<Projekt, String>(new ClickableTextCell())  {
					    
					@Override
					public String getValue(Projekt object) {
					// TODO Auto-generated method stub
								
					return object.getStartdatum().toString();
					}
			    };		
			    
	  Column<Projekt, String> enddatum = 
			    new Column<Projekt, String>(new ClickableTextCell())  {
							    
		  			@Override
		  			public String getValue(Projekt object) {
		  			// TODO Auto-generated method stub
										
		  			return object.getEnddatum().toString();
		  			}
	  			};			 
	  Column<Projekt, String> beschreibung = 
			  new Column<Projekt, String>(new ClickableTextCell())  {
							    
		  			@Override
					public String getValue(Projekt object) {					
					return object.getBeschreibung();
					}
			    };
			    
			   TextColumn<Projekt> meineProjekte =
					   new TextColumn<Projekt>(){

						@Override
						public String getValue(Projekt object) {
							// TODO Auto-generated method stub
							return "Meine Projekte als Teilnehmer";
						}
				   
			   };
			   
			   if(is.getSelectedIdentityAsObject() instanceof Person){
					this.append("<br><h3>Meine Projekte als Teilnehmer</h3></br>");
					vpanel_projekte.add(ct_meineProjekte);
					this.add(vpanel_projekte);
					ct_meineProjekte.addColumn(projektname, "Projektname");		    
					ct_meineProjekte.addColumn(startdatum, "Startdatum");	
					ct_meineProjekte.addColumn(enddatum, "Enddatum");	
					ct_meineProjekte.addColumn(beschreibung, "Beschreibung");
					filltableMeineProjekte();
					ct_verwProjekte.addColumn(projektname, "Projektname");
					ct_verwProjekte.addColumn(startdatum, "Startdatum");
					ct_verwProjekte.addColumn(enddatum, "Enddatum");
					ct_verwProjekte.addColumn(beschreibung, "Beschreibung");
					filltableVerwProjekte();
					this.append("<br><h3>Meine Projekte als Projektleiter</h3></br>");
					vpanel_projekte2.add(ct_verwProjekte);
					this.add(vpanel_projekte2);
					
					}else if(is.getSelectedIdentityAsObject() instanceof Unternehmen){
						this.append("<br><h3>Meine Projekte als Teilnehmer</h3></br>");
						vpanel_projekte.add(ct_meineProjekteUnternehmen);
						this.add(vpanel_projekte);
						ct_meineProjekteUnternehmen.addColumn(projektname, "Projektname");
						ct_meineProjekteUnternehmen.addColumn(startdatum, "Startdatum");
						ct_meineProjekteUnternehmen.addColumn(enddatum, "Enddatum");
						ct_meineProjekteUnternehmen.addColumn(beschreibung,"Beschreibung");
						filltableMeineProjekteUnternehmen();
						
					}else if(is.getSelectedIdentityAsObject() instanceof Team){
						this.append("<br><h3>Meine Projekte als Teilnehmer</h3></br>");
						vpanel_projekte.add(ct_meineProjekteTeam);
						this.add(vpanel_projekte);
						ct_meineProjekteTeam.addColumn(projektname, "Projektname");
						ct_meineProjekteTeam.addColumn(startdatum, "Startdatum");
						ct_meineProjekteTeam.addColumn(enddatum, "Enddatum");
						ct_meineProjekteTeam.addColumn(beschreibung,"Beschreibung");
						filltableMeineProjekteTeam();
					}
		
		
//		if(is.getSelectedIdentityAsObject() instanceof Person){
//		this.append(" ");
//		this.append("<br><h3>Meine Projekte als Projektleiter</h3></br>");
//		vpanel_projekte2.add(ct_verwProjekte);
//		this.add(vpanel_projekte2);
//		
//		}
		
		projektmarktplatz.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Showcase showcase = new ProjektmarktplatzSeite(is);
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
			}
		});
		
//		delete_projekt.addClickHandler(new ClickHandler() {
//			
//			@Override
//			public void onClick(ClickEvent event) {
//				((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
//				 if (adminService == null) {
//			      adminService = GWT.create(AdministrationProjektmarktplatz.class);
//			    }
//				 adminService.deleteTeilnahme(person, ssm_meineprojekt.getSelectedObject().getProjektmarktplatz_ID(), new deleteTeilnehmerEinesProjekts());
//			}
//		});
		
		show_projekt.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				Showcase showcase = new Projektseite(ssm_meineprojekt.getSelectedObject(), is);
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
				
			}
			
		});
		
		ssm_meineprojekt.addSelectionChangeHandler(new Handler(){

			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				
			}
			
		});
		

		
		
		
	}
	
	private void filltableVerwProjekte(){
		((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
		 if (adminService == null) {
	      adminService = GWT.create(AdministrationProjektmarktplatz.class);
	    }
		 adminService.getAllProjekteByProjektleiter(is.getUser().getID(), new AsyncCallback<Vector<Projekt>>(){

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Anzeigen fehlgeschlagen");
				
			}

			@Override
			public void onSuccess(Vector<Projekt> result) {
				ct_verwProjekte.setRowData(0, result);
				ct_verwProjekte.setRowCount(result.size(), true);
				
			}
			 
		 });
		 
	}
	private void filltableMeineProjekte(){	
		((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
		 if (adminService == null) {
	      adminService = GWT.create(AdministrationProjektmarktplatz.class);
	    }
		 
		adminService.getAllBeteiligungen(new AsyncCallback<Vector<Beteiligung>>(){

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(Vector<Beteiligung> result) {
				for(Beteiligung b : result){
					if(is.getSelectedIdentityAsObject() instanceof Person){
					if(b.getOrga_ID()==is.getSelectedIdentityAsObject().getID()){
						adminService.getProjektByOrgaID(b.getProjekt_ID(), new AsyncCallback<Vector<Projekt>>(){

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub
								
							}

							@Override
							public void onSuccess(Vector<Projekt> result) {
								
								ct_meineProjekte.setRowData(0, result);
								ct_meineProjekte.setRowCount(result.size(), true);
								
							}
							
						});
							
						
							
					
					}
				}
				}	
			}
			
		});

	}
	
	private void filltableMeineProjekteUnternehmen(){
		((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
		 if (adminService == null) {
	      adminService = GWT.create(AdministrationProjektmarktplatz.class);
	    }
		 
		adminService.getAllBeteiligungen(new AsyncCallback<Vector<Beteiligung>>(){

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(Vector<Beteiligung> result) {
				for(Beteiligung b : result){
					
					if(b.getOrga_ID()==is.getSelectedIdentityAsObject().getID()){
						adminService.getProjektByOrgaID(b.getProjekt_ID(), new AsyncCallback<Vector<Projekt>>(){

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub
								
							}

							@Override
							public void onSuccess(Vector<Projekt> result) {
								
								ct_meineProjekteUnternehmen.setRowData(0, result);
								ct_meineProjekteUnternehmen.setRowCount(result.size(), true);
								
							}
							
						});
							
						
							
					
					}
				}
				
			}
			
		});
	}

	
	private void filltableMeineProjekteTeam(){	
		((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
		 if (adminService == null) {
	      adminService = GWT.create(AdministrationProjektmarktplatz.class);
	    }
		 
		adminService.getAllBeteiligungen(new AsyncCallback<Vector<Beteiligung>>(){

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(Vector<Beteiligung> result) {
				for(Beteiligung b : result){
					if(b.getOrga_ID()==is.getUser().getID()){
						adminService.getProjektByOrgaID(b.getProjekt_ID(), new AsyncCallback<Vector<Projekt>>(){

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub
								
							}

							@Override
							public void onSuccess(Vector<Projekt> result) {
								
								ct_meineProjekteTeam.setRowData(0, result);
								ct_meineProjekteTeam.setRowCount(result.size(), true);
								
							}
							
						});
							
						
							
					
					}
				}
				
			}
			
		});
	}
	
}
