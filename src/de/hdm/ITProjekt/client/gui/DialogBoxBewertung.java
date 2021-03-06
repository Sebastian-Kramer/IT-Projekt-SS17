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
import de.hdm.ITProjekt.shared.bo.Ausschreibung;
import de.hdm.ITProjekt.shared.bo.Beteiligung;
import de.hdm.ITProjekt.shared.bo.Bewerbung;
import de.hdm.ITProjekt.shared.bo.Bewertung;
import de.hdm.ITProjekt.shared.bo.Person;
import de.hdm.ITProjekt.shared.bo.Projekt;
import de.hdm.ITProjekt.shared.bo.Projektmarktplatz;

/**
 * Die DialogBoxBewertung ermöglicht die Bewertung von Bewerbungen und die darauffolgende Erstellung
 * einer Projektbeteiligung.
 * Über die Listbox <code>janein</code> kann entschieden werden, ob eine Beteiligung erstellt wird.
 * Dies wird durch das hinzufügen der Auswahlmöglichkeiten "Ja" und "nein" realisiert, die über <code/>additem</code>
 * der Listbox hinzugefügt werden. Wird durch den User "Ja gewählt" öffnet sich die <code>DialogBoxBeteiligung</code>,
 * ansonsten wird lediglich eine Bewertung erstellt.
 *
 * @author Raphael
 *
 */
public class DialogBoxBewertung extends DialogBox{
	
	AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
	
	private VerticalPanel vpanel = new VerticalPanel();
	private HorizontalPanel hpanel = new HorizontalPanel();
	
	private Button bewertungAbgaben = new Button("Abgeben");
//	private Button beteiligungErstellen = new Button("Projektbeteiligung erstellen");
	private Button close = new Button("Zurück zu Ausschreibungen");
	
	private ListBox bewertung = new ListBox();
	private ListBox janein = new ListBox();
	
	private TextArea db = new TextArea();
	
	private FlexTable form = new FlexTable();
	
	private Label meineBewertung = new Label("Bewertung");
	private Label stellungname = new Label("Stellungnahme");
	private Label beteiligung = new Label("Projektbeteiligung erstellen?");
	
	private Bewerbung bew;
	private Bewertung bewert = new Bewertung();
	private Ausschreibung aus;
	private Beteiligung bet;
	private Person person;
	private IdentitySelection seleceted_is = null;
	private Projekt p;
	private Projektmarktplatz pmp;
	
	public DialogBoxBewertung(final Bewerbung b, Ausschreibung a, IdentitySelection is, final Projekt p, final Projektmarktplatz pmp){
		this.bew = b;
		this.aus = a;
		this.seleceted_is = is;
		this.p = p;
		this.pmp = pmp;
		
		this.setText("Hier können Sie ein Bewertung abgeben");
		this.setAnimationEnabled(true);
		this.setGlassEnabled(true);


//		bewertung.addItem(Double.toString(0.1));
//		bewertung.addItem(Double.toString(0.2));
//		bewertung.addItem(Double.toString(0.3));
//		bewertung.addItem(Double.toString(0.4));
//		bewertung.addItem(Double.toString(0.5));
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
		
		janein.addItem("Ja");
		janein.addItem("nein");
		
		vpanel.setSpacing(10);
		
		form.setWidget(0, 0, meineBewertung);
		form.setWidget(0, 1, bewertung);
		
		form.setWidget(2, 0, stellungname);
		form.setWidget(2, 1, db);
		
		form.setWidget(4, 0, beteiligung);
		form.setWidget(4, 1, janein);
		
		vpanel.add(form);
		
		vpanel.add(bewertungAbgaben);
//		vpanel.add(beteiligungErstellen);
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

				if(janein.getSelectedItemText() == "Ja"){
					
					bewert.setBewertung(Double.parseDouble(bewertung.getSelectedItemText()));
					bewert.setStellungnahme(db.getText());
					bewert.setBewerbungs_ID(b.getID());
				
					DialogBoxBeteiligung dialogBox  = new DialogBoxBeteiligung(bewert, aus, seleceted_is , bew, p, pmp);		
					dialogBox.center();
					DialogBoxBewertung.this.hide();		
				}
				else{
					
					bewert.setBewertung(Double.parseDouble(bewertung.getSelectedItemText()));
					bewert.setStellungnahme(db.getText());
					bewert.setBewerbungs_ID(b.getID());
					bew.setStatus("abgelehnt");
					
					((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
					 
					if (adminService == null) {
					 AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
					 }
					adminService.insertWithoutBeteil(bewert, new BewertungAnlegen());
					adminService.setBewerbungsStatus(bew, new BewerbungStatus());
					DialogBoxBewertung.this.hide();
					Showcase showcase = new AlleBewerbungenFromAuschreibung(aus, seleceted_is);
					RootPanel.get("Details").clear();
					RootPanel.get("Details").add(showcase);
				}
				
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
	public class BewerbungStatus implements AsyncCallback<Bewerbung>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Der Bewerbungsstatus konnte nicht verändert werden");
			
		}

		@Override
		public void onSuccess(Bewerbung result) {
			Window.alert("Der Status der Bewerbung wurde zu 'Abgelehnt' geändert");
			
		}
		
	}
	
	
	
	
	
	
	
	

}
