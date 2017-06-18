package de.hdm.ITProjekt.shared;

import java.util.Vector;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.ITProjekt.shared.bo.Ausschreibung;
import de.hdm.ITProjekt.shared.bo.Bewerbung;
import de.hdm.ITProjekt.shared.bo.Organisationseinheit;
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

	void getProjektmarktplatzById(int ID, AsyncCallback<Projektmarktplatz> callback);

//	void deleteProjektmarktplatz(Projektmarktplatz p, AsyncCallback<Projektmarktplatz> callback);

	void updateProjektmarktplatz(Projektmarktplatz p, AsyncCallback<Projektmarktplatz> callback);

	void addProjektmarktplatz(String bez, AsyncCallback<Projektmarktplatz> callback);

	void findByBez(String bez, AsyncCallback<Projektmarktplatz> callback);

	void deleteProjektmarktplatz(Projektmarktplatz p, AsyncCallback<Projektmarktplatz> callback);

//	void findByProjektmarktplatz(int projektmarktplatzID, AsyncCallback<Vector<Projekt>> callback);

	void deleteProjekt(Projekt a, AsyncCallback<Projekt> callback);

	void getPersonbyID(int id, AsyncCallback<Person> callback);

	void getAllProjekte(AsyncCallback<Vector<Projekt>> callback);

	void findByProjektmarktplatz(Projektmarktplatz projektmarktplatz, AsyncCallback<Vector<Projekt>> callback);

	

	void getTeamByID(int id, AsyncCallback<Team> callback);

	void getAllBewerbungen(AsyncCallback<Vector<Bewerbung>> callback);
	
	// Methodenköpfe von AusschreibungMapper

	void findByKey(int id, AsyncCallback<Ausschreibung> callback);

	void getAlLAuscchreibungenBy(int projektId, AsyncCallback<Vector<Ausschreibung>> callback);

	void getAll(AsyncCallback<Vector<Ausschreibung>> callback);

	void addAusschreibung(Ausschreibung a, AsyncCallback<Ausschreibung> callback);

	void deleteAusschreibung(Ausschreibung a, AsyncCallback<Void> callback);

	void update(Ausschreibung c, AsyncCallback<Ausschreibung> callback);

	void deleteBewerbung(Bewerbung a, AsyncCallback<Void> callback);


	//void getProjektmaktplaetzeOf(Person p, AsyncCallback<Vector<Projektmarktplatz>> callback);

	void addProjekt(Projekt pmp, AsyncCallback<Projekt> callback);

	void getMarktplatzByPerson(Person p, AsyncCallback<Vector<Projektmarktplatz>> callback);

	void getUnByID(int id, AsyncCallback<Unternehmen> callback);

	void insert(Bewerbung a, AsyncCallback<Bewerbung> callback);

	

	// Methoden für Organisationseinheit
	
	// void insert(Organisationseinheit o, AsyncCallback<Organisationseinheit> callback);

	// Methoden für Person
	
//	void createPerson(String email, String vorname, String nachname, String anrede, String strasse, int hausnr, int plz,
//			String ort, int partnerprofilId, Integer teamId, Integer unternehmenId, AsyncCallback<Person> callback);

	void getAllPerson(AsyncCallback<Vector<Person>> callback);


	void savePerson(Person p, AsyncCallback<Void> callback);

	void createPerson(Person p, AsyncCallback<Person> callback);

	

	

	

}
