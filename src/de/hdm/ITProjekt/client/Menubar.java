package de.hdm.ITProjekt.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.ITProjekt.client.gui.AGB;
import de.hdm.ITProjekt.client.gui.Homeseite;
import de.hdm.ITProjekt.client.gui.IdentitySelection;
import de.hdm.ITProjekt.client.gui.MeinProfilAnzeigen;
import de.hdm.ITProjekt.client.gui.MeineBewerbungenSeite;
import de.hdm.ITProjekt.client.gui.MeineProjekteAnzeigen;
import de.hdm.ITProjekt.client.gui.Projekte;
import de.hdm.ITProjekt.client.gui.ProjektmarktplatzSeite;
import de.hdm.ITProjekt.client.gui.StellenausschreibungenSeite;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;
import de.hdm.ITProjekt.shared.bo.Organisationseinheit;
import de.hdm.ITProjekt.shared.bo.Person;

public class Menubar extends StackPanel {
	
	
	// Clickhandler auf "null" setzen
	
	private static ClickHandler currentClickHandler = null;
	private static ClickEvent currentClickEvent = null;
	
	private static AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
	// Die "einzelnen" Seiten in die Panels legen
	private VerticalPanel homePanel = new VerticalPanel();
//	VerticalPanel projektPanel = new VerticalPanel();
	private VerticalPanel projektmarktplatzPanel = new VerticalPanel();
	

	
	
	
	// Buttons für die Panels erstellen
	
	// Buttons in dem Panel "home"
	
	private Button zurstartseiteButton = new Button("Startseite");
	private Button meineBewerbungenButton = new Button("Meine Bewerbungen");
	private Button meineProjekteButton = new Button("Meine Projekte");
	private Button meinProfilButton = new Button("Mein Profil");
	private Button agbButton = new Button("AGB");
	
		
	//Button für den ProjektPanel erstellen, dass heißt Button wird aber noch nicht angezeigt
	
	private Button projektmarktplaetzeButton = new Button("Projektmarktplätze");
	private Button stellenausschreibungenButton = new Button("Stellenausschreibungen");
	private Button reportgeneratorButton = new Button("Report Generator");
	
	IdentitySelection is = null;
	
	public Menubar(final IdentitySelection is){
		
	}
			
	public Menubar(final Person person){
		// Zusammensetzen des startseitePanels
		
	
		
//		homePanel.add(zurstartseiteButton);
//		zurstartseiteButton.setWidth("200px");
//		zurstartseiteButton.setStylePrimaryName("navi-button");
		
		
		homePanel.add(meineBewerbungenButton);
		meineBewerbungenButton.setWidth("200px");
		meineBewerbungenButton.setStylePrimaryName("navi-button");
		
		homePanel.add(meineProjekteButton);
		meineProjekteButton.setWidth("200px");
		meineProjekteButton.setStylePrimaryName("navi-button");
		
		homePanel.add(meinProfilButton);
		meinProfilButton.setWidth("200px");
		meinProfilButton.setStylePrimaryName("navi-button");		
		
		homePanel.setSpacing(5);
		homePanel.setWidth("100%");
		
		//Zusammensetzen des ProjektPanels
		
		projektmarktplatzPanel.add(projektmarktplaetzeButton);
		projektmarktplaetzeButton.setWidth("200px");
		projektmarktplaetzeButton.setStylePrimaryName("navi-button");
		
		projektmarktplatzPanel.add(stellenausschreibungenButton);
		stellenausschreibungenButton.setWidth("200px");
		stellenausschreibungenButton.setStylePrimaryName("navi-button");
				
		projektmarktplatzPanel.setSpacing(5);
		projektmarktplatzPanel.setWidth("100%");
		
		// Zusammensetzen des projektseitePanels
		
//		projektseitePanel.add(blablaButton);
//		blablaButton.setWidth("200px");
//		blablaButton.setStylePrimaryName("navi-button");
//		
//		projektseitePanel.setSpacing(5);
		
		this.setWidth("230px");
		this.addStyleName("gwt-StackPanel");
		this.add(homePanel, "Eigene Seite");
		this.add(projektmarktplatzPanel, "Marktplatz");
		
		//ProjektPanel zum StockPanel hinzufügen, dass es angezeigt wird
		
//		this.add(projektPanel, "Projekte");
	
	
//		zurstartseiteButton.addClickHandler(new ClickHandler() {
//			
//			@Override
//			public void onClick(ClickEvent event) {
//				Showcase showcase = new Homeseite();
//				RootPanel.get("Details").clear();
//				RootPanel.get("Details").add(showcase);
//				currentClickHandler=this;
//				currentClickEvent=event;
//				
//			}
//		});
		projektmarktplaetzeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Window.alert(""+ is.getUser().getID());
				Showcase showcase = new ProjektmarktplatzSeite(is);
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
				currentClickHandler=this;
				currentClickEvent=event;
				
			}
		});
		
		meineProjekteButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				Showcase showcase = new MeineProjekteAnzeigen(is);
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
				currentClickHandler=this;
				currentClickEvent=event;
				
			}
		});
		
		meineBewerbungenButton.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				Window.alert(""+ is.getSelectedIdentityAsObject().getID());
				Showcase showcase = new MeineBewerbungenSeite(is, getMenubar());
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
				currentClickHandler=this;
				currentClickEvent=event;
			}
		});
		
		stellenausschreibungenButton.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				Showcase showcase = new StellenausschreibungenSeite(is, getMenubar());
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
				currentClickHandler=this;
				currentClickEvent=event;
			}
		});
		
		
