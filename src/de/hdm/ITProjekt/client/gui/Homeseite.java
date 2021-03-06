package de.hdm.ITProjekt.client.gui;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.ITProjekt.client.Showcase;

public class Homeseite extends Showcase{

	@Override
	protected String getHeadlineText() {
		return null;
	}

	@Override
	protected void run() {
		
		VerticalPanel homePanel = new VerticalPanel();
		
		this.append("<article> <h2>Projektmarktplatz</h2> "
				+ "<section>"
				+ "<h3>Hier können Sie neue Mitarbeiter suchen und einstellen</h3> "
				+ "<p>"
				+ "Herzlich Willkommen auf dem Projektmarktplatz </br>"
				+ "Hier haben Sie die Möglichkeit viele verschiedene Personen für Ihre Projekte zu finden und einzustellen. "
				+ "Sie können sich natürlich auch auf diverse Stellen bewerben, falls Sie auf der Suche sind.</br> "
				+ "Es warten viele spannende und neue Herausforderungen auf Sie.</br>"
				+ "Viel Spaß und viel Glück.</br> "
				+ "Falls Sie fragen zur Funktion haben oder Support benötigen, können Sie unsere Kontaktdaten im Impressum finden. </p>"
				+ "	</section>	"
				+ "</article>");


		this.add(homePanel);
	}
}