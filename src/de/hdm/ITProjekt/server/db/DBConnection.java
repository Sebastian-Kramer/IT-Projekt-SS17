package de.hdm.ITProjekt.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;




public class DBConnection {
	
	private static Connection con = null;

	private static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static String localUrl = "jdbc:mysql://localhost:3308/IT Projekt";
    private static String user = "root";
    private static String password = "";
    
    public static Connection connection(){
    	
    	if(con == null){
    		try {
    			con = DriverManager.getConnection(localUrl, user, password);
    			return con;
    			}catch(Exception e){
    				System.out.println(e);
    			}
    		
    	}
    	return con;
    }
    		

}
