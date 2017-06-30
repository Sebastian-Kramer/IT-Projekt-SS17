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
import de.hdm.ITProjekt.shared.bo.Unternehmen;

public class DialogBoxUnternehmen extends DialogBox{
	
	IdentitySelection is = null;
	private Person person = new Person();
	private Unternehmen unternehmen = new Unternehmen();
	private VerticalPanel vpanel = new VerticalPanel();
	private HorizontalPanel hpanel = new HorizontalPanel();
	private Button ok = new Button("Ok");
	private Button abbrechen = new Button("Abbrechen");
	
	private Label unternehmennamelabel = new Label ("Unternehmenname");
	private TextArea unternehmennametext = new TextArea();
	
	private Label unternehmenplz = new Label("PLZ");
	private TextArea unternehmenplztext = new TextArea();
	
	private Label unternehmenort = new Label("Ort");
	private TextArea unternehmenorttext = new TextArea();
	
	private Label unternehmenstrasse = new Label("Straße");
	private TextArea unternehmenstrassetext = new TextArea();
	
	private Label unternehmenhausnummer = new Label("Hausnummer");
	private TextArea unternehmenhausnummertext = new TextArea();
	
	private FlexTable unternehmenseite = new FlexTable();
	
	AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
	
	public DialogBoxUnternehmen(final IdentitySelection is){
		this.setAnimationEnabled(false);
		this.setGlassEnabled(true);
		this.setText("Unternehmen");
		ok.setStylePrimaryName("button");
		abbrechen.setStylePrimaryName("button");
		this.is = is;
		
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
			adminService.createPartnerprofil(new unternehmenindb());
		}
	});
	unternehmenseite.setWidget(1, 0, unternehmennamelabel);
	unternehmenseite.setWidget(1, 1, unternehmennametext);
	unternehmenseite.setWidget(2, 0, unternehmenplz);
	unternehmenseite.setWidget(2, 1, unternehmenplztext);
	unternehmenseite.setWidget(3, 0, unternehmenort);
	unternehmenseite.setWidget(3, 1, unternehmenorttext);
	unternehmenseite.setWidget(4, 0, unternehmenstrasse);
	unternehmenseite.setWidget(4, 1, unternehmenstrassetext);
	unternehmenseite.setWidget(5, 0, unternehmenhausnummer);
	unternehmenseite.setWidget(5, 1, unternehmenhausnummertext);
	vpanel.add(unternehmenseite);
	vpanel.add(hpanel);
	this.add(vpanel);
	}
	
	private class unternehmenindb implements AsyncCallback<Partnerprofil>{

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
			
			 adminService.insertUnternehmen(unternehmennametext.getText(), Integer.parseInt(unternehmenplztext.getText()), 
					 Integer.parseInt(unternehmenhausnummertext.getText()), unternehmenorttext.getText(), unternehmenstrassetext.getText(), result.getID(), is.getUser().getID(), new AsyncCallback<Unternehmen>(){

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onSuccess(Unternehmen result) {
			
					is.getUser().setUN_ID(result.getID());
					((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
					 if (adminService == null) {
				     adminService = GWT.create(AdministrationProjektmarktplatz.class);
				   }
					adminService.updatePerson(is.getUser(), new updateperson());
					hide(); 
					adminService.getPersonbyID(is.getUser().getID(), new AsyncCallback<Person>(){

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub
								
							}

							@Override
							public void onSuccess(Person result) {
								hide();
								RootPanel.get("idendity").clear();
								RootPanel.get("Navigator").clear();
								RootPanel.get("Details").clear();
								Showcase showcase = new MeinProfilAnzeigen(is);
								Menubar mb = new Menubar(is.getUser());
								mb.setIdSelection(is);
								RootPanel.get("Details").add(showcase);
								RootPanel.get("idendity").add(new IdentitySelection(is.getUser(), mb));
								RootPanel.get("Navigator").add(mb);
							}
							 
						 });
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
