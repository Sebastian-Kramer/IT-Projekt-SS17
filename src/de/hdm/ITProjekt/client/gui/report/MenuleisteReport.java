package de.hdm.ITProjekt.client.gui.report;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import de.hdm.ITProjekt.client.Showcase;

/**
 * Diese Klasse erstellt die Menuleiste und dessen Anchors. 
 * Hierzu werden jeweils ClickHandler erstellt.
 * @author Giuseppe
 */
public class MenuleisteReport extends HorizontalPanel{
		
		private static ClickHandler currentClickHandler = null;
		private static ClickEvent currentClickEvent = null;
		
		private Anchor agbReportAnchor = new Anchor("AGB");
		private Anchor impressumReportAnchor = new Anchor("Impressum");
		
		 
		public MenuleisteReport(){
			agbReportAnchor.setStylePrimaryName("menuleistereportanchor");
			impressumReportAnchor.setStylePrimaryName("menuleistereportanchor");
			this.add(agbReportAnchor);
			this.add(impressumReportAnchor);
			
			agbReportAnchor.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					Showcase showcase = new AGBReport();
					RootPanel.get("DetailsReport").clear();
					RootPanel.get("DetailsReport").add(showcase);
					currentClickHandler=this;
					currentClickEvent=event;
			
				}
			});
			
			impressumReportAnchor.addClickHandler(new ClickHandler() {
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
		
