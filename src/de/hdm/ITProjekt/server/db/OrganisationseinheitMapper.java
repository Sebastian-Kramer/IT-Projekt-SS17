package de.hdm.ITProjekt.server.db;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.ITProjekt.shared.bo.Organisationseinheit;
import de.hdm.ITProjekt.shared.bo.Person;
import de.hdm.ITProjekt.shared.bo.Projektmarktplatz;

/*
 * Mapper für Organisationseinheit Objekte
 */

public class OrganisationseinheitMapper {


	/*
	 * Speicherung der einzigen Instanz dieser Mapperklasse
	 */
	
	private static OrganisationseinheitMapper orgMapper = null;
	
	/*
	 * Konstruktor wird geschützt, damit Objekte der Klasse OrganisatoinseinheitMapper
	 *  nicht außerhalb der Vererbungshirarchie der Klasse erstellt werden können
	 */
	
	protected OrganisationseinheitMapper(){
		
	}
	
	/*
	 * Singelton Eigenschaft der Mapperklasse, nur eine Instanz kann Existieren
	 * @return orgMapper
	 */
	
	public static OrganisationseinheitMapper orgMapper(){
		if(orgMapper == null){
			orgMapper = new OrganisationseinheitMapper();
		}
		return orgMapper;
	}
	
	/*
	 * Organisationseinheit wird anhand der übergebenen, eindeutigen ID zurückgegeben
	 * @return Organisationseinheit entsprechend der übergebenen ID
	 * @param ID Primärschlüssel ID der Tabelle Organisationseinheit
	 */
	
	
	public Organisationseinheit findByKey(int id){
		Connection con = DBConnection.connection();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT ID, strasse, hausnummer, plz, ort, Partnerprofil_ID FROM Organisationseinheit "
          + "WHERE ID=" + id);
			
			if(rs.next()){
				Organisationseinheit o = new Organisationseinheit();
				o.setID(rs.getInt("ID"));
				o.setStrasse(rs.getString("strasse"));
				o.setHausnummer(rs.getInt("hausnummer"));
				o.setPlz(rs.getInt("plz"));
				o.setOrt(rs.getString("ort"));
				o.setPartnerprofil_ID(rs.getInt("Partnerprofil_ID"));
				
				return o;
			}
		}
		catch(SQLException e2){
			e2.printStackTrace();
			return null;
		}
		return null;	
	
	}
	
	/*
	 * @param o
	 * @return Liefert die Id entsprechend des übergebenen Objekts zurück
	 */
	
	protected Organisationseinheit findByObject(Organisationseinheit o){
		return this.findByKey(o.getID()); 
	}
	
	/*Suche von einer Organisationseinheit durch ein übergebendes Partnerprofil.
	 *Ein Organisationseinheit-Objekt wird zurueckgegeben.
	 */
	
	public Organisationseinheit findByForeignPartnerprofilId(int partnerprofilId){
		Connection con = DBConnection.connection();
			
			try{
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT ID, strasse, hausnummer, plz, ort, Partnerprofil_ID FROM Organisationseinheit "
						+ "WHERE Partnerprofil_ID=" + partnerprofilId);
				
				
				if(rs.next()){
					Organisationseinheit o = new Organisationseinheit();
					o.setID(rs.getInt("ID"));
					o.setStrasse(rs.getString("strasse"));
					o.setHausnummer(rs.getInt("hausnummer"));
					o.setPlz(rs.getInt("plz"));
					o.setOrt(rs.getString("ort"));
					o.setPartnerprofil_ID(rs.getInt("Partnerprofil_ID"));
			return o;
				
				}
		}
			catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
			return null;
		}
	
	/*
	 *Durch die insert-Methode kann eine neue Organisationseinheit in die Datenbank geschrieben werden.
	 *Die id des Projekts wird überprüft und im Verlauf der Methode ggf. angepasst.
	 *@param o
	 *@return Übergebenes Organisationseinheit-Objekt o wird in DB geschrieben
	 */
	
		protected int insert(Organisationseinheit o){
		 
	    Connection con = DBConnection.connection();
	    int id=0;
	    try {
	    		
	      Statement stmt = con.createStatement();

	      /*
	       * Zunächst schauen wir nach, welches der momentan höchste
	       * Primärschlüsselwert ist.
	       */
	      ResultSet rs = stmt.executeQuery("SELECT MAX(ID) AS maxid "
	          + "FROM Organisationseinheit");
	      
	      if (rs.next()) {

	        o.setID(rs.getInt("maxid") + 1);
	        id=o.getID();
	        stmt = con.createStatement();

	        // Jetzt erst erfolgt die tatsächliche Einfügeoperation

	        stmt.executeUpdate("INSERT INTO Organisationseinheit (ID, strasse, hausnummer, plz, ort, Partnerprofil_ID) "
	            + "VALUES ('" + o.getID() + "','" + o.getStrasse() + "','"
	            + o.getHausnummer() + "','" + o.getPlz() + "','" + o.getOrt() + "', " + o.getPartnerprofil_ID() + ")");
	      }

	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	    }

	    return id;
	  
  }
		
		/*
		 * Erneutes schreiben eines Ausschreibungsobjekts in die Datenbank
		 * @param a
		 * @return das als PArameter übergebene und aktualisierte Ausschreibungsobjekt
		 */
	
	
	public int update(Organisationseinheit o){
		
		Connection con = DBConnection.connection();
		int id = 0;

		    try {
		    	id = o.getID();
		      Statement stmt = con.createStatement();

		      if (o.getPartnerprofil_ID() != null){
		      stmt.executeUpdate("UPDATE Organisationseinheit SET strasse='"
			          + o.getStrasse() + "'," + "hausnummer='" + o.getHausnummer() + "'," + "ort='" 
		    		  + o.getOrt() + "'," + "plz='" + o.getPlz() + "',"
			          + "Partnerprofil_ID='" + o.getPartnerprofil_ID() + "' WHERE ID="+o.getID());
		      }else {
		    	  stmt.executeUpdate("UPDATE Organisationseinheit SET strasse='"
				          + o.getStrasse() + "'," + "hausnummer='" + o.getHausnummer() + "'," + "ort='" 
			    		  + o.getOrt() + "'," + "plz='" + o.getPlz() + "',"
				          + "Partnerprofil_ID= NULL WHERE ID="+o.getID());
		      }
		    }
		    catch (SQLException e) {
		      e.printStackTrace();
		    }

		    return id;
	  }
	
	/*
	 * Löschen der Übergebenen Organisationseinheit
	 * @param o Organisationseinheitobjekt, das gelöscht werden soll
	 */
	
	
	public void delete(Organisationseinheit o){
		 Connection con = DBConnection.connection();

		    try {
		      Statement stmt = con.createStatement();

		      stmt.executeUpdate("DELETE FROM Organisationseinheit" + " WHERE ID=" + o.getID());
		      
		    }
		    catch (SQLException e) {
		      e.printStackTrace();
		    }
	  }	
	
}
