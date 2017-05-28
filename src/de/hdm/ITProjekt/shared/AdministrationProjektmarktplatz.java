package de.hdm.ITProjekt.shared;
import java.util.ArrayList;
import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.ITProjekt.shared.bo.*;


@RemoteServiceRelativePath("projektmarktplatz")
public interface AdministrationProjektmarktplatz extends RemoteService {
	
	public void init(); //Initialisierung des Objekts, muss nach Instantiierung aufgerufen werden
	
	public Projektmarktplatz createProjektmarktplatz(String bez);
	
	public Projektmarktplatz save(Projektmarktplatz p); //speichern eines Projektmarktplatz-Objekts in der Datenbank
	
	public Projektmarktplatz getProjektmarktplatzById (int ID); //Anzeigen eines bekannten Projektmarktplatz
	
	Vector<Projektmarktplatz> getProjektmarktplatzAll();
	
	public void deleteProjektmarktplatz(Projektmarktplatz p); //Löschen eines Projektmarktplatzes
	
	public void updateProjektmarktplatz(Projektmarktplatz p); //Editieren eines Projektmarktplatzes
	
	public Projektmarktplatz addProjektmarktplatz(String bez);
	
	
	
	

}
