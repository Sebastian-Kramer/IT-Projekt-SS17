package de.hdm.ITProjekt.client.gui;

import com.google.gwt.user.client.ui.DialogBox;
import java.util.Date;
import java.util.Vector;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.CellTable;
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
import com.google.gwt.user.datepicker.client.DatePicker;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.client.Showcase;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatz;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;
import de.hdm.ITProjekt.shared.bo.Person;
import de.hdm.ITProjekt.shared.bo.Projekt;

/**
 * Die Klasse bietet die Möglichkeit Details zu einem Projekt einzusehen.
 * Mithilfe von <code>adminService.getProjektByID</code> und <code>adminService.getPersonByID</code>
 * werden die Informationen des Projekts und über den Projektleiter in den TextBoxen ausgegeben.
 * 
 * @author Raphael
 *
 */
public class DialogBoxProjektdetails extends DialogBox {

	AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
	
	private	VerticalPanel vpanel = new VerticalPanel();
	private	HorizontalPanel hpanel = new HorizontalPanel();
	
	private Button openProjekt = new Button("Projekt öffnen");
	private Button close = new Button("Zurück");
	
	private Label projektleiter = new Label("Projektleiter");
	private Label kontaktdaten = new Label("Kontaktdaten:");
	private Label projektbeschreibung = new Label("Projektbeschreibung");
	private Label email = new Label("E-Mail");
	private Label strasse = new Label ("Straße");
	private Label ort = new Label ("Ort");
	
	private FlexTable form = new FlexTable();
	
	private TextBox email_box = new TextBox();
	private TextBox strasse_box = new TextBox();
	private TextBox ort_box = new TextBox();
	private TextBox projektleiter_box = new TextBox();
	private TextArea projektbeschreibung_text = new TextArea();
	
	private Projekt projekt;
	private Person person;
	
	IdentitySelection is = null;
	
	public DialogBoxProjektdetails(final Projekt projekt, final IdentitySelection is){
		this.projekt = projekt;
		this.is = is;
		
		((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
		 if (adminService == null) {
	      adminService = GWT.create(AdministrationProjektmarktplatz.class);
	    }
		 
		 adminService.getProjektByID(projekt.getID(), new getProjektAusDB());
		 adminService.getPersonbyID(projekt.getProjektleiter_ID(), new getPersonAusDB());
		
		 
		 setText(projekt.getName());
		 setAnimationEnabled(true);
		 setGlassEnabled(true);
		 
		 email_box.setReadOnly(true);
		 strasse_box.setReadOnly(true);
		 ort_box.setReadOnly(true);
		 projektleiter_box.setReadOnly(true);
		 projektbeschreibung_text.setReadOnly(true);
		 
			form.setWidget(1, 0, projektleiter);
			form.setWidget(1, 1, projektleiter_box);
			form.setWidget(2, 0, kontaktdaten);
			form.setWidget(3, 0, email);
			form.setWidget(3, 1, email_box);
			form.setWidget(4, 0, strasse);
			form.setWidget(4, 1, strasse_box);
			form.setWidget(5, 0, ort);
			form.setWidget(5, 1, ort_box);
			form.setWidget(6, 0, projektbeschreibung);
			form.setWidget(7, 0, projektbeschreibung_text);
			
			vpanel.add(form);
			hpanel.add(openProjekt);
			hpanel.add(close);
			vpanel.add(hpanel);
			this.add(vpanel);
			
		 close.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				DialogBoxProjektdetails.this.hide();
				
				
			}
			 
		 });
		 
		 /**
		  * Öffnen eines Projekts
		  */
		 
		 openProjekt.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				DialogBoxProjektdetails.this.hide();
				Showcase showcase = new Projektseite(projekt, is);
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
				
				
				
			}
			 
		 });
		 
	}
	
	
	
	private class getProjektAusDB implements AsyncCallback<Projekt>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Projekt konnte nicht geladen werden");
			
		}

		@Override
		public void onSuccess(Projekt result) {
			projektbeschreibung_text.setText(result.getBeschreibung());
			
			
		}
		
	}
	
	private class getPersonAusDB implements AsyncCallback<Person>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Projektleiter konnte nicht geladen werden");
			
		}

		@Override
		public void onSuccess(Person result) {
			projektleiter_box.setText(result.getVorname() + " " + result.getName());
			strasse_box.setText(result.getStrasse() + " " + result.getHausnummer());
			ort_box.setText(result.getPlz() + " " + result.getOrt());
			email_box.setText(result.getEmail());
			
		}
		
	}
	
	}

	

