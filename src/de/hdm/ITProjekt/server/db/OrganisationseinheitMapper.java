package de.hdm.ITProjekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.ITProjekt.shared.bo.Organisationseinheit;
import de.hdm.ITProjekt.shared.bo.Person;
import de.hdm.ITProjekt.shared.bo.Projektmarktplatz;

public class OrganisationseinheitMapper {


	
	
	private static OrganisationseinheitMapper orgMapper = null;
	
	protected OrganisationseinheitMapper(){
		
	}
	
	public static OrganisationseinheitMapper orgMapper(){
		if(orgMapper == null){
			orgMapper = new OrganisationseinheitMapper();
		}
		return orgMapper;
	}
	
	public Vector<Organisationseinheit> getAllOrganisationseinheit(){
		
		 Connection con = DBConnection.connection();
		 
		 Vector<Organisationseinheit> result = new Vector<Organisationseinheit>();
		 
		  try {
		      Statement stmt = con.createStatement();

		      ResultSet rs = stmt.executeQuery("SELECT * FROM Organisationseinheit");
		  
		  while (rs.next()) {
			    Organisationseinheit p = new Organisationseinheit();
				p.setID(rs.getInt("ID"));
				p.setStrasse(rs.getString("strasse"));
				p.setHausnummer(rs.getInt("hausnummer"));
				p.setPlz(rs.getInt("plz"));
				p.setOrt(rs.getString("ort"));
				p.setPartnerprofil_ID(rs.getInt("Partnerprofil_ID"));
			  
				result.add(p);
		  }
		}
		    catch (SQLException e2) {
		        e2.printStackTrace();
		      }
		  return result;
	}
	
	public Vector <Organisationseinheit> findOrgaByID(Integer id){
		Connection con = DBConnection.connection();
		Vector<Organisationseinheit> result = new Vector<Organisationseinheit>();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT ID, strasse, hausnummer, plz, ort, Partnerprofil_ID FROM Organisationseinheit "
          + "WHERE ID=" + id);
			
