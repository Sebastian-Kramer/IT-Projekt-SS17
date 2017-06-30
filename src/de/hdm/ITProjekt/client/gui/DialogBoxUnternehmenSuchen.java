package de.hdm.ITProjekt.client.gui;

import java.util.Vector;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.ClickableTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.client.Menubar;
import de.hdm.ITProjekt.client.Showcase;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatz;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;
import de.hdm.ITProjekt.shared.bo.Person;
import de.hdm.ITProjekt.shared.bo.Projekt;
import de.hdm.ITProjekt.shared.bo.Projektmarktplatz;
import de.hdm.ITProjekt.shared.bo.Unternehmen;

public class DialogBoxUnternehmenSuchen extends DialogBox{


	CellTable<Unternehmen> ct_allUnternehmen = new CellTable<Unternehmen>();
	private Person person = new Person();
	private VerticalPanel vpanel = new VerticalPanel();
	private HorizontalPanel hpanel = new HorizontalPanel();
	private Button abbrechen = new Button("Abbrechen");
	AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
	final SingleSelectionModel<Unternehmen> ssm_allunternehmen = new SingleSelectionModel<Unternehmen>();

	private ButtonCell auswaehlenButton = new ButtonCell();
	
	public DialogBoxUnternehmenSuchen(final IdentitySelection is){
		vpanel.add(ct_allUnternehmen);
		vpanel.add(abbrechen);
		this.add(vpanel);
		abbrechen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
			hide();	
			}
		});

		ct_allUnternehmen.setSelectionModel(ssm_allunternehmen);
		
		
		Column<Unternehmen, String> name = 
				    new Column<Unternehmen, String>(new ClickableTextCell())  {
				    
						@Override
						public String getValue(Unternehmen object) {
							return object.getName();
						}
						    
		 };
		 Column<Unternehmen, String> plz = 
				    new Column<Unternehmen, String>(new ClickableTextCell())  {
				    
						@Override
						public String getValue(Unternehmen object) {
							return String.valueOf(object.getPlz());
						}
						    
		 };
		 Column<Unternehmen, String> ort = 
				    new Column<Unternehmen, String>(new ClickableTextCell())  {
				    
						@Override
						public String getValue(Unternehmen object) {
							return object.getOrt();
						}
						    
		 };
		 
		 Column<Unternehmen, String> nummer = 
				    new Column<Unternehmen, String>(new ClickableTextCell())  {
				    
						@Override
						public String getValue(Unternehmen object) {
							return String.valueOf(object.getHausnummer());
						}
						    
		 };
		 Column<Unternehmen, String> strasse = 
				    new Column<Unternehmen, String>(new ClickableTextCell())  {
				    
						@Override
						public String getValue(Unternehmen object) {
							return object.getStrasse();
						}
						    
		 };
		 Column<Unternehmen, String> buttonCell = 
				    new Column<Unternehmen, String>(auswaehlenButton)  {
				    
						@Override
						public String getValue(Unternehmen object) {
							object = ssm_allunternehmen.getSelectedObject();
							return "Auswählen";
						}
						    
		 };
		 buttonCell.setFieldUpdater(new FieldUpdater<Unternehmen, String>() {

			@Override
			public void update(int index, Unternehmen object, String value) {
				object = ssm_allunternehmen.getSelectedObject();
				adminService.getUnByID(object.getID(), new AsyncCallback<Unternehmen>(){

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(Unternehmen result) {
						is.getUser().getID();
						is.getUser().getisAdmin();
						is.getUser().getAnrede();
						is.getUser().getEmail();
						is.getUser().getHausnummer();
						is.getUser().getName();
						is.getUser().getOrt();
						is.getUser().getPartnerprofil_ID();
						is.getUser().getPlz();
						is.getUser().getStrasse();
						is.getUser().setUN_ID(result.getID());
						adminService.updatePerson(is.getUser(), new AsyncCallback<Person>(){

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub
								
							}

							@Override
							public void onSuccess(Person result) {
								Window.alert("Unternehmen erfolgreich beigetreten");
								hide();
								RootPanel.get("idendity").clear();
								RootPanel.get("Navigator").clear();
								RootPanel.get("Details").clear();
								Showcase showcase = new MeinProfilAnzeigen(is);
								Menubar mb = new Menubar(is.getUser());
								mb.setIdSelection(is);
								RootPanel.get("Details").add(showcase);
								RootPanel.get("idendity").add(new IdentitySelection(is.getUser(), mb));
								RootPanel.get("Navigator").add(mb);
						
							}
							
						});
					
					}
				});
				
			}
		});
		 ct_allUnternehmen.addColumn(name, "Name");
		 ct_allUnternehmen.addColumn(plz, "PLZ");
		 ct_allUnternehmen.addColumn(ort, "Ort");
		 ct_allUnternehmen.addColumn(strasse, "Straße");
		 ct_allUnternehmen.addColumn(nummer, "Hausnummer");
		 ct_allUnternehmen.addColumn(buttonCell, "Beitreten");
		 
		 ((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
		 if (adminService == null) {
	      adminService = GWT.create(AdministrationProjektmarktplatz.class);
	    }
		adminService.getAllUnternehmen(new AsyncCallback<Vector<Unternehmen>>(){

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(Vector<Unternehmen> result) {
				ct_allUnternehmen.setRowData(0, result);
				ct_allUnternehmen.setRowCount(result.size(), true);
				
			}
			
		});
		
		
	}
}
