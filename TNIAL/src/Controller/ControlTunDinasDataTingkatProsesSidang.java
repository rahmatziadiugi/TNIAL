/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Database.DB4MySQL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Someone
 */
public class ControlTunDinasDataTingkatProsesSidang {
    //DB4MySQL db = new DB4MySQL();
    String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    String url = "jdbc:sqlserver://localhost:1433;"
                + "user=diskumal;"
                + "password=diskumal123;"
                +"databaseName=DISKUMAL;";
    private Connection con = null;
    private PreparedStatement st = null;
    private ResultSet rs = null;
    
    public boolean add2DB(
            long idr,
            long tgl,
            String proses,
            String stat
    ){
        boolean sukses = false;
        
        //db.connect();
        
        try{
            Connection con = DriverManager.getConnection(url);
            PreparedStatement st = con.prepareStatement("INSERT INTO bankum_tundinasproses VALUES "
                    + "('" + idr + "', '" +
                    (new java.sql.Date(tgl))+ "', '" +
                    proses + "', '" +
                     stat + "');");
//            ResultSet rs = st.executeQuery();
            if(st.executeUpdate() > 0)
            {
                JOptionPane.showMessageDialog(null,"Berhasil menambahkan data!");
                sukses = true;
            } else{
                JOptionPane.showMessageDialog(null,"Gagal!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Gagal!");
        }   finally {
                try { rs.close(); } catch (Exception e) { /* ignored */ }
                try { st.close(); } catch (Exception e) { /* ignored */ }
                try { con.close(); } catch (Exception e) { /* ignored */ }
        }
        return sukses;
    }
}
