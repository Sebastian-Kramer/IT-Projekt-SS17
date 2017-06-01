package de.hdm.ITProjekt.client;

import java.util.Vector;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;
import de.hdm.ITProjekt.shared.bo.Projektmarktplatz;
import de.hdm.ITProjekt.client.ClientsideSettings;


public class ProjektmarktplatzSeite extends Showcase {
	 AdministrationProjektmarktplatzAsync projektmarktplatzverwaltung = ClientsideSettings.getPmpAdmin();
	
	// Erstellen einer CellTable
	CellTable<Projektmarktplatz> projekttabelle = new CellTable<Projektmarktplatz>();
	
	
	// Automatisch implementierte Methoden

	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "ProjektmarktplatzSeiteShowcase";
	}


	@Override
	protected void run() {
		
		((ServiceDefTarget)projektmarktplatzverwaltung).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
		
		AdministrationProjektmarktplatzAsync projektmarktplatzverwaltung = ClientsideSettings.getPmpAdmin();
		projektmarktplatzverwaltung.getProjektmarktplatzAll(new GetPersonCallback(this));
		
	
	}

	



	
	
//	public void tabelleAufrufen(){
//	TextColumn<Projektmarktplatz> nameColumn = new TextColumn<Projektmarktplatz>(){
//
//		@Override
//		public String getValue(Projektmarktplatz object) {
//			// TODO Auto-generated method stub
//			return object.getBez();
//		}
//		
//	};
	
	// Der Spalte einen Namen geben
//	projekttabelle.addColumn(nameColumn, "Bezeichnung");
	
	// Eine Spalte auswählen
	
//	
//	projekttabelle.setSelectionModel(selectionModel);
//	final MultiSelectionModel<Projektmarktplatz> selectionModel = new MultiSelectionModel<Projektmarktplatz>();
	final SingleSelectionModel<Projektmarktplatz> selectionModel = new SingleSelectionModel<>();
//	projekttabelle.setSelectionModel(selectionModel);


	
    
    // Button in den addPanel platzieren
   
//    addPanel.add(newSymbolTextBox);
//    addPanel.add(addProjektButton);
   
    
    
    // HorizontalPanel (addPanel) in den mainPanel platzieren
//    mainPanel.add(addPanel);
	
	
//    mainPanel.add(projekttabelle);
//    RootPanel.get("projektListe").add(mainPanel);

	
	private class GetPersonCallback implements AsyncCallback<Vector<Projektmarktplatz>>{

		private Showcase showcase = null;
		
		public GetPersonCallback(Showcase c){
			this.showcase = c;
		}
		
		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			Window.alert("Fehler beim Laden der Daten aus der Datenbank");
			
		}

		@Override
		public void onSuccess(Vector <Projektmarktplatz> result) {
			// TODO Auto-generated method stub
			Window.alert("on Success");
						
			
			
			TextColumn<Projektmarktplatz> nameColumn = new TextColumn<Projektmarktplatz>(){

				@Override
				public String getValue(Projektmarktplatz object) {
					// TODO Auto-generated method stub
					return object.getBez();
				}
				
			};
			projekttabelle.setRowCount(result.size());
			projekttabelle.setRowData(0, result);
			projekttabelle.addColumn(nameColumn, "Bezeichnung");
		}
		
	}

}


