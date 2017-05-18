package de.hdm.ITProjekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import de.hdm.ITProjekt.shared.bo.Bewertung;
import com.ibm.icu.text.SimpleDateFormat;

public class BewertungMapper {

	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	private static BewertungMapper bewMapper = null;
	
	protected BewertungMapper(){
		
	}
	
	public static BewertungMapper bewMapper(){
		if(bewMapper == null){
			bewMapper = new BewertungMapper();
		}
		return bewMapper;
	}
	
public Bewertung findByKey(int id){
		
		Connection con = DBConnection.connection();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT ID, text, wert, erstellungsdatum FROM Bewertung "
          + "WHERE ID=" + id);
			
			if(rs.next()){
				Bewertung bew = new Bewertung();
				bew.setId(rs.getInt("ID"));
				bew.setText(rs.getString("text"));
				bew.setWert(rs.getDouble("wert"));
				bew.setErstellungsdatum(rs.getDate("erstellungsdatum"));
				return bew;
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

	      ResultSet rs = stmt.executeQuery("SELECT ID, text, wert, erstellungsdatum FROM Bewertung");
	  
	  while (rs.next()) {
		  	Bewertung bew = new Bewertung();
			bew.setId(rs.getInt("ID"));
			bew.setText(rs.getString("text"));
			bew.setWert(rs.getDouble("wert"));
			bew.setErstellungsdatum(rs.getDate("erstellungsdatum"));
		  
		  result.addElement(bew);
	  }
	}
	    catch (SQLException e2) {
	        e2.printStackTrace();
	      }
	  return result;
}

public Bewertung insert(Bewertung bew){
	
	Connection con = DBConnection.connection();
	
	try {
	      Statement stmt = con.createStatement();
	      
	      ResultSet rs = stmt.executeQuery("SELECT MAX(ID) AS maxid "
	              + "FROM Bewertung ");
	      
	
	      if(rs.next()){
	    	  
	    	  	bew.setId(rs.getInt("maxid") + 1);
	   	  
	    	  	stmt = con.createStatement();
	    	  	
	    		stmt.executeUpdate("INSERT INTO Bewertung (ID, text, wert, erstellungsdatum)" 
	    				+ "VALUES (" + bew.getId() + ", " + "'" + bew.getText() + "'" 
	    				+ ", " + bew.getWert() + ", "+ "'" + format.format(bew.getErstellungsdatum()) + "'" 
	    				+")"); 
	    	  
	      }
	}
	catch(SQLException e2){
		e2.printStackTrace();
	}
	return bew;		
}

public void delete(Bewertung bew){
	
	Connection con = DBConnection.connection();
	
	try {
	      Statement stmt = con.createStatement();

	      stmt.executeUpdate("DELETE FROM Bewertung " 
	    		  			+ "WHERE Bewertung.ID = " + bew.getId());

		}
	
	catch (SQLException e2) {
			e2.printStackTrace();
		}
}
	public Bewertung update(Bewertung c) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      stmt.executeUpdate("UPDATE Bewertung " + "SET text='"
	          + c.getText() +  "SET wert='" + c.getWert() +"', wert= '" + format.format(c.getErstellungsdatum())
	    		  + "' WHERE Bewertung.ID = " + c.getId());

	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	    }

	    return c;
	  }
	}

