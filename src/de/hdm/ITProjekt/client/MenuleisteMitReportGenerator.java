package de.hdm.ITProjekt.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.ITProjekt.client.gui.AGB;
import de.hdm.ITProjekt.client.gui.Impressum;

public class MenuleisteMitReportGenerator extends HorizontalPanel{
		
		private static ClickHandler currentClickHandler = null;
		private static ClickEvent currentClickEvent = null;
		
		private Anchor agbAnchor = new Anchor("AGB");
		private Anchor impressumAnchor = new Anchor("Impressum");
		private Anchor reportgeneratorAnchor = new Anchor("Report Generator");
		private Anchor reportLink = new Anchor();
		
		
		public MenuleisteMitReportGenerator(){

			this.add(reportgeneratorAnchor);
			this.add(agbAnchor);
			this.add(impressumAnchor);
			
			reportgeneratorAnchor.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					reportLink.setHref(GWT.getHostPageBaseURL()+"ProjektmarktplatzReports.html");
					Window.open(reportLink.getHref(), "_self", "");
				}
			});
			
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
			
			impressumAnchor.addClickHandler(new ClickHandler() {
				@Override
			public void onClick(ClickEvent event) {

				Showcase showcase = new Impressum();
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
				currentClickHandler=this;
				currentClickEvent=event;
			}
			});
		}}

