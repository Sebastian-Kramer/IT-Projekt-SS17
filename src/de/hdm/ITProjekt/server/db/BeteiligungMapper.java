package de.hdm.ITProjekt.server.db;

import de.hdm.ITProjekt.shared.bo.Beteiligung;
import de.hdm.ITProjekt.shared.bo.Organisationseinheit;
import de.hdm.ITProjekt.server.db.DBConnection;
import java.sql.*;
import java.util.Vector;
import java.text.SimpleDateFormat;

/*
 * Die Klasse BeteiligungMapper bildet Beteiligungs-Objekte auf einer relationalen Datenbank ab.
 * Mit Hilfe von verschiedenen Methoden können die jeweilgen Objekte aus der Datenbank geholt, geschrieben 
 * oder aktualisiert werden.
 * Die Besonderheit ist, dass Objekte in DB-Strukturen und umgekehrt umgewandelt werden können
 */

public class BeteiligungMapper {
	
	/*
	 * SimpleDateFormat wird benötigt um das korrekte Format 
	 * eines Datum zu lesen oder zu schreiben
	 */
	
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	/*
	 * Speicherung der einzigen Instanz dieser Mapperklasse
	 */
	
	private static BeteiligungMapper bMapper = null;
	
	/*
	 * Konstruktor wird geschützt, damit Objekte der Klasse BeteiligungMapper
	 *  nicht außerhalb der Vererbungshirarchie der Klasse erstellt werden können
	 */
	
	protected BeteiligungMapper(){
		
	}
	
	/*
	 * Singelton Eigenschaft der Mapperklasse, nur eine Instanz kann Existieren
	 * @return bMapper
	 */
	
	public static BeteiligungMapper bMapper(){
		if(bMapper == null){
			bMapper = new BeteiligungMapper();
		}
		return bMapper;
	}
	
	/*
	 * Beteiligung wird anhand der übergebenen, eindeutigen ID zurückgegeben
	 * @return Beteiligung entsprechend der übergebenen ID
	 * @param ID Primärschlüssel ID der Tabelle Beteiligung
	 */
	
	public Beteiligung findByKey(int id){
		
		Connection con = DBConnection.connection();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT ID, umfang, startdatum, enddatum, Projekt_ID, Orga_ID FROM Beteiligung "
          + "WHERE ID=" + id + " ORDER BY ID");
			
