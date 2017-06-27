package de.hdm.ITProjekt.server.db;

import de.hdm.ITProjekt.shared.bo.Projekt;
import de.hdm.ITProjekt.shared.bo.Projektmarktplatz;
import de.hdm.ITProjekt.server.db.DBConnection;
import java.sql.*;
import java.util.Vector;
import java.text.SimpleDateFormat;

public class ProjektMapper {
	
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	private static ProjektMapper pMapper = null;
	
	protected ProjektMapper(){
		
	}
	
	public static ProjektMapper pMapper(){
		if(pMapper == null){
			pMapper = new ProjektMapper();
		}
		return pMapper;
	}
	
	public Projekt getProjektByID(int id){
		Connection con = DBConnection.connection();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT ID, name, beschreibung, startdatum, enddatum FROM Projekt "
          + "WHERE ID=" + id + " ORDER BY ID");
			
			if(rs.next()){
				Projekt p = new Projekt();
				p.setID(rs.getInt("ID"));
				p.setName(rs.getString("name"));
				p.setBeschreibung(rs.getString("beschreibung"));
				p.setStartdatum(rs.getDate("startdatum"));
				p.setEnddatum(rs.getDate("enddatum"));
				return p;
			}
		}
		catch(SQLException e2){
			e2.printStackTrace();
			return null;
		}
		return null;	
	}
	
	public Vector <Projekt> getProjektById(Integer id){
		Connection con = DBConnection.connection();
		Vector <Projekt> result = new Vector<Projekt>();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT ID, name, beschreibung, startdatum, enddatum FROM Projekt "
          + "WHERE ID=" + id);
			
			if(rs.next()){
				Projekt p = new Projekt();
				p.setID(rs.getInt("ID"));
				p.setName(rs.getString("name"));
				p.setBeschreibung(rs.getString("beschreibung"));
				p.setStartdatum(rs.getDate("startdatum"));
				p.setEnddatum(rs.getDate("enddatum"));
				
				result.addElement(p);
				
			}
		}
		catch(SQLException e2){
			e2.printStackTrace();
			return null;
		}
		return result;	
		
	}
		
	public Vector<Projekt> findByProjektmarktplatz(int projektmarktplatzID){
		Connection con = DBConnection.connection();
		Vector <Projekt> result = new Vector<Projekt>();
		

		 try {
		      Statement stmt = con.createStatement();


		      ResultSet rs = stmt.executeQuery("SELECT ID, name, beschreibung, startdatum, enddatum, Projektmarktplatz_ID, Projektleiter_ID FROM projekt WHERE Projektmarktplatz_ID= " + projektmarktplatzID);
		  
		  
		      
		      while (rs.next()) {

			  	Projekt p = new Projekt();
			  	p.setID(rs.getInt("ID"));
				p.setName(rs.getString("name"));
				p.setBeschreibung(rs.getString("beschreibung"));
				p.setStartdatum(rs.getDate("startdatum"));
				p.setEnddatum(rs.getDate("enddatum"));
				p.setProjektmarktplatz_ID(rs.getInt("Projektmarktplatz_ID"));
				p.setProjektleiter_ID(rs.getInt("Projektleiter_ID"));

			  result.addElement(p);
		  
		      }
		  	}
		 
		catch (SQLException e2) {
		      e2.printStackTrace();
		    } 
		 return result;
	  }
	public Vector<Projekt> findByProjektmarktplatz(Projektmarktplatz projektmarktplatz) {

	    /*
	     * Wir lesen einfach die Kundennummer (Primärschlüssel) des Customer-Objekts
	     * aus und delegieren die weitere Bearbeitung an findByOwner(int ownerID).
	     */
		
	    return findByProjektmarktplatz(projektmarktplatz.getID());
	  }
	
	public Vector<Projekt>getAllProjekteByProjektleiter(int personId){
		Connection con = DBConnection.connection();
		Vector <Projekt> result = new Vector<Projekt>();
		

		 try {
		      Statement stmt = con.createStatement();


		      ResultSet rs = stmt.executeQuery("SELECT ID, name, beschreibung, startdatum, enddatum, Projektmarktplatz_ID, Projektleiter_ID FROM projekt WHERE Projektleiter_ID= " + personId);
		      
		      while(rs.next()){
		    	  Projekt p = new Projekt();
				  	p.setID(rs.getInt("ID"));
					p.setName(rs.getString("name"));
					p.setBeschreibung(rs.getString("beschreibung"));
					p.setStartdatum(rs.getDate("startdatum"));
					p.setEnddatum(rs.getDate("enddatum"));
					p.setProjektmarktplatz_ID(rs.getInt("Projektmarktplatz_ID"));
					p.setProjektleiter_ID(rs.getInt("Projektleiter_ID"));
					
					result.addElement(p);
		      }
		 }
		 catch (SQLException e2) {
		      e2.printStackTrace();
		 }
		 return result;
		
	}
	
	
	
	
	public Vector<Projekt> getAllProjekte(){
		
		 Connection con = DBConnection.connection();
		 
		 Vector<Projekt> result = new Vector<Projekt>();
		 
		  try {
		      Statement stmt = con.createStatement();

		      ResultSet rs = stmt.executeQuery("SELECT ID, name, beschreibung, startdatum, enddatum, Projektmarktplatz_ID, Projektleiter_ID FROM Projekt");
		  
		  while (rs.next()) {
			  	Projekt p = new Projekt();
			  	p.setID(rs.getInt("ID"));
				p.setName(rs.getString("name"));
				p.setBeschreibung(rs.getString("beschreibung"));
				p.setStartdatum(rs.getDate("startdatum"));
				p.setEnddatum(rs.getDate("enddatum"));
				p.setProjektmarktplatz_ID(rs.getInt("Projektmarktplatz_ID"));
				p.setProjektleiter_ID(rs.getInt("Projektleiter_ID"));
			  
			  result.addElement(p);
		  }
		}
		    catch (SQLException e2) {
		        e2.printStackTrace();
		      }
		  return result;
	}
	
	
	
	public Projekt addProjekt(Projekt p){
		
		Connection con = DBConnection.connection();
		
		try {
		      Statement stmt = con.createStatement();
		      
		      ResultSet rs = stmt.executeQuery("SELECT MAX(ID) AS maxid FROM Projekt");
		     	
		      if(rs.next()){
		    	  
		    	  	p.setID(rs.getInt("maxid") + 1);
		    	  
		    	  	stmt = con.createStatement();
		    	  	
		    		stmt.executeUpdate("INSERT INTO Projekt (ID, name, beschreibung, startdatum, enddatum, Projektmarktplatz_ID, Projektleiter_ID)" 
		    		+ "VALUES (" + p.getID() + "," + "'" + p.getName() + "'" + "," + "'" + p.getBeschreibung() 
		    		+ "'" + "," + "'" + format.format(p.getStartdatum()) + "'" 
    				+ "," + "'" + format.format(p.getEnddatum()) + "'" 
    				+ ", " + p.getProjektmarktplatz_ID() + ", " + p.getProjektleiter_ID() + ")"); 
		    	  
		      }
		}
		catch(SQLException e2){
			e2.printStackTrace();
		}
		return p;
		
	}
	
	public void deleteProjekt(Projekt a){
		
		Connection con = DBConnection.connection();
		
		try {
		      Statement stmt = con.createStatement();

		      stmt.executeUpdate("DELETE FROM Projekt " 
		    		  			+ "WHERE Projekt.ID = " + a.getID());
			}
		
		catch (SQLException e2) {
				e2.printStackTrace();
			}
		
		}
	
	public Projekt update(Projekt c) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();
	      
	      if(c.getProjektleiter_ID()==null && c.getProjektmarktplatz_ID()==null){
	    	  stmt.executeUpdate("UPDATE Projekt " + "SET name='"
	    	          + c.getName() + "', beschreibung='" + c.getBeschreibung() +   "', Projektmarktplatz_ID = NULL, Projektleiter_ID = NULL "
	    	          + "WHERE Projekt.ID=" + c.getID());
	      }else if(c.getProjektleiter_ID()!= null && c.getProjektmarktplatz_ID()==null){
	    	  stmt.executeUpdate("UPDATE Projekt " + "SET name='"
	    	          + c.getName() + "', beschreibung='" + c.getBeschreibung() + "', startdatum='" 
	    	          + format.format(c.getStartdatum()) + "', enddatum= '" 
	    	          + format.format(c.getEnddatum()) +  "',Projektmarktplatz_ID=NULL"  + "', Projektleiter_ID='" + + c.getProjektleiter_ID()
	    	          + "WHERE Projekt.ID=" + c.getID());
	      }else if(c.getProjektleiter_ID()== null && c.getProjektmarktplatz_ID()!=null){
	    	  stmt.executeUpdate("UPDATE Projekt " + "SET name='"
	    	          + c.getName() + "', beschreibung='" + c.getBeschreibung() + "', enddatum='" 
	    	          + format.format(c.getEnddatum()) + "', Projektmarktplatz_ID='" + c.getProjektmarktplatz_ID() + "', Projektleiter_ID = NULL "
	    	          + "WHERE Projekt.ID=" + c.getID());
	      }else if(c.getProjektleiter_ID()!=null && c.getProjektmarktplatz_ID()!=null){
	      stmt.executeUpdate("UPDATE Projekt " + "SET name='"
	          + c.getName() + "', beschreibung='" + c.getBeschreibung() + "', startdatum='" 
	          + format.format(c.getStartdatum()) + "', enddatum= '" 
	          + format.format(c.getEnddatum()) + c.getProjektmarktplatz_ID() + "', Projektmarktplatz_ID='" 
	          + c.getProjektleiter_ID() + "', Projektleiter_ID='" 
	          + "WHERE Projekt.ID=" + c.getID());
	      }
	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	    }

	    return c;
	  }
}


