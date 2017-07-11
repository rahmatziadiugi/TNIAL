/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Someone
 */
public class DB4MySQL {
    private Connection conn;
    private Statement sta;

    public void connect(){
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"
                    //nama DB:
                    + "diskumal", 
                    //id user db:
                    "root", 
                    //pw user db:
                    "");
            sta = conn.createStatement();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public int manipulate(String query){
        try{      
            return sta.executeUpdate(query);
        } catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
    
    public ResultSet get(String query){
        try{
            return sta.executeQuery(query);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public void disconnect(){
        try{
            if (!conn.isClosed()){
                sta.close();
                conn.close();
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}