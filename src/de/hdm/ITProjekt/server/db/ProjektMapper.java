package de.hdm.ITProjekt.server.db;

import de.hdm.ITProjekt.shared.bo.Projekt;
import de.hdm.ITProjekt.server.db.DBConnection;
import java.sql.*;
import java.util.Vector;

public class ProjektMapper {
	
	private static ProjektMapper pMapper = null;
	
	protected ProjektMapper(){
		
	}
	
	public static ProjektMapper pMapper(){
		if(pMapper == null){
			pMapper = new ProjektMapper();
		}
		return pMapper;
	}
	
	public Projekt findByKey(int id){
		Connection con = DBConnection.connection();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT ID, bez FROM Projekt "
          + "WHERE ID=" + id + " ORDER BY bez");
			
			if(rs.next()){
				Projekt p = new Projekt();
				p.setID(rs.getInt("ID"));
				p.setName(rs.getString("bez"));
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


