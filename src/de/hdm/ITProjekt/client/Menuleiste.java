package de.hdm.ITProjekt.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.ITProjekt.client.gui.AGB;

public class Menuleiste extends VerticalPanel{
	
	private static ClickHandler currentClickHandler = null;
	private static ClickEvent currentClickEvent = null;
	
	private Anchor agbAnchor = new Anchor("AGB");
	
	 
	public Menuleiste(){
		
		this.add(agbAnchor);
		
		agbAnchor.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Showcase showcase = new AGB();
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
				currentClickHandler=this;
				currentClickEvent=event;
		
			}
		});
		
		
	}}
	
	
//	
//   
//
//    RootPanel.get("Header").add(menuleistePanel);
// }	
//
