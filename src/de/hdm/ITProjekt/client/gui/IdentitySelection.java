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
	private static Projektmarktplatz projektmarktplatz;
	private static Vector<Projektmarktplatz> projektmarktplaetze;
	
private IdentitySelection (int id){
		
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
	}



private class getUser implements AsyncCallback<Person>{

	@Override
	public void onFailure(Throwable caught) {
		Window.alert("Die Person konnte nicht gefunden werden");
		
	}

	@Override
	public void onSuccess(Person result) {
			ListboxIdentitySelection.clear();
			Listbox2.clear();
			person = result;
			Integer personID = result.getID();
			ListboxIdentitySelection.addItem("Person" + result.getVorname() + " " +
												result.getNachname() , personID.toString());
			
//			if (person.getOrga_ID()!=null) {
//				projektmarktplatzVerwaltung
//			}
		}

	}
}





