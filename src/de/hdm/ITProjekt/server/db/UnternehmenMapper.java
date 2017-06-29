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
		
	};

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
			un.setStrasse(super.findByKey(id).getStrasse());
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
				u.setStrasse(super.findByObject(u).getStrasse());
				u.setHausnummer(super.findByObject(u).getHausnummer());
				u.setPlz(super.findByObject(u).getPlz());
				u.setOrt(super.findByObject(u).getOrt());
			 
				result.addElement(u);
		  }
		}
		    catch (SQLException e2) {
		        e2.printStackTrace();
		        return null;
		      }
		  return result;
	}
	
public Unternehmen createUnternehmen(Unternehmen u){
		
		Connection con = DBConnection.connection();
		
		try {
		      Statement stmt = con.createStatement();
		      
		      u.setID(super.insert(u));
		      
		      ResultSet rs = stmt.executeQuery("SELECT MAX(ID) AS maxid "
		              + "FROM organisationseinheit ");
		      
		
		      if(rs.next()){
		    	  
		    	  	u.setID(rs.getInt("maxid"));
		    	  
		    	  	stmt = con.createStatement();
		    	  	
		    		stmt.executeUpdate("INSERT INTO Unternehmen (ID , name)" + "VALUES "
		    				+ "("+  u.getID()+ "," + "'" + u.getName() + "'" +")");		    				    			    	  
		      }
		}
		catch(SQLException e2){
			e2.printStackTrace();
		}
		return u;
	}





public Unternehmen updateUnternehmen(Unternehmen u) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();
      u.setID(super.update(u));
      super.orgMapper().update(u);
      stmt.executeUpdate("UPDATE Unternehmen SET " + "name=\""
          + u.getName() + "\"" + "WHERE ID=" + u.getID());
    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    return u;
  }
public void deleteUnternehmen(Integer u) {
	Unternehmen unternehmen = new Unternehmen();
	unternehmen.setID(u);
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();
      stmt.executeUpdate("DELETE FROM Unternehmen " + "WHERE ID=" + u);
      super.delete(unternehmen);
    }
    
    catch (SQLException e2) {
      e2.printStackTrace();
    }

  }


public void deleteUnternehmen(Unternehmen u) {
    this.deleteUnternehmen(u.getID());

}
}
