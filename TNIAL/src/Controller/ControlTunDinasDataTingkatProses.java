/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Database.DB4MySQL;
import Model.TunDinasProses;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Someone
 */
public class ControlTunDinasDataTingkatProses {
    DB4MySQL db = new DB4MySQL();
    private ArrayList<TunDinasProses> data = new ArrayList<>();
    final private long idR;
    
    public ControlTunDinasDataTingkatProses(long id){
        this.idR = id;
    }
    
    public void getDataDB(){
        ArrayList<TunDinasProses> temp = new ArrayList<>();
        db.connect();
        
        ResultSet rs = db.get(
                "SELECT * FROM `bankum_tundinasproses` WHERE `idR` = '"+
                        this.idR + "' ORDER BY `tgl` ASC");
        try {
            rs.beforeFirst();
            while(rs.next()){
                
                temp.add(new TunDinasProses(
                        rs.getLong("idRtundinas"),
                        rs.getLong("idR"),
                        rs.getString("proses"),
                        rs.getString("id_status_tingkat"),
                        rs.getDate("tgl")
                )
                );
            }                
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(TunDinasProses.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.data = temp;
        
        db.disconnect();
    }
    
    public ArrayList<TunDinasProses> getDatanya(){
        return this.data;
    }
    
    public void updateDataDB(
            String ket,
            long id
    ){
        db.connect();
        
        try{
            if(db.manipulate("UPDATE `bankum_tundinastingkat` SET `ketstat` = '" +
                    ket + "' WHERE `idR` = " +
                    id + ";") > 0){
                //do something
                JOptionPane.showMessageDialog(null,"Berhasil disimpan."); 
            }else{
                JOptionPane.showMessageDialog(null,("Gagal!"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,"Gagal");                
        }
        
        db.disconnect();
    }
    
    public void deleteDataDB(long id){
        db.connect();
        
        try{
            if(db.manipulate("DELETE FROM `bankum_tundinastingkat` WHERE `idR` = " +
                    id + "") > 0){
                //do something
            }else{
                JOptionPane.showMessageDialog(null,("Gagal!"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,"Gagal");                
        }
        
        db.disconnect();
    }
    
}
