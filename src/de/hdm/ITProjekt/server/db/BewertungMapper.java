package de.hdm.ITProjekt.server.db;

import de.hdm.ITProjekt.shared.bo.Bewertung;
import de.hdm.ITProjekt.shared.bo.Beteiligung;
import de.hdm.ITProjekt.shared.bo.Bewerbung;
import de.hdm.ITProjekt.server.db.BeteiligungMapper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.text.SimpleDateFormat;

/*
 * Die Klasse BewertungMapper bildet Bewertungs-Objekte auf einer relationalen Datenbank ab.
 * Mit Hilfe von verschiedenen Methoden können die jeweilgen Objekte aus der Datenbank geholt, geschrieben 
 * oder aktualisiert werden.
 * Die Besonderheit ist, dass Objekte in DB-Strukturen und umgekehrt umgewandelt werden können
 */

public class BewertungMapper {

	/*
	 * SimpleDateFormat wird benötigt um das korrekte Format 
	 * eines Datum zu lesen oder zu schreiben
	 */
	
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	/*
	 * Speicherung der einzigen Instanz dieser Mapperklasse
	 */
	
	private static BewertungMapper beweMapper = null;
	
	/*
	 * Konstruktor wird geschützt, damit Objekte der Klasse BewertungMapper
	 *  nicht außerhalb der Vererbungshirarchie der Klasse erstellt werden können
	 */
	
	protected BewertungMapper(){
		
	}
	
	/*
	 * Singelton Eigenschaft der Mapperklasse, nur eine Instanz kann Existieren
	 * @return bewMapper
	 */
	
	public static BewertungMapper beweMapper(){
		if(beweMapper == null){
			beweMapper = new BewertungMapper();
		}
		return beweMapper;
	}
	
	/*
	 * Bewertung wird anhand der übergebenen, eindeutigen ID zurückgegeben
	 * @return Bewertung entsprechend der übergebenen ID
	 * @param ID Primärschlüssel ID der Tabelle Bewertung
	 */
	
	public Bewertung findByKey(int id){
		
		Connection con = DBConnection.connection();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT ID, stellungnahme, bewertung, Beteiligungs_ID, Bewerbungs_ID FROM Bewertung "
          + "WHERE ID=" + id);
			
