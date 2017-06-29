package de.hdm.ITProjekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.ITProjekt.shared.bo.*;

/*
 * Mapper für Teilnahmeobjekte
 */
public class TeilnahmeMapper {
	
	/*
	 * Speicherung der einzigen Instanz dieser Mapperklasse
	 */
	
	public static TeilnahmeMapper tnMapper = null;
	
	/*
	 * Konstruktor wird geschützt, damit Objekte der Klasse Teilnahmemapper
	 *  nicht außerhalb der Vererbungshirarchie der Klasse erstellt werden können
	 */
	
	protected TeilnahmeMapper(){
		
	}
	
	/*
	 * Singelton Eigenschaft der Mapperklasse, nur eine Instanz kann Existieren
	 * @return tMapper
	 */
	
	
	public static TeilnahmeMapper tnMapper(){
		if(tnMapper == null){
			tnMapper = new TeilnahmeMapper();
		}
		return tnMapper;
	}
	
	/*
	 *  Hinzufügen eines Teilnahmeobejkts in die Datenbank
	 *  @param p das Teilnahmeobjekt das gespeichert werden soll
	 *  @return p
	 */
	
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
	
	/*
	 * Löschen der Übergebenen Teilnahme
	 * @param p , projektmarktplatzid Ausschreibungsobjekt, das gelöscht werden soll
	 */
	
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
	
	  /* 
	   * @param p
	   * @return Vector mit allen projektmarktplätzen auf der sich die übergebene Person p befindet
	   */
	
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
	 
	  /*
	   * @param pm
	   * @return Vector mit allen Personen die sich auf dem übergebenen Projektmarktplatz pm befinden
	   */
	  
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
	  
	  
	  /*
	   * @param pm
	   * @return Vector mit allen Projekten auf der sich die übergebene Person pm befindet
	   */  
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
				p.add(ProjektMapper.pMapper().getProjektByID(personID));
			}
	          
	          
	         
	        }
	        catch (SQLException e) {
	          e.printStackTrace();
	        }
	        return p;
}
	

}
