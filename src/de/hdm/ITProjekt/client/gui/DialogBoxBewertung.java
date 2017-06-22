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
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.client.Menubar;
import de.hdm.ITProjekt.client.Showcase;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatz;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;
import de.hdm.ITProjekt.shared.bo.Bewerbung;
import de.hdm.ITProjekt.shared.bo.Bewertung;
import de.hdm.ITProjekt.shared.bo.Projektmarktplatz;


public class DialogBoxBewertung extends DialogBox{
	
	AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
	
	private VerticalPanel vpanel = new VerticalPanel();
	private HorizontalPanel hpanel = new HorizontalPanel();
	
	private Button bewertungAbgaben = new Button("Abgeben");
	private Button beteiligungErstellen = new Button("Projektbeteiligung erstellen");
	private Button close = new Button("Zurück zu Ausschreibungen");
	
	private ListBox bewertung = new ListBox();
	
	private TextArea db = new TextArea();
	
	private FlexTable form = new FlexTable();
	
	private Label meineBewertung = new Label("Bewertung");
	private Label stellungname = new Label("Stellungname");
	
	private Bewerbung bew;
	private Bewertung bewert = new Bewertung();
	
	public DialogBoxBewertung(final Bewerbung b){
		this.bew = b;
		
		this.setText("Hier können Sie ein Bewertung abgeben");
		this.setAnimationEnabled(true);
		this.setGlassEnabled(true);
		
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
		
		vpanel.setSpacing(10);
		
		form.setWidget(0, 0, meineBewertung);
		form.setWidget(0, 1, bewertung);
		
		form.setWidget(2, 0, stellungname);
		form.setWidget(2, 1, db);
		
		vpanel.add(form);
		vpanel.add(bewertungAbgaben);
		vpanel.add(beteiligungErstellen);
		vpanel.add(close);
		hpanel.add(vpanel);
		this.add(hpanel);
		
		close.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				DialogBoxBewertung.this.hide();
				
			}
			
		});
		bewertungAbgaben.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				bewert.setBewertung(1.0);
				bewert.setStellungnahme(db.getText());
				bewert.setBewerbungs_ID(b.getID());

				
				((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
				 
				if (adminService == null) {
				 AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
				 }
				adminService.insert(bewert, new BewertungAnlegen());
			}
			
		});
		
	
	}
	
	public class BewertungAnlegen implements AsyncCallback<Bewertung>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Die Bewertung konnte nicht abgegeben werden");
			
		}

		@Override
		public void onSuccess(Bewertung result) {
			Window.alert("Die Bewertung wurde erfolgreich abgegeben");
			
		}
		
	}
	
	
	
	
	
	
	
	
	

}
