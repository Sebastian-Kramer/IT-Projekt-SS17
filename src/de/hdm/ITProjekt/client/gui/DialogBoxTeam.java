package de.hdm.ITProjekt.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.client.Menubar;
import de.hdm.ITProjekt.client.Showcase;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatz;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;
import de.hdm.ITProjekt.shared.bo.Partnerprofil;
import de.hdm.ITProjekt.shared.bo.Person;
import de.hdm.ITProjekt.shared.bo.Projektmarktplatz;
import de.hdm.ITProjekt.shared.bo.Team;

public class DialogBoxTeam extends DialogBox{	
	
	private Person person = new Person();
	private Team team = new Team();
	
	IdentitySelection is = null;
	
	private VerticalPanel vpanel = new VerticalPanel();
	private HorizontalPanel hpanel = new HorizontalPanel();
	private Button ok = new Button("Ok");
	private Button abbrechen = new Button("Abbrechen");
	
	private Label teamnamelabel = new Label ("Teamname");
	private TextArea teamnametext = new TextArea();
	
	private Label teamplz = new Label ("PLZ");
	private TextArea teamplztext = new TextArea();
	
	private Label teamort = new Label("Ort");
	private TextArea teamorttext = new TextArea();
	
	private Label teamstrasse = new Label("Straße");
	private TextArea teamstrassetext = new TextArea();
	
	private Label teamhausnummer = new Label("Hausnummer");
	private TextArea teamhausnummertext = new TextArea();
	
	private FlexTable teamseite = new FlexTable();

	AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
	
	
	public DialogBoxTeam(final IdentitySelection is){		
		this.is = is;
		this.setAnimationEnabled(false);
		this.setGlassEnabled(true);
		this.setText("Team");
		ok.setStylePrimaryName("button");
		abbrechen.setStylePrimaryName("button");
		
		hpanel.add(ok);
		hpanel.add(abbrechen);
		
	abbrechen.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					hide();
				}
			});
	
	ok.addClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
			 if (adminService == null) {
		     adminService = GWT.create(AdministrationProjektmarktplatz.class);
		   }
			adminService.createPartnerprofil(new teamindb());
			
		}
	});
	
	
	teamseite.setWidget(1, 0, teamnamelabel);
	teamseite.setWidget(1, 1, teamnametext);
	teamseite.setWidget(2, 0, teamplz);
	teamseite.setWidget(2, 1, teamplztext);
	teamseite.setWidget(3, 0, teamort);
	teamseite.setWidget(3, 1, teamorttext);
	teamseite.setWidget(4, 0, teamstrasse);
	teamseite.setWidget(4, 1, teamstrassetext);
	teamseite.setWidget(5, 0, teamhausnummer);
	teamseite.setWidget(5, 1, teamhausnummertext);
	vpanel.add(teamseite);
	vpanel.add(hpanel);
	this.add(vpanel);
	}
//	private class teamInDB implements AsyncCallback<Team>{
//
//		@Override
//		public void onFailure(Throwable caught) {
//			// TODO Auto-generated method stub
//			
//		}
//
//		@Override
//		public void onSuccess(Team result) {
//			team.setName(teamnametext.getText());
//			team.setPlz(Integer.parseInt(teamplztext.getText()));
//			team.setHausnummer(Integer.parseInt(teamhausnummertext.getText()));
//			team.setOrt(teamorttext.getText());
//			team.setStrasse(teamstrassetext.getText());
//			team.setUN_ID(person.getUN_ID());
//			hide();
//			Showcase showcase = new MeinProfilAnzeigen(person);
//			RootPanel.get("Details").clear();
//			RootPanel.get("Details").add(showcase);
//			
//		}
//		
//	}

	private class teamindb implements AsyncCallback<Partnerprofil>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Partnerprofil result) {
			((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
			 if (adminService == null) {
			      adminService = GWT.create(AdministrationProjektmarktplatz.class);
			    }
			adminService.createTeam(teamnametext.getText(), Integer.parseInt(teamplztext.getText()),  Integer.parseInt(teamhausnummertext.getText()), 
					teamorttext.getText(), teamstrassetext.getText(), person.getUN_ID(), result.getID(), new AsyncCallback<Team>(){
				
				@Override
				public void onFailure(Throwable caught) {
					Window.alert("FEHLER");				
				}

				@Override
				public void onSuccess(Team result) {
					is.getUser().setTeam_ID(result.getID());
					((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
					 if (adminService == null) {
				     adminService = GWT.create(AdministrationProjektmarktplatz.class);
				     }
					 adminService.updatePerson(is.getUser(), new updateperson());
					 hide();
					Menubar menubar = new Menubar(is.getUser());
					RootPanel.get("idendity").clear();
					RootPanel.get("idendity").add(new IdentitySelection(is.getUser(), menubar));
					
					RootPanel.get("Navigator").clear();
					RootPanel.get("Navigator").add(menubar);
						
					Showcase showcase = new MeinProfilAnzeigen(is);
					RootPanel.get("Details").clear();
					RootPanel.get("Details").add(showcase);
				}
				
			});
		}
		
	}
	private class updateperson implements AsyncCallback<Person>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Person result) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
