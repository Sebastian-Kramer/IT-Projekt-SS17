package de.hdm.ITProjekt.client.gui;

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
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.client.Menubar;
import de.hdm.ITProjekt.client.Showcase;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;
import de.hdm.ITProjekt.shared.bo.Bewerbung;
import de.hdm.ITProjekt.shared.bo.Partnerprofil;
import de.hdm.ITProjekt.shared.bo.Person;

public class MeinProfilAnzeigen extends Showcase{
	
	private static ClickHandler currentClickHandler = null;
	private static ClickEvent currentClickEvent = null;
	
	AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
	
	private IdentitySelection identitySelection = null;
	private Menubar mb = null;
	Person p = new Person();
	
	public MeinProfilAnzeigen(){
		
	}
	public MeinProfilAnzeigen(IdentitySelection identitySelection, Menubar mb){
		this.identitySelection = identitySelection;
		this.mb = mb;
		p = (Person) identitySelection.getSelectedIdentityAsObject();
	}
	
	//Festlegen der Variabeln, um VerticalPanel und und die Flextables anzulegen
	private VerticalPanel vpanel = new VerticalPanel();
	HorizontalPanel hpanel = new HorizontalPanel();
	
	
	private FlexTable form = new FlexTable();
//	private FlexTable ftable_team = new FlexTable();
//	private FlexTable ftable_unternehmen = new FlexTable();
	private FlexTable ft_buttonPanel = new FlexTable();
//	private static DialogBox db_team = new DialogBox();
//	private static DialogBox db_unternehmen = new DialogBox();
//	private Button closeTeam = new Button("Schließen");
//	private Button closeUnternehmen = new Button("Schließen");

	//Anlegen der Buttons für verschiedene Funktionen
	private Button bearbeiten = new Button("Bearbeiten");
	private Button speichern = new Button("Speichern");
	private Button abbrechen = new Button("Abbrechen");
	private Button newTeam = new Button("Team hinzufügen");
	private Button newUN = new Button("Unternehmen hinzufügen");
	
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
	
	//Erstellen der Labels
	private Label anrede = new Label("Anrede");
	private Label vorname = new Label("Vorname");
	private Label nachname = new Label("Nachname");
	private Label straße = new Label("Straße");
	private Label hausnr = new Label("Hausnummer");
	private Label plz = new Label("Postleitzahl");
	private Label ort = new Label("Ort");
		
		@Override
		protected String getHeadlineText() {
			return "<h2>Mein Profil</2>";
		}
		@Override
		protected void run() {
			
			
			this.add(partnerprofil);
			
			vnameBox.setText(p.getVorname());
			nnameBox.setText(p.getName());
			anredeBox.setText(p.getAnrede());
			strasseBox.setText(p.getStraße());
			hausnrBox.setText(Integer.toString(p.getHausnummer()));
			plzBox.setText(Integer.toString(p.getPlz()));
			ortBox.setText(p.getOrt());
			
//			anredeBox.setReadOnly(true);
//			vnameBox.setReadOnly(true);
//			nnameBox.setReadOnly(true);
//			strasseBox.setReadOnly(true);
//			hausnrBox.setReadOnly(true);
//			plzBox.setReadOnly(true);
//			ortBox.setReadOnly(true);
			
			//Stylen der Buttons
			bearbeiten.setStylePrimaryName("myprofil-button");
			speichern.setStylePrimaryName("myprofil-button");
			abbrechen.setStylePrimaryName("myprofil-button");
			newTeam.setStylePrimaryName("myprofil-button");
			newUN.setStylePrimaryName("myprofil-button");
			
//			RootPanel.get("Details").add(vpanel);
//			RootPanel.get("Details").setWidth("100%");
			
			anredeListBox.addItem("Herr");
			anredeListBox.addItem("Frau");
//			RootPanel.get("Details").add(anredeBox);
//			RootPanel.get("Details").add(vnameBox);
//			RootPanel.get("Details").add(anredeListBox);
//			RootPanel.get("Details").add(bearbeiten);
			
			
			//Legt den Abstand zwischen diesen Zellen fest. Parameter:Beabstandet den Zwischenzellenabstand in Pixeln			
			vpanel.setSpacing(8);
			
			form.setWidget(0,  1, anredeListBox);
			form.setWidget(0, 0, anrede);
			
			form.setWidget(1,  1, vnameBox);
			form.setWidget(1, 0, vorname);
			
			form.setWidget(2,  1, nnameBox);
			form.setWidget(2, 0, nachname);
			
			form.setWidget(3,  1, strasseBox);
			form.setWidget(3, 0, straße);
			
			form.setWidget(4,  1, hausnrBox);
			form.setWidget(4, 0, hausnr);
			
			form.setWidget(5,  1, plzBox);
			form.setWidget(5, 0, plz);
			
			form.setWidget(6,  1, ortBox);
			form.setWidget(6, 0, ort);
			
			
			
			ft_buttonPanel.setWidget(0, 0, bearbeiten);
			ft_buttonPanel.setWidget(0, 1, speichern);
			ft_buttonPanel.setWidget(0, 2, abbrechen);
			ft_buttonPanel.setWidget(0, 3, newTeam);
			ft_buttonPanel.setWidget(0, 4, newUN);
			//ft_buttonPanel.setWidget(0, 5, partnerprofil);
			

			vpanel.add(ft_buttonPanel);
			vpanel.add(form);
			this.add(vpanel);
			this.setSpacing(8);
			
		}

	
	}

	
	
