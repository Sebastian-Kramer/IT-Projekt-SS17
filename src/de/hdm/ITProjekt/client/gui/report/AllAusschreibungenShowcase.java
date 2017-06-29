package de.hdm.ITProjekt.client.gui.report;

import com.google.gwt.user.client.rpc.AsyncCallback;
import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.client.ReportShowcase;
import de.hdm.ITProjekt.client.Showcase;
import de.hdm.ITProjekt.shared.ReportGeneratorAsync;
import de.hdm.ITProjekt.shared.report.AllAusschreibungenReport;
import de.hdm.ITProjekt.shared.report.HTMLReportWriter;

public class AllAusschreibungenShowcase extends Showcase {

	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "<h1>Alle Ausschreibungen</h1>";
	}

	@Override
	protected void run() {
		// TODO Auto-generated method stub

		final Showcase showcase = this;
		
		this.append("<h3>Auslesen aller Ausschreibungen auf dem Projektmarktplatz</h3>");
		
		/**
		 * Auslesen der ReportGeneratorAsync Instanz
		 */
		ReportGeneratorAsync reportGenerator = ClientsideSettings.getReportGenerator();
		
		/**
		 * Bei erfolgreichem Callback werden alle Ausschreibungen als Report ausgegeben.
		 */
		reportGenerator.createAllAusschreibungenReport(new AsyncCallback<AllAusschreibungenReport>() {
		
			public void onFailure(Throwable caught) {
//				showcase.append("Fehler: " + caught.getMessage());
				
			}

			@Override
			public void onSuccess(AllAusschreibungenReport result) {
				if(result!= null){
					
					HTMLReportWriter writer = new HTMLReportWriter();
				
					writer.process(result);
					showcase.append(writer.getReportText());
				}	
			}
		});	
	}
}