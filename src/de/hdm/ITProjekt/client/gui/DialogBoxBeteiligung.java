package de.hdm.ITProjekt.client.gui;

import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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
import com.google.gwt.user.datepicker.client.DateBox;

import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.client.Menubar;
import de.hdm.ITProjekt.client.Showcase;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatz;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;
import de.hdm.ITProjekt.shared.bo.Ausschreibung;
import de.hdm.ITProjekt.shared.bo.Bewerbung;
import de.hdm.ITProjekt.shared.bo.Bewertung;
import de.hdm.ITProjekt.shared.bo.Projektmarktplatz;

public class DialogBoxBeteiligung extends DialogBox{
	
	AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
	
	private VerticalPanel vpanel = new VerticalPanel();
	private HorizontalPanel hpanel = new HorizontalPanel();
	
	private FlexTable form = new FlexTable();
	
	private Button beteiligunganlegen = new Button("Projektbeteiligung erstellen");
		
	private Label umfang = new Label("Umfang der Beteiligung: ");
	private Label startdatum = new Label("Beteiligung startet am: ");
	private Label enddatum = new Label("Beteiligung geht bis zum: ");
	private Label zuprojekt = new Label("Beteiligung an folgendem Projekt wird erstellt: ");
	
	private TextBox umfangBox = new TextBox();
	private TextBox zuprojektBox = new TextBox();
	
	private DateBox startBox = new DateBox();
	private DateBox endBox = new DateBox();
	
	private Ausschreibung aus;
	
	public DialogBoxBeteiligung(Ausschreibung a){
		this.aus = a;
		
		this.setText("Projektbeteiligung erstellen");
		this.setAnimationEnabled(true);
		this.setGlassEnabled(true);
		
		vpanel.setSpacing(10);
		
		form.setWidget(0, 0, umfang);
		form.setWidget(0, 1, umfangBox);
		
		form.setWidget(2, 0, startdatum);
		form.setWidget(2, 1, startBox);
		
		form.setWidget(4, 0, enddatum);
		form.setWidget(4, 1, endBox);
		
		form.setWidget(6, 0, zuprojekt);
		form.setWidget(6, 1, zuprojektBox);
		
		vpanel.add(form);
		vpanel.add(beteiligunganlegen);
		hpanel.add(vpanel);
		this.add(hpanel);
		
		
	}
	
	
	
	

}
