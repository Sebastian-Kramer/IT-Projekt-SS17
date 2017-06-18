package de.hdm.ITProjekt.client;

import java.util.Vector;
//import de.hdm.ITProjekt.server.LoginServiceImpl;
//import de.hdm.ITProjekt.shared.LoginService;
//import de.hdm.ITProjekt.shared.LoginServiceAsync;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;

import de.hdm.ITProjekt.client.gui.Homeseite;
import de.hdm.ITProjekt.client.gui.IdentitySelection;
import de.hdm.ITProjekt.client.gui.Projekte;
import de.hdm.ITProjekt.client.gui.ProjektmarktplatzSeite;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatz;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;
import de.hdm.ITProjekt.shared.LoginService;
import de.hdm.ITProjekt.shared.LoginServiceAsync;
import de.hdm.ITProjekt.shared.bo.Partnerprofil;
import de.hdm.ITProjekt.shared.bo.Person;
import de.hdm.ITProjekt.shared.bo.Team;
import de.hdm.ITProjekt.shared.bo.Unternehmen;

public class IT_Projekt_SS17 implements EntryPoint {
		
	 private LogInInfo loginInfo = null;
	 private VerticalPanel loginPanel = new VerticalPanel();
	 private HorizontalPanel horvorpanel = new HorizontalPanel();
	 private VerticalPanel verpanel = new VerticalPanel();
	 private VerticalPanel vorpanel = new VerticalPanel();
	 private Label gotogooglelabel = new Label("Sie haben noch kein Google Account?");
	 private Label loginLabel = new Label("Bitte melden Sie sich mit Ihrem Google-Account an.");
	 private Anchor signInLink = new Anchor ("Sign In");
	 private Anchor signOutLink = new Anchor ("Sign Out");
	 private Anchor goToGoogle = new Anchor ("Go to Google Sign In");
	 
	  private HorizontalPanel addPanel = new HorizontalPanel();
	  private VerticalPanel mainPanel = new VerticalPanel();
	  private Person p1 = new Person();
	  ;
	  private Button loginButton = new Button("Login");
	  private Button seiteVerlassen = new Button("Seite verlassen");
	 
