package de.hdm.ITProjekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.text.SimpleDateFormat;

import de.hdm.ITProjekt.shared.bo.Ausschreibung;
import de.hdm.ITProjekt.shared.bo.Bewerbung;
import de.hdm.ITProjekt.shared.bo.Person;

/*
 * Die Klasse BewerbungMapper bildet Bewerbungs-Objekte auf einer relationalen Datenbank ab.
 * Mit Hilfe von verschiedenen Methoden können die jeweilgen Objekte aus der Datenbank geholt, geschrieben 
 * oder aktualisiert werden.
 * Die Besonderheit ist, dass Objekte in DB-Strukturen und umgekehrt umgewandelt werden können
 */

public class BewerbungMapper {
	
	/*
	 * SimpleDateFormat wird benötigt um das korrekte Format 
	 * eines Datum zu lesen oder zu schreiben
	 */
	
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	/*
	 * Speicherung der einzigen Instanz dieser Mapperklasse
	 */
	
	private static BewerbungMapper bewMapper = null;
	
	/*
	 * Konstruktor wird geschützt, damit Objekte der Klasse BewerbungMapper
	 *  nicht außerhalb der Vererbungshirarchie der Klasse erstellt werden können
	 */
	
	protected BewerbungMapper(){
		
	}
	
	/*
	 * Singelton Eigenschaft der Mapperklasse, nur eine Instanz kann Existieren
	 * @return bewMapper
	 */
	
	public static BewerbungMapper bewMapper(){
		if(bewMapper == null){
			bewMapper = new BewerbungMapper();
		}
		return bewMapper;
	}
	
	/*
	 * Bewerbung wird anhand der übergebenen, eindeutigen ID zurückgegeben
	 * @return Bewerbung entsprechend der übergebenen ID
	 * @param ID Primärschlüssel ID der Tabelle Bewerbung
	 */
	
	public Bewerbung findByKey(int id){
		
		Connection con = DBConnection.connection();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT ID, bewerbungstext, erstelldatum, Ausschreibungs_ID, Orga_ID FROM Bewerbung "
          + "WHERE ID=" + id + " ORDER BY ID");
			
