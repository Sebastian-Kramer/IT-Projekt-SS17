package de.hdm.ITProjekt.client.gui.report;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import de.hdm.ITProjekt.client.Showcase;
import de.hdm.ITProjekt.shared.bo.Person;

/**
 * In dieser Klasse wird die Menubar der ReportSeite erstellt.
 * @author Giuseppe
 */

public class MenubarReport extends HorizontalPanel{
	// Clickhandler auf "null" setzen
	
	private static ClickHandler currentClickHandler = null;
	private static ClickEvent currentClickEvent = null;
	
	// Die "einzelnen" Seiten in die Panels legen
	private VerticalPanel homePanel = new VerticalPanel();
	
	// Buttons für die Panels erstellen
	
	// Buttons in dem Panel "home"
	private Button startseite = new Button ("Startseite");	
	private Button alleBewerbungenByPerson = new Button("Eigene Bewerbungen");
	private Button alleAusschreibungen = new Button("Alle Ausschreibungen");
	private Button alleAusschreibungenzupartnerprofil = new Button("Ausschreibungen zu Partnerprofil");
	private Button alleeigenenbewerbungen = new Button("Bewerbungen auf eigene Ausschreibungen");
	private Button faninfanoutanalyse = new Button ("Fan in/Fan out Analyse");
	private Button projektverflechtungen = new Button("Projektverflechtungen anzeigen");
	
	IdentitySelectionReport isreport = null;
			
	public MenubarReport(final Person person){
		
		this.setSpacing(10);
		this.add(startseite);
		this.add(alleBewerbungenByPerson);
		this.add(alleAusschreibungen);
		this.add(alleAusschreibungenzupartnerprofil);
		this.add(alleeigenenbewerbungen);
		this.add(faninfanoutanalyse);
		this.add(projektverflechtungen);
		
			startseite.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					Showcase reportShowcase = new StartseiteReport();
					RootPanel.get("DetailsReport").clear();
					RootPanel.get("DetailsReport").add(reportShowcase);
					currentClickHandler=this;
					currentClickEvent=event;
				}
			});
		
			alleBewerbungenByPerson.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Showcase reportShowcase = new AllBewerbungenByPersonShowcase(isreport);
				RootPanel.get("DetailsReport").clear();
				RootPanel.get("DetailsReport").add(reportShowcase);
				currentClickHandler=this;
				currentClickEvent=event;
				
			}
		});
			
		alleAusschreibungen.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Showcase Showcase = new AllAusschreibungenShowcase();
				RootPanel.get("DetailsReport").clear();
				RootPanel.get("DetailsReport").add(Showcase);
				currentClickHandler=this;
				currentClickEvent=event;
				
			}
		});
		
		alleAusschreibungenzupartnerprofil.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				Showcase Showcase = new AllAusschreibungenByPartnerprofilShowcase(isreport);
				RootPanel.get("DetailsReport").clear();
				RootPanel.get("DetailsReport").add(Showcase);
				currentClickHandler=this;
				currentClickEvent=event;
			}
		});

		alleeigenenbewerbungen.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				Showcase showcase = new AllBewerbungenByAusschreibungShowcase(isreport);
				RootPanel.get("DetailsReport").clear();
				RootPanel.get("DetailsReport").add(showcase);
				currentClickHandler=this;
				currentClickEvent=event;
			}
		});
		
			faninfanoutanalyse.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				Showcase showcase = new FanInFanOutShowcase();
				RootPanel.get("DetailsReport").clear();
				RootPanel.get("DetailsReport").add(showcase);
				currentClickHandler=this;
				currentClickEvent=event;
			}
			});
					
			projektverflechtungen.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				Showcase showcase = new ProjektverflechtungenShowcase(isreport);
				RootPanel.get("DetailsReport").clear();
				RootPanel.get("DetailsReport").add(showcase);
				currentClickHandler=this;
				currentClickEvent=event;
		
			
	}
			});
	}
	
	private void add(VerticalPanel homePanel2, String string, VerticalPanel projektmarktplatzPanel2, String string2) {
		// TODO Auto-generated method stub
		
	}

	
	public void setIdSelectionReport(IdentitySelectionReport idSelection) {
		this.isreport = idSelection;
	}
	public IdentitySelectionReport getIdSelectionReport() {
		return isreport;
	}

	public void reloadReport(){
		currentClickHandler.onClick(currentClickEvent);
	}
	
	public MenubarReport getMenubarReport(){
		return this;
	}
	
	}
