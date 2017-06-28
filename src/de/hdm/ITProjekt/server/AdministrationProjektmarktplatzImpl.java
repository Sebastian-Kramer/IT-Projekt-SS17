package de.hdm.ITProjekt.server;


import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

//import org.mortbay.log.Log;

import de.hdm.ITProjekt.server.db.*;
import de.hdm.ITProjekt.shared.*;
import de.hdm.ITProjekt.shared.bo.*;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class AdministrationProjektmarktplatzImpl extends RemoteServiceServlet
			implements AdministrationProjektmarktplatz{
	
	private static final long serialVersionUID = 1L;	
	private ProjektmarktplatzMapper pmpMapper = null; //Referenz auf den ProjektmarktplatzMapper
	private ProjektMapper pMapper = null;
	private PersonMapper personMapper = null; 
	private TeamMapper tMapper = null;
	private UnternehmenMapper unMapper = null;
	private BewerbungMapper bewMapper = null;
	private BewertungMapper bewertMapper = null;
	private BeteiligungMapper beteilMapper = null;
	private AusschreibungMapper aMapper = null;
	private TeilnahmeMapper tnMapper = null;
	private OrganisationseinheitMapper orgMapper = null;
	private PartnerprofilMapper partnerprofilMapper = null;
	private EigenschaftMapper eigenschaftsMapper = null;
	

	
	public void init() {
		this.pmpMapper = ProjektmarktplatzMapper.pmpMapper(); //Initialisierung der Mapper
		this.personMapper = PersonMapper.perMapper();
		this.pMapper = ProjektMapper.pMapper();
		this.bewMapper = BewerbungMapper.bewMapper();
		this.aMapper = AusschreibungMapper.aMapper();
		this.tnMapper = TeilnahmeMapper.tnMapper();
		this.orgMapper = OrganisationseinheitMapper.orgMapper();
		this.tMapper = TeamMapper.tMapper();
		this.unMapper = UnternehmenMapper.unMapper();
		this.partnerprofilMapper = PartnerprofilMapper.ppMapper();
		this.eigenschaftsMapper = EigenschaftMapper.eMapper();
		this.bewertMapper = BewertungMapper.beweMapper();
		this.beteilMapper = BeteiligungMapper.bMapper();
		
	}
	/*
	   * ***************************************************************************
	   * ABSCHNITT, Anfang: Methoden für Projektmarktplatz-Objekte
	   * ***************************************************************************
	   */
	@Override
	public Projektmarktplatz createProjektmarktplatz(String bez) {
//		Log.info("start");
		return null;
//		Projektmarktplatz p = new Projektmarktplatz();
//		p.setBez(bez);
//		
//		p.setId(1); //Setzen einer vorl�ufigen ID
//		
//		return this.pmpMapper.addMarktplatz(p);   //Projektmarktplatz-Onjekt in Datenbank speichern
		
		
	}

	@Override
	public Projektmarktplatz save(Projektmarktplatz p){ //Speichern/anpassen eines Objekts in der Datenbank
		return this.pmpMapper.updateMarktplatz(p);
		
	}

	@Override
	public Projektmarktplatz getProjektmarktplatzById(int ID){
		
		return null;
	}

	@Override
	public Vector<Projektmarktplatz> getProjektmarktplatzAll(){ //Anzeigen aller Projektmarktplatz-Objekte
		return this.pmpMapper.getAll();
		
	}

//	@Override
//	public void deleteProjektmarktplatz(Projektmarktplatz p){
//		this.pmpMapper.deleteMarktplatz(p);
//		
//	}

	@Override
	public Projektmarktplatz addProjektmarktplatz(String bez){
		ProjektmarktplatzMapper pmp1 = ProjektmarktplatzMapper.pmpMapper();
		Projektmarktplatz p1 = new Projektmarktplatz(bez);
		p1.setBez(p1.getBez());		
		pmp1.addMarktplatz(p1);
		return p1;
	}
	public Projektmarktplatz findByBez(String bez){
		return this.pmpMapper.findByBez(bez);
	}

//	@Override
//	public Projektmarktplatz deleteProjektmarktplatz(Projektmarktplatz p)  {
//		// TODO Auto-generated method stub
//		// Wenn nicht alle Fremdschl�ssel gel�scht sind kann Proejktmarktplatz nicht gel�scht werden. Hier muss deleteprojekt auch aufgerufen werden.
//		
//		Vector<Projekt> projekt = this.getProjekteOf(p);
//		
//		if(projekt != null){
//			for(Projekt pr : projekt){
//				this.deleteProjekt(pr);
//			}
//		}
//		
//		return this.pmpMapper.deleteMarktplatz(p);
//	}

	@Override
	public Projektmarktplatz updateProjektmarktplatz(Projektmarktplatz p) {
		return this.pmpMapper.updateMarktplatz(p);
		
	}
	/*
	 * Wird noch von Sebi bearbeitet, bitte nicht weiter machen
	 */
//	@Override
//	public Vector<Projektmarktplatz> getProjektmaktplaetzeOf(Person p) {
//		Vector <Projektmarktplatz> result = new Vector<>();
//		
//		if(p != null && this.pmpMapper != null)
//		return null;
//	}	
	
	public Vector<Projektmarktplatz> getMarktplatzByPerson (Person p){
		
		Vector<Projektmarktplatz> result = new Vector<>();
		
		if(p != null && this.pmpMapper != null){
			
			Vector <Projektmarktplatz> pmp = this.tnMapper.findRelatedProjektMarktplaetze(p);
			
			if(pmp != null){
				result.addAll(pmp);
			}
		}
		return result;
	}
	
	/*
	   * ***************************************************************************
	   * ABSCHNITT, Ende: Methoden für Projektmarktplatz-Objekte
	   * ***************************************************************************
	   */
	
	/*
	   * ***************************************************************************
	   * ABSCHNITT, Anfang: Methoden für Projekt-Objekte
	   * ***************************************************************************
	   */
	
	
	
	
	public Vector<Projekt> getProjekteOf(Projektmarktplatz p){
		return this.pMapper.findByProjektmarktplatz(p.getID());
	}
	
	@Override
	public Vector<Projekt> findByProjektmarktplatz(Projektmarktplatz p) {
		// TODO Auto-generated method stub
		
		
		return this.pMapper.findByProjektmarktplatz(p);
	}
	
	//Alle Projekte die zu einem Projektmarktplatz gehören werden in einem Vector ausgegeben
//	@Override
//	public Vector<Projekt> findByProjektmarktplatz(int projektmarktplatzID) {
//		return this.pMapper.findByProjektmarktplatz(projektmarktplatzID);
//	}
	
//	public void deleteProjekt(Projekt pr){
//		
//		Vector<Ausschreibung> ausschreibung = this.findByProjekt(pr);
//		
//		
//		if(ausschreibung != null){
//			for(Ausschreibung a : ausschreibung){
//				this.deleteAusschreibung(a);
//			}
//			
//		}
//		
//		return this.pMapper.deleteProjekt(pr);
//	}
	
	@Override
	public Vector<Projekt> getAllProjekte() {
		return this.pMapper.getAllProjekte();
	}
	
	@Override
	public Projekt addProjekt(Projekt pmp) {
		return this.pMapper.addProjekt(pmp);
		
	}
	@Override
	public Projekt createProjekt(Date date, Date date2, String name, String beschreibung, int person_ID) {


		Projekt p = new Projekt();
		p.setStartdatum(date);
		p.setEnddatum(date2);
		p.setBeschreibung(beschreibung);
		p.setName(name);
		p.setProjektleiter_ID(person_ID);
		p.setProjektmarktplatz_ID(1);


		
		return this.pMapper.addProjekt(p);
	}
	
	@Override
	public Vector<Projekt> getAllProjekteByTeilnahme(Person p) throws IllegalArgumentException {
		return this.tnMapper.findTeilnahmeProjekte(p);
	}
	
	/*
	   * ***************************************************************************
	   * ABSCHNITT, Ende: Methoden für Projekt-Objekte
	   * ***************************************************************************
	   */

	/*
	   * ***************************************************************************
	   * ABSCHNITT, Anfang: Methoden für Person-Objekte
	   * ***************************************************************************
	   */
	//Rückgabe einer Person aus der Datenbank
	@Override
	public Person getPersonbyID(int id) {
		
		return this.personMapper.findByKey(id);
	}
	
	@Override
	public Person updatePerson(Person p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return this.personMapper.updatePerson(p);
	}
	
	@Override
	public Person createPerson(String email, String vorname, String nachname, String anrede, String strasse,
			int hausnr, int plz, String ort, int partnerprofilID, Integer teamID, Integer unternehmenID)
			throws IllegalArgumentException {	
		Person p = new Person();
		p.setID(0);
		p.setEmail(email);
		p.setVorname(vorname);
		p.setName(nachname);
		p.setAnrede(anrede);
		p.setStrasse(strasse);
		p.setHausnummer(hausnr);
		p.setOrt(ort);
		p.setPlz(plz);
		p.setPartnerprofil_ID(partnerprofilID);
		p.setTeam_ID(teamID);
		p.setUN_ID(unternehmenID);
		return this.personMapper.createPerson(p);
	}
//	@Override
//	public Person createPerson(String email, String vorname, String nachname, String anrede, String strasse,
//			int hausnr, int plz, String ort, int partnerprofilId, Integer teamId, Integer unternehmenId) {
//		Person p = new Person();
//		p.setID(0);
//		p.setEmail(email);
//		p.setVorname(vorname);
//		p.setName(nachname);
//		p.setAnrede(anrede);
//		p.setStraße(strasse);
//		p.setHausnummer(hausnr);
//		p.setPlz(plz);
//		p.setOrt(ort);
//		p.setPartnerprofil_ID(1);
//		p.setTeam_ID(1);
//		p.setUN_ID(1);		
//		return this.personMapper.createPerson(p);
//	}
	
	@Override
	public void deletePerson(Person p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		this.personMapper.deletePerson(p);;
	}
	
	@Override
	public Vector<Person> getAllPerson() {

		return this.personMapper.getAll();
		}
	@Override
	public void savePerson(Person p) throws IllegalArgumentException {
		this.personMapper.updatePerson(p);
		
	}
	
	
	/*
	   * ***************************************************************************
	   * ABSCHNITT, Ende: Methoden für Person-Objekte
	   * ***************************************************************************
	   */

	
	/*
	 * ***************************************************************************
	 * ABSCHNITT, Anfang: Methoden für Ausschreibung
	 * ***************************************************************************
	 */
	@Override
	public Ausschreibung findByKey(int id) {
		// TODO Auto-generated method stub
		return this.aMapper.findByKey(id);
	}
	@Override
	public Vector<Ausschreibung> getAlLAuscchreibungenBy(int projektId) {
		// TODO Auto-generated method stub
		return this.aMapper.getAlLAuscchreibungenBy(projektId);
	}
	@Override
	public Vector<Ausschreibung> getAll() {
		// TODO Auto-generated method stub
		return this.aMapper.getAll();
	}
	
	
	public Ausschreibung addAusschreibung(String text, String bezeichnung, Date date) {
		Ausschreibung aus = new Ausschreibung();
		aus.setAusschreibungstext(text);
		aus.setBezeichnung(bezeichnung);
		aus.setDatum(date);
		aus.setProjekt_ID(1);
		aus.setOrga_ID(1);
		
		return this.aMapper.addAusschreibung(aus);
	}
	
	


	@Override
	public void deleteAusschreibung(Ausschreibung a) {
		this.aMapper.deleteAusschreibung(a);

	}
	@Override
	public Ausschreibung update(Ausschreibung c) {
		// TODO Auto-generated method stub
		return this.aMapper.update(c);
	}
	
	@Override
	public Vector<Ausschreibung> findByProjekt(Projekt projekt) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return this.aMapper.findByProjekt(projekt);
	}
	
	
	
	/*
	   * ***************************************************************************
	   * ABSCHNITT, Ende: Methoden für Ausschreibung-Objekte
	   * ***************************************************************************
	   */
	

	/*
	   * ***************************************************************************
	   * ABSCHNITT, Anfang: Methoden für Team-Objekte
	   * ***************************************************************************
	   */

	//Rückgabe eines Team-Objects anhand der übergebenen ID
	@Override
	public Team getTeamByID(int id) {
		return this.tMapper.findByKey(id);
	}
	@Override
	public Team createTeam(String name, int plz, int hausnr, String ort, String strasse, int UN_ID,
			int Partnerprofil_ID) throws IllegalArgumentException {
		Team team = new Team();
		team.setID(0);
		team.setUN_ID(UN_ID);
		team.setHausnummer(hausnr);
		team.setName(name);
		team.setOrt(ort);
		team.setPartnerprofil_ID(Partnerprofil_ID);
		team.setPlz(plz);
		team.setStrasse(strasse);
		return this.tMapper.insert(team);
		
	}
	
	/*
	 * ***************************************************************************
	   * ABSCHNITT, Anfang: Methoden für Unternehmen-Objekte
	   * ***************************************************************************
	   */
	//Rückgabe eines Unternehmens-Objects anhand der übergebenen ID

	@Override
	public Unternehmen getUnByID(int id) {
		return this.unMapper.findByKey(id);
	}
	
	
	
	/*
	 * ***************************************************************************
	   * ABSCHNITT, Anfang: Methoden fürBewerbung-Objekte
	   * ***************************************************************************
	   */
	@Override
	public Vector<Bewerbung> getAllBewerbungen() {
		// TODO Auto-generated method stub
		return this.bewMapper.getAll();
	}

	@Override
	public void deleteBewerbung(Bewerbung a) {
		// TODO Auto-generated method stub
		this.bewMapper.delete(a);
	}
	@Override
	public Bewerbung insert(Bewerbung a) {
		return this.bewMapper.insert(a);
	}
	
	
	
	
	/*
	 * ***************************************************************************
	   * ABSCHNITT, Ende: Methoden fürBewerbung-Objekte
	   * ***************************************************************************
	   */
	/*
	 * ***************************************************************************
	   * ABSCHNITT, Anfang: Methoden für Partnerprofil
	   * ***************************************************************************
	   */
	@Override
	public Partnerprofil createPartnerprofil() throws IllegalArgumentException {
		Partnerprofil p = new Partnerprofil();
		p.setID(1);
		
		return partnerprofilMapper.insert(p);

		}
	
	
	@Override
	public void deletePartnerprofil(Partnerprofil p) throws IllegalArgumentException {
		this.partnerprofilMapper.delete(p);
	}
	
	@Override
	public Partnerprofil getPartnerprofilOfOrganisationseinheit(Organisationseinheit o) throws IllegalArgumentException {
		
		if (o instanceof Person){
			return this.partnerprofilMapper.findByKey(this.personMapper.findByKey(o.getID()).getPartnerprofil_ID());
		} else if (o instanceof Unternehmen){
			return this.partnerprofilMapper.findByKey(this.unMapper.findByKey(o.getID()).getPartnerprofil_ID());
		} else if (o instanceof Team){
			return this.partnerprofilMapper.findByKey(this.tMapper.findByKey(o.getID()).getPartnerprofil_ID());
		} 		
		return null;
	}

	/*
	 * ***************************************************************************
	   * ABSCHNITT, Ende: Methoden für Partnerprofil
	   * ***************************************************************************
	   */
	
	/*
	 * ***************************************************************************
	   * ABSCHNITT, Anfang: Methoden für Organisationseinheit
	   * ***************************************************************************
	   */
