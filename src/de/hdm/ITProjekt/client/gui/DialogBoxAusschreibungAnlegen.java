package de.hdm.ITProjekt.client.gui;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.cell.client.ClickableTextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
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

public class DialogBoxAusschreibungAnlegen extends DialogBox {
	
	AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
	
	private VerticalPanel vp = new VerticalPanel();
	private HorizontalPanel hp = new HorizontalPanel();
	
	private Button createAusschreibung = new Button("Stellenausschreibung anlegen");
	private Button cancel = new Button("abbrechen");
	private Button hinzufuegen = new Button("Partnerprofil hinzufügen");
	
	private CellTable <Eigenschaft> ct_eigenschaften = new CellTable<Eigenschaft>();
	
	IdentitySelection is = null;
	
	private TextArea ausschreibungstext = new TextArea();
	private TextArea ausschreibungsbez = new TextArea();
	
	private Label ablauffristLabel = new Label("Bewerbungfrist: ");
	private Label bez = new Label("Name der Stellenausschreibung: ");
	private Label text = new Label("Informationen zur Ausschreibung: ");
	
	private DateBox ablaufDatum = new DateBox();
	
	private FlexTable ausschreibungstextft = new FlexTable();
	private FlexTable pe_form = new FlexTable();
	
	private Ausschreibung ausschreibung_dialog = new Ausschreibung();
	private Eigenschaft eigenschaft = new Eigenschaft();
	
	private Partnerprofil profil = new Partnerprofil();
	private Person person1 = new Person();
	private Projekt projekt1 = new Projekt();
	
//	public DialogBoxAusschreibungAnlegen(Projekt projekt, Person person){
//		this.projekt = projekt;
//		this.person = person;
//	}
	

	
	public DialogBoxAusschreibungAnlegen (final Projekt projekt, final IdentitySelection is){
		this.projekt1 = projekt;
		this.is = is;
		
		setText("Ausschreibung anlegen");
		setAnimationEnabled(true);
		setGlassEnabled(true);
		
		
		this.projekt1 = projekt;
		
		hp.add(createAusschreibung);
		hp.add(cancel);
		ausschreibungsbez.setCharacterWidth(40);
		ausschreibungsbez.setVisibleLines(1);
		ausschreibungstext.setCharacterWidth(40);
		ausschreibungstext.setVisibleLines(30);
		ausschreibungstextft.setWidget(1, 0, bez);
		ausschreibungstextft.setWidget(1, 1, ausschreibungsbez);
		ausschreibungstextft.setWidget(2, 0, text);
		ausschreibungstextft.setWidget(2, 1, ausschreibungstext);
		ausschreibungstextft.setWidget(3, 0, ablauffristLabel);
		ausschreibungstextft.setWidget(3, 1, ablaufDatum);
		
		vp.add(ausschreibungstextft);
		vp.add(ct_eigenschaften);
		vp.add(hinzufuegen);
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
		
		hinzufuegen.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				adminService.addPartnerprofil(profil, new AsyncCallback<Partnerprofil>(){

					@Override
					public void onFailure(Throwable caught) {
						
						
					}

					@Override
					public void onSuccess(Partnerprofil result) {
						DialogBoxEigenschaftHinzufuegen dialog_eigenschaft = new DialogBoxEigenschaftHinzufuegen(result);
						Window.alert(" " + result.getID());
						profil.setID(result.getID());
						dialog_eigenschaft.center();
						
					}
					
				});
				
			}
			
		});
		
		cancel.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				DialogBoxAusschreibungAnlegen.this.hide();
				
			}
			
		});
		
	
		
		createAusschreibung.addClickHandler(new ClickHandler(){
			

			@Override
			public void onClick(ClickEvent event) {
				
			ausschreibung_dialog.setBezeichnung(ausschreibungsbez.getText());
			ausschreibung_dialog.setAusschreibungstext(ausschreibungstext.getText());
			ausschreibung_dialog.setDatum(ablaufDatum.getValue());
			ausschreibung_dialog.setOrga_ID(projekt.getProjektleiter_ID());
			ausschreibung_dialog.setProjekt_ID(projekt.getID());
			ausschreibung_dialog.setPartnerprofil_ID(profil.getID());
			
				
				if(ausschreibungsbez.getText().isEmpty()){
					Window.alert("Bitte eine Stellenbezeichnung hinzufügen");
					if(ausschreibungstext.getText().isEmpty()){
						Window.alert("Bitte eine Stellenbeschreibung hinzfügen");
					
					if(profil.getID() == 0){
						Window.alert("Bitte ein Partnerprofil hinzufügen");
					}
					}	
				}
				else{
					adminService.addAusschreibung(ausschreibung_dialog, new addAusschreibungInDB(){
						
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
					
//					ct_eigenschaften.addColumn(name,"Fähigkeit");
//					ct_eigenschaften.addColumn(wert, "Wert");
//					filltableeigenschaften();
					
				}
				
			}
			
			
		});
		
		DateTimeFormat dateformat = DateTimeFormat.getFormat("yyyy-MM-dd");
		ablaufDatum.setFormat(new DateBox.DefaultFormat(dateformat));
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
	
	
	
		
	private class addAusschreibungInDB implements AsyncCallback<Ausschreibung>{

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Ausschreibung konnte nicht angelegt werden");
				
			}

			@Override
			public void onSuccess(Ausschreibung result) {
				Window.alert("Die Ausschreibung wurde erfolgreich angelegt");
				hide();
				Showcase showcase = new Projektseite(projekt1, is);
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
				
				
				
			}
			
		}
	}
	
		
			