			if(rs.next()){
				Bewertung p = new Bewertung();
				p.setID(rs.getInt("ID"));
				p.setStellungnahme(rs.getString("stellungnahme"));
				p.setBewertung(rs.getDouble("bewertung"));
				p.setBeteiligungs_ID(rs.getInt("Beteiligungs_ID"));
				p.setBewerbungs_ID(rs.getInt("Bewerbungs_ID"));
				return p;
			}
		}
		catch(SQLException e2){
			e2.printStackTrace();
			return null;
		}
		return null;	
	}

	/*
	 * Auslesen aller Bewertungen die in der DB gespeichert sind
	 * @return Vector mit allen gefundenen Bewertungen
	 */
	
	public Vector<Bewertung> getAll(){
	
	 Connection con = DBConnection.connection();	 
	
	 Vector<Bewertung> result = new Vector<Bewertung>();
	 
	  try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("SELECT ID, stellungnahme, bewertung, Beteiligungs_ID, Bewerbungs_ID FROM Bewertung ");
	  
	  while (rs.next()) {
		  	
		  	Bewertung p = new Bewertung();
			p.setID(rs.getInt("ID"));
			p.setStellungnahme(rs.getString("stellungnahme"));
			p.setBewertung(rs.getDouble("bewertung"));
			p.setBeteiligungs_ID(rs.getInt("Beteiligungs_ID"));
			p.setBewerbungs_ID(rs.getInt("Bewerbungs_ID"));
		  
		  result.addElement(p);
	  	}
	  }
	    catch (SQLException e2) {
	        e2.printStackTrace();
	      }
	  		return result;
	}

	/*
	 * Alle Bewertungen werden anhand der übergebenen BewerbungsID
	 * in einem Vector zurückgegeben.
	 * @return alle Elemente die im Vector gespeichert wurden
	 * @param ID Bewerbung_ID der Tabelle Bewertung
	 */
	
	public Vector<Bewertung> getBewertungByBewerbung(int bewerbungID){
	
		Connection con = DBConnection.connection();	 
		Vector<Bewertung> result = new Vector<Bewertung>();
	 	
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT ID, stellungnahme, bewertung, Beteiligungs_ID, Bewerbungs_ID FROM bewertung " 
				 						+ "WHERE Bewerbungs_ID=" + bewerbungID);
			while(rs.next()){
				 Bewertung b = new Bewertung();
				 
				 b.setID(rs.getInt("ID"));
				 b.setStellungnahme(rs.getString("stellungnahme"));
				 b.setBewertung(rs.getDouble("bewertung"));
				 b.setBeteiligungs_ID(rs.getInt("Beteiligungs_ID"));
				 b.setBewerbungs_ID(rs.getInt("Bewerbungs_ID"));
				 
				 result.add(b);			 
		 	}
	 	}
		 catch (SQLException e) {
				e.printStackTrace();
			}	 
	 			return result;
	}
	
	public Vector<Bewertung> getBewertungByBeteiligung(int beteiligungID){
		
		Connection con = DBConnection.connection();	 
		Vector<Bewertung> result = new Vector<Bewertung>();
	 	
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT ID, stellungnahme, bewertung, Beteiligungs_ID, Bewerbungs_ID FROM bewertung " 
				 						+ "WHERE Beteiligungs_ID=" + beteiligungID);
			while(rs.next()){
				 Bewertung b = new Bewertung();
				 
				 b.setID(rs.getInt("ID"));
				 b.setStellungnahme(rs.getString("stellungnahme"));
				 b.setBewertung(rs.getDouble("bewertung"));
				 b.setBeteiligungs_ID(rs.getInt("Beteiligungs_ID"));
				 b.setBewerbungs_ID(rs.getInt("Bewerbungs_ID"));
				 
				 result.add(b);			 
		 	}
	 	}
		 catch (SQLException e) {
				e.printStackTrace();
			}	 
	 			return result;
	}

	/*
	 * Hilfsmethode die alle Bewertungen einer Bewerbung mit Hilfe eines
	 * Bewerbung-Object findet.
	 * Methode getBewertungByBewerbung(int bewerbungID) wird aufgerufen
	 * @param Bewerbung
	 */
	
	public Vector<Bewertung> getBewertungByBewerbung(Bewerbung bewerbung){
	
		return getBewertungByBewerbung(bewerbung.getID());
	}

	/*
	 * Wird eine Bewertung neu angelegt wird diese mit der insert-Methode in die jeweilige Tabelle 
	 * der Datenbank geschrieben.
	 * Dazu wird die bisher höchste ID gesucht und mit 1 addiert.
	 * @return Gespeichertes Bewertung Objekt.
	 * @param neuen Bewertungsobjekt
	 */

	public Bewertung insert(Bewertung bew){
	
		Connection con = DBConnection.connection();
				
		try {
		      Statement stmt = con.createStatement();
		      
		      ResultSet rs = stmt.executeQuery("SELECT MAX(ID) AS maxid "
		              + "FROM Bewertung ");
		      
		
		      if(rs.next()){
		    	  		    	  			    	  	
		    	  	bew.setID(rs.getInt("maxid") + 1);   	  	
		    	  	stmt = con.createStatement();
		    	  	stmt.executeUpdate("INSERT INTO Bewertung (ID, stellungnahme, bewertung, Beteiligungs_ID, Bewerbungs_ID)" 
		    	  			+ "VALUES (" + bew.getID() + ", " + "'" + bew.getStellungnahme() + "'" 
		    	  			+ ", " + bew.getBewertung() + ", " + bew.getBeteiligungs_ID() + ", " + bew.getBewerbungs_ID()
		    	  			+")"); 			
		      	}		    	  
	      }
				
		catch(SQLException e2){	
			e2.printStackTrace();
		}		
		return bew;	
	}
	
	/*
	 * Wird eine Bewertung neu angeleg wird diese mit der insert-Methode in die jeweilige Tabelle 
	 * der Datenbank geschrieben. In dieser Meethode wird die Bewertung ohne ein Beteiligung in die DB
	 * geschrieben
	 * Dazu wird die bisher höchste ID gesucht und mit 1 addiert.
	 * @return Gespeichertes Bewertung Objekt.
	 * @param neuen Bewertungsobjekt
	 */
	
	public Bewertung insertWithoutBeteil(Bewertung bew) {
		
		Connection con = DBConnection.connection();
		
		try {
	      
			Statement stmt = con.createStatement();
	      
		      ResultSet rs = stmt.executeQuery("SELECT MAX(ID) AS maxid "
		              + "FROM Bewertung ");
		      		
		      if(rs.next()){		    	  		    	  			    	  	
		    	  	bew.setID(rs.getInt("maxid") + 1);   	  	
		    	  	stmt = con.createStatement();
		    	  	stmt.executeUpdate("INSERT INTO Bewertung (ID, stellungnahme, bewertung, Bewerbungs_ID)" 
		    	  			+ "VALUES (" + bew.getID() + ", " + "'" + bew.getStellungnahme() + "'" 
		    	  			+ ", " + bew.getBewertung() + ", " + bew.getBewerbungs_ID()
		    	  			+")"); 			
		      	}		    	  
	      }
		
		catch(SQLException e2){
		
			e2.printStackTrace();	
		}		
		return bew;	
	}

	/*
	 * Bewertung wird aus der Datenbank gelöscht
	 * @param zu löschendes Bewertungsobjekt
	 */
	
	public void delete(Bewertung bew){
		
		Connection con = DBConnection.connection();
	
		try {
		      Statement stmt = con.createStatement();
	
		      stmt.executeUpdate("DELETE FROM Bewertung " 
		    		  			+ "WHERE Bewertung.ID = " + bew.getID());
	
			}
		
		catch (SQLException e2) {
				e2.printStackTrace();
			}
	}
	
	/*
	 * Eine vorhandene Bewertung aus der Datenbank wird aktualisiert
	 * @param zu aktualisierendes Bewertungsobjekt
	 */
	
	public Bewertung update(Bewertung bewe) {
	   
		Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();
	      
	      if(bewe.getBewerbungs_ID()==null){
	    	  stmt.executeUpdate("UPDATE Bewertung " + "SET stellungnahme='"
	    	          + bewe.getStellungnahme() +  "', bewertung= '" + bewe.getBewertung()
	    	          + "'" + "', Bewerbungs_ID = NULL, Beteiligungs_ID = NULL" + "WHERE Bewertung.ID=" + bewe.getID()); 
	      }else{
	      stmt.executeUpdate("UPDATE Bewertung " + "SET stellungnahme='"
	          + bewe.getStellungnahme() + "'" +   "SET bewertung=" + bewe.getBewertung());	     
	      }	    
	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	    }
    
	    return bewe; 
	}

}

