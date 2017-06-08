package de.hdm.ITProjekt.server;

import java.util.ArrayList;
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
	private PersonMapper prMApper = null; 
	private TeamMapper tMapper = null;
	private UnternehmenMapper unMapper = null;
	private BewerbungMapper bewMapper = null;
//	private  AusschreibungMapper aMapper = null; 

	
	public void init() {
		this.pmpMapper = ProjektmarktplatzMapper.pmpMapper(); //Initialisierung der Mapper
		this.pMapper = ProjektMapper.pMapper();
		this.bewMapper = BewerbungMapper.bewMapper();
//		this.aMapper = AusschreibungMapper.aMapper();
		
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
	public void updateProjektmarktplatz(Projektmarktplatz p) {
		// TODO Auto-generated method stub
		
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
		return this.pMapper.findByProjektmarktplatz(p);
	}
	
	@Override
	public Vector<Projekt> findByProjektmarktplatz(Projektmarktplatz projektmarktplatz) {
		// TODO Auto-generated method stub
		return this.pMapper.findByProjektmarktplatz(projektmarktplatz);
	}
	
	//Alle Projekte die zu einem Projektmarktplatz gehören werden in einem Vector ausgegeben
	@Override
	public Vector<Projekt> findByProjektmarktplatz(int projektmarktplatzID) {
		return this.pMapper.findByProjektmarktplatz(projektmarktplatzID);
	}
	
	public void deleteProjekt(Projekt pr){
		this.pMapper.deleteProjekt(pr);
	}
	
	@Override
	public Vector<Projekt> getAllProjekte() {
		return this.pMapper.getAllProjekte();
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
		
		return this.prMApper.findByKey(id);
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
//	@Override
//	public Ausschreibung findByKey(int id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	@Override
//	public Vector<Ausschreibung> getAlLAuscchreibungenBy(int projektId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	@Override
//	public Vector<Ausschreibung> getAll() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	@Override
//	public Ausschreibung addAusschreibung(Object String) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	@Override
//	public Ausschreibung deleteAusschreibung(Ausschreibung a) {
//		return null;
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public Ausschreibung update(Ausschreibung c) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	
	/*
	   * ***************************************************************************
	   * ABSCHNITT, Ende: Methoden für Person-Objekte
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
	public Unternehmen GetUnByID(int id) {
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
	
	
	
	
	
	/*
	 * ***************************************************************************
	   * ABSCHNITT, Ende: Methoden fürBewerbung-Objekte
	   * ***************************************************************************
	   */
	
	
	
	
	
	
	
	
}
