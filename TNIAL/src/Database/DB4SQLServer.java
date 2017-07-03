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
import javax.swing.JOptionPane;

/**
 *
 * @author aldebaran
 */
public class DB4SQLServer {
    private  Statement stmt = null;
    private  Connection conn ;   
    
    public DB4SQLServer(){  }

    public void connectToDB() throws SQLException, ClassNotFoundException{
        try {
          
            System.out.println("op...");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl = "jdbc:sqlserver://localhost:1433;user=diskumal;password=diskumal123;" +
            "databaseName=diskumal;";
            
            conn = DriverManager.getConnection(connectionUrl);
            
            System.out.println("Connected database successfully...");
            java.sql.Statement stmt=conn.createStatement();
            
        } catch(SQLException se){System.out.println(se.getMessage());}
          
     }

    
    public void disconnectFromDB(){

    try{
       // if (rs != null){rs.close();}
        if (stmt != null){stmt.close();}
        if (conn != null){conn.close();}
    }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, "Unable to close connection");
        }
    }
}