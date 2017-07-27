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
import javax.sql.DataSource;

/**
 *
 * @author aldebaran
 */
public class DB4SQLServer {
    private  Statement stmt ;
    Connection conn = null ;
    
    private final String driver;
    private final String url;
    public DB4SQLServer(){
            this.driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            this.url = "jdbc:sqlserver://10.3.18.94:1433;"
                + "user=diskumal;"
                + "password=diskumal123;"
                +"databaseName=DISKUMAL;";
    }
    
    public String getDriver(){
        return this.driver;
    }
    
    public String getURL(){
        return this.url;
    }
    
}