			if(rs.next()){
				Beteiligung p = new Beteiligung();
				p.setID(rs.getInt("ID"));
				p.setUmfang(rs.getString("umfang"));
				p.setStartdatum(rs.getDate("startdatum"));
				p.setEnddatum(rs.getDate("enddatum"));
				p.setProjekt_ID(rs.getInt("Projekt_ID"));
				p.setOrga_ID(rs.getInt("Orga_ID"));
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
	 * Alle Beteiligungen werden anhand der übergebenen, Organisationseinheit
	 * in einem Vector zurückgegeben.
	 * @return alle Elemente die im Vector gespeichert wurden
	 * @param ID Orga_ID der Tabelle Beteiligung
	 */
	
	public Vector<Beteiligung> findByOrgaeinheit(Organisationseinheit o){
		
		Connection con = DBConnection.connection();
		Vector<Beteiligung> result = new Vector<Beteiligung>();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT ID, umfang, startdatum, enddatum, Projekt_ID, Orga_ID FROM Beteiligung "
          + "WHERE Orga_ID=" + o.getID());
			
			while(rs.next()){
				Beteiligung p = new Beteiligung();
				p.setID(rs.getInt("ID"));
				p.setUmfang(rs.getString("umfang"));
				p.setStartdatum(rs.getDate("startdatum"));
				p.setEnddatum(rs.getDate("enddatum"));
				p.setProjekt_ID(rs.getInt("Projekt_ID"));
				p.setOrga_ID(rs.getInt("Orga_ID"));
				
				result.addElement(p);
			}
		}
		catch(SQLException e2){
			e2.printStackTrace();
			return null;
		}
		return result;	
	}
	
	/*
	 * Auslesen aller Beteiligungen die in der DB gespeichert sind
	 * @return Vector mit allen gefundenen Beteiligungen
	 */
		
	public Vector<Beteiligung> getAll(){
		
		 Connection con = DBConnection.connection();	 	
		 Vector<Beteiligung> result = new Vector<Beteiligung>();
		 
		  try {
		      Statement stmt = con.createStatement();

		      ResultSet rs = stmt.executeQuery("SELECT ID, umfang, startdatum, enddatum, Projekt_ID, Orga_ID FROM Beteiligung");
		  
		  while (rs.next()) {
			  	Beteiligung p = new Beteiligung();
				p.setID(rs.getInt("ID"));
				p.setUmfang(rs.getString("umfang"));
				p.setStartdatum(rs.getDate("startdatum"));
				p.setEnddatum(rs.getDate("enddatum"));
				p.setProjekt_ID(rs.getInt("Projekt_ID"));
				p.setOrga_ID(rs.getInt("Orga_ID"));
			  
			  result.addElement(p);
		  }
		}
		    catch (SQLException e2) {
		        e2.printStackTrace();
		      }
		  return result;
	}
	
	/*
	 * Auslesen aller Beteiligungen eines Projekts mittels Projekt ID.
	 * @return Vector mit allen Beteiligungen zu einem Projekt.
	 * @param projektId
	 */
	
	public Vector <Beteiligung> getBeteiligungByProjekt(int projektid){
		 Connection con = DBConnection.connection();	 
			
		 Vector<Beteiligung> result = new Vector<Beteiligung>();
		 
		 try{
			 Statement stmt = con.createStatement();

		      ResultSet rs = stmt.executeQuery("SELECT ID, umfang, startdatum, enddatum, Projekt_ID, Orga_ID FROM Beteiligung WHERE Projekt_ID=" + projektid); 
		      
		      while(rs.next()){
		    	  Beteiligung p = new Beteiligung();
					p.setID(rs.getInt("ID"));
					p.setUmfang(rs.getString("umfang"));
					p.setStartdatum(rs.getDate("startdatum"));
					p.setEnddatum(rs.getDate("enddatum"));
					p.setProjekt_ID(rs.getInt("Projekt_ID"));
					p.setOrga_ID(rs.getInt("Orga_ID"));
				  
				  result.addElement(p);
		      }
		 }
		 catch (SQLException e2) {
		        e2.printStackTrace();
		 }
		 return result;
	}
	
	/*
	 * Alle Beteiligungen werden anhand der übergebenen, Organisationseinheit
	 * in einem Vector zurückgegeben.
	 * @return alle Elemente die im Vector gespeichert wurden
	 * @param ID Orga_ID der Tabelle Beteiligung
	 */
	
	public Vector <Beteiligung> getBeteiligungByOrga(Integer orgaid){
		 Connection con = DBConnection.connection();	 
			
		 Vector<Beteiligung> result = new Vector<Beteiligung>();
		 
		 try{
			 Statement stmt = con.createStatement();

		      ResultSet rs = stmt.executeQuery("SELECT ID, umfang, startdatum, enddatum, Projekt_ID, Orga_ID FROM Beteiligung WHERE Orga_ID=" + orgaid); 
		      
		      while(rs.next()){
		    	  Beteiligung p = new Beteiligung();
					p.setID(rs.getInt("ID"));
					p.setUmfang(rs.getString("umfang"));
					p.setStartdatum(rs.getDate("startdatum"));
					p.setEnddatum(rs.getDate("enddatum"));
					p.setProjekt_ID(rs.getInt("Projekt_ID"));
					p.setOrga_ID(rs.getInt("Orga_ID"));
				  
				  result.addElement(p);
		      }
		 }
		 catch (SQLException e2) {
		        e2.printStackTrace();
		 }
		 return result;
	}
	
	/*
	 * Wird eine Beteiligung neu angelegt wird diese mit der insert-Methode in die jeweilige Tabelle 
	 * der Datenbank geschrieben.
	 * Dazu wird die bisher höchste ID gesucht und mit 1 addiert.
	 * @return Gespeichertes Beteiligungs Objekt.
	 * @param neuen Beteiligungsobjekt
	 */
	
	public Beteiligung insert(Beteiligung a){
		
		Connection con = DBConnection.connection();
		
		try {
		      Statement stmt = con.createStatement();
		      
		      ResultSet rs = stmt.executeQuery("SELECT MAX(ID) AS maxid "
		              + "FROM Beteiligung ");
		      		
		      if(rs.next()){
		    	  
		    	  	a.setID(rs.getInt("maxid") + 1);
		    	  	stmt = con.createStatement();
		    	  	
		    		stmt.executeUpdate("INSERT INTO Beteiligung (ID, umfang, startdatum, enddatum, Projekt_ID, Orga_ID)" 
		    				+ "VALUES (" + a.getID() + ", " + "'" + a.getUmfang() + "'" 
		    				+ ", " + "'" + format.format(a.getStartdatum()) + "'" 
		    				+ ", " + "'" + format.format(a.getEnddatum()) + "'" 
		    				+ ", " + a.getProjekt_ID() + ", " + a.getOrga_ID()  +")"); 
		    	  
		      }
		}
		catch(SQLException e2){
			e2.printStackTrace();
		}
		return a;		
	}
	
	/*
	 * Beteiligung wird aus der Datenbank gelöscht
	 * @param zu löschendes Beteiligungsobjekt
	 */
	
	public void delete(Beteiligung a){
		
		Connection con = DBConnection.connection();
		
		try {
		      Statement stmt = con.createStatement();
		      stmt.executeUpdate("DELETE FROM Beteiligung " 
		    		  			+ "WHERE Beteiligung.ID = " + a.getID());
			}
		
		catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	
	/*
	 * Eine vorhandene Beteiligung aus der Datenbank wird aktualisiert
	 * @param zu aktualisierendes Beteiligungsobjekt
	 */
	
	public Beteiligung update(Beteiligung c) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();
	      if(c.getProjekt_ID()==null && c.getOrga_ID()== null){
	    	  stmt.executeUpdate("UPDATE Beteiligung " + "SET umfang='"
	    	          + c.getUmfang() + "', enddatum= '" + format.format(c.getEnddatum())
	    	    		  + "', Projektmarktplatz_ID = NULL, Projektleiter_ID = NULL " + "' WHERE Beteiligung.ID = " + c.getID());
	      }else{
	      stmt.executeUpdate("UPDATE Beteiligung " + "SET umfang='"
	          + c.getUmfang() + "', enddatum= '" + format.format(c.getEnddatum())
	    		  + "' WHERE Beteiligung.ID = " + c.getID());
	      }
	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	    }

	    return c;
	  }
	
}
