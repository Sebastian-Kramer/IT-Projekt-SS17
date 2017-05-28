package de.hdm.ITProjekt.server;

import java.util.ArrayList;
import java.util.Vector;

import org.mortbay.log.Log;

import de.hdm.ITProjekt.server.db.*;
import de.hdm.ITProjekt.shared.*;
import de.hdm.ITProjekt.shared.bo.*;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class AdministrationProjektmarktplatzImpl extends RemoteServiceServlet
			implements AdministrationProjektmarktplatz{
	
	private static final long serialVersionUID = 1L;	
	private ProjektmarktplatzMapper pmpMapper = null; //Referenz auf den ProjektmarktplatzMapper
	
	public void init() {
		this.pmpMapper = ProjektmarktplatzMapper.pmpMapper(); //Initialisierung der Mapper
	}

	@Override
	public Projektmarktplatz createProjektmarktplatz(String bez) {
//		Log.info("start");
		return null;
//		Projektmarktplatz p = new Projektmarktplatz();
//		p.setBez(bez);
//		
//		p.setId(1); //Setzen einer vorläufigen ID
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

	@Override
	public void deleteProjektmarktplatz(Projektmarktplatz p){
		this.pmpMapper.deleteMarktplatz(p);
		
	}

	@Override
	public void updateProjektmarktplatz(Projektmarktplatz p){
		// TODO Auto-generated method stub
		
	}

	@Override
	public Projektmarktplatz addProjektmarktplatz(String bez){
		ProjektmarktplatzMapper pmp1 = ProjektmarktplatzMapper.pmpMapper();
		Projektmarktplatz p1 = new Projektmarktplatz(bez);
		p1.setBez(p1.getBez());		
		pmp1.addMarktplatz(p1);
		return p1;
	}
	
	
	

}
