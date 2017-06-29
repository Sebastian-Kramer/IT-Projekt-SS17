package de.hdm.ITProjekt.client.gui.report;

import de.hdm.ITProjekt.client.ReportShowcase;
import de.hdm.ITProjekt.client.Showcase;

/**
 * Startseite der Reports, um Reports erstellen zu können.
 * @author Giuseppe
 */
public class StartseiteReport extends Showcase{


	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "<h1>Projektmarktplatz Reports</h1>";
	}

	@Override
	protected void run() {
		this.append("<h3>Hier können Sie Reports erstellen</h3>");
	}

}
