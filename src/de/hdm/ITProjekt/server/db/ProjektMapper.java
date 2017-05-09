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
}