			if(rs.next()){
				Bewerbung p = new Bewerbung();
				p.setID(rs.getInt("ID"));
				p.setBewerbungstext(rs.getString("bewerbungstext"));
				p.setErstelldatum(rs.getDate("erstelldatum"));
				p.setAusschreibungs_ID(rs.getInt("Ausschreibungs_ID"));
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
	 * Auslesen aller Bewerbungen die in der DB gespeichert sind
	 * @return Vector mit allen gefundenen Bewerbungen
	 */
	
	public Vector<Bewerbung> getAll(){
		
		 Connection con = DBConnection.connection();	 
		
		 Vector<Bewerbung> result = new Vector<Bewerbung>();
		 
		  try {
		      Statement stmt = con.createStatement();

		      ResultSet rs = stmt.executeQuery("SELECT ID, bewerbungstext, status, erstelldatum, Ausschreibungs_ID, Orga_ID FROM Bewerbung");
		  
		  while (rs.next()) {
			  Bewerbung p = new Bewerbung();
				p.setID(rs.getInt("ID"));
				p.setBewerbungstext(rs.getString("bewerbungstext"));
				p.setStatus(rs.getString("status"));
				p.setErstelldatum(rs.getDate("erstelldatum"));
				p.setAusschreibungs_ID(rs.getInt("Ausschreibungs_ID"));
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
	 * Wird eine Bewerbung neu angelegt wird diese mit der insert-Methode in die jeweilige Tabelle 
	 * der Datenbank geschrieben.
	 * Dazu wird die bisher höchste ID gesucht und mit 1 addiert.
	 * @return Gespeichertes Bewerbung Objekt.
	 * @param neuen Bewerbungsobjekt
	 */
		
	public Bewerbung insert(Bewerbung a){
		
		Connection con = DBConnection.connection();
		
		try {
		      Statement stmt = con.createStatement();
		      
		      ResultSet rs = stmt.executeQuery("SELECT MAX(ID) AS maxid "
		              + "FROM Bewerbung ");
		      
		
		      if(rs.next()){
		    	  
		    	  	a.setID(rs.getInt("maxid") + 1);
		   	  
		    	  	stmt = con.createStatement();
		    	  	
		    		stmt.executeUpdate("INSERT INTO Bewerbung (ID, bewerbungstext, erstelldatum, status, Ausschreibungs_ID, Orga_ID)" 
		    				+ "VALUES (" + a.getID() + ", " + "'" + a.getBewerbungstext() + "'"  
		    				+ ", " + "'" + format.format(a.getErstelldatum()) + "'" + ", "  + "'" +a.getStatus() + "'"
		    				+ ", " + a.getAusschreibungs_ID() + ", " + a.getOrga_ID()  +")"); 
		    	  
		      }
		}
		catch(SQLException e2){
			e2.printStackTrace();
		}
		return a;		
	}
	
	/*
	 * Bewerbung wird aus der Datenbank gelöscht
	 * @param zu löschendes Bewerbungsobjekt
	 */
	
	public void delete(Bewerbung a){
		
		Connection con = DBConnection.connection();
		
		try {
		      Statement stmt = con.createStatement();

		      stmt.executeUpdate("DELETE FROM Bewerbung " 
		    		  			+ "WHERE Bewerbung.ID = " + a.getID());

			}
		
		catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	
	/*
	 * Eine vorhandener Bewerbungsstatus aus der Datenbank wird aktualisiert
	 * @param zu aktualisierendes Bewerbungsobjekt
	 */
	
	public Bewerbung updateBewerbungsstatus(Bewerbung b){
		Connection con = DBConnection.connection();
		
		try{
			Statement stmt = con.createStatement();
			stmt.executeUpdate("UPDATE Bewerbung " + "SET status= " + "'" + b.getStatus() + "'" 
			+  "WHERE ID = " +b.getID());
		}catch(SQLException e){
			e.printStackTrace();
		}
		return b;
	}
	
	/*
	 * Eine vorhandene Bewerbung aus der Datenbank wird aktualisiert
	 * @param zu aktualisierendes Bewerbungsobjekt
	 */
	
	public Bewerbung update(Bewerbung c) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();
	      if(c.getAusschreibungs_ID()==null && c.getOrga_ID()==null){
	    	  stmt.executeUpdate("UPDATE Bewerbung " + "SET bewerbungstext='" + c.getBewerbungstext() 
	    	  + "', Ausschreibungs_ID = NULL, Orga_ID = NULL " + " WHERE Bewerbung.ID = " + c.getID());
	      
	      }else{

	      stmt.executeUpdate("UPDATE Bewerbung " + "SET bewerbungstext='"
	          + c.getBewerbungstext() 
	    		  + "' WHERE Bewerbung.ID = " + c.getID());
	      }
	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	    }

	    return c;
	  }
	
	/*
	 * Alle Bewerbungen werden anhand der übergebenen, Organisationseinheit
	 * in einem Vector zurückgegeben.
	 * @return alle Elemente die im Vector gespeichert wurden
	 * @param ID Orga_ID der Tabelle Bewerbung
	 */
	
	public Vector<Bewerbung> findByPerson (int Orga_ID){
		
		Connection con = DBConnection.connection();
		Vector<Bewerbung> result = new Vector<Bewerbung>();
		
		try {
		      Statement stmt = con.createStatement();
		      
		      ResultSet rs = stmt.executeQuery("SELECT ID, bewerbungstext,status, erstelldatum, Ausschreibungs_ID, Orga_ID FROM bewerbung WHERE Orga_ID = " + Orga_ID);
		      
		      while(rs.next()){
		    	  Bewerbung b = new Bewerbung();
		    	  b.setID(rs.getInt("ID"));
		    	  b.setBewerbungstext(rs.getString("bewerbungstext"));
		    	  b.setStatus(rs.getString("status"));
		    	  b.setErstelldatum(rs.getDate("erstelldatum"));
		    	  b.setAusschreibungs_ID(rs.getInt("Ausschreibungs_ID"));
		    	  b.setOrga_ID(rs.getInt("Orga_ID"));
		    	  
		    	  result.addElement(b);
		    	  
		      }
		}
		catch (SQLException e2) {
		      e2.printStackTrace();
		}
		return result;
	}
	
	/*
	 * Hilfsmethode die alle Bewerbungen einer Person mit Hilfe eines
	 * Person-Object findet.
	 * Methode findByPerson (int Orga_ID) wird aufgerufen
	 * @param Person
	 */
	
	public Vector<Bewerbung> findByPerson(Person person){
		
		return findByPerson(person.getID());	
	}
	
	/*
	 * Alle Bewerbungen werden anhand der übergebenen AusschreibungsID
	 * in einem Vector zurückgegeben.
	 * @return alle Elemente die im Vector gespeichert wurden
	 * @param ID Ausschreibungs_ID der Tabelle Bewerbung
	 */
	
	public Vector<Bewerbung> findByAusschreibung(int id){
		
		Connection con = DBConnection.connection();
		Vector<Bewerbung> result = new Vector<Bewerbung>();
		
		try {
		      Statement stmt = con.createStatement();
		      
		      ResultSet rs = stmt.executeQuery("SELECT ID, bewerbungstext, erstelldatum, Ausschreibungs_ID, Orga_ID FROM Bewerbung WHERE Ausschreibungs_ID = " + id);
		
		      while(rs.next()){
		    	  Bewerbung b = new Bewerbung();
		    	  b.setID(rs.getInt("ID"));
		    	  b.setBewerbungstext(rs.getString("bewerbungstext"));
		    	  b.setErstelldatum(rs.getDate("erstelldatum"));
		    	  b.setAusschreibungs_ID(rs.getInt("Ausschreibungs_ID"));
		    	  b.setOrga_ID(rs.getInt("Orga_ID"));
		    	  
		    	  result.addElement(b);
		      }
		}
		catch (SQLException e2) {
		      e2.printStackTrace();
		}
		return result;		
		}
			
	}
	

