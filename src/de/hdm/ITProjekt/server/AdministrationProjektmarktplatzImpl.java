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
	
	public void init() {
		this.pmpMapper = ProjektmarktplatzMapper.pmpMapper(); //Initialisierung der Mapper
		this.pMapper = ProjektMapper.pMapper();
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
	@Override
	public Vector<Projekt> findByProjektmarktplatz(Projektmarktplatz projektmarktplatz) {
		// TODO Auto-generated method stub
		return this.pMapper.findByProjektmarktplatz(projektmarktplatz);
	}
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

	
	
	
	
	
	
	
	
	
}
