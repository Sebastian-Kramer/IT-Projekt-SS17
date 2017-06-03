package de.hdm.ITProjekt.client;

import de.hdm.ITProjekt.server.LoginServiceImpl;
import de.hdm.ITProjekt.shared.LoginService;
import de.hdm.ITProjekt.shared.LoginServiceAsync;

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
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Label;

import de.hdm.ITProjekt.client.gui.Homeseite;
import de.hdm.ITProjekt.client.gui.ProjektmarktplatzSeite;

public class IT_Projekt_SS17 implements EntryPoint {
		
	 private LogInInfo loginInfo = null;
	 private VerticalPanel loginPanel = new VerticalPanel();
	 private Label loginLabel = new Label("Bitte melden Sie sich mit Ihrem Google-Account an.");
	 private Anchor signInLink = new Anchor ("Sign In");
	 private Anchor signOutLink = new Anchor ("Sign Out");
	 
	  private HorizontalPanel addPanel = new HorizontalPanel();
	  private VerticalPanel mainPanel = new VerticalPanel();
	  private Button projektmarktplatz = new Button("Projektmarktplatz");

	  /**
	   * Da diese Klasse die Implementierung des Interface <code>EntryPoint</code>
	   * zusichert, benÃ¶tigen wir eine Methode
	   * <code>public void onModuleLoad()</code>. Diese ist das GWT-Pendant der
	   * <code>main()</code>-Methode normaler Java-Applikationen.
	   */
	  @Override
	public void onModuleLoad() {
		  loadIT_Projekt_SS17();
	  }
		 /* LoginServiceAsync loginService = GWT.create(LoginService.class);
		  
		  loginService.login(GWT.getHostPageBaseURL(), new AsyncCallback<LogInInfo>()	{
			  public void onFailure(Throwable Error) {
				  Window.alert("Fehler Login");
				  
			  }
			  public void onSuccess(LogInInfo result){
				  loginInfo = result;
				  if(loginInfo.isLoggedIn()){
					  loadIT_Projekt_SS17();
					  
				  } else {
					  loadLogin();
				  }
			  }
			  });
	  }
		  */ 
		  /*
		 private void loadLogin(){
			  signInLink.setHref(loginInfo.getLoginUrl());
			  loginPanel.add(loginLabel);
			  loginPanel.add(signInLink);
			  RootPanel.get("Details").add(loginPanel);
		  }
		  */
		  private void loadIT_Projekt_SS17(){
			  Homeseite startseite = new Homeseite();
			 // signOutLink.setHref(loginInfo.getLogoutUrl());//
			  addPanel.add(projektmarktplatz);
			  mainPanel.add(addPanel);
			  mainPanel.add(startseite);
			 // mainPanel.add(signOutLink);;//
			  RootPanel.get("Details").add(mainPanel);
			  
			  
//		 
			  projektmarktplatz.addClickHandler(new ClickHandler() {
			      @Override
					public void onClick(ClickEvent event) {
			    	  Showcase showcase = new ProjektmarktplatzSeite();
			    	  
			    	  RootPanel.get("Details").clear();
			    	  RootPanel.get("Details").add(showcase);
				      }
				    });;
			  
			  } 
		  
	  


		  
//		    /*
//		     * Wir bereiten nun die Erstellung eines bescheidenen Navigators vor, der
//		     * einige Schaltflächen (Buttons) für die Ausführung von Unterprogrammen
//		     * enthalten soll.
//		     * 
//		     * Die jeweils ausgeführten Unterprogramme sind Demonstratoren
//		     * exemplarischer Anwendungsfälle des Systems. Auf eine professionelle
//		     * Gestaltung der Benutzungsschnittstelle wurde bewusst verzichtet, um den
//		     * Blick nicht von den wesentlichen Funktionen abzulenken. Eine
//		     * exemplarische GUI-Realisierung findet sich separat.
//		     * 
//		     * Die Demonstratoren werden nachfolgend als Showcase bezeichnet. Aus diesem
//		     * Grund existiert auch eine Basisklasse für sämtliche Showcase-Klassen
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
//		     * zugehörigen HTML-Datei zugewiesen und erhält so seinen Darstellungsort.
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
//		     * Unter welchem Namen können wir den Button durch die CSS-Datei des
//		     * Projekts formatieren?
//		     */
//		     goToProjektmarktplatz.setStylePrimaryName("gotoprojektmarktplatz-menubutton");
//
//		    /*
//		     * Hinzufügen des Buttons zum VerticalPanel.
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
//			         * Für die Ausgaben haben wir ein separates DIV-Element namens "Details"
//			         * in die zugehörige HTML-Datei eingefügt. Bevor wir den neuen Showcase
//			         * dort einbetten, löschen wir vorsichtshalber sämtliche bisherigen
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