//	@Override
//	public Organisationseinheit insert(Organisationseinheit o) {
//		// TODO Auto-generated method stub
//		return this.orgMapper.insert(o);
//	}
	/*
	 * ***************************************************************************
	   * ABSCHNITT, Ende: Methoden fürOrganisationseinheit
	   * ***************************************************************************
	   */
	
	@Override
	public void deleteTeilnahme(Person p, int projektmarktplatzid) throws IllegalArgumentException {
		this.tnMapper.entfernenTeilnahme(p, projektmarktplatzid);		
	}

	@Override
	public Ausschreibung addAusschreibung(Ausschreibung a) throws IllegalArgumentException {
		
		return this.aMapper.addAusschreibung(a);
}

	@Override
	public Eigenschaft createEigenschaft(Eigenschaft e) throws IllegalArgumentException {
		return this.eigenschaftsMapper.insert(e);
	}
	@Override
	public Eigenschaft getPartnerprofilfromPerson(int id) throws IllegalArgumentException {
		
		return this.eigenschaftsMapper.findByKey(id);

	}

	
	@Override
	public Vector<Eigenschaft> getAllEigenschaftofPerson(Partnerprofil p) throws IllegalArgumentException {
		return this.eigenschaftsMapper.findByPartnerprofil(p);
	}
	
	@Override
	public Vector<Eigenschaft> getAllEigenschaftenbyPartnerprofilID(int id) {
	
		Vector <Eigenschaft> result = new Vector<Eigenschaft>();
		
		if(this.eigenschaftsMapper != null ){
			
			Vector<Eigenschaft> eigenschaft = this.eigenschaftsMapper.getEigenschaftbyID(id);
			
			if(eigenschaft != null){
				result.addAll(eigenschaft);
			}
		}
		return result;
	}
