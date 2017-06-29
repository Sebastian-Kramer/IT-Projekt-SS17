package de.hdm.ITProjekt.client.gui.report;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.client.Showcase;
import de.hdm.ITProjekt.shared.ReportGeneratorAsync;
import de.hdm.ITProjekt.shared.report.AllAusschreibungenByPartnerprofilReport;
import de.hdm.ITProjekt.shared.report.HTMLReportWriter;

/**
 * Diese Klasse gibt einen Report mit passenden Ausschreibungen auf das Partnerprofil der Users qus. 
 * Hierfür wird die ReportGeneratorAsync Instanz ausgelesen. 
 * Bei erfolgreichem Callback werden alle Ausschreibungen, die zum Partnerprofil des Users passen,
 * in Form eines Reports ausgegeben.
 * @author Giuseppe
 */

public class AllAusschreibungenByPartnerprofilShowcase extends Showcase {

	private IdentitySelectionReport identitySelectionReport = null;
	
	public AllAusschreibungenByPartnerprofilShowcase(){
	}
	
	/**
	 * Konstruktor, dem eine Instanz der IdentitySelectionReport und der Menubar übergeben wird.
	 * @param identitySelectionReport
	 */
	public AllAusschreibungenByPartnerprofilShowcase(IdentitySelectionReport identityChoiceReport) {
		this.identitySelectionReport = identityChoiceReport;
	}
	
	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "Alle Ausschreibungen des Partnerprofils";
	}

	@Override
	protected void run() {
		final Showcase showcase = this;
		
		this.append("Auslesen aller Ausschreibungen des Partnerprofils");
		
		
		ReportGeneratorAsync reportGenerator = ClientsideSettings.getReportGenerator();
		
		reportGenerator.createAllAusschreibungenByPartnerprofilReport(identitySelectionReport.getSelectedIdentityAsObjectReport(), new AsyncCallback<AllAusschreibungenByPartnerprofilReport>() {
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler: " + caught.getMessage());
				
			}
			@Override
			public void onSuccess(AllAusschreibungenByPartnerprofilReport result) {
				if(result!= null){
					
					HTMLReportWriter writer = new HTMLReportWriter();
				
					writer.process(result);
					
					showcase.append(writer.getReportText());
			}
			}
		});
	}
}



