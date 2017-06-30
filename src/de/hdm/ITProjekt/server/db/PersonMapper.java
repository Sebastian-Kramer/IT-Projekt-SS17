package de.hdm.ITProjekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.ITProjekt.shared.bo.Person;

/*
 * Mapper fuer Person Objekte
 */

public class PersonMapper extends OrganisationseinheitMapper{
	
	/*
	 * Speicherung der einzigen Instanz dieser Mapperklasse
	 */
	
	private static PersonMapper perMapper = null;
	
	/*
	 * Konstruktor wird geschützt, damit Objekte der Klasse Personenmapper
	 *  nicht außerhalb der Vererbungshirarchie der Klasse erstellt werden können
	 */
	
	protected PersonMapper(){
		
	}
	
	/*
	 * Singelton Eigenschaft der Mapperklasse, nur eine Instanz kann Existieren
	 * @return perMapper
	 */
	
	public static PersonMapper perMapper(){
		if(perMapper == null){
			perMapper = new PersonMapper();
		}
		return perMapper;
	}
	
//	public Person getByEmail(Person p1){
//		Connection con = DBConnection.connection();
//		
//		try{
//			Statement stmt = con.createStatement();
//			ResultSet rs = stmt.executeQuery("SELECT ID, email, anrede, vorname, name, Team_ID, UN_ID FROM Person "
//          + " WHERE email=" + p1.getEmail());
//			
//			if(rs.next()){
//				Person p = new Person();
//				p.setID(rs.getInt("ID"));
//				p.setEmail(rs.getString("email"));
//				p.setAnrede(rs.getString("anrede"));
//				p.setVorname(rs.getString("vorname"));
//				p.setName(rs.getString("name"));
//				p.setTeam_ID(rs.getInt("Team_ID"));
//				p.setUN_ID(rs.getInt("UN_ID"));
//				p.setStrasse(super.findByKey(p1.getID()).getStrasse());
//				p.setHausnummer(super.findByKey(p1.getID()).getHausnummer());
//				p.setPlz(super.findByKey(p1.getID()).getPlz());
//				p.setOrt(super.findByKey(p1.getID()).getOrt());
//				p.setPartnerprofil_ID(super.findByKey(p1.getID()).getPartnerprofil_ID());				
//				
//				return p;
//			}
//		}
//		catch(SQLException e2){
//			e2.printStackTrace();
//			return null;
//		}
//		return null;	
//		
//	}
	
	/*
	 * Person wird anhand der übergebenen, eindeutigen ID zurückgegeben
	 * @return Person entsprechend der übergebenen ID
	 * @param id Primärschlüssel ID der Tabelle Person
	 */
	public Person findByKey(int id){
		
		Connection con = DBConnection.connection();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT ID, email, anrede, vorname, name, Team_ID, UN_ID, isAdmin FROM Person "
          + "WHERE ID=" + id);
			
			if(rs.next()){
				Person p = new Person();
				p.setID(rs.getInt("ID"));
				p.setEmail(rs.getString("email"));
				p.setAnrede(rs.getString("anrede"));
				p.setVorname(rs.getString("vorname"));
				p.setName(rs.getString("name"));
				p.setTeam_ID(rs.getInt("Team_ID"));
				p.setUN_ID(rs.getInt("UN_ID"));
				p.setAdmin(rs.getBoolean("isAdmin"));
				p.setStrasse(super.findByKey(id).getStrasse());
				p.setHausnummer(super.findByKey(id).getHausnummer());
				p.setPlz(super.findByKey(id).getPlz());
				p.setOrt(super.findByKey(id).getOrt());
				p.setPartnerprofil_ID(super.findByKey(id).getPartnerprofil_ID());				
				
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
	 * @param p
	 * @return Liefert die ID entsprechend des übergebenen Objekts zurück.
	 */
	
	public Person findByObject(Person p){
		
		return this.findByKey(p.getID());	
	}

	public Person getPersonbyOrgaID(Integer id){
		
		Connection con = DBConnection.connection();
		Person p = new Person();
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT ID, email, anrede, vorname, name FROM Person "
			          + "WHERE ID=" + id);
			
			if(rs.next()){
				p.setID(rs.getInt("ID"));
				p.setEmail(rs.getString("email"));
				p.setAnrede(rs.getString("anrede"));
				p.setVorname(rs.getString("vorname"));
				p.setName(rs.getString("name"));
			}
			
		}catch(SQLException e2){
				e2.printStackTrace();
				return null;
			}
		return p;
	}
	
	/*
	 * Notiz von Mert: Nochmal dr�ber schauen und ausgeben lassen in TestStart!
	 */
	
	/* 
	 * @param int id
	 * @return Liefert alle Personen des uebergebenen Teams zurueck.
	 */
	
	public Vector<Person> findByForeignTeamId(int id){
		
		Connection con = DBConnection.connection();
		
		Vector<Person> result = new Vector<Person>();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * "
					+ " FROM Person " + "WHERE Team_ID=" + id);
			
			
			while (rs.next()){
				Person p = new Person();
				p.setID(rs.getInt("ID"));
				p.setEmail(rs.getString("email"));
				p.setAnrede(rs.getString("anrede"));
				p.setVorname(rs.getString("vorname"));
				p.setName(rs.getString("name"));
				p.setTeam_ID(rs.getInt("Team_ID"));
				p.setUN_ID(rs.getInt("UN_ID"));
				p.setAdmin(rs.getBoolean("isAdmin"));
				p.setStrasse(super.findByKey(id).getStrasse());
				p.setHausnummer(super.findByKey(id).getHausnummer());
				p.setPlz(super.findByKey(id).getPlz());
				p.setOrt(super.findByKey(id).getOrt());
				p.setPartnerprofil_ID(super.findByKey(id).getPartnerprofil_ID());	
			
				result.add(p);
				}
			}   
		catch (SQLException e) {
		e.printStackTrace();
		}
		return result;
		
	}
	
	/*
	 * @param int id
	 * @return Liefer alle Personen des uebergebenen Unternehmens zurueck.
	 */
	
