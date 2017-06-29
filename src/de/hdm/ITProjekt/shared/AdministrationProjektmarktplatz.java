package de.hdm.ITProjekt.shared;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.ITProjekt.shared.bo.*;

/*
 * Das Interface AdministrationProjektmarktplatz ist eine synchrone Schnitte für eine RPC-fähig Klasse 
 * zur Verwaltung von Projektmarktplätzen.
 * Diese Schnittestelle wird benötigt um über Projektverflechtung zwischen verschiedenen Funktionen 
 * Aufschluss zu geben. Das Wissen, wie einzelene Daten-Objekte koexistieren, wird in dieser gekapselt.
 * Außerdem gilt dieser Klasse der Übersicht, welche funktionen alles von unserem System realisiert wurden
 * und ausgeführt werden können
 */

@RemoteServiceRelativePath("projektmarktplatz")
public interface AdministrationProjektmarktplatz extends RemoteService {
	
	public void init(); //Initialisierung des Objekts, muss nach Instantiierung aufgerufen werden
	
	public Projektmarktplatz createProjektmarktplatz(String bez)throws IllegalArgumentException;
	
	public Projektmarktplatz save(Projektmarktplatz p)throws IllegalArgumentException; 
	
	public Projektmarktplatz getProjektmarktplatzById (int ID)throws IllegalArgumentException; 
	
	public Vector<Projektmarktplatz> getProjektmarktplatzAll()throws IllegalArgumentException; 
	
	public Projektmarktplatz updateProjektmarktplatz(Projektmarktplatz p)throws IllegalArgumentException; 
	
	public Projektmarktplatz addProjektmarktplatz(String bez)throws IllegalArgumentException; 
	
	public Projektmarktplatz findByBez(String bez)throws IllegalArgumentException;

	public void deleteProjektmarktplatz(Projektmarktplatz p) throws IllegalArgumentException;

	public Vector<Projektmarktplatz> getMarktplatzByPerson (Person p)throws IllegalArgumentException;
	
	public Vector<Projekt> findByProjektmarktplatz(Projektmarktplatz projektmarktplatz)throws IllegalArgumentException;
	
	public void deleteProjekt(Projekt a) throws IllegalArgumentException;
	
	public Projekt getProjektByID(int id) throws IllegalArgumentException;
	
	public Person getPersonbyID(int id)throws IllegalArgumentException;
	
	public Projekt updateProjekt(Projekt c)throws IllegalArgumentException;
	
	public void savePerson(Person p)throws IllegalArgumentException;
	
	public Vector<Projekt> getAllProjekte()throws IllegalArgumentException;
	
	public Vector<Projekt>getAllProjekteByProjektleiter(int personId) throws IllegalArgumentException;
	
	public Vector<Bewerbung> getAllBewerbungen()throws IllegalArgumentException;
	
	public void deleteBewerbung(Bewerbung a)throws IllegalArgumentException;
	
	public Vector<Bewerbung> findByPerson(Person person)throws IllegalArgumentException; 
	
	public Bewerbung updateBewerbung(Bewerbung c)throws IllegalArgumentException;
	
	public Ausschreibung findByKey(int id)throws IllegalArgumentException;
	
	public Vector<Ausschreibung> getAlLAuscchreibungenBy(int projektId)throws IllegalArgumentException;
	 
	public Vector<Ausschreibung> getAll()throws IllegalArgumentException;
	
	public Ausschreibung addAusschreibung(Ausschreibung a) throws IllegalArgumentException;
	
	public void deleteAusschreibung(Ausschreibung a)throws IllegalArgumentException;
	
	public Ausschreibung update(Ausschreibung c) throws IllegalArgumentException;
	
	public Vector<Ausschreibung> findByProjekt(Projekt projekt) throws IllegalArgumentException;
	
	public Vector<Ausschreibung> getAllAusschreibungen() throws IllegalArgumentException;

	public Projekt addProjekt(Projekt pmp) throws IllegalArgumentException;
	
	public Projekt createProjekt(java.util.Date date, java.util.Date date2, String name, String beschreibung, int person_ID) throws IllegalArgumentException;
	
	public Bewertung insert(Bewertung a) throws IllegalArgumentException;

	public Person createPerson(String email, String vorname, String nachname, String anrede, 
			String strasse, int hausnr, int plz, String ort, int partnerprofilId, Integer teamId, Integer unternehmenId) throws IllegalArgumentException;
	
	public Person updatePerson(Person p) throws IllegalArgumentException;
	
	public Vector<Person> getAllPerson() throws IllegalArgumentException;

	public void deletePerson(Person p) throws IllegalArgumentException;
	
	public Vector<Person> getPersonByID(Integer id) throws IllegalArgumentException;
	
	public Partnerprofil createPartnerprofil() throws IllegalArgumentException;
	
	public void deletePartnerprofil(Partnerprofil p) throws IllegalArgumentException;
	
	public Partnerprofil getPartnerprofilOfOrganisationseinheit (Organisationseinheit o) throws IllegalArgumentException;
	
	public Partnerprofil addPartnerprofil(Partnerprofil pp1) throws IllegalArgumentException;	
	
