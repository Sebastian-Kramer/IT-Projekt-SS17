package de.hdm.ITProjekt.client.gui.report;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.client.ReportShowcase;
import de.hdm.ITProjekt.client.Showcase;
import de.hdm.ITProjekt.shared.ReportGeneratorAsync;
import de.hdm.ITProjekt.shared.report.AllAusschreibungenByPartnerprofilReport;
import de.hdm.ITProjekt.shared.report.HTMLReportWriter;

public class AllAusschreibungenByPartnerprofilShowcase extends Showcase {

	private IdentitySelectionReport identitySelectionReport = null;
	
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



