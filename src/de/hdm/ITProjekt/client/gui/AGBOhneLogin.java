package de.hdm.ITProjekt.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.client.Showcase;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;


public class AGBOhneLogin extends Showcase {
	
	@Override
	protected String getHeadlineText() {
		
		return 	"<b>Unsere AGB's</b>";
	}

	@Override
	protected void run() {
		
		RootPanel.get("Details").setWidth("100%");

		
		this.append("<div class="
				+ "<b>" +"Hinweise zum Datenschutz</b></br></br>"
				+ "<b>" +"1. Allgemeines</b></br></br>"
				+ " Wir erheben und verwenden Ihre personenbezogenen Daten ausschließlich im Rahmen der Bestimmungen des Datenschutzrechts "
				+ "der Bundesrepublik Deutschland. Maßgeblich sind Bestimmungen des Bundesdatenschutzgesetzes (BDSG) sowie zum Teil speziellere Rechtsvorschriften, "
				+ "insb. diejenigen des Telekommunikations- (TKG) und des Telemediengesetzes (TMG)."
				+ "Die genannten Vorschriften betreffen personenbezogene Daten. Personenbezogene Daten sind Einzelangaben über persönliche und sachliche Verhältnisse einer bestimmten oder bestimmbaren natürlichen Person. Dies können bei einer Kontaktaufnahme per Post, E-Mail, oder Web-Formular beispielsweise der Name, die Anschrift oder die E-Mail Adresse sein."
				+ "Hiermit unterrichten wir Sie über Art, Umfang und Zwecke der Erhebung und Verwendung derjenigen Daten, die während Ihres Besuchs auf unserer Homepage erfasst und wie sie genutzt werden. Sie können diese Unterrichtung jederzeit auf unserer Webseite abrufen.</br>"
				+"<br></br>"
				+  "<b>" +"2. Zugriff auf die Homepage</b></br></br>"
				+ " Jeder Zugriff auf unsere Homepage und jeder Abruf einer auf der Homepage hinterlegten Datei wird protokolliert. Die Speicherung dient internen systembezogenen und statistischen Zwecken. Protokolliert werden: Name der abgerufenen Datei, Datum und Uhrzeit des Abrufs, übertragene Datenmenge, Meldung über erfolgreichen Abruf, Browser Version des zugreifenden Hostsystems; Betriebssystem Version des zugreifenden Hostsystems."
				+ "Zusätzlich werden die IP Adressen der anfragenden Rechner protokolliert."
				+ "</div>");
		}
		
		

	}


