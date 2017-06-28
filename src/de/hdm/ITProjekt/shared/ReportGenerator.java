package de.hdm.ITProjekt.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

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

@RemoteServiceRelativePath("reportgenerator")
public interface ReportGenerator extends RemoteService {

	public void init() throws IllegalArgumentException;


	AllAusschreibungenByPartnerprofilReport createAllAusschreibungenByPartnerprofilReport (Organisationseinheit o) throws IllegalArgumentException;

	public AllAusschreibungenByPartnerprofilReport getAusschreibungByMatchingPartnerprofil(Organisationseinheit o) throws IllegalArgumentException;
	
	public abstract AllAusschreibungenReport createAllAusschreibungenReport() throws IllegalArgumentException;
	
	public abstract AllBeteiligungenToProjectReport createAllBeteiligungenToProjectReport (int id) throws IllegalArgumentException;
	
	public abstract AllBewerbungenByAusschreibungReport createAllBewerbungenByAusschreibungReport (Organisationseinheit o) throws IllegalArgumentException;
	
	public abstract AllBewerbungenByOrganisationseinheitReport createAllBewerbungenByPersonReport (int id) throws IllegalArgumentException;
	
	public abstract AllBewerbungenWithAusschreibungenReport createAllBewerbungenWithAusschreibungenReport(Organisationseinheit o) throws IllegalArgumentException;
		
	public abstract AllBewerbungenToOneAusschreibungReport createAllBewerbungenToOneAusschreibungReport(Organisationseinheit o) throws IllegalArgumentException;

	public abstract FanInFanOutReport createFanInFanOutReport() throws IllegalArgumentException;
	
	public abstract FanIn createFanInAnalyse () throws IllegalArgumentException;
	
	public abstract FanOut createFanOutAnalyse() throws IllegalArgumentException;
	
	ProjektverflechtungReport createProjektverflechtungenReport(int id) throws IllegalArgumentException;
	
	public Person findPersonByKey(int id) throws IllegalArgumentException;
	
	public Team findTeamByKey(int id) throws IllegalArgumentException;
	
	public Unternehmen findUnternehmenByKey (int id) throws IllegalArgumentException;

	public Vector<Organisationseinheit> getBewerberByAusschreibungen(Organisationseinheit o) throws IllegalArgumentException;

	public Vector<Person> getAllPersonen() throws IllegalArgumentException;
	
	public Team getTeamByKey (int teamID) throws IllegalArgumentException;
	
	public Unternehmen getUnternehmenByKey (int unternehmenID) throws IllegalArgumentException;
}

