package de.hdm.ITProjekt.server.db;

import de.hdm.ITProjekt.shared.bo.Ausschreibung;
import de.hdm.ITProjekt.shared.bo.Bewerbung;
import de.hdm.ITProjekt.shared.bo.Organisationseinheit;
import de.hdm.ITProjekt.shared.bo.Projekt;
import de.hdm.ITProjekt.server.db.DBConnection;
import java.sql.*;
import java.util.Vector;
import java.text.SimpleDateFormat;

/*
 * Die Klasse AusschreibungMapper bildet Ausschreibungs-Objekte auf einer relationalen Datenbank ab.
 * Mit Hilfe von verschiedenen Methoden können die jeweilgen Objekte aus der Datenbank geholt, geschrieben 
 * oder aktualisiert werden.
 * Die Besonderheit ist, dass Objekte in DB-Strukturen und umgekehrt umgewandelt werden können
 */

public class AusschreibungMapper {
	
	/*
	 * Definieren des Datumsformats
	 */
	
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		
		/*
		 * Speicherung der einzigen Instanz dieser Mapperklasse
		 */
		
		private static AusschreibungMapper aMapper = null;
		
		/*
		 * Konstruktor wird geschützt, damit Objekte der Klasse Ausschreibungsmapper
		 *  nicht außerhalb der Vererbungshirarchie der Klasse erstellt werden können
		 */
		
		protected AusschreibungMapper(){
			
		}
		
		/*
		 * Singelton Eigenschaft der Mapperklasse, nur eine Instanz kann Existieren
		 * @return aMapper
		 */
		
		public static AusschreibungMapper aMapper(){
			if(aMapper == null){
				aMapper = new AusschreibungMapper();
			}
			return aMapper;
		}
		
		/*
		 * Ausschreibung wird anhand der übergebenen, eindeutigen ID zurückgegeben
		 * @return Ausschreibung entsprechend der übergebenen ID
		 * @param ID Primärschlüssel ID der Tabelle Ausschreibung
		 */
		
