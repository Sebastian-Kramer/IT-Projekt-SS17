package de.hdm.ITProjekt.server.report;

import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

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

public class ReportGeneratorImpl extends RemoteServiceServlet implements ReportGenerator{

	public ReportGeneratorImpl() throws IllegalArgumentException{	
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
	public AllBewerbungenByOrganisationseinheitReport createAllBewerbungenByOrganisationseinheitReport(int id)
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
		return null;
	}

	@Override
	public Team findTeamByKey(int id) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
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
	public Vector<Person> getAllPersonen() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	
}
