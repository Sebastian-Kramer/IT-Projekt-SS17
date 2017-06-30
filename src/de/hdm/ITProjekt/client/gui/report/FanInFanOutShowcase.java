package de.hdm.ITProjekt.client.gui.report;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.client.Showcase;
import de.hdm.ITProjekt.shared.ReportGeneratorAsync;
import de.hdm.ITProjekt.shared.report.FanInFanOutReport;
import de.hdm.ITProjekt.shared.report.HTMLReportWriter;


/**
 * Diese Klasse gibt eine FanIn-FanOut Analyse qus. 
 * Hierfür wird die ReportGeneratorAsync Instanz ausgelesen. 
 * Bei erfolgreichem Callback wird sie
 * in Form eines Reports ausgegeben.
 * @author Giuseppe
 */
public class FanInFanOutShowcase extends Showcase {

	@Override
	protected String getHeadlineText() {
		return "Report für die Fan In- Fan Out Analyse";
	}

	@Override
	protected void run() {

		final Showcase showcase = this;
		

		ReportGeneratorAsync reportGenerator = ClientsideSettings.getReportGenerator();
		
		
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


