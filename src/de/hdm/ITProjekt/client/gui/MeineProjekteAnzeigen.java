package de.hdm.ITProjekt.client.gui;

import java.util.Vector;

import com.google.gwt.cell.client.ClickableTextCell;
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
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;

import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.client.Showcase;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatz;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;
import de.hdm.ITProjekt.shared.bo.Person;
import de.hdm.ITProjekt.shared.bo.Projekt;

public class MeineProjekteAnzeigen extends Showcase{


	AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
	
	CellTable<Projekt> ct_meineProjekte = new CellTable<Projekt>();

	
	HorizontalPanel hpanel_projekte = new HorizontalPanel();
	VerticalPanel vpanel_projekte = new VerticalPanel();
	
	Button projektmarktplatz = new Button("Projekte Suchen");
	Button delete_projekt = new Button("Projekt Löschen");
	
	private final SingleSelectionModel<Projekt> ssm_projekt = new SingleSelectionModel<Projekt>();
	
	private Projekt projekt;
	private Person person;
	
	public MeineProjekteAnzeigen(Person person) {
		this.person = person;
	}

	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "<h2> Meine Projekte </h3>";
	}

	@Override
	protected void run() {

		
		RootPanel.get("Details").setWidth("100%");
		ct_meineProjekte.setWidth("100%", true);
		ct_meineProjekte.setSelectionModel(ssm_projekt);
		hpanel_projekte.add(delete_projekt);
		hpanel_projekte.add(projektmarktplatz);
		
		vpanel_projekte.add(ct_meineProjekte);
		this.add(hpanel_projekte);
		this.add(vpanel_projekte);
		
		projektmarktplatz.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Showcase showcase = new ProjektmarktplatzSeite();
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
			}
		});
		
		delete_projekt.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
				 if (adminService == null) {
			      adminService = GWT.create(AdministrationProjektmarktplatz.class);
			    }
				 adminService.deleteTeilnahme(person, ssm_projekt.getSelectedObject().getProjektmarktplatz_ID(), new deleteTeilnehmerEinesProjekts());
			}
		});
		
		ssm_projekt.addSelectionChangeHandler(new Handler(){

			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				
			}
			
		});
		
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
						return object.getBeschreibung();
						}
				    };	
		ct_meineProjekte.addColumn(projektname, "Projektname");		    
		ct_meineProjekte.addColumn(startdatum, "Startdatum");	
		ct_meineProjekte.addColumn(enddatum, "Enddatum");	
		ct_meineProjekte.addColumn(beschreibung, "Beschreibung");	
		
		filltableMeineProjekte();
		
	}
	private void filltableMeineProjekte(){	
		((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
		 if (adminService == null) {
	      adminService = GWT.create(AdministrationProjektmarktplatz.class);
	    }
		adminService.getAllProjekteByTeilnahme(person, new getProjekteByTeilnahme());
	}	
	
	private class deleteTeilnehmerEinesProjekts implements AsyncCallback<Void>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Löschen fehlgeschlagen");
		}

		@Override
		public void onSuccess(Void result) {
			Window.alert("Löschen erfolgreich");
			Showcase showcase = new MeineProjekteAnzeigen(person);
			RootPanel.get("Details").clear();
			RootPanel.get("Details").add(showcase);
			
		}
		
	}
	
	private class getProjekteByTeilnahme implements AsyncCallback <Vector<Projekt>>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Vector<Projekt> result) {
			if (result != null){
				ct_meineProjekte.setRowData(0, result);
				ct_meineProjekte.setRowCount(result.size(), true);
			}else{
				Window.alert("Sie haben keine Projekte");
			}
			
		}
		
	}
	
	
	
	
	
	
	
	
	
	
}
