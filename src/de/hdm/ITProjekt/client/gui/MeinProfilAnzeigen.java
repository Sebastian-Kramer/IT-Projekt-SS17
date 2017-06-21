package de.hdm.ITProjekt.client.gui;

import java.util.Vector;

import com.google.gwt.cell.client.ClickableTextCell;
import com.google.gwt.core.shared.GWT;
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
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;

import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.client.Menubar;
import de.hdm.ITProjekt.client.Showcase;
import de.hdm.ITProjekt.server.AdministrationProjektmarktplatzImpl;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatz;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;
import de.hdm.ITProjekt.shared.bo.Bewerbung;
import de.hdm.ITProjekt.shared.bo.Eigenschaft;
import de.hdm.ITProjekt.shared.bo.Partnerprofil;
import de.hdm.ITProjekt.shared.bo.Person;

public class MeinProfilAnzeigen extends Showcase{
	
	private static ClickHandler currentClickHandler = null;
	private static ClickEvent currentClickEvent = null;
	
	private IdentitySelection identitySelection = null;
	private Menubar mb = null;

	public MeinProfilAnzeigen(Person person){
		this.user = person;
	}
	//Festlegen der Variabeln, um VerticalPanel und und die Flextables anzulegen
	private VerticalPanel vpanel = new VerticalPanel();
	private VerticalPanel eigeneDaten = new VerticalPanel();
	private VerticalPanel partnerprofilDaten = new VerticalPanel();
	private HorizontalPanel hpanel = new HorizontalPanel();
	private HorizontalPanel buttonPartnerprofilPanel = new HorizontalPanel();
	
	CellTable<Eigenschaft> pe_alleEigenschaften = new CellTable<Eigenschaft>();
	
	final SingleSelectionModel<Eigenschaft> ssm_alleEigenschaften = new SingleSelectionModel<Eigenschaft>();
	
	private FlexTable form = new FlexTable();
	private FlexTable pe_form = new FlexTable();
	private FlexTable pe_buttonPanel = new FlexTable();
//	private FlexTable ftable_team = new FlexTable();
//	private FlexTable ftable_unternehmen = new FlexTable();
	private FlexTable ft_buttonPanel = new FlexTable();
	private static DialogBox db_team = new DialogBox();
	private static DialogBox db_unternehmen = new DialogBox();
//	private Button closeTeam = new Button("Schließen");
//	private Button closeUnternehmen = new Button("Schließen");

	//Anlegen der Buttons für verschiedene Funktionen
	private Button bearbeiten = new Button("Bearbeiten");
	private Button speichern = new Button("Speichern");
	private Button abbrechen = new Button("Abbrechen");
	private Button newTeam = new Button("Team hinzufügen");
	private Button newUN = new Button("Unternehmen hinzufügen");
	private Button eigenschaften = new Button("Eigenschaften hinzufügen");
	
	private Button partnerprofil = new Button("Weiter zum Partnerprofil");
	
//	private Button teamErstellenButton = new Button("Team Erstellen");
//	private MultiWordSuggestOracle oracle_teamHinzufuegen= new MultiWordSuggestOracle();
//	private SuggestBox sb_teamHinzufuegen = new SuggestBox(oracle_teamHinzufuegen);
//	private Button teamHinzufuegenButton = new Button("OK");
//	
//	private Button unternehmenErstellenButton = new Button("Unternehmen Erstellen");
//	private MultiWordSuggestOracle oracle_unternehmenHinzufuegen= new MultiWordSuggestOracle();
//	private SuggestBox sb_unternehmenHinzufuegen = new SuggestBox(oracle_unternehmenHinzufuegen);
//	private Button unternehmenHinzufuegenButton = new Button("OK");

	//Erstellen der Text- bzw. ListBoxen
	private ListBox anredeListBox = new ListBox();
	private TextBox anredeBox = new TextBox();
	private TextBox vnameBox = new TextBox();
	private TextBox nnameBox = new TextBox();
	private TextBox strasseBox = new TextBox();
	private TextBox hausnrBox = new TextBox();
	private TextBox plzBox = new TextBox();
	private TextBox ortBox = new TextBox();
	private TextBox emailBox = new TextBox();
	
