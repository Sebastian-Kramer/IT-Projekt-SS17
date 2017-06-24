package de.hdm.ITProjekt.server.db;
import de.hdm.ITProjekt.shared.bo.Eigenschaft;
import de.hdm.ITProjekt.shared.bo.Organisationseinheit;
import de.hdm.ITProjekt.shared.bo.Partnerprofil;
import de.hdm.ITProjekt.shared.bo.Person;
import de.hdm.ITProjekt.server.db.DBConnection;
import java.sql.*;
import java.util.Vector;
import java.text.SimpleDateFormat;

public class EigenschaftMapper {
	
	
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		private static EigenschaftMapper eMapper = null;
		
		protected EigenschaftMapper(){
			
		};
		
		public static EigenschaftMapper eMapper(){
			if(eMapper == null){
				eMapper = new EigenschaftMapper();
			}
			return eMapper;
		}
		
		public Vector<Eigenschaft> findByPartnerprofil(Partnerprofil p){
			Connection con = DBConnection.connection();
			
			Vector<Eigenschaft> result = new Vector<Eigenschaft>();
			
			try{
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT ID, name, wert, Partnerprofil_ID FROM Eigenschaft "
	          + "WHERE Partnerprofil_ID=" + p.getID());
				
				while(rs.next()){
					Eigenschaft e = new Eigenschaft();
					e.setID(rs.getInt("ID"));
					e.setName(rs.getString("name"));
					e.setWert(rs.getString("wert"));
					e.setPartnerprofil_ID(rs.getInt("Partnerprofil_ID"));
					
					result.addElement(e);
				}
			}
			catch(SQLException e2){
				e2.printStackTrace();
			}
			return result;	
		}
		public Eigenschaft findByKey(int id){
			Connection con = DBConnection.connection();
			
			try{
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT ID, name, wert, Partnerprofil_ID FROM Eigenschaft "
	          + "WHERE ID" + id);
				
				if(rs.next()){
					Eigenschaft e = new Eigenschaft();
					e.setID(rs.getInt("ID"));
					e.setName(rs.getString("name"));
					e.setWert(rs.getString("wert"));
					e.setPartnerprofil_ID(rs.getInt("Partnerprofil_ID"));
					
					return e;
				}
			}
			catch(SQLException e2){
				e2.printStackTrace();
				return null;
			}
			return null;	
		}
		
		public Vector<Eigenschaft> getAll(){
			
			 Connection con = DBConnection.connection();
			 
			 Vector<Eigenschaft> result = new Vector<Eigenschaft>();
			 
			  try {
			      Statement stmt = con.createStatement();

			      ResultSet rs = stmt.executeQuery("SELECT name, wert, Partnerprofil_ID FROM Eigenschaft");
			  
			  while (rs.next()) {
				  	Eigenschaft e = new Eigenschaft();
				
				  	e.setID(rs.getInt("ID"));
					e.setName(rs.getString("name"));
					e.setWert(rs.getString("wert"));
					e.setPartnerprofil_ID(rs.getInt("Partnerprofil_ID"));
					
				  
				  result.addElement(e);
			  }
			}
			    catch (SQLException e2) {
			        e2.printStackTrace();
			      }
			  return result;
		}
		
		public Eigenschaft insert(Eigenschaft e){
			
			Connection con = DBConnection.connection();
			
			try {
			      Statement stmt = con.createStatement();
			      
			      ResultSet rs = stmt.executeQuery("SELECT MAX(ID) AS maxid "
			              + "FROM Eigenschaft ");
			     	
			      if(rs.next()){
			    	  
			    	  	e.setID(rs.getInt("maxid") + 1);
			    	  
			    	  	stmt = con.createStatement();
			    	  	
			    		stmt.executeUpdate("INSERT INTO Eigenschaft (ID, name, wert, Partnerprofil_ID)" 
			    		+ "VALUES (" + e.getID() + "," + "'" + e.getName() + "'" + ",'" + e.getWert() 
			    		+ "','" + e.getPartnerprofil_ID() + "'" + ")"); 
			    	  
			      }
			}
			catch(SQLException e2){
				e2.printStackTrace();
			}
			return e;
			
		}
		
	public void deleteEigenschaft(Eigenschaft e){
			
			Connection con = DBConnection.connection();
			
			try {
			      Statement stmt = con.createStatement();

			      stmt.executeUpdate("DELETE FROM Eigenschaft " 
			    		  			+ "WHERE ID = " + e.getID());
				}
			
			catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		
		public Eigenschaft update(Eigenschaft e) {
		    Connection con = DBConnection.connection();

		    try {
		      Statement stmt = con.createStatement();

		      stmt.executeUpdate("UPDATE Eigenschaft " + "SET name='"
		          + e.getName() + "', wert='" + e.getWert() +  "'," + "ID=" + e.getID());

		    }
		    catch (SQLException c) {
		      c.printStackTrace();
		    }

		    return e;
		  }
		
		public Vector<Eigenschaft> getEigenschaftbyID(int Partnerprofil_ID ){
			
			Vector<Eigenschaft> eObj = new Vector<Eigenschaft>();
			
			Connection con = DBConnection.connection();
			
			try{
				Statement stmt = con.createStatement();
				
				 ResultSet rs = stmt.executeQuery("SELECT ID, name, wert, Partnerprofil_ID FROM Eigenschaft "
						 + "WHERE Partnerprofil_ID =" + Partnerprofil_ID);
				 
				 while (rs.next()) {
				        // Ergebnis-Tupel in Objekt umwandeln
				    	  Eigenschaft e = new Eigenschaft();
					       e.setID(rs.getInt("ID"));
					       e.setName(rs.getString("name"));
					       e.setWert(rs.getString("wert"));
					       e.setPartnerprofil_ID(rs.getInt("Partnerprofil_ID"));
					       eObj.add(e);
				      }
				   
				    }
				    catch (SQLException e2) {
				      e2.printStackTrace();
				    }  
				    return eObj;
		}
	

	}



