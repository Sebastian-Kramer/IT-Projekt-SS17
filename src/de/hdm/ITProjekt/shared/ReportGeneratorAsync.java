package de.hdm.ITProjekt.shared;
import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;
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



public interface ReportGeneratorAsync {

	void init(AsyncCallback<Void> callback);

	void createAllAusschreibungenByPartnerprofilReport(Organisationseinheit o,
			AsyncCallback<AllAusschreibungenByPartnerprofilReport> callback);

	void createAllAusschreibungenReport(AsyncCallback<AllAusschreibungenReport> callback);

	void createAllBeteiligungenToProjectReport(int id, AsyncCallback<AllBeteiligungenToProjectReport> callback);

	void createAllBewerbungenByAusschreibungReport(Organisationseinheit o,
			AsyncCallback<AllBewerbungenByAusschreibungReport> callback);

	void createAllBewerbungenByPersonReport(int id,
			AsyncCallback<AllBewerbungenByOrganisationseinheitReport> callback);

	void createAllBewerbungenToOneAusschreibungReport(Organisationseinheit o,
			AsyncCallback<AllBewerbungenToOneAusschreibungReport> callback);

	void createAllBewerbungenWithAusschreibungenReport(Organisationseinheit o,
			AsyncCallback<AllBewerbungenWithAusschreibungenReport> callback);

	void createFanInAnalyse(AsyncCallback<FanIn> callback);

	void createFanInFanOutReport(AsyncCallback<FanInFanOutReport> callback);

	void createFanOutAnalyse(AsyncCallback<FanOut> callback);

	void createProjektverflechtungReport(int id, AsyncCallback<ProjektverflechtungReport> callback);

	void findPersonByKey(int id, AsyncCallback<Person> callback);

	void findTeamByKey(int id, AsyncCallback<Team> callback);

	void findUnternehmenByKey(int id, AsyncCallback<Unternehmen> callback);

	void getAllPersonen(AsyncCallback<Vector<Person>> callback);
	

	void getAusschreibungByMatchingPartnerprofil(Organisationseinheit o,
			AsyncCallback<AllAusschreibungenByPartnerprofilReport> callback);

	void getBewerberByAusschreibungen(Organisationseinheit o, AsyncCallback<Vector<Organisationseinheit>> callback);

	void getTeamByKey(int teamID, AsyncCallback<Team> callback);

	void getUnternehmenByKey(int unternehmenID, AsyncCallback<Unternehmen> callback);


}
