package de.hdm.ITProjekt.client.gui;

import java.util.Vector;

import com.gargoylesoftware.htmlunit.protocol.data.Handler;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;

import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.client.Showcase;
import de.hdm.ITProjekt.client.gui.report.MenubarReport;
import de.hdm.ITProjekt.client.Menubar;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatz;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;
import de.hdm.ITProjekt.shared.bo.*;

public class IdentitySelection extends FlexTable{

	
	private static IdentitySelection navigation=null;
	
//Listbox zur Auswahl von Identität 
	private ListBox orgEinheit = new ListBox();
//	private static ListBox Listbox2 = new ListBox();
	
	private FlexCellFormatter cellFormatter = this.getFlexCellFormatter();
	
	//Objekt zum Zugriff auf die Servermethoden

	private static AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
	private static Person person;


	public static void setPerson(Person person) {
		IdentitySelection.person = person;
	}

	//Objekte zum Speichern der Identitätsinformationen
	private static Team team;
	private static Unternehmen unternehmen;
	private static Vector<Projektmarktplatz> projektmarktplaetze;
	private Menubar menubar;
	private boolean marktplatz = false;
	
	/**
	 * Ein Objekt dieser Klasse stellt ein Menü zur Auswahl von Identität zur Verfügung
	 * @param id
	 * @param menubar
	 */
	
	public IdentitySelection (Person person, final Menubar menubar){
	
		this.setWidget(1, 0, new Label("Organisationseinheiten: "));		
		this.setWidget(1, 1, orgEinheit);

//		this.setWidget(2, 0, new Label("Projektmarktplatz: "));		
//		this.setWidget(2, 1, Listbox2);
		
		
		this.setStylePrimaryName("IdentityPanel");
		
		cellFormatter.setHorizontalAlignment(1, 1, HasHorizontalAlignment.ALIGN_RIGHT);
	    cellFormatter.setHorizontalAlignment(2, 1, HasHorizontalAlignment.ALIGN_RIGHT);
		orgEinheit.setWidth("250px");
//		Listbox2.setWidth("250px");
		
	
		
		((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
		 if (adminService == null) {
	      adminService = GWT.create(AdministrationProjektmarktplatz.class);
	    }
		adminService.getPersonbyID(person.getID(), new getUser());

//		orgEinheit.addItem(person.getName());
//		orgEinheit.addItem(team.getName());
//		orgEinheit.addItem(unternehmen.getName());
		
		
		
		orgEinheit.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				menubar.reload();
				
			}

		});
	}

	/**
	 * Gibt den gewählten Index der Identitäts-Listbox zurück
	 */
	public int getSelectedIndex(){
		
		int selectedID = orgEinheit.getSelectedIndex();
		
		return selectedID;
	}
	/**
	 * Gibt die ID der gewählten Identität zurück
	 */
	public int getSelectedIdentityID(){
		if(person.getTeam_ID() != null){
			if(orgEinheit.getSelectedIndex() == 0){
				return person.getID();
			}else if(orgEinheit.getSelectedIndex() == 1){
				return team.getID();
			}else if(orgEinheit.getSelectedIndex() == 2){
				return unternehmen.getID();
			}
		}else if (person.getTeam_ID() == null){
				if (orgEinheit.getSelectedIndex() == 0){
					return person.getID();
			}else if(orgEinheit.getSelectedIndex() == 1){
				return unternehmen.getID();   
			}
		}
	return 0; 
	}
	
	/**
	 * Gibt die gewählte Identität als Objekt zurück
	 */
	public Organisationseinheit getSelectedIdentityAsObject(){

		if(person.getTeam_ID() != null){
			if(orgEinheit.getSelectedIndex()==0){
				return person;
			}else if(orgEinheit.getSelectedIndex()==1){
				return team;
			}else if(orgEinheit.getSelectedIndex()==2){
				return unternehmen;
			}
		}else if(person.getTeam_ID() == null){
			if(orgEinheit.getSelectedIndex()==0){
				return person;
			}else if(orgEinheit.getSelectedIndex()==1){
				return unternehmen;
			}

		}
		return null;
	}
	
//	public int getSelectedProjectMarketplaceId(){
//		for(Projektmarktplatz p : projektmarktplaetze){
//			if(p.getBez()==Listbox2.getSelectedItemText()){
//				return p.getID();
//			}
//		}
//		return 0;
//	}
	
	/**
	 * Gibt den User zurück
	 */
	public Person getUser(){
		return person;
	}
	
	/**
	 * Gibt das Team zurück
	 */
	public Team getTeamOfUser(){
		return team;
	}
	
	/**
	 * Gibt das Unternehmen zurück
	 */
	public Unternehmen getUnternehmenOfUser(){
		return unternehmen;
	}
	
	/**
	 * Gibt das Listbox zurück
	 */
	public  ListBox getOwnOrgUnits(){
		return orgEinheit;
	}
	
