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
				+ "<h3>Unterueberschrift</h3> "
				+ "<p>"
				+ "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam tristique tristique "
				+ "lacus in varius. Donec nec vestibulum ligula. "
				+ "Aenean turpis diam, feugiat a luctus in, rhoncus eget risus. Maecenas "
				+ "consequat porttitor dui, vitae consequat massa imperdiet ut. elit ut tempus lobortis, eros leo "
				+ "molestie velit, non egestas augue nulla eget erat. </p>"
				+ "	</section>	"
				+ "</article>");


		this.add(homePanel);
	}
}