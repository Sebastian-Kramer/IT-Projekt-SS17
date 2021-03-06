package de.hdm.ITProjekt.client.gui;

import java.util.Vector;

import com.google.gwt.cell.client.ClickableTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.*;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatz;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;
import de.hdm.ITProjekt.shared.bo.Person;
import de.hdm.ITProjekt.shared.bo.Projekt;
import de.hdm.ITProjekt.shared.bo.Projektmarktplatz;
import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.client.Menubar;
import de.hdm.ITProjekt.client.Showcase;

public class ProjektmarktplatzSeite extends Showcase{

	private static ClickHandler currentClickHandler = null;
	private static ClickEvent currentClickEvent = null;
		
	AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
	
	CellTable<Projektmarktplatz> ct_alleProjektmarktplaetze = new CellTable<Projektmarktplatz>();
	
	private Projektmarktplatz p1;
	private Person person;
	private IdentitySelection is = null;
	private Menubar menubar = null;
	
	public ProjektmarktplatzSeite(IdentitySelection is){
		this.is = is;
	}
	public ProjektmarktplatzSeite(IdentitySelection is, Menubar menubar){
		this.is = is;
		this.menubar = menubar;
	}
	
	private HorizontalPanel hpanel_projektmarktplatz = new HorizontalPanel();
	private VerticalPanel vpanel = new VerticalPanel();
	
	// Buttons NUR erstellen
	private Button bearbeitungsmodus_deaktiv = new Button("Bearbeitungsansicht");

	private Label labelprojektmarktplatz = new Label("/Projektmarktplatz");
	private Anchor zurstartseite = new Anchor("Startseite");
	
	private HorizontalPanel hpanel_navigation = new HorizontalPanel();
	
	private FlexTable ft_anchor = new FlexTable();
	// Erlaubt, dass in der Tabelle nur eins ausgewählt werden darf
	

	final SingleSelectionModel<Projektmarktplatz> ssm_alleProjektmarktplaetze = new SingleSelectionModel<Projektmarktplatz>();
	final SingleSelectionModel<Projektmarktplatz> ssm_eigeneProjektmarktplaetze = new SingleSelectionModel<Projektmarktplatz>();
	
	private Projektmarktplatz selectedObject_alleProjektmarktplaetze;

	
	@Override
	protected String getHeadlineText() {
		return "<h1>Projektmarktplatz Suche</h1>";
	}

	@Override
	protected void run() {
		
		zurstartseite.setStylePrimaryName("navigationanchor");
		labelprojektmarktplatz.setStylePrimaryName("navigationanchor");
		ft_anchor.setWidget(0, 0, zurstartseite);
		ft_anchor.setWidget(0, 1, labelprojektmarktplatz);
		ft_anchor.setCellPadding(10);
		hpanel_navigation.add(ft_anchor);
		//Größe des "div" Containers, sprich der Seite
		RootPanel.get("Details").setWidth("100%");
		// Größe der Tablle im div Container, sprich der Seite
		ct_alleProjektmarktplaetze.setWidth("100%", true);
		// Größe der Tablle im div Container, sprich der Seite
//		ct_eigeneProjektmarktplaetze.setWidth("100%", true);

		
		// Hinzufügen der Buttons und Textbox zum Panel
		if (is.getUser().getisAdmin() == true){
		hpanel_projektmarktplatz.add(bearbeitungsmodus_deaktiv);
		}
		
		
		// Hinzufügen der Tabelle ins VerticalPanel
		vpanel.add(ct_alleProjektmarktplaetze);
//		hpanel_projektmarktplatz.add(ct_alleProjektmarktplaetze);
//		vpanel.add(ct_eigeneProjektmarktplaetze);
				
		// In die seite laden
		this.add(hpanel_navigation);
		this.add(hpanel_projektmarktplatz);
		this.add(vpanel);
		
		
		//Stylen der Buttons 
		bearbeitungsmodus_deaktiv.setStylePrimaryName("bearbungsmodusdeaktiv-button");
//		deleteprojektmarktplatz.setStylePrimaryName("navi-button");
		
		

		ct_alleProjektmarktplaetze.setSelectionModel(ssm_alleProjektmarktplaetze);	
//		ct_eigeneProjektmarktplaetze.setSelectionModel(ssm_eigeneProjektmarktplaetze);

		ct_alleProjektmarktplaetze.setSelectionModel(ssm_alleProjektmarktplaetze);
		
		ssm_alleProjektmarktplaetze.addSelectionChangeHandler(new Handler() {
			
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				//  
				p1 = ssm_alleProjektmarktplaetze.getSelectedObject();
				Showcase showcase = new Projekte(p1, is, menubar);
	        	RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
				
			}
		});

