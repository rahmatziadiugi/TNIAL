/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

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
            con = DriverManager.getConnection(this.db.getURL());
            st = con.prepareStatement("SELECT t.idTundinas, lokasiDT, Dasar, noSurat, tglDasar, Permasalahan, Koordinat, MAX(tt.kdTingkat) AS tingkat " +
                    "FROM bankum_tundinas t " +
                    "LEFT OUTER JOIN bankum_tundinastingkat tt on t.idTundinas=tt.idTundinas " +
                    "GROUP by t.idTundinas, lokasiDT, Dasar, noSurat, tglDasar, Permasalahan, Koordinat");
            rs = st.executeQuery();
            int n = 0;
            //default kursor pada ResultSet adalah pada row paling atas
            while(rs.next()){//selama masih ada row selanjutnya
                this.data.add(new TunDinas(
                        rs.getString("idTundinas"), //ambil nilai dari row dengan nama kolom idTundinas
                        rs.getString("lokasiDT"),
                        rs.getString("Dasar"),
                        rs.getString("noSurat"),
                        rs.getDate("tglDasar"),
                        rs.getString("Permasalahan")
                ));
                String[] coor = new String[2];//untuk menampung koordinat latitude dan longitudenya, di database, tipe data untuk koordinat adalah string dengan format lat,lon
                if(rs.getString("Koordinat")!=null){
                    coor = rs.getString("Koordinat").split(","); //lat dan lon nya dipisahkan dengan ,
                }else{
                    coor[0] = "0";
                    coor[1] = "0";
                }
                this.data.get(n).setCoor(Double.valueOf(coor[0]), Double.valueOf(coor[1]));                
                this.data.get(n).setLastTingkat(rs.getString("tingkat"));
                n++;
            }                
            rs.close();
            st.close();
            
            //menentukan status terakhir dari tingkat terakhir untuk setiap kasus
            for(int i=0; i<this.data.size(); i++){
                if(this.data.get(i).getLastTingkat()!=null){
                    st = con.prepareStatement("SELECT TOP 1 TP.id_status_tingkat " +
                            "FROM bankum_tundinasproses TP " +
                            "JOIN bankum_tundinastingkat TT on TT.idR=TP.idR " +
                            "JOIN bankum_tundinas T on T.idTundinas=TT.idTundinas " +
                            "WHERE T.idTundinas= '" + this.data.get(i).getidTundinas() + "'" +
                            "AND " +
                            "kdTingkat = '" + this.data.get(i).getLastTingkat() + "' " +
                            "ORDER BY TP.tgl DESC");
                    rs = st.executeQuery();
                    if(rs.next()){
                        this.data.get(i).setLastStatus(rs.getString("id_status_tingkat"));
                    }
                }
                rs.close(); st.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(TunDinas.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {con.close();} catch (Exception ex) {}
            try {st.close();} catch (Exception ex) {}
        }
    }
    
    private int getIdIdx(String id){
        //untuk menentukan index dari idTundinas dalam arraylist data
        int n = 0;
        for(TunDinas i : data){
            if(i.getidTundinas().equals(id)){
                return n;
            }
            n++;
        }
        return -1;
    }

    public void getDataDBDatajenis(){
        //ambil data dari bankum_jenistingkat
        this.dataJns.clear();
        
        //data jenis        
        try {
            con = DriverManager.getConnection(db.getURL());
            st = con.prepareStatement("SELECT * FROM bankum_jenistingkat");
            rs = st.executeQuery();            
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
        //menambil data untuk status tingkat
        this.dataStatusTingkat.clear();
        
        try {
            con = DriverManager.getConnection(db.getURL());
            st = con.prepareStatement("SELECT * FROM bankum_statustingkat");
            rs = st.executeQuery();
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
        //mengambil keterangan status dengan pemilik TunDinas
        this.dataStatus.clear();
        
        try {
            con = DriverManager.getConnection(db.getURL());
            st = con.prepareStatement("SELECT * FROM bankum_status WHERE kdPemilik='04'");
            rs = st.executeQuery();
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
        //menerjemahkan kdTingkat->ketTingkat, jika belum ada tingkat maka akan return "-"
        if(kd==null){
            return "-";
        }
        for(BankumJnsTingkat jns : dataJns){
            if(jns.getkdTingkat().equals(kd)){
                return jns.getketTingkat();
            }
        }
        return "-";
    }
    
    public String getStatusnya(String id){
        //menerjemahkan status tingkatnya
        if(id==null){
            return "-";
        }
        for(BankumStatusTingkat status : dataStatusTingkat){
            if(status.getId().equals(id)){
                return status.getKet();
            }
        }        
        return "-";
    }
    
    public boolean tambahData(
            String lokasi,
            String dasar,
            String noSurat,
            long tgl,
            String permasalahan,
            String coor
    ){
        boolean berhasil = false;
        
        try{
            con = DriverManager.getConnection(db.getURL());
            //id kasus ditentukan oleh waktu dia input, untuk mempermudah menentukan kasus terbaru,
            //format tanggalnya diubah supaya hasil sortingnya nanti sesuai
            java.sql.Date sqlDate = new java.sql.Date(tgl);
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String utilDate = dateFormat.format(new java.util.Date());

            st = con.prepareStatement("INSERT INTO bankum_tundinas"
                    + "(idTundinas,lokasiDT,Dasar,noSurat,tglDasar,Permasalahan,Koordinat)"
                    + "values(?,?,?,?,?,?,?)");            
            st.setString(1, utilDate);
            st.setString(2, lokasi);
            st.setString(3, dasar);
            st.setString(4, noSurat);
            st.setDate(5, sqlDate);
            st.setString(6, permasalahan);
            st.setString(7, coor);
            st.executeUpdate();
            st.close();
                        
            {
                JOptionPane.showMessageDialog(null,"Berhasil menambahkan!");
                berhasil = true;
            } 
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Gagal!");
            ex.printStackTrace();
        }   finally {
                try { rs.close(); } catch (Exception e) { /* ignored */ }
                try { st.close(); } catch (Exception e) { /* ignored */ }
                try { con.close(); } catch (Exception e) { /* ignored */ }
        }
        
        return berhasil;
    }
    
    public void deleteDisRow(String id){
        try{
            con = DriverManager.getConnection(db.getURL());
            st = con.prepareStatement("DELETE FROM bankum_tundinas WHERE idTundinas = '" +id+ "';");
            st.executeUpdate();
            st.close();
            {
                JOptionPane.showMessageDialog(null,"Berhasil dihapus!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Gagal!");
            ex.printStackTrace();
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
            String coor,
            String id
    ){
        boolean berhasil = true;
        
        try{
            con = DriverManager.getConnection(db.getURL());
            java.sql.Date sqlDate = new java.sql.Date(tgl);

            st = con.prepareStatement("UPDATE bankum_tundinas SET "
                    + "lokasiDT = '" + lokasi + "', "
                    + "Dasar = '" + dasar + "', "
                    + "noSurat = '" + noSurat + "', "
                    + "tglDasar = '" + sqlDate + "', "
                    + "Permasalahan = '" + permasalahan + "', "
                    + "Koordinat = '" + coor + "' "
                    + "WHERE idTundinas = '" + id + "';");
            st.executeUpdate();
            st.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Gagal!");
            ex.printStackTrace();
            berhasil = false;
        }   finally {
                try { rs.close(); } catch (Exception e) { /* ignored */ }
                try { st.close(); } catch (Exception e) { /* ignored */ }
                try { con.close(); } catch (Exception e) { /* ignored */ }
        }
        
        return berhasil;
    }
}
