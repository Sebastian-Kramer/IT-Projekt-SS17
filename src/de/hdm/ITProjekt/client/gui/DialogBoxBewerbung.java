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
import de.hdm.ITProjekt.server.db.BewerbungMapper;
import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;
import de.hdm.ITProjekt.shared.bo.Bewerbung;

public class DialogBoxBewerbung extends DialogBox {
	
	AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
	VerticalPanel vp = new VerticalPanel();
	
	Button schliessen = new Button("schliessen");
	
	IdentitySelection is = null;
	
	TextArea bewerbungstext = new TextArea();
	FlexTable bewerbungstextft = new FlexTable();
	
	private Bewerbung bewerbungId;
	public DialogBoxBewerbung(Bewerbung selectedId){
		this.bewerbungId = selectedId;
	
	}
//	private static ClickHandler currentClickHandler = null;
//	private static ClickEvent currentClickEvent = null;
	
	public DialogBoxBewerbung(String text){
		schliessen.setStylePrimaryName("navi-button");
		setText(text);
		setAnimationEnabled(true);
		setGlassEnabled(true);
		this.center();
		bewerbungstext.setReadOnly(true);
		bewerbungstext.setText(text);
		bewerbungstext.setCharacterWidth(80);
		bewerbungstext.setVisibleLines(30);
		bewerbungstextft.setWidget(0, 0, bewerbungstext);
		bewerbungstextft.setWidget(1, 0, schliessen);
		vp.add(bewerbungstextft);
		vp.add(bewerbungstext);
		vp.add(schliessen);
		
		setWidget(vp);
		
		schliessen.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				DialogBoxBewerbung.this.hide();
			}
		});
		
		
	}

}
