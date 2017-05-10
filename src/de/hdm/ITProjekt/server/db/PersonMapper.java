package de.hdm.ITProjekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.ITProjekt.shared.bo.Person;
import de.hdm.ITProjekt.shared.bo.Projektmarktplatz;

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
			ResultSet rs = stmt.executeQuery("SELECT ID, anrede, vorname, nachname FROM organisationseinheit "
          + "WHERE ID=" + id);
			
			if(rs.next()){
				Person p = new Person();
				p.setID(rs.getInt("ID"));
				p.setAnrede(rs.getString("anrede"));
				p.setVorname(rs.getString("vorname"));
				p.setNachname(rs.getString("nachname"));
				return p;
			}
		}
		catch(SQLException e2){
			e2.printStackTrace();
			return null;
		}
		return null;	
	}
	

	public Vector<Person> getAll(){
		
		 Connection con = DBConnection.connection();
		 
		 Vector<Person> result = new Vector<Person>();
		 
		  try {
		      Statement stmt = con.createStatement();

		      ResultSet rs = stmt.executeQuery("SELECT ID, anrede, vorname, nachname FROM organisationseinheit");
		  
		  while (rs.next()) {
			  Person p = new Person();
			  p.setID(rs.getInt("ID"));
			  p.setAnrede(rs.getString("bez"));
			  
			  result.addElement(p);
		  }
		}
		    catch (SQLException e2) {
		        e2.printStackTrace();
		      }
		  return result;
	}
	
	
	public Projektmarktplatz addMarktplatz(Projektmarktplatz pmp){
		
		Connection con = DBConnection.connection();
		
		try {
		      Statement stmt = con.createStatement();
		      
		      ResultSet rs = stmt.executeQuery("SELECT MAX(ID) AS maxid "
		              + "FROM Projektmarktplatz ");
		      
		
		      if(rs.next()){
		    	  
		    	  	pmp.setID(rs.getInt("maxid") + 1);
		    	  
		    	  	stmt = con.createStatement();
		    	  	
		    		stmt.executeUpdate("INSERT INTO Projektmarktplatz (ID , bez)" + "VALUES (" 
		    		+ pmp.getID() + "," + "'" + pmp.getBez() + "'" +")");
		    	  
		      }
		}
		catch(SQLException e2){
			e2.printStackTrace();
		}
		return pmp;
		
	}
	
	
	public Projektmarktplatz updateMarktplatz(Projektmarktplatz p){
		
		Connection con = DBConnection.connection();
		
		try{
			
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate(" UPDATE Projektmarktplatz " + "SET bez =\""
								+ p.getBez() + "\" " + "WHERE ID= " + p.getID());
		
			}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
			
		return p;
	}
	public Projektmarktplatz deleteMarktplatz(Projektmarktplatz p){
		
		Connection con = DBConnection.connection();
	
		try{
			
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate(" DELETE FROM Projektmarktplatz " +  "WHERE ID= " + p.getID());
		
			}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
			
		return p;
	}
	

}
