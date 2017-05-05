package de.hdm.ITProjekt.server.db;
import de.hdm.ITProjekt.shared.bo.Projektmarktplatz;

import java.sql.*;



public class ProjektmarktplatzMapper {
	
	
	private static ProjektmarktplatzMapper pmpMapper = null;
	
	protected ProjektmarktplatzMapper(){
		
	}
	
	public static ProjektmarktplatzMapper pmpMapper(){
		if(pmpMapper == null){
			pmpMapper = new ProjektmarktplatzMapper();
		}
		return pmpMapper;
	}
	public Projektmarktplatz findByKey(int id){
		Connection con = DBConnection.connection();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT ID, bez FROM Projektmarktplatz "
          + "WHERE ID=" + id + " ORDER BY bez");
			
			if(rs.next()){
				Projektmarktplatz p = new Projektmarktplatz();
				p.setID(rs.getInt("ID"));
				p.setBez(rs.getString("bez"));
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