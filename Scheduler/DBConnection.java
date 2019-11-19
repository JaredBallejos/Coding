/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcmain;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author JBallejos
 */
public class DBConnection {
    private static final String databaseName = "U06FJM";
    private static final String DB_URL = "jdbc:mysql://52.206.157.109/" + databaseName;
    private static final String Username = "U06FJM";
    private static final String Password = "53688747810";
    private static final String driver = "com.mysql.jdbc.Driver";
    public static Connection conn;
    
    public static void makeConnection() {
       try{
       Class.forName(driver);
       conn = DriverManager.getConnection(DB_URL, Username, Password );
       }
       catch(ClassNotFoundException e){
           
       }
       catch(SQLException e){
       }
       
    }
     public static void closeConnection() {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     public static Connection getConnection() {
        return conn;
     }
}
     
