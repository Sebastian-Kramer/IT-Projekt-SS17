package de.hdm.ITProjekt.client.gui.report;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.client.gui.IdentitySelection;
import de.hdm.ITProjekt.shared.ReportGeneratorAsync;
import de.hdm.ITProjekt.shared.bo.Person;
import de.hdm.ITProjekt.shared.bo.Team;
import de.hdm.ITProjekt.shared.bo.Unternehmen;

public class IdentitySelectionReport extends FlexTable{

	private static IdentitySelection navigation=null;
	
	private ListBox orgEinheit = new ListBox();
	
	private FlexCellFormatter cellFormatter = this.getFlexCellFormatter();
	
	private static ReportGeneratorAsync adminService = ClientsideSettings.getReportGenerator();
	
	// Zum Speichern von Informationen über die Identität
	
	private static Person person;
	private static Team team;
	private static Unternehmen unternehmen;
	private MenubarReport menubarReport;
	
	public IdentitySelectionReport (final Person person, final MenubarReport menubarReport){
	this.menubarReport=menubarReport;
	this.setWidget(1, 0, new Label("Nutze Identität von: "));
	this.setWidget(1, 1, orgEinheit);
	this.setStylePrimaryName("IdentityPanel");
	
	cellFormatter.setHorizontalAlignment(1, 1, HasHorizontalAlignment.ALIGN_RIGHT);
	cellFormatter.setHorizontalAlignment(2, 1, HasHorizontalAlignment.ALIGN_RIGHT);
	orgEinheit.setWidth("200px");

	
}
}