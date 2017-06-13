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
import de.hdm.ITProjekt.client.Menubar;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatz;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;
import de.hdm.ITProjekt.shared.bo.*;

public class IdentitySelection extends FlexTable{

	private static int loginID = 1;
	private static IdentitySelection navigation=null;
	
	private static ListBox ListboxIdentitySelection = new ListBox();
	private static ListBox Listbox2 = new ListBox();
	
	private FlexCellFormatter cellFormatter = this.getFlexCellFormatter();
	private static AdministrationProjektmarktplatzAsync projektmarktplatzVerwaltung = ClientsideSettings.getpmpVerwaltung();
	private static Person person;
	private static Team team;
	private static Unternehmen unternehmen;
	private static Vector<Projektmarktplatz> projektmarktplaetze;
	private Menubar menubar;
	private boolean marktplatz = false;
	
	private VerticalPanel vpanel = new VerticalPanel();
	
	public IdentitySelection (int id, final Menubar menubar){
		
		this.menubar = menubar;
		this.setWidget(1, 0, new Label("Nutze Identität von: "));		
		this.setWidget(1, 1, ListboxIdentitySelection);
		this.setStylePrimaryName("IdentityPanel");
		this.setWidget(2, 0, new Label("Projektmarktplatz: "));		
		this.setWidget(2, 1, Listbox2);
		cellFormatter.setHorizontalAlignment(1, 1, HasHorizontalAlignment.ALIGN_RIGHT);
		cellFormatter.setHorizontalAlignment(2, 1, HasHorizontalAlignment.ALIGN_RIGHT);
		ListboxIdentitySelection.setWidth("250px");
		Listbox2.setWidth("250px");
		projektmarktplatzVerwaltung.getPersonbyID(id, new getUser());

		ListboxIdentitySelection.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				menubar.reload();
				
			}

		});
		Listbox2.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				menubar.reload();
			}
		});
	}

	
	public static int getSelectedIndex(){
		
		int selectedID = ListboxIdentitySelection.getSelectedIndex();
		
		return selectedID;
	}

	public static int getSelectedIdentityID(){
		if(person.getTeam_ID() != 0){
			if(ListboxIdentitySelection.getSelectedIndex() == 0){
				return person.getID();
			}else if(ListboxIdentitySelection.getSelectedIndex() == 1){
				return team.getID();
			}else if(ListboxIdentitySelection.getSelectedIndex() == 2){
				return unternehmen.getID();
			}
		}else if (person.getTeam_ID() == null){
				if (ListboxIdentitySelection.getSelectedIndex() == 0){
					return person.getID();
			}else if(ListboxIdentitySelection.getSelectedIndex() == 0){
				return unternehmen.getID();   
			}
		}
	return 0; 
	}
	
	public static Organisationseinheit getSelectedIdentityAsObject(){

		if(person.getTeam_ID() != null){
			if(ListboxIdentitySelection.getSelectedIndex()==0){
				return person;
			}else if(ListboxIdentitySelection.getSelectedIndex()==1){
				return team;
			}else if(ListboxIdentitySelection.getSelectedIndex()==2){
				return unternehmen;
			}
		}else if(person.getTeam_ID() == null){
			if(ListboxIdentitySelection.getSelectedIndex()==0){
				return person;
			}else if(ListboxIdentitySelection.getSelectedIndex()==1){
				return unternehmen;
			}

		}
		return null;
	}
	
	public static int getSelectedProjectMarketplaceId(){
		for(Projektmarktplatz p : projektmarktplaetze){
			if(p.getBez()==Listbox2.getSelectedItemText()){
				return p.getID();
			}
		}
		return 0;
	}
	
	public static Person getUser(){
		return person;
	}
	
	public static Team getTeamOfUser(){
		return team;
	}
	
	public static Unternehmen getUnternehmenOfUser(){
		return unternehmen;
	}
	
	public static ListBox getOwnOrgUnits(){
		return ListboxIdentitySelection;
	}
	
	public ListBox Listbox2(){
		return Listbox2;
	}
	
	public static void deactivateOrgUnits(){
		ListboxIdentitySelection.setEnabled(false);
	}
	
	public static void deactivateProjectMarkets(){
		Listbox2.setEnabled(false);
	}
	
	public static void activateOrgUnits(){
		ListboxIdentitySelection.setEnabled(true);
	}
	
	public static void activateProjectMarkets(){
		Listbox2.setEnabled(true);
	}
	
	public static void setOwnOrgUnitToZero(){
		ListboxIdentitySelection.setSelectedIndex(0);
	}
	
	public void reinitialize(){
		projektmarktplatzVerwaltung.getPersonbyID(loginID, new getUser());
	}
	
	private IdentitySelection getThis(){
		return this;
	}
	
	public boolean getisMarktplatzSet(){
		return marktplatz;
	}

	
	
private class getUser implements AsyncCallback<Person>{

	@Override
	public void onFailure(Throwable caught) {
		Window.alert("Die Person konnte nicht gefunden werden");
		Window.alert("wegen des folgenden Fehlers: " + caught.toString());
		
	}

	@Override
	public void onSuccess(Person result) {
			ListboxIdentitySelection.clear();
			Listbox2.clear();
			person = result;
			Integer personID = result.getID();
			ListboxIdentitySelection.addItem("Person: " + result.getVorname() + " " +
												result.getName() , personID.toString());
					
			if (person.getTeam_ID() !=null) {
				projektmarktplatzVerwaltung.getTeamByID(result.getTeam_ID(), new getTeam());
			}else if (person.getUN_ID() != null){
				projektmarktplatzVerwaltung.getUnByID(result.getUN_ID(), new getUnternehmen());
			}
			projektmarktplatzVerwaltung.getMarktplatzByPerson(result, new getProjektmarktplatz());
			
		}
	
	}
	
	private class getTeam implements AsyncCallback<Team>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Das Team der Person konnte nicht geladen werden");		
		}

		@Override
		public void onSuccess(Team result) {
			
			Integer TeamID=result.getID();
			ListboxIdentitySelection.addItem("Team: "+result.getName(),TeamID.toString());	
			team=result;
			if(person.getUN_ID()!=null){
				projektmarktplatzVerwaltung.getUnByID(person.getUN_ID(), new getUnternehmen());
			}
			
		}
		
	}
	
	private class getUnternehmen implements AsyncCallback<Unternehmen>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Das Unternehmen der Person konnte nicht geladen werden");			
		}

		@Override
		public void onSuccess(Unternehmen result) {
			Integer UnternehmenID=result.getID();
			ListboxIdentitySelection.addItem("Unternehmen: "+result.getName(),UnternehmenID.toString());
			unternehmen=result;
			}
			
		}

	private class getProjektmarktplatz implements AsyncCallback<Vector<Projektmarktplatz>>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Der Projektmarktplatz der Person konnte nicht geladen werden");		
		}
		
		@Override
		public void onSuccess(Vector<Projektmarktplatz> result) {
			
			if (result != null){
				if (result.isEmpty()){
					ListboxIdentitySelection.addItem("Bitte einen Projektmarktplatz wählen oder anlegen");
					menubar.getProjektmarktplaetzeButton().click();
					RootPanel.get("Navigator").clear();
				}else{
					marktplatz = true;
					for(Projektmarktplatz p : result){
					ListboxIdentitySelection.addItem(p.getBez());
					}
					projektmarktplaetze = result;
					RootPanel.get("Navigator").add(menubar);
				}
				
			}
			
		}
		
	}
	
}





