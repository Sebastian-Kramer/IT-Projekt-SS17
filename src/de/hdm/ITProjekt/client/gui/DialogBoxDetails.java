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
	
	private IdentitySelection identitySelection = null;
	
	private VerticalPanel vp = new VerticalPanel();
	private HorizontalPanel hp = new HorizontalPanel();
	
	private Button close = new Button("Zurück zu Ausschreibungen");
	
//	private Label text = new Label("<h2>Haben Sie zu dieser Ausschreibung können Sie sich an folgende Person wenden</h2>");
	private Label zustaendigePerson = new Label("Projektleiter: ");
	private Label kontaktPerson = new Label("Kontakt E-mail; ");
	
	
	private Person person;
	private Projekt selectedProjekt;
	

	public DialogBoxDetails(Projekt selectedProjekt, Person person) {
		this.person = person;
		this.selectedProjekt = selectedProjekt;
		
		setText("<h2>Haben Sie zu dieser Ausschreibung können Sie sich an folgende Person wenden</h2>");
		setAnimationEnabled(true);
		setGlassEnabled(true);
		
		hp.add(close);
		hp.add(zustaendigePerson);
		hp.add(kontaktPerson);
		vp.add(hp);
		this.add(vp);
		
		close.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				DialogBoxDetails.this.hide();
				
			}
			
		});
	}
	
	

}
