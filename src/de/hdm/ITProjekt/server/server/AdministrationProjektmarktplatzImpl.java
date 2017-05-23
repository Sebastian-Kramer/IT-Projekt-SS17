package de.hdm.ITProjekt.server.server;

import java.util.ArrayList;
import java.util.Vector;

import de.hdm.ITProjekt.server.db.*;
import de.hdm.ITProjekt.shared.*;
import de.hdm.ITProjekt.shared.bo.*;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class AdministrationProjektmarktplatzImpl extends RemoteServiceServlet
			implements AdministrationProjektmarktplatz{
	
	private ProjektmarktplatzMapper pmpMapper = null; //Referenz auf den ProjektmarktplatzMapper
	
	public void init() throws IllegalArgumentException {
		this.pmpMapper = ProjektmarktplatzMapper.pmpMapper(); //Initialisierung der Mapper
	}

	@Override
	public Projektmarktplatz createProjektmarktplatz(String bez) throws IllegalArgumentException {
		Projektmarktplatz p = new Projektmarktplatz();
		p.setBez(bez);
		
		p.setId(1); //Setzen einer vorläufigen ID
		
		return this.pmpMapper.addMarktplatz(p);   //Projektmarktplatz-Onjekt in Datenbank speichern
		
		
	}

	@Override
	public void save(Projektmarktplatz p) throws IllegalArgumentException { //Speichern/anpassen eines Objekts in der Datenbank
		pmpMapper.updateMarktplatz(p);
		
	}

	@Override
	public Projektmarktplatz getProjektmarktplatzById(int ID) throws IllegalArgumentException {
		
		return null;
	}

	@Override
	public Vector<Projektmarktplatz> getProjektmarktplatzAll() throws IllegalArgumentException { //Anzeigen aller Projektmarktplatz-Objekte
		return this.pmpMapper.getAll();
	}

	@Override
	public void deleteProjektmarktplatz(Projektmarktplatz p) throws IllegalArgumentException {
		this.pmpMapper.deleteMarktplatz(p);
		
	}

	@Override
	public void updateProjektmarktplatz(Projektmarktplatz p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
