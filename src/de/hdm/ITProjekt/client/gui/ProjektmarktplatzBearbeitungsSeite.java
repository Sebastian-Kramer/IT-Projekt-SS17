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
import de.hdm.ITProjekt.shared.bo.Ausschreibung;
import de.hdm.ITProjekt.shared.bo.Beteiligung;
import de.hdm.ITProjekt.shared.bo.Bewerbung;
import de.hdm.ITProjekt.shared.bo.Bewertung;
import de.hdm.ITProjekt.shared.bo.Person;
import de.hdm.ITProjekt.shared.bo.Projekt;
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
	
	ProjektmarktplatzBearbeitungsSeite(IdentitySelection is){
		this.is = is;
	}
	
	private Person person;
	private IdentitySelection is = null;
	
	Button bearbeitungsmodus_aktiv = new Button("Bearbeitungsansicht");
	Button projektmarktplatz_anlegen = new Button("Hinzufügen");
	Button projektmarktplatz_loeschen = new Button("Löschen");
	Button projektmarktplatz_aendern = new Button("Ändern");
	
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
						Showcase showcase = new ProjektmarktplatzSeite(is);
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
					final Projektmarktplatz selectedObject = ssm_alleProjektmarktplaetze.getSelectedObject();
					if (selectedObject != null){
						((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
						 if (adminService == null) {
					      adminService = GWT.create(AdministrationProjektmarktplatz.class);
					    }
						 
						 adminService.findByProjektmarktplatz(selectedObject, new AsyncCallback<Vector<Projekt>>(){

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub
								
							}

							@Override
							public void onSuccess(Vector<Projekt> result) {
								for(Projekt p : result){
									adminService.getBeteiligungByProjekt(p.getID(), new AsyncCallback<Vector<Beteiligung>>(){

										@Override
										public void onFailure(Throwable caught) {
											// TODO Auto-generated method stub
											
										}

										@Override
										public void onSuccess(Vector<Beteiligung> result) {
											for(Beteiligung bet : result){
												adminService.delete(bet, new AsyncCallback<Void>(){

													@Override
													public void onFailure(Throwable caught) {
														// TODO Auto-generated method stub
														
													}

													@Override
													public void onSuccess(Void result) {
														 adminService.findByProjektmarktplatz(selectedObject, new AsyncCallback<Vector<Projekt>>(){

																@Override
																public void onFailure(Throwable caught) {
																	Window.alert("Löschen hat nicht funktioniert 1");
																	
																	
																}

																@Override
																public void onSuccess(Vector<Projekt> result) {
																	if(result.isEmpty()){
																		adminService.deleteProjektmarktplatz(selectedObject, new AsyncCallback<Void>(){

																			@Override
																			public void onFailure(Throwable caught) {
																				// TODO Auto-generated method stub
																				
																			}

																			@Override
																			public void onSuccess(Void result) {
																				Window.alert("Der Projektmarktplatz wurde erfolgreich gelöscht");
																				Showcase showcase = new ProjektmarktplatzBearbeitungsSeite(is);
																				RootPanel.get("Details").clear();
																				RootPanel.get("Details").add(showcase);
																				
																			}
																			
																		});
																	}else{
																	for(final Projekt p : result){
																		p.setProjektleiter_ID(0);
																		p.setProjektmarktplatz_ID(0);
																		adminService.updateProjekt(p, new AsyncCallback<Projekt>(){

																			@Override
																			public void onFailure(Throwable caught) {
																				Window.alert("Löschen hat nicht funktioniert 2");
																				
																			}

																			@Override
																			public void onSuccess(Projekt result) {
																				adminService.getAlLAuscchreibungenBy(result.getID(), new AsyncCallback<Vector<Ausschreibung>>(){

																					@Override
																					public void onFailure(Throwable caught) {
																						Window.alert("Löschen hat nicht funktioniert 3");
																						
																					}

																					@Override
																					public void onSuccess(Vector<Ausschreibung> result) {
																						if(result.isEmpty()){
																							adminService.deleteProjekt(p, new AsyncCallback<Void>(){

																								@Override
																								public void onFailure(Throwable caught) {
																									
																									
																								}

																								@Override
																								public void onSuccess(Void result) {
																									adminService.deleteProjektmarktplatz(selectedObject, new AsyncCallback<Void>(){

																										@Override
																										public void onFailure(Throwable caught) {
																											// TODO Auto-generated method stub
																											
																										}

																										@Override
																										public void onSuccess(Void result) {
																											Window.alert("Der Projektmarktplatz wurde erfolgreich gelöscht");
																											Showcase showcase = new ProjektmarktplatzBearbeitungsSeite(is);
																											RootPanel.get("Details").clear();
																											RootPanel.get("Details").add(showcase);
																											
																										}
																										
																									});
																									
																								}
																								
																							});
																						}else{
																						for(final Ausschreibung a: result){
																							a.setOrga_ID(0);
																							a.setPartnerprofil_ID(0);
																							a.setProjekt_ID(0);
																							adminService.update(a, new AsyncCallback<Ausschreibung>(){

																								@Override
																								public void onFailure(Throwable caught) {
																									Window.alert("Löschen hat nicht funktioniert 4");
																									
																								}

																								@Override
																								public void onSuccess(Ausschreibung result) {
																									adminService.findBewerbungByAusschreibungId(result.getID(), new AsyncCallback<Vector<Bewerbung>>(){

																										@Override
																										public void onFailure(Throwable caught) {
																											Window.alert("Löschen hat nicht funktioniert 3");
																											
																										}

																										@Override
																										public void onSuccess(Vector<Bewerbung> result) {
																											if(result.isEmpty()){
																												adminService.deleteAusschreibung(a, new AsyncCallback<Void>(){

																													@Override
																													public void onFailure(
																															Throwable caught) {
																														// TODO Auto-generated method stub
																														
																													}

																													@Override
																													public void onSuccess(Void result) {
																														adminService.deleteProjekt(p, new AsyncCallback<Void>(){

																															@Override
																															public void onFailure(
																																	Throwable caught) {
																																// TODO Auto-generated method stub
																																
																															}

																															@Override
																															public void onSuccess(
																																	Void result) {
																																adminService.deleteProjektmarktplatz(selectedObject, new AsyncCallback<Void>(){

																																	@Override
																																	public void onFailure(
																																			Throwable caught) {
																																		// TODO Auto-generated method stub
																																		
																																	}

																																	@Override
																																	public void onSuccess(
																																			Void result) {
																																		Window.alert("Der Projektmarktplatz wurde erfolgreich gelöscht");
																																		Showcase showcase = new ProjektmarktplatzBearbeitungsSeite(is);
																																		RootPanel.get("Details").clear();
																																		RootPanel.get("Details").add(showcase);
																																	}
																																	
																																});
																																
																															}
																															
																														});
																														
																													}
																													
																												});
																											}else{
																											for(final Bewerbung b : result){
																												b.setAusschreibungs_ID(0);
																												b.setOrga_ID(0);
																												adminService.updateBewerbung(b, new AsyncCallback<Bewerbung>(){

																													@Override
																													public void onFailure(
																															Throwable caught) {
																														Window.alert("Löschen hat nicht funktioniert 4");
																														
																													}

																													@Override
																													public void onSuccess(
																															Bewerbung result) {
																														adminService.getBewertungByBewerbung(result.getID(), new AsyncCallback<Vector<Bewertung>>(){

																															@Override
																															public void onFailure(
																																	Throwable caught) {
																																Window.alert("Löschen hat nich funktioniert 5");
																																
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
																																					adminService.deleteProjekt(p, new AsyncCallback<Void>(){

																																						@Override
																																						public void onFailure(
																																								Throwable caught) {
																																							// TODO Auto-generated method stub
																																							
																																						}

																																						@Override
																																						public void onSuccess(
																																								Void result) {
																																							adminService.deleteProjektmarktplatz(selectedObject, new AsyncCallback<Void>(){

																																								@Override
																																								public void onFailure(
																																										Throwable caught) {
																																									// TODO Auto-generated method stub
																																									
																																								}

																																								@Override
																																								public void onSuccess(
																																										Void result) {
																																									Window.alert("Der Projektmarktplatz wurde erfolgreich gelöscht");
																																									Showcase showcase = new ProjektmarktplatzBearbeitungsSeite(is);
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
																																}else{
																																for(final Bewertung bewe : result){
																																	bewe.setBewerbungs_ID(0);
																																	bewe.setBeteiligungs_ID(0);
																																	adminService.updateBewertung(bewe, new AsyncCallback<Bewertung>(){

																																		@Override
																																		public void onFailure(
																																				Throwable caught) {
																																			Window.alert("Löschen hat nicht funktioniert 6");
																																			
																																		}

																																		@Override
																																		public void onSuccess(
																																				Bewertung result) {
																																			adminService.deleteBewertung(result, new AsyncCallback<Void>(){

																																				@Override
																																				public void onFailure(
																																						Throwable caught) {
																																					Window.alert("Löschen hat nicht funktioniert 7");
																																					
																																				}

																																				@Override
																																				public void onSuccess(
																																						Void result) {
																																					adminService.deleteBewerbung(b, new AsyncCallback<Void>(){

																																						@Override
																																						public void onFailure(
																																								Throwable caught) {
																																							Window.alert("Löschen hat nicht funktioniert 8");
																																							
																																						}

																																						@Override
																																						public void onSuccess(
																																								Void result) {
																																							adminService.deleteAusschreibung(a, new AsyncCallback<Void>(){

																																								@Override
																																								public void onFailure(
																																										Throwable caught) {
																																									Window.alert("Löschen hat icht funktioniert 9");
																																									
																																								}

																																								@Override
																																								public void onSuccess(
																																										Void result) {
																																									adminService.getBeteiligungByProjekt(p.getID(), new AsyncCallback<Vector<Beteiligung>>(){

																																										@Override
																																										public void onFailure(
																																												Throwable caught) {
																																											// TODO Auto-generated method stub
																																											
																																										}

																																										@Override
																																										public void onSuccess(
																																												Vector<Beteiligung> result) {
																																											if(result.isEmpty()){
																																												adminService.deleteProjekt(p, new AsyncCallback<Void>(){

																																													@Override
																																													public void onFailure(
																																															Throwable caught) {
																																														// TODO Auto-generated method stub
																																														
																																													}

																																													@Override
																																													public void onSuccess(
																																															Void result) {
																																														adminService.deleteProjektmarktplatz(selectedObject, new AsyncCallback<Void>(){

																																															@Override
																																															public void onFailure(
																																																	Throwable caught) {
																																																// TODO Auto-generated method stub
																																																
																																															}

																																															@Override
																																															public void onSuccess(
																																																	Void result) {
																																																Window.alert("Der Projektmarktplatz wurde erfolgreich gelöscht");
																																																Showcase showcase = new ProjektmarktplatzBearbeitungsSeite(is);
																																																RootPanel.get("Details").clear();
																																																RootPanel.get("Details").add(showcase);
																																																
																																															}
																																															
																																														});
																																														
																																													}
																																													
																																												});
																																											}else{
																																												for(final Beteiligung bet : result){
																																													bet.setOrga_ID(0);
																																													bet.setProjekt_ID(0);
																																													adminService.updateBeteiligung(bet, new AsyncCallback<Beteiligung>(){

																																														@Override
																																														public void onFailure(
																																																Throwable caught) {
																																															// TODO Auto-generated method stub
																																															
																																														}

																																														@Override
																																														public void onSuccess(
																																																Beteiligung result) {
																																															adminService.delete(bet, new AsyncCallback<Void>(){

																																																@Override
																																																public void onFailure(
																																																		Throwable caught) {
																																																	// TODO Auto-generated method stub
																																																	
																																																}

																																																@Override
																																																public void onSuccess(
																																																		Void result) {
																																																	adminService.deleteProjekt(p, new AsyncCallback<Void>(){

																																																		@Override
																																																		public void onFailure(
																																																				Throwable caught) {
																																																			// TODO Auto-generated method stub
																																																			
																																																		}

																																																		@Override
																																																		public void onSuccess(
																																																				Void result) {
																																																			adminService.deleteProjektmarktplatz(selectedObject, new AsyncCallback<Void>(){

																																																				@Override
																																																				public void onFailure(
																																																						Throwable caught) {
																																																					// TODO Auto-generated method stub
																																																					
																																																				}

																																																				@Override
																																																				public void onSuccess(
																																																						Void result) {
																																																					Window.alert("Der Projektmarktplatz wurde erfolgreich gelöscht");
																																																					Showcase showcase = new ProjektmarktplatzBearbeitungsSeite(is);
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
																																											}
																																											
																																										}
																																										
																																									});
																																									
//																																									
																																									
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
																	
																}
																}
																
																 
															 });
															 
															 
															 
															 
														
													}
													
												});
												
											}
											
										}
										
									});
								}
								
							}
							 
						 });

						 
						 
						 
						 
						 

					}
	}
			});
		 }
		 


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
