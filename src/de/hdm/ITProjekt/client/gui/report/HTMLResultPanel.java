package de.hdm.ITProjekt.client.gui.report;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Ein Element der GUI, das für die Projektverflechtungen gebraucht wird.
 * @author Giuseppe
 */
public class HTMLResultPanel extends VerticalPanel {

	
	public void append(String text) {
	    HTML content = new HTML(text);
	    content.setStylePrimaryName("projektmarktplatz-simpletext");
	    this.add(content);
	  }

	
	
}

