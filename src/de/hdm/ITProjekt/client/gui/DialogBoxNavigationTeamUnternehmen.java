package de.hdm.ITProjekt.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.ITProjekt.shared.bo.Person;

public class DialogBoxNavigationTeamUnternehmen extends DialogBox{

	private Person person = new Person();
	
	private VerticalPanel vpanel = new VerticalPanel();
	private HorizontalPanel hpanel = new HorizontalPanel();	

	private Button fertig = new Button("Fertig");
	
	private Anchor teamerstellen = new Anchor("Team Erstellen");
	private Anchor teamloeschen = new Anchor("Team Löschen");
	
	private Anchor unternehmenerstellen = new Anchor("Unternehmen Erstellen");
	private Anchor unternehmenloeschen = new Anchor("Unternehmen Löschen");
	
	private FlexTable naviseite = new FlexTable();
	private FlexTable naviseite2 = new FlexTable();
	
	public DialogBoxNavigationTeamUnternehmen(final Person person){
		this.person = person;
		this.setAnimationEnabled(false);
		this.setGlassEnabled(true);
		this.setText("Team");
		fertig.setStylePrimaryName("button");
		
		teamerstellen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if (person.getUN_ID() != null ){
					Window.alert("erstellen sie ein unternehmen");
				}
//				else if (person.getUN_ID() == null){
//					Window.alert("Erstellen Sie bitte zuerst ein Unternehmen");
//				} 
				else{
				DialogBox dialogbox = new DialogBoxTeam(person);
				dialogbox.center();
				hide();
				}
				
				}
			});
		teamloeschen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if (person.getTeam_ID() == null){
				Window.alert("Sie haben bereits kein Team");
			}else{
			}
			}
		});
		
		unternehmenerstellen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if (person.getUN_ID() != null){
					Window.alert("Bearbeiten Sie bitte Ihr bestehendes Unternehmen");
				}else{
					DialogBox dialogbox = new DialogBoxUnternehmen(person);
				dialogbox.center();
				hide();
				}
				
			}
		});
		
		unternehmenloeschen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if (person.getUN_ID() == null){
					Window.alert("Sie haben bereits kein Unternehmen");
				}else{
				}
			}
		});
		
		fertig.addClickHandler(new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {
				hide();
			}
		});
		naviseite.setWidget(1, 0, teamerstellen);
		naviseite.setWidget(1, 1, teamloeschen);
		naviseite2.setWidget(1, 0, unternehmenerstellen);
		naviseite2.setWidget(1, 1, unternehmenloeschen);
		hpanel.add(naviseite);
		hpanel.add(naviseite2);
		vpanel.setPixelSize(150, 80);
		vpanel.add(hpanel);
		vpanel.add(fertig);
		this.add(vpanel);
	}
	

}
