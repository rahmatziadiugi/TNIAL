/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.File;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Someone
 */
public class TunDinasTingkat {
    private long idR;
    private String idTunDinas;
    private String ketTingkat;
    private String ketStatus;
    private String ket;
    private File lampiran;
    private ArrayList<TunDinasProses> proses;
            
    public TunDinasTingkat(
            long idR,
            String idTunDinas,
            String ketTingkat,
            String ketStatus,
            String ket,
            File lampiran
    ){
        this.idR = idR;
        this.idTunDinas = idTunDinas;
        this.ketTingkat = ketTingkat;
        this.ketStatus = ketStatus;
        this.ket = ket;
        this.lampiran = lampiran; 
        this.proses = new ArrayList<>();
    }
    
    public String getKdTIngkat(){
        return this.ketTingkat;
    }
        
    public String getKetStatus(){
        return this.ketStatus;
    }
    
    public String getKet(){
        return this.ket;
    }
    
    public long getIdR(){
        return this.idR;
    }
    
    public ArrayList<TunDinasProses> getProses(){
        return this.proses;
    }
    
    public void setProses(ArrayList<TunDinasProses> proses){
        this.proses = proses;
    }
    
    public void addProses(TunDinasProses proses){
        this.proses.add(proses);
    }
}
