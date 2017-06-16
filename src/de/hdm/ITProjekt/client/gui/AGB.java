package de.hdm.ITProjekt.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.ITProjekt.client.Showcase;

public class AGB extends Showcase {
	private static ClickHandler currentClickHandler = null;
	private static ClickEvent currentClickEvent = null;

	@Override
	protected String getHeadlineText() {
		
		return "Unsere AGB's";
	}

	@Override
	protected void run() {
		// TODO Auto-generated method stub
		
		VerticalPanel aPanel  = new VerticalPanel();
		
		Label aLabel = new Label("Unsere AGB's");
	aLabel.setWidth("250px");
	aPanel.setWidth("250px");
		aPanel.add(aLabel);
		this.add(aLabel);
		
	//Button AGB = new Button ("AGB");
	RootPanel.get("AGB").add(aPanel);	

	aLabel.addClickHandler(new ClickHandler() {
		
		@Override
	public void onClick(ClickEvent event) {
			
	
	}
			
			
		
	});
	

	}}
