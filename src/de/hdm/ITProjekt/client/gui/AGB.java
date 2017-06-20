package de.hdm.ITProjekt.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.client.Showcase;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;

public class AGB extends Showcase {

	private static ClickHandler currentClickHandler = null;
	private static ClickEvent currentClickEvent = null;
	
	AdministrationProjektmarktplatzAsync adminService = ClientsideSettings.getpmpVerwaltung();
	
	
	@Override
	protected String getHeadlineText() {
		
		return 	"Unsere AGB's";
	}

	@Override
	protected void run() {
		
		RootPanel.get("Details").setWidth("100%");
		
		this.append("<div class='Unsere AGB's'>"
				+ "<b>" +"Angaben gem&auml&szlig §5 Telemediengesetz</b></br></br>"
				+ "Hochschule der Medien"+ "</br>"
				+ "<b>Wirtschaftsinformatik und Digitale Medien</b></br>"
				+ "Nobelstra&szlige 10</br>"
				+ "70569 Stuttgart</br></br>"
				+ "Kontakt</br>Telefon: +49 711 8923 10</br>"
				+ "<br><br>Der Studiengang Wirtschaftsinformatik und digitale "
				+ "Medien<br>wird rechtlich vertreten durch die Hochschule der Med"
				+ "ien Stuttgart. <br> <br><A HREF=\"https://www.hdm-stuttgart.de/"
				+ "impressum\"TARGET=\"_blank\">Impressum der Hochschule</A>"
				+ "</div>");
	

		
		
			
		}
		
		

	}
//		this.setHTML((
//				"<div class='Impressum'>"
//				+ "<b>" +"Angaben gem&auml&szlig §5 Telemediengesetz</b></br></br>"
//				+ "Hochschule der Medien"+ "</br>"
//				+ "<b>Wirtschaftsinformatik und Digitale Medien</b></br>"
//				+ "Nobelstra&szlige 10</br>"
//				+ "70569 Stuttgart</br></br>"
//				+ "Kontakt</br>Telefon: +49 711 8923 10</br>"
//				+ "<br><br>Der Studiengang Wirtschaftsinformatik und digitale "
//				+ "Medien<br>wird rechtlich vertreten durch die Hochschule der Med"
//				+ "ien Stuttgart. <br> <br><A HREF=\"https://www.hdm-stuttgart.de/"
//				+ "impressum\"TARGET=\"_blank\">Impressum der Hochschule</A>"
//				+ "</div>"));
//		}
//		
//	}
	
	

//	private static AGB navigation=null;
//	private static ClickHandler currentClickHandler = null;
//	private static ClickEvent currentClickEvent = null;
//
//
//	
//	
//
//	
//	public AGB(){
		
//	
//		
//		this.setHTML((
//				"<div class='Impressum'>"
//				+ "<b>" +"Angaben gem&auml&szlig §5 Telemediengesetz</b></br></br>"
//				+ "Hochschule der Medien"+ "</br>"
//				+ "<b>Wirtschaftsinformatik und Digitale Medien</b></br>"
//				+ "Nobelstra&szlige 10</br>"
//				+ "70569 Stuttgart</br></br>"
//				+ "Kontakt</br>Telefon: +49 711 8923 10</br>"
//				+ "<br><br>Der Studiengang Wirtschaftsinformatik und digitale "
//				+ "Medien<br>wird rechtlich vertreten durch die Hochschule der Med"
//				+ "ien Stuttgart. <br> <br><A HREF=\"https://www.hdm-stuttgart.de/"
//				+ "impressum\"TARGET=\"_blank\">Impressum der Hochschule</A>"
//				+ "</div>"));
//		}
	
	
//	@Override
//	protected String getHeadlineText() {
//		
//		return "Unsere AGB's";
//	}
//
//	@Override
//	protected void run() {
//		// TODO Auto-generated method stub
//		
//		VerticalPanel aPanel  = new VerticalPanel();
//		
//		Label aLabel = new Label("Unsere AGB's");
//	aLabel.setWidth("250px");
//	aPanel.setHeight("250px");
//		aPanel.add(aLabel);
//		this.add(aLabel);
//		
//	//Button AGB = new Button ("AGB");
//	RootPanel.get("AGB").add(aPanel);	
//
//	aLabel.addClickHandler(new ClickHandler() {
//		
//		@Override
//	public void onClick(ClickEvent event) {
//			
//	
//	}
//			
//			
//		
//	});
//	
//
//	}}

