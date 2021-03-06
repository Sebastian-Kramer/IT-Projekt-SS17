package de.hdm.ITProjekt.client.gui;

import java.util.Vector;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.ClickableTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
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
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;

import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.client.Menubar;
import de.hdm.ITProjekt.client.Showcase;
import de.hdm.ITProjekt.server.AdministrationProjektmarktplatzImpl;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatz;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;
import de.hdm.ITProjekt.shared.bo.Bewerbung;
import de.hdm.ITProjekt.shared.bo.Eigenschaft;
import de.hdm.ITProjekt.shared.bo.Partnerprofil;
import de.hdm.ITProjekt.shared.bo.Person;
import de.hdm.ITProjekt.shared.bo.Projekt;
import de.hdm.ITProjekt.shared.bo.Team;
import de.hdm.ITProjekt.shared.bo.Unternehmen;

public class MeinProfilAnzeigen extends Showcase{
	
	private static ClickHandler currentClickHandler = null;
	private static ClickEvent currentClickEvent = null;
	
	private ButtonCell bearbeitenButton = new ButtonCell();
	private ButtonCell loeschenButton = new ButtonCell();
	
	private IdentitySelection is = null;
	private Menubar mb = null;

	public MeinProfilAnzeigen(IdentitySelection is){
		this.is = is;
	}
	//Festlegen der Variabeln, um VerticalPanel und und die Flextables anzulegen
	private VerticalPanel vpanel = new VerticalPanel();
	private VerticalPanel eigeneDaten = new VerticalPanel();
	private VerticalPanel partnerprofilDaten = new VerticalPanel();
	private VerticalPanel blabla = new VerticalPanel();
	private HorizontalPanel hpanel = new HorizontalPanel();
	private HorizontalPanel buttonPartnerprofilPanel = new HorizontalPanel();
	
	CellTable<Eigenschaft> pe_alleEigenschaften = new CellTable<Eigenschaft>();
	
	final SingleSelectionModel<Eigenschaft> ssm_alleEigenschaften = new SingleSelectionModel<Eigenschaft>();
	
	private FlexTable form = new FlexTable();
	private FlexTable pe_form = new FlexTable();
	private FlexTable pe_buttonPanel = new FlexTable();
	private FlexTable ft_buttonPanel = new FlexTable();
	private static DialogBox db_team = new DialogBox();
	private static DialogBox db_unternehmen = new DialogBox();
	private Button bearbeiten = new Button("Bearbeiten");
	private Button speichern = new Button("Speichern");
	private Button abbrechen = new Button("Abbrechen");
	private Button newTeam = new Button("Team hinzufügen");
	private Button newUN = new Button("Unternehmen hinzufügen");
	private Button eigenschaften = new Button("Eigenschaften hinzufügen");
	
	private Anchor team_suchen = new Anchor("Team Suchen");
	private Button team_bearbeiten = new Button("Bearbeiten");
	private Button team_speichern = new Button("Speichern");
	private Button team_abbrechen = new Button("Abbrechen");
	
	private Anchor unternehmen_suchen = new Anchor("Suchen");
	private Button unternehmen_bearbeiten = new Button("Bearbeiten");
	private Button unternehmen_speichern = new Button("Speichern");
	private Button unternehmen_abbrechen = new Button("Abbrechen");
	
	//Erstellen der Text- bzw. ListBoxen
	private ListBox anredeListBox = new ListBox();
	private TextBox anredeBox = new TextBox();
	private TextBox vnameBox = new TextBox();
	private TextBox nnameBox = new TextBox();
	private TextBox strasseBox = new TextBox();
	private TextBox hausnrBox = new TextBox();
	private TextBox plzBox = new TextBox();
	private TextBox ortBox = new TextBox();
	private TextBox emailBox = new TextBox();
	
	//Erstellen der Labels
	private Label daten = new Label("Meine Daten:");
	private Label email = new Label("E-Mail");
	private Label anrede = new Label("Anrede");
	private Label vorname = new Label("Vorname");
	private Label nachname = new Label("Nachname");
	private Label straße = new Label("Straße");
	private Label hausnr = new Label("Hausnummer");
	private Label plz = new Label("Postleitzahl");
	private Label ort = new Label("Ort");
	
