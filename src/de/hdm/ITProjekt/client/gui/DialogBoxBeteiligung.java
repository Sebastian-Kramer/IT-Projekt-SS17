package de.hdm.ITProjekt.client.gui;

import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;

import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.client.Menubar;
import de.hdm.ITProjekt.client.Showcase;
import de.hdm.ITProjekt.client.gui.DialogBoxBewertung.BewertungAnlegen;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatz;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;
import de.hdm.ITProjekt.shared.bo.Ausschreibung;
import de.hdm.ITProjekt.shared.bo.Beteiligung;
import de.hdm.ITProjekt.shared.bo.Bewerbung;
import de.hdm.ITProjekt.shared.bo.Bewertung;
import de.hdm.ITProjekt.shared.bo.Person;
import de.hdm.ITProjekt.shared.bo.Projektmarktplatz;

public class DialogBoxBeteiligung extends DialogBox{
	
	DateTimeFormat dateformat = DateTimeFormat.getFormat("dd.MM.yyyy");
	
	AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
	
	private VerticalPanel vpanel = new VerticalPanel();
	private HorizontalPanel hpanel = new HorizontalPanel();
	
	private FlexTable form = new FlexTable();
	
	private Button beteiligunganlegen = new Button("Projektbeteiligung erstellen");
		
	private Label umfang = new Label("Beteiligung in Personentagen: ");
	private Label startdatum = new Label("Beteiligung startet am: ");
	private Label enddatum = new Label("Beteiligung geht bis zum: ");
	private Label zuprojekt = new Label("Beteiligung an folgendem Projekt wird erstellt: ");
	
	private TextBox umfangBox = new TextBox();
	private TextBox zuprojektBox = new TextBox();
	
	private DateBox startBox = new DateBox();
	private DateBox endBox = new DateBox();
	
	private IdentitySelection is = null;
	
	private Ausschreibung aus;
	private Bewertung bewe;
	private Beteiligung be = new Beteiligung();
	private Beteiligung newBeteiligung;
	private Person person;
	private Bewerbung bewerbung;
	
	public DialogBoxBeteiligung(Bewertung b, Ausschreibung a, IdentitySelection is, Bewerbung bew){
		this.aus = a;
		this.bewe = b;
		this.is = is;
		this.bewerbung = bew;
		
		this.setText("Projektbeteiligung erstellen");
		this.setAnimationEnabled(true);
		this.setGlassEnabled(true);
		
		startBox.setFormat(new DateBox.DefaultFormat(dateformat));
		endBox.setFormat(new DateBox.DefaultFormat(dateformat));
		
		
		
		vpanel.setSpacing(10);
		
		form.setWidget(0, 0, umfang);
		form.setWidget(0, 1, umfangBox);
		
		form.setWidget(2, 0, startdatum);
		form.setWidget(2, 1, startBox);
		
		form.setWidget(4, 0, enddatum);
		form.setWidget(4, 1, endBox);
		
		
		vpanel.add(form);
		vpanel.add(beteiligunganlegen);
		hpanel.add(vpanel);
		this.add(hpanel);
		
		beteiligunganlegen.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				
				be.setUmfang(umfangBox.getText());
				be.setStartdatum(startBox.getValue());
				be.setEnddatum(endBox.getValue());
				be.setOrga_ID(bewerbung.getOrga_ID());
				be.setProjekt_ID(aus.getProjekt_ID());
				bewerbung.setStatus("angenommen");

				
				((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
				 
				if (adminService == null) {
				 AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
				 }
				adminService.insert(be, new BeteiligungAnlegen());
				adminService.setBewerbungsStatus(bewerbung, new BewerbungStatus());
				
				DialogBoxBeteiligung.this.hide();
			}
			
		});
		

		
	}
	public class BeteiligungAnlegen implements AsyncCallback<Beteiligung>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Es konnte keine Beteiligung angelegt werden");
			
		}

		@Override
		public void onSuccess(Beteiligung result) {
			newBeteiligung = result;
			bewe.setBeteiligungs_ID(newBeteiligung.getID());				
			
			((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
			 
			if (adminService == null) {
			 AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
			 }
			adminService.insert(bewe, new BewertungAnlegen());
			
			Window.alert("Die Beteiligung wurde erfolgreich angelegt");
			
		}
		
	}
	public class BewertungAnlegen implements AsyncCallback<Bewertung>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Die Bewertung konnte nicht abgegeben werden");
			
		}

		@Override
		public void onSuccess(Bewertung result) {
			Window.alert("Die Bewertung wurde erfolgreich abgegeben");
			Showcase showcase = new AlleBewerbungenFromAuschreibung(aus, is);
			RootPanel.get("Details").clear();
			RootPanel.get("Details").add(showcase);
			
		}
		
	}
	public class BewerbungStatus implements AsyncCallback<Bewerbung>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Der Bewerbungsstatus konnte nicht verändert werden");
			
		}

		@Override
		public void onSuccess(Bewerbung result) {
			Window.alert("Der Status der Bewerbung wurde zu 'Angenommen' geändert");
			
		}
		
	}
	
	
	

}
