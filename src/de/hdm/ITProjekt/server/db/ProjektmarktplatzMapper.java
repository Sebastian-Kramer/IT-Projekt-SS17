package de.hdm.ITProjekt.server.db;
import de.hdm.ITProjekt.shared.bo.Projektmarktplatz;

import de.hdm.ITProjekt.server.db.DBConnection;
import java.sql.*;
import java.util.Vector;

/*
 * Mapper für ProjektmarktplatzObjekte
 */

public class ProjektmarktplatzMapper {
	
	/*
	 * Speicherung der einzigen Instanz dieser Mapperklasse
	 */
	
	private static ProjektmarktplatzMapper pmpMapper = null;
	
	/*
	 * Konstruktor wird geschützt, damit Objekte der Klasse Projektmarktplatzmapper
	 *  nicht außerhalb der Vererbungshirarchie der Klasse erstellt werden können
	 */
	
	protected ProjektmarktplatzMapper(){
		
	}
	
	/*
	 * Singelton Eigenschaft der Mapperklasse, nur eine Instanz kann Existieren
	 * @return pmpMapper
	 */
	
	public static ProjektmarktplatzMapper pmpMapper(){
		if(pmpMapper == null){
			pmpMapper = new ProjektmarktplatzMapper();
		}
		return pmpMapper;
	}
	
	/*
	 * Projektmarktplatz wird anhand der übergebenen, eindeutigen ID zurückgegeben
	 * @return Ausschreibung entsprechend der übergebenen ID
	 * @param id Primärschlüssel ID der Tabelle Projektmarktplatz
	 */
	
	
	public Projektmarktplatz findByKey(int id){
		Connection con = DBConnection.connection();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT ID, bez FROM Projektmarktplatz "
          + "WHERE ID=" + id + " ORDER BY bez");
			
			if(rs.next()){
				Projektmarktplatz p = new Projektmarktplatz();
				p.setID(rs.getInt("ID"));
				p.setBez(rs.getString("bez"));
				return p;
			}
		}
		catch(SQLException e2){
			e2.printStackTrace();
			return null;
		}
		return null;	
	}
	
	/*
	 * Alle Projektmarktplätze aus der Datenbank werden ausgegeben
	 * @return result
	 */
	
	public Vector<Projektmarktplatz> getAll(){
		
		 Connection con = DBConnection.connection();
		 
		 Vector<Projektmarktplatz> result = new Vector<Projektmarktplatz>();
		 
		  try {
		      Statement stmt = con.createStatement();

		      ResultSet rs = stmt.executeQuery("SELECT ID, bez FROM Projektmarktplatz");
		  
		  while (rs.next()) {
			  Projektmarktplatz p = new Projektmarktplatz();
			  p.setID(rs.getInt("ID"));
			  p.setBez(rs.getString("bez"));
			  
			  result.addElement(p);
		  }
		}
		    catch (SQLException e2) {
		        e2.printStackTrace();
		      }
		  return result;
	}
	
	/*
	 *  Hinzufügen eines Projektmarktplatzobejkts in die Datenbank
	 *  @param pmp das Projektmarktplatzobjekt das gespeichert werden soll
	 *  @return pmp
	 */
	
	
	public Projektmarktplatz addMarktplatz(Projektmarktplatz pmp){
		
		Connection con = DBConnection.connection();
		
		try {
		      Statement stmt = con.createStatement();
		      
		      ResultSet rs = stmt.executeQuery("SELECT MAX(ID) AS maxid "
		              + "FROM Projektmarktplatz ");
		      
		
		      if(rs.next()){
		    	  
		    	  	pmp.setID(rs.getInt("maxid") + 1);
		    	  
		    	  	stmt = con.createStatement();
		    	  	
		    		stmt.executeUpdate("INSERT INTO Projektmarktplatz (ID , bez)" + "VALUES (" 
		    		+ pmp.getID() + "," + "'" + pmp.getBez() + "'" +")");
		    	  
		      }
		}
		catch(SQLException e2){
			e2.printStackTrace();
		}
		return pmp;
		
	}
	
	/*
	 * Erneutes schreiben eines Projektmarktplatzobjekts in die Datenbank
	 * @param p
	 * @return p das als Parameter übergebene und aktualisierte Projektmarktplatzobjekt
	 */

	public Projektmarktplatz updateMarktplatz(Projektmarktplatz p){
		
		Connection con = DBConnection.connection();
		
		try{
			
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate(" UPDATE Projektmarktplatz SET bez = '" + p.getBez()
  		  +"' WHERE ID =" + p.getID() + ";");
		}

			
		catch (SQLException e2) {
			e2.printStackTrace();
		}
			
		return p;
	}
	
	/*
	 * Löschen der Übergebenen Projektmarktplatz
	 * @param p Projektmarktplatzobjekt, das gelöscht werden soll
	 */
	
	public Projektmarktplatz deleteMarktplatz(Projektmarktplatz p){
		
		Connection con = DBConnection.connection();
	
		try{
			
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate(" DELETE FROM Projektmarktplatz " +  "WHERE ID= " + p.getID());
		
			}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
			
		return p;
	}
	
	/*
	 * Projektmarktl´platz wird anhand der übergebenen Bezeichnung zurückgegeben
	 * @param bez
	 * @return p oder null
	 */

	public Projektmarktplatz findByBez(String bez){
		Connection con = DBConnection.connection();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT ID, bez FROM Projektmarktplatz "
          + "WHERE bez=" + bez);
			
			if(rs.next()){
				Projektmarktplatz p = new Projektmarktplatz();
				p.setID(rs.getInt("ID"));
				p.setBez(rs.getString("bez"));
				return p;
			}
		}
		catch(SQLException e2){
			e2.printStackTrace();
			return null;
		}
		return null;	
	}
}
	