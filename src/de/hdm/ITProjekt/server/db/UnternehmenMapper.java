package de.hdm.ITProjekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.ITProjekt.shared.bo.Organisationseinheit;
import de.hdm.ITProjekt.shared.bo.Person;
import de.hdm.ITProjekt.shared.bo.Team;
import de.hdm.ITProjekt.shared.bo.Unternehmen;

/*
 * Mapper für Unternehmenobjekte
 */

public class UnternehmenMapper extends OrganisationseinheitMapper{
	
	/*
	 * Speicherung der einzigen Instanz dieser Mapperklasse
	 */

	
	private static UnternehmenMapper unMapper = null;
	
	/*
	 * Konstruktor wird geschützt, damit Objekte der Klasse UnternehmenMapper
	 *  nicht außerhalb der Vererbungshirarchie der Klasse erstellt werden können
	 */
	
	protected UnternehmenMapper(){
		
	}
	
	/*
	 * Singelton Eigenschaft der Mapperklasse, nur eine Instanz kann Existieren
	 * @return unMapper
	 */

	public static UnternehmenMapper unMapper(){
		if(unMapper == null){
			unMapper = new UnternehmenMapper();
		}
		return unMapper;
	}
	
	/*
	 * Unternehmen wird anhand der übergebenen, eindeutigen ID zurückgegeben
	 * @return Unternehmen entsprechend der übergebenen ID
	 * @param id Primärschlüssel ID der Tabelle Unternehmen
	 */


public Unternehmen findByKey(int id){
	
	Connection con = DBConnection.connection();
	
	try{
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT ID, name FROM Unternehmen "
      + "WHERE ID=" + id);
		
		if(rs.next()){
			Unternehmen un = new Unternehmen();
			un.setID(rs.getInt("ID"));
			un.setName(rs.getString("name"));
			un.setStrasse(super.findByKey(id).getStrasse());
			un.setHausnummer(super.findByKey(id).getHausnummer());
			un.setOrt(super.findByKey(id).getOrt());
			un.setPlz(super.findByKey(id).getPlz());
			un.setPartnerprofil_ID(super.findByKey(id).getPartnerprofil_ID());
			return un;
		}
	}
	catch(SQLException e2){
		e2.printStackTrace();
		return null;
	}
	return null;	
	
}

/*
 * @param u
 * @return Liefert die ID entsprechend des übergebenen Objekts zurück.
 */
	
	public Unternehmen findByObject(Unternehmen u){
		return this.findByKey(u.getID());
	}
	
	/*
	 * Alle Unternehmen aus der Datenbank werden ausgegeben
	 * @return result
	 */

	
	public Vector<Unternehmen> getAllUnternehmen(){
		
		 Connection con = DBConnection.connection();
		 
		 Vector<Unternehmen> result = new Vector<Unternehmen>();
		 
		  try {
		      Statement stmt = con.createStatement();

		      ResultSet rs = stmt.executeQuery("SELECT * FROM Unternehmen");
		  
		  while (rs.next()) {
			  Unternehmen u = new Unternehmen();
				u.setID(rs.getInt("Unternehmen_Id"));
				u.setName(rs.getString("Name"));
				u.setStrasse(super.findByObject(u).getStrasse());
				u.setHausnummer(super.findByObject(u).getHausnummer());
				u.setPlz(super.findByObject(u).getPlz());
				u.setOrt(super.findByObject(u).getOrt());
			 
				result.addElement(u);
		  }
		}
		    catch (SQLException e2) {
		        e2.printStackTrace();
		        return null;
		      }
		  return result;
	}
	
	/*
	 *  Hinzufügen eines Unternehmensobejkts in die Datenbank
	 *  @param u das Unternehmensobjekt das gespeichert werden soll
	 *  @return u
	 */
	
public Unternehmen createUnternehmen(Unternehmen u){
		
		Connection con = DBConnection.connection();
		
		try {
		      Statement stmt = con.createStatement();
		      
		      u.setID(super.insert(u));
		      
		      ResultSet rs = stmt.executeQuery("SELECT MAX(ID) AS maxid "
		              + "FROM Unternehmen ");
		      
		
		      if(rs.next()){
		    	  
		    	  	u.setID(rs.getInt("maxid") + 1);
		    	  
		    	  	stmt = con.createStatement();
		    	  	
		    		stmt.executeUpdate("INSERT INTO Unternehmen (ID , name)" + "VALUES "
		    				+ "("+  u.getID()+ "," + "'" + u.getName() + "'" +")");		    				    			    	  
		      }
		}
		catch(SQLException e2){
			e2.printStackTrace();
		}
		return u;
	}


/*
 * Erneutes schreiben eines Unternehmensobjekts in die Datenbank
 * @param u
 * @return das als Parameter übergebene und aktualisierte Unternehmensobjekt
 */


public Unternehmen updateUnternehmen(Unternehmen u) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("UPDATE Unternehmen SET " + "name=\""
          + u.getName() + "\"" + "WHERE ID=" + u.getID());
    }
    catch (SQLException e2) {
      e2.printStackTrace();
      super.update(u);
    }

    return u;
  }


/*
 * Löschen der Übergebenen Unternehmen
 * @param u Unternehmensobjekt, das gelöscht werden soll
 */


public void deleteUnternehmen(Unternehmen u) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();
      stmt.executeUpdate("DELETE FROM Unternehmen " + "WHERE ID=" + u.getID());
      super.delete(u);
    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

  }

}
