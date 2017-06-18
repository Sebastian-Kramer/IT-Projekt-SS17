package de.hdm.ITProjekt.client.gui;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.cell.client.ClickableTextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
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
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.client.Showcase;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatz;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;
import de.hdm.ITProjekt.shared.bo.Ausschreibung;
import de.hdm.ITProjekt.shared.bo.Bewerbung;
import de.hdm.ITProjekt.shared.bo.Organisationseinheit;
import de.hdm.ITProjekt.shared.bo.Projekt;
import de.hdm.ITProjekt.shared.bo.Unternehmen;

public class Projektseite extends Showcase{

	AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
	
	private Button createStelle = new Button("Stellenausschreibung anlegen");
	
	VerticalPanel vp_projekt = new VerticalPanel();
	HorizontalPanel hp_projekt = new HorizontalPanel();
	
	CellTable<Ausschreibung> ct_projektausschreibungen = new CellTable<Ausschreibung>();
	
	final SingleSelectionModel<Ausschreibung> ssm = new SingleSelectionModel<>();
	
	private Projekt selectedProjekt;
	
	public Projektseite(Projekt selectedObject){
		this.selectedProjekt = selectedObject;
	}
	
	
	@Override
	protected String getHeadlineText() {
		return "Projektverwaltung";
	}

	@Override
	protected void run() {
		
		RootPanel.get("Details").setWidth("100%");
		ct_projektausschreibungen.setWidth("100%");
		
		vp_projekt.add(ct_projektausschreibungen);
		this.add(hp_projekt);
		this.add(vp_projekt);
		hp_projekt.add(createStelle);
		
		
			createStelle.addClickHandler(new ClickHandler(){

				@Override
				public void onClick(ClickEvent event) {
					DialogBoxAusschreibungAnlegen dialogBox = new DialogBoxAusschreibungAnlegen();
					int left = Window.getClientWidth() / 3;
					int top = Window.getClientHeight() / 3;
					dialogBox.center();
					
					
				}
				
			});
			
		
		
		Column<Ausschreibung, String> bezeichnung =
					new Column<Ausschreibung, String>(new ClickableTextCell()){

						@Override
						public String getValue(Ausschreibung object) {
						
							return object.getBezeichnung();
						}
			
		};
		Column<Ausschreibung, String> ablauffrist =
				new Column<Ausschreibung, String>(new ClickableTextCell()){

					@Override
					public String getValue(Ausschreibung object) {
						// TODO Auto-generated method stub
						return object.getDatum().toString();
					}
		
	};
	
	
	ct_projektausschreibungen.addColumn(bezeichnung, "Stellenbezeichnung");
	ct_projektausschreibungen.addColumn(ablauffrist, "Ablauffrist");
	
	filltableauschreibungen();
	
	}

	
	private void filltableauschreibungen(){
		((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
		 if (adminService == null) {
	      adminService = GWT.create(AdministrationProjektmarktplatz.class);
	    }
		AsyncCallback<Vector<Ausschreibung>> callback = new AsyncCallback<Vector<Ausschreibung>>(){

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Das hat nicht geklappt");
				
			}

			@Override
			public void onSuccess(Vector<Ausschreibung> result) {
				
				if(result != null){
					ct_projektausschreibungen.setRowData(0, result);
					ct_projektausschreibungen.setRowCount(result.size(), true);
				}else{
					Window.alert("Keine Ausschreibungen");
				}
				
				
			}
			
		};
		adminService.findByProjekt(selectedProjekt, callback);
	}

}
