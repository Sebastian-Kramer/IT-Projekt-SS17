package de.hdm.ITProjekt.client;
import com.google.gwt.user.client.ui.*;

	/**
	 * Diese Klasse ist die Basisklasse aller Showcases. Jeder Showcase ist ein
	 * VerticalPanel und lÃ¤sst sich somit unter GWT entsprechend anordnen.
	 * 
	 * @author thies
	 * @version 1.0
	 * 
	 */
	public abstract class ReportShowcase extends VerticalPanel {

	  /**
	   * Jedes GWT Widget muss diese Methode implementieren. Sie gibt an, sas
	   * geschehen soll, wenn eine Widget-Instanz zur Anzeige gebracht wird.
	   */
	  @Override
	public void onLoad() {
	    /*
	     * Bevor wir unsere eigene Formatierung veranslassen, Ã¼berlassen wir es der
	     * Superklasse eine Initialisierung vorzunehmen.
	     */
	    super.onLoad();

	    /*
	     * Als erstes geben wir stets die Headline des Showcase aus. Da
	     * getHeadlineText() als abstrakte Methode bzw. als Einschubmethode
	     * realisiert wird, obliegt es den Subklassen, fÃ¼r eine Ausgestaltung also
	     * Implementierung zu sorgen.
	     */
	    this.add(this.createHeadline(this.getHeadlineText()));

	    /*
	     * Wenn alles vorbereitet ist, stoÃŸen wir die run()-Methode an. Auch run()
	     * ist als abstrakte Methode bzw. als Einschubmethode realisiert. Auch hier
	     * ist es Aufgabe der Subklassen, fÃ¼r deren Implementierung zu sorgen.
	     */
	    this.run();
	  }

	  /**
	   * Mit Hilfe dieser Methode erstellen wir aus einem String ein mittels CSS
	   * formatierbares HTML-Element. Unter CSS lÃ¤sst sich das Ergebnis Ã¼ber
	   * <code>.bankproject-headline</code> referenzieren bzw. formatieren.
	   * 
	   * @param text der String, den wir als andernorts HTML setzen wollen.
	   * @return GWT HTML Widget.
	   */
	  protected HTML createHeadline(String text) {
	    HTML content = new HTML(text);
	    content.setStylePrimaryName("bankproject-headline");
	    return content;
	  }

	  /**
	   * Mit Hilfe dieser Methode erstellen wir aus einem Strinng ein mittels CSS
	   * formatierbares HTML-Element, das an das Ende der bisherigen Ausgabe dieses
	   * Showcase angehÃ¤ngt wird. Unter CSS lÃ¤sst sich das Ergebnis Ã¼ber
	   * <code>.bankproject-simpletext</code> referenzieren bzw. formatieren.
	   * 
	   * @param text der String, den wir als HTML an die bisherige Showcase-Ausgabe
	   *          anhÃ¤ngen wollen.
	   */
	  protected void append(String text) {
	    HTML content = new HTML(text);
	    content.setStylePrimaryName("bankproject-simpletext");
	    this.add(content);
	  }

	  /**
	   * Abstrakte Einschubmethode, die in den Subklassen zu realisieren ist.
	   * @return der Text, den wir als Headline setzen wollen. 
	   */
	  protected abstract String getHeadlineText();

	  /**
	   * Abstrakte Einschubmethode, die in den Subklassen zu realisieren ist.
	   */
	  protected abstract void run();
	}


