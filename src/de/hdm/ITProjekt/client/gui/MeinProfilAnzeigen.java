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
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.client.Showcase;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;
import de.hdm.ITProjekt.shared.bo.Bewerbung;
import de.hdm.ITProjekt.shared.bo.Partnerprofil;

public class MeinProfilAnzeigen extends Showcase{
	
	private static ClickHandler currentClickHandler = null;
	private static ClickEvent currentClickEvent = null;
	
	AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
	
	CellTable<Partnerprofil> myPartnerprofil = new CellTable<Partnerprofil>();
	
	HorizontalPanel hpanel_Profil = new HorizontalPanel();
	VerticalPanel vpanel = new VerticalPanel();
	
	final SingleSelectionModel<Partnerprofil> ssm = new SingleSelectionModel<>();
	
	Button eigenschaften = new Button("Eigenschaft anzeigen");

	@Override
	protected String getHeadlineText() {
		
		return "MeinPartnerprofil";
	}

	@Override
	protected void run() {

		RootPanel.get("Details").setWidth("100%");
		myPartnerprofil.setWidth("100%");
		
		vpanel.add(myPartnerprofil);
		
		this.add(hpanel_Profil);
		this.add(vpanel);
		
		hpanel_Profil.add(eigenschaften);
		
		myPartnerprofil.setSelectionModel(ssm);
		
//		Column<Partnerprofil, String> erstelldatum =
//				new Column<Partnerprofil, String>(new ClickableTextCell()){
//
//					@Override
//					public String getValue(Partnerprofil object) {
//						// TODO Auto-generated method stub
//						return object.getErstelldatum().toString();
//					}
//		};
//		
		
	}

}
