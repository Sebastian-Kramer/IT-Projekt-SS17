package de.hdm.ITProjekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.ITProjekt.shared.bo.*;

public class TeilnahmeMapper {
	
	public static TeilnahmeMapper tmMapper = null;
	
	
	protected TeilnahmeMapper(){
		
	}
	
	
	public static TeilnahmeMapper tmMapper(){
		if(tmMapper == null){
			tmMapper = new TeilnahmeMapper();
		}
		return tmMapper;
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
	
	public void entfernenTeilnahme(Person p, Projektmarktplatz pm){
		
		Connection con = DBConnection.connection();
		
		try{
			Statement stmt = con.createStatement();
			
			stmt = con.createStatement();
    	  	
    		stmt.executeUpdate("DELETE FROM 'Teilnahme' WHERE 'Teilname'.'Person_ID' = "+p.getID() 
    							+ "AND 'Teilname'.'Porjektmarktplatz_ID' = " +p.getID());
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
	
	

}
