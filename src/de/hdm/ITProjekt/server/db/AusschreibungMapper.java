package de.hdm.ITProjekt.server.db;

import de.hdm.ITProjekt.shared.bo.Ausschreibung;
import de.hdm.ITProjekt.shared.bo.Projekt;
import de.hdm.ITProjekt.server.db.DBConnection;
import java.sql.*;
import java.util.Vector;
import java.text.SimpleDateFormat;

public class AusschreibungMapper {
	
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		
		private static AusschreibungMapper aMapper = null;
		
		protected AusschreibungMapper(){
			
		}
		
		public static AusschreibungMapper aMapper(){
			if(aMapper == null){
				aMapper = new AusschreibungMapper();
			}
			return aMapper;
		}
		
		//Ausschreibung anhand der übergebenen ID wird zurückgegeben
		public Ausschreibung findByKey(int id){
			
			Connection con = DBConnection.connection();
			
			try{
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT ID, ausschreibungstext, bezeichnung, datum, Projekt_ID, Orga_ID FROM Ausschreibung "
	          + "WHERE ID=" + id + " ORDER BY ID");
				
				if(rs.next()){
					Ausschreibung p = new Ausschreibung();
					p.setID(rs.getInt("ID"));
					p.setAusschreibungstext(rs.getString("ausschreibungstext"));
					p.setBezeichnung(rs.getString("bezeichnung"));
					p.setDatum(rs.getDate("datum"));
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
		
		//Alle Auschreibungen eines Projekts sollen ausgegeben werden
		 public Vector<Ausschreibung> getAlLAuscchreibungenBy(int projektId){
				
			  Connection con = DBConnection.connection();
			  Vector<Ausschreibung> result = new Vector<Ausschreibung>();
			  
			  try {
				
				  Statement stmt = con.createStatement();
				  ResultSet rs = stmt.executeQuery("SELECT ID, ausschreibungstext, bezeichnung, datum, Projekt_ID FROM Ausschreibung "
				  		+ "WHERE Projekt_Id=" + projektId + " ORDER BY Bezeichnung");
				  
				  while (rs.next()) {
					Ausschreibung a = new Ausschreibung();
					
				 	a.setID(rs.getInt("ID"));
					a.setAusschreibungstext(rs.getString("ausschreibungstext"));
					a.setBezeichnung(rs.getString("bezeichnung"));
					a.setDatum(rs.getDate("datum"));
					a.setProjekt_ID(rs.getInt("Projekt_ID"));
					
					result.add(a);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			  return result;
		  }
		
		
		//Alle Auschreibungen aus der Datenbank werden ausgegeben
		public Vector<Ausschreibung> getAll(){
			
			 Connection con = DBConnection.connection();
			 
			
			 Vector<Ausschreibung> result = new Vector<Ausschreibung>();
			 
			  try {
			      Statement stmt = con.createStatement();

			      ResultSet rs = stmt.executeQuery("SELECT ID, ausschreibungstext, bezeichnung, datum, Projekt_ID, Orga_ID FROM Ausschreibung");
			  
			  while (rs.next()) {
				  	Ausschreibung p = new Ausschreibung();
				  	p.setID(rs.getInt("ID"));
					p.setAusschreibungstext(rs.getString("ausschreibungstext"));
					p.setBezeichnung(rs.getString("bezeichnung"));
					p.setDatum(rs.getDate("datum"));
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
		
		
		public Ausschreibung addAusschreibung(Ausschreibung a){
			
			Connection con = DBConnection.connection();
			
			try {
			      Statement stmt = con.createStatement();
			      
			      ResultSet rs = stmt.executeQuery("SELECT MAX(ID) AS maxid "
			              + "FROM Ausschreibung ");
			      
			
			      if(rs.next()){
			    	  
			    	  	a.setID(rs.getInt("maxid") + 1);
			   	  
			    	  	stmt = con.createStatement();
			    	  	
			    		stmt.executeUpdate("INSERT INTO Ausschreibung (ID , ausschreibungstext, bezeichnung, datum, Projekt_ID, Orga_ID)" 
			    		+ "VALUES (" + a.getID() + ", " + "'" + a.getAusschreibungstext() + "'" + ", " + "'" + a.getBezeichnung() 
			    		+ "'" + ", " + "'" + date.format(a.getDatum()) +"'"+ ", " + a.getProjekt_ID() + ", " +a.getOrga_ID()  +")"); 
			    	  
			      }
			}
			catch(SQLException e2){
				e2.printStackTrace();
			}
			return a;		
		}
		
		
		public void deleteAusschreibung(Ausschreibung a){
			
			Connection con = DBConnection.connection();
			
			try {
			      Statement stmt = con.createStatement();

			      stmt.executeUpdate("DELETE FROM Ausschreibung " 
			    		  			+ "WHERE Ausschreibung.ID = " + a.getID());

				}
			
			catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		
		public Ausschreibung update(Ausschreibung c) {
		    Connection con = DBConnection.connection();

		    try {
		      Statement stmt = con.createStatement();

		      stmt.executeUpdate("UPDATE Ausschreibung " + "SET ausschreibungstext=\""
		          + c.getAusschreibungstext() + "\", " + "bezeichnung=\"" + c.getBezeichnung() + "\" "
		          + "WHERE Ausschreibung.ID=" + c.getID());

		    }
		    catch (SQLException e) {
		      e.printStackTrace();
		    }

		    return c;
		  }
		
		
		public Vector<Ausschreibung> findByProjekt(int projektID){
			Connection con = DBConnection.connection();
			Vector<Ausschreibung> result = new Vector<Ausschreibung>();
			
			try {
			      Statement stmt = con.createStatement();
			      
			      ResultSet rs = stmt.executeQuery("SELECT ID, ausschreibungstext, bezeichnung, datum, Projekt_ID, Orga_ID FROM Ausschreibung WHERE Projekt_ID = " + projektID );
			      
			      while(rs.next()){
			    	  Ausschreibung a = new Ausschreibung();
			    	  a.setID(rs.getInt("ID"));
			    	  a.setAusschreibungstext(rs.getString("ausschreibungstext"));
			    	  a.setBezeichnung(rs.getString("bezeichnung"));
			    	  a.setDatum(rs.getDate("datum"));
			    	  a.setProjekt_ID(rs.getInt("Projekt_ID"));
			    	  a.setOrga_ID(rs.getInt("ID"));
			    	  
			    	  result.addElement(a);
			    	  		
			      }
			}
			catch (SQLException e2) {
			      e2.printStackTrace();
			    }
			return result;
		}
		
		public Vector<Ausschreibung> findByProjekt(Projekt projekt){
			
			return findByProjekt(projekt.getID());
			
		}
		
}

