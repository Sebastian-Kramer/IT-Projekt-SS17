package de.hdm.ITProjekt.shared;

import java.sql.Date;
import java.util.Vector;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.SingleSelectionModel;
import de.hdm.ITProjekt.shared.bo.Ausschreibung;
import de.hdm.ITProjekt.shared.bo.Beteiligung;
import de.hdm.ITProjekt.shared.bo.Bewerbung;
import de.hdm.ITProjekt.shared.bo.Bewertung;
import de.hdm.ITProjekt.shared.bo.Eigenschaft;
import de.hdm.ITProjekt.shared.bo.Organisationseinheit;
import de.hdm.ITProjekt.shared.bo.Partnerprofil;
import de.hdm.ITProjekt.shared.bo.Person;
import de.hdm.ITProjekt.shared.bo.Projekt;
import de.hdm.ITProjekt.shared.bo.Projektmarktplatz;
import de.hdm.ITProjekt.shared.bo.Team;
import de.hdm.ITProjekt.shared.bo.Unternehmen;

public interface AdministrationProjektmarktplatzAsync {

	void init(AsyncCallback<Void> callback);

	void createProjektmarktplatz(String bezeichnung, AsyncCallback<Projektmarktplatz> callback);

	void save(Projektmarktplatz p, AsyncCallback<Projektmarktplatz>callback);
	
	void getProjektmarktplatzAll(AsyncCallback<Vector<Projektmarktplatz>> callback);
	
	void getAllEigenschaftenbyPartnerprofilID (int id, AsyncCallback<Vector<Eigenschaft>> callback);

	void getProjektmarktplatzById(int ID, AsyncCallback<Projektmarktplatz> callback);

//	void deleteProjektmarktplatz(Projektmarktplatz p, AsyncCallback<Projektmarktplatz> callback);

	void updateProjektmarktplatz(Projektmarktplatz p, AsyncCallback<Projektmarktplatz> callback);

	void addProjektmarktplatz(String bez, AsyncCallback<Projektmarktplatz> callback);

	void findByBez(String bez, AsyncCallback<Projektmarktplatz> callback);

	void deleteProjektmarktplatz(Projektmarktplatz p, AsyncCallback<Projektmarktplatz> callback);

//	void findByProjektmarktplatz(int projektmarktplatzID, AsyncCallback<Vector<Projekt>> callback);

	void deleteProjekt(Projekt a, AsyncCallback<Projekt> callback);

	void getPersonbyID(int id, AsyncCallback<Person> callback);
	
	void getPartnerprofilfromPerson(int id, AsyncCallback<Eigenschaft> callback);

	void getAllProjekte(AsyncCallback<Vector<Projekt>> callback);
	
	void getAllBewertungen(AsyncCallback<Vector<Bewertung>> callback);

	void getAllProjekteByTeilnahme(Person p, AsyncCallback<Vector<Projekt>> callback);

	void findByProjektmarktplatz(Projektmarktplatz projektmarktplatz, AsyncCallback<Vector<Projekt>> callback);

	void createProjekt(java.util.Date date, java.util.Date date2, String name, String beschreibung, int person_ID,
				 AsyncCallback<Projekt> callback);


	void getAllBewerbungen(AsyncCallback<Vector<Bewerbung>> callback);
	
	// Methodenköpfe von AusschreibungMapper

	void findByKey(int id, AsyncCallback<Ausschreibung> callback);

	void getAlLAuscchreibungenBy(int projektId, AsyncCallback<Vector<Ausschreibung>> callback);

	void getAll(AsyncCallback<Vector<Ausschreibung>> callback);

//	void addAusschreibung(String text, String bezeichnung, java.util.Date date, AsyncCallback<Ausschreibung> callback);

	void deleteAusschreibung(Ausschreibung a, AsyncCallback<Void> callback);

	void update(Ausschreibung c, AsyncCallback<Ausschreibung> callback);

	void deleteBewerbung(Bewerbung a, AsyncCallback<Void> callback);
	
