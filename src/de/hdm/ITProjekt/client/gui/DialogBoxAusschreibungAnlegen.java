package de.hdm.ITProjekt.client.gui;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;

import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.client.Showcase;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;
import de.hdm.ITProjekt.shared.bo.Ausschreibung;

import com.google.gwt.user.client.ui.TextArea;

public class DialogBoxAusschreibungAnlegen extends DialogBox {
	
	AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
	
	private VerticalPanel vp = new VerticalPanel();
	private HorizontalPanel hp = new HorizontalPanel();
	
	Button createAusschreibung = new Button("Stellenausschreibung anlegen");
	Button cancel = new Button("abbrechen");
	
	private TextArea ausschreibungstext = new TextArea();
	private TextArea ausschreibungsbez = new TextArea();
	
	private Label ablauffristLabel = new Label("Abblauffrist");
	
	private DateBox ablaufDatum = new DateBox();
	
	private FlexTable ausschreibungstextft = new FlexTable();
	
	private Ausschreibung ausschreibung_dialog = new Ausschreibung();
	

	
	public DialogBoxAusschreibungAnlegen (){
		setText("Ausschreibung anlegen");
		setAnimationEnabled(true);
		setGlassEnabled(true);
		
		hp.add(createAusschreibung);
		hp.add(cancel);
		ausschreibungsbez.setCharacterWidth(40);
		ausschreibungsbez.setVisibleLines(1);
		ausschreibungstext.setCharacterWidth(40);
		ausschreibungstext.setVisibleLines(30);
		ausschreibungstextft.setWidget(1, 0, ausschreibungsbez);
		ausschreibungstextft.setWidget(2, 0, ausschreibungstext);
		ausschreibungstextft.setWidget(3, 0, ablauffristLabel);
		ausschreibungstextft.setWidget(3, 1, ablaufDatum);
		vp.add(ausschreibungstextft);
		vp.add(hp);
		this.add(vp);
		
		final DatePicker datepicker_datum = new DatePicker();
		
		datepicker_datum.addValueChangeHandler(new ValueChangeHandler <Date>(){

			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				Date date = event.getValue();
				String dateString = DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_TIME_MEDIUM).format(new Date());
				
			}
			
			
		});
		datepicker_datum.setValue(new Date(), true);
		
		cancel.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				DialogBoxAusschreibungAnlegen.this.hide();
				
			}
			
		});
		
		createAusschreibung.addClickHandler(new ClickHandler(){
			

			@Override
			public void onClick(ClickEvent event) {
//				ausschreibung_dialog.setBezeichnung(ausschreibungsbez.getText());
//				ausschreibung_dialog.setAusschreibungstext(ausschreibungstext.getText());
//				ausschreibung_dialog.setDatum(datepicker_datum.getValue());
				
				if(ausschreibungsbez.getText().isEmpty()){
					Window.alert("Bitte eine Stellenbezeichnung hinzufügen");
					if(ausschreibungstext.getText().isEmpty()){
						Window.alert("Bitte eine Stellenbeschreibung hinzfügen");
					}
					
				}
				else{
					((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
					 
					if (adminService == null) {
					 AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
					 }
					adminService.addAusschreibung(ausschreibungstext.getText(), ausschreibungsbez.getText(), ablaufDatum.getValue(), new addAusschreibungInDB());
//					adminService.addAusschreibung(ausschreibung_dialog, new addAusschreibungInDB());
					
				}
			}
			
		});
		
		DateTimeFormat dateformat = DateTimeFormat.getFormat("yyyy-MM-dd");
		ablaufDatum.setFormat(new DateBox.DefaultFormat(dateformat));
	}
	
		
	private class addAusschreibungInDB implements AsyncCallback<Ausschreibung>{

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Ausschreibung konnte nicht angelegt werden");
				
			}

			@Override
			public void onSuccess(Ausschreibung result) {
				Window.alert("Die Ausschreibung wurde erfolgreich angelegt");
				hide();
				Showcase showcase = new StellenausschreibungenSeite();
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
				
				
			}
			
		}
	}
	
		
			