		public Ausschreibung findByKey(int id){
			
			Connection con = DBConnection.connection();
			
			try{
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT ID, ausschreibungstext, bezeichnung, datum, status, Projekt_ID, Orga_ID, Partnerprofil_ID FROM Ausschreibung "
	          + "WHERE ID=" + id);
				
				if(rs.next()){
					Ausschreibung p = new Ausschreibung();
					p.setID(rs.getInt("ID"));
					p.setAusschreibungstext(rs.getString("ausschreibungstext"));
					p.setBezeichnung(rs.getString("bezeichnung"));
					p.setDatum(rs.getDate("datum"));
					p.setStatus(rs.getString("status"));
					p.setProjekt_ID(rs.getInt("Projekt_ID"));
					p.setOrga_ID(rs.getInt("Orga_ID"));
					p.setPartnerprofil_ID(rs.getInt("Partnerprofil_ID"));
					return p;
				}
			}
			catch(SQLException e2){
				e2.printStackTrace();
				return null;
			}
			return null;	
		}		
		public Vector<Ausschreibung> findAusschreibungByOrga1(Organisationseinheit o){
			
			Connection con = DBConnection.connection();
			 Vector<Ausschreibung> result = new Vector<Ausschreibung>();

			try{
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT ID, ausschreibungstext, bezeichnung, datum, Projekt_ID, Orga_ID FROM Ausschreibung "
	          + "WHERE Orga_ID=" + o.getID());
				
				if(rs.next()){
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
			catch(SQLException e2){
				e2.printStackTrace();
				return null;
			}
			return result;	
		}
		
		/*
		 * Alle Ausschreibungen werden anhand der übergebenen, Organisationseinheit
		 * in einem Vector zurückgegeben.
		 * @return alle Elemente die im Vector gespeichert wurden
		 * @param ID Orga_ID der Tabelle Ausschreibung
		 */
		
		public Vector<Ausschreibung> findAusschreibungByOrga(Organisationseinheit o){
			
			Connection con = DBConnection.connection();
			Vector<Ausschreibung> result = new Vector<Ausschreibung>();

			try{
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT ID, ausschreibungstext, bezeichnung, datum, status, Projekt_ID, Orga_ID FROM Ausschreibung "
	          + "WHERE Orga_ID=" + o.getID());
				
				if(rs.next()){
					Ausschreibung p = new Ausschreibung();
					p.setID(rs.getInt("ID"));
					p.setAusschreibungstext(rs.getString("ausschreibungstext"));
					p.setBezeichnung(rs.getString("bezeichnung"));
					p.setDatum(rs.getDate("datum"));
					p.setStatus(rs.getString("status"));
					p.setProjekt_ID(rs.getInt("Projekt_ID"));
					p.setOrga_ID(rs.getInt("Orga_ID"));
					result.addElement(p);

				}
			}
			catch(SQLException e2){
				e2.printStackTrace();
				return null;
			}
			return result;	
		}
		
		/*
		 * Auslesen aller Ausschreibungen eines Projekts mittels Projekt ID
		 * @return result
		 * @param projektId
		 */
		
		 public Vector<Ausschreibung> getAlLAuscchreibungenBy(int projektId){
				
			  Connection con = DBConnection.connection();
			  Vector<Ausschreibung> result = new Vector<Ausschreibung>();
			  
			  try {
				
				  Statement stmt = con.createStatement();
				  ResultSet rs = stmt.executeQuery("SELECT ID, ausschreibungstext, bezeichnung, datum, status, Projekt_ID FROM Ausschreibung "
				  		+ "WHERE Projekt_Id=" + projektId);
				  
				  while (rs.next()) {
					Ausschreibung a = new Ausschreibung();
				 	a.setID(rs.getInt("ID"));
					a.setAusschreibungstext(rs.getString("ausschreibungstext"));
					a.setBezeichnung(rs.getString("bezeichnung"));
					a.setDatum(rs.getDate("datum"));
					a.setStatus(rs.getString("status"));
					a.setProjekt_ID(rs.getInt("Projekt_ID"));
					
					result.add(a);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			  return result;
		  }
				
		/*
		 * Alle Auschreibungen aus der Datenbank werden ausgegeben
		 * @return result
		 */
		
		public Vector<Ausschreibung> getAll(){
			
			 Connection con = DBConnection.connection();		
			 Vector<Ausschreibung> result = new Vector<Ausschreibung>();
			 
			  try {
			      Statement stmt = con.createStatement();

			      ResultSet rs = stmt.executeQuery("SELECT ID, ausschreibungstext, bezeichnung, datum, status, Projekt_ID, Orga_ID, Partnerprofil_ID FROM Ausschreibung");
			  
			  while (rs.next()) {
				  	Ausschreibung p = new Ausschreibung();
				  	p.setID(rs.getInt("ID"));
					p.setAusschreibungstext(rs.getString("ausschreibungstext"));
					p.setBezeichnung(rs.getString("bezeichnung"));
					p.setDatum(rs.getDate("datum"));
					p.setStatus(rs.getString("status"));
					p.setProjekt_ID(rs.getInt("Projekt_ID"));
					p.setOrga_ID(rs.getInt("Orga_ID"));
					p.setPartnerprofil_ID(rs.getInt("Partnerprofil_ID"));
				  
				  result.addElement(p);
			  }
			}
			    catch (SQLException e2) {
			        e2.printStackTrace();
			      }
			  return result;
		}
		
		public Vector<Ausschreibung> getAllAusschreibung(){
			
			 Connection con = DBConnection.connection();		
			 Vector<Ausschreibung> result = new Vector<Ausschreibung>();
			 
			  try {
			      Statement stmt = con.createStatement();

			      ResultSet rs = stmt.executeQuery("SELECT * FROM `ausschreibung` WHERE `status` = 'laufend'");
			  
			  while (rs.next()) {
				  	Ausschreibung p = new Ausschreibung();
				  	p.setID(rs.getInt("ID"));
					p.setAusschreibungstext(rs.getString("ausschreibungstext"));
					p.setBezeichnung(rs.getString("bezeichnung"));
					p.setDatum(rs.getDate("datum"));
					p.setStatus(rs.getString("status"));
					p.setProjekt_ID(rs.getInt("Projekt_ID"));
					p.setOrga_ID(rs.getInt("Orga_ID"));
					p.setPartnerprofil_ID(rs.getInt("Partnerprofil_ID"));
				  
				  result.addElement(p);
			  }
			}
			    catch (SQLException e2) {
			        e2.printStackTrace();
			      }
			  return result;
		}
		
		
		/*
		 *  Hinzufügen eines Ausschreibungsobejkts in die Datenbank
		 *  Primärschlüssel des übergebenen Projekts wird geprüft
		 *  @param a das Ausschreibungsobjekt das gespeichert werden soll
		 *  @return a
		 */
		
		public Ausschreibung addAusschreibung(Ausschreibung a){
			
			Connection con = DBConnection.connection();
			
			try {
			      Statement stmt = con.createStatement();
			      
			      ResultSet rs = stmt.executeQuery("SELECT MAX(ID) AS maxid "
			              + "FROM Ausschreibung ");
			      
			
			      if(rs.next()){
			    	  
			    	  	a.setID(rs.getInt("maxid") + 1);
			   	  
			    	  	stmt = con.createStatement();
			    	  	
			    		stmt.executeUpdate("INSERT INTO Ausschreibung (ID , ausschreibungstext, bezeichnung, datum, status, Projekt_ID, Orga_ID,Partnerprofil_ID)" 
			    		+ "VALUES (" + a.getID() + ", " + "'" + a.getAusschreibungstext() + "'" + ", " + "'" + a.getBezeichnung() 
			    		+ "'" + ", " + "'" + date.format(a.getDatum()) +"'"+ ", " + "'" + a.getStatus() +"'"+ ", " + a.getProjekt_ID() + ", " +a.getOrga_ID() + ", " + a.getPartnerprofil_ID()  +")"); 
			    	  
			      }
			}
			catch(SQLException e2){
				e2.printStackTrace();
			}
			return a;		
		}
		
		/*
		 * Löschen der Übergebenen Ausschreibung
		 * @param a Ausschreibungsobjekt, das gelöscht werden soll
		 */
		
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
		
		/*
		 * Wir eine Ausschreibung aktualisiert wird geprüft ob eine Projekt null ist. 
		 * Dies sorgt dafür dass es keine Wert mit 0 oder gibt. Daselbe wird für Organisationseinheit
		 * und Partnerprofil gemacht 
		 * @param a
		 * @return das als PArameter übergebene und aktualisierte Ausschreibungsobjekt
		 */
		
		public Ausschreibung update(Ausschreibung c) {
		    
			Connection con = DBConnection.connection();

		    try {
		      Statement stmt = con.createStatement();
		      
		      if(c.getProjekt_ID()==null && c.getOrga_ID()==null && c.getPartnerprofil_ID()==null){
		    	  stmt.executeUpdate("UPDATE Ausschreibung " + "SET ausschreibungstext=\""
				          + c.getAusschreibungstext() + "\", " + "bezeichnung=\"" + c.getBezeichnung() + "\", "
		    			  + "Projekt_ID = NULL, Orga_ID = NULL, Partnerprofil_ID = NULL"  
				          + " WHERE Ausschreibung.ID=" + c.getID());
		      }else if(c.getProjekt_ID()!= null && c.getOrga_ID()==null && c.getPartnerprofil_ID()==null){
		    	  stmt.executeUpdate("UPDATE Ausschreibung " + "SET ausschreibungstext=\""
				          + c.getAusschreibungstext() + "\", " + "bezeichnung=\"" + c.getBezeichnung() + "\", " + "Projekt_ID=\"" + c.getProjekt_ID() + "\", "
		    			  + "Orga_ID=NULL, Partnerprofil_ID=NULL" + "\", "
				          + "WHERE Ausschreibung.ID=" + c.getID());
		      }else if(c.getProjekt_ID()!= null &&  c.getOrga_ID()!=null && c.getPartnerprofil_ID()!= null){  
	    		  stmt.executeUpdate("UPDATE Ausschreibung " + "SET ausschreibungstext=\""
	          + c.getAusschreibungstext() + "\", " + "bezeichnung=\"" + c.getBezeichnung() + "\", "
	          + "WHERE Ausschreibung.ID=" + c.getID());


		      }
		      
		    		  

		      }		    		  

		  
		    catch (SQLException e) {
		      e.printStackTrace();
		    }
		    return c;
		  }
		
		/*
		 * Suchen einer oder mehrerer Ausschreibungen über die Projekt_ID.
		 * Die aus der DB gelesenen Werte werden in einen Vector gespeichert.
		 * @param a
		 * @return projekt.getID
		 */
		public Vector<Ausschreibung> findByProjekt(int projektID){
			
			Connection con = DBConnection.connection();
			Vector<Ausschreibung> result = new Vector<Ausschreibung>();
			
			try {
			      Statement stmt = con.createStatement();
			      
			      ResultSet rs = stmt.executeQuery("SELECT ID, ausschreibungstext, bezeichnung, datum, status, Projekt_ID, Orga_ID, Partnerprofil_ID FROM Ausschreibung WHERE Projekt_ID = " + projektID );
			      
			      while(rs.next()){
			    	  Ausschreibung a = new Ausschreibung();
			    	  a.setID(rs.getInt("ID"));
			    	  a.setAusschreibungstext(rs.getString("ausschreibungstext"));
			    	  a.setBezeichnung(rs.getString("bezeichnung"));
			    	  a.setDatum(rs.getDate("datum"));
			    	  a.setStatus(rs.getString("status"));
			    	  a.setProjekt_ID(rs.getInt("Projekt_ID"));
			    	  a.setOrga_ID(rs.getInt("Orga_ID"));
			    	  a.setPartnerprofil_ID(rs.getInt("Partnerprofil_ID"));
			    	  
			    	  result.addElement(a);
			    	  		
			      }
			}
			catch (SQLException e2) {
			      e2.printStackTrace();
			    }
			return result;
		}
		
		/*
		 * Hilfsmethode, die die Methode finByProjekt(int projektID) nicht mit der ID,
		 * sondern mit einem übergebenen Projekt ausführt.
		 */
		
		public Vector<Ausschreibung> findByProjekt(Projekt projekt){
			
			return findByProjekt(projekt.getID());			
		}	
		
		public Ausschreibung updateStatus(Ausschreibung a){
			Connection con = DBConnection.connection();
			
			try{
				Statement stmt = con.createStatement();
				stmt.executeUpdate("UPDATE Ausschreibung " + "SET status= " + "'" + a.getStatus() + "'" 
				+  "WHERE ID = " +a.getID());
			}catch(SQLException e){
				e.printStackTrace();
			}
			return a;
		}
}

