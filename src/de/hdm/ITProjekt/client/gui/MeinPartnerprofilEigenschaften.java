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
import de.hdm.ITProjekt.shared.bo.Projektmarktplatz;

public class MeinPartnerprofilEigenschaften extends Showcase{

	private static ClickHandler currentClickHandler = null;
	private static ClickEvent currentClickEvent = null;
	
	private IdentitySelection identitySelection = null;
	private Menubar mb = null;
	
	AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
	
	CellTable<Eigenschaft> pe_alleEigenschaften = new CellTable<Eigenschaft>();
	
	private Person user = new Person();
	private Eigenschaft eigen;
	
	public MeinPartnerprofilEigenschaften(Person person){
		this.user = person;
	}
	
	//Festlegen der Variabeln, um VerticalPanel und und die Flextables anzulegen
	private VerticalPanel vpanel = new VerticalPanel();
	HorizontalPanel hpanel = new HorizontalPanel();
	
	private FlexTable pe_form = new FlexTable();
	private FlexTable pe_buttonPanel = new FlexTable();
	private static DialogBox db_team = new DialogBox();
	private static DialogBox db_unternehmen = new DialogBox();
	
	private Button eigenschaften = new Button("Eigenschaften anzeigen");
	private Button speichern = new Button("Speichern");
	private Button abbrechen = new Button("Abbrechen");
	

	private TextBox erstellungsDatum = new TextBox();
	private TextBox aenderungsDatum = new TextBox();
	private TextBox wert = new TextBox();
	private TextArea eigenschaft = new TextArea();
	
	private Label edatum = new Label("Das Erstelldatum: ");
	private Label adatum = new Label("Das Änderungsdatum: ");
	private Label wertLabel = new Label("Der Wert: ");
	private Label eigenschaftLabel = new Label("Mit den folgenden Eigenschaft: ");

	final SingleSelectionModel<Eigenschaft> ssm_alleEigenschaften = new SingleSelectionModel<Eigenschaft>();
	
	
	@Override
	protected String getHeadlineText() {
		return "<h2> Mein Partnerprofil mit allen Eigenschaften </h2>";
	}

	@Override
	protected void run() {
		((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
		 if (adminService == null) {
	      adminService = GWT.create(AdministrationProjektmarktplatz.class);
	    }
		 adminService.getPartnerprofilfromPerson(user.getID(), new getEigenschaftByPartnerprofil());
		 
		 erstellungsDatum.setReadOnly(true);
//		 aenderungsDatum.setReadOnly(true);
//		 wert.setReadOnly(true);
//		 eigenschaft.setReadOnly(true);
		 
		//Stylen der Buttons
		eigenschaften.setStylePrimaryName("myprofil-button");
//		speichern.setStylePrimaryName("myprofil-button");
//		abbrechen.setStylePrimaryName("myprofil-button");
		
		pe_alleEigenschaften.setWidth("100%");
		
		hpanel.add(eigenschaften);
		
		vpanel.add(pe_alleEigenschaften);
		
		this.add(hpanel);
		this.add(vpanel);
		
		pe_alleEigenschaften.setSelectionModel(ssm_alleEigenschaften);
		
		ssm_alleEigenschaften.addSelectionChangeHandler(new Handler() {
			
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
		//Legt den Abstand zwischen diesen Zellen fest. Parameter:Beabstandet den Zwischenzellenabstand in Pixeln				
//		vpanel.setSpacing(4);
//		
//		
//		
//		pe_form.setWidget(0, 1, erstellungsDatum);
//		pe_form.setWidget(0, 0, edatum);
//		
//		pe_form.setWidget(1,  1, aenderungsDatum);
//		pe_form.setWidget(1, 0, adatum);
//		
//		pe_form.setWidget(2,  1, wert);
//		pe_form.setWidget(2, 0, wertLabel);
//		
//		pe_form.setWidget(3,  1, eigenschaft);
//		pe_form.setWidget(3, 0, eigenschaftLabel);
//		
//
//		
//		pe_buttonPanel.setWidget(0, 0, eigenschaften);
//		pe_buttonPanel.setWidget(0, 1, speichern);
//		pe_buttonPanel.setWidget(0, 2, abbrechen);
//		
//		speichern.setVisible(false);
//		abbrechen.setVisible(false);
//		
//		vpanel.add(pe_buttonPanel);
//		vpanel.add(pe_form);
//		
//		this.add(vpanel);
//		this.setSpacing(4);
		
		
		eigenschaften.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				Showcase showcase = new EigenschaftenHinzufuegen(user);
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);

				
		}
		
	});
			
	}
	
	
	
	
	
	
	private class getEigenschaftByPartnerprofil implements AsyncCallback<Eigenschaft> {

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Eigenschaft result) {
			
			
		}

	
	
	}

}

