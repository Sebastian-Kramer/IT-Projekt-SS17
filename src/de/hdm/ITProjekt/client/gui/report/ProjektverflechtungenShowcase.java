package de.hdm.ITProjekt.client.gui.report;

import java.util.Vector;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.client.ReportShowcase;
import de.hdm.ITProjekt.shared.ReportGeneratorAsync;
import de.hdm.ITProjekt.shared.bo.Organisationseinheit;
import de.hdm.ITProjekt.shared.bo.Person;
import de.hdm.ITProjekt.shared.bo.Team;
import de.hdm.ITProjekt.shared.bo.Unternehmen;

public class ProjektverflechtungenShowcase extends ReportShowcase {

	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void run() {
		// TODO Auto-generated method stub
		
	}
//
//	
//private IdentitySelectionReport identitySelectionReport = null;
//	
//	/**
//	 * Konstruktor, dem eine Instanz der IdentityChoiceReport und der Navigation übergeben wird.
//	 * @param identityChoiceReport
//	 */
//
//	public ProjektverflechtungenShowcase(IdentitySelectionReport identitySelectionReport) {
//		this.identitySelectionReport=identitySelectionReport;
//	}
//	@Override
//	protected String getHeadlineText() {
//		// TODO Auto-generated method stub
//		return "Report Projektverpflechtungen";
//	}
//
//	@Override
//	protected void run() {
//
//
//		/**
//		 * Auslesen der ReportGeneratorAsync Instanz
//		 */
//		ReportGeneratorAsync reportGenerator = ClientsideSettings.getReportGenerator();
//		
//		/**
//		 * GUI- Elemente
//		 */
//		VerticalPanel inputPanel = new VerticalPanel();
////		final HTMLResultPanel resultPanel = new HTMLResultPanel();
//		final ReportShowcase showcase = this;
//			
//		
//		
//		final ListBox bewerberBox = new ListBox();
//		bewerberBox.addItem("Bitte wähle einen Bewerber aus");
//	
//	
//	/**
//	 * Bei erfolgreichem Callback wird ein Vector mit Organisationseinheiten zurückgegben.
//	 * Anschließend wird geprüft ob es sich bei der jeweiligen Organisationseinheit um eine
//	 * Person, ein Unternehmen oder ein Team handelt.
//	 */
//	reportGenerator.getBewerberByAusschreibungen(identitySelectionReport.getSelectedIdentityAsObjectReport(), 
//			new AsyncCallback<Vector<Organisationseinheit>>() {
//
//				@Override
//				public void onFailure(Throwable caught) {
//					Window.alert("Fehler: "+caught.getMessage());
//					
//				}
//
//				@Override
//				public void onSuccess(Vector<Organisationseinheit> result) {
//					
//					for (Organisationseinheit bewerber : result) {
//						
//						  
//					      if(bewerber instanceof Person){
//								bewerberBox.addItem(((Person)bewerber).getVorname() + " "
//					      + ((Person)bewerber).getName()+", ID:"+bewerber.getID());
//							
//							} else if(bewerber instanceof Team){
//								bewerberBox.addItem(((Team)bewerber).getName() + ", ID:" +bewerber.getID());
//							
//							} else if(bewerber instanceof Unternehmen){
//								bewerberBox.addItem(((Unternehmen)bewerber).getName() + ", ID:" +bewerber.getID());
//						
//								
//							}	
//					}	
//			}
//	});
//	
//	
//	inputPanel.add(bewerberBox);
//	inputPanel.add(child);;
//	this.add(inputPanel);
//	
//	/**
//	 * Anlegen Click-Handler
//	 * 
//	 */
//	bewerberBox.addChangeHandler(new ChangeHandler() {
//		
//		@Override
//		public void onChange(ChangeEvent event) {
//			
//			
//			ReportGeneratorAsync reportGenerator = ClientsideSettings.getReportGenerator();
//			
//			resultPanel.clear();
//			String s= bewerberBox.getValue(bewerberBox.getSelectedIndex());	
//			String last = s.substring(s.indexOf(':')+1, s.length());
//			int selectedId = Integer.valueOf(last);	
//		
//			/**
//			 * Bei erfolgreichem Callback wird zu dem in der Listbox ausgewählten Bewerber 
//			 * ein Report mit dessen Projektverflechtungen ausgegeben.
//			 */
//			reportGenerator.createProjektverflechtungenReport(selectedId, 
//					new AsyncCallback<ProjektverflechtungenReport>() {
//
//						@Override
//						public void onFailure(Throwable caught) {
//							Window.alert("Fehler bei Projektverflechtungen Report: "+caught.getMessage());
//							
//						}
//
//						@Override
//						public void onSuccess(ProjektverflechtungenReport result) {
//							
//							if(result!= null){
//
//								HTMLReportWriter writer = new HTMLReportWriter();
//							
//								writer.process(result);
//				
//								resultPanel.append(writer.getReportText());
//							}
//						}
//			});
//			
//		}
//	});
//}
}