package de.hdm.ITProjekt.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.client.Menubar;
import de.hdm.ITProjekt.client.Showcase;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatz;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;
import de.hdm.ITProjekt.shared.bo.Partnerprofil;
import de.hdm.ITProjekt.shared.bo.Person;
import de.hdm.ITProjekt.shared.bo.Team;
import de.hdm.ITProjekt.shared.bo.Unternehmen;

public class DialogBoxNavigationTeamUnternehmen extends DialogBox{

	private Person person = new Person();
	
	private Unternehmen unternehmen = new Unternehmen();
	
	private Person person_neu = new Person();
	
	IdentitySelection is = null;

	Partnerprofil p1 = new Partnerprofil();
	
	AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();	
	
	private VerticalPanel vpanel = new VerticalPanel();
	private HorizontalPanel hpanel = new HorizontalPanel();	

	private Button fertig = new Button("Fertig");
	
	private Anchor teamerstellen = new Anchor("Team Erstellen");
	private Anchor teamloeschen = new Anchor("Team Löschen");
	
	private Anchor unternehmenerstellen = new Anchor("Unternehmen Erstellen");
	private Anchor unternehmenloeschen = new Anchor("Unternehmen Löschen");
	
	private Team team = new Team();
	
	private FlexTable naviseite = new FlexTable();
	private FlexTable naviseite2 = new FlexTable();
	
	private Partnerprofil partnerprofil = new Partnerprofil();
	
