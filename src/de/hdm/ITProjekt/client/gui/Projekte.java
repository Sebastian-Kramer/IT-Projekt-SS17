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
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;

import de.hdm.ITProjekt.client.gui.ProjektmarktplatzSeite;
import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.client.Showcase;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatz;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;
import de.hdm.ITProjekt.shared.bo.Ausschreibung;
import de.hdm.ITProjekt.shared.bo.Person;
import de.hdm.ITProjekt.shared.bo.Projekt;
import de.hdm.ITProjekt.shared.bo.Projektmarktplatz;

public class Projekte extends Showcase {

	AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
	
	CellTable<Projekt> ct_alleProjekte = new CellTable<Projekt>();
	CellTable<HybridProjektPerson> ct_projekte = new CellTable<HybridProjektPerson>();
	Vector<HybridProjektPerson> projekte = new Vector<HybridProjektPerson>();

	
	private TextBox projektbox = new TextBox();
	HorizontalPanel hpanel_projekte = new HorizontalPanel();
	VerticalPanel vpanel_projekte = new VerticalPanel();
	
	Button add_projekt = new Button("Projekt Hinzufügen");
	Button delete_projekt = new Button("Projekt Löschen");
	Button show_projekt = new Button("Projekt anzeigen");
	
	final SingleSelectionModel<Projekt> ssm_projekt = new SingleSelectionModel<Projekt>();
	
	private Projekt projekt;

	 
	public Projekte(){
		
	}
	
	private Person person;
	private Projektmarktplatz selectedProjektmarktplatz;
	
	public Projekte(Projektmarktplatz selectedObject, Person person){
		this.selectedProjektmarktplatz = selectedObject;
		this.person = person;
	}



	@Override
	protected String getHeadlineText() {
		
		return "<h3> Das sind alle Projekte <h/3>";
	}

