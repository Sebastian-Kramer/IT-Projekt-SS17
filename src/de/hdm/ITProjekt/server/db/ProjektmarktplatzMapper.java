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
	public ProjektmarktplatzMapper findByKey(int id){
		Connection con = DBConnection.connection();
		
		try{
			Statement stmt = con.createStatement();
			Result rs = stmt.executeQuery("SELECT ID, bez FROM Projektmarktplatz "
          + "WHERE ID=" + id + " ORDER BY bez");
			
			if(rs.next()){
				Projektmarktplatz p = new Projektmarktplatz();
			}
		}
		
	}
	
	
	

}
