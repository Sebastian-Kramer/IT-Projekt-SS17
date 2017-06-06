package de.hdm.ITProjekt.client.gui;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.cell.client.ClickableTextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.client.Showcase;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatz;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;
import de.hdm.ITProjekt.shared.bo.Projekt;
import de.hdm.ITProjekt.shared.bo.Projektmarktplatz;

public class Projekte extends Showcase {

	AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
	
	CellTable<Projekt> ct_alleProjekte = new CellTable<Projekt>();
	final SingleSelectionModel<Projektmarktplatz> ssm = new SingleSelectionModel<Projektmarktplatz>();
	
	private TextBox projektbox = new TextBox();
	HorizontalPanel hpanel_projekte = new HorizontalPanel();
	VerticalPanel vpanel_projekte = new VerticalPanel();
	
	Button projekt = new Button("IchBinEinPlatzhalter");
	
	final SingleSelectionModel<Projekt> ssm_projekt = new SingleSelectionModel<Projekt>();

	
	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void run() {
		RootPanel.get("Details").setWidth("100%");
		ct_alleProjekte.setWidth("100%", true);
		ct_alleProjekte.setSelectionModel(ssm_projekt);
		hpanel_projekte.add(projekt);
		
		vpanel_projekte.add(ct_alleProjekte);
		this.add(hpanel_projekte);
		this.add(vpanel_projekte);
		
		 Column<Projekt, String> projektname = 
				    new Column<Projekt, String>(new ClickableTextCell())  {
				    
						@Override
						public String getValue(Projekt object) {
							// TODO Auto-generated method stub
							
							return object.getName();
						}
				    };
				    
				    
		 Column<Projekt, String> startdatum = 
				    new Column<Projekt, String>(new ClickableTextCell())  {
						    
						@Override
						public String getValue(Projekt object) {
						// TODO Auto-generated method stub
									
						return object.getStartdatum().toString();
						}
				    };		
				    
		  Column<Projekt, String> enddatum = 
				    new Column<Projekt, String>(new ClickableTextCell())  {
								    
			  			@Override
			  			public String getValue(Projekt object) {
			  			// TODO Auto-generated method stub
											
			  			return object.getEnddatum().toString();
			  			}
		  			};			 
		  Column<Projekt, String> beschreibung = 
				  new Column<Projekt, String>(new ClickableTextCell())  {
								    
			  			@Override
						public String getValue(Projekt object) {
						// TODO Auto-generated method stub
											
						return object.getBeschreibung();
						}
				    };	
		ct_alleProjekte.addColumn(projektname, "Projektname");		    
		ct_alleProjekte.addColumn(startdatum, "Startdatum");	
		ct_alleProjekte.addColumn(enddatum, "Enddatum");	
		ct_alleProjekte.addColumn(beschreibung, "Beschreibung");	
	filltableprojekte();
	}
	private void filltableprojekte(){
		
		((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
		 if (adminService == null) {
	      adminService = GWT.create(AdministrationProjektmarktplatz.class);
	    }
		 AsyncCallback<Vector<Projekt>> callback = new AsyncCallback<Vector<Projekt>>(){

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler beim Laden der Daten aus der Datenbank");
			}

			@Override
			public void onSuccess(Vector<Projekt> result) {
				ct_alleProjekte.setRowData(0, result);
				ct_alleProjekte.setRowCount(result.size(), true);
			}
		 };
		adminService.getAllProjekte(callback);	
	}

}