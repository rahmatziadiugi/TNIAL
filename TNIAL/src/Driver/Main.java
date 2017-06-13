/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Driver;

import View.*;

/**
 *
 * @author Someone
 */
public class Main{    
    public static void main(String[] args) {
        Login login = new Login();
        login.setTitle("NAMA APLIKASI");
        login.setVisible(true);
        
        MDI mdi = new MDI();
        mdi.setTitle("NAMA APLIKASI");
        mdi.setVisible(true);
    }
}