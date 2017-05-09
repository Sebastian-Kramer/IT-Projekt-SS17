package de.hdm.ITProjekt.server.db;


import de.hdm.ITProjekt.shared.bo.Projekt;
import de.hdm.ITProjekt.shared.bo.Projektmarktplatz;
import de.hdm.ITProjekt.server.db.DBConnection;
import java.sql.*;
import java.util.Vector;

public class ProjektMapper {
	
	private static ProjektMapper pMapper = null;
	
	protected ProjektMapper(){
		
	}
	
	public static ProjektMapper pMapper(){
		if(pMapper == null){
			pMapper = new ProjektMapper();
		}
		return pMapper;
	}
	
	public Projekt findByKey(int id){
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
	
	public Vector<Projekt> getAll(){
		
		 Connection con = DBConnection.connection();
		 
		 Vector<Projekt> result = new Vector<Projekt>();
		 
		  try {
		      Statement stmt = con.createStatement();

		      ResultSet rs = stmt.executeQuery("SELECT ID, name, beschreibung, startdatum, enddatum FROM Projektmarktplatz");
		  
		  while (rs.next()) {
			  	Projekt p = new Projekt();
			  	p.setID(rs.getInt("ID"));
				p.setName(rs.getString("name"));
				p.setBeschreibung(rs.getString("beschreibung"));
				p.setStartdatum(rs.getDate("startdatum"));
				p.setEnddatum(rs.getDate("enddatum"));
			  
			  result.addElement(p);
		  }
		}
		    catch (SQLException e2) {
		        e2.printStackTrace();
		      }
		  return result;
	}
	
	public Projekt addProjekt(Projekt pmp){
		
		Connection con = DBConnection.connection();
		
		try {
		      Statement stmt = con.createStatement();
		      
		      ResultSet rs = stmt.executeQuery("SELECT MAX(ID) AS maxid "
		              + "FROM Projekt ");
		      
		
		      if(rs.next()){
		    	  
		    	  	pmp.setID(rs.getInt("maxid") + 1);
		    	  
		    	  	stmt = con.createStatement();
		    	  	
		    		stmt.executeUpdate("INSERT INTO Projekt (ID , name, beschreibung, startdatum, enddatum)" 
		    		+ "VALUES (" + pmp.getID() + "," + "'" + pmp.getName() + "'" + "," + "'" + pmp.getBeschreibung() 
		    		+ "'" + pmp.getStartdatum()  +  pmp.getEnddatum() +")"); 
		    	  
		      }
		}
		catch(SQLException e2){
			e2.printStackTrace();
		}
		return pmp;
		
	}
}


