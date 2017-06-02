package de.hdm.ITProjekt.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.ITProjekt.client.gui.ProjektmarktplatzSeite;

public class Menubar extends StackPanel {
	
	
	// Clickhandler auf "null" setzen
	
	private static ClickHandler currentClickHandler = null;
	private static ClickEvent currentClickEvent = null;
		
	// Die "einzelnen" Seiten in die Panels legen
	VerticalPanel startseite = new VerticalPanel();
	Button btn1 = new Button();
	Button projektmarktplaetzeButton = new Button();
	
	
	
	public Menubar(){
		startseite.add(btn1);
		btn1.setWidth("200px");
		btn1.setStylePrimaryName("navi-button");
		startseite.setSpacing(5);
	
	
		projektmarktplaetzeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				Showcase showcase = new ProjektmarktplatzSeite();
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
				currentClickHandler=this;
				currentClickEvent=event;
			}
		});
	}
	
	

}