			while(rs.next()){
				Organisationseinheit o = new Organisationseinheit();
				o.setID(rs.getInt("ID"));
				o.setStrasse(rs.getString("strasse"));
				o.setHausnummer(rs.getInt("hausnummer"));
				o.setPlz(rs.getInt("plz"));
				o.setOrt(rs.getString("ort"));
				o.setPartnerprofil_ID(rs.getInt("Partnerprofil_ID"));
				
				result.add(o);
			}
		}
		catch(SQLException e2){
			e2.printStackTrace();
			return null;
		}
		return result;	
	
	}
	
	public Organisationseinheit findByKey(int id){
		Connection con = DBConnection.connection();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT ID, strasse, hausnummer, plz, ort, Partnerprofil_ID FROM Organisationseinheit "
          + "WHERE ID=" + id);
			
			if(rs.next()){
				Organisationseinheit o = new Organisationseinheit();
				o.setID(rs.getInt("ID"));
				o.setStrasse(rs.getString("strasse"));
				o.setHausnummer(rs.getInt("hausnummer"));
				o.setPlz(rs.getInt("plz"));
				o.setOrt(rs.getString("ort"));
				o.setPartnerprofil_ID(rs.getInt("Partnerprofil_ID"));
				return o;
			}
		}
		catch(SQLException e2){
			e2.printStackTrace();
			return null;
		}
		return null;	
	
	}
	public Organisationseinheit findByObject(Organisationseinheit o){
		return this.findByKey(o.getID()); 
	}
	
	public Organisationseinheit findByID(Integer id){
		Connection con = DBConnection.connection();
		Organisationseinheit o = new Organisationseinheit();
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT ID, strasse, hausnummer, plz, ort, Partnerprofil_ID FROM Organisationseinheit "
          + "WHERE ID=" + id);
			
			if(rs.next()){
				
				o.setID(rs.getInt("ID"));
				o.setStrasse(rs.getString("strasse"));
				o.setHausnummer(rs.getInt("hausnummer"));
				o.setPlz(rs.getInt("plz"));
				o.setOrt(rs.getString("ort"));
				o.setPartnerprofil_ID(rs.getInt("Partnerprofil_ID"));
				
			}
		}
		catch(SQLException e2){
			e2.printStackTrace();
			return null;
		}
		return o;	
	
	}
	
	public Organisationseinheit findByForeignPartnerprofilId(int partnerprofilId){
		Connection con = DBConnection.connection();
			
			try{
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT ID, strasse, hausnummer, plz, ort, Partnerprofil_ID FROM Organisationseinheit "
						+ "WHERE Partnerprofil_ID=" + partnerprofilId);
				
				
				if(rs.next()){
					Organisationseinheit o = new Organisationseinheit();
					o.setID(rs.getInt("ID"));
					o.setStrasse(rs.getString("strasse"));
					o.setHausnummer(rs.getInt("hausnummer"));
					o.setPlz(rs.getInt("plz"));
					o.setOrt(rs.getString("ort"));
					o.setPartnerprofil_ID(rs.getInt("Partnerprofil_ID"));
			return o;
				
				}
		}
			catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
			return null;
		}
	
	
		protected int insert(Organisationseinheit o){
		 
	    Connection con = DBConnection.connection();
	    int id=0;
	    try {
	    		
	      Statement stmt = con.createStatement();

	      /*
	       * Zunächst schauen wir nach, welches der momentan höchste
	       * Primärschlüsselwert ist.
	       */
	      ResultSet rs = stmt.executeQuery("SELECT MAX(ID) AS maxid "
	          + "FROM Organisationseinheit");
	      
	      if (rs.next()) {

	        o.setID(rs.getInt("maxid") + 1);
	        id=o.getID();
	        stmt = con.createStatement();

	        // Jetzt erst erfolgt die tatsächliche Einfügeoperation

	        stmt.executeUpdate("INSERT INTO Organisationseinheit (ID, strasse, hausnummer, plz, ort, Partnerprofil_ID) "
	            + "VALUES ('" + o.getID() + "','" + o.getStrasse() + "','"
	            + o.getHausnummer() + "','" + o.getPlz() + "','" + o.getOrt() + "', " + o.getPartnerprofil_ID() + ")");
	      }

	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	    }

	    return id;
	  
  }
	
	
	public int update(Organisationseinheit o){
		
		Connection con = DBConnection.connection();
		int id = 0;

		    try {
		    	id = o.getID();
		      Statement stmt = con.createStatement();

		      if (o.getPartnerprofil_ID() != null){
		      stmt.executeUpdate("UPDATE Organisationseinheit SET strasse='"
			          + o.getStrasse() + "'," + "hausnummer='" + o.getHausnummer() + "'," + "ort='" 
		    		  + o.getOrt() + "'," + "plz='" + o.getPlz() + "',"
			          + "Partnerprofil_ID='" + o.getPartnerprofil_ID() + "' WHERE ID="+o.getID());
		      }else {
		    	  stmt.executeUpdate("UPDATE Organisationseinheit SET strasse='"
				          + o.getStrasse() + "'," + "hausnummer='" + o.getHausnummer() + "'," + "ort='" 
			    		  + o.getOrt() + "'," + "plz='" + o.getPlz() + "',"
				          + "Partnerprofil_ID= NULL WHERE ID="+o.getID());
		      }
		    }
		    catch (SQLException e) {
		      e.printStackTrace();
		    }

		    return id;
	  }
	
	
	public void delete(Organisationseinheit o){
		 Connection con = DBConnection.connection();

		    try {
		      Statement stmt = con.createStatement();

		      stmt.executeUpdate("DELETE FROM Organisationseinheit" + " WHERE ID=" + o.getID());
		      
		    }
		    catch (SQLException e) {
		      e.printStackTrace();
		    }
	  }	
	
}
