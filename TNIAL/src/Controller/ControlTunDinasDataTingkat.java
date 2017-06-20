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
        ResultSet rs = db.get(
                "SELECT `idR`, `idTundinas`, `ketTingkat`, `ketStatus`, `ketstat`, `Keterangan`, `File_lampiran`, `StatusTingkat`, `tglStatusAkhir` FROM `bankum_tundinastingkat` " +
                        "JOIN bankumstatus USING (`idStatus`) " +
                        "JOIN bankum_jnstingkat USING (`kdTingkat`) " +
                        "JOIN bankumstatustingkat USING (`id_status_tingkat`) WHERE `idTundinas` = '"+
                        id
                        + "'");
        try {
            rs.beforeFirst();
            while(rs.next()){
                temp.add(new TunDinasTingkat(
                        rs.getLong("idR"),
                        rs.getString("idTunDinas"),
                        rs.getString("ketTingkat"),
                        rs.getString("ketStatus"),
                        rs.getString("ketstat"),
                        rs.getString("Keterangan"), 
                        (File) rs.getBlob("File_lampiran"),
                        rs.getString("StatusTingkat"),
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
}
