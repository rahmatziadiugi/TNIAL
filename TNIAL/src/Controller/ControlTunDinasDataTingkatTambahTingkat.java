/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Database.DB4MySQL;
import Model.BankumJnsTingkat;
import Model.BankumStatus;
import Model.TunDinas;
import java.sql.Connection;
import java.sql.Date;
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
        //DB4MySQL db = new DB4MySQL();
    String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    String url = "jdbc:sqlserver://localhost:1433;"
                + "user=diskumal;"
                + "password=diskumal123;"
                +"databaseName=DISKUMAL;";
    private ArrayList<BankumStatus> dataStatus = new ArrayList<>();
    private ArrayList<BankumJnsTingkat> dataJns = new ArrayList<>();
    
    public void getDataDB(){
        ArrayList<BankumStatus> temp = new ArrayList<>();
        //db.connect();
        
        //ResultSet rs = db.get(
              //  "SELECT * FROM `bankum_status` WHERE `kdPemilik` = '04'");
        try {
            Connection con = DriverManager.getConnection(url);
            PreparedStatement st = con.prepareStatement("SELECT * FROM bankum_status WHERE kdPemilik = '04'");
            ResultSet rs = st.executeQuery();
            //rs.beforeFirst();
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
        
        //db.disconnect();
    }
    
    public void getDataDBDatajenis(){
        
        //db.connect();
        
        this.dataJns.clear();
        
        //data jenis        
        //ResultSet rs = db.get("SELECT * FROM `bankum_jenistingkat` WHERE `kdPemilik` = '04'");
        try {
            Connection con = DriverManager.getConnection(url);
            PreparedStatement st = con.prepareStatement("SELECT * FROM bankum_jenistingkat WHERE kdPemilik = '04'");
            ResultSet rs = st.executeQuery();
            //rs.beforeFirst();
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
                
        //db.disconnect();
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
            int idStatus,
            String ket
    ){
        boolean temp = false;
        
        //db.connect();
        //'Mon Jun 19 08:25:31 ICT 2017', 'C0', 'C8', 'asfawfaw', NULL)
        try{
            Connection con = DriverManager.getConnection(url);
            PreparedStatement st = con.prepareStatement("INSERT INTO bankum_tundinastingkat VALUES "
                    +"('"+ id + "', '" +
                    dataJns.get(kdTingkat).getkdTingkat() + "', '" +
                    dataStatus.get(idStatus).getID() + "', '"+
                    ket + "', NULL " +  
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
        
        //db.disconnect();
        
        return temp;
    }
}
