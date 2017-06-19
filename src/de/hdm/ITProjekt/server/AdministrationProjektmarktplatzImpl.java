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
	

	
	private ArrayList<String> projektmarktplaetze = new ArrayList<String>(); 
	private static final long serialVersionUID = 1L;	
	private ProjektmarktplatzMapper pmpMapper = null; //Referenz auf den ProjektmarktplatzMapper
	private ProjektMapper pMapper = null;
	private PersonMapper personMapper = null; 
	private TeamMapper tMapper = null;
	private UnternehmenMapper unMapper = null;
	private BewerbungMapper bewMapper = null;
	private AusschreibungMapper aMapper = null;
	private TeilnahmeMapper tnMapper = null;
	private OrganisationseinheitMapper orgMapper = null;
	private PartnerprofilMapper partnerprofilMapper = null;
	

	
	public void init() {
		this.pmpMapper = ProjektmarktplatzMapper.pmpMapper(); //Initialisierung der Mapper
		this.personMapper = PersonMapper.perMapper();
		this.pMapper = ProjektMapper.pMapper();
		this.bewMapper = BewerbungMapper.bewMapper();
		this.aMapper = AusschreibungMapper.aMapper();
		this.tnMapper = TeilnahmeMapper.tnMapper();
//		this.orgMapper = OrganisationseinheitMapper.orgMapper();
		this.tMapper = TeamMapper.tMapper();
		this.unMapper = UnternehmenMapper.unMapper();
		this.partnerprofilMapper = PartnerprofilMapper.ppMapper();
		
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

	@Override
	public Projektmarktplatz deleteProjektmarktplatz(Projektmarktplatz p)  {
		// TODO Auto-generated method stub
		// Wenn nicht alle Fremdschl�ssel gel�scht sind kann Proejktmarktplatz nicht gel�scht werden. Hier muss deleteprojekt auch aufgerufen werden.
		
		Vector<Projekt> projekt = this.getProjekteOf(p);
		
		if(projekt != null){
			for(Projekt pr : projekt){
				this.deleteProjekt(pr);
			}
		}
		
		return this.pmpMapper.deleteMarktplatz(p);
	}

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
	
	public Projekt deleteProjekt(Projekt pr){
		return this.pMapper.deleteProjekt(pr);
	}
	
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
	
	@Override
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

	
	
	
	
	}
