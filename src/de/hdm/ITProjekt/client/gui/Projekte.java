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
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;

import de.hdm.ITProjekt.client.gui.ProjektmarktplatzSeite;
import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.client.Menubar;
import de.hdm.ITProjekt.client.Showcase;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatz;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;
import de.hdm.ITProjekt.shared.bo.Ausschreibung;
import de.hdm.ITProjekt.shared.bo.Beteiligung;
import de.hdm.ITProjekt.shared.bo.Bewerbung;
import de.hdm.ITProjekt.shared.bo.Bewertung;
import de.hdm.ITProjekt.shared.bo.Person;
import de.hdm.ITProjekt.shared.bo.Projekt;
import de.hdm.ITProjekt.shared.bo.Projektmarktplatz;

public class Projekte extends Showcase {

	AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
	
	CellTable<Projekt> ct_alleProjekte = new CellTable<Projekt>();
	

	ButtonCell detailsButton = new ButtonCell();
	
	
	HorizontalPanel hpanel_projekte = new HorizontalPanel();
	VerticalPanel vpanel_projekte = new VerticalPanel();
	
	Button add_projekt = new Button("Projekt Hinzufügen");
	Button delete_projekt = new Button("Projekt Löschen");
	Button show_projekt = new Button("Projekt anzeigen");
	
	final SingleSelectionModel<Projekt> ssm_projekt = new SingleSelectionModel<Projekt>();
	
	private Projekt projekt = new Projekt();
	private Vector<Ausschreibung> aus;
	private Anchor zurstartseite = new Anchor("Startseite");
	private Anchor zuprojektmarktplaetze = new Anchor("/Projektmarktplätze");
	private Label projektelabel = new Label("/Projekte");
	private HorizontalPanel hpanelnavigator = new HorizontalPanel();  
	private FlexTable ft_navi = new FlexTable();
	
	private IdentitySelection is = null;
	
	public Projekte(){
		
	}
	
	
	private Menubar menubar;
	private Person person = new Person();
	private Projektmarktplatz selectedProjektmarktplatz = new Projektmarktplatz();
	
	public Projekte(Projektmarktplatz selectedObject, IdentitySelection is){
		this.selectedProjektmarktplatz = selectedObject;
		this.is = is;
	}
	public Projekte(Projektmarktplatz selectedObject, IdentitySelection is, Menubar menubar){
		this.selectedProjektmarktplatz = selectedObject;
		this.is = is;
		this.menubar = menubar;
	}


	@Override
	protected String getHeadlineText() {
		
		return "<h3> Das sind alle Projekte <h/3>";
	}

