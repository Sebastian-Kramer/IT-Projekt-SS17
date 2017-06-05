package de.hdm.ITProjekt.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.ITProjekt.client.gui.Homeseite;
import de.hdm.ITProjekt.client.gui.ProjektmarktplatzSeite;

public class Menubar extends StackPanel {
	
	
	// Clickhandler auf "null" setzen
	
	private static ClickHandler currentClickHandler = null;
	private static ClickEvent currentClickEvent = null;
		
	// Die "einzelnen" Seiten in die Panels legen
	VerticalPanel startseitePanel = new VerticalPanel();
	//VerticalPanel projektseitePanel = new VerticalPanel();
	VerticalPanel projektPanel = new VerticalPanel();
	VerticalPanel beispielseitePanel = new VerticalPanel();
	
	// Buttons für die Panels erstellen
	
	// Buttons dem Panel "startseite"
	Button zurstartseiteButton = new Button("Startseite");
	Button projektmarktplaetzeButton = new Button("Projektmarktplätze");
	
	//Button für den ProjektPanel erstellen, dass heißt Button wird aber noch nicht angezeigt
	
	Button projektButton = new Button("Projekte");
	
	
	//Button in dem Panel "projektseite"
	//Button blablaButton = new Button("blablabutton");
	
	
	
	public Menubar(){
		// Zusammensetzen des startseitePanels
		
		startseitePanel.add(zurstartseiteButton);
		zurstartseiteButton.setWidth("200px");
		zurstartseiteButton.setStylePrimaryName("navi-button");
		
		
		startseitePanel.add(projektmarktplaetzeButton);
		projektmarktplaetzeButton.setWidth("200px");
		projektmarktplaetzeButton.setStylePrimaryName("navi-button");
		
		
		startseitePanel.setSpacing(5);
		startseitePanel.setWidth("100%");
		
		
		
		//Zusammensetzen des ProjektPanels
		
		projektPanel.add(projektButton);
		projektButton.setWidth("200px");
		projektButton.setStylePrimaryName("navi-button");
		
		projektPanel.setSpacing(5);
		projektPanel.setWidth("100%");
		
		
		
		
		
		// Zusammensetzen des projektseitePanels
		
//		projektseitePanel.add(blablaButton);
//		blablaButton.setWidth("200px");
//		blablaButton.setStylePrimaryName("navi-button");
//		
//		projektseitePanel.setSpacing(5);
//		
		this.setWidth("250px");
		this.addStyleName("gwt-StackPanel");
		this.add(startseitePanel, "Startseite");
//		this.add(projektseitePanel, "Projekte");
		
		//ProjektPanel zum StockPanel hinzufügen, dass es angezeigt wird
		
		this.add(projektPanel, "Projekte");
	
	
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
		
		projektButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
				Showcase showcase = new ProjektmarktplatzSeite();
				//Unser Detail Container wird geleert, damit der Container neu befüllt werden kann
				RootPanel.get("Details").clear();
				// Unser Container wird mit dem instanziierten showcase befüllt
				RootPanel.get("Details").add(showcase);
				//Der ClickHandel und das ClickEvent referenziert auf die aktuelle Methode
				currentClickHandler=this;
				currentClickEvent=event;
				
				
			}
		});
	}
	
	
	
	

}
