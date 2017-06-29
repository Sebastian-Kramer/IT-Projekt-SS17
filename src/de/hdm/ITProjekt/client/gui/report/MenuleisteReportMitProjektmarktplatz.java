package de.hdm.ITProjekt.client.gui.report;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.ITProjekt.client.ReportShowcase;
import de.hdm.ITProjekt.client.Showcase;

public class MenuleisteReportMitProjektmarktplatz extends HorizontalPanel{

	private static ClickHandler currentClickHandler = null;
	private static ClickEvent currentClickEvent = null;
	
	private Anchor agbAnchor = new Anchor("AGB");
	private Anchor impressumAnchor = new Anchor("Impressum");
	private Anchor projektmarktplatzAnchor = new Anchor("Projektmarktplatz");
	 
	public MenuleisteReportMitProjektmarktplatz(){
		
		this.add(projektmarktplatzAnchor);
		this.add(agbAnchor);
		this.add(impressumAnchor);
		
		projektmarktplatzAnchor.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				projektmarktplatzAnchor.setHref(GWT.getHostPageBaseURL()+"IT_Projekt_SS17.html");
				Window.open(projektmarktplatzAnchor.getHref(), "_self", "");
			}
		});
		
		agbAnchor.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Showcase showcase = new AGBReport();
				RootPanel.get("DetailsReport").clear();
				RootPanel.get("DetailsReport").add(showcase);
				currentClickHandler=this;
				currentClickEvent=event;
		
			}
		});
		
		impressumAnchor.addClickHandler(new ClickHandler() {
			@Override
		public void onClick(ClickEvent event) {

			Showcase showcase = new ImpressumReport();
			RootPanel.get("DetailsReport").clear();
			RootPanel.get("DetailsReport").add(showcase);
			currentClickHandler=this;
			currentClickEvent=event;
		}
		});
	}}
	

