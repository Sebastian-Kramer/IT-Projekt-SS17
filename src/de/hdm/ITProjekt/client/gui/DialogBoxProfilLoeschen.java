package de.hdm.ITProjekt.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.client.IT_Projekt_SS17;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatz;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;
import de.hdm.ITProjekt.shared.bo.Person;

public class DialogBoxProfilLoeschen extends DialogBox{
	private Person person = new Person();

	private IT_Projekt_SS17 profilloschen = new IT_Projekt_SS17();
	
	private VerticalPanel vpanel = new VerticalPanel();
	private HorizontalPanel hpanel = new HorizontalPanel();
	private Button ja = new Button("Ja");
	private Button abbrechen = new Button("Nein");
	
	private Label frage = new Label("Möchten Sie Ihr Profil Löschen? Dieser Vorgang kann nicht rückganig gemacht werden.");

	AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
	
	private FlexTable ft_person = new FlexTable();
	
	public DialogBoxProfilLoeschen(final Person person){
		this.person = person;
		this.setAnimationEnabled(false);
		this.setGlassEnabled(true);
		this.setText("Unternehmen");
		ja.setStylePrimaryName("button");
		abbrechen.setStylePrimaryName("button");
		
		hpanel.add(ja);
		hpanel.add(abbrechen);
		
		abbrechen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				hide();
			}
		});
		
		ja.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
				 if (adminService == null) {
			     adminService = GWT.create(AdministrationProjektmarktplatz.class);
			   }
				 adminService.deletePerson(person, new AsyncCallback<Void>(){

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(Void result) {
//						profilloschen.onModuleLoad();
						hide();
//						ja.setHTML(profilloschen.getLoginInfo().getLogoutUrl());	
					}
					 
				 });
			}
		});
		ft_person.setWidget(0, 0, frage);
		ft_person.setWidget(1, 0, ja);
		ft_person.setWidget(1, 1, abbrechen);
		
		vpanel.add(ft_person);
		this.add(vpanel);
	}
	






}
