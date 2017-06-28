package de.hdm.ITProjekt.server.db;

import de.hdm.ITProjekt.shared.bo.Partnerprofil;
import de.hdm.ITProjekt.server.db.DBConnection;
import java.util.Vector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

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

			ResultSet rs = stmt.executeQuery("SELECT ID, erstellungsdatum, aenderungsdatum FROM Partnerprofil "

          + "WHERE ID=" + id);
			
			if(rs.next()){
				Partnerprofil p = new Partnerprofil();
				p.setID(rs.getInt("ID"));
				p.setErstellungsdatum(rs.getDate("erstellungsdatum"));
				p.setAenderungsdatum(rs.getDate("aenderungsdatum"));
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

	      ResultSet rs = stmt.executeQuery("SELECT ID, erstellungsdatum, aenderungsdatum FROM Partnerprofil ");
	  
	  while (rs.next()) {
		  	Partnerprofil p = new Partnerprofil();
		  	p.setID(rs.getInt("ID"));
			p.setErstellungsdatum(rs.getDate("erstellungsdatum"));
			p.setAenderungsdatum(rs.getDate("aenderungsdatum"));
			result.addElement(p);
		  
		  result.addElement(p);
	  }
	}
	    catch (SQLException e2) {
	        e2.printStackTrace();
	      }
	  return result;
}

public Vector<Partnerprofil> getAllPartnerprofile(){
	
	 Connection con = DBConnection.connection();	 
	
	 Vector<Partnerprofil> result = new Vector<Partnerprofil>();
	 
	  try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("SELECT ID, erstellungsdatum, aenderungsdatum FROM Partnerprofil ");
	  
	  while (rs.next()) {
		  	Partnerprofil p = new Partnerprofil();
		  	p.setID(rs.getInt("ID"));
			result.addElement(p);
		  
		  result.addElement(p);
	  }
	}
	    catch (SQLException e2) {
	        e2.printStackTrace();
	      }
	  return result;
}



//public Vector<Partnerprofil> getPartnerprofilByAusschreibungID(ausschreibungID){
//	Connection con = DBConnection.connection();	 
//	
//	 Vector<Partnerprofil> result = new Vector<Partnerprofil>();
//	 
//	  try {
//	      Statement stmt = con.createStatement();
//
//	      ResultSet rs = stmt.executeQuery("SELECT ID, erstellungsdatum, aenderungsdatum FROM Partnerprofil ");
//}

//public Partnerprofil insert(Partnerprofil pp1){
//	
//	Connection con = DBConnection.connection();
//	
//	try {
//	      Statement stmt = con.createStatement();
//	      
//	      ResultSet rs = stmt.executeQuery("SELECT MAX(ID) AS maxid "
//	              + "FROM Partnerprofil ");
//	      
//	
//	      if(rs.next()){
//	    	  
//	    	  	pp1.setID(rs.getInt("maxid") + 1);
//	   	  
//	    	  	stmt = con.createStatement();
//	    	  	
//	    		stmt.executeUpdate("INSERT INTO Partnerprofil (ID, erstellungsdatum, aenderungsdatum)" 
//	    				+ "VALUES (" + pp1.getID() 
//	    				+ ", " + "'" + format.format(pp1.getErstellungsdatum()) + "'" + ")"); 
//	    	  
//	      }
//	}
//	catch(SQLException e2){
//		e2.printStackTrace();
//	}
//	return pp1;		
//}


public Partnerprofil insert(Partnerprofil pp1){
	
	Connection con = DBConnection.connection();
	
	try {
	      Statement stmt = con.createStatement();
	      
	      ResultSet rs = stmt.executeQuery("SELECT MAX(ID) AS maxid "
	              + "FROM Partnerprofil ");
	      
	
	      if(rs.next()){
	    	  
	    	  	pp1.setID(rs.getInt("maxid") + 1);
	   	  
	    	  	stmt = con.createStatement();
	    	  	
	    		stmt.executeUpdate("INSERT INTO Partnerprofil (ID, `erstellungsdatum`) VALUES "
	    				+ "(" + pp1.getID() + ",'"+format.format(pp1.getErstellungsdatum()) + "')");
	    	  
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
	    		  			+ "WHERE Partnerprofil.ID = " + a.getID());

		}
	
	catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

public Partnerprofil update(Partnerprofil c) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("UPDATE Partnerprofil " + "SET aenderungsdatum='"
          + format.format(c.getAenderungsdatum()) + "', arbeitsgebiet= '" + "' WHERE Partnerprofil.ID = " + c.getID());

    }
    catch (SQLException e) {
      e.printStackTrace();
    }

    return c;
  }
}
