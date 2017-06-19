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
public class BankumJnsTingkat {
    private String kdTingkat;
    private String ketTingkat;
    
    public BankumJnsTingkat(
            String kdTingkat,
            String ketTingkat
    )
    {
        this.kdTingkat = kdTingkat;
        this.ketTingkat = ketTingkat;
    }
    
    public  String getkdTingkat(){
        return this.kdTingkat;
    }
    public  String getketTingkat(){
        return this.ketTingkat;
    }
    
}
