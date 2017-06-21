package de.hdm.ITProjekt.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.ITProjekt.client.gui.AGB;
import de.hdm.ITProjekt.client.gui.Impressum;

public class Menuleiste extends HorizontalPanel{
	
	private static ClickHandler currentClickHandler = null;
	private static ClickEvent currentClickEvent = null;
	
	private Anchor agbAnchor = new Anchor("AGB");
	private Anchor impressumAnchor = new Anchor("Impressum");
	
	 
	public Menuleiste(){
		
		this.add(agbAnchor);
		this.add(impressumAnchor);
		
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
		
		impressumAnchor.addClickHandler(new ClickHandler() {@Override
		public void onClick(ClickEvent event) {

			Showcase showcase = new Impressum();
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
