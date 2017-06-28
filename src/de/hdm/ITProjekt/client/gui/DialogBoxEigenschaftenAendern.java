package de.hdm.ITProjekt.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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
import de.hdm.ITProjekt.client.Showcase;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatz;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;
import de.hdm.ITProjekt.shared.bo.Eigenschaft;
import de.hdm.ITProjekt.shared.bo.Person;
import de.hdm.ITProjekt.shared.bo.Unternehmen;

public class DialogBoxEigenschaftenAendern extends DialogBox{

	AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
	
	private VerticalPanel vpanel = new VerticalPanel();
	private HorizontalPanel hpanel = new HorizontalPanel();
	private Button ok = new Button("Ok");
	private Button abbrechen = new Button("Abbrechen");

	private ListBox auswahlEigenschaften = new ListBox();
	private ListBox wertEigenschaften = new ListBox();
	
	private IdentitySelection is = null;

	private Label auswahlLabel = new Label("Eigenschaften:");
	private Label wertLabel = new Label("Kenntnisstand:");
	
	private FlexTable eigenschaftaendern = new FlexTable();

	public DialogBoxEigenschaftenAendern(final IdentitySelection is, final Eigenschaft eigenschaft){
		this.is = is;
		
		
		this.setAnimationEnabled(false);
		this.setGlassEnabled(true);
		this.setText("Eigenschaften Ändern");
		ok.setStylePrimaryName("button");
		abbrechen.setStylePrimaryName("button");
		
		auswahlEigenschaften.addItem("Java");
		auswahlEigenschaften.addItem("PHP");
		auswahlEigenschaften.addItem("Word");
		auswahlEigenschaften.addItem("Excel");
		auswahlEigenschaften.addItem("PowerPoint");
		auswahlEigenschaften.addItem("C++");

		wertEigenschaften.addItem("Grundkenntisse");
		wertEigenschaften.addItem("Fortgeschritten");
		wertEigenschaften.addItem("Experte");	
		
		ok.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				eigenschaft.setName(auswahlEigenschaften.getSelectedValue());
				eigenschaft.setWert(wertEigenschaften.getSelectedValue());
				((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
				 if (adminService == null) {
			     adminService = GWT.create(AdministrationProjektmarktplatz.class);
			   }
				 adminService.updateEigenschaft(eigenschaft, new AsyncCallback<Eigenschaft>(){

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(Eigenschaft result) {
						hide();
						Showcase showcase = new MeinProfilAnzeigen(is);
						RootPanel.get("Details").clear();
						RootPanel.get("Details").add(showcase);
					}
					 
				 });
			}
		});
		
		abbrechen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				hide();
			}
		});
		
		eigenschaftaendern.setWidget(0, 0, auswahlLabel);
		eigenschaftaendern.setWidget(0, 1, auswahlEigenschaften);
		
		eigenschaftaendern.setWidget(1, 0, wertLabel);
		eigenschaftaendern.setWidget(1, 1, wertEigenschaften);
		
		eigenschaftaendern.setWidget(2, 0, ok);
		eigenschaftaendern.setWidget(2, 1, abbrechen);
		
		vpanel.add(eigenschaftaendern);
		this.add(vpanel);
	}
	
}
