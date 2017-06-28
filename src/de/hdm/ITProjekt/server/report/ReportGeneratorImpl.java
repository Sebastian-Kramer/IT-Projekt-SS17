package de.hdm.ITProjekt.server.report;

import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.ITProjekt.server.AdministrationProjektmarktplatzImpl;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatz;
import de.hdm.ITProjekt.shared.ReportGenerator;
import de.hdm.ITProjekt.shared.bo.Organisationseinheit;
import de.hdm.ITProjekt.shared.bo.Person;
import de.hdm.ITProjekt.shared.bo.Team;
import de.hdm.ITProjekt.shared.bo.Unternehmen;
import de.hdm.ITProjekt.shared.report.AllAusschreibungenByPartnerprofilReport;
import de.hdm.ITProjekt.shared.report.AllAusschreibungenReport;
import de.hdm.ITProjekt.shared.report.AllBeteiligungenToProjectReport;
import de.hdm.ITProjekt.shared.report.AllBewerbungenByAusschreibungReport;
import de.hdm.ITProjekt.shared.report.AllBewerbungenByOrganisationseinheitReport;
import de.hdm.ITProjekt.shared.report.AllBewerbungenToOneAusschreibungReport;
import de.hdm.ITProjekt.shared.report.AllBewerbungenWithAusschreibungenReport;
import de.hdm.ITProjekt.shared.report.FanIn;
import de.hdm.ITProjekt.shared.report.FanInFanOutReport;
import de.hdm.ITProjekt.shared.report.FanOut;
import de.hdm.ITProjekt.shared.report.ProjektverflechtungReport;

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
		
		
	
	@Override
	public AllAusschreibungenByPartnerprofilReport createAllAusschreibungenByPartnerprofilReport(Organisationseinheit o)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AllAusschreibungenByPartnerprofilReport getAusschreibungByMatchingPartnerprofil(Organisationseinheit o)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AllAusschreibungenReport createAllAusschreibungenReport() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AllBeteiligungenToProjectReport createAllBeteiligungenToProjectReport(int id)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AllBewerbungenByAusschreibungReport createAllBewerbungenByAusschreibungReport(Organisationseinheit o)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public AllBewerbungenWithAusschreibungenReport createAllBewerbungenWithAusschreibungenReport(Organisationseinheit o)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AllBewerbungenToOneAusschreibungReport createAllBewerbungenToOneAusschreibungReport(Organisationseinheit o)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FanInFanOutReport createFanInFanOutReport() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FanIn createFanInAnalyse() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FanOut createFanOutAnalyse() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProjektverflechtungReport createProjektverflechtungenReport(int id) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person findPersonByKey(int id) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return this.adminService.getPersonbyID(id);
	}

	@Override
	public Team findTeamByKey(int id) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return this.adminService.getTeamByID(id);
	}

	@Override
	public Unternehmen findUnternehmenByKey(int id) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<Organisationseinheit> getBewerberByAusschreibungen(Organisationseinheit o)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AllBewerbungenByOrganisationseinheitReport createAllBewerbungenByPersonReport(int id) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Team getTeamByKey(int teamID) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return adminService.getTeamByID(teamID);
	}

	@Override
	public Unternehmen getUnternehmenByKey(int unternehmenID) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return adminService.getUnByID(unternehmenID);
	}



	
}
