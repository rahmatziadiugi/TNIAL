/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Database.DB4MySQL;
import View.Login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Someone
 */
public class ControlLogin implements ActionListener {
    private Login view;
    DB4MySQL db = new DB4MySQL();
    public ControlLogin() {        
        view = new Login();
        view.setVisible(true);
        view.addListener(this);        
        view.setTitle("Login");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source.equals(view.getBtnLogin())) {
            db.connect();
            ResultSet rs = db.get("SELECT * FROM `user` WHERE "
                    + "`username` = '"+view.getUsername()+"' "
                    + "AND "
                    + "`password` = '"+view.getPassword()+"'");
            try {
                if(rs.first()){
                    ControlMDI mdi = new ControlMDI(rs.getInt("role"));                       
                    db.disconnect();
                    view.dispose();                 
                    } 
                 else{
                    JOptionPane.showMessageDialog(null,"Username atau Password salah !");
                }
                rs.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }       
            db.disconnect();
        }
    }
}
