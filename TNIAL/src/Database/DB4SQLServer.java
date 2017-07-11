/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import javax.swing.JOptionPane;

/**
 *
 * @author aldebaran
 */
public class DB4SQLServer {
    private  Statement stmt ;
    private DataSource dataSource ;
    //private  Connection conn ;
    Connection conn = null ;
    public DB4SQLServer(){  }

    public void connect(){
        //System.out.println("op...");
        //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String connectionUrl = "jdbc:sqlserver://localhost:1433;"
                + "user=diskumal;"
                + "password=diskumal123;"
                +"databaseName=DISKUMAL;";        
        try {
            conn = DriverManager.getConnection(connectionUrl);
            System.out.println("Connected database successfully...");
         
        }catch(SQLException ex){
            ex.printStackTrace();
        }
          
     }

    
    public void disconnect(){
        try{
            if (!conn.isClosed()){
                stmt.close();
                conn.close();
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
        
    public int manipulate(String query){
        try{      
            return stmt.executeUpdate(query);
        } catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
    
    public ResultSet get(String query){
        
        try{            
            return stmt.executeQuery(query);
            
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
//    private Connection getConnection() throws SQLException{
//        return dataSource.getConnection();
//    }
//    
//    public ResultSet get(String sql)
//    {
//        System.out.println("come here....");
//        ResultSet resultSet = null;
//        try {
//            conn = getConnection();
//            stmt = conn.createStatement();
//            resultSet = stmt.executeQuery(sql);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }finally {
//            try { if(null!=resultSet)resultSet.close();} catch (SQLException e) 
//            {e.printStackTrace();}
//            try { if(null!=stmt)stmt.close();} catch (SQLException e) 
//            {e.printStackTrace();}
//            try { if(null!=conn)conn.close();} catch (SQLException e) 
//            {e.printStackTrace();}
//        }
//        return resultSet;
//    }
}