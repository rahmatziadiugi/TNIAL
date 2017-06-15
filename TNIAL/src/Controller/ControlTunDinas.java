/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Database.DB4MySQL;
import java.util.ArrayList;

/**
 *
 * @author Someone
 */
public class ControlTunDinas {
    DB4MySQL db = new DB4MySQL();
    private ArrayList<String> data = new ArrayList<>();
    
    public void getDataDB(){
        ArrayList<String> temp = new ArrayList<>();
        db.connect();
        
        
        
        db.disconnect();
        this.data = temp;
    }
    
    public ArrayList<String> getDatanya(){
        return this.data;
    }
}
