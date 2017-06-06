package de.hdm.ITProjekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.ITProjekt.shared.bo.Organisationseinheit;
import de.hdm.ITProjekt.shared.bo.Person;
import de.hdm.ITProjekt.shared.bo.Team;
import de.hdm.ITProjekt.shared.bo.Unternehmen;

public class UnternehmenMapper extends OrganisationseinheitMapper{

	
	private static UnternehmenMapper unMapper = null;
	
	protected UnternehmenMapper(){
		
	}

	public static UnternehmenMapper unMapper(){
		if(unMapper == null){
			unMapper = new UnternehmenMapper();
		}
		return unMapper;
	}


public Unternehmen findByKey(int id){
	
	Connection con = DBConnection.connection();
	
	try{
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT ID, name FROM Unternehmen "
      + "WHERE ID=" + id);
		
		if(rs.next()){
			Unternehmen un = new Unternehmen();
			un.setID(rs.getInt("ID"));
			un.setName(rs.getString("name"));
			un.setStraße(super.findByKey(id).getStraße());
			un.setHausnummer(super.findByKey(id).getHausnummer());
			un.setOrt(super.findByKey(id).getOrt());
			un.setPlz(super.findByKey(id).getPlz());
			un.setPartnerprofil_ID(super.findByKey(id).getPartnerprofil_ID());
			return un;
		}
	}
	catch(SQLException e2){
		e2.printStackTrace();
		return null;
	}
	return null;	
	
}
	
	public Unternehmen findByObject(Unternehmen u){
		return this.findByKey(u.getID());
	}

	
	public Vector<Unternehmen> getAllUnternehmen(){
		
		 Connection con = DBConnection.connection();
		 
		 Vector<Unternehmen> result = new Vector<Unternehmen>();
		 
		  try {
		      Statement stmt = con.createStatement();

		      ResultSet rs = stmt.executeQuery("SELECT * FROM Unternehmen");
		  
		  while (rs.next()) {
			  Unternehmen u = new Unternehmen();
				u.setID(rs.getInt("Unternehmen_Id"));
				u.setName(rs.getString("Name"));
				u.setStraße(super.findByObject(u).getStraße());
				u.setHausnummer(super.findByObject(u).getHausnummer());
				u.setPlz(super.findByObject(u).getPlz());
				u.setOrt(super.findByObject(u).getOrt());
				u.setPartnerprofil_ID(super.findByObject(u).getPartnerprofil_ID());
			 
				result.addElement(u);
		  }
		}
		    catch (SQLException e2) {
		        e2.printStackTrace();
		        return null;
		      }
		  return result;
	}
	
public void createUnternehmen(Unternehmen u){
		
		Connection con = DBConnection.connection();
		
		try {
		      Statement stmt = con.createStatement();
		      
		      ResultSet rs = stmt.executeQuery("SELECT MAX(ID) AS maxid "
		              + "FROM unternehmen ");
		      
		
		      if(rs.next()){
		    	  
		    	  	u.setID(rs.getInt("maxid") + 1);
		    	  
		    	  	stmt = con.createStatement();
		    	  	
		    		stmt.executeUpdate("INSERT INTO organisationseinheit (ID , name, )" + "VALUES "
		    				+ "("+  u.getID()+ "," + "'" + u.getName() + "'" +")");		    				    			    	  
		      }
		}
		catch(SQLException e2){
			e2.printStackTrace();
		}
	}





public Unternehmen updateUnternehmen(Unternehmen u) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("UPDATE organisationseinheit SET " + "Name=\""
          + u.getName() + "\"" + "WHERE ID=" + u.getID());
    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    return u;
  }



public void deletePerson(Unternehmen unternehmen1) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();
      stmt.executeUpdate("DELETE FROM organisationseinheit " + "WHERE ID=" + unternehmen1.getID());

    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

  }

}
