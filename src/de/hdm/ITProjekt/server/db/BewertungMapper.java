package de.hdm.ITProjekt.server.db;

import de.hdm.ITProjekt.shared.bo.Bewertung;
import de.hdm.ITProjekt.shared.bo.Beteiligung;
import de.hdm.ITProjekt.server.db.BeteiligungMapper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.text.SimpleDateFormat;

public class BewertungMapper {

	
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	private static BewertungMapper beweMapper = null;
	
	protected BewertungMapper(){
		
	}
	
	public static BewertungMapper beweMapper(){
		if(beweMapper == null){
			beweMapper = new BewertungMapper();
		}
		return beweMapper;
	}
	
public Bewertung findByKey(int id){
		
		Connection con = DBConnection.connection();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT ID, stellungnahme, bewertung, Beteiligungs_ID, Bewerbungs_ID FROM Bewertung "
          + "WHERE ID=" + id);
			
			if(rs.next()){
				Bewertung p = new Bewertung();
				p.setID(rs.getInt("ID"));
				p.setStellungnahme(rs.getString("stellungnahme"));
				p.setBewertung(rs.getDouble("bewertung"));
				p.setBeteiligungs_ID(rs.getInt("Beteiligungs_ID"));
				p.setBewerbungs_ID(rs.getInt("Bewerbungs_ID"));
				return p;
			}
		}
		catch(SQLException e2){
			e2.printStackTrace();
			return null;
		}
		return null;	
	}

public Vector<Bewertung> getAll(){
	
	 Connection con = DBConnection.connection();	 
	
	 Vector<Bewertung> result = new Vector<Bewertung>();
	 
	  try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("SELECT ID, stellungnahme, bewertung, Beteiligungs_ID, Bewerbungs_ID FROM Bewertung ");
	  
	  while (rs.next()) {
		  	
		  	Bewertung p = new Bewertung();
			p.setID(rs.getInt("ID"));
			p.setStellungnahme(rs.getString("stellungnahme"));
			p.setBewertung(rs.getDouble("bewertung"));
			p.setBeteiligungs_ID(rs.getInt("Beteiligungs_ID"));
			p.setBewerbungs_ID(rs.getInt("Bewerbungs_ID"));
		  
		  result.addElement(p);
	  }
	}
	    catch (SQLException e2) {
	        e2.printStackTrace();
	      }
	  return result;
}

public Bewertung insert(Bewertung bew){
	
	if (bew.getBewertung() == 1.0)
  	{
		Connection con = DBConnection.connection();
		
		try {
		      Statement stmt = con.createStatement();
		      
		      ResultSet rs = stmt.executeQuery("SELECT MAX(ID) AS maxid "
		              + "FROM Bewertung ");
		      
		
		      if(rs.next()){
		    	  		    	  			    	  	
		    	  	bew.setID(rs.getInt("maxid") + 1);   	  	
		    	  	stmt = con.createStatement();
		    	  	stmt.executeUpdate("INSERT INTO Bewertung (ID, stellungnahme, bewertung, Beteiligungs_ID, Bewerbungs_ID)" 
		    	  			+ "VALUES (" + bew.getID() + ", " + "'" + bew.getStellungnahme() + "'" 
		    	  			+ ", " + bew.getBewertung() + ", " + bew.getBeteiligungs_ID() + ", " + bew.getBewerbungs_ID()
		    	  			+")"); 
			
  	}
		    	  	
	    	 else{
	    	  		
	    	  		stmt.executeUpdate("INSERT INTO Bewertung (ID, stellungnahme, bewertung, Bewerbungs_ID, Beteiligungs_ID)" 
		    				+ "VALUES (" + bew.getID() + ", " + "'" + bew.getStellungnahme() + "'" 
		    				+ ", " + bew.getBewertung() + ", " + bew.getBewerbungs_ID() + ", " + bew.getBeteiligungs_ID()
		    				+")"); 
	    	  	} 
	    	  	

	    	  
	      }
	
	catch(SQLException e2){
		e2.printStackTrace();
	}
	
  	}
	return bew;	
}
	

public void delete(Bewertung bew){
	
	Connection con = DBConnection.connection();
	
	try {
	      Statement stmt = con.createStatement();

	      stmt.executeUpdate("DELETE FROM Bewertung " 
	    		  			+ "WHERE Bewertung.ID = " + bew.getID());

		}
	
	catch (SQLException e2) {
			e2.printStackTrace();
		}
}
	public Bewertung update(Bewertung bewe) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      stmt.executeUpdate("UPDATE Bewertung " + "SET stellungnahme='"
	          + bewe.getStellungnahme() + "'" +   "SET bewertung=" + bewe.getBewertung());

	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	    }

	    return bewe;
	  }
	}

