package de.hdm.ITProjekt.server.db;

import de.hdm.ITProjekt.shared.bo.Team;
import de.hdm.ITProjekt.server.db.DBConnection;
import java.sql.*;
import java.util.Vector;

public class TeamMapper {

private static TeamMapper tMapper = null;
	
	protected TeamMapper(){
		
	}
	
	public static TeamMapper tMapper(){
		if(tMapper == null){
			tMapper = new TeamMapper();
		}
		return tMapper;
	}
	
public Team findByKey(int id){
		
		Connection con = DBConnection.connection();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT ID, name, anzahlMitglieder FROM Team "
          + "WHERE ID=" + id);
			
			if(rs.next()){
				Team t = new Team();
				t.setID(rs.getInt("ID"));
				t.setName(rs.getString("name"));
				
				
				return t;
			}
		}
		catch(SQLException e2){
			e2.printStackTrace();
			return null;
		}
		return null;	
	}

public Vector<Team> getAll(){
	
	 Connection con = DBConnection.connection();	 
	
	 Vector<Team> result = new Vector<Team>();
	 
	  try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("SELECT ID, name, FROM Team ");
	  
	  while (rs.next()) {
		  	Team t = new Team();
			t.setID(rs.getInt("ID"));
			t.setName(rs.getString("name"));
			
		  
		  result.addElement(t);
	  }
	}
	    catch (SQLException e2) {
	        e2.printStackTrace();
	      }
	  return result;
}

public Team insert(Team p1){
	
	Connection con = DBConnection.connection();
	
	try {
	      Statement stmt = con.createStatement();
	      
	      ResultSet rs = stmt.executeQuery("SELECT MAX(ID) AS maxid "
	              + "FROM Team ");
	      
	
	      if(rs.next()){
	    	  
	    	  	p1.setID(rs.getInt("maxid") + 1);
	   	  
	    	  	stmt = con.createStatement();
	    	  	
	    		stmt.executeUpdate("INSERT INTO Team (ID, name, anzahlMitglieder)" 
	    				+ "VALUES (" + p1.getID() + ", " + "'" + p1.getName() + "'" 
	    				+")"); 
	    	  
	      }
	}
	catch(SQLException e2){
		e2.printStackTrace();
	}
	return p1;		
}

public void delete(Team p1){
	
	Connection con = DBConnection.connection();
	
	try {
	      Statement stmt = con.createStatement();

	      stmt.executeUpdate("DELETE FROM Team " 
	    		  			+ "WHERE Team.ID = " + p1.getID());

		}
	
	catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

public Team update(Team c) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("UPDATE Team " + "SET name='"
          + c.getName() + "SET anzahlMitglieder='"
    	  + "' WHERE Team.ID = " + c.getID());

    }
    catch (SQLException e) {
      e.printStackTrace();
    }

    return c;
  }
}