	void findBewerbungByAusschreibungId(int id, AsyncCallback<Vector<Bewerbung>> callback);
		

	//void getProjektmaktplaetzeOf(Person p, AsyncCallback<Vector<Projektmarktplatz>> callback);

	void addProjekt(Projekt pmp, AsyncCallback<Projekt> callback);

	void getMarktplatzByPerson(Person p, AsyncCallback<Vector<Projektmarktplatz>> callback);

	void getUnByID(int id, AsyncCallback<Unternehmen> callback);

	void insert(Bewerbung a, AsyncCallback<Bewerbung> callback);
	
	void insert(Bewertung a, AsyncCallback<Bewertung> callback);
	
	void insertWithoutBeteil(Bewertung a, AsyncCallback<Bewertung> callback);
	
	void insert(Beteiligung b, AsyncCallback<Beteiligung> callback);

	

	// Methoden für Organisationseinheit
	
	// void insert(Organisationseinheit o, AsyncCallback<Organisationseinheit> callback);

	// Methoden für Person
	
//	void createPerson(String email, String vorname, String nachname, String anrede, String strasse, int hausnr, int plz,
//			String ort, int partnerprofilId, Integer teamId, Integer unternehmenId, AsyncCallback<Person> callback);

	void getAllPerson(AsyncCallback<Vector<Person>> callback);

	void savePerson(Person p, AsyncCallback<Void> callback);
	
	void updatePerson(Person p, AsyncCallback<Person> callback);

	void createPerson(String email, String vorname, String nachname, String anrede, String strasse, int hausnr,
			int plz, String ort, int partnerprofilId, Integer teamId, Integer unternehmenId,
			AsyncCallback<Person> callback);

	// Methoden für Partnerprofil
	
	void createPartnerprofil(AsyncCallback<Partnerprofil> callback);

	void findByProjekt(Projekt projekt, AsyncCallback<Vector<Ausschreibung>> callback);

	void deleteTeilnahme(Person p, int projektmarktplatzid, AsyncCallback<Void> callback);

	void addAusschreibung(Ausschreibung a, AsyncCallback<Ausschreibung> callback);


//	void getAllEigenschaftenByOrgaID(int id, AsyncCallback<Vector<Eigenschaft>> callback);

	// Methoden für Team

	void deleteTeam(Team team, AsyncCallback<Void> callback);
	
	void getTeamByID(Integer id, AsyncCallback<Team> callback);

	void createTeam(String name, int plz, int hausnr, String ort, String strasse, int UN_ID, int Partnerprofil_ID,
			AsyncCallback<Team> callback);

	void updateTeam(Team team, AsyncCallback<Team> callback);
	
	void deletePartnerprofil(Partnerprofil p, AsyncCallback<Void> callback);

	void getPartnerprofilOfOrganisationseinheit(Organisationseinheit o, AsyncCallback<Partnerprofil> callback);


	// Unternehmen Methoden
	
	void insertUnternehmen(String text, int plz, int hausnr, String ort, String strasse, int partnerprofilID,
			AsyncCallback<Unternehmen> callback);

	void findByPerson(Person person, AsyncCallback<Vector<Bewerbung>> callback);

	void getProjektByID(int id, AsyncCallback<Projekt> callback);


	void deleteUnternehmen(Unternehmen u, AsyncCallback<Void> callback);

	void deletePerson(Person p, AsyncCallback<Void> callback);

	void updateProjekt(Projekt c, AsyncCallback<Projekt> callback);

	void updateBewerbung(Bewerbung c, AsyncCallback<Bewerbung> callback);

	void getBewertungByBewerbung(Bewerbung bewerbung, AsyncCallback<Vector<Bewertung>> callback);

	void getBewertungByBewerbung(int bewerbungId, AsyncCallback<Vector<Bewertung>> callback);

	void updateBewertung(Bewertung bewe, AsyncCallback<Bewertung> callback);

	void deleteBewertung(Bewertung bew, AsyncCallback<Void> callback);




	

	

}
