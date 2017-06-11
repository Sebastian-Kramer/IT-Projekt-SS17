package de.hdm.ITProjekt.client.gui;

import java.util.Date;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;

import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;

public class DialogBoxProjekte extends DialogBox {

	AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
	VerticalPanel vpanel = new VerticalPanel();
	HorizontalPanel hpanel = new HorizontalPanel();
//	HorizontalPanel h_panel = new HorizontalPanel();
	Button ok = new Button("Ok");
	Button abbrechen = new Button("Abbrechen");
	
	Label projektbezeichnung = new Label ("Projektbezeichnung: ");
	TextArea bezeichnung = new TextArea();
	
	Label projektbeschreibung = new Label ("Projektbeschreibung: ");
	TextArea beschreibung = new TextArea();
	
	Label label_startdatum = new Label("Startdatum");
	
	Label label_enddatum = new Label("Enddatum");
	
	FlexTable projektseite = new FlexTable();
	
	public DialogBoxProjekte(){
		this.setText("Projekt anlegen");
		this.setAnimationEnabled(false);
		this.setGlassEnabled(true);
		
		ok.setStylePrimaryName("button");
		abbrechen.setStylePrimaryName("button");
		
		hpanel.add(ok);
		hpanel.add(abbrechen);
		
		
		   // Create a date picker
		DatePicker startdatum = new DatePicker();
		
		
	    DatePicker enddatum = new DatePicker();
//	    final Label text = new Label();
	 // Set the value in the text box when the user selects a date
	    startdatum.addValueChangeHandler(new ValueChangeHandler<Date>() {

			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				Date date = event.getValue();
				String dateString = DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_TIME_MEDIUM).format(new Date());
				}
		});
	    startdatum.setValue(new Date(), true);
	    
	    
	    enddatum.addValueChangeHandler(new ValueChangeHandler<Date>() {
			
			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				Date date = event.getValue();
				String dateString = DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_TIME_MEDIUM).format(new Date());
//				text.setText(dateString);
			}
		});
	 // Set the default value
	    enddatum.setValue(new Date(), true);
	   
//	    RootPanel.get().add(text);
//	    RootPanel.get().add(datePicker);
	projektseite.setWidget(1, 0, projektbezeichnung);
	projektseite.setWidget(1, 1, bezeichnung);
	projektseite.setWidget(2, 0, projektbeschreibung);
	projektseite.setWidget(2, 1, beschreibung);
	projektseite.setWidget(3, 0, label_startdatum);
	projektseite.setWidget(3, 1, startdatum);
	projektseite.setWidget(4, 0, label_enddatum);
	projektseite.setWidget(4, 1, enddatum);
	vpanel.add(projektseite);
	vpanel.add(hpanel);
	this.add(vpanel);
	
	}
	
	
	
}
