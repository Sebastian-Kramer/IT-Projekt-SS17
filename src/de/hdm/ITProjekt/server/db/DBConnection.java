package de.hdm.ITProjekt.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.google.appengine.api.utils.SystemProperty;




public class DBConnection {
	
	private static Connection con = null;

	private static String JDBC_DRIVER = "com.mysql.jdbc.Driver";


    private static String localUrl = "jdbc:mysql://localhost:3306/it projekt";
   
    

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

        // Zur�ckgegeben der Verbindung
        return con;
    }
}

//public class DBConnection {
//
//    /**
//     * Die Klasse DBConnection wird nur einmal instantiiert. Man spricht hierbei
//     * von einem sogenannten <b>Singleton</b>.
//     * <p>
//     * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal
//     * f�r s�mtliche eventuellen Instanzen dieser Klasse vorhanden. Sie
//     * speichert die einzige Instanz dieser Klasse.
//     * 
//     * @see AccountMapper.accountMapper()
//     * @see CustomerMapper.customerMapper()
//     */
//    private static Connection con = null;
//
//    /**
//     * Die URL, mit deren Hilfe die Datenbank angesprochen wird. In einer
//     * professionellen Applikation w�rde diese Zeichenkette aus einer
//     * Konfigurationsdatei eingelesen oder �ber einen Parameter von au�en
//     * mitgegeben, um bei einer Ver�nderung dieser URL nicht die gesamte
//     * Software neu komilieren zu m�ssen.
//     */
//    private static String googleUrl = "jdbc:google:mysql://it-projekt-ss-17:europe-west1:itprojekt/itprojekt?user=admin&password=itprojekt";
//    private static String localUrl = "jdbc:mysql://127.0.0.1:3306/it projekt?user=root&password=";
//
//    /**
//     * Diese statische Methode kann aufgrufen werden durch
//     * <code>DBConnection.connection()</code>. Sie stellt die
//     * Singleton-Eigenschaft sicher, indem Sie daf�r sorgt, dass nur eine
//     * einzige Instanz von <code>DBConnection</code> existiert.
//     * <p>
//     * 
//     * <b>Fazit:</b> DBConnection sollte nicht mittels <code>new</code>
//     * instantiiert werden, sondern stets durch Aufruf dieser statischen
//     * Methode.
//     * <p>
//     * 
//     * <b>Nachteil:</b> Bei Zusammenbruch der Verbindung zur Datenbank - dies
//     * kann z.B. durch ein unbeabsichtigtes Herunterfahren der Datenbank
//     * ausgel�st werden - wird keine neue Verbindung aufgebaut, so dass die in
//     * einem solchen Fall die gesamte Software neu zu starten ist. In einer
//     * robusten L�sung w�rde man hier die Klasse dahingehend modifizieren, dass
//     * bei einer nicht mehr funktionsf�higen Verbindung stets versucht w�rde,
//     * eine neue Verbindung aufzubauen. Dies w�rde allerdings ebenfalls den
//     * Rahmen dieses Projekts sprengen.
//     * 
//     * @return DAS <code>DBConncetion</code>-Objekt.
//     * @see con
//     */
//    public static Connection connection() {
//        // Wenn es bisher keine Conncetion zur DB gab, ...
//        if (con == null) {
//            String url = null;
//            try {
//                if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
//                    // Load the class that provides the new
//                    // "jdbc:google:mysql://" prefix.
//                    Class.forName("com.mysql.jdbc.GoogleDriver");
//                    url = googleUrl;
//                } else {
//                    // Local MySQL instance to use during development.
//                    Class.forName("com.mysql.jdbc.Driver");
//                    url = localUrl;              
//                }
//                /*
//                 * Dann erst kann uns der DriverManager eine Verbindung mit den
//                 * oben in der Variable url angegebenen Verbindungsinformationen
//                 * aufbauen.
//                 * 
//                 * Diese Verbindung wird dann in der statischen Variable con
//                 * abgespeichert und fortan verwendet.
//                 */
//                con = DriverManager.getConnection(url);
//            } catch (Exception e) {
//                con = null;
//                e.printStackTrace();
//            }
//        }
//
//        // Zur�ckgegeben der Verbindung
//        return con;
//    }
//
//}

    
    		

