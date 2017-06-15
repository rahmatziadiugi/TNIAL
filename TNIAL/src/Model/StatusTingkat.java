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
public class StatusTingkat {
    private String idStatus;
    private String ketStatus;
    
    public StatusTingkat(String id, String ket){
        this.idStatus = id;
        this.ketStatus = ket;
    }
    
    public String getId(){
        return this.idStatus;
    }
    
    public String getKet(){
        return this.ketStatus;
    }
}
