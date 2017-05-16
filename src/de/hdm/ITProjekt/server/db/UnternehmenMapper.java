package de.hdm.ITProjekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.ITProjekt.shared.bo.Organisationseinheit;
import de.hdm.ITProjekt.shared.bo.Person;
import de.hdm.ITProjekt.shared.bo.Projektmarktplatz;
import de.hdm.ITProjekt.shared.bo.Unternehmen;

public class UnternehmenMapper {

	
	
	
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
		ResultSet rs = stmt.executeQuery("SELECT ID, name, firmensitz FROM Unternehmen "
      + "WHERE ID=" + id);
		
		if(rs.next()){
			Unternehmen un = new Unternehmen();
			un.setID(rs.getInt("ID"));
			un.setName(rs.getString("name"));
			un.setFirmensitz(rs.getString("firmensitz"));
			return un;
		}
	}
	catch(SQLException e2){
		e2.printStackTrace();
		return null;
	}
	return null;	
	
}
	
	public Vector<Unternehmen> getAll(){
		
		 Connection con = DBConnection.connection();
		 
		 Vector<Unternehmen> result = new Vector<Unternehmen>();
		 
		  try {
		      Statement stmt = con.createStatement();

		      ResultSet rs = stmt.executeQuery("SELECT * FROM Unternehmen");
		  
		  while (rs.next()) {
			  Unternehmen un = new Unternehmen();
			  un.setID(rs.getInt("ID"));
			  un.setName(rs.getString("name"));
			  un.setFirmensitz(rs.getString("firmensitz"));
			  
			  result.addElement(un);
		  }
		}
		    catch (SQLException e2) {
		        e2.printStackTrace();
		      }
		  return result;
	}
	
public void createUnternehmen(Unternehmen unternehmen1){
		
		Connection con = DBConnection.connection();
		
		try {
		      Statement stmt = con.createStatement();
		      
		      ResultSet rs = stmt.executeQuery("SELECT MAX(ID) AS maxid "
		              + "FROM unternehmen ");
		      
		
		      if(rs.next()){
		    	  
		    	  	unternehmen1.setID(rs.getInt("maxid") + 1);
		    	  
		    	  	stmt = con.createStatement();
		    	  	
		    		stmt.executeUpdate("INSERT INTO organisationseinheit (ID , name, firmensitz)" + "VALUES "
		    				+ "("+  unternehmen1.getID()+ "," + "'" + unternehmen1.getName() + "'" + "," + "'" + 
		    				unternehmen1.getFirmensitz()+ "'" +")");
		    		
		    		
		    	  
		      }
		}
		catch(SQLException e2){
			e2.printStackTrace();
		}
	}





public Unternehmen updateUnternehmen(Unternehmen unternehmen1) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("UPDATE organisationseinheit SET " + "Name=\""
          + unternehmen1.getName() + "\", " + "firmensitz=\""
          + unternehmen1.getFirmensitz() + "\" " + "WHERE id=" + unternehmen1.getID());
    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    return unternehmen1;
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
