package de.hdm.ITProjekt.server.db;

import de.hdm.ITProjekt.shared.bo.Ausschreibung;
import de.hdm.ITProjekt.server.db.DBConnection;
import java.sql.*;
import java.util.Vector;

public class AusschreibungMapper {
	
		
		private static AusschreibungMapper aMapper = null;
		
		protected AusschreibungMapper(){
			
		}
		
		public static AusschreibungMapper aMapper(){
			if(aMapper == null){
				aMapper = new AusschreibungMapper();
			}
			return aMapper;
		}
		
		public Ausschreibung findByKey(int id){
			
			Connection con = DBConnection.connection();
			
			try{
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT ID, bez FROM Projektmarktplatz "
	          + "WHERE ID=" + id + " ORDER BY bez");
				
				if(rs.next()){
					Ausschreibung p = new Ausschreibung();
					p.setID(rs.getInt("ID"));
					p.setAusschreibungstext(rs.getString("ausschreibungstext"));
					p.setBezeichnung(rs.getString("bezeichnung"));
					p.setDatum(rs.getDate("datum"));
					return p;
				}
			}
			catch(SQLException e2){
				e2.printStackTrace();
				return null;
			}
			return null;	
		}
		public Vector<Ausschreibung> getAll(){
			
			 Connection con = DBConnection.connection();
			 
			
		Vector<Ausschreibung> result = new Vector<Ausschreibung>();
			 
			  try {
			      Statement stmt = con.createStatement();

			      ResultSet rs = stmt.executeQuery("SELECT ID, ausschreibungstext, bezeichnung, datum FROM Ausschreibung");
			  
			  while (rs.next()) {
				  	Ausschreibung p = new Ausschreibung();
				  	p.setID(rs.getInt("ID"));
					p.setAusschreibungstext(rs.getString("ausschreibungstext"));
					p.setBezeichnung(rs.getString("bezeichnung"));
					p.setDatum(rs.getDate("datum"));
				  
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
			    	  	
			    		stmt.executeUpdate("INSERT INTO Ausschreibung (ID , ausschreibungstext, bezeichnung, datum)" 
			    		+ "VALUES (" + a.getID() + "," + "'" + a.getAusschreibungstext() + "'" + "," + "'" + a.getBezeichnung() 
			    		+ "'" + "," + a.getDatum()  +")"); 
			    	  
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
		
}

