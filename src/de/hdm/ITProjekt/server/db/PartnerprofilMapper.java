package de.hdm.ITProjekt.server.db;

import de.hdm.ITProjekt.shared.bo.Partnerprofil;
import de.hdm.ITProjekt.server.db.DBConnection;
import java.sql.*;
import java.util.Vector;

import com.ibm.icu.text.SimpleDateFormat;

public class PartnerprofilMapper {

	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	private static PartnerprofilMapper ppMapper = null;
	
	protected PartnerprofilMapper(){
		
	}
	
	public static PartnerprofilMapper ppMapper(){
		if(ppMapper == null){
			ppMapper = new PartnerprofilMapper();
		}
		return ppMapper;
	}
	
public Partnerprofil findByKey(int id){
		
		Connection con = DBConnection.connection();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT ID, erstellungsdatum, änderungsdatum, arbeitsgebiet, berufserfahrungsJahre FROM Partnerprofil "
          + "WHERE ID=" + id);
			
			if(rs.next()){
				Partnerprofil p = new Partnerprofil();
				p.setId(rs.getInt("ID"));
				p.setErstellungsdatum(rs.getDate("startdatum"));
				p.setAenderungsdatum(rs.getDate("enddatum"));
				p.setArbeitsgebiet(rs.getString("arbeitsgebiet"));
				p.setBerufserfahrungsJahre(rs.getInt("berufserfahrungsJahre"));
				return p;
			}
		}
		catch(SQLException e2){
			e2.printStackTrace();
			return null;
		}
		return null;	
	}

public Vector<Partnerprofil> getAll(){
	
	 Connection con = DBConnection.connection();	 
	
	 Vector<Partnerprofil> result = new Vector<Partnerprofil>();
	 
	  try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("SELECT ID, erstellungsdatum, änderungsdatum, arbeitsgebiet, berufserfahrungsJahre FROM Partnerprofil ");
	  
	  while (rs.next()) {
		  	Partnerprofil p = new Partnerprofil();
		  	p.setId(rs.getInt("ID"));
			p.setErstellungsdatum(rs.getDate("startdatum"));
			p.setAenderungsdatum(rs.getDate("enddatum"));
			p.setArbeitsgebiet(rs.getString("arbeitsgebiet"));
			p.setBerufserfahrungsJahre(rs.getInt("berufserfahrungsJahre"));
			result.addElement(p);
		  
		  result.addElement(p);
	  }
	}
	    catch (SQLException e2) {
	        e2.printStackTrace();
	      }
	  return result;
}

public Partnerprofil insert(Partnerprofil pp1){
	
	Connection con = DBConnection.connection();
	
	try {
	      Statement stmt = con.createStatement();
	      
	      ResultSet rs = stmt.executeQuery("SELECT MAX(ID) AS maxid "
	              + "FROM Partnerprofil ");
	      
	
	      if(rs.next()){
	    	  
	    	  	pp1.setId(rs.getInt("maxid") + 1);
	   	  
	    	  	stmt = con.createStatement();
	    	  	
	    		stmt.executeUpdate("INSERT INTO Partnerprofil (ID, erstellungsdatum, änderungsdatum, arbeitsgebiet, berufserfahrungsJahre)" 
	    				+ "VALUES (" + pp1.getId() 
	    				+ ", " + "'" + format.format(pp1.getErstellungsdatum()) + "'" 
	    				+ ", " + "'" + format.format(pp1.getAenderungsdatum()) + "'" 
	                    + ", " + "'" + pp1.getArbeitsgebiet() 
	                    + ","  + "'" + pp1.getBerufserfahrungsJahre() +")"); 
	    	  
	      }
	}
	catch(SQLException e2){
		e2.printStackTrace();
	}
	return pp1;		
}

public void delete(Partnerprofil a){
	
	Connection con = DBConnection.connection();
	
	try {
	      Statement stmt = con.createStatement();

	      stmt.executeUpdate("DELETE FROM Partnerprofil " 
	    		  			+ "WHERE Partnerprofil.ID = " + a.getId());

		}
	
	catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

public Partnerprofil update(Partnerprofil c) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("UPDATE Partnerprofil " + "SET änderungsdatum='"
          + format.format(c.getAenderungsdatum()) + "', arbeitsgebiet= '" + c.getArbeitsgebiet() +  "', berufserfahrungsJahre= '" + c.getBerufserfahrungsJahre()
    		  + "' WHERE Partnerprofil.ID = " + c.getId());

    }
    catch (SQLException e) {
      e.printStackTrace();
    }

    return c;
  }
}