package de.hdm.ITProjekt.client.gui;

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
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;

import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.client.Showcase;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatz;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;
import de.hdm.ITProjekt.shared.bo.Ausschreibung;
import de.hdm.ITProjekt.shared.bo.Eigenschaft;
import de.hdm.ITProjekt.shared.bo.Partnerprofil;
import de.hdm.ITProjekt.shared.bo.Person;
import de.hdm.ITProjekt.shared.bo.Projekt;

import com.google.gwt.user.client.ui.TextArea;

public class DialogBoxEigenschaftHinzufuegen extends DialogBox {
	
	
	AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
	
	private VerticalPanel vpanel = new VerticalPanel();
	private HorizontalPanel hpanel = new HorizontalPanel();
	
	private Button hinzufuegen = new Button("Eigenschaft hinzufuegen");
	private Button abbrechen = new Button("Abbrechen");
	
	private ListBox auswahlEigenschaften = new ListBox();
	private ListBox wertEigenschaften = new ListBox();
	
	private Label auswahlLabel = new Label("Eigenschaften:");
	private Label wertLabel = new Label("Kenntnisstand:");
	
	private FlexTable pe_form = new FlexTable();
	
	private Eigenschaft eigenschaft = new Eigenschaft();
	
	
	
	public DialogBoxEigenschaftHinzufuegen(final Ausschreibung ausschreibung, final Partnerprofil partnerprofil, final Person person, final Projekt projekt){
		this.setAnimationEnabled(false);
		this.setGlassEnabled(true);
		this.setText("Eigenschaft zu Partnerprofil hinzufuegen");
		
		auswahlEigenschaften.addItem("Java");
		auswahlEigenschaften.addItem("PHP");
		auswahlEigenschaften.addItem("Word");
		auswahlEigenschaften.addItem("Excel");
		auswahlEigenschaften.addItem("PowerPoint");
		auswahlEigenschaften.addItem("C++");
		

		wertEigenschaften.addItem("Grundkenntisse");
		wertEigenschaften.addItem("Fortgeschritten");
		wertEigenschaften.addItem("Experte");	
		
		pe_form.setWidget(0, 0, auswahlLabel);
		pe_form.setWidget(0, 1, auswahlEigenschaften);
		pe_form.setWidget(1, 0, wertLabel);
		pe_form.setWidget(1, 1, wertEigenschaften);
		pe_form.setWidget(2, 0, hinzufuegen);
		pe_form.setWidget(2, 1, abbrechen);
		
		vpanel.add(pe_form);
		this.add(vpanel);
		
		
		hinzufuegen.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
				 if (adminService == null) {
			     adminService = GWT.create(AdministrationProjektmarktplatz.class);
			   }
				 eigenschaft.setPartnerprofil_ID(partnerprofil.getID());
				 eigenschaft.setName(auswahlEigenschaften.getSelectedItemText());
				 eigenschaft.setWert(wertEigenschaften.getSelectedItemText());
				 adminService.createEigenschaft(eigenschaft, new AsyncCallback<Eigenschaft>(){

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(Eigenschaft result) {
						DialogBoxPartnerprofilAnlegen box_profil = new DialogBoxPartnerprofilAnlegen(ausschreibung,person,projekt);
						DialogBoxEigenschaftHinzufuegen.this.hide();
						box_profil.center();
						
					}
					 
				 });
				
				
			}
			
		});
	}

}
