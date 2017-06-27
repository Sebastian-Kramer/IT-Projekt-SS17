package de.hdm.ITProjekt.client.gui;
import java.util.Date;

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
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.TextArea;

import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatz;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;
import de.hdm.ITProjekt.server.AdministrationProjektmarktplatzImpl;
import de.hdm.ITProjekt.server.db.BewerbungMapper;
import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.client.Menubar;
import de.hdm.ITProjekt.client.Showcase;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;
import de.hdm.ITProjekt.shared.bo.Ausschreibung;
import de.hdm.ITProjekt.shared.bo.Bewerbung;
import de.hdm.ITProjekt.shared.bo.Person;
import de.hdm.ITProjekt.shared.bo.Projekt;

public class DialogBoxBewerbungAnlegen extends DialogBox {
	
	AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
	
	private VerticalPanel vp = new VerticalPanel();
	private HorizontalPanel hp = new HorizontalPanel();
	
	Button sendBewerbung = new Button("Bewerbung abschicken");
	Button cancelBewerbung = new Button("Bewerbung abbrechen");
	
	private Bewerbung bewerbung_dialog = new Bewerbung();
	
	private Person person;
	
	
	private Ausschreibung selectedAusschreibung = new Ausschreibung();
	private IdentitySelection is = null;
	private Menubar menubar = null;
	
	
	Label bewerbungstext_label = new Label();
	
	TextArea bewerbungstext = new TextArea();
	FlexTable bewerbungstextft = new FlexTable();
	
	public DialogBoxBewerbungAnlegen(final Ausschreibung ausschreibung1, final IdentitySelection is, Menubar menubar){
		this.is = is;
		this.menubar = menubar;
		this.selectedAusschreibung = ausschreibung1;
		
		setText("Bewerbung verfassen");
		this.setAnimationEnabled(true);
		this.setGlassEnabled(true);
		bewerbungstext.setCharacterWidth(80);
		bewerbungstext.setVisibleLines(30);
		bewerbungstextft.setWidget(1, 0, bewerbungstext_label);
		bewerbungstextft.setWidget(1, 1, bewerbungstext);
		
		vp.add(bewerbungstextft);
		hp.add(sendBewerbung);
		hp.add(cancelBewerbung);
		vp.add(hp);
		this.add(vp);
		
		cancelBewerbung.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
			DialogBoxBewerbungAnlegen.this.hide();
				
			}
			
		});
		
		sendBewerbung.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				bewerbung_dialog.setBewerbungstext(bewerbungstext.getText());
				bewerbung_dialog.setAusschreibungs_ID(ausschreibung1.getID());
				bewerbung_dialog.setOrga_ID(is.getSelectedIdentityID());
				bewerbung_dialog.setErstelldatum(new Date());
				
				if(bewerbungstext.getText().isEmpty()){
					Window.alert("Bitte geben Sie einen Bewerbungstext ein");
				}
				else{
					((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
					 
					if (adminService == null) {
					 AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
					 }
					adminService.insert(bewerbung_dialog, new addBewerbungInDB());
				}
				
			}
		
			
		});
		
		
		
	}
	
	private class addBewerbungInDB implements AsyncCallback<Bewerbung>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Die Bewerbung konnte nicht versendet werden");
			
		}

		@Override
		public void onSuccess(Bewerbung result) {
			Window.alert("Ihr Bewerbung wurde erfolgreich versendet");
			hide();
			Showcase showcase = new MeineBewerbungenSeite(is, menubar);
			RootPanel.get("Details").clear();
			RootPanel.get("Details").add(showcase);
			
		}
		
	}
	
	
	
	
	

}
