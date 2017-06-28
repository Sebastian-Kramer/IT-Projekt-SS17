package de.hdm.ITProjekt.client.gui;

import java.sql.Date;
import java.util.Vector;

import com.google.gwt.cell.client.ClickableTextCell;
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
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;

import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.client.Showcase;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatz;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;
import de.hdm.ITProjekt.shared.bo.Ausschreibung;
import de.hdm.ITProjekt.shared.bo.Eigenschaft;
import de.hdm.ITProjekt.shared.bo.Partnerprofil;
import de.hdm.ITProjekt.shared.bo.Person;
import de.hdm.ITProjekt.shared.bo.Projekt;
 


public class DialogBoxPartnerprofilAnlegen extends DialogBox {
	
	AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
	
	private VerticalPanel vp = new VerticalPanel();
	private HorizontalPanel hp = new HorizontalPanel();
	
	private Button hinzufuegen = new Button("Eigenschaft hinzufügen");
	private Button anlegen = new Button("Ausschreibung anlegen");
	private Button abbrechen = new Button("Abbrechen");
	
	private CellTable <Eigenschaft> ct_eigenschaften = new CellTable<Eigenschaft>();
	
	private Partnerprofil profil = new Partnerprofil();
	private Person person = new Person();
	private Ausschreibung ausschreibung = new Ausschreibung();
	private Projekt projekt = new Projekt();
	
	
	

	
	
	DialogBoxPartnerprofilAnlegen( final Ausschreibung ausschreibung, final Person person, final Projekt projekt){
		this.ausschreibung = ausschreibung;
		this.person = person;
		this.projekt = projekt;
		
		setText("Partnerprofil zu Ausschreibung hinzufügen");
		setAnimationEnabled(true);
		setGlassEnabled(true);
		ct_eigenschaften.setWidth("100%");
		
		vp.add(hinzufuegen);
		vp.add(ct_eigenschaften);
		this.add(vp);
		
		hp.add(anlegen);
		hp.add(abbrechen);
		this.add(hp);
	
		
		hinzufuegen.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
			
				
			}
			
		});
		
		
		anlegen.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
				 if (adminService == null) {
			      adminService = GWT.create(AdministrationProjektmarktplatz.class);
			    }	
				 profil.setErstellungsdatum(profil.getErstellungsdatum());
				adminService.addPartnerprofil(profil, new AsyncCallback<Partnerprofil>(){

					@Override
					public void onFailure(Throwable caught) { 
						
					}

					@Override
					public void onSuccess(Partnerprofil result) {
						adminService.addAusschreibung(ausschreibung, new AsyncCallback<Ausschreibung>(){

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub
								
							}

							@Override
							public void onSuccess(Ausschreibung result) {
								Window.alert("Die Ausschreibung und das dazugehörige Partnerprofil wurden erfolgreich angelegt");
//								Showcase showcase = new Projektseite(projekt, is);
								RootPanel.get("Details").clear();
//								RootPanel.get("Details").add(showcase);
								
								
							}
							
						});
						
					}
					
				});
			}
			
		});
		
		
		Column<Eigenschaft, String> name = 
			    new Column<Eigenschaft, String>(new ClickableTextCell())  {
			    
					@Override
					public String getValue(Eigenschaft object) {
						return object.getName();
					}
					    
	 };
	 Column<Eigenschaft, String> wert = 
			    new Column<Eigenschaft, String>(new ClickableTextCell())  {
			    
					@Override
					public String getValue(Eigenschaft object) {
						return object.getWert();
					}
					
	 };
	 
	 
		ct_eigenschaften.addColumn(name,"Fähigkeit");
		ct_eigenschaften.addColumn(wert, "Wert");
		filltableeigenschaften();
		
		
	}
	
	private void filltableeigenschaften(){
		
		((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
		 if (adminService == null) {
	      adminService = GWT.create(AdministrationProjektmarktplatz.class);
	    }	
		 adminService.getAllEigenschaftenbyPartnerprofilID(profil.getID(), new AsyncCallback<Vector<Eigenschaft>>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Vector<Eigenschaft> result) {
				ct_eigenschaften.setRowData(0, result);
				ct_eigenschaften.setRowCount(result.size(), true);
			}
			 
		 });
	}


}
