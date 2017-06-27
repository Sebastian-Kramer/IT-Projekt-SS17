package de.hdm.ITProjekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.text.SimpleDateFormat;

import de.hdm.ITProjekt.shared.bo.Ausschreibung;
import de.hdm.ITProjekt.shared.bo.Bewerbung;
import de.hdm.ITProjekt.shared.bo.Person;

public class BewerbungMapper {
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	private static BewerbungMapper bewMapper = null;
	
	protected BewerbungMapper(){
		
	}
	
	public static BewerbungMapper bewMapper(){
		if(bewMapper == null){
			bewMapper = new BewerbungMapper();
		}
		return bewMapper;
	}
	public Bewerbung findByKey(int id){
		
		Connection con = DBConnection.connection();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT ID, bewerbungstext, erstelldatum, Ausschreibungs_ID, Orga_ID FROM Bewerbung "
          + "WHERE ID=" + id + " ORDER BY ID");
			
			if(rs.next()){
				Bewerbung p = new Bewerbung();
				p.setID(rs.getInt("ID"));
				p.setBewerbungstext(rs.getString("bewerbungstext"));
				p.setErstelldatum(rs.getDate("erstelldatum"));
				p.setAusschreibungs_ID(rs.getInt("Ausschreibungs_ID"));
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
	
	public Vector<Bewerbung> getAll(){
		
		 Connection con = DBConnection.connection();	 
		
		 Vector<Bewerbung> result = new Vector<Bewerbung>();
		 
		  try {
		      Statement stmt = con.createStatement();

		      ResultSet rs = stmt.executeQuery("SELECT ID, bewerbungstext, erstelldatum, Ausschreibungs_ID, Orga_ID FROM Bewerbung");
		  
		  while (rs.next()) {
			  Bewerbung p = new Bewerbung();
				p.setID(rs.getInt("ID"));
				p.setBewerbungstext(rs.getString("bewerbungstext"));
				p.setErstelldatum(rs.getDate("erstelldatum"));
				p.setAusschreibungs_ID(rs.getInt("Ausschreibungs_ID"));
				p.setOrga_ID(rs.getInt("Orga_ID"));
				
			  
			  result.addElement(p);
		  }
		}
		    catch (SQLException e2) {
		        e2.printStackTrace();
		      }
		  return result;
	}
	
	
	public Bewerbung insert(Bewerbung a){
		
		Connection con = DBConnection.connection();
		
		try {
		      Statement stmt = con.createStatement();
		      
		      ResultSet rs = stmt.executeQuery("SELECT MAX(ID) AS maxid "
		              + "FROM Bewerbung ");
		      
		
		      if(rs.next()){
		    	  
		    	  	a.setID(rs.getInt("maxid") + 1);
		   	  
		    	  	stmt = con.createStatement();
		    	  	
		    		stmt.executeUpdate("INSERT INTO Bewerbung (ID, bewerbungstext, erstelldatum, status, Ausschreibungs_ID, Orga_ID)" 
		    				+ "VALUES (" + a.getID() + ", " + "'" + a.getBewerbungstext() + "'"  
		    				+ ", " + "'" + format.format(a.getErstelldatum()) + "'" + ", "  + "'" +a.getStatus() + "'"
		    				+ ", " + a.getAusschreibungs_ID() + ", " + a.getOrga_ID()  +")"); 
		    	  
		      }
		}
		catch(SQLException e2){
			e2.printStackTrace();
		}
		return a;		
	}
	
	
	public void delete(Bewerbung a){
		
		Connection con = DBConnection.connection();
		
		try {
		      Statement stmt = con.createStatement();

		      stmt.executeUpdate("DELETE FROM Bewerbung " 
		    		  			+ "WHERE Bewerbung.ID = " + a.getID());

			}
		
		catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	
	public Bewerbung updateBewerbungsstatus(Bewerbung b){
		Connection con = DBConnection.connection();
		
		try{
			Statement stmt = con.createStatement();
			stmt.executeUpdate("UPDATE Bewerbung " + "SET status= " + "'" + b.getStatus() + "'" 
			+  "WHERE ID = " +b.getID());
		}catch(SQLException e){
			e.printStackTrace();
		}
		return b;
	}
	
	public Bewerbung update(Bewerbung c) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();
	      if(c.getAusschreibungs_ID()==null && c.getOrga_ID()==null){
	    	  stmt.executeUpdate("UPDATE Bewerbung " + "SET bewerbungstext='" + c.getBewerbungstext() 
	    	  + "', Ausschreibungs_ID = NULL, Orga_ID = NULL " + " WHERE Bewerbung.ID = " + c.getID());
	      
	      }else{

	      stmt.executeUpdate("UPDATE Bewerbung " + "SET bewerbungstext='"
	          + c.getBewerbungstext() 
	    		  + "' WHERE Bewerbung.ID = " + c.getID());
	      }
	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	    }

	    return c;
	  }
	
	public Vector<Bewerbung> findByPerson (int Orga_ID){
		Connection con = DBConnection.connection();
		Vector<Bewerbung> result = new Vector<Bewerbung>();
		
		try {
		      Statement stmt = con.createStatement();
		      
		      ResultSet rs = stmt.executeQuery("SELECT ID, bewerbungstext, erstelldatum, Ausschreibungs_ID, Orga_ID FROM bewerbung WHERE Orga_ID = " + Orga_ID);
		      
		      while(rs.next()){
		    	  Bewerbung b = new Bewerbung();
		    	  b.setID(rs.getInt("ID"));
		    	  b.setBewerbungstext(rs.getString("bewerbungstext"));
		    	  b.setErstelldatum(rs.getDate("erstelldatum"));
		    	  b.setAusschreibungs_ID(rs.getInt("Ausschreibungs_ID"));
		    	  b.setOrga_ID(rs.getInt("Orga_ID"));
		    	  
		    	  result.addElement(b);
		    	  
		      }
		}
		catch (SQLException e2) {
		      e2.printStackTrace();
		}
		return result;
	}
	
	public Vector<Bewerbung> findByPerson(Person person){
		
		return findByPerson(person.getID());	
	}
	
	
	public Vector<Bewerbung> findByAusschreibung(int id){
		
		Connection con = DBConnection.connection();
		
		Vector<Bewerbung> result = new Vector<Bewerbung>();
		
		try {
		      Statement stmt = con.createStatement();
		      
		      ResultSet rs = stmt.executeQuery("SELECT ID, bewerbungstext, erstelldatum, Ausschreibungs_ID FROM Bewerbung WHERE Ausschreibungs_ID = " + id);
		
		      while(rs.next()){
		    	  Bewerbung b = new Bewerbung();
		    	  b.setID(rs.getInt("ID"));
		    	  b.setBewerbungstext(rs.getString("bewerbungstext"));
		    	  b.setErstelldatum(rs.getDate("erstelldatum"));
		    	  b.setAusschreibungs_ID(rs.getInt("Ausschreibungs_ID"));
//		    	  b.setOrga_ID(rs.getInt("Orga_ID"));
		    	  
		    	  result.addElement(b);
		      }
		}
		catch (SQLException e2) {
		      e2.printStackTrace();
		}
		return result;
		
		}
		
		
	}
	

