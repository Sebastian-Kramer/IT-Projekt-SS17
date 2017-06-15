package de.hdm.ITProjekt.server.db;
import de.hdm.ITProjekt.shared.bo.Eigenschaft;
import de.hdm.ITProjekt.server.db.DBConnection;
import java.sql.*;
import java.util.Vector;

import com.ibm.icu.text.SimpleDateFormat;

public class EigenschaftMapper {
	
	
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		private static EigenschaftMapper eMapper = null;
		
		protected EigenschaftMapper(){
			
		}
		
		public static EigenschaftMapper eMapper(){
			if(eMapper == null){
				eMapper = new EigenschaftMapper();
			}
			return eMapper;
		}
		
		public Eigenschaft findByKey(int id){
			Connection con = DBConnection.connection();
			
			try{
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT ID, name, wert, Partnerprofil_ID FROM Eigenschaft "
	          + "WHERE ID=" + id + " ORDER BY ID");
				
				if(rs.next()){
					Eigenschaft e = new Eigenschaft();
					e.setID(rs.getInt("ID"));
					e.setName(rs.getString("name"));
					e.setWert(rs.getString("wert"));
			//		e.setPartnerprofil_ID(rs.getPartnerprofil_ID("partnerprofil_ID"));
					
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

			      ResultSet rs = stmt.executeQuery("SELECT name, wert, partnerprofil_ID FROM Eigenschaft");
			  
			  while (rs.next()) {
				  	Eigenschaft e = new Eigenschaft();
				
				  	e.setID(rs.getInt("ID"));
					e.setName(rs.getString("name"));
					e.setWert(rs.getString("wert"));
			//		e.setPartnerprofil_ID(rs.getPartnerprofil_ID("partnerprofil_ID"));
					
				  
				  result.addElement(e);
			  }
			}
			    catch (SQLException e2) {
			        e2.printStackTrace();
			      }
			  return result;
		}
		
		public Eigenschaft insert(Eigenschaft pmp){
			
			Connection con = DBConnection.connection();
			
			try {
			      Statement stmt = con.createStatement();
			      
			      ResultSet rs = stmt.executeQuery("SELECT MAX(ID) AS maxid "
			              + "FROM Eigenscahft ");
			     	
			      if(rs.next()){
			    	  
			    	  	pmp.setID(rs.getInt("maxid") + 1);
			    	  
			    	  	stmt = con.createStatement();
			    	  	
			    		stmt.executeUpdate("INSERT INTO Projekt (ID, name, wert, partnerprojekt_ID)" 
			    		+ "VALUES (" + pmp.getName() + "'" + "," + "'" + pmp.getWert() 
			    		+ ")"); 
			    	  
			      }
			}
			catch(SQLException e2){
				e2.printStackTrace();
			}
			return pmp;
			
		}
		
//	public void delete(EigenschaftMapper e){
//			
//			Connection con = DBConnection.connection();
//			
//			try {
//			      Statement stmt = con.createStatement();
//
//			      stmt.executeUpdate("DELETE FROM Eigenschaft " 
//			    		  			+ "WHERE Eigenschaft.ID = " + e.getID());
//				}
//			
//			catch (SQLException e2) {
//					e2.printStackTrace();
//				}
//			}
//		
//		public EigenschaftMapper update(EigenschaftMapper e) {
//		    Connection con = DBConnection.connection();
//
//		    try {
//		      Statement stmt = con.createStatement();
//
//		      stmt.executeUpdate("UPDATE Eigenschaft " + "SET name='"
//		          + e.getName() + "', wert='" + e.getWert() + "partnerprofil ID" + e.getPartnerprofil_ID() );
//
//		    }
//		    catch (SQLException c) {
//		      c.printStackTrace();
//		    }
//
//		    return e;
//		  }

	}