	private FlexTable un_flextable = new FlexTable();
	
	private TextBox unternehmenNameBox = new TextBox();
	private TextBox unternehmenStrasseBox = new TextBox();
	private TextBox unternehmenHausnummerBox = new TextBox();
	private TextBox unternehmenPlzBox = new TextBox();
	private TextBox unternehmenOrtBox = new TextBox();
	
	private Label unternehmen = new Label("Unternehmen:");
	private Label unternehmenNameLabel = new Label("Firmenname");
	private Label unternehmenStrasseLabel = new Label("Straße");
	private Label unternehmenHausnummerLabel = new Label("Nummer");
	private Label unternehmenPlzLabel = new Label("PLZ");
	private Label unternehmenOrtLabel = new Label("Ort");
	
	private FlexTable team_flextable = new FlexTable();
	
	private TextBox teamstrasse = new TextBox();
	private TextBox teamhausnummer = new TextBox();
	private TextBox teamplz = new TextBox();
	private TextBox teamort = new TextBox();
	private TextBox teamname = new TextBox();
	
	private Label team = new Label("Team:");
	private Label labelteamname = new Label ("Teamname");
	private Label labelteamplz = new Label ("PLZ");
	private Label labelteamort = new Label("Ort");
	private Label labelteamstrasse = new Label("Straße");
	private Label labelteamhausnummer = new Label("Hausnummer");

	private Anchor profilloeschen = new Anchor ("Profil Löschen");
	private Anchor klickensiehier = new Anchor("Unternehmen/Team Löschen/Erstellen");
	
	AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
	