	  private static AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
	  
	  
//	  private Button projektmarktplatz = new Button("Projektmarktplatz");

	  
	/**
	   * Da diese Klasse die Implementierung des Interface <code>EntryPoint</code>
	   * zusichert, benötigen wir eine Methode
	   * <code>public void onModuleLoad()</code>. Diese ist das GWT-Pendant der
	   * <code>main()</code>-Methode normaler Java-Applikationen.
	   */
	  @Override
	public void onModuleLoad() {
		  RootPanel.get("header-top").add(new Menuleiste());
		  
		  
		  LoginServiceAsync loginService = GWT.create(LoginService.class);
		  
		  loginService.login(GWT.getHostPageBaseURL(), new AsyncCallback<LogInInfo>()	{
			  public void onFailure(Throwable Error) {
				  Window.alert("Fehler Login" + Error.toString());
				  
			  }
			  @Override
				public void onSuccess(LogInInfo result) {
					loginInfo = result;
					if(loginInfo.isLoggedIn()){
						
						/**
						 * @return Vector mit allen Personen
						 */ 
						((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
						 if (adminService == null) {
						      adminService = GWT.create(AdministrationProjektmarktplatz.class);
						    }
						adminService.getAllPerson(new AsyncCallback<Vector<Person>>() {

							@Override
							public void onFailure(Throwable caught) {
								Window.alert("Login fehlgeschlagen!");
							}

							@Override
							public void onSuccess(Vector<Person> result) {
								boolean isUserRegistered = false;
								for (Person person : result) {
									/**
									 * Überprüfung ob User bereits registriert ist
									 */
									if(person.getEmail()==loginInfo.getEmailAddress()){
										isUserRegistered = true;
										/**
										 * Falls User registriert ist wird der Projektmarktplatz geladen
										 * @param id des jeweiligen Person-Objekts
										 */
										load(person);
										break;
									}
								}
								if(isUserRegistered==false){
									RootPanel.get("Details").clear();
									/**
									 * Falls User noch nicht registriert ist wird der User zu RegistrierenForm weitergeleitetj
									 */
									RootPanel.get("Details").add(new RegistrierungsForm());							
								}
							}
						});
						
					} else{ 
						nichtEingeloggt();
				  }
			  }
			  });
	  }
		  
	  		public void load(Person person){
	  			signOutLink.setHref(loginInfo.getLogoutUrl());
		  		Showcase showcase = new Homeseite();
		  		Menubar mb = new Menubar(person);
				signOutLink.setHref(loginInfo.getLogoutUrl());//
				mainPanel.add(addPanel);
				mainPanel.add(showcase);
				RootPanel.get("idendity").add(new IdentitySelection(person, mb));
				RootPanel.get("login").add(signOutLink);
				RootPanel.get("Details").add(mainPanel);
				RootPanel.get("Navigator").add(mb);
//				RootPanel.get("Header").add(mb.getIdSelection());
	  			
	  		}
		 private void nichtEingeloggt(){
//			 signInLink.setHref(loginInfo.getLoginUrl());
//			  loginPanel.add(loginLabel);
//			  loginPanel.add(signInLink);  
//			  RootPanel.get("login").add(loginPanel);  
				loginPanel.add(loginLabel);
				loginPanel.add(loginButton);
				loginPanel.add(goToGoogle);
				goToGoogle.setHref("https://accounts.google.com/SignUp?hl=de");
				signInLink.setHref(loginInfo.getLoginUrl());
				goToGoogle.setStylePrimaryName("googlesignin");
				
				verpanel.add(gotogooglelabel);
				verpanel.add(goToGoogle);
				verpanel.setSpacing(10);
				
				vorpanel.add(loginPanel);
				vorpanel.add(loginButton);
				vorpanel.setSpacing(10);
				horvorpanel.add(vorpanel);
				horvorpanel.add(verpanel);
				
				RootPanel.get("Details").add(horvorpanel);
				
				loginButton.setWidth("150px");
				loginButton.setStylePrimaryName("login-btn");
				
			
//				google.addClickHandler(new ClickHandler() {
//					
//					@Override
//					public void onClick(ClickEvent event) {
//						goToGoogle.getHref();
//					}
//				});
				loginButton.addClickHandler(new ClickHandler() {
					
					
					
					@Override
					public void onClick(ClickEvent event) {
						Window.open(signInLink.getHref(), "_self",
								"");	
					}
				});
		 }


		  private class RegistrierungsForm extends Showcase{

			
				private AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
				
				private VerticalPanel vpanel_registrierung = new VerticalPanel();
				private HorizontalPanel hpanel_registrierung = new HorizontalPanel();
				
				private FlexTable form = new FlexTable();
				
				private Button abbrechen = new Button("Abbrechen");
				private Button bestaetigen = new Button("Konto Erstellen");
				
				private ListBox anredeListbox = new ListBox();
				private ListBox unternehmenListbox = new ListBox();
				private ListBox teamListbox = new ListBox();
				
				private Label anrede = new Label("Anrede");
				private Label vorname = new Label("Vorname");
				private Label nachname = new Label("Nachname");
				private Label strasse = new Label("Straße");
				private Label hausnr = new Label("Hausnummer");
				private Label plz = new Label("Postleitzahl");
				private Label ort = new Label("Ort");
				private Label emailLabel = new Label("Google-Mail");
				
				private TextBox vnameBox = new TextBox();
				private TextBox nnameBox = new TextBox();
				private TextBox strasseBox = new TextBox();
				private TextBox hausnrBox = new TextBox();
				private TextBox plzBox = new TextBox();
				private TextBox ortBox = new TextBox();
				private TextBox emailBox = new TextBox();

				
				private Partnerprofil partnerprofil = new Partnerprofil();
				private Team team = new Team();
				private Unternehmen unternehmen = new Unternehmen();
				private Person person = new Person();
				
				@Override
				protected String getHeadlineText() {
					// TODO Auto-generated method stub
					return "<h1> Registrierung</h1>";
				}

				@Override
				protected void run() {
					emailBox.setText(loginInfo.getEmailAddress());
					emailBox.setReadOnly(true);
					plzBox.setMaxLength(5);
					
					anredeListbox.addItem("Herr");
					anredeListbox.addItem("Frau");
					
					form.setWidget(0, 1, emailBox);
					form.setWidget(0, 0, emailLabel);
					
					form.setWidget(1,  1, anredeListbox);
					form.setWidget(1, 0, anrede);
					
					form.setWidget(2,  1, vnameBox);
					form.setWidget(2, 0, vorname);
					
					form.setWidget(3,  1, nnameBox);
					form.setWidget(3, 0, nachname);
					
					form.setWidget(4,  1, strasseBox);
					form.setWidget(4, 0, strasse);
					
					form.setWidget(5,  1, hausnrBox);
					form.setWidget(5, 0, hausnr);
					
					form.setWidget(6,  1, plzBox);
					form.setWidget(6, 0, plz);
					
					form.setWidget(7,  1, ortBox);
					form.setWidget(7, 0, ort);
					
//					form.setWidget(8, 0, abbrechen);
					form.setWidget(8, 1, bestaetigen);
					
					vpanel_registrierung.add(form);
					this.add(vpanel_registrierung);
					
				
//					abbrechen.addClickHandler(new ClickHandler() {
//						
//						@Override
//						public void onClick(ClickEvent event) {
//							}
//					});
					
					bestaetigen.addClickHandler(new ClickHandler() {

						@Override
						public void onClick(ClickEvent event) {
							try {
								Integer.parseInt(plzBox.getText());
								((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
								 if (adminService == null) {
								      adminService = GWT.create(AdministrationProjektmarktplatz.class);
								    }
								adminService.createPartnerprofil(new PersonInDB());
							} catch (Exception e) {
								Window.alert("PLZ muss eine Zahl sein!");
							}
							
						}
						
					
					});
				
				}
				private class PersonInDB implements AsyncCallback <Partnerprofil>{

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(Partnerprofil result) {
						int plzint = 0;
						int hausnrint = 0;
						hausnrint = Integer.parseInt(hausnrBox.getText());
						if(plzBox.getText().isEmpty()==false){
							plzint=Integer.parseInt(plzBox.getText());
						}
						((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
						 if (adminService == null) {
						      adminService = GWT.create(AdministrationProjektmarktplatz.class);
						    }
						adminService.createPerson(emailBox.getText(), vnameBox.getText(), nnameBox.getText(), 
								anredeListbox.getSelectedItemText(), strasseBox.getText(), hausnrint, plzint, 
								ortBox.getText(), result.getID(), new Integer(0), new Integer(0), new AsyncCallback<Person>() {

									@Override
									public void onFailure(Throwable caught) {
										Window.alert("Fehler beim Erstellen eines neuen Users");
									}

									@Override
									public void onSuccess(Person result) {
										RootPanel.get("Details").clear();
										load(result);
									}
								});
					}
				}
				
			}
		
		
	}
			
			
		
		
					
					
				
				
					