	@Override
	protected void run() {
		
				
		RootPanel.get("Details").setWidth("100%");
		ct_projekte.setWidth("100%", true);
		ct_alleProjekte.setSelectionModel(ssm_projekt);
		hpanel_projekte.add(show_projekt);
		hpanel_projekte.add(add_projekt);
		hpanel_projekte.add(delete_projekt);
		
		vpanel_projekte.add(ct_projekte);
		this.add(hpanel_projekte);
		this.add(vpanel_projekte);
		
		add_projekt.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				DialogBox dialogbox = new DialogBoxProjekte(selectedProjektmarktplatz, person);
				dialogbox.center();
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
					 Showcase showcase = new Projektseite(projekt, person);
					 RootPanel.get("Details").clear();
					 RootPanel.get("Details").add(showcase);
					 
				}
				
			}
			
		});
		
		delete_projekt.addClickHandler(new ClickHandler(){
			 
			@Override
			public void onClick(ClickEvent event) {
				// "selectedobject" sprich die angewÃ¤hlte Zeile in der Tabelle wird instanziiert
				Projekt selectedProjektObject = ssm_projekt.getSelectedObject();
				if (selectedProjektObject != null){
					((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
					 if (adminService == null) {
				      adminService = GWT.create(AdministrationProjektmarktplatz.class);
				    }
					 AsyncCallback<Projekt> callback = new AsyncCallback<Projekt>(){

						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
							Window.alert("Fehler beim Löschen");
							
						}

						@Override
						public void onSuccess(Projekt result) {
							Window.alert("Projekt wurde erfolgreich gelöscht");
							filltableprojekte();
							
						}
						};
						adminService.deleteProjekt(selectedProjektObject, callback);
				}
}
		});
		
//		ssm_projekt.addSelectionChangeHandler(new Handler(){
//
//			@Override
//			public void onSelectionChange(SelectionChangeEvent event) {
//				projekt = ssm_projekt.getSelectedObject();
//				Showcase showcase = new Projektseite(projekt, person);
//				RootPanel.get("Details").clear();
//				RootPanel.get("Details").add(showcase);
////				
//			}
//			
//		});
		
		Column<HybridProjektPerson, String> projektname =
				new Column<HybridProjektPerson, String>(new ClickableTextCell()){

					@Override
					public String getValue(HybridProjektPerson object) {
						// TODO Auto-generated method stub
						return object.getProjektname();
					}
		};
					
		Column<HybridProjektPerson, String>	startdatum =
				new Column<HybridProjektPerson, String>(new ClickableTextCell()){

					@Override
					public String getValue(HybridProjektPerson object) {
						// TODO Auto-generated method stub
						return object.getStartdatum().toString();
					}
			
		};
		
		Column<HybridProjektPerson, String> enddatum =
				new Column<HybridProjektPerson, String>(new ClickableTextCell()){

					@Override
					public String getValue(HybridProjektPerson object) {
						// TODO Auto-generated method stub
						return object.getEnddatum().toString();
					}
			
		};
		
		Column<HybridProjektPerson, String> projektleiter =
				new Column<HybridProjektPerson, String>(new ClickableTextCell()){

					@Override
					public String getValue(HybridProjektPerson object) {
						// TODO Auto-generated method stub
						return object.getProjektleiter();
					}
			
		};
			
		
//		Column<Projekt, String> projektname = 
//				    new Column<Projekt, String>(new ClickableTextCell())  {
//				    
//						@Override
//						public String getValue(Projekt object) {
//							// TODO Auto-generated method stub
//							
//							return object.getName();
//						}
//				    };
//				    
//		Column<Projekt, String> beschreibung = 
//					new Column<Projekt, String>(new ClickableTextCell())  {
//											    
//						  	@Override
//							public String getValue(Projekt object) {					
//							
//						  		return object.getBeschreibung();
//							}
//					};	
//				    
//				    
//		 Column<Projekt, String> startdatum = 
//				    new Column<Projekt, String>(new ClickableTextCell())  {
//						    
//						@Override
//						public String getValue(Projekt object) {
//						// TODO Auto-generated method stub
//									
//						return object.getStartdatum().toString();
//						}
//				    };		
//				    
//		  Column<Projekt, String> enddatum = 
//				    new Column<Projekt, String>(new ClickableTextCell())  {
//								    
//			  			@Override
//			  			public String getValue(Projekt object) {
//			  			// TODO Auto-generated method stub
//											
//			  			return object.getEnddatum().toString();
//			  			}
//		  			};			 

//		ct_alleProjekte.addColumn(projektname, "Projektname");		    
//		ct_alleProjekte.addColumn(startdatum, "Startdatum");	
//		ct_alleProjekte.addColumn(enddatum, "Enddatum");	
//		ct_alleProjekte.addColumn(beschreibung, "Beschreibung");
		ct_projekte.addColumn(projektname, "Projektname");
		ct_projekte.addColumn(startdatum, "Startdatum");
		ct_projekte.addColumn(enddatum, "Enddatum");
		ct_projekte.addColumn(projektleiter, "Projektleiter");
		
		ct_projekte.setRowData(0, projekte);
		ct_projekte.setRowCount(projekte.size(), true);
		
		((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
		 if (adminService == null) {
	      adminService = GWT.create(AdministrationProjektmarktplatz.class);
	    }
		 adminService.findByProjektmarktplatz(selectedProjektmarktplatz, new ProjekteAnzeigenCallback());
		
		
//		((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
//		 if (adminService == null) {
//	      adminService = GWT.create(AdministrationProjektmarktplatz.class);
//	    }
		
//		adminService.findByProjektmarktplatz(projektid, new getProjekteOfProjektmarktplatz());
//		adminService.getAllProjekte(new getProjekteOfProjektmarktplatz());
//		filltableprojekte();
//		deleteProjekt();
	
	
}
	
//	private void deleteProjekt(){
//
//		 delete_projekt.addClickHandler(new ClickHandler(){
//			 
//				@Override
//				public void onClick(ClickEvent event) {
//					// "selectedobject" sprich die angewÃ¤hlte Zeile in der Tabelle wird instanziiert
//					Projekt selectedProjektObject = ssm_projekt.getSelectedObject();
//					if (selectedProjektObject != null){
//						((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
//						 if (adminService == null) {
//					      adminService = GWT.create(AdministrationProjektmarktplatz.class);
//					    }
//						 AsyncCallback<Projekt> callback = new AsyncCallback<Projekt>(){
//	
//							@Override
//							public void onFailure(Throwable caught) {
//								// TODO Auto-generated method stub
//								Window.alert("Fehler beim Löschen");
//								
//							}
//	
//							@Override
//							public void onSuccess(Projekt result) {
//								Window.alert("Projekt wurde erfolgreich gelöscht");
//								filltableprojekte();
//								
//							}
//							};
//							adminService.deleteProjekt(selectedProjektObject, callback);
//					}
//	}
//			});
//		 }
	private void filltableprojekte(){
		
		((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
		 if (adminService == null) {
	      adminService = GWT.create(AdministrationProjektmarktplatz.class);
	    }
		
		 AsyncCallback<Vector<Projekt>> callback = new AsyncCallback<Vector<Projekt>>(){
			 
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler beim Laden der Daten aus der Datenbank");
			}

			@Override
			public void onSuccess(Vector<Projekt> result) {
				if (result != null){
				ct_alleProjekte.setRowData(0, result);
				ct_alleProjekte.setRowCount(result.size(), true);
				} else{
					Window.alert("Keine Projekte");
				}
			}
		 };
		adminService.findByProjektmarktplatz(selectedProjektmarktplatz, callback);

		 
//		 		 AsyncCallback<Vector<Projekt>> callback = new AsyncCallback<Vector<Projekt>>(){
//			 
//				@Override
//				public void onFailure(Throwable caught) {
//					Window.alert("Fehler beim Laden der Daten aus der Datenbank");
//				}
//
//				@Override
//				public void onSuccess(Vector<Projekt> result) {
//					Window.alert("Halloonsuccess");
//					ct_alleProjekte.setRowData(0, result);
//					ct_alleProjekte.setRowCount(result.size(), true);
//					
//				}
//			 };
//			adminService.getProjekteOf(projektid, callback);
//		
//		
//	}
//		class getProjekteOfProjektmarktplatz implements AsyncCallback<Vector<Projekt>>{
//
//			
//			@Override
//			public void onFailure(Throwable caught) {
//				// TODO Auto-generated method stub
//				Window.alert("Failed bro");
//				
//			}
//
//			@Override
//			public void onSuccess(Vector<Projekt> result) {
//				Window.alert("onsuccess");
//				ct_alleProjekte.setRowData(0, result);
//				ct_alleProjekte.setRowCount(result.size(), true);
//			}
//			
//		}
	}
	
	private class HybridProjektPerson{
		private int projekt_id;
		private String projektname;
		private Date startdatum;
		private Date enddatum;
		private String projektbezeichnung;
		private String projektleiter;
		private int projektmarktplatz_id;
		
		public int getProjekt_id() {
			return projekt_id;
		}
		
		public void setProjekt_id(int projekt_id) {
			this.projekt_id = projekt_id;
		}
		
		public String getProjektname() {
			return projektname;
		}
		
		public void setProjektname(String projektname) {
			this.projektname = projektname;
		}
		
		public Date getStartdatum() {
			return startdatum;
		}
		
		public void setStartdatum(Date startdatum) {
			this.startdatum = startdatum;
		}
		
		public Date getEnddatum() {
			return enddatum;
		}
		
		public void setEnddatum(Date enddatum) {
			this.enddatum = enddatum;
		}
		
		public String getProjektbezeichnung() {
			return projektbezeichnung;
		}
		
		public void setProjektbezeichnung(String projektbezeichnung) {
			this.projektbezeichnung = projektbezeichnung;
		}
		
		public String getProjektleiter() {
			return projektleiter;
		}
		
		public void setProjektleiter(String projektleiter) {
			this.projektleiter = projektleiter;
		}
	}
		
		private class ProjekteAnzeigenCallback implements AsyncCallback<Vector<Projekt>>{

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Schritt 1 ist fehlgeschlagen");
				
			}

			@Override
			public void onSuccess(Vector<Projekt> result) {
				
				for (int i=0;i< result.size();i++ ){
					final Projekt localProjekt = result.get(i);
					final HybridProjektPerson localHybrid = new HybridProjektPerson();
					
					ClientsideSettings.getpmpVerwaltung().getPersonbyID(result.get(i).getProjektleiter_ID(), new AsyncCallback<Person>(){
						
						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Schritt 1 fehlgeschlagen");
							
						}

						@Override
						public void onSuccess(Person result) {
							final Person p = result;
							localHybrid.setProjektleiter(p.getName());
							projekte.add(localHybrid);
							
							
						}
						
					});
						localHybrid.setProjekt_id(localProjekt.getID());
						localHybrid.setProjektname(localProjekt.getName());
						localHybrid.setEnddatum(localProjekt.getEnddatum());
						localHybrid.setStartdatum(localProjekt.getStartdatum());
						
						
				}
				
			}
			
		}

		
		
		
	}
	







