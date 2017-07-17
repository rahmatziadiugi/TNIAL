/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Database.DB4MySQL;
import Database.DB4SQLServer;
import Model.BankumJnsTingkat;
import Model.BankumStatus;
import Model.BankumStatusTingkat;
import Model.TunDinas;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
    //DB4SQLServer db = new DB4SQLServer();
    private ArrayList<TunDinas> data = new ArrayList<>();
    private ArrayList<BankumJnsTingkat> dataJns = new ArrayList<>();
    private ArrayList<BankumStatusTingkat> dataStatusTingkat = new ArrayList<>();
    private ArrayList<BankumStatus> dataStatus = new ArrayList<>();
    private DB4SQLServer db = new DB4SQLServer();
    private Connection con = null;
    private PreparedStatement st = null;
    private ResultSet rs = null;
    
    public void getDataDBTunDinas(){
        this.data.clear();
        try {
            //Class.forName(driver);
            Connection con = DriverManager.getConnection(this.db.getURL());
            PreparedStatement st = con.prepareStatement("select * from bankum_tundinas");
            ResultSet rs = st.executeQuery();
            //rs.beforeFirst();
            while(rs.next()){
                this.data.add(new TunDinas(
                        rs.getString("idTundinas"),
                        rs.getString("lokasiDT"),
                        rs.getString("Dasar"),
                        rs.getString("noSurat"),
                        rs.getDate("tglDasar"),
                        rs.getString("Permasalahan")
                ));
            }                
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(TunDinas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getDataDBDatajenis(){
        
    //    db.connect();
        
        this.dataJns.clear();
        
        //data jenis        
        //ResultSet rs = db.get("SELECT * FROM `bankum_jenistingkat` WHERE `kdPemilik` = '04'");
        try {
            Connection con = DriverManager.getConnection(db.getURL());
            PreparedStatement st = con.prepareStatement("select * from bankum_jenistingkat");
            ResultSet rs = st.executeQuery();            
//            rs.beforeFirst();
            while(rs.next()){
                this.dataJns.add(new BankumJnsTingkat(
                        rs.getString("kdTingkat"), 
                        rs.getString("ketTingkat")
                ));
            }                
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(TunDinas.class.getName()).log(Level.SEVERE, null, ex);
        }   finally {
                try { rs.close(); } catch (Exception e) { /* ignored */ }
                try { st.close(); } catch (Exception e) { /* ignored */ }
                try { con.close(); } catch (Exception e) { /* ignored */ }
        }
    }
    
    public void getDataDBDataStatusTingkat(){
        //db.connect();
        
        this.dataStatusTingkat.clear();
        
        //ResultSet rs = db.get("SELECT * FROM `bankum_statustingkat`");
        try {
            Connection con = DriverManager.getConnection(db.getURL());
            PreparedStatement st = con.prepareStatement("select * from bankum_statustingkat");
            ResultSet rs = st.executeQuery();
//            rs.beforeFirst();
            while(rs.next()){
                this.dataStatusTingkat.add(new BankumStatusTingkat(
                        rs.getString("id_status_tingkat"), 
                        rs.getString("statusTingkat")
                ));
            }                
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(TunDinas.class.getName()).log(Level.SEVERE, null, ex);
        }   finally {
                try { rs.close(); } catch (Exception e) { /* ignored */ }
                try { st.close(); } catch (Exception e) { /* ignored */ }
                try { con.close(); } catch (Exception e) { /* ignored */ }
        }
    }
    
    public void getDataDBDataStatus(){
        //db.connect();
        
        this.dataStatus.clear();
        
        //ResultSet rs = db.get("SELECT * FROM `bankum_status` WHERE `kdPemilik` = '04' ");
        try {
            Connection con = DriverManager.getConnection(db.getURL());
            PreparedStatement st = con.prepareStatement("select * from bankum_status");
            ResultSet rs = st.executeQuery();
//            rs.beforeFirst();
            while(rs.next()){
                this.dataStatus.add(new BankumStatus(
                        rs.getString("idStatus"), 
                        rs.getString("ketStatus")
                ));
            }                
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(TunDinas.class.getName()).log(Level.SEVERE, null, ex);
        }   finally {
                try { rs.close(); } catch (Exception e) { /* ignored */ }
                try { st.close(); } catch (Exception e) { /* ignored */ }
                try { con.close(); } catch (Exception e) { /* ignored */ }
        }
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
        
        //db.connect();
        
        try{
            Connection con = DriverManager.getConnection(db.getURL());
            java.sql.Date sqlDate = new java.sql.Date(tgl);
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String utilDate = dateFormat.format(new java.util.Date());

            PreparedStatement st = con.prepareStatement("INSERT INTO bankum_tundinas"
                    + "(idTundinas,lokasiDT,Dasar,noSurat,tglDasar,Permasalahan)"
                    + "values(?,?,?,?,?,?)");
            st.setString(1, utilDate);
            st.setString(2, lokasi);
            st.setString(3, dasar);
            st.setString(4, noSurat);
            st.setDate(5, sqlDate);
            st.setString(6, permasalahan);
            st.executeUpdate();
            st.close();
                        
            {
                JOptionPane.showMessageDialog(null,"Berhasil menambahkan!");
                berhasil = true;
            } 
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Gagal!");
        }   finally {
                try { rs.close(); } catch (Exception e) { /* ignored */ }
                try { st.close(); } catch (Exception e) { /* ignored */ }
                try { con.close(); } catch (Exception e) { /* ignored */ }
        }
        
        return berhasil;
    }
    
    public void deleteDisRow(String id){
        //db.connect();
        try{
            Connection con = DriverManager.getConnection(db.getURL());
            PreparedStatement st = con.prepareStatement("DELETE FROM bankum_tundinas WHERE idTundinas = '" +id+ "';");
            st.executeUpdate();
            st.close();
            {
                JOptionPane.showMessageDialog(null,"Berhasil dihapus!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Gagal!");
        }   finally {
                try { rs.close(); } catch (Exception e) { /* ignored */ }
                try { st.close(); } catch (Exception e) { /* ignored */ }
                try { con.close(); } catch (Exception e) { /* ignored */ }
        }
    }
    
    public boolean editData(
            String lokasi,
            String dasar,
            String noSurat,
            long tgl,
            String permasalahan,
            String id
    ){
        boolean berhasil = false;
        
        //db.connect();
        
        try{
            Connection con = DriverManager.getConnection(db.getURL());
            java.sql.Date sqlDate = new java.sql.Date(tgl);
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            java.util.Date utilDate = new java.util.Date();

            PreparedStatement st = con.prepareStatement("UPDATE bankum_tundinas SET"
                    + "lokasiDT = ?,Dasar = ?,noSurat = ?,tglDasar = ?,Permasalahan = ?,id_status_tingkat = NULL,kdTingkat = NULL,tglStatus = NULL"
                    + "WHERE bankum_tundinas.idTundinas = '"+id+"';");
            st.setString(1, lokasi);
            st.setString(2, dasar);
            st.setString(3, noSurat);
            st.setDate(4, sqlDate);
            st.setString(5, permasalahan);
            st.executeUpdate();
            st.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Gagal!");
        }   finally {
                try { rs.close(); } catch (Exception e) { /* ignored */ }
                try { st.close(); } catch (Exception e) { /* ignored */ }
                try { con.close(); } catch (Exception e) { /* ignored */ }
        }
        
        return berhasil;
    }
}