	//Erstellen der Labels
	private Label email = new Label("E-Mail");
	private Label anrede = new Label("Anrede");
	private Label vorname = new Label("Vorname");
	private Label nachname = new Label("Nachname");
	private Label straße = new Label("Straße");
	private Label hausnr = new Label("Hausnummer");
	private Label plz = new Label("Postleitzahl");
	private Label ort = new Label("Ort");
	
//	((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
//	 if (adminService == null) {
//   adminService = GWT.create(AdministrationProjektmarktplatz.class);
//	 }
	
	AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
	
	private Person user = new Person();
	private Eigenschaft eigen = new Eigenschaft();
	private Eigenschaft selectedObject_alleEigenschaften = new Eigenschaft();

	
		@Override
		protected String getHeadlineText() {
			return "<h2>Mein Profil</h2>";
		}
		@Override
		protected void run() {

			((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
			 if (adminService == null) {
		      adminService = GWT.create(AdministrationProjektmarktplatz.class);
		    }
			adminService.getPersonbyID(user.getID(), new getPersonausDB());
			
			vpanel.add(partnerprofil);
			
			pe_alleEigenschaften.setWidth("100%");
			
			emailBox.setReadOnly(true);
			vnameBox.setReadOnly(true);
			nnameBox.setReadOnly(true);
			anredeBox.setReadOnly(true);
			strasseBox.setReadOnly(true);
			hausnrBox.setReadOnly(true);
			plzBox.setReadOnly(true);
			ortBox.setReadOnly(true);
			
			//Stylen der Buttons
			bearbeiten.setStylePrimaryName("myprofil-button");
			speichern.setStylePrimaryName("myprofil-button");
			abbrechen.setStylePrimaryName("myprofil-button");
			newTeam.setStylePrimaryName("myprofil-button");
			newUN.setStylePrimaryName("myprofil-button");
			eigenschaften.setStylePrimaryName("myprofil-button");
			
			
			anredeListBox.addItem("Herr");
			anredeListBox.addItem("Frau");

			
			
			//Legt den Abstand zwischen diesen Zellen fest. Parameter:Beabstandet den Zwischenzellenabstand in Pixeln			
			vpanel.setSpacing(8);
			
			form.setWidget(0, 1, emailBox);
			form.setWidget(0, 0, email);
			
			form.setWidget(1,  1, anredeBox);
			form.setWidget(1, 0, anrede);
			
			form.setWidget(2,  1, vnameBox);
			form.setWidget(2, 0, vorname);
			
			form.setWidget(3,  1, nnameBox);
			form.setWidget(3, 0, nachname);
		
			form.setWidget(4,  1, strasseBox);
			form.setWidget(4, 0, straße);
			
			form.setWidget(5,  1, hausnrBox);
			form.setWidget(5, 0, hausnr);
			
			form.setWidget(6,  1, plzBox);
			form.setWidget(6, 0, plz);
			
			form.setWidget(7,  1, ortBox);
			form.setWidget(7, 0, ort);
			
			
			
			ft_buttonPanel.setWidget(0, 0, bearbeiten);
			ft_buttonPanel.setWidget(0, 1, speichern);
			ft_buttonPanel.setWidget(0, 2, abbrechen);
			ft_buttonPanel.setWidget(0, 3, newTeam);
			ft_buttonPanel.setWidget(0, 4, newUN);
			
			speichern.setVisible(false);
			abbrechen.setVisible(false);
			newTeam.setVisible(false);
			newUN.setVisible(false);

			vpanel.add(ft_buttonPanel);
			vpanel.add(form);
			partnerprofilDaten.add(eigenschaften);
			partnerprofilDaten.add(pe_alleEigenschaften);
			buttonPartnerprofilPanel.add(partnerprofilDaten);
			hpanel.add(vpanel);
			hpanel.add(partnerprofilDaten);			
			this.add(hpanel);


//			hpanel.add(vpanel);
			

			this.setSpacing(8);
			
		
		Column<Eigenschaft, String> name = 
				    new Column<Eigenschaft, String>(new ClickableTextCell())  {
				    
						@Override
						public String getValue(Eigenschaft object) {
							return object.getName();
						}
						    
		 };
		 Column<Eigenschaft, String> wert = 
				    new Column<Eigenschaft, String>(new ClickableTextCell())  {
				    
						@Override
						public String getValue(Eigenschaft object) {
							return object.getWert();
						}
						    
		 };
		 
		 ssm_alleEigenschaften.addSelectionChangeHandler(new Handler(){
				
				public void onSelectionChange(final SelectionChangeEvent event) {
					
					selectedObject_alleEigenschaften = ssm_alleEigenschaften.getSelectedObject();
					if(selectedObject_alleEigenschaften != null){
						
						Showcase showcase= new EigenschaftenHinzufuegen(user);
						RootPanel.get("Details").clear();
						RootPanel.get("Details").add(showcase);
			}else{
				Window.alert("Zum Bearbeiten muss eine Eigenschaft ausgewählt werden");
				}
			}
		});
		 
		 pe_alleEigenschaften.addColumn(name, "Bereich");
		 pe_alleEigenschaften.addColumn(wert, "Ausprägung der Eigenschaft");
		
		bearbeiten.addClickHandler(new ClickHandler(){
			
			public void onClick(ClickEvent event) {
				//Listbox anstelle von Textbox setzten
				form.setWidget(1, 1, anredeListBox);
				//ReadOnly auf false setzten
				emailBox.setReadOnly(false);
				vnameBox.setReadOnly(false);
				nnameBox.setReadOnly(false);
				anredeBox.setReadOnly(false);
				strasseBox.setReadOnly(false);
				hausnrBox.setReadOnly(false);
				plzBox.setReadOnly(false);
				ortBox.setReadOnly(false);
				
				bearbeiten.setVisible(false);
				speichern.setVisible(true);
				abbrechen.setVisible(true);
				
			}
		
		});
		
		abbrechen.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Showcase showcase = new MeinProfilAnzeigen(user);
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
			}
		});
		
