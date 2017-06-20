/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Database.DB4MySQL;
import Model.BankumJnsTingkat;
import Model.BankumStatus;
import Model.BankumStatusTingkat;
import Model.TunDinas;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Someone
 */
public class ControlTunDinas {
    DB4MySQL db = new DB4MySQL();
    private ArrayList<TunDinas> data = new ArrayList<>();
    private ArrayList<BankumJnsTingkat> dataJns = new ArrayList<>();
    private ArrayList<BankumStatusTingkat> dataStatusTingkat = new ArrayList<>();
    private ArrayList<BankumStatus> dataStatus = new ArrayList<>();
    
    public void getDataDBTunDinas(){
        db.connect();
        
        this.data.clear();
        
        //data Tun Dinas
        ResultSet rs = db.get("SELECT * FROM `bankum_tundinas`");
        try {
            rs.beforeFirst();
            while(rs.next()){
                this.data.add(new TunDinas(
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
        
        db.disconnect();
    }
    
    public void getDataDBDatajenis(){
        
        db.connect();
        
        this.dataJns.clear();
        
        //data jenis        
        ResultSet rs = db.get("SELECT * FROM `bankum_jnstingkat` WHERE `kdPemilik` = '04'");
        try {
            rs.beforeFirst();
            while(rs.next()){
                this.dataJns.add(new BankumJnsTingkat(
                        rs.getString("kdTingkat"), 
                        rs.getString("ketTingkat")
                ));
            }                
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(TunDinas.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        db.disconnect();
    }
    
    public void getDataDBDataStatusTingkat(){
        db.connect();
        
        this.dataStatusTingkat.clear();
        
        ResultSet rs = db.get("SELECT * FROM `bankumstatustingkat`");
        try {
            rs.beforeFirst();
            while(rs.next()){
                this.dataStatusTingkat.add(new BankumStatusTingkat(
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
    
    public void getDataDBDataStatus(){
        db.connect();
        
        this.dataStatus.clear();
        
        ResultSet rs = db.get("SELECT * FROM `bankumstatus` WHERE `kdPemilik` = '04' ");
        try {
            rs.beforeFirst();
            while(rs.next()){
                this.dataStatus.add(new BankumStatus(
                        rs.getString("idStatus"), 
                        rs.getString("ketStatus")
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
    
    public ArrayList<BankumJnsTingkat> getDataJns(){
        return this.dataJns;
    }
    
    public ArrayList<BankumStatus> getDataStatus(){
        return this.dataStatus;
    }
    
    public TunDinas getDataNo(int i){
        return this.data.get(i);
    }
    
    public String getKetTingkat(String kd){
        String temp = "-";
        for(BankumJnsTingkat jns : dataJns){
            if(jns.getkdTingkat().equals(kd)){
                temp = jns.getketTingkat();
                break;
            }
        }
        
        return temp;
    }
    
    public String getStatusnya(String id){
        String temp = "-";
        for(BankumStatusTingkat status : dataStatusTingkat){
            if(status.getId().equals(id)){
                temp = status.getKet();
                break;
            }
        }
        
        return temp;
    }
    
    public boolean tambahData(
            String lokasi,
            String dasar,
            String noSurat,
            long tgl,
            String permasalahan
    ){
        boolean berhasil = false;
        
        db.connect();
        
        try{
            java.sql.Date sqlDate = new java.sql.Date(tgl);
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            java.util.Date utilDate = new java.util.Date();
            
            if(db.manipulate("INSERT INTO `bankum_tundinas` "
                    + "(`idTundinas`, `lokasiDT`, `Dasar`, `noSurat`, `tglDasar`, `Permasalahan`, `id_status_tingkat`, `kdTingkat`, `tglStatus`) "
                    + "VALUES ('"
                    //+ String.valueOf(utilDate.getTime()) + "', '"
                    + String.valueOf(utilDate) + "', '"
                    + lokasi + "', '"
                    + dasar + "', '"
                    + noSurat + "', '"
                    + sqlDate + "', '"
                    + permasalahan + "', NULL, NULL, NULL);") >= 1)
            {
                JOptionPane.showMessageDialog(null,"Berhasil menambahkan!");
                berhasil = true;
            } else{
                JOptionPane.showMessageDialog(null,"Gagal menambahkan!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Gagal!");
        }
        
        db.disconnect();
        
        return berhasil;
    }
    
    public void deleteDisRow(
            String id
    ){
        db.connect();
        try{
            if(db.manipulate("DELETE FROM `bankum_tundinas` WHERE `idTundinas` = '" +
                    id + "';") > 0)
            {
                JOptionPane.showMessageDialog(null,"Berhasil dihapus!");
            } else{
                JOptionPane.showMessageDialog(null,"Gagal! #Feelsblackman");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Gagal!");
        }
        db.disconnect();
    }
}
