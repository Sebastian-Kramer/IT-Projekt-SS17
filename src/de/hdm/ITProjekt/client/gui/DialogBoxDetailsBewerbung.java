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
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;

import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatz;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;
import de.hdm.ITProjekt.server.AdministrationProjektmarktplatzImpl;
import de.hdm.ITProjekt.server.db.BewerbungMapper;
import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;
import de.hdm.ITProjekt.shared.bo.Bewerbung;
import de.hdm.ITProjekt.shared.bo.Person;

public class DialogBoxDetailsBewerbung extends DialogBox{
	
	AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
	VerticalPanel vp = new VerticalPanel();
	
	Button schliessen = new Button("schliessen");
	
	TextArea bewerbungstext = new TextArea();
	FlexTable bewerbungstextft = new FlexTable();
	
	private Label personAnrede = new Label("Anrede: ");
	private Label personVorname = new Label("Vorname: ");
	private Label personName = new Label("Name: ");
	private Label personEmail = new Label("E-Mail: ");
	
	private TextBox anredeBox = new TextBox();
	private TextBox vornameBox = new TextBox();
	private TextBox nameBox = new TextBox();
	private TextBox emailBox = new TextBox();
	
	
	
	private Bewerbung bewerbungId;
	private Person p;

	
	public DialogBoxDetailsBewerbung(Bewerbung selectedId){
		this.bewerbungId = selectedId;
		
		schliessen.setStylePrimaryName("navi-button");
		setText("Bewerbung ");
		setAnimationEnabled(true);
		setGlassEnabled(true);
		this.center();
		bewerbungstext.setReadOnly(true);
		bewerbungstext.setText(bewerbungId.getBewerbungstext());
		bewerbungstext.setCharacterWidth(30);
		bewerbungstext.setVisibleLines(30);

		
		
		adminService.getPersonFromBewerbung(bewerbungId.getOrga_ID(), new BewerberDatails());
		
		
		bewerbungstextft.setWidget(0, 0, bewerbungstext);
		
		bewerbungstextft.setWidget(1, 0, personAnrede);
		bewerbungstextft.setWidget(1, 1, anredeBox);
		
		bewerbungstextft.setWidget(2, 0, personVorname);
		bewerbungstextft.setWidget(2, 1, vornameBox);
		
		bewerbungstextft.setWidget(3, 0, personName);
		bewerbungstextft.setWidget(3, 1, nameBox);
		
		bewerbungstextft.setWidget(4, 0, personEmail);
		bewerbungstextft.setWidget(4, 1, emailBox);
		
		bewerbungstextft.setWidget(5, 0, schliessen);
		
		vp.add(bewerbungstextft);
		
		this.add(vp);
	
		
	
		schliessen.addClickHandler(new ClickHandler(){
		public void onClick(ClickEvent event){
			DialogBoxDetailsBewerbung.this.hide();
		}
	});
	}
	
	public class BewerberDatails implements AsyncCallback<Person>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Die Daten des Bewerbers konnten nicht geladen werden");
			
		}

		@Override
		public void onSuccess(Person result) {
			
			anredeBox.setText(result.getAnrede());
			vornameBox.setText(result.getVorname());
			nameBox.setText(result.getName());
			emailBox.setText(result.getEmail());
			
			Window.alert(" " + bewerbungId.getOrga_ID());
			Window.alert("Die Daten des Bewerbers wurden erfolgreich geladen");
			
		}
		
	}
}

