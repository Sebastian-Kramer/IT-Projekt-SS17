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
import de.hdm.ITProjekt.client.Menubar;
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
import de.hdm.ITProjekt.shared.bo.Team;
import de.hdm.ITProjekt.shared.bo.Unternehmen;
import de.hdm.ITProjekt.shared.bo.Beteiligung;


public class Projektseite extends Showcase{

	AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
	
	private Button createStelle = new Button("Stellenausschreibung anlegen");
	private Button detailsButton = new Button("Stellendetails anzeigen und bewerben");
	private Button alleBewerbungen = new Button("Bewerbungen anzeigen");
	private Button delete = new Button("Stellenausschreibung löschen");
	private Button deleteTeilnehmer = new Button("Person entfernen");
	private Button deleteUN = new Button("Unternehmen entfernen");
	private Button deleteTeam = new Button("Team entfernen");
	private Anchor zurstartseite = new Anchor("Startseite");
	private Anchor zuprojektmarktplaetze = new Anchor("/Projektmarktplätze");
	private Anchor zuprojekte = new Anchor("/Projekte");
	private Label projektverwaltung = new Label("/Projektverwaltung");
	private HorizontalPanel hpanelnavigator = new HorizontalPanel();  
	private FlexTable ft_navi = new FlexTable();
	
	VerticalPanel vp_projekt = new VerticalPanel();
	HorizontalPanel hp_projekt = new HorizontalPanel();
	VerticalPanel vp_projekt2 = new VerticalPanel();
	VerticalPanel vp_projekt3 = new VerticalPanel();
	VerticalPanel vp_projekt4 = new VerticalPanel();
	HorizontalPanel hp_projekt2 = new HorizontalPanel();
	
	CellTable<Ausschreibung> ct_projektausschreibungen = new CellTable<Ausschreibung>();
	CellTable<Person> ct_teilnehmer = new CellTable<Person>();
	CellTable<Team> ct_teamteilnehmer = new CellTable<Team>();
	CellTable<Unternehmen> ct_unternehmenteilnehmer = new CellTable<Unternehmen>();
	
	final SingleSelectionModel<Ausschreibung> ssm = new SingleSelectionModel<>();
	final SingleSelectionModel<Person> ssm_person = new SingleSelectionModel<>();
	final SingleSelectionModel<Team> ssm_team = new SingleSelectionModel<>();
	final SingleSelectionModel<Unternehmen> ssm_unternehmen = new SingleSelectionModel<>();
	private IdentitySelection is = null;
	private Menubar mb = null;
	
	private Person person = new Person();
	private Projekt selectedProjekt = new Projekt();
	private Ausschreibung a1 = new Ausschreibung();
	private Projektmarktplatz projektmarktplatz = new Projektmarktplatz();
	
	public Projektseite(){
		
	}
	
	public Projektseite(Projekt selectedObject,IdentitySelection is){
		this.selectedProjekt = selectedObject;
		this.is = is;
		
	}
	public Projektseite(Projekt selectedObject, IdentitySelection is, Projektmarktplatz projektmarktplatz){
		this.selectedProjekt = selectedObject;
		this.is = is;
		this.projektmarktplatz = projektmarktplatz;
	}
	
