package de.hdm.ITProjekt.client;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import de.hdm.ITProjekt.shared.*;
import de.hdm.ITProjekt.shared.CommonSettings;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatz;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;

public class ClientsideSettings extends CommonSettings{

	  /**
	   * Remote Service Proxy zur Verbindungsaufnahme mit dem Server-seitgen Dienst
	   * namens <code>BankAdministration</code>.
	   */

	  private static AdministrationProjektmarktplatzAsync pmpVerwaltung = null;
	  
	  /**
	   * Name des Client-seitigen Loggers.
	   */
	  private static final String LOGGER_NAME = "Projektmarktplatz Web Client";
	  
	  /**
	   * Instanz des Client-seitigen Loggers.
	   */
	  private static final Logger log = Logger.getLogger(LOGGER_NAME);
	  /**
	   * <p>
	   * Auslesen des applikationsweiten (Client-seitig!) zentralen Loggers.
	   * </p>
	   * 
	   * <h2>Anwendungsbeispiel:</h2> Zugriff auf den Logger herstellen durch:
	   * 
	   * <pre>
	   * Logger logger = ClientSideSettings.getLogger();
	   * </pre>
	   * 
	   * und dann Nachrichten schreiben etwa mittels
	   * 
	   * <pre>
	   * logger.severe(&quot;Sie sind nicht berechtigt, ...&quot;);
	   * </pre>
	   * 
	   * oder
	   * 
	   * <pre>
	   * logger.info(&quot;Lege neuen Kunden an.&quot;);
	   * </pre>
	   * 
	   * <p>
	   * Bitte auf <em>angemessene Log Levels</em> achten! Severe und info sind nur
	   * Beispiele.
	   * </p>
	   * 
	   * <h2>HINWEIS:</h2>
	   * <p>
	   * Beachten Sie, dass Sie den auszugebenden Log nun nicht mehr durch
	   * bedarfsweise Einfügen und Auskommentieren etwa von
	   * <code>System.out.println(...);</code> steuern. Sie belassen künftig
	   * sämtliches Logging im Code und können ohne abermaliges Kompilieren den Log
	   * Level "von außen" durch die Datei <code>logging.properties</code> steuern.
	   * Sie finden diese Datei in Ihrem <code>war/WEB-INF</code>-Ordner. Der dort
	   * standardmäßig vorgegebene Log Level ist <code>WARN</code>. Dies würde
	   * bedeuten, dass Sie keine <code>INFO</code>-Meldungen wohl aber
	   * <code>WARN</code>- und <code>SEVERE</code>-Meldungen erhielten. Wenn Sie
	   * also auch Log des Levels <code>INFO</code> wollten, müssten Sie in dieser
	   * Datei <code>.level = INFO</code> setzen.
	   * </p>
	   * 
	   * Weitere Infos siehe Dokumentation zu Java Logging.
	   * 
	   * @return die Logger-Instanz für die Server-Seite
	   */
	  
	  public static Logger getLogger() {
		    return log;
		  }

	  public static AdministrationProjektmarktplatzAsync getpmpVerwaltung() {
		    // Gab es bislang noch keine BankAdministration-Instanz, dann...
		    if (pmpVerwaltung == null) {
		      // Zunächst instantiieren wir BankAdministration
		    	pmpVerwaltung = GWT.create(AdministrationProjektmarktplatz.class);
		   // So, nun brauchen wir die BankAdministration nur noch zurückzugeben.
		     
		    }
			return pmpVerwaltung;
	  }	
}


