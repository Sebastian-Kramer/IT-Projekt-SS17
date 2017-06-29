package de.hdm.ITProjekt.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.ITProjekt.client.gui.AGB;
import de.hdm.ITProjekt.client.gui.AGBOhneLogin;
import de.hdm.ITProjekt.client.gui.Impressum;
import de.hdm.ITProjekt.client.gui.ImpressumOhneLogin;
import de.hdm.ITProjekt.client.gui.report.AGBReportOhneLogin;

public class MenuleisteOhneLogin extends HorizontalPanel{
		
		private static ClickHandler currentClickHandler = null;
		private static ClickEvent currentClickEvent = null;
		
		private Anchor agbAnchor = new Anchor("AGB");
		private Anchor impressumAnchor = new Anchor("Impressum");
		
		
		public MenuleisteOhneLogin(){

			agbAnchor.setStylePrimaryName("menuleistereportanchor");
			impressumAnchor.setStylePrimaryName("menuleistereportanchor");
			
			this.add(agbAnchor);
			this.add(impressumAnchor);
						
			agbAnchor.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					Showcase showcase = new AGBOhneLogin();
					RootPanel.get("Details").clear();
					RootPanel.get("Details").add(showcase);
					currentClickHandler=this;
					currentClickEvent=event;
			
				}
			});
			
			impressumAnchor.addClickHandler(new ClickHandler() {
				@Override
			public void onClick(ClickEvent event) {

				Showcase showcase = new ImpressumOhneLogin();
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
				currentClickHandler=this;
				currentClickEvent=event;
			}
			});
		}}

