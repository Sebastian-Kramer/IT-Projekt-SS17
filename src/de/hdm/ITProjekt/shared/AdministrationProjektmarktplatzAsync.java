package de.hdm.ITProjekt.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

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

	void updateProjektmarktplatz(Projektmarktplatz p, AsyncCallback<Void> callback);

	void addProjektmarktplatz(String bez, AsyncCallback<Projektmarktplatz> callback);

	void findByBez(String bez, AsyncCallback<Projektmarktplatz> callback);

	void deleteProjektmarktplatz(Projektmarktplatz p, AsyncCallback<Projektmarktplatz> callback);

	void findByProjektmarktplatz(int projektmarktplatzID, AsyncCallback<Vector<Projekt>> callback);

	void deleteProjekt(Projekt a, AsyncCallback<Void> callback);

	void getPersonbyID(int id, AsyncCallback<Person> callback);

	void getAllProjekte(AsyncCallback<Vector<Projekt>> callback);

	void findByProjektmarktplatz(Projektmarktplatz projektmarktplatz, AsyncCallback<Vector<Projekt>> callback);

	void getTeamByID(int id, AsyncCallback<Team> callback);

	void GetUnByID(int id, AsyncCallback<Unternehmen> callback);

	//void getProjektmaktplaetzeOf(Person p, AsyncCallback<Vector<Projektmarktplatz>> callback);


//	void addProjektmarktplatz(Projektmarktplatz p2, AsyncCallback<Projektmarktplatz> callback);
	
	

	

}
