/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author aldebaran
 */
public class DBjtds {
    private static String url = "jdbc:mysql://localhost:1433/diskumal";    
    private static String driverName = "com.mysql.jdbc.Driver";   
    private static String username = "diskumal";   
    private static String password = "diskumal123";
    private static Connection con;
    private static String urlstring;

    public static void getConnection() {
        try {
            Class.forName(driverName);
            try {
                con = DriverManager.getConnection(urlstring, username, password);
            } catch (SQLException ex) {
                // log an exception. fro example:
                System.out.println("Failed to create the database connection."); 
            }
        } catch (ClassNotFoundException ex) {
            // log an exception. for example:
            System.out.println("Driver not found."); 
        }
        //return con;
    }
}
