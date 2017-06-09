package de.hdm.ITProjekt.client.gui;

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
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.client.Showcase;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatz;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;
import de.hdm.ITProjekt.shared.bo.Ausschreibung;
import de.hdm.ITProjekt.shared.bo.Bewerbung;
import de.hdm.ITProjekt.shared.bo.Organisationseinheit;
import de.hdm.ITProjekt.shared.bo.Unternehmen;

public class MeineBewerbungenSeite extends Showcase {
	
	private static ClickHandler currentClickHandler = null;
	private static ClickEvent currentClickEvent = null;
	
	AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
	
	CellTable<Bewerbung> ct_alleBewerbungen = new CellTable<Bewerbung>();
	
	private TextBox bewerbungbox = new TextBox();
	HorizontalPanel hpanel_bewerbung = new HorizontalPanel();
	VerticalPanel vpanel = new VerticalPanel();
	
	final SingleSelectionModel<Bewerbung> ssm = new SingleSelectionModel<>();
	
	Button bewerbungAnzeigen = new Button ("Bewerbung anzeigen");
	

	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "Meine Bewerbungen";
	}

	@Override
	protected void run() {
		
		RootPanel.get("Details").setWidth("100%");
		ct_alleBewerbungen.setWidth("100%");
		
		vpanel.add(ct_alleBewerbungen);
		
		
		this.add(hpanel_bewerbung);
		this.add(vpanel);
		hpanel_bewerbung.add(bewerbungAnzeigen);
		
		ct_alleBewerbungen.setSelectionModel(ssm);
		
		Column<Bewerbung, String> erstelldatum =
				new Column<Bewerbung, String>(new ClickableTextCell()){

					@Override
					public String getValue(Bewerbung object) {
						
						return object.getErstelldatum().toString();
					}
		};
		
		
		bewerbungAnzeigen.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				if(ssm != null){
				DialogBoxBewerbung dialogBox = new DialogBoxBewerbung(ssm.getSelectedObject().getBewerbungstext());
					int left = Window.getClientWidth() / 3;
					int top = Window.getClientHeight() / 3;
					dialogBox.setPopupPosition(left, top);
					dialogBox.show();
				}
			else{
					Window.alert("Fehler");
				
			}
			}
		});		
			
			
		
//		ct_alleBewerbungen.addDomHandler(new ClickHandler()
//				{
//
//					@Override
//					public void onClick(ClickEvent event) {
//						
//						if(ssm != null){
//							DialogBoxBewerbung dialogBox = new DialogBoxBewerbung(ssm.getSelectedObject().getBewerbungstext());
//							int left = Window.getClientWidth() / 3;
//							int top = Window.getClientHeight() / 3;
//							dialogBox.setPopupPosition(left, top);
//							dialogBox.show();
//						}
//						else{
//							Window.alert("Fehler");
//						}
//					}
//			
//				}, ClickEvent.getType());
		
//		Column<Bewerbung, String> ausschreibung =
//				new Column<Bewerbung,String>(new ClickableTextCell()){
//
//					@Override
//					public String getValue(Bewerbung object) {
//						
//						;
//					}
			
				
		
//		Column<Unternehmen, String> unternehmen =
//				new Column<Unternehmen, String>(new ClickableTextCell()){
//
//					@Override
//					public String getValue(Unternehmen object) {
//						
//						return object.getName();
//					}
//		};
		
//		Column<Ausschreibung, String> ausschreibung =
//				new Column <Ausschreibung, String>(new ClickableTextCell()){
//
//					@Override
//					public String getValue(Ausschreibung object) {
//						
//						return object.getBezeichnung();
//					}
//		};
		
	
		ct_alleBewerbungen.addColumn(erstelldatum, "Erstelldatum");
		filltablebewerbungen();
	}
	
	private void filltablebewerbungen(){
		
		((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
		 if (adminService == null) {
	      adminService = GWT.create(AdministrationProjektmarktplatz.class);
	    }		
		 
		 
		 AsyncCallback<Vector<Bewerbung>> callback = new AsyncCallback<Vector<Bewerbung>>(){

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Dumm gelaufen");
				
			}

			@Override
			public void onSuccess(Vector<Bewerbung> result) {
				ct_alleBewerbungen.setRowData(0, result);
				ct_alleBewerbungen.setRowCount(result.size(), true);
				
			}
			
		 };
		 adminService.getAllBewerbungen(callback);
			 
		 }

					
				
			

		
		
	}
	
	


