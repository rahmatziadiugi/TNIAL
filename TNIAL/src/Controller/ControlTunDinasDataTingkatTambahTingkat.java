/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Database.DB4SQLServer;
import Model.BankumJnsTingkat;
import Model.BankumStatus;
import Model.TunDinas;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
public class ControlTunDinasDataTingkatTambahTingkat {
    private DB4SQLServer db = new DB4SQLServer();        
    private ArrayList<BankumStatus> dataStatus = new ArrayList<>();
    private ArrayList<BankumJnsTingkat> dataJns = new ArrayList<>();
    
    public void getDataDB(){
        ArrayList<BankumStatus> temp = new ArrayList<>();
        
        try {
            Connection con = DriverManager.getConnection(db.getURL());
            PreparedStatement st = con.prepareStatement("SELECT * FROM bankum_status WHERE kdPemilik = '04'");
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                temp.add(new BankumStatus(
                        rs.getString("idStatus"), 
                        rs.getString("ketStatus")
                )
                );
            }                
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(BankumStatus.class.getName()).log(Level.SEVERE, null, ex);
        }        
        this.dataStatus = temp;
    }
    
    public void getDataDBDatajenis(){
        this.dataJns.clear();
        try {
            Connection con = DriverManager.getConnection(db.getURL());
            PreparedStatement st = con.prepareStatement("SELECT * FROM bankum_jenistingkat WHERE kdPemilik = '04'");
            ResultSet rs = st.executeQuery();
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
    }
    
    public String[] getKetJns(){
        String[] temp = new String[dataJns.size()];
        
        int i = 0;
        for(BankumJnsTingkat jns : dataJns){
            temp[i] = jns.getketTingkat();
            i++;
        }
        return temp;
    }
    
    public String[] getKetStat(){
        String[] temp = new String[dataStatus.size()];
        
        int i = 0;
        for(BankumStatus stat : dataStatus){
            temp[i] = stat.getStatus();
            i++;
        }
        return temp;
    }
    
    public boolean tambahData(
            String id,
            int kdTingkat,
            int idStatus
    ){
        boolean temp = false;        
        try{
            Connection con = DriverManager.getConnection(db.getURL());
            PreparedStatement st = con.prepareStatement("INSERT INTO bankum_tundinastingkat VALUES "
                    +"('"+ id + "', '" +
                    dataJns.get(kdTingkat).getkdTingkat() + "', '" +
                    dataStatus.get(idStatus).getID() + "', "+
                    "NULL, NULL " +  
                    ")");
                         
            if(st.executeUpdate()>0)
            {
                JOptionPane.showMessageDialog(null,"Berhasil menambahkan!");
                temp = true;
            } else{
                JOptionPane.showMessageDialog(null,"Gagal menambahkan!");
            }               
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Gagal!");
        }
        return temp;
    }
}
