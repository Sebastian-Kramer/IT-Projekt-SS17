package de.hdm.ITProjekt.server.report;

import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.ITProjekt.server.AdministrationProjektmarktplatzImpl;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatz;
import de.hdm.ITProjekt.shared.ReportGenerator;
import de.hdm.ITProjekt.shared.bo.Person;


@SuppressWarnings("serial")
public class ReportGeneratorImpl extends RemoteServiceServlet implements ReportGenerator{

	private AdministrationProjektmarktplatz adminService = null;
	
	public ReportGeneratorImpl() throws IllegalArgumentException {
	}
	
	public void init() throws IllegalArgumentException{
		
		
		AdministrationProjektmarktplatzImpl ap = new AdministrationProjektmarktplatzImpl();
		ap.init();
		this.adminService = ap;
	}
	
	protected AdministrationProjektmarktplatz getAdminservice(){
		return this.adminService;
	}
	
	public Vector<Person> getAllPersonen() throws IllegalArgumentException{		
		return adminService.getAllPerson();
	}
	
	
	
}
