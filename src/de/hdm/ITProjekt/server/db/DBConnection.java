package de.hdm.ITProjekt.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.google.appengine.api.utils.SystemProperty;




public class DBConnection {
	
	private static Connection con = null;

	private static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static String localUrl = "jdbc:mysql://localhost:3306/itprojekt";
    private static String user = "root";
    private static String password = "";
    
    public static Connection connection(){
//    	
//    	if(con == null){
//    		try {
//    			
//    			
//    			con = DriverManager.getConnection(localUrl, user, password);
//    			
//    			return con;
//    			}catch(Exception e){
//    				System.out.println(e);
//    			}
//    		
//    	}
//    	return con;
//    }
//}
    	// Wenn es bisher keine Conncetion zur DB gab, ...
        if (con == null) {
            String url = null;
            try {
                if 
                (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
//                    // Load the class that provides the new
//                    // "jdbc:google:mysql://" prefix.
//                    Class.forName("com.mysql.jdbc.GoogleDriver");
//                   // url = googleUrl;
//                } else {
                    // Local MySQL instance to use during development.
                    Class.forName("com.mysql.jdbc.Driver");
                    url = localUrl;
                    
                }
                /*
                 * Dann erst kann uns der DriverManager eine Verbindung mit den
                 * oben in der Variable url angegebenen Verbindungsinformationen
                 * aufbauen.
                 * 
                 * Diese Verbindung wird dann in der statischen Variable con
                 * abgespeichert und fortan verwendet.
                 */
                con = DriverManager.getConnection(localUrl, user, password);
            } catch (Exception e) {
                con = null;
                e.printStackTrace();
            }
        }

        // Zurückgegeben der Verbindung
        return con;
    }
}



    
    		