	@Override
	protected void run() {
		
		zurstartseite.setStylePrimaryName("navigationanchor");
		zuprojektmarktplaetze.setStylePrimaryName("navigationanchor");
		projektelabel.setStylePrimaryName("navigationanchor");
		add_projekt.setStylePrimaryName("myprofil-button");
		delete_projekt.setStylePrimaryName("myprofil-button");
		show_projekt.setStylePrimaryName("myprofil-button");
		
		ft_navi.setWidget(0, 0, zurstartseite);
		ft_navi.setWidget(0, 1, zuprojektmarktplaetze);
		ft_navi.setWidget(0, 2, projektelabel);
		ft_navi.setCellPadding(10);
		hpanelnavigator.add(ft_navi);
		
		RootPanel.get("Details").setWidth("100%");
		ct_alleProjekte.setWidth("100%", true);
		ct_alleProjekte.setSelectionModel(ssm_projekt);
		hpanel_projekte.add(show_projekt);
		

		hpanel_projekte.add(add_projekt);
		hpanel_projekte.add(delete_projekt);
		
		vpanel_projekte.add(ct_alleProjekte);
		this.add(ft_navi);
		this.add(hpanel_projekte);
		this.add(vpanel_projekte);
		
		
		
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
				Showcase showcase = new ProjektmarktplatzSeite(is);
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
			}
		});
		
		add_projekt.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				if(is.getSelectedIdentityAsObject() instanceof Person){
				DialogBox dialogbox = new DialogBoxProjekte(selectedProjektmarktplatz, is);
				dialogbox.center();
			}else{
				Window.alert("Ein Team oder Unternehmen kann kein Projekt anlegen");
			}
			
		}
	});
		
		show_projekt.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				if(ssm_projekt != null){
					((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
					 if (adminService == null) {
				      adminService = GWT.create(AdministrationProjektmarktplatz.class);
				    }
					 projekt = ssm_projekt.getSelectedObject();
					 Showcase showcase = new Projektseite(projekt, is, selectedProjektmarktplatz, menubar);
					 RootPanel.get("Details").clear();
					 RootPanel.get("Details").add(showcase);
					 
					 
				}else{
					Window.alert("Bitte wählen Sie ein Projekt");
				}
				
			}
			
		});
		
		delete_projekt.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				final Projekt selectedProjektObject = ssm_projekt.getSelectedObject();
				if(is.getSelectedIdentityAsObject() instanceof Person){
				if(is.getUser().getID() == selectedProjektObject.getProjektleiter_ID()){
					
				selectedProjektObject.setProjektmarktplatz_ID(0);
				selectedProjektObject.setProjektleiter_ID(0);
				if (selectedProjektObject != null){
					
					((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
					 if (adminService == null) {
				      adminService = GWT.create(AdministrationProjektmarktplatz.class);
				    }
					 adminService.getBeteiligungByProjekt(selectedProjektObject.getID(), new AsyncCallback<Vector<Beteiligung>>(){

						@Override
						public void onFailure(Throwable caught) {

							
							
						}

							@Override
							public void onSuccess(Vector<Beteiligung> result) {
								if(result.isEmpty()){
									adminService.updateProjekt(selectedProjektObject, new AsyncCallback<Projekt>(){
									
										@Override
										public void onFailure(Throwable caught) {
											Window.alert("Löschen hat nicht funktioniert 1");
											
										}

										@Override
										public void onSuccess(Projekt result) {
											adminService.getAlLAuscchreibungenBy(result.getID(), new AsyncCallback<Vector<Ausschreibung>>(){

												@Override
												public void onFailure(Throwable caught) {
													Window.alert("Löschen hat nicht funktioniert 2");
													
												}

												@Override
												public void onSuccess(Vector<Ausschreibung> result) {
													if(result.isEmpty()){
														adminService.deleteProjekt(selectedProjektObject, new AsyncCallback<Void>(){

															@Override
															public void onFailure(Throwable caught) {
																// TODO Auto-generated method stub
																
															}

															@Override
															public void onSuccess(Void result) {
																Window.alert("Projekt erfolgreich gelöscht");
																Showcase showcase = new Projekte(selectedProjektmarktplatz, is);
																RootPanel.get("Details").clear();
																RootPanel.get("Details").add(showcase);
																
															}
															
														});
													}else{
														for(final Ausschreibung a : result){
															a.setOrga_ID(0);
															a.setPartnerprofil_ID(0);
															a.setProjekt_ID(0);
															adminService.update(a, new AsyncCallback<Ausschreibung>(){

																@Override
																public void onFailure(Throwable caught) {
																	// TODO Auto-generated method stub
																	
																}

																@Override
																public void onSuccess(Ausschreibung result) {
																	adminService.findBewerbungByAusschreibungId(result.getID(), new AsyncCallback<Vector<Bewerbung>>(){

																		@Override
																		public void onFailure(Throwable caught) {
																			// TODO Auto-generated method stub
																			
																		}

																		@Override
																		public void onSuccess(
																				Vector<Bewerbung> result) {
																			if(result.isEmpty()){
																				adminService.deleteAusschreibung(a, new AsyncCallback<Void>(){

																					@Override
																					public void onFailure(
																							Throwable caught) {
																						// TODO Auto-generated method stub
																						
																					}

																					@Override
																					public void onSuccess(Void result) {
																						adminService.deleteProjekt(selectedProjektObject, new AsyncCallback<Void>(){

																							@Override
																							public void onFailure(
																									Throwable caught) {
																								
																								
																							}

																							@Override
																							public void onSuccess(
																									Void result) {
																								Window.alert("Projekt erfolgreich gelöscht");
																								Showcase showcase = new Projekte(selectedProjektmarktplatz, is);
																								RootPanel.get("Details").clear();
																								RootPanel.get("Details").add(showcase);
																								
																							}
																							
																						});
																						
																					}
																					
																				});
																			}else{
																				for(final Bewerbung b : result){
																					b.setOrga_ID(0);
																					b.setAusschreibungs_ID(0);
																					adminService.updateBewerbung(b, new AsyncCallback<Bewerbung>(){

																						@Override
																						public void onFailure(
																								Throwable caught) {
																							// TODO Auto-generated method stub
																							
																						}

																						@Override
																						public void onSuccess(
																								Bewerbung result) {
																							adminService.getBewertungByBewerbung(result.getID(), new AsyncCallback<Vector<Bewertung>>(){

																								@Override
																								public void onFailure(
																										Throwable caught) {
																									// TODO Auto-generated method stub
																									
																								}

																								@Override
																								public void onSuccess(
																										Vector<Bewertung> result) {
																									if(result.isEmpty()){
																										adminService.deleteBewerbung(b, new AsyncCallback<Void>(){

																											@Override
																											public void onFailure(
																													Throwable caught) {
																												// TODO Auto-generated method stub
																												
																											}

																											@Override
																											public void onSuccess(
																													Void result) {
																												adminService.deleteAusschreibung(a, new AsyncCallback<Void>(){

																													@Override
																													public void onFailure(
																															Throwable caught) {
																														// TODO Auto-generated method stub
																														
																													}

																													@Override
																													public void onSuccess(
																															Void result) {
																														adminService.deleteProjekt(selectedProjektObject, new AsyncCallback<Void>(){

																															@Override
																															public void onFailure(
																																	Throwable caught) {
																																// TODO Auto-generated method stub
																																
																															}

																															@Override
																															public void onSuccess(
																																	Void result) {
																																Window.alert("Projekt erfolgreich gelöscht");
																																Showcase showcase = new Projekte(selectedProjektmarktplatz, is);
																																RootPanel.get("Details").clear();
																																RootPanel.get("Details").add(showcase);
																																
																															}
																															
																														});
																														
																													}
																													
																												});
																												
																											}
																											
																										});
																									}else{
																										for(final Bewertung bew : result){
																											bew.setBewerbungs_ID(0);
																											bew.setBeteiligungs_ID(0);
																											adminService.updateBewertung(bew, new AsyncCallback<Bewertung>(){

																												@Override
																												public void onFailure(
																														Throwable caught) {
																													// TODO Auto-generated method stub
																													
																												}

																												@Override
																												public void onSuccess(
																														Bewertung result) {
																													
																													adminService.deleteBewertung(bew, new AsyncCallback<Void>(){

																														@Override
																														public void onFailure(
																																Throwable caught) {
																															// TODO Auto-generated method stub
																															
																														}

																														@Override
																														public void onSuccess(
																																Void result) {
																															adminService.deleteBewerbung(b, new AsyncCallback<Void>(){

																																@Override
																																public void onFailure(
																																		Throwable caught) {
																																	// TODO Auto-generated method stub
																																	
																																}

																																@Override
																																public void onSuccess(
																																		Void result) {
																																	adminService.deleteAusschreibung(a, new AsyncCallback<Void>(){

																																		@Override
																																		public void onFailure(
																																				Throwable caught) {
																																			// TODO Auto-generated method stub
																																			
																																		}

																																		@Override
																																		public void onSuccess(
																																				Void result) {
																																			adminService.deleteProjekt(selectedProjektObject, new AsyncCallback<Void>(){

																																				@Override
																																				public void onFailure(
																																						Throwable caught) {
																																					// TODO Auto-generated method stub
																																					
																																				}

																																				@Override
																																				public void onSuccess(
																																						Void result) {
																																					Window.alert("Projekt erfolgreich gelöscht");
																																					Showcase showcase = new Projekte(selectedProjektmarktplatz, is);
																																					RootPanel.get("Details").clear();
																																					RootPanel.get("Details").add(showcase);
																																					
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
													
												}
												
											});
											
										}
										
									});
								}else{
									for(final Beteiligung bet : result){
										bet.setOrga_ID(0);
										bet.setProjekt_ID(0);
										adminService.deleteBewertungbyBeteiligung(bet.getID(),new BewertungLoeschen());
										adminService.updateBeteiligung(bet, new AsyncCallback<Beteiligung>(){

											@Override
											public void onFailure(Throwable caught) {
												// TODO Auto-generated method stub
												
											}

											@Override
											public void onSuccess(Beteiligung result) {
												
												adminService.delete(bet, new AsyncCallback<Void>(){

													@Override
													public void onFailure(Throwable caught) {
														// TODO Auto-generated method stub
														
													}

													@Override
													public void onSuccess(Void result) {
														adminService.updateProjekt(selectedProjektObject, new AsyncCallback<Projekt>(){

															@Override
															public void onFailure(Throwable caught) {
																Window.alert("Löschen hat nicht funktioniert 1");
																
															}

															@Override
															public void onSuccess(Projekt result) {
																adminService.getAlLAuscchreibungenBy(result.getID(), new AsyncCallback<Vector<Ausschreibung>>(){

																	@Override
																	public void onFailure(Throwable caught) {
																		Window.alert("Löschen hat nicht funktioniert 2");
																		
																	}

																	@Override
																	public void onSuccess(Vector<Ausschreibung> result) {
																		if(result.isEmpty()){
																			adminService.deleteProjekt(selectedProjektObject, new AsyncCallback<Void>(){

																				@Override
																				public void onFailure(Throwable caught) {
																					// TODO Auto-generated method stub
																					
																				}

																				@Override
																				public void onSuccess(Void result) {
																					Window.alert("Projekt erfolgreich gelöscht");
																					Showcase showcase = new Projekte(selectedProjektmarktplatz, is);
																					RootPanel.get("Details").clear();
																					RootPanel.get("Details").add(showcase);
																					
																				}
																				
																			});
																		}else{
																			for(final Ausschreibung a : result){
																				a.setOrga_ID(0);
																				a.setPartnerprofil_ID(0);
																				a.setProjekt_ID(0);
																				adminService.update(a, new AsyncCallback<Ausschreibung>(){

																					@Override
																					public void onFailure(Throwable caught) {
																						// TODO Auto-generated method stub
																						
																					}

																					@Override
																					public void onSuccess(Ausschreibung result) {
																						adminService.findBewerbungByAusschreibungId(result.getID(), new AsyncCallback<Vector<Bewerbung>>(){

																							@Override
																							public void onFailure(Throwable caught) {
																								// TODO Auto-generated method stub
																								
																							}

																							@Override
																							public void onSuccess(
																									Vector<Bewerbung> result) {
																								if(result.isEmpty()){
																									adminService.deleteAusschreibung(a, new AsyncCallback<Void>(){

																										@Override
																										public void onFailure(
																												Throwable caught) {
																											// TODO Auto-generated method stub
																											
																										}

																										@Override
																										public void onSuccess(Void result) {
																											adminService.deleteProjekt(selectedProjektObject, new AsyncCallback<Void>(){

																												@Override
																												public void onFailure(
																														Throwable caught) {
																													
																													
																												}

																												@Override
																												public void onSuccess(
																														Void result) {
																													Window.alert("Projekt erfolgreich gelöscht");
																													Showcase showcase = new Projekte(selectedProjektmarktplatz, is);
																													RootPanel.get("Details").clear();
																													RootPanel.get("Details").add(showcase);
																													
																												}
																												
																											});
																											
																										}
																										
																									});
																								}else{
																									for(final Bewerbung b : result){
																										b.setOrga_ID(0);
																										b.setAusschreibungs_ID(0);
																										adminService.updateBewerbung(b, new AsyncCallback<Bewerbung>(){

																											@Override
																											public void onFailure(
																													Throwable caught) {
																												// TODO Auto-generated method stub
																												
																											}

																											@Override
																											public void onSuccess(
																													Bewerbung result) {
																												adminService.getBewertungByBewerbung(result.getID(), new AsyncCallback<Vector<Bewertung>>(){

																													@Override
																													public void onFailure(
																															Throwable caught) {
																														// TODO Auto-generated method stub
																														
																													}

																													@Override
																													public void onSuccess(
																															Vector<Bewertung> result) {
																														if(result.isEmpty()){
																															adminService.deleteBewerbung(b, new AsyncCallback<Void>(){

																																@Override
																																public void onFailure(
																																		Throwable caught) {
																																	// TODO Auto-generated method stub
																																	
																																}

																																@Override
																																public void onSuccess(
																																		Void result) {
																																	adminService.deleteAusschreibung(a, new AsyncCallback<Void>(){

																																		@Override
																																		public void onFailure(
																																				Throwable caught) {
																																			// TODO Auto-generated method stub
																																			
																																		}

																																		@Override
																																		public void onSuccess(
																																				Void result) {
																																			adminService.deleteProjekt(selectedProjektObject, new AsyncCallback<Void>(){

																																				@Override
																																				public void onFailure(
																																						Throwable caught) {
																																					// TODO Auto-generated method stub
																																					
																																				}

																																				@Override
																																				public void onSuccess(
																																						Void result) {
																																					Window.alert("Projekt erfolgreich gelöscht");
																																					Showcase showcase = new Projekte(selectedProjektmarktplatz, is);
																																					RootPanel.get("Details").clear();
																																					RootPanel.get("Details").add(showcase);
																																					
																																				}
																																				
																																			});
																																			
																																		}
																																		
																																	});
																																	
																																}
																																
																															});
																														}else{
																															for(final Bewertung bew : result){
																																bew.setBewerbungs_ID(0);
																																bew.setBeteiligungs_ID(0);
																																adminService.updateBewertung(bew, new AsyncCallback<Bewertung>(){

																																	@Override
																																	public void onFailure(
																																			Throwable caught) {
																																		// TODO Auto-generated method stub
																																		
																																	}

																																	@Override
																																	public void onSuccess(
																																			Bewertung result) {
																																		adminService.deleteBewertung(bew, new AsyncCallback<Void>(){

																																			@Override
																																			public void onFailure(
																																					Throwable caught) {
																																				// TODO Auto-generated method stub
																																				
																																			}

																																			@Override
																																			public void onSuccess(
																																					Void result) {
																																				adminService.deleteBewerbung(b, new AsyncCallback<Void>(){

																																					@Override
																																					public void onFailure(
																																							Throwable caught) {
																																						// TODO Auto-generated method stub
																																						
																																					}

																																					@Override
																																					public void onSuccess(
																																							Void result) {
																																						adminService.deleteAusschreibung(a, new AsyncCallback<Void>(){

																																							@Override
																																							public void onFailure(
																																									Throwable caught) {
																																								// TODO Auto-generated method stub
																																								
																																							}

																																							@Override
																																							public void onSuccess(
																																									Void result) {
																																								adminService.deleteProjekt(selectedProjektObject, new AsyncCallback<Void>(){

																																									@Override
																																									public void onFailure(
																																											Throwable caught) {
																																										// TODO Auto-generated method stub
																																										
																																									}

																																									@Override
																																									public void onSuccess(
																																											Void result) {
																																										Window.alert("Projekt erfolgreich gelöscht");
																																										Showcase showcase = new Projekte(selectedProjektmarktplatz, is);
																																										RootPanel.get("Details").clear();
																																										RootPanel.get("Details").add(showcase);
																																										
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
				}else{
					Window.alert("Nur der Projektleiter kann ein Projekt löschen");
				}
				
			}else{
				Window.alert("Nur der Projektleiter kann ein Projekt löschen");
				
			}
			}
			
		});
			
		
		Column<Projekt, String> projektname = 
				    new Column<Projekt, String>(new ClickableTextCell())  {
				    
						@Override
						public String getValue(Projekt object) {
							// TODO Auto-generated method stub
							
							return object.getName();
						}
				    };
				    
		Column<Projekt, String> beschreibung = 
					new Column<Projekt, String>(new ClickableTextCell())  {
											    
						  	@Override
						public String getValue(Projekt object) {										
						  		return object.getBeschreibung();
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

		  //Erstellen der Buttonspalte um Details öffnen zu können
		  Column<Projekt,String> buttonCell =
				  new Column<Projekt,String>(detailsButton){

				@Override
					public String getValue(Projekt object) {
					object = ssm_projekt.getSelectedObject();						
						return "Details";
					}
			  
		  };
		  
		  
		  // implementieren des FieldUpdater um die Zeile anklicken zu können
	  buttonCell.setFieldUpdater(new FieldUpdater<Projekt,String>(){

			@Override
			public void update(int index, Projekt object , String value) {
				object = ssm_projekt.getSelectedObject();
				DialogBoxProjektdetails dialogbox_details  = new DialogBoxProjektdetails(object, is);
				dialogbox_details.center();
				
					
				
			}
		  
	
			
		  
		  });
		  

		ct_alleProjekte.addColumn(projektname, "Projektname");		    
		ct_alleProjekte.addColumn(startdatum, "Startdatum");	
		ct_alleProjekte.addColumn(enddatum, "Enddatum");	
		ct_alleProjekte.addColumn(beschreibung, "Beschreibung");
		ct_alleProjekte.addColumn(buttonCell, "Details anzeigen");	


		filltableprojekte();

	
	
}

	private void filltableprojekte(){
		
		((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
		 if (adminService == null) {
	      adminService = GWT.create(AdministrationProjektmarktplatz.class);
	    }
		 adminService.findByProjektmarktplatz(selectedProjektmarktplatz, new AsyncCallback<Vector<Projekt>>(){

			@Override
			public void onFailure(Throwable caught) {
		
			}

			@Override
			public void onSuccess(Vector<Projekt> result) {
				ct_alleProjekte.setRowData(0, result);
				ct_alleProjekte.setRowCount(result.size(), true);
				
				
			}
			 
		 });
		 
	}
				
	private class BewertungLoeschen implements AsyncCallback<Void>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Void result) {
			Window.alert("Beteiligungen gelöscht");
			
		}
		
	}
		
		
		
	}
	







