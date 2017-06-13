package de.hdm.ITProjekt.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.ITProjekt.client.gui.Homeseite;
import de.hdm.ITProjekt.client.gui.IdentitySelection;
import de.hdm.ITProjekt.client.gui.MeinProfilAnzeigen;
import de.hdm.ITProjekt.client.gui.MeineBewerbungenSeite;
import de.hdm.ITProjekt.client.gui.Projekte;
import de.hdm.ITProjekt.client.gui.ProjektmarktplatzSeite;
import de.hdm.ITProjekt.client.gui.StellenausschreibungenSeite;

import de.hdm.ITProjekt.shared.bo.Organisationseinheit;

public class Menubar extends StackPanel {
	
	
	// Clickhandler auf "null" setzen
	
	private static ClickHandler currentClickHandler = null;
	private static ClickEvent currentClickEvent = null;
	
	IdentitySelection idSelection = null;
		
	// Die "einzelnen" Seiten in die Panels legen
	VerticalPanel homePanel = new VerticalPanel();
//	VerticalPanel projektPanel = new VerticalPanel();
	VerticalPanel projektmarktplatzPanel = new VerticalPanel();
	
	
	
	// Buttons für die Panels erstellen
	
	// Buttons in dem Panel "home"
	
	Button zurstartseiteButton = new Button("Startseite");
	Button meineBewerbungenButton = new Button("Meine Bewerbungen");
	Button meineProjekteButton = new Button("Meine Projekte");
	Button meinProfilButton = new Button("Mein Profil");
		
	//Button für den ProjektPanel erstellen, dass heißt Button wird aber noch nicht angezeigt
	
	Button projektmarktplaetzeButton = new Button("Projektmarktplätze");
	Button stellenausschreibungenButton = new Button("Stellenausschreibungen");
	
	IdentitySelection is = null;
	
	public Menubar(){
		// Zusammensetzen des startseitePanels
		
		homePanel.add(zurstartseiteButton);
		zurstartseiteButton.setWidth("200px");
		zurstartseiteButton.setStylePrimaryName("navi-button");
		
		
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
//		
		this.setWidth("250px");
		this.addStyleName("gwt-StackPanel");
		this.add(homePanel, "Startseite");
		this.add(projektmarktplatzPanel, "Projekte");
		
		//ProjektPanel zum StockPanel hinzufügen, dass es angezeigt wird
		
//		this.add(projektPanel, "Projekte");
	
	
		zurstartseiteButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Showcase showcase = new Homeseite();
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
				currentClickHandler=this;
				currentClickEvent=event;
				
			}
		});
		
		projektmarktplaetzeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				Showcase showcase = new ProjektmarktplatzSeite();
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
				currentClickHandler=this;
				currentClickEvent=event;
				
			}
		});
		
		// Dieser ClickHandler geh�rt nicht hierher!! Wurde von Mert als Test angelegt!!!
		
		meineProjekteButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
//				Showcase showcase = new Projekte();
//				RootPanel.get("Details").clear();
//				RootPanel.get("Details").add(showcase);
//				currentClickHandler=this;
//				currentClickEvent=event;
				
			}
		});
		
		meineBewerbungenButton.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				Showcase showcase = new MeineBewerbungenSeite();
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
				currentClickHandler=this;
				currentClickEvent=event;
			}
		});
		
		stellenausschreibungenButton.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				Showcase showcase = new StellenausschreibungenSeite();
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
//				idSelection.deactivateProjectMarkets();
//				idSelection.activateOrgUnits();
//				
//				Organisationseinheit selectedIdentity = idSelection.getSelectedIdentityAsObject();	
//				
				Showcase showcase = new MeinProfilAnzeigen();
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);

			}
		});
	}
	
	
	public Button getProjektmarktplaetzeButton() {
		return projektmarktplaetzeButton;
	}


	public IdentitySelection getIdSelection() {
		return idSelection;
	}


	public void setIdSelection(IdentitySelection idSelection) {
		this.idSelection = idSelection;
	}
	
	public void reload(){
		currentClickHandler.onClick(currentClickEvent);
	}
	
	

}