//	@Override
//	public Team findByKeyOfPerson(int id) throws IllegalArgumentException {
//		return this.tMapper.findByKey(id);
//	}
	@Override
	public Unternehmen insertUnternehmen(String text, int plz, int hausnr, String ort, String strasse,
			int partnerprofilID) throws IllegalArgumentException {
		Unternehmen u = new Unternehmen();
		u.setID(0);
		u.setHausnummer(hausnr);
		u.setName(text);
		u.setOrt(ort);
		u.setPartnerprofil_ID(partnerprofilID);
		u.setPlz(plz);
		u.setStrasse(strasse);
		
		return this.unMapper.createUnternehmen(u);
	}
	@Override
	public void deleteUnternehmen(Unternehmen u) throws IllegalArgumentException {
		this.unMapper.deleteUnternehmen(u);
	}
	@Override
	public Unternehmen updateUnternehmen(Unternehmen u) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return this.unMapper.updateUnternehmen(u);
	}	
	@Override
	public void deleteTeam(Team team) throws IllegalArgumentException {
		this.tMapper.delete(team);
		
	}
	@Override
	public Team updateTeam(Team team) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return this.tMapper.update(team);
	}


	
	
	public Vector<Bewerbung> findByPerson(Person person) throws IllegalArgumentException {
		return this.bewMapper.findByPerson(person);
	}
	
	public Projekt getProjektByID(int id) throws IllegalArgumentException {
		
		return this.pMapper.getProjektByID(id);
	}
	@Override
	public Vector<Bewerbung> findBewerbungByAusschreibungId(int id) throws IllegalArgumentException{

		return this.bewMapper.findByAusschreibung(id);
	}
	@Override
	public Bewertung insert(Bewertung a) {
		
		return this.bewertMapper.insert(a);
	}

	@Override
	public Beteiligung insert(Beteiligung b) {
		
		return this.beteilMapper.insert(b);
	}
	@Override
	public Bewertung insertWithoutBeteil(Bewertung a) {
		// TODO Auto-generated method stub
		return this.bewertMapper.insertWithoutBeteil(a);
	}
	@Override
	public Projekt updateProjekt(Projekt c) throws IllegalArgumentException {
		return this.pMapper.update(c);
	}
	@Override
	public Bewerbung updateBewerbung(Bewerbung c) throws IllegalArgumentException {
		
		return this.bewMapper.update(c);
	}
	@Override
	public Vector<Bewertung> getBewertungByBewerbung(Bewerbung bewerbung) throws IllegalArgumentException {
		
		return this.bewertMapper.getBewertungByBewerbung(bewerbung);
	}
	@Override
	public Vector<Bewertung> getBewertungByBewerbung(int bewerbungId) throws IllegalArgumentException {
		return this.bewertMapper.getBewertungByBewerbung(bewerbungId);
	}
	@Override
	public Bewertung updateBewertung(Bewertung bewe) throws IllegalArgumentException {
		
		return this.bewertMapper.update(bewe);
	}
	@Override
	public void deleteBewertung(Bewertung bew) throws IllegalArgumentException {
		this.bewertMapper.delete(bew);
	}
		
		
		

	public Vector<Bewertung> getAllBewertungen() {
		// TODO Auto-generated method stub
		return this.bewertMapper.getAll();

	}

	public void deleteEigenschaft(Eigenschaft e) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		this.eigenschaftsMapper.deleteEigenschaft(e);
	}
	@Override
	public Eigenschaft updateEigenschaft(Eigenschaft e) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return this.eigenschaftsMapper.update(e);
	}
	@Override
	public void deleteProjekt(Projekt a) throws IllegalArgumentException {
		this.pMapper.deleteProjekt(a);
		
	}
	@Override
	public void deleteProjektmarktplatz(Projektmarktplatz p) throws IllegalArgumentException {
		this.pmpMapper.deleteMarktplatz(p);
		
	}
	@Override
	public Vector<Projekt> getAllProjekteByProjektleiter(int personId) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return this.pMapper.getAllProjekteByProjektleiter(personId);
	}
	
	public Vector<Beteiligung> getBeteiligungByProjekt(int projektid) throws IllegalArgumentException {
		
		return this.beteilMapper.getBeteiligungByProjekt(projektid);
	}
	@Override
	public void delete(Beteiligung a) throws IllegalArgumentException {
		this.beteilMapper.delete(a);
		
	}
	@Override
	public Beteiligung updateBeteiligung(Beteiligung c) throws IllegalArgumentException {
		
		return this.beteilMapper.update(c);
	}
	@Override
	public Vector<Beteiligung> getAllBeteiligungen() throws IllegalArgumentException {
		
		return this.beteilMapper.getAll();
	}
	@Override
	public Vector<Projekt> getProjektByOrgaID(Integer id) throws IllegalArgumentException {
		
		return this.pMapper.getProjektById(id);
	}
	@Override
	public Vector<Person> getPersonByID(Integer id) throws IllegalArgumentException {
		
		return this.personMapper.getPersonByID(id);
	}
	@Override

	public Bewerbung setBewerbungsStatus(Bewerbung b) throws IllegalArgumentException {
		return this.bewMapper.updateBewerbungsstatus(b);
	}
	@Override
	public Person getPersonFromBewerbung(Integer id) {
		return this.personMapper.getPersonbyOrgaID(id);
	}

	public Vector<Partnerprofil> getAllPartnerprofile() throws IllegalArgumentException {
		
		return this.partnerprofilMapper.getAllPartnerprofile();
	}
	@Override
	public Vector<Ausschreibung> getAllAusschreibungen() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return this.aMapper.getAll();
	}
	@Override
	public Partnerprofil addPartnerprofil(Partnerprofil pp1) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return this.partnerprofilMapper.insert(pp1);
	}
	@Override
	public Vector<Bewerbung> findByOrgaID(int Orga_ID) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return this.bewMapper.findByPerson(Orga_ID);

	}
	@Override

	public Organisationseinheit getOrgaEinheitFromBewerbung(Integer id) throws IllegalArgumentException {
		return this.orgMapper.findByID(id);
	}
	@Override
	public Vector<Eigenschaft> getAllEigenschaftenFromOrga(Integer id) throws IllegalArgumentException {
		return this.eigenschaftsMapper.getEigenschaftbyId(id);
	}


	public Partnerprofil findPartnerprofilByID(int id) throws IllegalArgumentException {
		
		return this.partnerprofilMapper.findByKey(id);
	}

	
}
