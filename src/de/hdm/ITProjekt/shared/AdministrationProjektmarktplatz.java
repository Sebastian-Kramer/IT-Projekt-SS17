package de.hdm.ITProjekt.shared;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.ITProjekt.shared.bo.*;


@RemoteServiceRelativePath("projektmarktplatz")
public interface AdministrationProjektmarktplatz extends RemoteService {
	
	public void init(); //Initialisierung des Objekts, muss nach Instantiierung aufgerufen werden
	
	public Projektmarktplatz createProjektmarktplatz(String bez)throws IllegalArgumentException;
	
	public Projektmarktplatz save(Projektmarktplatz p)throws IllegalArgumentException; //speichern eines Projektmarktplatz-Objekts in der Datenbank
	
	public Projektmarktplatz getProjektmarktplatzById (int ID)throws IllegalArgumentException; //Anzeigen eines bekannten Projektmarktplatz
	
	Vector<Projektmarktplatz> getProjektmarktplatzAll()throws IllegalArgumentException;
	
//	public void deleteProjektmarktplatz(Projektmarktplatz p); //L�schen eines Projektmarktplatzes
	
	Projektmarktplatz updateProjektmarktplatz(Projektmarktplatz p)throws IllegalArgumentException;
	
	public Projektmarktplatz addProjektmarktplatz(String bez)throws IllegalArgumentException;
	
	public Projektmarktplatz findByBez(String bez)throws IllegalArgumentException;

	public Projektmarktplatz deleteProjektmarktplatz(Projektmarktplatz p)throws IllegalArgumentException; // L�schen eines Projektmarktplatzes

	public Vector<Projektmarktplatz> getMarktplatzByPerson (Person p)throws IllegalArgumentException;
	
//	Projektmarktplatz addProjektmarktplatz(Projektmarktplatz p2);
	
	//public Vector <Projektmarktplatz> getProjektmaktplaetzeOf(Person p);
	
//	public Vector<Projekt> findByProjektmarktplatz (int projektmarktplatzID);
	
	public Vector<Projekt> findByProjektmarktplatz(Projektmarktplatz projektmarktplatz)throws IllegalArgumentException;
	
	public Projekt deleteProjekt(Projekt a)throws IllegalArgumentException;
	
	public Projekt getProjektByID(int id) throws IllegalArgumentException;
	
	public Person getPersonbyID(int id)throws IllegalArgumentException;
	
	void savePerson(Person p)throws IllegalArgumentException;
	
	Vector<Projekt> getAllProjekte()throws IllegalArgumentException;

	
	Vector<Bewerbung> getAllBewerbungen()throws IllegalArgumentException;
	
	public void deleteBewerbung(Bewerbung a)throws IllegalArgumentException;
	
	Vector<Bewerbung> findByPerson(Person person)throws IllegalArgumentException; 
	
	
	
		// Methoden von Ausschreibung
	
	public Ausschreibung findByKey(int id)throws IllegalArgumentException;
	
	public Vector<Ausschreibung> getAlLAuscchreibungenBy(int projektId)throws IllegalArgumentException;
	 
	public Vector<Ausschreibung> getAll()throws IllegalArgumentException;
	
	public Ausschreibung addAusschreibung(Ausschreibung a) throws IllegalArgumentException;
	
	
//	public Ausschreibung addAusschreibung(String text, String bezeichnung, java.util.Date date);
	
	public void deleteAusschreibung(Ausschreibung a)throws IllegalArgumentException;
	
	public Ausschreibung update(Ausschreibung c) throws IllegalArgumentException;
	
	public Vector<Ausschreibung> findByProjekt(Projekt projekt) throws IllegalArgumentException;

	
/*
 * Methoden von Projekten
 */
	public Projekt addProjekt(Projekt pmp) throws IllegalArgumentException;

	
	Projekt createProjekt(java.util.Date date, java.util.Date date2, String name, String beschreibung, int person_ID);
	
	/*
	 * Methoden von Bewerbung
	 */
	
	Bewertung insert(Bewertung a);

	
	// Methoden von Organisationseinheit
	
	// public Organisationseinheit insert ( Organisationseinheit o);

	//Methoden von Person
	
//	public Person createPerson(String email, String vorname, String nachname, String anrede, 
//			String strasse, int hausnr, int plz, String ort, int partnerprofilId, Integer teamId, Integer unternehmenId);
	public Person createPerson(String email, String vorname, String nachname, String anrede, 
			String strasse, int hausnr, int plz, String ort, int partnerprofilId, Integer teamId, Integer unternehmenId) throws IllegalArgumentException;
	
	public Person updatePerson(Person p) throws IllegalArgumentException;
	public Vector<Person> getAllPerson() ;

	
	//Methoden von Partnerprofil

	
	public Partnerprofil createPartnerprofil() throws IllegalArgumentException;

	
	
	// Methoden von Teilnahme
	
	public Vector<Projekt> getAllProjekteByTeilnahme(Person p) throws IllegalArgumentException;
	
	public void deleteTeilnahme(Person p, int projektmarktplatzid) throws IllegalArgumentException;

	Eigenschaft getPartnerprofilfromPerson(int id);

	public Vector<Eigenschaft> getAllEigenschaftenbyPartnerprofilID(int id);

//	public Vector<Eigenschaft> getAllEigenschaftenByOrgaID(int id);

 // Methoden von Team
	
	public Team createTeam(String name, int plz, int hausnr, String ort, String strasse, int UN_ID, int Partnerprofil_ID) throws IllegalArgumentException;

	public Team getTeamByID(int id) throws IllegalArgumentException;
	
	// Methoden von Unternehmen
	
	public Unternehmen getUnByID(int id) throws IllegalArgumentException;
	
	public Unternehmen insertUnternehmen(String text, int plz, int hausnr, String ort, String strasse, int partnerprofilID) throws IllegalArgumentException;

	public Vector<Bewerbung> findBewerbungByAusschreibungId(int id);

	Bewerbung insert(Bewerbung a);

	}
