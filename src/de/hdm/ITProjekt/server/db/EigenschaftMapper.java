package de.hdm.ITProjekt.server.db;
import de.hdm.ITProjekt.shared.bo.Eigenschaft;
import de.hdm.ITProjekt.shared.bo.Organisationseinheit;
import de.hdm.ITProjekt.shared.bo.Partnerprofil;
import de.hdm.ITProjekt.shared.bo.Person;
import de.hdm.ITProjekt.server.db.DBConnection;
import java.sql.*;
import java.util.Vector;
import java.text.SimpleDateFormat;

/*
 * Mapper für Eigenschaft-Objekte
 */

public class EigenschaftMapper {
	
	/*
	 * Festlegung des Datumformats
	 */
	
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		/*
		 * Speicherung der einzigen Instanz der dieser Mapperklasse
		 */
		
		private static EigenschaftMapper eMapper = null;
		
		/*
		 * Konstruktor wird geschützt, damit Objekte der Klasse EigenschaftMapper
		 *  nicht außerhalb der Vererbungshirarchie der Klasse erstellt werden können
		 */
		
		protected EigenschaftMapper(){
			

		}
		
		/*
		 * Singelton Eigenschaft der Mapperklasse, nur eine Instanz kann Existieren
		 * @return eMapper
		 */
		

		
		public static EigenschaftMapper eMapper(){
			if(eMapper == null){
				eMapper = new EigenschaftMapper();
			}
			return eMapper;
		}
		
		/*
		 * Eigenschaft wird anhand der übergebenen, eindeutigen ID zurückgegeben
		 * @return Eigenschaft entsprechend der übergebenen ID
		 * @param ID Primärschlüssel ID der Tabelle EIgenschaft
		 */
		
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

		
		/*
		 * /*
		 * Alle Eigenscahft aus der Datenbank werden ausgegeben
		 * @return result
		 */
		 

		public Eigenschaft getByPartnerprofil_ID(int id){
			Connection con = DBConnection.connection();
			
			try{
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT ID, name, wert, Partnerprofil_ID FROM Eigenschaft "
	          + "WHERE Partnerprofil_ID" + id);
				
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
		 
		/*
		 * @param e
		 * @return Uebergebenes Objekt als neue Entitaet in die Datenbank schreiben
		 */
		
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
		

		/*
		 * Löschen der Übergebenen Eigenschaft
		 * @param e Eigenschaftobjekt, das gelöscht werden soll
		 */
		
	

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
	
		/*
		 * Erneutes schreiben eines Eigenschaftsobjekts in die Datenbank
		 * @param e
		 * @return das als Parameter übergebene und aktualisierte Eigenschaftsobjekt
		 */
		
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
		
	    /*
	     * @param partnerprofilId
	     * @return Liefert alle Eigenschaften zu dem uebergenen Partnerprofil
	     */

		public Vector<Eigenschaft> getEigenschaftbyID(int Partnerprofil_ID ){
			
			Vector<Eigenschaft> eObj = new Vector<Eigenschaft>();
			
			Connection con = DBConnection.connection();
			
			try{
				Statement stmt = con.createStatement();
				
				 ResultSet rs = stmt.executeQuery("SELECT * FROM eigenschaft "+ " WHERE Partnerprofil_ID= " + Partnerprofil_ID);
				 
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
public Vector<Eigenschaft> getEigenschaftbyId(Integer Partnerprofil_ID ){
			
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



