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
import de.hdm.ITProjekt.shared.bo.Partnerprofil;
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
	private ListBox wertEigenschaften = new ListBox();
	
	private Label auswahlLabel = new Label("Eigenschaften:");
	private Label wertLabel = new Label("Kenntnisstand:");
	
	private Eigenschaft eigenschaft1 = new Eigenschaft();
	
	@Override
	protected String getHeadlineText() {

		return "<h2> Mein Partnerprofil </h2></br>Hier können Sie neue Eigenschaften zu ihrem Profil hinzufügen";
	}

	@Override
	protected void run() {
				 
		
			auswahlEigenschaften.addItem("Java");
			auswahlEigenschaften.addItem("PHP");
			auswahlEigenschaften.addItem("Word");
			auswahlEigenschaften.addItem("Excel");
			auswahlEigenschaften.addItem("PowerPoint");
			auswahlEigenschaften.addItem("C++");
			

			wertEigenschaften.addItem("Grundkenntisse");
			wertEigenschaften.addItem("Fortgeschritten");
			wertEigenschaften.addItem("Experte");			
			
			
			speichern.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {

					((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
					 if (adminService == null) {
				     adminService = GWT.create(AdministrationProjektmarktplatz.class);
				   }
					 adminService.getPartnerprofilOfOrganisationseinheit(user, new AsyncCallback<Partnerprofil>(){

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub
								
							}

							@Override
							public void onSuccess(Partnerprofil result) {
							eigenschaft1.setPartnerprofil_ID(result.getID());
							eigenschaft1.setName(auswahlEigenschaften.getSelectedItemText());
							eigenschaft1.setWert(wertEigenschaften.getSelectedItemText());
							adminService.createEigenschaft(eigenschaft1, new EigenschaftHinzufuegen());
							}
							 
						 });
				}
			});
			abbrechen.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					Showcase showcase = new MeinProfilAnzeigen(user);
					RootPanel.get("Details").clear();
					RootPanel.get("Details").add(showcase);
				}
			});
			
			pe_form.setWidget(0, 0, auswahlLabel);
			pe_form.setWidget(0, 1, auswahlEigenschaften);
			pe_form.setWidget(1, 0, wertLabel);
			pe_form.setWidget(1, 1, wertEigenschaften);
			pe_form.setWidget(2, 0, speichern);
			
			vpanel.add(pe_form);
			
			this.add(vpanel);
			this.setSpacing(2);
	
			
	}
	
	private class EigenschaftHinzufuegen implements AsyncCallback<Eigenschaft>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Eigenschaft result) {
			Showcase showcase = new MeinProfilAnzeigen(user);
			RootPanel.get("Details").clear();
			RootPanel.get("Details").add(showcase);
		}
		
	}

}