	public Vector<Projekt> getAllProjekteByTeilnahme(Person p) throws IllegalArgumentException;
	
	public void deleteTeilnahme(Person p, int projektmarktplatzid) throws IllegalArgumentException;
	
	public Vector<Eigenschaft> getAllEigenschaftofPerson(Partnerprofil p) throws IllegalArgumentException;
	
	public Eigenschaft createEigenschaft(Eigenschaft e) throws IllegalArgumentException;
	
	public Eigenschaft updateEigenschaft(Eigenschaft e) throws IllegalArgumentException;
	
	public void deleteEigenschaft(Eigenschaft e) throws IllegalArgumentException;
	
	public Eigenschaft getPartnerprofilfromPerson(int id) throws IllegalArgumentException;

	public Vector<Eigenschaft> getAllEigenschaftByPartnerprofilObjekt(Partnerprofil p) throws IllegalArgumentException;
	
	public Vector<Eigenschaft> getAllEigenschaftenbyPartnerprofilID(int id) throws IllegalArgumentException;
	
	public Team updateTeam(Team team) throws IllegalArgumentException;
	
	public Team createTeam(String name, int plz, int hausnr, String ort, String strasse, int UN_ID, int Partnerprofil_ID) throws IllegalArgumentException;

	public Team getTeamByID(int id) throws IllegalArgumentException;
	
	public void deleteTeam(Team team) throws IllegalArgumentException;
	
	public void deleteUnternehmen(Unternehmen u) throws IllegalArgumentException;
	
	public Unternehmen updateUnternehmen(Unternehmen u) throws IllegalArgumentException;
	
	public Unternehmen getUnByID(int id) throws IllegalArgumentException;
	
	public Unternehmen insertUnternehmen(String text, int plz, int hausnr, String ort, String strasse, int partnerprofilID) throws IllegalArgumentException;

	public Vector<Bewerbung> findBewerbungByAusschreibungId(int id) throws IllegalArgumentException;

	public Bewerbung insert(Bewerbung a) throws IllegalArgumentException;
	
	public Vector<Bewerbung> findByOrgaID (int Orga_ID) throws IllegalArgumentException;

	public Beteiligung insert(Beteiligung b) throws IllegalArgumentException;
	
	public Vector<Bewertung> getBewertungByBewerbung(Bewerbung bewerbung) throws IllegalArgumentException;
	
	public Vector<Bewertung> getBewertungByBewerbung(int bewerbungId) throws IllegalArgumentException;
	
	public Bewertung updateBewertung(Bewertung bewe) throws IllegalArgumentException;
	
	public void deleteBewertung(Bewertung bew) throws IllegalArgumentException;

	public Vector<Bewertung> getAllBewertungen() throws IllegalArgumentException;

	public Bewertung insertWithoutBeteil(Bewertung a) throws IllegalArgumentException;
	
	public Vector <Beteiligung> getBeteiligungByProjekt(int projektid) throws IllegalArgumentException;
	
	public void delete(Beteiligung a) throws IllegalArgumentException;
	
	public Beteiligung updateBeteiligung(Beteiligung c) throws IllegalArgumentException;
	
	public Vector<Beteiligung> getAllBeteiligungen() throws IllegalArgumentException;
	
	public Vector<Projekt> getProjektByOrgaID(Integer id) throws IllegalArgumentException;
	
	public Vector<Partnerprofil> getAllPartnerprofile() throws IllegalArgumentException;

	public Bewerbung setBewerbungsStatus(Bewerbung b) throws IllegalArgumentException;

	public Person getPersonFromBewerbung(Integer id) throws IllegalArgumentException;
	
	public Organisationseinheit getOrgaEinheitFromBewerbung(Integer id) throws IllegalArgumentException;
	
	public Vector<Eigenschaft> getAllEigenschaftenFromOrga(Integer id) throws IllegalArgumentException;
	
	public Partnerprofil findPartnerprofilByID(int id) throws IllegalArgumentException;
		
	public Partnerprofil getPartnerprofilByAusschreibung(Ausschreibung a) throws IllegalArgumentException;
	
	public Organisationseinheit getOrgaeinheitByID(int o) throws IllegalArgumentException;

	public Vector<Beteiligung> getBeteiligungByOrgaeinheit(Organisationseinheit o) throws IllegalArgumentException;
	
	public Ausschreibung getAusschreibungByID(int a) throws IllegalArgumentException;
	
	public Vector<Bewerbung> getBewerbungByOrgaeinheit(Organisationseinheit o) throws IllegalArgumentException;
	
	public Vector<Organisationseinheit> getAllOrganisationseinheiten()throws IllegalArgumentException;
	
	public Vector<Ausschreibung> getAusschreibungByOrgaeinheit(Organisationseinheit o)throws IllegalArgumentException;

	public Vector <Beteiligung> getBeteiligungByOrga(Integer orgaid) throws IllegalArgumentException;
	
	public void deleteTeamByID(Integer t) throws IllegalArgumentException;
	
	public void deleteUnternehmenByID(Integer u) throws IllegalArgumentException;
	
	public void deleteBewertungbyBeteiligung(int id) throws IllegalArgumentException;

	}
