package de.hdm.ITProjekt.server.db;

import de.hdm.ITProjekt.shared.bo.Beteiligung;
import de.hdm.ITProjekt.server.db.DBConnection;
import java.sql.*;
import java.util.Vector;
import java.text.SimpleDateFormat;

public class BeteiligungMapper {
	
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	private static BeteiligungMapper bMapper = null;
	
	protected BeteiligungMapper(){
		
	}
	
	public static BeteiligungMapper bMapper(){
		if(bMapper == null){
			bMapper = new BeteiligungMapper();
		}
		return bMapper;
	}
	public Beteiligung findByKey(int id){
		
		Connection con = DBConnection.connection();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT ID, umfang, startdatum, enddatum, Projekt_ID, Orga_ID FROM Beteiligung "
          + "WHERE ID=" + id + " ORDER BY ID");
			
			if(rs.next()){
				Beteiligung p = new Beteiligung();
				p.setID(rs.getInt("ID"));
				p.setUmfang(rs.getString("umfang"));
				p.setStartdatum(rs.getDate("startdatum"));
				p.setEnddatum(rs.getDate("enddatum"));
				p.setProjekt_ID(rs.getInt("Projekt_ID"));
				p.setOrga_ID(rs.getInt("Orga_ID"));
				return p;
			}
		}
		catch(SQLException e2){
			e2.printStackTrace();
			return null;
		}
		return null;	
	}
	
//	public Vector<Beteiligung> getAllProjektbeteiligungen(int beteiligungid){
//		Connection con = DBConnection.connection();	 
//		
//		 Vector<Beteiligung> result = new Vector<Beteiligung>();
//		 
//		  try {
//		      Statement stmt = con.createStatement();
//
//		      ResultSet rs = stmt.executeQuery("SELECT Beteiligung.ID, Beteiligung.umfang, Beteiligung.startdatum, Beteiligung.enddatum, Beteiligung.Projekt_ID, "
//		      										+ "Beteiligung.Orga_ID, projekt.name FROM Beteiligung INNER JOIN projekt On beteiligung.Projekt_ID=projekt.ID");
//		      while(rs.next()){
//		    	  Beteiligung b = new Beteiligung();
//		    	  
//		      }
//	}
	
	public Vector<Beteiligung> getAll(){
		
		 Connection con = DBConnection.connection();	 
		
		 Vector<Beteiligung> result = new Vector<Beteiligung>();
		 
		  try {
		      Statement stmt = con.createStatement();

		      ResultSet rs = stmt.executeQuery("SELECT ID, umfang, startdatum, enddatum, Projekt_ID, Orga_ID FROM Beteiligung");
		  
		  while (rs.next()) {
			  	Beteiligung p = new Beteiligung();
				p.setID(rs.getInt("ID"));
				p.setUmfang(rs.getString("umfang"));
				p.setStartdatum(rs.getDate("startdatum"));
				p.setEnddatum(rs.getDate("enddatum"));
				p.setProjekt_ID(rs.getInt("Projekt_ID"));
				p.setOrga_ID(rs.getInt("Orga_ID"));
			  
			  result.addElement(p);
		  }
		}
		    catch (SQLException e2) {
		        e2.printStackTrace();
		      }
		  return result;
	}
	
	public Vector <Beteiligung> getBeteiligungByProjekt(int projektid){
		 Connection con = DBConnection.connection();	 
			
		 Vector<Beteiligung> result = new Vector<Beteiligung>();
		 
		 try{
			 Statement stmt = con.createStatement();

		      ResultSet rs = stmt.executeQuery("SELECT ID, umfang, startdatum, enddatum, Projekt_ID, Orga_ID FROM Beteiligung WHERE Projekt_ID=" + projektid); 
		      
		      while(rs.next()){
		    	  Beteiligung p = new Beteiligung();
					p.setID(rs.getInt("ID"));
					p.setUmfang(rs.getString("umfang"));
					p.setStartdatum(rs.getDate("startdatum"));
					p.setEnddatum(rs.getDate("enddatum"));
					p.setProjekt_ID(rs.getInt("Projekt_ID"));
					p.setOrga_ID(rs.getInt("Orga_ID"));
				  
				  result.addElement(p);
		      }
		 }
		 catch (SQLException e2) {
		        e2.printStackTrace();
		 }
		 return result;
	}
	
	
	public Beteiligung insert(Beteiligung a){
		
		Connection con = DBConnection.connection();
		
		try {
		      Statement stmt = con.createStatement();
		      
		      ResultSet rs = stmt.executeQuery("SELECT MAX(ID) AS maxid "
		              + "FROM Beteiligung ");
		      
		
		      if(rs.next()){
		    	  
		    	  	a.setID(rs.getInt("maxid") + 1);
		   	  
		    	  	stmt = con.createStatement();
		    	  	
		    		stmt.executeUpdate("INSERT INTO Beteiligung (ID, umfang, startdatum, enddatum, Projekt_ID, Orga_ID)" 
		    				+ "VALUES (" + a.getID() + ", " + "'" + a.getUmfang() + "'" 
		    				+ ", " + "'" + format.format(a.getStartdatum()) + "'" 
		    				+ ", " + "'" + format.format(a.getEnddatum()) + "'" 
		    				+ ", " + a.getProjekt_ID() + ", " + a.getOrga_ID()  +")"); 
		    	  
		      }
		}
		catch(SQLException e2){
			e2.printStackTrace();
		}
		return a;		
	}
	
	
	public void delete(Beteiligung a){
		
		Connection con = DBConnection.connection();
		
		try {
		      Statement stmt = con.createStatement();

		      stmt.executeUpdate("DELETE FROM Beteiligung " 
		    		  			+ "WHERE Beteiligung.ID = " + a.getID());

			}
		
		catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	
	public Beteiligung update(Beteiligung c) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();
	      if(c.getProjekt_ID()==null && c.getOrga_ID()== null){
	    	  stmt.executeUpdate("UPDATE Beteiligung " + "SET umfang='"
	    	          + c.getUmfang() + "', enddatum= '" + format.format(c.getEnddatum())
	    	    		  + "', Projektmarktplatz_ID = NULL, Projektleiter_ID = NULL " + "' WHERE Beteiligung.ID = " + c.getID());
	      }else{
	      stmt.executeUpdate("UPDATE Beteiligung " + "SET umfang='"
	          + c.getUmfang() + "', enddatum= '" + format.format(c.getEnddatum())
	    		  + "' WHERE Beteiligung.ID = " + c.getID());
	      }
	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	    }

	    return c;
	  }
	
}
