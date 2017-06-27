package de.hdm.ITProjekt.client.gui.report;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.client.ReportShowcase;
import de.hdm.ITProjekt.client.Showcase;
import de.hdm.ITProjekt.shared.ReportGeneratorAsync;
import de.hdm.ITProjekt.shared.report.AllAusschreibungenByPartnerprofilReport;
import de.hdm.ITProjekt.shared.report.HTMLReportWriter;

public class AllAusschreibungenByPartnerprofilShowcase extends ReportShowcase {

	private IdentitySelectionReport identitySelectionReport = null;
	
	/**
	 * Konstruktor, dem eine Instanz der IdentityChoiceReport und der Navigation übergeben wird.
	 * @param identityChoiceReport
	 */
	public AllAusschreibungenByPartnerprofilShowcase(){
	}
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
		// TODO Auto-generated method stub

		final ReportShowcase showcase = this;
		
		this.append("Auslesen aller Ausschreibungen des Partnerprofils");
		
		/**
		 * Auslesen der ReportGeneratorAsync Instanz
		 */
		ReportGeneratorAsync reportGenerator = ClientsideSettings.getReportGenerator();
		
		/**
		 * Bei erfolgreichem Callback werden alle Ausschreibungen als Report ausgegeben.
		 */
		reportGenerator.createAllAusschreibungenByPartnerprofilReport(identitySelectionReport.getSelectedIdentityAsObjectReport(), new AsyncCallback<AllAusschreibungenByPartnerprofilReport>() {
			
			@Override
			public void onSuccess(AllAusschreibungenByPartnerprofilReport result) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	}
			public void onFailure(Throwable caught) {
				Window.alert("Fehler: " + caught.getMessage());
				
			}

			public void onSuccess(AllAusschreibungenByPartnerprofilReport result) {
				if(result!= null){
					
					HTMLReportWriter writer = new HTMLReportWriter();
				
					writer.process(result);
					
//					showcase.append(writer.getReportText());
			}
		};
}



