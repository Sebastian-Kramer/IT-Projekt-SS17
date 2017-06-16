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
import de.hdm.ITProjekt.client.Showcase;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;
import de.hdm.ITProjekt.shared.bo.Ausschreibung;
import de.hdm.ITProjekt.shared.bo.Bewerbung;
import de.hdm.ITProjekt.shared.bo.Projekt;

public class DialogBoxBewerbungAnlegen extends DialogBox {
	AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
	VerticalPanel vp = new VerticalPanel();
	HorizontalPanel hp = new HorizontalPanel();
	
	Button sendBewerbung = new Button("Bewerbung abschicken");
	Button cancelBewerbung = new Button("Bewerbung abbrechen");
	
	private Bewerbung bewerbung_dialog = new Bewerbung();
	
	private Ausschreibung selectedAusschreibung = new Ausschreibung();
	
	
	Label bewerbungstext_label = new Label();
	
	TextArea bewerbungstext = new TextArea();
	FlexTable bewerbungstextft = new FlexTable();
	
	public DialogBoxBewerbungAnlegen(final Ausschreibung ausschreibung1){
		this.selectedAusschreibung = ausschreibung1;
		setText("Bewerbung verfassen");
		this.setAnimationEnabled(true);
		this.setGlassEnabled(true);
		bewerbungstextft.setWidget(0, 0, bewerbungstext_label);
		bewerbungstextft.setWidget(0, 1, bewerbungstext);
		bewerbungstextft.setWidget(1, 1, sendBewerbung);
		bewerbungstextft.setWidget(1, 1, cancelBewerbung);
		hp.add(sendBewerbung);
		hp.add(cancelBewerbung);
		vp.add(bewerbungstext_label);
		vp.add(bewerbungstext);
		vp.add(bewerbungstextft);
		
		
		setWidget(vp);
		setWidget(hp);
		
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
			Showcase showcase = new MeineBewerbungenSeite();
			RootPanel.get("Details").clear();
			RootPanel.get("Details").add(showcase);
			
		}
		
	}
	
	
	
	
	

}
