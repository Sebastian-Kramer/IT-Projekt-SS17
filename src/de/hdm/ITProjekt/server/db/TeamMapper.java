package de.hdm.ITProjekt.server.db;

import de.hdm.ITProjekt.shared.bo.Team;
import de.hdm.ITProjekt.server.db.DBConnection;
import java.sql.*;
import java.util.Vector;
import de.hdm.ITProjekt.shared.bo.Organisationseinheit;

/*
 * Mapper für TeamObjekte
 */

public class TeamMapper extends OrganisationseinheitMapper{
	
	/*
	 * Speicherung der einzigen Instanz dieser Mapperklasse
	 */

private static TeamMapper tMapper = null;

/*
 * Konstruktor wird geschützt, damit Objekte der Klasse TeamMapper
 *  nicht außerhalb der Vererbungshirarchie der Klasse erstellt werden können
 */
	
	protected TeamMapper(){
		
	}
	
	/*
	 * Singelton Eigenschaft der Mapperklasse, nur eine Instanz kann Existieren
	 * @return tMapper
	 */
	
	public static TeamMapper tMapper(){
		if(tMapper == null){
			tMapper = new TeamMapper();
		}
		return tMapper;
	}
	
	/*
	 * Team wird anhand der übergebenen, eindeutigen ID zurückgegeben
	 * @return Team entsprechend der übergebenen ID
	 * @param id Primärschlüssel ID der Tabelle Team
	 */
	
public Team findByKey(int id){
		
		Connection con = DBConnection.connection();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT ID, name, UN_ID FROM Team " + "WHERE ID=" + id);
			
			if(rs.next()){
					Team t = new Team();
					t.setID(rs.getInt("ID"));
					t.setName(rs.getString("name"));
					t.setUN_ID(rs.getInt("UN_ID"));
					t.setStrasse(super.findByKey(id).getStrasse());
					t.setHausnummer(super.findByKey(id).getHausnummer());
					t.setOrt(super.findByKey(id).getOrt());
					t.setPlz(super.findByKey(id).getPlz());
					t.setPartnerprofil_ID(super.findByKey(id).getPartnerprofil_ID());
					
					return t;
			}
		}
		catch(SQLException e2){
			e2.printStackTrace();
			return null;
		}
		return null;	
	}

/*
 * @param t
 * @return Liefert ein Team entsprechend des übergebenen Objekts zurueck.
 */

public Team findByObject(Team t){
	return this.findByKey(t.getID());
	  
  }

/*
 * Alle Teams aus der Datenbank werden ausgegeben
 * @return result
 */

public Vector<Team> getAll(){
	
	 Connection con = DBConnection.connection();	 
	
	 Vector<Team> result = new Vector<Team>();
	 
	  try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("SELECT ID, name, UN_ID FROM Team ");
	  
	  while (rs.next()) {
		  	Team t = new Team();
			t.setID(rs.getInt("ID"));
			t.setName(rs.getString("name"));
			t.setUN_ID(rs.getInt("UN_ID"));
			
		  
		  result.addElement(t);
	  }
	}
	    catch (SQLException e2) {
	        e2.printStackTrace();
	      }
	  return result;
}

/* 
 * @param unternehmenId
 * @return Liefert alle Team-Objekte des uebergebenen Unternehmens zurueck.
 */

public Vector<Team> findByUN(int unternehmenId){
	
        Connection con = DBConnection.connection();
         Vector <Team> t = new Vector<Team>();

        try {

          Statement stmt = con.createStatement();


          ResultSet rs = stmt.executeQuery("SELECT * FROM team "
              + "WHERE UN_ID=" + unternehmenId);


          while (rs.next()) {
            // Ergebnis-Tupel in Objekt umwandeln
        	Team te=new Team();
            te.setID(rs.getInt("ID"));
            te.setName(rs.getString("name"));	        
            te.setUN_ID(rs.getInt("UN_ID"));
            
            t.add(te);
          }
        }
        catch (SQLException e) {
          e.printStackTrace();
        }
	  return t;
  }

/*
 *  Hinzufügen einesTeamobejkts in die Datenbank
 *  @param p1 das Teamsobjekt das gespeichert werden soll
 *  @return p1
 */

public Team insert(Team p1){
	
	Connection con = DBConnection.connection();
	
	try {
	      Statement stmt = con.createStatement();
	      
	      p1.setID(super.insert(p1));
	      
	      if (p1.getUN_ID() == null) {
        	  stmt.executeUpdate("INSERT INTO `Team`(`ID`, `name`) "
	        		  + "VALUES ('" + p1.getID() + "','" + p1.getName()+"')");
		}
          else {
			
        	  stmt.executeUpdate("INSERT INTO `Team`(`ID`, `name`,`UN_ID`) "
        			  + "VALUES ('" + p1.getID() + "','" + p1.getName() +"','" + p1.getUN_ID()+"')");
		}
//	      ResultSet rs = stmt.executeQuery("SELECT MAX(ID) AS maxid "
//	              + "FROM Team ");
//	      
//	
//	      if(rs.next()){
//	    	  
//	    	  	p1.setID(rs.getInt("maxid") + 1);
//	   	  
//	    	  	stmt = con.createStatement();
//	    	  	
//	    		stmt.executeUpdate("INSERT INTO Team (ID, name, UN_ID)" 
//	    				+ "VALUES (" + p1.getID() + ", " + "'" + p1.getName() + "'" + ", " + p1.getUN_ID() 
//	    				+")"); 
//	    	  
//	      }
	}
	catch(SQLException e2){
		e2.printStackTrace();
	}
	return p1;		
}

/*
 * Löschen des Übergebenen Teams
 * @param p1 Ausschreibungsobjekt, das gelöscht werden soll
 * @return t
 */

public void delete(Team p1){
	
	Connection con = DBConnection.connection();
	
	try {
	      Statement stmt = con.createStatement();

	      stmt.executeUpdate("DELETE FROM Team " 
	    		  			+ "WHERE ID = " + p1.getID());
	      super.delete(p1);

		}
	
	catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

/*
 * Erneutes schreiben eines Teamobjekts in die Datenbank
 * @param t
 * @return das als Parameter übergebene und aktualisierte Teamobjekt
 */

public Team update(Team t) {
    Connection con = DBConnection.connection();

    try {
    	t.setID(super.update(t));
    	
    	super.orgMapper().update(t);
    	
    	Statement stmt = con.createStatement();

      if(t.getUN_ID() == null){
    	  stmt.executeUpdate("UPDATE Team SET name='"
    	          + t.getName() +"'" +  "WHERE ID = " + t.getID());
    	  super.update(t);
    	  
      }else if (t.getUN_ID() != null){
    	  stmt.executeUpdate("UPDATE Team SET name='"
    	          + t.getName() +"'" + ", UN_ID = " + t.getUN_ID() + " WHERE ID = " + t.getID());
    	  super.update(t);
      }
      
    }
    catch (SQLException e) {
      e.printStackTrace();
    }

    return t;
  }
}
