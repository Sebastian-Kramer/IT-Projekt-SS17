package de.hdm.ITProjekt.client.gui.report;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.client.Showcase;
import de.hdm.ITProjekt.shared.ReportGeneratorAsync;
import de.hdm.ITProjekt.shared.report.AllBewerbungenByAusschreibungReport;
import de.hdm.ITProjekt.shared.report.HTMLReportWriter;

/**
 * Diese Klasse gibt einen Report aller Bewerbungen des Users qus. 
 * Hierfür wird die ReportGeneratorAsync Instanz ausgelesen. 
 * Bei erfolgreichem Callback werden alle Bewerbungen eines Users,
 * in Form eines Reports ausgegeben.
 * @author Giuseppe
 */

public class AllBewerbungenByPersonShowcase extends Showcase {

	private IdentitySelectionReport isreport = null;
	
	/**
	 * Konstruktor, dem eine Instanz der IdentitySelectionReport und der Menubar übergeben wird.
	 * @param identitySelectionReport
	 */
	public AllBewerbungenByPersonShowcase(IdentitySelectionReport isreport) {
		this.isreport = isreport;
	}

	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "<h1>Report für meine Bewerbungen</h1>";
	}

	@Override
	protected void run() {
		ReportGeneratorAsync reportGenerator = ClientsideSettings.getReportGenerator();
			
		final Showcase showcase = this;
		
		reportGenerator.createAllBewerbungenByAusschreibungReport(isreport.getSelectedIdentityAsObjectReport(), 
				new AsyncCallback<AllBewerbungenByAusschreibungReport>() {
			
			@Override
			public void onSuccess(AllBewerbungenByAusschreibungReport result) {

				if(result!= null){
					
					HTMLReportWriter writer = new HTMLReportWriter();
				
					writer.process(result);
					
					showcase.append(writer.getReportText());
				}
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler: " + caught.getMessage());
			}
		});
		
	}


	}