	private Person user = new Person();
	private Eigenschaft eigen = new Eigenschaft();
	private Eigenschaft selectedObject_alleEigenschaften = new Eigenschaft();
	private Unternehmen unternehmenobject = new Unternehmen();
	private Team teamobject = new Team();
	private Team teamupdate = new Team();
	private Anchor teamaustreten = new Anchor("Verlassen");
		@Override
		protected String getHeadlineText() {
			return "<h2>Mein Profil</h2>";
		}
		@Override
		protected void run() {
			
			unternehmen_suchen.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					if(is.getTeamOfUser() == null){
					DialogBox dbox = new DialogBoxUnternehmenSuchen(is);
					dbox.center();
				}else{
					Window.alert("Bitte löschen Sie zu erst Ihr Team");
				}
			}
			});
			
			teamaustreten.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					if (is.getTeamOfUser() == null){
					is.getUser().getID();
					is.getUser().getisAdmin();
					is.getUser().getAnrede();
					is.getUser().getEmail();
					is.getUser().getHausnummer();
					is.getUser().getName();
					is.getUser().getOrt();
					is.getUser().getPartnerprofil_ID();
					is.getUser().getPlz();
					is.getUser().getStrasse();
					is.getUser().setUN_ID(0);
					adminService.updatePerson(is.getUser(), new AsyncCallback<Person>(){

						@Override
						public void onFailure(Throwable caught) {
						}

						@Override
						public void onSuccess(Person result) {
							Window.alert("Sie haben Ihr Unternehmen verlassen");
							RootPanel.get("idendity").clear();
							RootPanel.get("Navigator").clear();
							RootPanel.get("Details").clear();
							Showcase showcase = new MeinProfilAnzeigen(is);
							Menubar mb = new Menubar(is.getUser());
							mb.setIdSelection(is);
							RootPanel.get("Details").add(showcase);
							RootPanel.get("idendity").add(new IdentitySelection(is.getUser(), mb));
							RootPanel.get("Navigator").add(mb);
						}
						
					});
				}else{
					Window.alert("Sie müssen zu erst Ihr Team löschen!");
				}
				}
			});
			
			
			profilloeschen.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
				if (is.getUser().getUN_ID() == null && is.getUser().getTeam_ID() == null){
					DialogBox dbox = new DialogBoxProfilLoeschen(is.getUser());
					dbox.center();
					
				}else{
					Window.alert("Team und Unternehmen zu erst löschen");
				}
				}
			});
			
			klickensiehier.addClickHandler(new ClickHandler() {
						
						@Override
						public void onClick(ClickEvent event) {
							DialogBox dialogbox = new DialogBoxNavigationTeamUnternehmen(is);
							dialogbox.center();
						}
					});
			((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
			 if (adminService == null) {
		      adminService = GWT.create(AdministrationProjektmarktplatz.class);
		    }
			
			adminService.getPersonbyID(is.getUser().getID(), new getPersonausDB());
			adminService.getPartnerprofilOfOrganisationseinheit(is.getSelectedIdentityAsObject(), new AsyncCallback<Partnerprofil>(){

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onSuccess(Partnerprofil result) {
					adminService.getAllEigenschaftofPerson(result, new AsyncCallback<Vector<Eigenschaft>>(){

						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void onSuccess(Vector<Eigenschaft> result) {
							pe_alleEigenschaften.setRowData(0, result);
							pe_alleEigenschaften.setRowCount(result.size(), true);
						}
						
					});
				}
				
			});
			if(is.getUser().getTeam_ID() != null){
			adminService.getTeamByID(is.getUser().getTeam_ID(), new getTeamAusDBbyPerson());
			}
			
			if(is.getUser().getUN_ID() != null){
			adminService.getUnByID(is.getUser().getUN_ID(), new getUnternehmenAusDBbyPerson());
			}
			
			pe_alleEigenschaften.setWidth("100%");
			
			emailBox.setReadOnly(true);
			vnameBox.setReadOnly(true);
			nnameBox.setReadOnly(true);
			anredeBox.setReadOnly(true);
			strasseBox.setReadOnly(true);
			hausnrBox.setReadOnly(true);
			plzBox.setReadOnly(true);
			ortBox.setReadOnly(true);

			teamstrasse.setReadOnly(true);
			teamhausnummer.setReadOnly(true);
			teamplz.setReadOnly(true);
			teamort.setReadOnly(true);
			teamname.setReadOnly(true);
			
			unternehmenNameBox.setReadOnly(true);
			unternehmenStrasseBox.setReadOnly(true);
			unternehmenHausnummerBox.setReadOnly(true);
			unternehmenPlzBox.setReadOnly(true);
			unternehmenOrtBox.setReadOnly(true);
			
			//Stylen der Buttons
			bearbeiten.setStylePrimaryName("myprofil-button");
			speichern.setStylePrimaryName("myprofil-button");
			abbrechen.setStylePrimaryName("myprofil-button");
			team_bearbeiten.setStylePrimaryName("myprofil-button");
			team_speichern.setStylePrimaryName("myprofil-button");
			team_abbrechen.setStylePrimaryName("myprofil-button");
			unternehmen_bearbeiten.setStylePrimaryName("myprofil-button");
			unternehmen_speichern.setStylePrimaryName("myprofil-button");
			unternehmen_abbrechen.setStylePrimaryName("myprofil-button");
			
			
			newTeam.setStylePrimaryName("myprofil-button");
			newUN.setStylePrimaryName("myprofil-button");
			eigenschaften.setStylePrimaryName("myprofil-button");
			
			
			anredeListBox.addItem("Herr");
			anredeListBox.addItem("Frau");

			
			
			//Legt den Abstand zwischen diesen Zellen fest. Parameter:Beabstandet den Zwischenzellenabstand in Pixeln			
			vpanel.setSpacing(8);
			
			form.setWidget(0, 0, daten);
			
			form.setWidget(1, 1, emailBox);
			form.setWidget(1, 0, email);
			
			form.setWidget(2,  1, anredeBox);
			form.setWidget(2, 0, anrede);
			
			form.setWidget(3,  1, vnameBox);
			form.setWidget(3, 0, vorname);
			
			form.setWidget(4,  1, nnameBox);
			form.setWidget(4, 0, nachname);
		
			form.setWidget(5,  1, strasseBox);
			form.setWidget(5, 0, straße);
			
			form.setWidget(6,  1, hausnrBox);
			form.setWidget(6, 0, hausnr);
			
			form.setWidget(7,  1, plzBox);
			form.setWidget(7, 0, plz);
			
			form.setWidget(8,  1, ortBox);
			form.setWidget(8, 0, ort);
			form.setCellSpacing(10);
		
			
			
			ft_buttonPanel.setWidget(0, 0, bearbeiten);
			ft_buttonPanel.setWidget(0, 1, speichern);
			ft_buttonPanel.setWidget(0, 2, abbrechen);

			
		
			unternehmen_abbrechen.setVisible(false);
			unternehmen_speichern.setVisible(false);
			team_abbrechen.setVisible(false);
			team_speichern.setVisible(false);
			speichern.setVisible(false);
			abbrechen.setVisible(false);

			
			un_flextable.setWidget(0, 0, unternehmen_suchen);
			un_flextable.setWidget(1, 0, teamaustreten);
			
			un_flextable.setWidget(2, 0, unternehmen_bearbeiten);
			un_flextable.setWidget(3, 0, unternehmen_speichern);
			un_flextable.setWidget(3, 1, unternehmen_abbrechen);
			
			un_flextable.setWidget(4, 0, unternehmen);
		
			un_flextable.setWidget(5, 1, unternehmenNameBox);
			un_flextable.setWidget(5, 0, unternehmenNameLabel);
			
			un_flextable.setWidget(6, 1, unternehmenStrasseBox);
			un_flextable.setWidget(6, 0, unternehmenStrasseLabel);
			
			un_flextable.setWidget(7, 1, unternehmenHausnummerBox);
			un_flextable.setWidget(7, 0, unternehmenHausnummerLabel);
			
			un_flextable.setWidget(8,  1, unternehmenPlzBox);
			un_flextable.setWidget(8, 0, unternehmenPlzLabel);
		
			un_flextable.setWidget(9,  1, unternehmenOrtBox);
			un_flextable.setWidget(9, 0, unternehmenOrtLabel);

		
			
			un_flextable.setCellSpacing(10);
			
			
			team_flextable.setWidget(0, 0, team_bearbeiten);
			team_flextable.setWidget(1, 0, team_speichern);
			team_flextable.setWidget(1, 1, team_abbrechen);
			
			team_flextable.setWidget(2, 0, team);
			

			team_flextable.setWidget(3,  1, teamname);
			team_flextable.setWidget(3, 0, labelteamname);
			
			team_flextable.setWidget(4, 1, teamstrasse);
			team_flextable.setWidget(4, 0, labelteamstrasse);
			
			team_flextable.setWidget(5,  1, teamhausnummer);
			team_flextable.setWidget(5, 0, labelteamhausnummer);
			
			team_flextable.setWidget(6,  1, teamplz);
			team_flextable.setWidget(6, 0, labelteamplz);
			
			team_flextable.setWidget(7,  1, teamort);
			team_flextable.setWidget(7, 0, labelteamort);
		
		
			team_flextable.setCellSpacing(10);
			
			vpanel.add(profilloeschen);
			vpanel.add(klickensiehier);
			vpanel.add(ft_buttonPanel);
			vpanel.add(form);
			vpanel.add(eigenschaften);
			vpanel.add(pe_alleEigenschaften);
			
			blabla.add(un_flextable);
			
			partnerprofilDaten.add(team_flextable);
			
			
			buttonPartnerprofilPanel.add(partnerprofilDaten);
			hpanel.add(vpanel);
			hpanel.add(blabla);
			hpanel.add(partnerprofilDaten);	
			
			this.add(hpanel);

			

			this.setSpacing(8);
			
		
		Column<Eigenschaft, String> name = 
				    new Column<Eigenschaft, String>(new ClickableTextCell())  {
				    
						@Override
						public String getValue(Eigenschaft object) {
							return object.getName();
						}
						    
		 };
		 Column<Eigenschaft, String> wert = 
				    new Column<Eigenschaft, String>(new ClickableTextCell())  {
				    
						@Override
						public String getValue(Eigenschaft object) {
							return object.getWert();
						}
						    
		 };
		 Column<Eigenschaft,String> bearbeitenCell =
				  new Column<Eigenschaft,String>(bearbeitenButton){

					@Override
					public String getValue(Eigenschaft object) {
						
						return "Bearbeiten";
					}
			  
		  };
		  Column<Eigenschaft,String> loeschenCell =
				  new Column<Eigenschaft,String>(loeschenButton){

					@Override
					public String getValue(Eigenschaft object) {
						
						return "Löschen";
					}
			  
		  };
		  
		  //
		  bearbeitenCell.setFieldUpdater(new FieldUpdater<Eigenschaft,String>(){

				@Override
				public void update(int index, Eigenschaft object, String value) {
					
				DialogBox dbeigenschaftaendern = new DialogBoxEigenschaftenAendern(is, object);
				dbeigenschaftaendern.center();
					
				}
				  
			  });
		 
		  loeschenCell.setFieldUpdater(new FieldUpdater<Eigenschaft,String>(){

				@Override
				public void update(int index, Eigenschaft object, String value) {
					
				DialogBox dbeigenschaftenloschen = new DialogBoxEigenschaftLoeschenAbfrage(object);
				dbeigenschaftenloschen.center();		
					
				}
				  
			  });
		 
		 
		 ssm_alleEigenschaften.addSelectionChangeHandler(new Handler(){
				
				public void onSelectionChange(final SelectionChangeEvent event) {
					
					selectedObject_alleEigenschaften = ssm_alleEigenschaften.getSelectedObject();
					if(selectedObject_alleEigenschaften != null){
						
						RootPanel.get("idendity").clear();
						RootPanel.get("Navigator").clear();
						Menubar mb = new Menubar(is.getUser());
						mb.setIdSelection(is);
						Showcase showcase= new EigenschaftenHinzufuegen(is);
						RootPanel.get("Details").clear();
						RootPanel.get("Details").add(showcase);
						RootPanel.get("idendity").add(new IdentitySelection(is.getUser(), mb));
						RootPanel.get("Navigator").add(mb);
				
			}else{
				Window.alert("Zum Bearbeiten muss eine Eigenschaft ausgewählt werden");
				}
			}
		});
		 
		 pe_alleEigenschaften.addColumn(name, "Bereich");
		 pe_alleEigenschaften.addColumn(wert, "Ausprägung der Eigenschaft");
		 pe_alleEigenschaften.addColumn(bearbeitenCell, " ");
		 pe_alleEigenschaften.addColumn(loeschenCell, " ");
	
		
		
		 
		unternehmen_bearbeiten.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if(is.getUnternehmenOfUser().getErstellerid() == is.getUser().getID()){
				unternehmenNameBox.setReadOnly(false);
				unternehmenStrasseBox.setReadOnly(false);
				unternehmenHausnummerBox.setReadOnly(false);
				unternehmenPlzBox.setReadOnly(false);
				unternehmenOrtBox.setReadOnly(false);
				
				unternehmen_bearbeiten.setVisible(false);
				unternehmen_speichern.setVisible(true);
				unternehmen_abbrechen.setVisible(true);
				}else{
					Window.alert("Sie sind nicht berechtigt Änderungen an diesem Unternehmen durchzuführen!");
				}
			}
		});	
		unternehmen_speichern.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
				 if (adminService == null) {
			      adminService = GWT.create(AdministrationProjektmarktplatz.class);
			    }
				 ClientsideSettings.getpmpVerwaltung().getUnByID(is.getUser().getUN_ID(), new AsyncCallback<Unternehmen>(){

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(Unternehmen result) {
						result.setHausnummer(Integer.parseInt(unternehmenHausnummerBox.getText()));
						result.setName(unternehmenNameBox.getText());
						result.setOrt(unternehmenOrtBox.getText());
						result.setPlz(Integer.parseInt(unternehmenPlzBox.getText()));
						result.setStrasse(unternehmenStrasseBox.getText());
						
						((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
						 if (adminService == null) {
					      adminService = GWT.create(AdministrationProjektmarktplatz.class);
					    }
						ClientsideSettings.getpmpVerwaltung().updateUnternehmen(result, new AsyncCallback<Unternehmen>(){

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub
								
							}

							@Override
							public void onSuccess(Unternehmen result) {	
							RootPanel.get("idendity").clear();
							RootPanel.get("Navigator").clear();
							RootPanel.get("Details").clear();
							Showcase showcase = new MeinProfilAnzeigen(is);
							Menubar mb = new Menubar(is.getUser());
							mb.setIdSelection(is);
							RootPanel.get("Details").add(showcase);
							RootPanel.get("idendity").add(new IdentitySelection(is.getUser(), mb));
							RootPanel.get("Navigator").add(mb);
					
							}
							
						});
						
					}
					});
			}
		});
		unternehmen_abbrechen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				RootPanel.get("idendity").clear();
				RootPanel.get("Navigator").clear();
				RootPanel.get("Details").clear();
				Showcase showcase = new MeinProfilAnzeigen(is);
				Menubar mb = new Menubar(is.getUser());
				mb.setIdSelection(is);
				RootPanel.get("Details").add(showcase);
				RootPanel.get("idendity").add(new IdentitySelection(is.getUser(), mb));
				RootPanel.get("Navigator").add(mb);
		
			}
		});
		team_speichern.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Window.alert("Nach clickhandler");
				((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
				 if (adminService == null) {
			      adminService = GWT.create(AdministrationProjektmarktplatz.class);
			    }
				 ClientsideSettings.getpmpVerwaltung().getTeamByID(is.getUser().getTeam_ID(), new AsyncCallback<Team>(){

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(Team result) {
						result.setHausnummer(Integer.parseInt(teamhausnummer.getText()));
						result.setName(teamname.getText());
						result.setOrt(teamort.getText());
						result.setPlz(Integer.parseInt(teamplz.getText()));
						result.setStrasse(teamstrasse.getText());
						((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
						 if (adminService == null) {
					      adminService = GWT.create(AdministrationProjektmarktplatz.class);
					    }
						ClientsideSettings.getpmpVerwaltung().updateTeam(result, new AsyncCallback<Team>(){

							@Override
							public void onFailure(Throwable caught) {
								Window.alert("Failure");
							}

							@Override
							public void onSuccess(Team result) {
								RootPanel.get("idendity").clear();
								RootPanel.get("Navigator").clear();
								RootPanel.get("Details").clear();
								Showcase showcase = new MeinProfilAnzeigen(is);
								Menubar mb = new Menubar(is.getUser());
								mb.setIdSelection(is);
								RootPanel.get("Details").add(showcase);
								RootPanel.get("idendity").add(new IdentitySelection(is.getUser(), mb));
								RootPanel.get("Navigator").add(mb);
						
							}
							
						});
					}
					 
				 });
			}
		});
		team_abbrechen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get("idendity").clear();
				RootPanel.get("Navigator").clear();
				RootPanel.get("Details").clear();
				Showcase showcase = new MeinProfilAnzeigen(is);
				Menubar mb = new Menubar(is.getUser());
				mb.setIdSelection(is);
				RootPanel.get("Details").add(showcase);
				RootPanel.get("idendity").add(new IdentitySelection(is.getUser(), mb));
				RootPanel.get("Navigator").add(mb);
		
			}
		});
		team_bearbeiten.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				teamstrasse.setReadOnly(false);
				teamhausnummer.setReadOnly(false);
				teamplz.setReadOnly(false);
				teamort.setReadOnly(false);
				teamname.setReadOnly(false);
				
				team_bearbeiten.setVisible(false);
				team_speichern.setVisible(true);
				team_abbrechen.setVisible(true);
			}
		});
		 
		 
		bearbeiten.addClickHandler(new ClickHandler(){
			
			public void onClick(ClickEvent event) {
				//Listbox anstelle von Textbox setzten
				form.setWidget(2, 1, anredeListBox);
				//ReadOnly auf false setzten
				emailBox.setReadOnly(false);
				vnameBox.setReadOnly(false);
				nnameBox.setReadOnly(false);
				anredeBox.setReadOnly(false);
				strasseBox.setReadOnly(false);
				hausnrBox.setReadOnly(false);
				plzBox.setReadOnly(false);
				ortBox.setReadOnly(false);
				
				bearbeiten.setVisible(false);
				speichern.setVisible(true);
				abbrechen.setVisible(true);
				
			}
		
		});
		eigenschaften.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Showcase showcase = new EigenschaftenHinzufuegen(is);
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
			}
		});
		
		team_abbrechen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get("idendity").clear();
				RootPanel.get("Navigator").clear();
				RootPanel.get("Details").clear();
				Showcase showcase = new MeinProfilAnzeigen(is);
				Menubar mb = new Menubar(is.getUser());
				mb.setIdSelection(is);
				RootPanel.get("Details").add(showcase);
				RootPanel.get("idendity").add(new IdentitySelection(is.getUser(), mb));
				RootPanel.get("Navigator").add(mb);
		
			}
		});
		unternehmen_abbrechen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get("idendity").clear();
				RootPanel.get("Navigator").clear();
				RootPanel.get("Details").clear();
				Showcase showcase = new MeinProfilAnzeigen(is);
				Menubar mb = new Menubar(is.getUser());
				mb.setIdSelection(is);
				RootPanel.get("Details").add(showcase);
				RootPanel.get("idendity").add(new IdentitySelection(is.getUser(), mb));
				RootPanel.get("Navigator").add(mb);
		;
			}
		});
		
		abbrechen.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				RootPanel.get("idendity").clear();
				RootPanel.get("Navigator").clear();
				RootPanel.get("Details").clear();
				Showcase showcase = new MeinProfilAnzeigen(is);
				Menubar mb = new Menubar(is.getUser());
				mb.setIdSelection(is);
				RootPanel.get("Details").add(showcase);
				RootPanel.get("idendity").add(new IdentitySelection(is.getUser(), mb));
				RootPanel.get("Navigator").add(mb);
		
			}
		});
		
		speichern.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
				 if (adminService == null) {
			      adminService = GWT.create(AdministrationProjektmarktplatz.class);
			    }
				 ClientsideSettings.getpmpVerwaltung().
					getPersonbyID(is.getUser().getID(), new ProfilBearbeitenCallback());
				
			}
		});
		
	
	}
		
	
		
		private class ProfilBearbeitenCallback implements AsyncCallback<Person>{

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Das Bearbeiten des Profils ist fehlgeschlagen. Bitte versuche es erneut.");
				
			}

			@Override
			public void onSuccess(Person result) {
				result.setAnrede(anredeListBox.getItemText(anredeListBox.getSelectedIndex()));
				result.setHausnummer(Integer.parseInt(hausnrBox.getText()));
				result.setName(nnameBox.getText());
				result.setOrt(ortBox.getText());
				result.setPlz(Integer.parseInt(plzBox.getText()));
				result.setStrasse(strasseBox.getText());
				result.setVorname(vnameBox.getText());
				((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
				 if (adminService == null) {
			      adminService = GWT.create(AdministrationProjektmarktplatz.class);
			    }
				ClientsideSettings.getpmpVerwaltung().savePerson(result, new PersonSpeichernCallback());
				
			}}
		
		private class PersonSpeichernCallback implements AsyncCallback<Void>{

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Beim Speichern der Person ist etwas schief gegangen");
				
			}

			@Override
			public void onSuccess(Void result) {
				RootPanel.get("idendity").clear();
				RootPanel.get("Navigator").clear();
				RootPanel.get("Details").clear();
				Showcase showcase = new MeinProfilAnzeigen(is);
				Menubar mb = new Menubar(is.getUser());
				mb.setIdSelection(is);
				RootPanel.get("Details").add(showcase);
				RootPanel.get("idendity").add(new IdentitySelection(is.getUser(), mb));
				RootPanel.get("Navigator").add(mb);
		
			}
			
		}
		
		private class getPersonausDB implements AsyncCallback<Person> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Daten der Person konnten nicht ausgelesen werden");
			
		}

		@Override
		public void onSuccess(Person result) {
			emailBox.setText(result.getEmail());
			vnameBox.setText(result.getVorname());
			nnameBox.setText(result.getName());
			anredeBox.setText(result.getAnrede());
			strasseBox.setText(result.getStrasse());
			hausnrBox.setText(Integer.toString(result.getHausnummer()));
			plzBox.setText(Integer.toString(result.getPlz()));
			ortBox.setText(result.getOrt());
			
			
			
		}	
	}
		private class getTeamAusDBbyPerson implements AsyncCallback<Team>{

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Team result) {
				if (result != null){
				
				teamstrasse.setReadOnly(true);
				teamhausnummer.setReadOnly(true);
				teamplz.setReadOnly(true);
				teamort.setReadOnly(true);
				teamname.setReadOnly(true);
				teamname.setText(result.getName());
				teamplz.setText(Integer.toString(result.getPlz()));
				teamort.setText(result.getOrt());
				teamstrasse.setText(result.getStrasse());
				teamhausnummer.setText(Integer.toString(result.getHausnummer()));
				}
				else{
					teamstrasse.setReadOnly(false);
					teamhausnummer.setReadOnly(false);
					teamplz.setReadOnly(false);
					teamort.setReadOnly(false);
					teamname.setReadOnly(false);
				}
				
				
				
			}
			
		}
