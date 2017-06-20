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
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;

import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.client.Menubar;
import de.hdm.ITProjekt.client.Showcase;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatz;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;
import de.hdm.ITProjekt.shared.bo.Eigenschaft;
import de.hdm.ITProjekt.shared.bo.Person;
import de.hdm.ITProjekt.shared.bo.Projekt;

public class EigenschaftenHinzufuegen extends Showcase{
	
	private IdentitySelection identitySelection = null;
	private Menubar mb = null;
	
	AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
	
	private Person user = new Person();
	
	public EigenschaftenHinzufuegen(Person person){
		this.user = person;
	}
	
	private VerticalPanel vpanel = new VerticalPanel();
	private HorizontalPanel hpanel = new HorizontalPanel();
	
	private FlexTable pe_form = new FlexTable();
	private FlexTable pe_buttonPanel = new FlexTable();
	
	private Button hinzufuegen = new Button("Eigenschaft hinzufügen");
	private Button speichern = new Button("Speichern");
	private Button abbrechen = new Button("Abbrechen");
	
	private ListBox auswahlEigenschaften = new ListBox();
	
	private Label auswahlLabel = new Label("Folgende Eigenschaften können hinzugefügt werden");
	
	
	@Override
	protected String getHeadlineText() {

		return "<h2> Mein Partnerprofil </h2></br>Hier können Sie neue Eigenschaften zu ihrem Profil hinzufügen";
	}

	@Override
	protected void run() {
		
		((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
		 if (adminService == null) {
	     adminService = GWT.create(AdministrationProjektmarktplatz.class);
	   }
		 adminService.getPartnerprofilfromPerson(user.getID(), new getEigenschaftHinzufuegen());

		 
		 
		
		 	vpanel.setSpacing(2);
		 
			auswahlEigenschaften.addItem("Java");
			auswahlEigenschaften.addItem("PHP");
			auswahlEigenschaften.addItem("Word");
			auswahlEigenschaften.addItem("Excel");
			auswahlEigenschaften.addItem("PowerPoint");
			auswahlEigenschaften.addItem("C++");
			
			pe_form.setWidget(5, 1, auswahlEigenschaften);
			pe_form.setWidget(5, 0, auswahlLabel);
			
			vpanel.add(pe_form);
			
			this.add(vpanel);
			this.setSpacing(2);
	}
	
	private class getEigenschaftHinzufuegen implements AsyncCallback<Eigenschaft>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Eigenschaft result) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
