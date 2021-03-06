package de.hdm.ITProjekt.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.ITProjekt.client.Showcase;

public class Impressum extends Showcase{
	private Button zumLoginButton = new Button("Zum Login");
	private Anchor zumLoginAnchor = new Anchor();

	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "<b>Impressum</b>";
	}

	@Override
	protected void run() {
		
		RootPanel.get("Details").setWidth("100%");
		
		zumLoginButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				zumLoginAnchor.setHref(GWT.getHostPageBaseURL()+"IT_Projekt_SS17.html");
				Window.open(zumLoginAnchor.getHref(), "_self", "");
				}
			
		});
		
		this.add(zumLoginButton);
		
		this.append("<div class='Impressum'>"
				+ "Hochschule der Medien"+ "</br>"
				+ "<b>Wirtschaftsinformatik und Digitale Medien</b></br>"
				+ "Nobelstrasse 10</br>"
				+ "70569 Stuttgart</br></br>"
				+ "Kontakt</br>Telefon: +49 711 8923 10</br>"
				+ "<br><br>Der Studiengang Wirtschaftsinformatik und digitale "
				+ "Medien<br>wird rechtlich vertreten durch die Hochschule der Med"
				+ "ien Stuttgart. <br> <br><A HREF=\"https://www.hdm-stuttgart.de/"
				+ "impressum\"TARGET=\"_blank\">Impressum der Hochschule</A>"
				+ "</div>");
		
	}

}
