package de.hdm.ITProjekt.client.gui.report;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.client.Showcase;
import de.hdm.ITProjekt.shared.ReportGeneratorAsync;
import de.hdm.ITProjekt.shared.report.AllBewerbungenWithAusschreibungenReport;
import de.hdm.ITProjekt.shared.report.HTMLReportWriter;

public class AllBewerbungenByAusschreibungShowcase extends Showcase {

	private IdentitySelectionReport isreport = null;
	
	public AllBewerbungenByAusschreibungShowcase(IdentitySelectionReport isreport) {
		this.isreport = isreport;
	}

	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "Report für alle Bewerbungen auf die eigene Ausschreibungen";
	}

	@Override
	protected void run() {
	final Showcase showcase = this;
		
		/**
		 * Auslesen der ReportGeneratorAsync Instanz
		 */		
		ReportGeneratorAsync reportGenerator = ClientsideSettings.getReportGenerator();
		
		
		/**
		 * Bei erfolgreichem Callback werden alle Bewerbungen die auf eigene Ausschreibungen eingingen als
		 * Report ausgegeben.
		 */
		reportGenerator.createAllBewerbungenWithAusschreibungenReport(isreport.getSelectedIdentityAsObjectReport(),
				new AsyncCallback<AllBewerbungenWithAusschreibungenReport>() {

					@Override
					public void onFailure(Throwable caught) {
						showcase.append("Fehler: "+ caught.getMessage());
						
					}

					@Override
					public void onSuccess(AllBewerbungenWithAusschreibungenReport result) {
						if(result!= null){
							
							HTMLReportWriter writer = new HTMLReportWriter();
						
							writer.process(result);
							
							showcase.append(writer.getReportText());
							}
						
					}
				});
		
	}


	}


