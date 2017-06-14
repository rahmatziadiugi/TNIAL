/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.MDI;

/**
 *
 * @author Someone
 */
public class ControlMDI {
    private int role;
    
    private MDI view;
    
    public ControlMDI() {        
        view = new MDI();
    }
    
    public ControlMDI(int i){
        this.role = i;
    }
    
    
}
