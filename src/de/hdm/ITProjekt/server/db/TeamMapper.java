package de.hdm.ITProjekt.server.db;

import de.hdm.ITProjekt.shared.bo.Team;
import de.hdm.ITProjekt.server.db.DBConnection;
import java.sql.*;
import java.util.Vector;
import de.hdm.ITProjekt.shared.bo.Organisationseinheit;

public class TeamMapper extends OrganisationseinheitMapper{

private static TeamMapper tMapper = null;
	
	protected TeamMapper(){
		
	};
	
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

public Team findByObject(Team t){
	return this.findByKey(t.getID());
	  
  }

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
public void deleteTeam(Integer t){
	Team team = new Team();
	team.setID(t);
	Connection con = DBConnection.connection();
	
	try {
	      Statement stmt = con.createStatement();

	      stmt.executeUpdate("DELETE FROM Team " 
	    		  			+ "WHERE ID = " + t);
	      super.delete(team);

		}
	
	catch (SQLException e2) {
			e2.printStackTrace();
		}
	
}
public void deleteTeam(Team p1){
	this.deleteTeam(p1.getID());
	}



public Team update(Team t) {
    Connection con = DBConnection.connection();

    try {
    	t.setID(super.update(t));
    	
    	super.orgMapper().update(t);
    	
    	Statement stmt = con.createStatement();

      if(t.getUN_ID() == null){
    	  stmt.executeUpdate("UPDATE Team SET name='"
    	          + t.getName() +"'" +  "WHERE ID = " + t.getID());
    	  
      }else if (t.getUN_ID() != null){
    	  stmt.executeUpdate("UPDATE Team SET name='"
    	          + t.getName() +"'" + ", UN_ID = " + t.getUN_ID() + " WHERE ID = " + t.getID());
    	  
      }
      
    }
    catch (SQLException e) {
      e.printStackTrace();
    }

    return t;
  }
}
