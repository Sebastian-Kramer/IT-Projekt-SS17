package de.hdm.ITProjekt.client.gui.report;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.client.ReportShowcase;
import de.hdm.ITProjekt.client.Showcase;
import de.hdm.ITProjekt.shared.ReportGeneratorAsync;
import de.hdm.ITProjekt.shared.report.FanInFanOutReport;
import de.hdm.ITProjekt.shared.report.HTMLReportWriter;

public class FanInFanOutShowcase extends Showcase {

	@Override
	protected String getHeadlineText() {
		return "Report für die Fan In- Fan Out Analyse";
	}

	@Override
	protected void run() {

		final Showcase showcase = this;
		
		/**
		 * Auslesen der ProjektmarktplatzAsync Instanz
		 */
		ReportGeneratorAsync reportGenerator = ClientsideSettings.getReportGenerator();
		
		
		/**
		 * Bei erfolgreichem Callback wird ein Report mit einer Fan-in/ Fan-out Analyse ausgegeben.
		 */
		reportGenerator.createFanInFanOutReport(new AsyncCallback<FanInFanOutReport>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert(" " + caught.getMessage());
						
					}

					@Override
					public void onSuccess(FanInFanOutReport result) {
						
						if(result!= null){
							
							HTMLReportWriter writer = new HTMLReportWriter();
						
							writer.process(result);
							
							showcase.append(writer.getReportText());
						}
					}
		});
		
	}
	}