	public DialogBoxNavigationTeamUnternehmen(final IdentitySelection is){
		
		this.is = is;
		this.setAnimationEnabled(false);
		this.setGlassEnabled(true);
		this.setText("Team");
		fertig.setStylePrimaryName("button");
		
		if(is.getUser().getTeam_ID() != null){
		teamerstellen.setVisible(false);
		}
		if(is.getUser().getUN_ID() != null){
		unternehmenerstellen.setVisible(false);
		}
		if(is.getUser().getTeam_ID() == null){
		teamloeschen.setVisible(false);
		}
		if(is.getUser().getUN_ID() == null){
		unternehmenloeschen.setVisible(false);
		}
		
		teamerstellen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if (is.getUser().getUN_ID() != null ){					
					DialogBox dialogbox = new DialogBoxTeam(is);
					dialogbox.center();
					hide();
				}
				else{
					Window.alert("Erstellen Sie bitte zu erst ein Unternehmen");
				}
				
				}
			});
		
		teamloeschen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if (is.getUser().getTeam_ID() == null){
				Window.alert("Sie haben kein Team");
			}else{
				DialogBox dialogbox = new DialogBoxTeamLoeschen(is);
				dialogbox.center();
				hide();
				
			}
			}
		});
		
		unternehmenerstellen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if (is.getUser().getUN_ID() != null){
					Window.alert("Bearbeiten Sie bitte Ihr bestehendes Unternehmen");
				}else{
				DialogBox dialogbox = new DialogBoxUnternehmen(is);
				dialogbox.center();
				hide();
				}
				
			}
		});
		
		unternehmenloeschen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if (is.getUser().getUN_ID() == null){
					Window.alert("Sie haben kein Unternehmen");
				}else{
				DialogBox dialogbox = new DialogBoxUnternehmenLoeschen(is);
				dialogbox.center();
				hide();
				}
			}
		});
		
		fertig.addClickHandler(new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {
				hide();
			}
		});
		naviseite.setWidget(1, 0, teamerstellen);
		naviseite.setWidget(1, 1, teamloeschen);
		naviseite2.setWidget(1, 0, unternehmenerstellen);
		naviseite2.setWidget(1, 1, unternehmenloeschen);
		hpanel.add(naviseite2);
		hpanel.add(naviseite);
		vpanel.setPixelSize(150, 80);
		vpanel.add(hpanel);
		vpanel.add(fertig);
		this.add(vpanel);
	}
	private class DialogBoxTeamLoeschen extends DialogBox{
		private IdentitySelection is = null;
		
		private VerticalPanel vpanel = new VerticalPanel();
		private HorizontalPanel hpanel = new HorizontalPanel();	

		private Button fertig = new Button("Fertig");
		
		private Label frage = new Label ("Möchten Sie Ihr Team löschen? Dies kann nicht rückgängig gemacht werden.");
		private Button ja = new Button("Ja");
		private Button nein = new Button("Nein");
		
		AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
		
		private FlexTable teamloschenseite = new FlexTable();
		public DialogBoxTeamLoeschen(final IdentitySelection is) {
			
			ja.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					
					 team.setID(is.getUser().getTeam_ID());
					 if (team.getID() != 0){
					 is.getUser().setTeam_ID(0);
					 }
					 
					 ((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
					 if (adminService == null) {
				     adminService = GWT.create(AdministrationProjektmarktplatz.class);
				   }
					 
					 adminService.getPartnerprofilOfOrganisationseinheit(team, new AsyncCallback<Partnerprofil>(){

						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void onSuccess(Partnerprofil result) {
							result = partnerprofil; 
							team.setPartnerprofil_ID(partnerprofil.getID());
							adminService.updatePerson(is.getUser(), new AsyncCallback<Person>(){

								@Override
								public void onFailure(Throwable caught) {
									// TODO Auto-generated method stub
									
								}

								@Override
								public void onSuccess(Person result) {
									((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
									 if (adminService == null) {
								     adminService = GWT.create(AdministrationProjektmarktplatz.class);
								   }
									 adminService.deleteTeam(team, new AsyncCallback<Void>(){

										@Override
										public void onFailure(Throwable caught) {
											// TODO Auto-generated method stub
											
										}

										@Override
										public void onSuccess(Void result) {
											adminService.deletePartnerprofil(partnerprofil, new AsyncCallback<Void>(){

												@Override
												public void onFailure(Throwable caught) {
													// TODO Auto-generated method stub
													
												}

												@Override
												public void onSuccess(Void result) {
													hide();
												 	RootPanel.get("idendity").clear();
													RootPanel.get("Navigator").clear();
													RootPanel.get("Details").clear();
													Showcase showcase = new MeinProfilAnzeigen(is);
													Menubar mb = new Menubar(is.getUser());
													RootPanel.get("Details").add(showcase);
													RootPanel.get("idendity").add(new IdentitySelection(is.getUser(), mb));
													RootPanel.get("Navigator").add(mb);
												}
												
											});
										}
										 
									 });
								}
								 
							 });
							 
							
						}
						 
					 });
					 
					
					 
					
					

				}
				});
			nein.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					hide();
					Showcase showcase = new MeinProfilAnzeigen(is);
					RootPanel.get("Details").clear();
					RootPanel.get("Details").add(showcase);
					
				}
			});
			
			teamloschenseite.setWidget(0, 0, frage);
			teamloschenseite.setWidget(1, 0, ja);
			teamloschenseite.setWidget(1, 1, nein);
			vpanel.add(teamloschenseite);
			this.add(vpanel);
			
		}
	}
	private class DialogBoxUnternehmenLoeschen extends DialogBox {
		
		private VerticalPanel vpanel = new VerticalPanel();
		private HorizontalPanel hpanel = new HorizontalPanel();	
		
		private IdentitySelection is = null;

		private Button fertig = new Button("Fertig");
		
		private Label frage = new Label ("Mit der Löschung Ihres Unternehmens, wird auch Ihr Team gelöscht. Dies kann nicht rückgängig gemacht werden. Trotzdem löschen?");
		private Button ja = new Button("Ja");
		private Button nein = new Button("Nein");
		
		private Partnerprofil partnerprofil = null;
		Partnerprofil p1 = new Partnerprofil();
		AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
		
		private FlexTable unternehmenloschenseite = new FlexTable();
		public DialogBoxUnternehmenLoeschen(final IdentitySelection is) {
			
			ja.addClickHandler(new ClickHandler() {
				
				
				@Override
				public void onClick(ClickEvent event) {
					unternehmen.setID(is.getUser().getUN_ID());
					if (is.getUser().getTeam_ID() == null){
						is.getUser().setUN_ID(0);
					 
					 ((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
					 if (adminService == null) {
				     adminService = GWT.create(AdministrationProjektmarktplatz.class);
				   }
					 adminService.getPartnerprofilOfOrganisationseinheit(is.getSelectedIdentityAsObject(), new AsyncCallback<Partnerprofil>(){

						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void onSuccess(Partnerprofil result) {
							unternehmen.setPartnerprofil_ID(result.getID());
							((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
							 if (adminService == null) {
						     adminService = GWT.create(AdministrationProjektmarktplatz.class);
						   }
							adminService.updatePerson(is.getUser(), new AsyncCallback<Person>(){

								@Override
								public void onFailure(Throwable caught) {
									// TODO Auto-generated method stub
									
								}

								@Override
								public void onSuccess(Person result) {
									final Partnerprofil p = new Partnerprofil();
									p.setID(is.getSelectedIdentityAsObject().getPartnerprofil_ID());
									((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
									 if (adminService == null) {
								     adminService = GWT.create(AdministrationProjektmarktplatz.class);
									 }
									 adminService.deleteUnternehmenByID(is.getSelectedIdentityAsObject().getID(), new AsyncCallback<Void>(){

										@Override
										public void onFailure(Throwable caught) {
											// TODO Auto-generated method stub
											
										}

										@Override
										public void onSuccess(Void result) {
											((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
											 if (adminService == null) {
										     adminService = GWT.create(AdministrationProjektmarktplatz.class);
										   }
											 
											 adminService.deletePartnerprofil(p, new AsyncCallback<Void>(){

												@Override
												public void onFailure(Throwable caught) {
													// TODO Auto-generated method stub
													
												}

												@Override
												public void onSuccess(Void result) {
													hide();
												 	RootPanel.get("idendity").clear();
													RootPanel.get("Navigator").clear();
													RootPanel.get("Details").clear();
													Showcase showcase = new MeinProfilAnzeigen(is);
													Menubar mb = new Menubar(is.getUser());
													RootPanel.get("Details").add(showcase);
													RootPanel.get("idendity").add(new IdentitySelection(is.getUser(), mb));
													RootPanel.get("Navigator").add(mb);
												}
												 
											 });
											
										}
										 
									 });
									
								}
								
							});
							
						}
						 
					 });
					
					
					 
					 
				}else{
					Window.alert("Löschen Sie erst Ihr Team");
					hide();
				}
			}
		});
			nein.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					hide();
					Showcase showcase = new MeinProfilAnzeigen(is);
					RootPanel.get("Details").clear();
					RootPanel.get("Details").add(showcase);
					
				}
			});
			
			unternehmenloschenseite.setWidget(0, 0, frage);
			unternehmenloschenseite.setWidget(1, 0, ja);
			unternehmenloschenseite.setWidget(1, 1, nein);
			vpanel.add(unternehmenloschenseite);
			this.add(vpanel);
			
		}
	}
	private class DialogBoxAbfrageTeamLoschen extends DialogBox{
private Person person = new Person();
		
		private VerticalPanel vpanel = new VerticalPanel();
		private HorizontalPanel hpanel = new HorizontalPanel();	

		private Button fertig = new Button("Fertig");
		
		private Label frage = new Label ("Mit der Löschung Ihres Unternehmens, wird auch Ihr Team gelöscht. Dies kann nicht rückgängig gemacht werden. Trotzdem löschen?");
		private Button ja = new Button("Ja");
		private Button nein = new Button("Nein");
		
		private Partnerprofil partnerprofil = null;
		Partnerprofil p1 = new Partnerprofil();
		AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
		
		private FlexTable unternehmenloschenseite = new FlexTable();
		
		public DialogBoxAbfrageTeamLoschen(final Person person){
			ja.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					
					 team.setID(is.getUser().getTeam_ID());
					 if (team.getID() != 0){
					 is.getUser().setTeam_ID(0);
					 }
					 
					 ((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
					 if (adminService == null) {
				     adminService = GWT.create(AdministrationProjektmarktplatz.class);
				   }
					 
					 adminService.getPartnerprofilOfOrganisationseinheit(is.getSelectedIdentityAsObject(), new AsyncCallback<Partnerprofil>(){

						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void onSuccess(Partnerprofil result) {
							result = partnerprofil; 
							is.getSelectedIdentityAsObject().setPartnerprofil_ID(partnerprofil.getID());
							adminService.updatePerson(is.getUser(), new AsyncCallback<Person>(){

								@Override
								public void onFailure(Throwable caught) {
									// TODO Auto-generated method stub
									
								}

								@Override
								public void onSuccess(Person result) {
									((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
									 if (adminService == null) {
								     adminService = GWT.create(AdministrationProjektmarktplatz.class);
								   }
									 adminService.deleteTeamByID(is.getSelectedIdentityAsObject().getID(), new AsyncCallback<Void>(){

										@Override
										public void onFailure(Throwable caught) {
											// TODO Auto-generated method stub
											
										}

										@Override
										public void onSuccess(Void result) {
											adminService.deletePartnerprofil(partnerprofil, new AsyncCallback<Void>(){

												@Override
												public void onFailure(Throwable caught) {
													// TODO Auto-generated method stub
													
												}

												@Override
												public void onSuccess(Void result) {
													hide();
												 	
												}
												
											});
										}
										 
									 });
								}
								 
							 });
							 
							
						}
						 
					 });
					 
					
					 
					
					

				}
				});
		
		unternehmenloschenseite.setWidget(0, 0, frage);
		unternehmenloschenseite.setWidget(1, 0, ja);
		unternehmenloschenseite.setWidget(1, 1, nein);
		vpanel.add(unternehmenloschenseite);
		this.add(vpanel);
		}
	}

	

}
