/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Someone
 */
public class BankumStatus {
    private String idStatus;
    private String ketStatus;
    
    public BankumStatus(
            String idStatus,
            String ketStatus
    ){
        this.idStatus = idStatus;
        this.ketStatus = ketStatus;
    }
    
    public String getID(){
        return this.idStatus;
    }
    
    public String getStatus(){
        return this.ketStatus;
    }
}
