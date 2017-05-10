package de.hdm.ITProjekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.ITProjekt.shared.bo.Person;


public class PersonMapper {

	
	
	private static PersonMapper perMapper = null;
	
	protected PersonMapper(){
		
	}
	
	public static PersonMapper perMapper(){
		if(perMapper == null){
			perMapper = new PersonMapper();
		}
		return perMapper;
	}
	
	
	public Person findByKey(int id){
		Connection con = DBConnection.connection();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT ID, anrede, vorname, nachname, anschrift FROM organisationseinheit "
          + "WHERE ID=" + id);
			
			if(rs.next()){
				Person p = new Person();
				p.setID(rs.getInt("ID"));
				p.setAnrede(rs.getString("anrede"));
				p.setVorname(rs.getString("vorname"));
				p.setNachname(rs.getString("nachname"));
				p.setAnschrift(rs.getString("anschrift"));
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
	 * Notiz von Mert: Nochmal drüber schauen und ausgeben lassen in TestStart!
	 */
	
	public Vector<Person> getAll(){
		
		 Connection con = DBConnection.connection();
		 
		 Vector<Person> result = new Vector<Person>();
		 
		  try {
		      Statement stmt = con.createStatement();

		      ResultSet rs = stmt.executeQuery("SELECT * FROM organisationseinheit");
		  
		  while (rs.next()) {
			  Person p = new Person();
			  p.setID(rs.getInt("ID"));
			  p.setAnrede(rs.getString("anrede"));
			  p.setVorname(rs.getString("vorname"));
			  p.setNachname(rs.getString("nachname"));
			  p.setAnschrift(rs.getString("anschrift"));
			  result.add(p);
		  }
		}
		    catch (SQLException e2) {
		        e2.printStackTrace();
		      }
		  return result;
	}

	public void createPerson(Person person1){
		
		Connection con = DBConnection.connection();
		
		try {
		      Statement stmt = con.createStatement();
		      
		      ResultSet rs = stmt.executeQuery("SELECT MAX(ID) AS maxid "
		              + "FROM organisationseinheit ");
		      
		
		      if(rs.next()){
		    	  
		    	  	person1.setID(rs.getInt("maxid") + 1);
		    	  
		    	  	stmt = con.createStatement();
		    	  	
		    		stmt.executeUpdate("INSERT INTO organisationseinheit (ID , vorname, nachname, anrede, anschrift)" + "VALUES "
		    				+ "("+  person1.getID()+ "," + "'" + person1.getVorname() + "'" + "," + "'" + 
		    				person1.getNachname() + "'" + "," + "'"+person1.getAnrede()+"'"+"," + "'" + person1.getAnschrift()+ "'" +")");
		    		
		    		
		    	  
		      }
		}
		catch(SQLException e2){
			e2.printStackTrace();
		}
	}

	public Person updatePerson(Person person1) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      stmt.executeUpdate("UPDATE organisationseinheit SET " + "vorname=\""
	          + person1.getVorname() + "\", " + "nachname=\""
	          + person1.getNachname() + "\", " + "anrede=\"" + person1.getAnrede()+ "\", " + "anschrift=\"" + person1.getAnschrift()
	          + "\" " + "WHERE id=" + person1.getID());
	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }

	    return person1;
	  }
	
	
	public void deletePerson(Person person1) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();
	      stmt.executeUpdate("DELETE FROM organisationseinheit " + "WHERE ID=" + person1.getID());

	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }
	
	  
	

	

	
	}
}


