package de.hdm.ITProjekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.ITProjekt.shared.bo.Organisationseinheit;
import de.hdm.ITProjekt.shared.bo.Person;
import de.hdm.ITProjekt.shared.bo.Projektmarktplatz;

public class OrganisationseinheitMapper {


	
	
	private static OrganisationseinheitMapper orgMapper = null;
	
	protected OrganisationseinheitMapper(){
		
	}
	
	public static OrganisationseinheitMapper orgMapper(){
		if(orgMapper == null){
			orgMapper = new OrganisationseinheitMapper();
		}
		return orgMapper;
	}
	
	
	public Organisationseinheit findByKey(int id){
		Connection con = DBConnection.connection();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT ID, vorname, nachname, straße, hausnummer, plz, ort FROM Organisationseinheit "
          + "WHERE ID=" + id);
			
			if(rs.next()){
				Organisationseinheit o = new Organisationseinheit();
				o.setID(rs.getInt("ID"));
				o.setVorname(rs.getString("vorname"));
				o.setNachname(rs.getString("nachname"));
				return o;
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
	
	
	
	
}
