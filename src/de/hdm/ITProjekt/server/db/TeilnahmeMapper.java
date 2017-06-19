package de.hdm.ITProjekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.ITProjekt.shared.bo.*;

public class TeilnahmeMapper {
	
	public static TeilnahmeMapper tnMapper = null;
	
	
	protected TeilnahmeMapper(){
		
	}
	
	
	public static TeilnahmeMapper tnMapper(){
		if(tnMapper == null){
			tnMapper = new TeilnahmeMapper();
		}
		return tnMapper;
	}
	
	public void hinzufuegenTeilnahme(Person p, Projektmarktplatz pm){
		
		Connection con = DBConnection.connection();
		
		try{
			
			Statement stmt = con.createStatement();
			
			stmt = con.createStatement();
    	  	
    		stmt.executeUpdate("INSERT INTO Teilnahme (Person_ID , Projektmarktplatz_ID)" + "VALUES "
    				+ "("+  p.getID()+ "," + pm.getID()  +")");
		}
		 catch (SQLException e) {
		      e.printStackTrace();
		    }

		return;
	} 
	
	public void entfernenTeilnahme(Person p, int projektmarktplatzid){
		
		Connection con = DBConnection.connection();
		
		try{
			Statement stmt = con.createStatement();
			
			stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM `Teilnahme` WHERE `Person_ID` = " + p.getID() + " AND `Projektmarktplatz_ID` = " +projektmarktplatzid);
		}
	    catch (SQLException e) {
	      e.printStackTrace();
	    }

	    return;
	}
	
	 public Vector<Projektmarktplatz> findRelatedProjektMarktplaetze(Person p){
	        
		 
	        Connection con = DBConnection.connection();
	        
	   
	        Vector <Projektmarktplatz> pm = new Vector<>();

	        try {
	          
	          Statement stmt = con.createStatement();

	          ResultSet rs = stmt.executeQuery("SELECT * FROM `Teilnahme` WHERE Person_ID="+p.getID());
	         
	          Vector <Integer> marktplatzID = new Vector<>();
	     
	          
	          while(rs.next()){
	        	  marktplatzID.add(rs.getInt("Projektmarktplatz_ID"));
	          }
	           
	          for (Integer ids : marktplatzID) {
	        	  pm.add(ProjektmarktplatzMapper.pmpMapper().findByKey(ids));
			}
	        }
	        catch (SQLException e) {
	          e.printStackTrace();
	        }
	        return pm;
	  }
	  
	  public Vector<Person> findRelatedPersonen(Projektmarktplatz pm){
	        
	         Connection con = DBConnection.connection();
	        
	         Vector <Person> p = new Vector<>();

	        try {
	        
	          Statement stmt = con.createStatement();

	          
	          ResultSet rs = stmt.executeQuery("SELECT * FROM `Teilnahme` WHERE Projektmarktplatz_ID="+pm.getID());
	          
	          Vector <Integer> personID = new Vector<>();
	     
	          
	          while(rs.next()){
	        	  personID.add(rs.getInt("Person_ID"));
	          }
	          
	          for (Integer ids : personID) {
	        	  p.add(PersonMapper.perMapper().findByKey(ids));
			}
	        }
	        catch (SQLException e) {
	          e.printStackTrace();
	        }
	        return p;
}
	  public Vector<Projekt> findTeilnahmeProjekte(Person pm){
	        
	         Connection con = DBConnection.connection();
	        
	         Vector <Projekt> p = new Vector<>();

	        try {
	        
	          Statement stmt = con.createStatement();

	          
	          ResultSet rs = stmt.executeQuery("SELECT * FROM `Teilnahme` WHERE Person_ID="+pm.getID());
	          
	          Integer personID = 0;
	          Integer projektID = 0;
	          
	          while(rs.next()){
	        	  personID = rs.getInt("Person_ID");
	        	  projektID = rs.getInt("Projekt_ID");
	          }
	          
	          for (int i= 0; i<=p.size();i++) {
				p.add(ProjektMapper.pMapper().findByKey(personID));
			}
	          
	          
	         
	        }
	        catch (SQLException e) {
	          e.printStackTrace();
	        }
	        return p;
}
	

}
