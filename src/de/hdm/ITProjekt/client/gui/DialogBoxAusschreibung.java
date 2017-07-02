package de.hdm.ITProjekt.client.gui;

import java.util.Vector;

import com.google.gwt.cell.client.ClickableTextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.TextArea;

import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatz;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;
import de.hdm.ITProjekt.server.AdministrationProjektmarktplatzImpl;
import de.hdm.ITProjekt.shared.bo.*;

import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.client.Menubar;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;

/**
 * Die Klasse DialogBoxAusschreibung zeigt die Stellenausschreibung im Detail an.
 * Hierzu gehört der Ausschreibungstext, das in der CellTable <code>ct_eigenschaften</code> anhand der 
 * Eigenschaften gespeicherte Partnerprofil, sowie die Möglichkeit sich über den Button <code>bewerben</code>
 * direkt auf die Ausschreibung zu bewerben.
 * @author Raphael
 *
 */

public class DialogBoxAusschreibung extends DialogBox {
	
	AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
	private VerticalPanel vp = new VerticalPanel();
	private VerticalPanel vp1 = new VerticalPanel();
	Button schliessen = new Button("schliessen");
	Button bewerben = new Button("Auf Stelle bewerben");
	
	
	private Ausschreibung ausschreibung = new Ausschreibung();
	
	
	private IdentitySelection is = null;
	private Menubar menubar = null;
	private TextArea ausschreibungstext = new TextArea();
	private FlexTable ausschreibungstextft = new FlexTable();
	private Label partnerprofillabel = new Label("Das geforderte Partnerprofil zu dieser Stelle");
	private CellTable<Eigenschaft> ct_eigenschaften = new CellTable<Eigenschaft>();
	
	public DialogBoxAusschreibung (final Ausschreibung selectedAusschreibung,final IdentitySelection is, final Menubar menubar){
		this.ausschreibung = selectedAusschreibung;
		this.menubar = menubar;
		setText(selectedAusschreibung.getBezeichnung());
		setAnimationEnabled(true);
		setGlassEnabled(true);
		this.center();
		ausschreibungstext.setReadOnly(true);
		ausschreibungstext.setText(selectedAusschreibung.getAusschreibungstext());
		ausschreibungstext.setCharacterWidth(50);
		ausschreibungstext.setVisibleLines(20);
		ausschreibungstextft.setWidget(0, 0, ausschreibungstext);
		ausschreibungstextft.setWidget(1, 0, schliessen);
		ausschreibungstextft.setWidget(1, 0, bewerben);
		ausschreibungstextft.setWidget(2, 0, partnerprofillabel);
		
		vp.add(ausschreibungstextft);
		vp.add(ct_eigenschaften);
		vp.add(schliessen);
		vp.add(bewerben);
		this.add(vp);
		
		
	Column<Eigenschaft, String> name = 
			    new Column<Eigenschaft, String>(new ClickableTextCell())  {
			    
					@Override
					public String getValue(Eigenschaft object) {
						return object.getName();
					}
					    
	 };
	 
	 Column<Eigenschaft, String> wert = 
			    new Column<Eigenschaft, String>(new ClickableTextCell())  {
			    
					@Override
					public String getValue(Eigenschaft object) {
						return object.getWert();
					}
					
	 };
	 
	 ct_eigenschaften.addColumn(name);
	 ct_eigenschaften.addColumn(wert);
	 
	 filltableigenschaft();
	 
	 
	 
	 
		
		schliessen.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				DialogBoxAusschreibung.this.hide();
				
			}
			
		});
		
		bewerben.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				
					if(is.getSelectedIdentityAsObject() instanceof Person && is.getUser().getID() == selectedAusschreibung.getOrga_ID() ){
						Window.alert("Der Projektleiter kann sich nicht auf Stellen seines eigenen Projekts bewerben!");
					}
				else{
					hide();
					DialogBox DialogBoxBewerbungAnlegen = new DialogBoxBewerbungAnlegen(selectedAusschreibung, is, menubar);
					DialogBoxBewerbungAnlegen.center();
				}
			}
//				adminService.getAllProjekte(new AsyncCallback<Vector<Projekt>>(){
//
//					@Override
//					public void onFailure(Throwable caught) {
//						// TODO Auto-generated method stub
//						
//					}
//
//					@Override
//					public void onSuccess(Vector<Projekt> result) {
//						for(Projekt p : result){
//							if(is.getSelectedIdentityAsObject() instanceof Person){
//							if(p.getProjektleiter_ID() ==  is.getUser().getID()){
//								Window.alert("Der Projektleiter kann sich nicht auf Stellen seines eigenen Projekts bewerben!");
//								
//							}
//							}else{
//								hide();
//								DialogBox DialogBoxBewerbungAnlegen = new DialogBoxBewerbungAnlegen(selectedAusschreibung, is, menubar);
//								DialogBoxBewerbungAnlegen.center();
//								
//								
//							}break;
//							
//						}
//						
//					}
//					
//				});
//			
//			}
			
		});
	}

		private void filltableigenschaft(){
			adminService.getAllPartnerprofile(new AsyncCallback<Vector<Partnerprofil>>(){

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onSuccess(Vector<Partnerprofil> result) {



					for(Partnerprofil a : result){
						
						

						
						if(ausschreibung.getPartnerprofil_ID() == a.getID()){

							
							
							adminService.getAllEigenschaftenbyPartnerprofilID(a.getID(), new AsyncCallback<Vector<Eigenschaft>>(){

								
								@Override
								public void onFailure(Throwable caught) {
									Window.alert(" Die Eigenschaften konnten nicht geladen werden");
									
								}

								@Override
								public void onSuccess(Vector<Eigenschaft> result) {
									ct_eigenschaften.setRowData(0, result);
									ct_eigenschaften.setRowCount(result.size(),true);
									
								}
								
							});
						}
					}
					
				}
				 
			 });
		}
	
}
