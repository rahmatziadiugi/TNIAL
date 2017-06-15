/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Database.DB4MySQL;
import Model.JnsTingkat;
import Model.StatusTingkat;
import Model.TunDinas;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Someone
 */
public class ControlTunDinas {
    DB4MySQL db = new DB4MySQL();
    private ArrayList<TunDinas> data = new ArrayList<>();
    private ArrayList<JnsTingkat> dataJns = new ArrayList<>();
    private ArrayList<StatusTingkat> dataStatus = new ArrayList<>();
    
    public void getDataDB(){
        ArrayList<TunDinas> temp = new ArrayList<>();
        ArrayList<JnsTingkat> tempJns = new ArrayList<>();
        db.connect();
        
        //data Tun Dinas
        ResultSet rs = db.get("SELECT * FROM `bankum_tundinas`");
        try {
            rs.beforeFirst();
            while(rs.next()){
                temp.add(new TunDinas(
                        rs.getString("idTundinas"),
                        rs.getString("lokasiDT"),
                        rs.getString("Dasar"),
                        rs.getString("noSurat"),
                        rs.getDate("tglDasar"),
                        rs.getString("Permasalahan"),
                        rs.getString("id_status_tingkat"),
                        rs.getString("kdTingkat"),
                        rs.getDate("tglStatus")
                ));
            }                
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(TunDinas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.data = temp;

        //data jenis        
        rs = db.get("SELECT * FROM `bankum_jnstingkat` WHERE `kdPemilik` = '04'");
        try {
            rs.beforeFirst();
            while(rs.next()){
                tempJns.add(new JnsTingkat(
                        rs.getString("kdTingkat"), 
                        rs.getString("ketTingkat"), 
                        rs.getString("kdPemilik"), 
                        rs.getString("Pemilik")
                ));
            }                
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(TunDinas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.dataJns = tempJns;
        
        rs = db.get("SELECT * FROM `bankumstatustingkat`");
        try {
            rs.beforeFirst();
            while(rs.next()){
                this.dataStatus.add(new StatusTingkat(
                        rs.getString("id_status_tingkat"), 
                        rs.getString("StatusTingkat")
                ));
            }                
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(TunDinas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        db.disconnect();
    }
    
    public ArrayList<TunDinas> getDatanya(){
        return this.data;
    }
    
    public TunDinas getDataNo(int i){
        return this.data.get(i);
    }
    
    public String getKetTingkat(String kd){
        String temp = "Tidak diketahui";
        for(JnsTingkat jns : dataJns){
            if(jns.getkdTingkat().equals(kd)){
                temp = jns.getketTingkat();
                break;
            }
        }
        
        return temp;
    }
    
    public String getStatusnya(String id){
        String temp = "Tidak diketahui";
        for(StatusTingkat status : dataStatus){
            if(status.getId().equals(id)){
                temp = status.getKet();
                break;
            }
        }
        
        return temp;
    }
}