//		projektmarktplatzButton.addClickHandler(new ClickHandler() {
//			
//			@Override
//			public void onClick(ClickEvent event) {
//				Showcase showcase = new ProjektmarktplatzSeite();
//				//Unser Detail Container wird geleert, damit der Container neu befüllt werden kann
//				RootPanel.get("Details").clear();
//				// Unser Container wird mit dem instanziierten showcase befüllt
//				RootPanel.get("Details").add(showcase);
//				//Der ClickHandel und das ClickEvent referenziert auf die aktuelle Methode
//				currentClickHandler=this;
//				currentClickEvent=event;
//				
//				
//			}
//		});
		
		meinProfilButton.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				currentClickHandler=this;
				currentClickEvent=event;
				Showcase showcase = new MeinProfilAnzeigen(is);
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
		
		
//				is.activateOrgUnits();
//			
//				Organisationseinheit selectedIdentity = is.getSelectedIdentityAsObject();	
//				
//				if(selectedIdentity instanceof Person){
//					Showcase showcase = new MeinProfilAnzeigen(selectedIdentity);
//					RootPanel.get("Details").clear();
//					RootPanel.get("Details").add(showcase);}
//				}else if(selectedIdentity instanceof Team){
//					Showcase showcase = new TeamProfilAnzeigenForm(identityMarketChoice, getNavigation());
//					RootPanel.get("Details").clear();
//					RootPanel.get("Details").add(showcase);
//					
//				//Falls der Index 2 ist, dann ist ein Unternehmen aktiv und es wird die UnternehmenProfilAnzeigenForm geladen.
//				}else if(selectedIdentity instanceof Unternehmen){
//					Showcase showcase = new UnternehmenProfilAnzeigenForm(identityMarketChoice, getNavigation());
//					RootPanel.get("Details").clear();
//					RootPanel.get("Details").add(showcase);
//				}

//				RootPanel.get("Details").clear();
//				RootPanel.get("Details").add(showcase);

			}
		});
	}
	
	
	private void add(VerticalPanel homePanel2, String string, VerticalPanel projektmarktplatzPanel2, String string2) {
		// TODO Auto-generated method stub
		
	}


//	public Button getProjektmarktplaetzeButton() {
//		return projektmarktplaetzeButton;
//	}


	public IdentitySelection getIdSelection() {
		return is;
	}


	public void setIdSelection(IdentitySelection idSelection) {
		this.is = idSelection;
	}
	
	public void reload(){
		currentClickHandler.onClick(currentClickEvent);
	}
	public Menubar getMenubar(){
		return this;
	}
	

}
