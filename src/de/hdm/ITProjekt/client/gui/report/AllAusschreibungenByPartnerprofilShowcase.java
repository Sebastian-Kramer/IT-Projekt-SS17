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

	private IdentitySelectionReport isreport = null;
	
	
	public AllAusschreibungenByPartnerprofilShowcase(IdentitySelectionReport isreport) {
		this.isreport = isreport;
	}
	
	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "<h1>Alle Ausschreibungen des Partnerprofils</h1>";
	}

	@Override
	protected void run() {
		final Showcase showcase = this;
		
		this.append("<h3>Auslesen aller Ausschreibungen des Partnerprofils</h3>");
		
		
		ReportGeneratorAsync reportGenerator = ClientsideSettings.getReportGenerator();
		
		reportGenerator.getAusschreibungByMatchingPartnerprofil(isreport.getSelectedIdentityAsObjectReport(), new AsyncCallback<AllAusschreibungenByPartnerprofilReport>() {
			
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