		 Column<Projektmarktplatz, String> linkColumn = 
				    new Column<Projektmarktplatz, String>(new ClickableTextCell())  {
				    
						@Override
						public String getValue(Projektmarktplatz object) {
							return object.getBez();
						}
						    
		 };
		 zurstartseite.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Showcase showcase = new Homeseite();
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
				currentClickHandler=this;
				currentClickEvent=event;
			}
		});
		 bearbeitungsmodus_deaktiv.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Showcase showcase = new ProjektmarktplatzBearbeitungsSeite(is);
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
			}
		});
		 ssm_alleProjektmarktplaetze.addSelectionChangeHandler(new Handler() {
			    @Override
			    public void onSelectionChange(final SelectionChangeEvent event)
			    {
			    	selectedObject_alleProjektmarktplaetze = ssm_alleProjektmarktplaetze.getSelectedObject();
			        if (selectedObject_alleProjektmarktplaetze != null){
			        	
			        	Showcase showcase = new Projekte(selectedObject_alleProjektmarktplaetze, is);
			        	RootPanel.get("Details").clear();
						RootPanel.get("Details").add(showcase);
			        }else{
			        	Window.alert("Kein Projektmarktplatz angew�hlt");
			        }
			    	
			    	
			    }
			});

		ct_alleProjektmarktplaetze.addColumn(linkColumn, "Bezeichnung");
		((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
		 if (adminService == null) {
	      adminService = GWT.create(AdministrationProjektmarktplatz.class);
	    }
		adminService.getProjektmarktplatzAll(new getProjektmarktplatzAusDB());
		
			}
	
	 
	 // Liste erneuern, der Trigger ist das Löschen eines Projektmarktplatzes
	 private void refreshlist(){
		 ((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
		 if (adminService == null) {
	      adminService = GWT.create(AdministrationProjektmarktplatz.class);
	    }
		 
		 AsyncCallback<Vector<Projektmarktplatz>> callback = new AsyncCallback<Vector<Projektmarktplatz>>(){

			@Override
			public void onFailure(Throwable caught) {
				//  
				Window.alert("Fehler beim Laden der Daten aus der Datenbank");
			}
			@Override
			public void onSuccess(Vector<Projektmarktplatz> result) {
				//  
				ct_alleProjektmarktplaetze.setRowData(0, result);
				ct_alleProjektmarktplaetze.setRowCount(result.size(), true);
				
					
				}
			};
		adminService.getProjektmarktplatzAll(callback);
		 
	 }
	
	public class getProjektmarktplatzAusDB implements AsyncCallback<Vector<Projektmarktplatz>>{

		@Override
		public void onFailure(Throwable caught) {
			//  
			Window.alert("Fehler beim Laden der Daten aus der Datenbank");
		}

		@Override
		public void onSuccess(Vector<Projektmarktplatz> result) {
			//  	
			ct_alleProjektmarktplaetze.setRowData(0, result);
			ct_alleProjektmarktplaetze.setRowCount(result.size(), true);
			
		}
		
	}
	 
}
	

