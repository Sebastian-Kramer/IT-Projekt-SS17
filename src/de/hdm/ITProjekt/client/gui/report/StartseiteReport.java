package de.hdm.ITProjekt.client.gui.report;

import de.hdm.ITProjekt.client.ReportShowcase;

public class StartseiteReport extends ReportShowcase{

	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "Projektmarktplatz Reports";
	}

	@Override
	protected void run() {
		this.append("Hier können Sie Reports erstellen");
	}

}