//	public ListBox Listbox2(){
//		return Listbox2;
//	}
	
	/**
	 * Deaktiviert die Listbox
	 */
	public void deactivateOrgUnits(){
		orgEinheit.setEnabled(false);
	}
	
//	public void deactivateProjectMarkets(){
//		Listbox2.setEnabled(false);
//	}
	
	/**
	 * Aktiviert die IdentitätsListbox
	 */
	public void activateOrgUnits(){
		orgEinheit.setEnabled(true);
	}
	
//	public void activateProjectMarkets(){
//		Listbox2.setEnabled(true);
//	}
	
	/**
	 * Setzt den Index der IdentitästsListbox auf 0, 
	 * sodass der User eingeloggt ist
	 */
	public void setOwnOrgUnitToZero(){
		orgEinheit.setSelectedIndex(0);
	}
	
	/**
	 * Lädt die Listbox neu
	 */
	public void reinitialize(){
		((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
		 if (adminService == null) {
	      adminService = GWT.create(AdministrationProjektmarktplatz.class);
	    }
		adminService.getPersonbyID(person.getID(), new getUser());
	}
	
	private IdentitySelection getThis(){
		return this;
	}
	
//	public boolean getisMarktplatzSet(){
//		return marktplatz;
//	}

	/**
	 * Private Klasse, um die verknüpften Informationen des Users aus
	 * der DB zu erhalten
	 */
	
private class getUser implements AsyncCallback<Person>{

	@Override
	public void onFailure(Throwable caught) {
		Window.alert("Die Person konnte nicht gefunden werden");
		Window.alert("wegen des folgenden Fehlers: " + caught.toString());
		
	}

	@Override
	public void onSuccess(Person result) {
		//löschen der alten Einträge
			orgEinheit.clear();
//			Listbox2.clear();
			//Speichern des eingeloggten Users
			person = result;
			Integer personID = result.getID();
			
			//Hinzufügen der Person-Identität zur Listbox
			orgEinheit.addItem("Person: " + result.getVorname() + " " +
												result.getName() , personID.toString());
			((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
			 if (adminService == null) {
		      adminService = GWT.create(AdministrationProjektmarktplatz.class);
		    }
			
			 //Ruft RPCs auf, nachdem geprüft wurde, ob Team oder Unternehmen 
			if (person.getTeam_ID() !=null) {
				adminService.getTeamByID(result.getTeam_ID(), new getTeam());
			}else if (person.getUN_ID() != null){
				adminService.getUnByID(result.getUN_ID(), new getUnternehmen());
			}
//			adminService.getMarktplatzByPerson(result, new getProjektmarktplatz());
			
		}
	
	}
	
/**
 * Private Klasse, um die verknüpften Informationen des Teams aus
 * der DB zu erhalten
 */

	private class getTeam implements AsyncCallback<Team>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Das Team der Person konnte nicht geladen werden");		
		}

		@Override
		public void onSuccess(Team result) {
			
			Integer TeamID=result.getID();
			
			//Hinzufügen der Team-Identität zur Listbox
			orgEinheit.addItem("Team: "+result.getName(),TeamID.toString());	
			
			//Team wird gespeichert
			team=result;
			
			 //Ruft RPCs auf, wenn der User zusätzlich einem Team zugewiesen ist  
			if(person.getUN_ID()!=null){
				((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
				 if (adminService == null) {
			      adminService = GWT.create(AdministrationProjektmarktplatz.class);
			    }
				adminService.getUnByID(person.getUN_ID(), new getUnternehmen());
			}
			
		}
		
	}
	
	/**
	 * Private Klasse, um das verknüpfte Unternehmen des Users 
	 * in die Listbox schreiben zu können
	 */
	private class getUnternehmen implements AsyncCallback<Unternehmen>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Das Unternehmen der Person konnte nicht geladen werden");			
		}

		@Override
		public void onSuccess(Unternehmen result) {
			Integer UnternehmenID=result.getID();
			orgEinheit.addItem("Unternehmen: "+result.getName(),UnternehmenID.toString());
			unternehmen=result;
			}
			
		}

//	private class getProjektmarktplatz implements AsyncCallback<Vector<Projektmarktplatz>>{
//
//		@Override
//		public void onFailure(Throwable caught) {
//			Window.alert("Der Projektmarktplatz der Person konnte nicht geladen werden");		
//		}
//		
//		@Override
//		public void onSuccess(Vector<Projektmarktplatz> result) {
//			
//			if (result != null){
//				if (result.isEmpty()){
//					orgEinheit.addItem("Bitte einen Projektmarktplatz wählen oder anlegen");
//					menubar.getProjektmarktplaetzeButton().click();
//					RootPanel.get("Navigator").clear();
//				}else{
//					marktplatz = true;
//					for(Projektmarktplatz p : result){
//					orgEinheit.addItem(p.getBez());
//					}
//					projektmarktplaetze = result;
//					RootPanel.get("Navigator").add(menubar);
//				}
//				
//			}
//			
//		}
//		
//	}
	
}





