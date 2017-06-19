package de.hdm.ITProjekt.client.gui;

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
import de.hdm.ITProjekt.shared.bo.Projektmarktplatz;

public class DialogBoxProjekte extends DialogBox {

	AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
	
	private IdentitySelection identitySelection = null;
	
	VerticalPanel vpanel = new VerticalPanel();
	HorizontalPanel hpanel = new HorizontalPanel();
//	HorizontalPanel h_panel = new HorizontalPanel();
	Button ok = new Button("Ok");
	Button abbrechen = new Button("Abbrechen");
	
	Label pmp = new Label ("Projektmarktplatz: ");
	
	
	Label projektbezeichnung = new Label ("Projektbezeichnung: ");
	TextArea bezeichnung = new TextArea();
	
	Label projektbeschreibung = new Label ("Projektbeschreibung: ");
	TextArea beschreibung = new TextArea();
	
	Label label_startdatum = new Label("Startdatum");
	
	Label label_enddatum = new Label("Enddatum");
	
	DateBox startdatum = new DateBox();
	
	DateBox enddatum = new DateBox();
	
	
	private Projekt projekt_dialogbox = new Projekt();
	FlexTable projektseite = new FlexTable();
	
	private Projektmarktplatz p1 = new Projektmarktplatz();
	
	
	public DialogBoxProjekte(final Projektmarktplatz selectedobjectinprojekt){
		this.p1 = selectedobjectinprojekt;
		
		Label label_objekt = new Label(selectedobjectinprojekt.getBez());
		
		
		this.setText("Projekt anlegen");
		this.setAnimationEnabled(false);
		this.setGlassEnabled(true);
//		this.setStylePrimaryName("dialogbox-projekt");
		
		ok.setStylePrimaryName("button");
		abbrechen.setStylePrimaryName("button");
		
		hpanel.add(ok);
		hpanel.add(abbrechen);
		
		// Create a date picker
		DatePicker datepicker_startdatum = new DatePicker();
		
		
	    DatePicker datepicker_enddatum = new DatePicker();
	    //   final Label text = new Label();
	 // Set the value in the text box when the user selects a date
	    datepicker_startdatum.addValueChangeHandler(new ValueChangeHandler<Date>() {

			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				Date date = event.getValue();
				String dateString = DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_TIME_MEDIUM).format(new Date());
				}
		});
	    datepicker_startdatum.setValue(new Date(), true);
	    
	    
	    datepicker_enddatum.addValueChangeHandler(new ValueChangeHandler<Date>() {
			
			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				Date date = event.getValue();
				String dateString = DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_TIME_MEDIUM).format(new Date());
//				text.setText(dateString);
			}
		});
	 // Set the default value
	    datepicker_enddatum.setValue(new Date(), true);

	    
	    abbrechen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				hide();
			}
		});
	    
	    // Anlegen der Funktion für den ClickHandel des Buttons "OK"
	     
	    ok.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
			   
				if (bezeichnung.getText().isEmpty()){
					Window.alert("Bitte geben Sie ein Projektenamen an");
				}if (beschreibung.getText().isEmpty()){
					Window.alert("Bitte geben Sie eine Projektbeschreibung an");
				}else{
					((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
					 
					if (adminService == null) {
					 AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
					 }
					adminService.createProjekt(startdatum.getValue(), enddatum.getValue(), bezeichnung.getText(), beschreibung.getText(), 1, new addProjekteinDB());
				    
					}
				
			}
		});
	    
	    
	    
//	    RootPanel.get().add(text);
//	    RootPanel.get().add(datePicker);
	    
	    DateTimeFormat dateformat = DateTimeFormat.getFormat("dd.MM.yyyy");
		startdatum.setFormat(new DateBox.DefaultFormat(dateformat));
		enddatum.setFormat(new DateBox.DefaultFormat(dateformat));
	
		projektseite.setWidget(1, 0, projektbezeichnung);
		projektseite.setWidget(1, 1, bezeichnung);
		projektseite.setWidget(2, 0, projektbeschreibung);
		projektseite.setWidget(2, 1, beschreibung);
		projektseite.setWidget(3, 0, label_startdatum);
		projektseite.setWidget(3, 1, startdatum);
		projektseite.setWidget(4, 0, label_enddatum);
		projektseite.setWidget(4, 1, enddatum);
		projektseite.setWidget(5, 0, pmp);
		projektseite.setWidget(5, 1, label_objekt);
		vpanel.add(projektseite);
		vpanel.add(hpanel);
		this.add(vpanel);
	
	}	
	
	private class addProjekteinDB implements AsyncCallback<Projekt>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler beim Laden der Daten in die Datenbank");
		}

		@Override
		public void onSuccess(Projekt result) {
			Window.alert("Projekt wurde in die Datenbank eingetragen");
			hide();
			Showcase showcase = new Projekte(p1);
        	RootPanel.get("Details").clear();
			RootPanel.get("Details").add(showcase);
		}
	}
	
	private class GetPersonCallback implements AsyncCallback<Person>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Die Person wurde nicht gefunden");
			
		}

		@Override
		public void onSuccess(Person result) {
			if (result != null){
				((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
				 
				if (adminService == null) {
				 AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
				 }
				 adminService.createProjekt(startdatum.getValue(), enddatum.getValue(), bezeichnung.getText(), beschreibung.getText(), result.getID(), new addProjekteinDB());
				 //(startdatum.getValue(), enddatum.getValue(), bezeichnung.getText(), beschreibung.getText(), result.getID(), new addProjekteinDB());
			}
			
		}
		
	} 
}
