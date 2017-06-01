package de.hdm.ITProjekt.server.db;
import de.hdm.ITProjekt.shared.bo.Projektmarktplatz;

import de.hdm.ITProjekt.server.db.DBConnection;
import java.sql.*;
import java.util.Vector;



public class ProjektmarktplatzMapper {
	
	
	private static ProjektmarktplatzMapper pmpMapper = null;
	
	protected ProjektmarktplatzMapper(){
		
	}
	
	public static ProjektmarktplatzMapper pmpMapper(){
		if(pmpMapper == null){
			pmpMapper = new ProjektmarktplatzMapper();
		}
		return pmpMapper;
	}
	
	
	public Projektmarktplatz findByKey(int id){
		Connection con = DBConnection.connection();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT ID, bez FROM Projektmarktplatz "
          + "WHERE ID=" + id + " ORDER BY bez");
			
			if(rs.next()){
				Projektmarktplatz p = new Projektmarktplatz();
				p.setID(rs.getInt("ID"));
				p.setBez(rs.getString("bez"));
				return p;
			}
		}
		catch(SQLException e2){
			e2.printStackTrace();
			return null;
		}
		return null;	
	}
	
	
	public Vector<Projektmarktplatz> getAll(){
		
		 Connection con = DBConnection.connection();
		 
		 Vector<Projektmarktplatz> result = new Vector<Projektmarktplatz>();
		 
		  try {
		      Statement stmt = con.createStatement();

		      ResultSet rs = stmt.executeQuery("SELECT ID, bez FROM Projektmarktplatz");
		  
		  while (rs.next()) {
			  Projektmarktplatz p = new Projektmarktplatz();
			  p.setID(rs.getInt("ID"));
			  p.setBez(rs.getString("bez"));
			  
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

	public Projektmarktplatz findByBez(String bez){
		Connection con = DBConnection.connection();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT ID, bez FROM Projektmarktplatz "
          + "WHERE bez=" + bez);
			
			if(rs.next()){
				Projektmarktplatz p = new Projektmarktplatz();
				p.setID(rs.getInt("ID"));
				p.setBez(rs.getString("bez"));
				return p;
			}
		}
		catch(SQLException e2){
			e2.printStackTrace();
			return null;
		}
		return null;	
	}
}
	