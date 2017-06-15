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
public class JnsTingkat {
    private String kdTingkat;
    private String ketTingkat;
    private String kdPemilik;
    private String pemilik;
    
    public JnsTingkat(
            String kdTingkat,
            String ketTingkat,
            String kdPemilik,
            String pemilik
    )
    {
        this.kdTingkat = kdTingkat;
        this.ketTingkat = ketTingkat;
        this.kdPemilik = kdPemilik; 
        this.pemilik = pemilik;
    }
    
    public  String getkdTingkat(){
        return this.kdTingkat;
    }
    public  String getketTingkat(){
        return this.ketTingkat;
    }
    public  String getkdPemilik(){
        return this.kdPemilik;
    }
    public  String getpemilik(){
        return this.pemilik;
    }
    
}
