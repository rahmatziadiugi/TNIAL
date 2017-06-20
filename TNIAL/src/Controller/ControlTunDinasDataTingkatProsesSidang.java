/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Database.DB4MySQL;
import javax.swing.JOptionPane;

/**
 *
 * @author Someone
 */
public class ControlTunDinasDataTingkatProsesSidang {
    DB4MySQL db = new DB4MySQL();
    
    public boolean add2DB(
            long idr,
            long tgl,
            String proses,
            String stat
    ){
        boolean sukses = false;
        
        db.connect();
        
        try{
            if(db.manipulate("INSERT INTO `bankum_tundinasproses` (`idRtundinas`, `idR`, `tgl`, `proses`, `id_status_tingkat`) VALUES "
                    + "(NULL, '" +
                    idr + "', '" +
                    (new java.sql.Date(tgl))+ "', '" +
                    proses + "', '" +
                     stat + "');") > 0)
            {
                JOptionPane.showMessageDialog(null,"Berhasil menambahkan data!");
                sukses = true;
            } else{
                JOptionPane.showMessageDialog(null,"Gagal!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Gagal!");
        }
        
        db.disconnect();
        
        return sukses;
    }
}
