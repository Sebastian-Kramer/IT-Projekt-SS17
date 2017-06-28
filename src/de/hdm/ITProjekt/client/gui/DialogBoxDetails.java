package de.hdm.ITProjekt.client.gui;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.Window;
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
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.client.Showcase;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatz;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;
import de.hdm.ITProjekt.shared.bo.Person;
import de.hdm.ITProjekt.shared.bo.Projekt;
import de.hdm.ITProjekt.shared.bo.Projektmarktplatz;

public class DialogBoxDetails extends DialogBox{
	
	
	AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
	
	
	
	private VerticalPanel vp = new VerticalPanel();
	private HorizontalPanel hp = new HorizontalPanel();
	
	private Button close = new Button("Zurück zu Ausschreibungen");
	
//	private Label text = new Label("<h2>Haben Sie zu dieser Ausschreibung können Sie sich an folgende Person wenden</h2>");
	private Label zustaendigePerson = new Label("Projektleiter: ");
	private Label kontaktPerson = new Label("E-mail Adresse: ");

	private FlexTable form = new FlexTable();
	
	private IdentitySelection is = null;
	
	private TextBox kontaktNameBox = new TextBox();
	private TextBox emailBox = new TextBox();
	
	private Person person;
	private Projekt selectedProjekt;
	

	public DialogBoxDetails(Projekt selectedProjekt, IdentitySelection is) {
		this.is = is;
		this.selectedProjekt = selectedProjekt;
		
		((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
		 if (adminService == null) {
	      adminService = GWT.create(AdministrationProjektmarktplatz.class);
	    }
		adminService.getPersonbyID(selectedProjekt.getProjektleiter_ID(), new getDetailsfromPerson());
		
		setText("Haben Sie Fragen zu dieser Ausschreibung, können Sie sich an folgende Person wenden");
		setAnimationEnabled(true);
		setGlassEnabled(true);
		
		vp.setSpacing(15);
		
		form.setWidget(1, 0, zustaendigePerson);
		form.setWidget(1, 1, kontaktNameBox);
		
		form.setWidget(3, 0, kontaktPerson);
		form.setWidget(3, 1, emailBox);
		
		
		
		vp.add(form);
		vp.add(close);
		hp.add(vp);
		this.add(hp);
		
		close.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				DialogBoxDetails.this.hide();
				
			}
			
		});
	}
	
	private class getDetailsfromPerson implements AsyncCallback<Person>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Die Daten des Projektleiters konnten nicht geladen werden");
			
		}

		@Override
		public void onSuccess(Person result) {
			kontaktNameBox.setText(result.getVorname() + " " + result.getName());
			emailBox.setText(result.getEmail());
			
		}
		
	}

}
