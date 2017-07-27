/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Database.DB4SQLServer;
import Model.TunDinasTingkat;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
    private DB4SQLServer db = new DB4SQLServer();
    private ArrayList<TunDinasTingkat> dataDinasTingkat = new ArrayList<>();
    private Connection con = null;
    private PreparedStatement st = null;
    private ResultSet rs = null;
    
    public void getDataDB(String id){
        ArrayList<TunDinasTingkat> temp = new ArrayList<>();
        try {
            con = DriverManager.getConnection(db.getURL());
            st = con.prepareStatement("SELECT tt.idR, tt.idTundinas, jt.ketTingkat, s.ketStatus, tt.Keterangan, tt.file_lampiran" +
            " FROM bankum_tundinastingkat tt" +
            " JOIN bankum_status s ON tt.idStatus = s.idStatus" +
            " JOIN bankum_jenistingkat jt ON tt.kdTingkat = jt.kdTingkat WHERE tt.idTundinas = '"+ id + "'");
            rs = st.executeQuery();
            
            while(rs.next()){
                temp.add(new TunDinasTingkat(
                        rs.getLong("idR"),
                        rs.getString("idTundinas"),
                        rs.getString("ketTingkat"),
                        rs.getString("ketStatus"),
                        rs.getString("Keterangan"), 
                        null)
                );
            }
                            
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(TunDinasTingkat.class.getName()).log(Level.SEVERE, null, ex);
        }   finally {
                try { rs.close(); } catch (Exception e) { /* ignored */ }
                try { st.close(); } catch (Exception e) { /* ignored */ }
                try { con.close(); } catch (Exception e) { /* ignored */ }
        }
        
        this.dataDinasTingkat = temp;
    }
    
    public ArrayList<TunDinasTingkat> getDatanya(){
        return this.dataDinasTingkat;
    }        
}
