package de.hdm.ITProjekt.client.gui;

import java.util.Date;
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
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.client.Menubar;
import de.hdm.ITProjekt.client.Showcase;
import de.hdm.ITProjekt.client.gui.ProjektmarktplatzSeite.getProjektmarktplatzAusDB;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatz;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;
import de.hdm.ITProjekt.shared.bo.Ausschreibung;
import de.hdm.ITProjekt.shared.bo.Person;
import de.hdm.ITProjekt.shared.bo.Projekt;
import de.hdm.ITProjekt.shared.bo.Projektmarktplatz;

public class StellenausschreibungenSeite extends Showcase {

	private static ClickHandler currentClickHandler = null;
	private static ClickEvent currentClickEvent = null;
	
	AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
	
	CellTable<Ausschreibung> ct_alleAusschreibungen = new CellTable<Ausschreibung> ();
	CellTable<Ausschreibung> ct_eigeneAusschreibungen = new CellTable<Ausschreibung>();

	
	HorizontalPanel hpanel_ausschreibung = new HorizontalPanel();
	VerticalPanel vpanel = new VerticalPanel();
	
	//Buttons erstellen
	Button showausschreibung = new Button("Stellenausschreibung anzeigen");
	
	// Erlaubt das anklicken in Tabellen
	
	final SingleSelectionModel <Ausschreibung> ssm = new SingleSelectionModel<>();
	final SingleSelectionModel <Ausschreibung> ssm_fremde = new SingleSelectionModel<Ausschreibung>();
	
	private Ausschreibung a1 = new Ausschreibung();
	private Person p1 = new Person();
	private IdentitySelection is = null;
	private Menubar menubar = null;

	public StellenausschreibungenSeite(){
		
	}

	public StellenausschreibungenSeite(IdentitySelection is, Menubar menubar) {
		this.is = is;
		this.menubar = menubar;
	}

	@Override
	protected String getHeadlineText() {
		return "<h1>Stellenausschreibungen</h1>";
	}

	@Override
	protected void run() {
		
		//Größe des "div" Containers, sprich der Seite
		RootPanel.get("Details").setWidth("100%");
		// Größe der Tablle im div Container, sprich der Seite
		ct_alleAusschreibungen.setWidth("100%", true);
		// Größe der Tablle im div Container, sprich der Seite
		ct_eigeneAusschreibungen.setWidth("100%", true);
		

		hpanel_ausschreibung.add(showausschreibung);

				

		vpanel.add(ct_alleAusschreibungen);
		vpanel.add(ct_eigeneAusschreibungen);
						

		this.add(hpanel_ausschreibung);
		this.add(vpanel);
		

		
		ct_alleAusschreibungen.setSelectionModel(ssm);

		
		Column <Ausschreibung, String> Bezeichnung =
				new Column<Ausschreibung, String>(new ClickableTextCell()){
			
			public String getValue(Ausschreibung object) {
				
				return object.getBezeichnung().toString();
			}	
			
		};
		

Column<Ausschreibung, String> Datum = 
new Column<Ausschreibung, String>(new ClickableTextCell()) {
	
	public String getValue(Ausschreibung object) {
		
		return object.getDatum().toString();
		
	}
	};	


Column <Ausschreibung, String> Stellenbeschreibung =
new Column<Ausschreibung, String>(new ClickableTextCell()) {
	
	public String getValue(Ausschreibung object) {
		
		return object.getAusschreibungstext().toString();
	
	}
	
	};
	
	showausschreibung.addClickHandler(new ClickHandler(){

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			if(ssm !=null){
				
				a1 = ssm.getSelectedObject();
				DialogBoxAusschreibung dialogBox = new DialogBoxAusschreibung(a1, is, menubar);
				int left = Window.getClientHeight() / 3;
				int top = Window.getClientWidth() / 3;
				dialogBox.setPopupPosition(left, top);
				dialogBox.center();
			}
			else{
				Window.alert("Bitte Ausschreibung auswählen");
			}
		}
		
	});
	

	ct_alleAusschreibungen.addColumn(Bezeichnung, "Bezeichnung");
	ct_alleAusschreibungen.addColumn(Datum, "Einstelldatum");
	ct_alleAusschreibungen.addColumn(Stellenbeschreibung, "Stellenbeschreibung");
	
	filltableausschreibung();

		}

	private void filltableausschreibung() {

		((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
		 if (adminService == null) {
	      adminService = GWT.create(AdministrationProjektmarktplatz.class);
	    }
		 
		 AsyncCallback<Vector<Ausschreibung>> callback = new AsyncCallback<Vector<Ausschreibung>>(){

		 	@Override
	public void onFailure(Throwable caught) {

		Window.alert("Fehler beim Laden der Daten aus der Datenbank");
	}

	@Override
	public void onSuccess(Vector<Ausschreibung> result) {
		ct_alleAusschreibungen.setRowData(0, result);
		ct_alleAusschreibungen.setRowCount(result.size(), true);	
		
	}
		 };
		adminService.getAll(callback);

	}
	
	public class getAusschreibungAusDB implements AsyncCallback<Vector<Ausschreibung>>{
	@Override
	public void onFailure(Throwable caught) {

		Window.alert("Fehler");
		
	}

	@Override
	public void onSuccess(Vector<Ausschreibung> result) {

		ct_alleAusschreibungen.setRowData(0, result);
		ct_alleAusschreibungen.setRowCount(result.size(), true);

	}

}
	
	private class getPersonByID implements AsyncCallback<Person>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler Person laden");
			
		}

		@Override
		public void onSuccess(Person result) {
			result = p1;
			
			
		}
		
	}
	

}	
	
	


