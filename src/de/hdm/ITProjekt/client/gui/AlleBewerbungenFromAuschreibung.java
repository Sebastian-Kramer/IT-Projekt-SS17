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
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
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
import de.hdm.ITProjekt.shared.bo.Person;
import de.hdm.ITProjekt.shared.bo.Projekt;
import de.hdm.ITProjekt.shared.bo.Projektmarktplatz;
import de.hdm.ITProjekt.shared.bo.Unternehmen;

public class AlleBewerbungenFromAuschreibung extends Showcase{
	
	AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
	
	private Button anzeigen = new Button("Bewerbung anzeigen");
	private Button zurueck = new Button("Zurück zu den Ausschreibungen");
	
	private ListBox bewertung = new ListBox();

	private VerticalPanel vp_bew = new VerticalPanel();
	private HorizontalPanel hp_bew = new HorizontalPanel();
	private HorizontalPanel hp_bew1 = new HorizontalPanel();
	
	private CellTable<Bewerbung> ct_bewerbungen = new CellTable<Bewerbung>();
	
	private FlexTable form = new FlexTable();
	
	final SingleSelectionModel<Bewerbung> ssm = new SingleSelectionModel<>();
	
	private Ausschreibung selectedAusschreibung;
	private Person angemeldetePerson;
	
	public AlleBewerbungenFromAuschreibung(Ausschreibung a, Person p){
		this.selectedAusschreibung = a;
		this.angemeldetePerson = p;
	}

	@Override
	protected String getHeadlineText() {
		
		return "<h3> Dies sind alle abgegebenen Bewerbungen der ausgewählten Ausschreibung </h3>";
	}

	@Override
	protected void run() {
		
		RootPanel.get("Details").setWidth("100%");
		ct_bewerbungen.setWidth("100%");
		
		anzeigen.setStylePrimaryName("myprofil-button");
		zurueck.setStylePrimaryName("myprofil-button");
		
		bewertung.addItem("0.0");
		bewertung.addItem("0.1");
		bewertung.addItem("0.2");
		bewertung.addItem("0.3");
		bewertung.addItem("0.4");
		bewertung.addItem("0.5");
		bewertung.addItem("0.6");
		bewertung.addItem("0.7");
		bewertung.addItem("0.8");
		bewertung.addItem("0.9");
		bewertung.addItem("1.0");
		
		vp_bew.setSpacing(10);
		
//		form.setWidget(0, 1, bewertung);
//		form.setWidget(0, 0, ct_bewerbungen);
//			
//		hp_bew.add(anzeigen);
//		hp_bew.add(zurueck);
//		hp_bew1.add(form);
//		this.add(hp_bew);
//		this.add(hp_bew1);
		
		if (selectedAusschreibung.getOrga_ID() == angemeldetePerson.getID()){
			Window.alert("Diese Person hat die Ausschreibung angelegt und darf eine Bewertung abgeben");
			form.setWidget(0, 1, bewertung);
			form.setWidget(0, 0, ct_bewerbungen);
			hp_bew.add(anzeigen);
			hp_bew.add(zurueck);
			hp_bew1.add(form);
			this.add(hp_bew);
			this.add(hp_bew1);	
			Column<Bewerbung, String> text = 
					new Column<Bewerbung, String>(new ClickableTextCell()){

				@Override
				public String getValue(Bewerbung object) {
					// TODO Auto-generated method stub
					return object.getBewerbungstext();
				}
			
			};
			Column<Bewerbung, String> erstelldatum = 
					new Column<Bewerbung, String>(new ClickableTextCell()){

				@Override
				public String getValue(Bewerbung object) {
					// TODO Auto-generated method stub
					return object.getErstelldatum().toString();
				}
		
			};
			ct_bewerbungen.addColumn(text, "Bewerbungstext"); 
			ct_bewerbungen.addColumn(erstelldatum, "Einreichungsdatum");
			
			
			filltablebewerbung();
			
		}else{
			Window.alert("Sie haben diese Ausschreibung nicht angelegt und sehen lediglich die Bewerbungen");
			form.setWidget(0, 0, ct_bewerbungen);
			hp_bew.add(zurueck);
			hp_bew1.add(form);
			this.add(hp_bew);
			this.add(hp_bew1);
			
			Column<Bewerbung, String> text = 
					new Column<Bewerbung, String>(new ClickableTextCell()){

				@Override
				public String getValue(Bewerbung object) {
					// TODO Auto-generated method stub
					return object.getBewerbungstext();
				}
			
			};
			Column<Bewerbung, String> erstelldatum = 
					new Column<Bewerbung, String>(new ClickableTextCell()){

				@Override
				public String getValue(Bewerbung object) {
					// TODO Auto-generated method stub
					return object.getErstelldatum().toString();
				}
		
			};
			ct_bewerbungen.addColumn(text, "Bewerbungstext"); 
			ct_bewerbungen.addColumn(erstelldatum, "Einreichungsdatum");
			
			
			filltablebewerbung();
		}
		
		
		
		
	}	
	private void filltablebewerbung(){
		((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
		 if (adminService == null) {
	      adminService = GWT.create(AdministrationProjektmarktplatz.class);
	    }

		 adminService.findBewerbungByAusschreibungId(selectedAusschreibung.getID(), new allBewByAus());

	}
	
	
public class allBewByAus implements AsyncCallback<Vector<Bewerbung>>{

	@Override
	public void onFailure(Throwable caught) {
		Window.alert("Da ist etwas schief gegangen");
		
	}

	@Override
	public void onSuccess(Vector<Bewerbung> result) {
		ct_bewerbungen.setRowData(0, result);
		ct_bewerbungen.setRowCount(result.size(), true);
		Window.alert("Alle Bewerbung auf diese Ausschreibung wurden geladen");
		
	}
	
}










}