//		private class 
		private class DialogBoxEigenschaftLoeschenAbfrage extends DialogBox{

			private VerticalPanel vpanel = new VerticalPanel();
			private HorizontalPanel hpanel = new HorizontalPanel();
			private Button ja = new Button("Ja");
			private Button abbrechen = new Button("Nein");
			
			private Label eigenschaftloschenfrage = new Label("Möchten Sie Ihre Eigenschaft löschen?.");

			AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
			
			private FlexTable ft_person = new FlexTable();
			
			public DialogBoxEigenschaftLoeschenAbfrage(final Eigenschaft e){
				
				ja.addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
										
				((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
				 if (adminService == null) {
			      adminService = GWT.create(AdministrationProjektmarktplatz.class);
			    }
				adminService.deleteEigenschaft(e, new AsyncCallback<Void>(){

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
						mb.setIdSelection(is);
						RootPanel.get("Details").add(showcase);
						RootPanel.get("idendity").add(new IdentitySelection(is.getUser(), mb));
						RootPanel.get("Navigator").add(mb);
				
					}
					
				});
			}
		});
				abbrechen.addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
						hide();
					}
				});
				this.setAnimationEnabled(false);
				this.setGlassEnabled(true);
				this.setText("Eigenschaft Löschen");
				ja.setStylePrimaryName("button");
				abbrechen.setStylePrimaryName("button");
				
				ft_person.setWidget(0, 0, eigenschaftloschenfrage);
				ft_person.setWidget(1, 0, ja);
				ft_person.setWidget(1, 1, abbrechen);
				
				vpanel.add(ft_person);
						
				this.add(vpanel);
			}
			
		}
		
		private class getUnternehmenAusDBbyPerson implements AsyncCallback<Unternehmen>{

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Unternehmen result) {
				if (result != null){
					
					unternehmenNameBox.setReadOnly(true);
					unternehmenStrasseBox.setReadOnly(true);
					unternehmenHausnummerBox.setReadOnly(true);
					unternehmenPlzBox.setReadOnly(true);
					unternehmenOrtBox.setReadOnly(true);
					unternehmenNameBox.setText(result.getName());
					unternehmenStrasseBox.setText(result.getStrasse());
					unternehmenHausnummerBox.setText(Integer.toString(result.getHausnummer()));
					unternehmenPlzBox.setText(Integer.toString(result.getPlz()));
					unternehmenOrtBox.setText(result.getOrt());
				}else {
					unternehmenNameBox.setReadOnly(false);
					unternehmenStrasseBox.setReadOnly(false);
					unternehmenHausnummerBox.setReadOnly(false);
					unternehmenPlzBox.setReadOnly(false);
					unternehmenOrtBox.setReadOnly(false);
				}
			
				
			}
			
		}
}
	

	
	
