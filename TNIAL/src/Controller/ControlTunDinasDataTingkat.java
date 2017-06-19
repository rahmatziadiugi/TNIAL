/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Database.DB4MySQL;
import Model.TunDinasTingkat;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Someone
 */
public class ControlTunDinasDataTingkat {
    DB4MySQL db = new DB4MySQL();
    private ArrayList<TunDinasTingkat> dataDinasTingkat = new ArrayList<>();    
    
    public void getDataDB(String id){
        ArrayList<TunDinasTingkat> temp = new ArrayList<>();
        db.connect();
        
        //data Tun Dinas
        ResultSet rs = db.get("SELECT * FROM `bankum_tundinastingkat` WHERE `idTundinas` = '"+
                id
                + "'");
        try {
            rs.beforeFirst();
            while(rs.next()){
                temp.add(new TunDinasTingkat(
                        rs.getLong("idR"),
                        rs.getString("idTunDinas"),
                        rs.getString("kdTingkat"),
                        rs.getString("idStatus"),
                        rs.getString("ketstat"),
                        rs.getString("Keterangan"), 
                        (File) rs.getBlob("File_lampiran"),
                        rs.getString("id_status_tingkat"),
                        rs.getDate("tglStatusAkhir")
                ));
            }                
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(TunDinasTingkat.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.dataDinasTingkat = temp;
        
        db.disconnect();
    }
    
    public ArrayList<TunDinasTingkat> getDatanya(){
        return this.dataDinasTingkat;
    }
    
//    public boolean tambahData(
//            String lokasi,
//            String dasar,
//            String noSurat,
//            long tgl,
//            String permasalahan
//    ){
//        boolean berhasil = false;
//        
//        db.connect();
//        
//        try{
//            java.sql.Date sqlDate = new java.sql.Date(tgl);
//            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//            java.util.Date utilDate = new java.util.Date();
//            
//            if(db.manipulate("INSERT INTO `bankum_tundinas` "
//                    + "(`idTundinas`, `lokasiDT`, `Dasar`, `noSurat`, `tglDasar`, `Permasalahan`, `id_status_tingkat`, `kdTingkat`, `tglStatus`) "
//                    + "VALUES ('"
//                    //+ String.valueOf(utilDate.getTime()) + "', '"
//                    + String.valueOf(utilDate) + "', '"
//                    + lokasi + "', '"
//                    + dasar + "', '"
//                    + noSurat + "', '"
//                    + sqlDate + "', '"
//                    + permasalahan + "', NULL, NULL, NULL);") >= 1)
//            {
//                JOptionPane.showMessageDialog(null,"Berhasil menambahkan!");
//                berhasil = true;
//            } else{
//                JOptionPane.showMessageDialog(null,"Gagal menambahkan!");
//            }
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(null,"Gagal!");
//        }
//        
//        db.disconnect();
//        
//        return berhasil;
//    }
    
    
}
