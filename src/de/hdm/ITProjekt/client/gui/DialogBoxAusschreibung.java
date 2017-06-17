package de.hdm.ITProjekt.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.TextArea;

import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatz;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;
import de.hdm.ITProjekt.server.AdministrationProjektmarktplatzImpl;
import de.hdm.ITProjekt.shared.bo.*;

import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;



public class DialogBoxAusschreibung extends DialogBox {
	
	AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
	private VerticalPanel vp = new VerticalPanel();
	private VerticalPanel vp1 = new VerticalPanel();
	Button schliessen = new Button("schliessen");
	Button bewerben = new Button("Auf Stelle bewerben");
	
	private TextArea ausschreibungstext = new TextArea();
	private FlexTable ausschreibungstextft = new FlexTable();
	
	
	public DialogBoxAusschreibung (final Ausschreibung selectedAusschreibung, final Person person){
		setText(selectedAusschreibung.getBezeichnung());
		setAnimationEnabled(true);
		setGlassEnabled(true);
		this.center();
		ausschreibungstext.setReadOnly(true);
		ausschreibungstext.setText(selectedAusschreibung.getAusschreibungstext());
		ausschreibungstext.setCharacterWidth(80);
		ausschreibungstext.setVisibleLines(30);
		ausschreibungstextft.setWidget(0, 0, ausschreibungstext);
		ausschreibungstextft.setWidget(1, 0, schliessen);
		ausschreibungstextft.setWidget(1, 0, bewerben);
		
		vp.add(ausschreibungstextft);
		vp.add(schliessen);
		vp.add(bewerben);
		
		
		setWidget(vp);
		
		schliessen.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				DialogBoxAusschreibung.this.hide();
				
			}
			
		});
		
		bewerben.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				DialogBoxAusschreibung.this.hide();
				DialogBox DialogBoxBewerbungAnlegen = new DialogBoxBewerbungAnlegen(selectedAusschreibung, person);
				DialogBoxBewerbungAnlegen.center();
				
			}
			
		});
	}

	
}