public Vector<Person> findByForeignUnternehmenId(int id){
		
		Connection con = DBConnection.connection();
		
		Vector<Person> result = new Vector<Person>();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * "
					+ " FROM Person " + "WHERE UN_ID=" + 
			id);
			
			
			while (rs.next()){
				Person p = new Person();
				p.setID(rs.getInt("ID"));
				p.setEmail(rs.getString("email"));
				p.setAnrede(rs.getString("anrede"));
				p.setVorname(rs.getString("vorname"));
				p.setName(rs.getString("name"));
				p.setTeam_ID(rs.getInt("Team_ID"));
				p.setUN_ID(rs.getInt("UN_ID"));
				p.setAdmin(rs.getBoolean("isAdmin"));
				p.setStrasse(super.findByKey(id).getStrasse());
				p.setHausnummer(super.findByKey(id).getHausnummer());
				p.setPlz(super.findByKey(id).getPlz());
				p.setOrt(super.findByKey(id).getOrt());
				p.setPartnerprofil_ID(super.findByKey(id).getPartnerprofil_ID());				

				result.add(p);
				} 
			}  
		catch (SQLException e) {
		e.printStackTrace();
		}
		return result;
	}

/*
 * Alle Personen aus der Datenbank werden ausgegeben
 * @return result
 */
	
	public Vector<Person> getAll(){
		
		 Connection con = DBConnection.connection();
		 
		 Vector<Person> result = new Vector<Person>();
		 
		  try {
		      Statement stmt = con.createStatement();

		      ResultSet rs = stmt.executeQuery("SELECT * FROM Person");
		  
		  while (rs.next()) {
			    Person p = new Person();
				p.setID(rs.getInt("ID"));
				p.setEmail(rs.getString("email"));
				p.setAnrede(rs.getString("anrede"));
				p.setVorname(rs.getString("vorname"));
				p.setName(rs.getString("name"));
				p.setTeam_ID(rs.getInt("Team_ID"));
				p.setUN_ID(rs.getInt("UN_ID"));
				p.setAdmin(rs.getBoolean("isAdmin"));
				p.setStrasse(super.findByObject(p).getStrasse());
				p.setHausnummer(super.findByObject(p).getHausnummer());
				p.setPlz(super.findByObject(p).getPlz());
				p.setOrt(super.findByObject(p).getOrt());
				p.setPartnerprofil_ID(super.findByObject(p).getPartnerprofil_ID());
			  
				result.add(p);
		  }
		}
		    catch (SQLException e2) {
		        e2.printStackTrace();
		      }
		  return result;
	}
	
	public Vector<Person> getPersonByID(Integer id){
Connection con = DBConnection.connection();
Vector <Person> result = new Vector<Person>();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT ID, email, anrede, vorname, name, Team_ID, UN_ID FROM Person "
          + "WHERE ID=" + id);
			
			if(rs.next()){
				Person p = new Person();
				p.setID(rs.getInt("ID"));
				p.setEmail(rs.getString("email"));
				p.setAnrede(rs.getString("anrede"));
				p.setVorname(rs.getString("vorname"));
				p.setName(rs.getString("name"));
				p.setTeam_ID(rs.getInt("Team_ID"));
				p.setUN_ID(rs.getInt("UN_ID"));
				p.setStrasse(super.findByKey(id).getStrasse());
				p.setHausnummer(super.findByKey(id).getHausnummer());
				p.setPlz(super.findByKey(id).getPlz());
				p.setOrt(super.findByKey(id).getOrt());
				p.setPartnerprofil_ID(super.findByKey(id).getPartnerprofil_ID());
				
				result.addElement(p);
	}
		}
		catch (SQLException e2) {
	        e2.printStackTrace();
		}
		return result;
		
	}
	
	  /*
	   * @param p
	   * @return Uebergebenes Objekt als neue Entitaet in die Datenbank schreiben.
	   */

	public Person createPerson(Person p){
		
		Connection con = DBConnection.connection();
		
		try {
		      Statement stmt = con.createStatement();
		      
		      p.setID(super.insert(p));
		      		      
		      stmt = con.createStatement();
		     		
		      if(p.getTeam_ID()==null && p.getUN_ID()==null){
			        stmt.executeUpdate("INSERT INTO Person (ID, email, name, vorname, anrede) "
				            + "VALUES (" + p.getID() + ",'" + p.getEmail() + "','" + p.getName() + "','"
				            + p.getVorname() + "','" + p.getAnrede() +"'" +")");
			        
		        }else if(p.getTeam_ID()!=null && p.getUN_ID()==null){
			        stmt.executeUpdate("INSERT INTO Person (ID, email, name, vorname, anrede, Team_ID) "
				            + "VALUES (" + p.getID() + ",'"  + p.getEmail() + "','" + p.getName() + "','"
				            + p.getVorname() + "','" + p.getAnrede() + "'," + p.getTeam_ID() +"')");
			        
		        }else if(p.getTeam_ID()==null && p.getUN_ID()!=null){
			        stmt.executeUpdate("INSERT INTO Person (ID, email, name, vorname, anrede, UN_ID) "
				            + "VALUES (" + p.getID() + ",'" + p.getEmail() + "','" + p.getName() + "','"
				            + p.getVorname() + "','" + p.getAnrede() + "'," + p.getUN_ID() +"')");
			        
		        }else if(p.getTeam_ID()!=null && p.getUN_ID()!=null){
			        // Jetzt erst erfolgt die tatsächliche Einfügeoperation
			        stmt.executeUpdate("INSERT INTO Person (ID, email, name, vorname, anrede, UN_ID, Team_ID) "
			            + "VALUES (" + p.getID() + ",'" + p.getEmail() + "','" + p.getName() + "','"
			            + p.getVorname() + "','" + p.getAnrede() + "'," + p.getUN_ID() + "," + p.getTeam_ID() +"')");
		        } 
		      
		}
		catch(SQLException e2){
			e2.printStackTrace();
			System.out.println("Falsche Werte");
		}
		return p;
	}
	
	/*
	 * Erneutes schreiben eines Personenobjekts in die Datenbank
	 * @param p
	 * @return das als PArameter übergebene und aktualisierte Personobjekt
	 */

	public Person updatePerson(Person p) {
		
	    Connection con = DBConnection.connection();

	    
	    try {
	    	p.setID(super.update(p));
	    	super.orgMapper().update(p);
	    	
	    	 Statement stmt = con.createStatement();
	    	
	    	 if(p.getTeam_ID()==null && p.getUN_ID()==null){
			      stmt.executeUpdate("UPDATE Person " + "SET vorname=\""
				          + p.getVorname() + "\", " + "name=\"" + p.getName() + "\", " + "anrede=\""+ p.getAnrede() +
				          "\", " + "UN_ID=NULL, Team_ID=NULL" + " WHERE ID=" + p.getID());
	        }else if(p.getTeam_ID()!=null && p.getUN_ID()==null){
			      stmt.executeUpdate("UPDATE Person " + "SET vorname=\""
				          + p.getVorname() + "\", " + "name=\"" + p.getName() + "\", " + "anrede=\""+ p.getAnrede() +
				          "\", " + "UN_ID=NULL, Team_ID=\""+ p.getTeam_ID() + "\" "
				          + "WHERE ID=" + p.getID());
	        }else if(p.getTeam_ID()==null && p.getUN_ID()!=null){
			      stmt.executeUpdate("UPDATE Person " + "SET vorname=\""
				          + p.getVorname() + "\", " + "name=\"" + p.getName() + "\", " + "anrede=\""+ p.getAnrede() +
				          "\", " + "UN_ID=\"" + p.getUN_ID() + "\", " + "Team_ID=NULL" + " WHERE ID=" + p.getID());
	        }else if(p.getTeam_ID()!=null && p.getUN_ID()!=null){
			      stmt.executeUpdate("UPDATE Person " + "SET vorname=\""
				          + p.getVorname() + "\", " + "name=\"" + p.getName() + "\", " + "anrede=\""+ p.getAnrede() +
				          "\", " + "UN_ID=\"" + p.getUN_ID() + "\", " + "Team_ID=\""+ p.getTeam_ID() + "\" "
				          + "WHERE ID=" + p.getID());
	        } 
	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }

	    return p;
	  }
	
	/*
	 * Löschen der Übergebenen Person
	 * @param p Personobjekt, das gelöscht werden soll
	 */
	
	public void deletePerson(Person p) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();
	      
	      stmt.executeUpdate("DELETE FROM Person " + "WHERE ID=" + p.getID());
	      
	      super.delete(p);

	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }
	
	}
}