	public Projektseite(Projekt selectedObject, IdentitySelection is, Projektmarktplatz projektmarktplatz, Menubar mb){
		this.selectedProjekt = selectedObject;
		this.is = is;
		this.projektmarktplatz = projektmarktplatz;
		this.mb = mb;
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
		ct_teilnehmer.setSelectionModel(ssm_person);
		ct_teamteilnehmer.setSelectionModel(ssm_team);
		ct_unternehmenteilnehmer.setSelectionModel(ssm_unternehmen);
		
		zurstartseite.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Showcase showcase = new Homeseite();
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
			}
		});
	
		zuprojekte.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Showcase showcase = new Projekte(projektmarktplatz, is);
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
		delete.setStylePrimaryName("myprofil-button");
		deleteTeilnehmer.setStylePrimaryName("myprofil-button");
		deleteTeam.setStylePrimaryName("myprofil-button");
		deleteUN.setStylePrimaryName("myprofil-button");
		
		vp_projekt.add(ct_projektausschreibungen);
		this.add(hpanelnavigator);
		this.add(hp_projekt);
		this.append("<br><h3>Die offenen Stellen des Projekts</h3></br>");
		this.add(vp_projekt);
		
		vp_projekt2.add(ct_teilnehmer);
		this.append("<br><h3>Die Teilnehmer des Projekts</h3></br>");
		this.append("Personen");
		this.add(vp_projekt2);
		
		vp_projekt3.add(ct_teamteilnehmer);
		this.append("Teams");
		this.add(vp_projekt3);
		
		vp_projekt4.add(ct_unternehmenteilnehmer);
		this.append("Unternehmen");
		this.add(vp_projekt4);
		
		
		if(is.getSelectedIdentityAsObject() instanceof Person){
		if(is.getUser().getID()== selectedProjekt.getProjektleiter_ID()){
		
			hp_projekt.add(alleBewerbungen);		
			hp_projekt.add(createStelle);		
			hp_projekt.add(delete);
			hp_projekt.add(deleteTeilnehmer);
			hp_projekt.add(deleteUN);
			hp_projekt.add(deleteTeam);
		}else{
			hp_projekt.add(detailsButton);
		}
		}else{
			hp_projekt.add(detailsButton);
		}
		
		
		ct_projektausschreibungen.setSelectionModel(ssm);
		
		
			createStelle.addClickHandler(new ClickHandler(){

				@Override
				public void onClick(ClickEvent event) {
					if(is.getUser().getID() == selectedProjekt.getProjektleiter_ID()){
					DialogBoxAusschreibungAnlegen dialogBox = new DialogBoxAusschreibungAnlegen(selectedProjekt, is);
					dialogBox.center();
					
					}else{
						Window.alert("Nur der Projektleiter kann Stellenausschreibungen für dieses Projekt anlegen!");
					}
				}
				
			});
			
			detailsButton.addClickHandler(new ClickHandler(){

				@Override
				public void onClick(ClickEvent event) {
					if(ssm.getSelectedObject().getStatus() == "besetzt"){
						Window.alert("Die Stelle wurde schon besetzt");
					}else{
					a1 = ssm.getSelectedObject();
					DialogBoxAusschreibung dialogBox = new DialogBoxAusschreibung(a1, is, mb);
					dialogBox.center();
					}
				}
							
			});
			
			delete.addClickHandler(new ClickHandler(){

				@Override
				public void onClick(ClickEvent event) {
					final Ausschreibung selectedAusschreibung = ssm.getSelectedObject();
					if(is.getUser().getID() == selectedProjekt.getProjektleiter_ID()){
						selectedAusschreibung.setOrga_ID(0);
						selectedAusschreibung.setPartnerprofil_ID(0);
						selectedAusschreibung.setProjekt_ID(0);
						adminService.update(selectedAusschreibung, new AsyncCallback<Ausschreibung>(){

							@Override
							public void onFailure(Throwable caught) {
								Window.alert("Das hat nich geklappt1");
								
							}

							@Override
							public void onSuccess(Ausschreibung result) {
								adminService.findBewerbungByAusschreibungId(result.getID(), new AsyncCallback<Vector<Bewerbung>>(){

									@Override
									public void onFailure(Throwable caught) {
										Window.alert("Das hat nicht geklappt 2");
										
									}

									@Override
									public void onSuccess(Vector<Bewerbung> result) {
										if(result.isEmpty()){
											
											adminService.deleteAusschreibung(selectedAusschreibung, new AsyncCallback<Void>(){

												@Override
												public void onFailure(Throwable caught) {
													Window.alert("Das hat nicht geklappt 2.4");
													
												}

												@Override
												public void onSuccess(Void result) {
							
													Showcase Showcase = new Projektseite(selectedProjekt, is);
													Window.alert("Die Ausschreibung wurde erfolgreich gelöscht");
													RootPanel.get("Details").clear();
													RootPanel.get("Details").add(Showcase);
													
													
												}
												
											});
										}else{
											for(final Bewerbung b : result){
												b.setAusschreibungs_ID(0);
												b.setOrga_ID(0);
												adminService.updateBewerbung(b, new AsyncCallback<Bewerbung>(){

													@Override
													public void onFailure(Throwable caught) {
														// TODO Auto-generated method stub
														
													}

													@Override
													public void onSuccess(Bewerbung result) {
														adminService.getBewertungByBewerbung(result, new AsyncCallback<Vector<Bewertung>>(){

															@Override
															public void onFailure(Throwable caught) {
																// TODO Auto-generated method stub
																
															}

															@Override
															public void onSuccess(Vector<Bewertung> result) {
																if(result.isEmpty()){
																	adminService.deleteBewerbung(b, new AsyncCallback<Void>(){

																		@Override
																		public void onFailure(Throwable caught) {
																			// TODO Auto-generated method stub
																			
																		}

																		@Override
																		public void onSuccess(Void result) {
																			adminService.deleteAusschreibung(selectedAusschreibung, new AsyncCallback<Void>(){

																				@Override
																				public void onFailure(
																						Throwable caught) {
																					// TODO Auto-generated method stub
																					
																				}

																				@Override
																				public void onSuccess(Void result) {
																					Showcase Showcase = new Projektseite(selectedProjekt, is);
																					Window.alert("Die Ausschreibung wurde erfolgreich gelöscht");
																					RootPanel.get("Details").clear();
																					RootPanel.get("Details").add(Showcase);
																					
																				}
																				
																			});
																			
																		}
																		
																	});
																}else{
																	for(final Bewertung bew : result){
																		bew.setBeteiligungs_ID(0);
																		bew.setBewerbungs_ID(0);
																		adminService.updateBewertung(bew, new AsyncCallback<Bewertung>(){

																			@Override
																			public void onFailure(Throwable caught) {
																				// TODO Auto-generated method stub
																				
																			}

																			@Override
																			public void onSuccess(Bewertung result) {
																				adminService.deleteBewertung(bew, new AsyncCallback<Void>(){

																					@Override
																					public void onFailure(
																							Throwable caught) {
																						// TODO Auto-generated method stub
																						
																					}

																					@Override
																					public void onSuccess(Void result) {
																						adminService.deleteBewerbung(b, new AsyncCallback<Void>(){

																							@Override
																							public void onFailure(
																									Throwable caught) {
																								// TODO Auto-generated method stub
																								
																							}

																							@Override
																							public void onSuccess(
																									Void result) {
																								adminService.deleteAusschreibung(selectedAusschreibung, new AsyncCallback<Void>(){

																									@Override
																									public void onFailure(
																											Throwable caught) {
																										// TODO Auto-generated method stub
																										
																									}

																									@Override
																									public void onSuccess(
																											Void result) {
																										Showcase Showcase = new Projektseite(selectedProjekt, is);
																								
																										Window.alert("Die Ausschreibung wurde erfolgreich gelöscht");
																										RootPanel.get("Details").clear();
																										RootPanel.get("Details").add(Showcase);
																										
																									}
																									
																								});
																								
																							}
																							
																						});
																						
																					}
																					
																				});
																				
																			}
																			
																		});
																	}
																}
																
															}
															
														});
														
													}
													
												});
											}
										}
										
									}
									
								});
								
							}
							
						});
					}
					
				}
				
			});
			alleBewerbungen.addClickHandler(new ClickHandler(){

				@Override
				public void onClick(ClickEvent event) {
					if(ssm.getSelectedObject()!= null){
					a1 = ssm.getSelectedObject();
					Showcase showcase = new AlleBewerbungenFromAuschreibung(a1, is, selectedProjekt, projektmarktplatz);
					RootPanel.get("Details").clear();
					RootPanel.get("Details").add(showcase);
					}else{
						Window.alert("Bitte wählen Sie eine entsprechende Stellenausschreibung");
					}
				}
				
			});
			
			deleteTeilnehmer.addClickHandler(new ClickHandler(){

				@Override
				public void onClick(ClickEvent event) {
					if(ssm_person!=null){
					final Person selectedPerson = ssm_person.getSelectedObject();
					if(is.getUser().getID()==selectedProjekt.getProjektleiter_ID()){
						((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
						 if (adminService == null) {
					      adminService = GWT.create(AdministrationProjektmarktplatz.class);
					    }
						adminService.getBeteiligungByOrgaeinheit(selectedPerson, new AsyncCallback<Vector<Beteiligung>>(){

							@Override
							public void onFailure(Throwable caught) {
								Window.alert("kacke");
								
							}

							@Override
							public void onSuccess(Vector<Beteiligung> result) {
								for(final Beteiligung b : result){
									if(b.getProjekt_ID()==selectedProjekt.getID()){
									adminService.deleteBewertungbyBeteiligung(b.getID(), new AsyncCallback<Void>(){

										@Override
										public void onFailure(Throwable caught) {
											Window.alert("doof");
										}

										@Override
										public void onSuccess(Void result) {
											adminService.delete(b, new AsyncCallback<Void>(){

												@Override
												public void onFailure(Throwable caught) {
													Window.alert("blöd");
													
												}

												@Override
												public void onSuccess(Void result) {
													Showcase showcase = new Projektseite(selectedProjekt, is, projektmarktplatz);
													RootPanel.get("Details").clear();
													RootPanel.get("Details").add(showcase);
													
												}
												
											});
											
										}
										
									});
								}
								}
							}
							
						});

					}else{
						Window.alert("Das geht so nicht");
					}
					}else{
						Window.alert("Bitte wählen Sie eine Person zum entfernen aus!");
					}
				}
				
			});	
			
			
			deleteTeam.addClickHandler(new ClickHandler(){

				@Override
				public void onClick(ClickEvent event) {
					if(ssm_team != null){
					final Team selectedTeam = ssm_team.getSelectedObject();
					if(is.getUser().getID()==selectedProjekt.getProjektleiter_ID()){
						((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
						 if (adminService == null) {
					      adminService = GWT.create(AdministrationProjektmarktplatz.class);
					    }
						 adminService.getBeteiligungByOrgaeinheit(selectedTeam, new AsyncCallback<Vector<Beteiligung>>(){

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub
								
							}

							@Override
							public void onSuccess(Vector<Beteiligung> result) {
								for(final Beteiligung b : result){
									if(b.getProjekt_ID()==selectedProjekt.getID()){
									adminService.deleteBewertungbyBeteiligung(b.getID(), new AsyncCallback<Void>(){

										@Override
										public void onFailure(Throwable caught) {
											// TODO Auto-generated method stub
											
										}

										@Override
										public void onSuccess(Void result) {
											adminService.delete(b, new AsyncCallback<Void>(){

												@Override
												public void onFailure(Throwable caught) {
													// TODO Auto-generated method stub
													
												}

												@Override
												public void onSuccess(Void result) {
													Showcase showcase = new Projektseite(selectedProjekt, is, projektmarktplatz);
													RootPanel.get("Details").clear();
													RootPanel.get("Details").add(showcase);
												}
												
											});
										
											
										}
										
									});
								}
								}
							}
							 
						 });
					}
					}else{
						Window.alert("Bitte wählen Sie ein Team zum entfernen aus!");
					}
				}
				
			});
			
			deleteUN.addClickHandler(new ClickHandler(){

				@Override
				public void onClick(ClickEvent event) {
					if(ssm_unternehmen != null){
					final Unternehmen selectedUnternehmen = ssm_unternehmen.getSelectedObject();
					if(is.getUser().getID()==selectedProjekt.getProjektleiter_ID()){
						((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
						 if (adminService == null) {
					      adminService = GWT.create(AdministrationProjektmarktplatz.class);
					    }
						 adminService.getBeteiligungByOrgaeinheit(selectedUnternehmen, new AsyncCallback<Vector<Beteiligung>>(){

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub
								
							}

							@Override
							public void onSuccess(Vector<Beteiligung> result) {
								for(final Beteiligung b : result){
									if(b.getProjekt_ID()==selectedProjekt.getID()){
									adminService.deleteBewertungbyBeteiligung(b.getID(), new AsyncCallback<Void>(){

										@Override
										public void onFailure(Throwable caught) {
											// TODO Auto-generated method stub
											
										}

										@Override
										public void onSuccess(Void result) {
											adminService.delete(b, new AsyncCallback<Void>(){

												@Override
												public void onFailure(Throwable caught) {
													// TODO Auto-generated method stub
													
												}

												@Override
												public void onSuccess(Void result) {
													Showcase showcase = new Projektseite(selectedProjekt, is, projektmarktplatz);
													RootPanel.get("Details").clear();
													RootPanel.get("Details").add(showcase);
												}
												
											});
											
										}
										
									});
								}
								}
							}
							 
						 });
				}
					
				}else{
					Window.alert("Bitte wählen Sie ein Unternehmen zum entfernen aus");
				}
				}
			});
		
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
		Column<Ausschreibung, String> status =
				new Column<Ausschreibung, String>(new ClickableTextCell()){

					@Override
					public String getValue(Ausschreibung object) {
						// TODO Auto-generated method stub
						return object.getStatus();
					}
		
		};
		
	
	ct_projektausschreibungen.addColumn(bezeichnung, "Stellenbezeichnung");
	ct_projektausschreibungen.addColumn(ablauffrist, "Bewerbungsfrist");
	ct_projektausschreibungen.addColumn(status, "Ausschreibungsstatus");
	
	
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
	
	Column<Unternehmen, String>	nameunternehmen = 
			new Column<Unternehmen, String>(new ClickableTextCell()){

				@Override
				public String getValue(Unternehmen object) {
					// TODO Auto-generated method stub
					return object.getName();
				}

				
		
	};
	
	Column<Unternehmen, String>	anschriftunt = 
			new Column<Unternehmen, String>(new ClickableTextCell()){

				@Override
				public String getValue(Unternehmen object) {
					// TODO Auto-generated method stub
					return object.getStrasse() + " " + object.getHausnummer() + ", " + object.getPlz() + " " + object.getOrt();
				}

				
		
	};
	
	Column<Team, String>	nameteam = 
			new Column<Team, String>(new ClickableTextCell()){

				@Override
				public String getValue(Team object) {
					// TODO Auto-generated method stub
					return object.getName();
				}

				
		
	};
	
	Column<Team, String>	anschriftteam = 
			new Column<Team, String>(new ClickableTextCell()){

				@Override
				public String getValue(Team object) {
					// TODO Auto-generated method stub
					return object.getStrasse() + " " + object.getHausnummer() + ", " + object.getPlz() + " " + object.getOrt();
				}

				
		
	};
	
	ct_teamteilnehmer.addColumn(nameteam, "Name");
	ct_teamteilnehmer.addColumn(anschriftteam, "Anschrift");
	
	ct_unternehmenteilnehmer.addColumn(nameunternehmen, "Name");
	ct_unternehmenteilnehmer.addColumn(anschriftunt, "Anschrift");
	
	ct_teilnehmer.addColumn(name, "Name");
	ct_teilnehmer.addColumn(email, "E-Mail");
	
	filltableauschreibungen();
	filltableteilnehmer();
	filltableteam();
	filltableunternehmen();
	
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
	
	private void filltableunternehmen(){
		((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
		 if (adminService == null) {
	     adminService = GWT.create(AdministrationProjektmarktplatz.class);
	   }
		 adminService.getBeteiligungByProjekt(selectedProjekt.getID(), new AsyncCallback<Vector<Beteiligung>>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Vector<Beteiligung> result) {
				for(Beteiligung b : result){
					adminService.getUnternehmenByID(b.getOrga_ID(), new AsyncCallback<Vector<Unternehmen>>(){

						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void onSuccess(Vector<Unternehmen> result) {
							ct_unternehmenteilnehmer.setRowData(0, result);
							ct_unternehmenteilnehmer.setRowCount(result.size(), true);
							
						}
						
					});
				}
				
			}
			 
		 });
	}
	
private void filltableteam(){
	((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
	 if (adminService == null) {
     adminService = GWT.create(AdministrationProjektmarktplatz.class);
   }
	adminService.getBeteiligungByProjekt(selectedProjekt.getID(), new AsyncCallback<Vector<Beteiligung>>(){

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Vector<Beteiligung> result) {
			for(Beteiligung b : result){
				adminService.findTeamByID(b.getOrga_ID(), new AsyncCallback<Vector<Team>>(){

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(Vector<Team> result) {
						ct_teamteilnehmer.setRowData(0, result);
						ct_teamteilnehmer.setRowCount(result.size(), true);
						
					}
					
				});
			}
			
		}
		
	});
}

	
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
