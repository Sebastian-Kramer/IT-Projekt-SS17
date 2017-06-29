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
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;

import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatz;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;
import de.hdm.ITProjekt.server.AdministrationProjektmarktplatzImpl;
import de.hdm.ITProjekt.server.db.BewerbungMapper;
import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;
import de.hdm.ITProjekt.shared.bo.Bewerbung;
import de.hdm.ITProjekt.shared.bo.Eigenschaft;
import de.hdm.ITProjekt.shared.bo.Organisationseinheit;
import de.hdm.ITProjekt.shared.bo.Person;

public class DialogBoxDetailsBewerbung extends DialogBox{
	
	AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
	VerticalPanel vp = new VerticalPanel();
	
	Button schliessen = new Button("schliessen");
	
	TextArea bewerbungstext = new TextArea();
	FlexTable bewerbungstextft = new FlexTable();
	
	private CellTable<Eigenschaft> ct_eigenschaft = new CellTable<Eigenschaft>();
	
	private Label personAnrede = new Label("Anrede: ");
	private Label personVorname = new Label("Vorname: ");
	private Label personName = new Label("Name: ");
	private Label personEmail = new Label("E-Mail: ");
	private Label info = new Label("Persönliche Daten des Bewerbers: ");
	
	private TextBox anredeBox = new TextBox();
	private TextBox vornameBox = new TextBox();
	private TextBox nameBox = new TextBox();
	private TextBox emailBox = new TextBox();
	private TextBox nameTeam = new TextBox();
	private TextBox nameUn = new TextBox();
	
	private Bewerbung bewerbungId;
	private Person p;
	private IdentitySelection is;
	
	public DialogBoxDetailsBewerbung(Bewerbung selectedId, IdentitySelection is){
		this.bewerbungId = selectedId;
		this.is = is;
		
		ct_eigenschaft.setWidth("100%");
		schliessen.setStylePrimaryName("navi-button");
		setText("Bewerbung ");
		setAnimationEnabled(true);
		setGlassEnabled(true);
		this.center();

		bewerbungstext.setReadOnly(true);
		bewerbungstext.setText(selectedId.getBewerbungstext());
		bewerbungstext.setCharacterWidth(30);
		bewerbungstext.setVisibleLines(30);

		if(is.getSelectedIdentityAsObject() instanceof Person){
		((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
		 if (adminService == null) {
	      adminService = GWT.create(AdministrationProjektmarktplatz.class);
	    }	 
		adminService.getPersonFromBewerbung(selectedId.getOrga_ID(), new BewerberDatails());
		anredeBox.setReadOnly(true);
		vornameBox.setReadOnly(true);
		nameBox.setReadOnly(true);
		emailBox.setReadOnly(true);
		
		bewerbungstextft.setWidget(2, 0, personAnrede);
		bewerbungstextft.setWidget(2, 1, anredeBox);
		
		bewerbungstextft.setWidget(3, 0, personVorname);
		bewerbungstextft.setWidget(3, 1, vornameBox);
		
		bewerbungstextft.setWidget(4, 0, personName);
		bewerbungstextft.setWidget(4, 1, nameBox);
		
		bewerbungstextft.setWidget(5, 0, personEmail);
		bewerbungstextft.setWidget(5, 1, emailBox);
		}
		
		
		bewerbungstextft.setWidget(0, 0, bewerbungstext);
		bewerbungstextft.setWidget(0, 1, ct_eigenschaft);
		
		bewerbungstextft.setWidget(1, 0, info);
		
	
		
		bewerbungstextft.setWidget(5, 0, schliessen);
		
		vp.add(bewerbungstextft);
		
		this.add(vp);
	
		
	
		schliessen.addClickHandler(new ClickHandler(){
		public void onClick(ClickEvent event){
			DialogBoxDetailsBewerbung.this.hide();
		}
	});
		
		Column<Eigenschaft, String> wert = 
				new Column<Eigenschaft, String>(new ClickableTextCell()){

			@Override
			public String getValue(Eigenschaft object) {
				// TODO Auto-generated method stub
				return object.getWert();
			}
		
		};
		Column<Eigenschaft, String> name = 
				new Column<Eigenschaft, String>(new ClickableTextCell()){

			@Override
			public String getValue(Eigenschaft object) {
				// TODO Auto-generated method stub
				return object.getName();
			}
	
		};
		ct_eigenschaft.setTitle("Das sind alle Eigenschaften des Bewerbers");
		ct_eigenschaft.addColumn(wert, "Wert"); 
		ct_eigenschaft.addColumn(name, "Name");
		
		
	}
	
	public class BewerberDatails implements AsyncCallback<Person>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Die Daten des Bewerbers konnten nicht geladen werden");
			
		}

		@Override
		public void onSuccess(Person result) {
			
			anredeBox.setText(result.getAnrede());
			vornameBox.setText(result.getVorname());
			nameBox.setText(result.getName());
			emailBox.setText(result.getEmail());
			
			Window.alert("Die Daten des Bewerbers wurden erfolgreich geladen");
			
			((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
			 if (adminService == null) {
		      adminService = GWT.create(AdministrationProjektmarktplatz.class);
		    }
			adminService.getOrgaEinheitFromBewerbung(bewerbungId.getOrga_ID(), new OrgaeinheitFromBewerbung());
			
		}
		
	}
	public class OrgaeinheitFromBewerbung implements AsyncCallback<Organisationseinheit>{

		@Override
		public void onFailure(Throwable caught) {
			
			Window.alert(" " + bewerbungId.getOrga_ID());
			
		}

		@Override
		public void onSuccess(Organisationseinheit result) {
			
			((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
			 if (adminService == null) {
		      adminService = GWT.create(AdministrationProjektmarktplatz.class);
		    }
			
			adminService.getAllEigenschaftenFromOrga(result.getPartnerprofil_ID(), new AllEigenschaftenFromBewerber());
			
		}
		
	}
	
	public class AllEigenschaftenFromBewerber implements AsyncCallback<Vector<Eigenschaft>>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Vector<Eigenschaft> result) {
			ct_eigenschaft.setRowData(0, result);
			ct_eigenschaft.setRowCount(result.size(), true);
			
		}
		
	}
}

