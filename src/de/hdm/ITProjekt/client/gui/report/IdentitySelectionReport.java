package de.hdm.ITProjekt.client.gui.report;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.client.gui.IdentitySelection;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatz;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;
import de.hdm.ITProjekt.shared.ReportGeneratorAsync;
import de.hdm.ITProjekt.shared.bo.Organisationseinheit;
import de.hdm.ITProjekt.shared.bo.Person;
import de.hdm.ITProjekt.shared.bo.Team;
import de.hdm.ITProjekt.shared.bo.Unternehmen;

public class IdentitySelectionReport extends FlexTable{

	private static IdentitySelection navigation=null;
	
	private ListBox orgEinheit = new ListBox();
	
	private FlexCellFormatter cellFormatter = this.getFlexCellFormatter();
	
	private AdministrationProjektmarktplatzAsync adminService;
	private ReportGeneratorAsync reportGenerator;
	
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
	
	this.reportGenerator = ClientsideSettings.getReportGenerator();
	this.adminService = ClientsideSettings.getpmpVerwaltung();
	
	cellFormatter.setHorizontalAlignment(1, 1, HasHorizontalAlignment.ALIGN_RIGHT);
	cellFormatter.setHorizontalAlignment(2, 1, HasHorizontalAlignment.ALIGN_RIGHT);
	orgEinheit.setWidth("200px");
	reportGenerator.findPersonByKey(person.getID(), new getUserReport());
	orgEinheit.addChangeHandler(new ChangeHandler() {

		@Override
		public void onChange(ChangeEvent event) {
			menubarReport.reloadReport();
			
		}

	});
	
}public int getSelectedIndexReport(){
	
	int selectedID = orgEinheit.getSelectedIndex();
	
	return selectedID;
}

public int getSelectedIdentityIDReport(){
	if(person.getTeam_ID() != null){
		if(orgEinheit.getSelectedIndex() == 0){
			return person.getID();
		}else if(orgEinheit.getSelectedIndex() == 1){
			return team.getID();
		}else if(orgEinheit.getSelectedIndex() == 2){
			return unternehmen.getID();
		}
	}else if (person.getTeam_ID() == null){
			if (orgEinheit.getSelectedIndex() == 0){
				return person.getID();
		}else if(orgEinheit.getSelectedIndex() == 1){
			return unternehmen.getID();   
		}
	}
return 0; 
}

public Organisationseinheit getSelectedIdentityAsObjectReport(){

	if(person.getTeam_ID() != null){
		if(orgEinheit.getSelectedIndex()==0){
			return person;
		}else if(orgEinheit.getSelectedIndex()==1){
			return team;
		}else if(orgEinheit.getSelectedIndex()==2){
			return unternehmen;
		}
	}else if(person.getTeam_ID() == null){
		if(orgEinheit.getSelectedIndex()==0){
			return person;
		}else if(orgEinheit.getSelectedIndex()==1){
			return unternehmen;
		}

	}
	return null;
}
public Person getUserReport(){
	return person;
}

public Team getTeamOfUserReport(){
	return team;
}

public Unternehmen getUnternehmenOfUserReport(){
	return unternehmen;
}

public  ListBox getOwnOrgUnitsReport(){
	return orgEinheit;
}

public void deactivateOrgUnitsReport(){
	orgEinheit.setEnabled(false);
}

//public void deactivateProjectMarkets(){
//	Listbox2.setEnabled(false);
//}

public void activateOrgUnitsReport(){
	orgEinheit.setEnabled(true);
}

//public void activateProjectMarkets(){
//	Listbox2.setEnabled(true);
//}

public void setOwnOrgUnitToZeroReport(){
	orgEinheit.setSelectedIndex(0);
}

public void reinitializeReport(){
	
	reportGenerator.findPersonByKey(person.getID(), new getUserReport());
}

private IdentitySelectionReport getThis(){
	return this;
}
	private class getUserReport implements AsyncCallback<Person>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Die Person konnte nicht gefunden werden");
			Window.alert("wegen des folgenden Fehlers: " + caught.toString());
			
		}

		@Override
		public void onSuccess(Person result) {
			orgEinheit.clear();
//		Listbox2.clear();
		person = result;
		Integer personID = result.getID();
		orgEinheit.addItem("Person: " + result.getVorname() + " " +
											result.getName() , personID.toString());
		
		if (person.getTeam_ID() !=null) {
			reportGenerator.getTeamByKey(result.getTeam_ID(), new getTeamReport());
		}else if (person.getUN_ID() != null){
			reportGenerator.getUnternehmenByKey(result.getUN_ID(), new getUnternehmenReport());
		}
			
		}
		
	}
	private class getTeamReport implements AsyncCallback<Team>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Das Team der Person konnte nicht geladen werden");		
		}

		@Override
		public void onSuccess(Team result) {
			
			Integer TeamID=result.getID();
			orgEinheit.addItem("Team: "+result.getName(),TeamID.toString());	
			team=result;
			if(person.getUN_ID()!=null){
				
				reportGenerator.getUnternehmenByKey(person.getUN_ID(), new getUnternehmenReport());
			}
			
		}
}
	private class getUnternehmenReport implements AsyncCallback<Unternehmen>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Unternehmen result) {
			Integer UnternehmenID=result.getID();
			orgEinheit.addItem("Unternehmen: "+result.getName(),UnternehmenID.toString());
			unternehmen=result;
			}
			
		
	}
}