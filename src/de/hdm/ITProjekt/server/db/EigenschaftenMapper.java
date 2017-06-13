package de.hdm.ITProjekt.server.db;
import de.hdm.ITProjekt.shared.bo.Partnerprofil.Eigenschaft;
import de.hdm.ITProjekt.shared.bo.Projektmarktplatz;
import de.hdm.ITProjekt.server.db.DBConnection;
import java.sql.*;
import java.util.Vector;
import java.text.SimpleDateFormat;

public class EigenschaftenMapper {
	
//	
//		
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//		
//		private static EigenschaftenMapper eMapper = null;
//		
//		protected EigenschaftenMapper(){
//			
//		}
//		
//		public static EigenschaftenMapper eMapper(){
//			if(eMapper == null){
//				eMapper = new EigenschaftenMapper();
//			}
//			return eMapper;
//		}
//		
//		public Eigenschaft findByKey(int id){
//			Connection con = DBConnection.connection();
//			
//			try{
//				Statement stmt = con.createStatement();
//				ResultSet rs = stmt.executeQuery("SELECT ID, name, beschreibung, startdatum, enddatum FROM Projekt "
//	          + "WHERE ID=" + id + " ORDER BY ID");
//				
//				if(rs.next()){
//					Eigenschaft e = new Eigenschaft();
//					
//					e.setName(rs.getString("name"));
//					e.setWert(rs.getString("wert"));
//					
//					return e;
//				}
//			}
//			catch(SQLException e2){
//				e2.printStackTrace();
//				return null;
//			}
//			return null;	
//		}
//		
//		public Vector<Eigenschaft> getAll(){
//			
//			 Connection con = DBConnection.connection();
//			 
//			 Vector<Eigenschaft> result = new Vector<Eigenschaft>();
//			 
//			  try {
//			      Statement stmt = con.createStatement();
//
//			      ResultSet rs = stmt.executeQuery("SELECT name, wert FROM Projekt");
//			  
//			  while (rs.next()) {
//				  	EigenschaftenMapper e = new EigenschaftenMapper();
//				
//					e.setName(rs.getString("name"));
//					e.setWert(rs.getString("wert"));
//					
//				  
//				  result.addElement(e);
//			  }
//			}
//			    catch (SQLException e2) {
//			        e2.printStackTrace();
//			      }
//			  return result;
//		}
//		
//		public Eigenschaft insert(Eigenschaft pmp){
//			
//			Connection con = DBConnection.connection();
//			
//			try {
//			      Statement stmt = con.createStatement();
//			      
//			      ResultSet rs = stmt.executeQuery("SELECT MAX(ID) AS maxid "
//			              + "FROM Projekt ");
//			     	
//			      if(rs.next()){
//			    	  
//			    	  	pmp.setID(rs.getInt("maxid") + 1);
//			    	  
//			    	  	stmt = con.createStatement();
//			    	  	
//			    		stmt.executeUpdate("INSERT INTO Projekt (ID, name, beschreibung, startdatum, enddatum, Projektmarktplatz_ID)" 
//			    		+ "VALUES (" + pmp.getName() + "'" + "," + "'" + pmp.getWert() 
//			    		+ ")"); 
//			    	  
//			      }
//			}
//			catch(SQLException e2){
//				e2.printStackTrace();
//			}
//			return pmp;
//			
//		}
//		
//		public void delete(EigenschaftenMapper e){
//			
//			Connection con = DBConnection.connection();
//			
//			try {
//			      Statement stmt = con.createStatement();
//
//			      stmt.executeUpdate("DELETE FROM Eigenschaft " 
//			    		  			+ "WHERE Eigenscahft.ID = " + e.getID());
//				}
//			
//			catch (SQLException e2) {
//					e2.printStackTrace();
//				}
//			}
//		
//		public EigenschaftenMapper update(EigenschaftenMapper e) {
//		    Connection con = DBConnection.connection();
//
//		    try {
//		      Statement stmt = con.createStatement();
//
//		      stmt.executeUpdate("UPDATE Projekt " + "SET name='"
//		          + c.getName() + "', wert='" + c.getWert() );
//
//		    }
//		    catch (SQLException e) {
//		      e.printStackTrace();
//		    }
//
//		    return c;
//		  }
	}



