package de.hdm.ITProjekt.shared;
import java.util.ArrayList;
import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.ITProjekt.shared.bo.*;


@RemoteServiceRelativePath("AdministrationProjektmarktplatz")
public interface AdministrationProjektmarktplatz extends RemoteService {
	
	public void init() throws IllegalArgumentException; //Initialisierung des Objekts, muss nach Instantiierung aufgerufen werden
	
	public Projektmarktplatz createProjektmarktplatz(String bez)
		throws IllegalArgumentException;
	
	public void save(Projektmarktplatz p) throws IllegalArgumentException; //speichern eines Projektmarktplatz-Objekts in der Datenbank
	
	public Projektmarktplatz getProjektmarktplatzById (int ID) throws IllegalArgumentException; //Anzeigen eines bekannten Projektmarktplatz
	
	public Vector<Projektmarktplatz> getProjektmarktplatzAll() throws IllegalArgumentException; //Anzeigen aller Projektmarktplätze
	
	public void deleteProjektmarktplatz(Projektmarktplatz p) throws IllegalArgumentException; //Löschen eines Projektmarktplatzes
	
	public void updateProjektmarktplatz(Projektmarktplatz p) throws IllegalArgumentException; //Editieren eines Projektmarktplatzes
	
	
	
	
	
	

}
