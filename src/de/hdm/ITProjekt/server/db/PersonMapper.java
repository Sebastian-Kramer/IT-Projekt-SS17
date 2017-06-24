package de.hdm.ITProjekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.ITProjekt.shared.bo.Person;


public class PersonMapper extends OrganisationseinheitMapper{
	
	
	private static PersonMapper perMapper = null;
	
	protected PersonMapper(){
		
	}
	
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
	public Person findByKey(int id){
		
		Connection con = DBConnection.connection();
		
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
				
				return p;
			}
		}
		catch(SQLException e2){
			e2.printStackTrace();
			return null;
		}
		return null;	
	}
	
	public Person findByObject(Person p){
		
		return this.findByKey(p.getID());	
	}

	/*
	 * Notiz von Mert: Nochmal dr�ber schauen und ausgeben lassen in TestStart!
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


