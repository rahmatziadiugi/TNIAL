///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Controller;
//
//import Database.DB4MySQL;
//import Database.DB4SQLServer;
//import View.Login;
//import View.MDI;
//import static com.sun.glass.ui.Cursor.setVisible;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import javax.swing.JOptionPane;
//
///**
// *
// * @author Someone
// */
//public class ControlLogin implements ActionListener {
//    private Login view;
//    private DB4SQLServer db = new DB4SQLServer();
//    private Connection con = null;
//    private PreparedStatement st = null;
//    private ResultSet rs = null;
//    public ControlLogin() {        
//        view = new Login();
//        view.setVisible(true);
//        view.addListener(this);        
//        view.setTitle("Login");
//    }
//    
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        Login source = new Login();
//        if(source.equals(view.getBtnLogin())) {
//
//            try {
//            Connection con = DriverManager.getConnection(this.db.getURL());
//            PreparedStatement st = con.prepareStatement("SELECT * FROM bankum_pengguna WHERE "
//                    + "username='"+view.getUsername()+"' "
//                    + "AND "
//                    + "password='"+view.getPassword()+"'");
//            ResultSet rs = st.executeQuery();
//                if(rs.next()){
//                    JOptionPane.showMessageDialog(null, "berhasil");
//                    MDI mdi = new MDI();
//                    mdi.setVisible(true);
//                    setVisible(false);
//                    //db.disconnect();
//                                     
//                    } 
//                 else{
//                    JOptionPane.showMessageDialog(null,"Username atau Password salah !");
//                }
//                rs.close();
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }       
//            //db.disconnect();
//        }
//    }
//}