		speichern.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
				 if (adminService == null) {
			      adminService = GWT.create(AdministrationProjektmarktplatz.class);
			    }
				 ClientsideSettings.getpmpVerwaltung().
					getPersonbyID(user.getID(), new ProfilBearbeitenCallback());
				
			}
		});
		
		partnerprofil.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				Showcase showcase = new MeinPartnerprofilEigenschaften(user);
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
				
			}
		});
		}
		
	
		
		private class ProfilBearbeitenCallback implements AsyncCallback<Person>{

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Das Bearbeiten des Profils ist fehlgeschlagen. Bitte versuche es erneut.");
				
			}

			@Override
			public void onSuccess(Person result) {
				result.setAnrede(anredeListBox.getItemText(anredeListBox.getSelectedIndex()));
				result.setHausnummer(Integer.parseInt(hausnrBox.getText()));
				result.setName(nnameBox.getText());
				result.setOrt(ortBox.getText());
				result.setPlz(Integer.parseInt(plzBox.getText()));
				result.setStrasse(strasseBox.getText());
				result.setVorname(vnameBox.getText());
				((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
				 if (adminService == null) {
			      adminService = GWT.create(AdministrationProjektmarktplatz.class);
			    }
				ClientsideSettings.getpmpVerwaltung().savePerson(result, new PersonSpeichernCallback());
				
			}}
		
		private class PersonSpeichernCallback implements AsyncCallback<Void>{

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Beim Speichern der Person ist etwas schief gegangen");
				
			}

			@Override
			public void onSuccess(Void result) {
				Showcase showcase = new MeinProfilAnzeigen(user);
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
			}
			
		}
		
		private class getPersonausDB implements AsyncCallback<Person> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Daten der Person konnten nicht ausgelesen werden");
			
		}

		@Override
		public void onSuccess(Person result) {
			emailBox.setText(result.getEmail());
			vnameBox.setText(result.getVorname());
			nnameBox.setText(result.getName());
			anredeBox.setText(result.getAnrede());
			strasseBox.setText(result.getStrasse());
			hausnrBox.setText(Integer.toString(result.getHausnummer()));
			plzBox.setText(Integer.toString(result.getPlz()));
			ortBox.setText(result.getOrt());
			
			
			
		}


		
	}
}
	

	
	
