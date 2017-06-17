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
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Label;

import de.hdm.ITProjekt.client.gui.Homeseite;
import de.hdm.ITProjekt.client.gui.IdentitySelection;
import de.hdm.ITProjekt.client.gui.Projekte;
import de.hdm.ITProjekt.client.gui.ProjektmarktplatzSeite;
import de.hdm.ITProjekt.client.gui.RegistrierungsForm;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatz;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;
import de.hdm.ITProjekt.shared.LoginService;
import de.hdm.ITProjekt.shared.LoginServiceAsync;
import de.hdm.ITProjekt.shared.bo.Person;

public class IT_Projekt_SS17 implements EntryPoint {
		
	 private LogInInfo loginInfo = null;
	 private VerticalPanel loginPanel = new VerticalPanel();
	 private Label loginLabel = new Label("Bitte melden Sie sich mit Ihrem Google-Account an.");
	 private Anchor signInLink = new Anchor ("Sign In");
	 private Anchor signOutLink = new Anchor ("Sign Out");
	 
	  private HorizontalPanel addPanel = new HorizontalPanel();
	  private VerticalPanel mainPanel = new VerticalPanel();
	  private Person p1 = new Person();
	  
	  private Person personobject = new Person();

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
		  
		  LoginServiceAsync loginService = GWT.create(LoginService.class);
		  
		  loginService.login(GWT.getHostPageBaseURL(), new AsyncCallback<LogInInfo>()	{
			  public void onFailure(Throwable Error) {
				  Window.alert("Fehler Login");
				  
			  }
			  public void onSuccess(LogInInfo result){
				  loginInfo = result;
				  
				  if(loginInfo.isLoggedIn()){
					
					  bereitsEingeloggt();
					  
				  } else {
					  nichtEingeloggt();
					  
				  }
			  }
			  });
	  }
		  
		  
		 private void loadLogin(){
			  
		  }
		 
		 private void nichtEingeloggt(){
			 signInLink.setHref(loginInfo.getLoginUrl());
			  loginPanel.add(loginLabel);
			  loginPanel.add(signInLink);  
			  RootPanel.get("login").add(loginPanel);
			 
		 }
		 private void bereitsEingeloggt(){
			 ((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
			 if (adminService == null) {
		      adminService = GWT.create(AdministrationProjektmarktplatz.class);
		    }
	
			  adminService.getPersonbyID(1, new getPersonFurMenubar());
		
		 }

//		 
		 private class getPersonFurMenubar implements AsyncCallback<Person>{

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler beim Laden der Daten");
			}

			@Override
			public void onSuccess(Person result) {
				  Showcase showcase = new Homeseite();
				  Menubar mb = new Menubar(result);
				  signOutLink.setHref(loginInfo.getLogoutUrl());//
				  mainPanel.add(addPanel);
				  mainPanel.add(showcase);
				  RootPanel.get("idendity").add(new IdentitySelection(result, mb));
				  RootPanel.get("login").add(signOutLink);
				  RootPanel.get("Details").add(mainPanel);
				  RootPanel.get("Navigator").add(new Menubar(result));
				  RootPanel.get("Header").add(mb.getIdSelection());
			}
			 
		 }

		  private class getAllPerson implements AsyncCallback <Vector<Person>>{

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler beim Laden der Person aus der Datenbank");
			}

			@Override
			public void onSuccess(Vector<Person> result) {
				for (int i = 0 ; i<= result.size(); i++){
				if(loginInfo.getEmailAddress() == result.elementAt(i).getEmail()){
					Window.alert("AHSDHASDHUAD");
//					bereitsEingeloggt();
						}
				else{
					Showcase showcase = new RegistrierungsForm();
					RootPanel.get("Details").add(showcase);
					
				}
					}
				}  
		  	}


		  
//		    /*
//		     * Wir bereiten nun die Erstellung eines bescheidenen Navigators vor, der
//		     * einige Schaltfl�chen (Buttons) f�r die Ausf�hrung von Unterprogrammen
//		     * enthalten soll.
//		     * 
//		     * Die jeweils ausgef�hrten Unterprogramme sind Demonstratoren
//		     * exemplarischer Anwendungsf�lle des Systems. Auf eine professionelle
//		     * Gestaltung der Benutzungsschnittstelle wurde bewusst verzichtet, um den
//		     * Blick nicht von den wesentlichen Funktionen abzulenken. Eine
//		     * exemplarische GUI-Realisierung findet sich separat.
//		     * 
//		     * Die Demonstratoren werden nachfolgend als Showcase bezeichnet. Aus diesem
//		     * Grund existiert auch eine Basisklasse f�r s�mtliche Showcase-Klassen
//		     * namens Showcase.
//		     */
//
//		    /*
//		     * Der Navigator ist als einspaltige Aneinanderreihung von Buttons
//		     * realisiert. Daher bietet sich ein VerticalPanel als Container an.
//		     */
//		    VerticalPanel navPanel = new VerticalPanel();
//
//		    /*
//		     * Das VerticalPanel wird einem DIV-Element namens "Navigator" in der
//		     * zugeh�rigen HTML-Datei zugewiesen und erh�lt so seinen Darstellungsort.
//		     */
//		    RootPanel.get("Navigator").add(navPanel);
//
//		    /*
//		     * Ab hier bauen wir sukzessive den Navigator mit seinen Buttons aus.
//		     */
//
//		    /*
//		     * Neues Button Widget erzeugen und eine Beschriftung festlegen.
//		     */
//		    final Button goToProjektmarktplatz = new Button("Projektmarktplatz");
//
//		    /*
//		     * Unter welchem Namen k�nnen wir den Button durch die CSS-Datei des
//		     * Projekts formatieren?
//		     */
//		     goToProjektmarktplatz.setStylePrimaryName("gotoprojektmarktplatz-menubutton");
//
//		    /*
//		     * Hinzuf�gen des Buttons zum VerticalPanel.
//		     */
//		    navPanel.add(goToProjektmarktplatz);
//		    
//		    goToProjektmarktplatz.addClickHandler(new ClickHandler() {
//
//				@Override
//				public void onClick(ClickEvent event) {
//					/*
//			         * Showcase instantiieren.
//			         */
//			        Showcase showcase = new ProjektmarktplatzSeite();
//
//			        /*
//			         * F�r die Ausgaben haben wir ein separates DIV-Element namens "Details"
//			         * in die zugeh�rige HTML-Datei eingef�gt. Bevor wir den neuen Showcase
//			         * dort einbetten, l�schen wir vorsichtshalber s�mtliche bisherigen
//			         * Elemente dieses DIV.
//			         */
//			        
//			        RootPanel.get("Details").clear();
//			        RootPanel.get("Details").add(showcase);
//				}
//			      
//		    });
//		    
//}
	  }


