package de.hdm.ITProjekt.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.client.Showcase;
import de.hdm.ITProjekt.client.gui.ProjektmarktplatzSeite.getProjektmarktplatzAusDB;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatz;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;
import de.hdm.ITProjekt.shared.bo.Person;

public class RegistrierungsForm extends Showcase{

	private static ClickHandler currentClickHandler = null;
	private static ClickEvent currentClickEvent = null;
	
	private AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
	
	private VerticalPanel vpanel_registrierung = new VerticalPanel();
	private HorizontalPanel hpanel_registrierung = new HorizontalPanel();
	
	private FlexTable form = new FlexTable();
	
	private Button abbrechen = new Button("Abbrechen");
	private Button bestaetigen = new Button("Bestätigen");
	
	private ListBox anredeListbox = new ListBox();
	private ListBox unternehmenListbox = new ListBox();
	private ListBox teamListbox = new ListBox();
	
	private Label anrede = new Label("Anrede");
	private Label vorname = new Label("Vorname");
	private Label nachname = new Label("Nachname");
	private Label straße = new Label("Straße");
	private Label hausnr = new Label("Hausnummer");
	private Label plz = new Label("Postleitzahl");
	private Label ort = new Label("Ort");
	
	private TextBox vnameBox = new TextBox();
	private TextBox nnameBox = new TextBox();
	private TextBox strasseBox = new TextBox();
	private TextBox hausnrBox = new TextBox();
	private TextBox plzBox = new TextBox();
	private TextBox ortBox = new TextBox();

	private Person person = new Person();
	
	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "<h1> Registrierung</h1>";
	}

	@Override
	protected void run() {
		anredeListbox.addItem("Herr");
		anredeListbox.addItem("Frau");
		
		form.setWidget(0,  1, anredeListbox);
		form.setWidget(0, 0, anrede);
		
		form.setWidget(1,  1, vnameBox);
		form.setWidget(1, 0, vorname);
		
		form.setWidget(2,  1, nnameBox);
		form.setWidget(2, 0, nachname);
		
		form.setWidget(3,  1, strasseBox);
		form.setWidget(3, 0, straße);
		
		form.setWidget(4,  1, hausnrBox);
		form.setWidget(4, 0, hausnr);
		
		form.setWidget(5,  1, plzBox);
		form.setWidget(5, 0, plz);
		
		form.setWidget(6,  1, ortBox);
		form.setWidget(6, 0, ort);
		
		form.setWidget(7, 0, abbrechen);
		form.setWidget(7, 1, bestaetigen);
		
		vpanel_registrierung.add(form);
		this.add(vpanel_registrierung);
		
	
		bestaetigen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				person.setAnrede(anredeListbox.getSelectedValue());
				person.setVorname(vnameBox.getText());
				person.setName(nnameBox.getText());
				person.setStraße(strasseBox.getText());
				person.setHausnummer(Integer.parseInt(hausnrBox.getText()));
				person.setPlz(Integer.parseInt(plzBox.getValue()));
				person.setOrt(ortBox.getValue());
			
				((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
				 if (adminService == null) {
			      adminService = GWT.create(AdministrationProjektmarktplatz.class);
			    }
				adminService.createPerson(person, new setPersoninDB());
			}
		});
		
		abbrechen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Showcase showcase = new Homeseite();
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
			}
		});
	}
	
private class setPersoninDB implements AsyncCallback<Person>{

	@Override
	public void onFailure(Throwable caught) {
		Window.alert("Fehler beim Laden in die Datenbank");
	}

	@Override
	public void onSuccess(Person result) {
		Window.alert("Sie wurden erfolgreich registriert");
		Showcase showcase = new Homeseite();
		RootPanel.get("Details").clear();
		RootPanel.get("Details").add(showcase);
	}
	
}
}
