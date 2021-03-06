package de.hdm.ITProjekt.client.gui;

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
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.client.Showcase;

import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatz;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;
import de.hdm.ITProjekt.shared.bo.Projektmarktplatz;


/**
 * Die Klasse ermöglicht das Andern des Namens eines Projektmarktplatzes.
 * Da diese Klasse nur über die Klasse <code>ProjektmarktplatzBearbeitungsSeite</code> aufgerufen werden kann,
 * kann nur ein Administrator diese nutzen.
 * @author Raphael
 *
 */
public class DialogBoxProjektmarktplatzSeiteAendern extends DialogBox {
	// Konflikt gel�st
	AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
	VerticalPanel vpanel = new VerticalPanel();
	HorizontalPanel hpanel = new HorizontalPanel();
	
	Button ok = new Button("Ok");
	Button abbrechen = new Button("Abbrechen");
	
	private IdentitySelection is = null;
	
	Label projektmarktplatzname = new Label ("Projektmarktplatzbezeichnung: ");
	TextArea bezeichnung = new TextArea();
	


	FlexTable projektmarktplatzseite = new FlexTable();
	
	public DialogBoxProjektmarktplatzSeiteAendern(final Projektmarktplatz selectedObject) {
		this.setText("Projektmarktplatz anlegen");
		this.setAnimationEnabled(false);
		this.setGlassEnabled(true);
		ok.setStylePrimaryName("button");
		abbrechen.setStylePrimaryName("button");
		
		hpanel.add(ok);
		hpanel.add(abbrechen);

		ok.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if (bezeichnung.getText().isEmpty()){
					Window.alert("Bitte Geben Sie einen Projektmarktplatznamen ein");
					}
				else{
					selectedObject.setBez(bezeichnung.getValue());
					((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
				 
					if (adminService == null) {
					 AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
					 }
					adminService.updateProjektmarktplatz(selectedObject, new updateProjektmarktplatzausDB());
				    
					}
					
					
				}
		});
		
		abbrechen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Showcase showcase = new ProjektmarktplatzBearbeitungsSeite(is);
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
				
			}
		});
		
		projektmarktplatzseite.setWidget(1, 0, projektmarktplatzname);
		projektmarktplatzseite.setWidget(2, 0, bezeichnung);
		
		vpanel.add(projektmarktplatzseite);
		vpanel.add(hpanel);
		this.add(vpanel);
		
	}
	

	public class updateProjektmarktplatzausDB implements AsyncCallback<Projektmarktplatz>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Veränderung konnte nicht gespeichert werden!");
			
		}

		@Override
		public void onSuccess(Projektmarktplatz result) {
			Window.alert("Veränderung wurde gespeichert werden!");
			Showcase showcase = new ProjektmarktplatzBearbeitungsSeite(is);
			RootPanel.get("Details").clear();
			RootPanel.get("Details").add(showcase);			
		}
		
	}

}
