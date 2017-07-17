/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Someone
 */
public class TunDinas {
    private String idTundinas;
    private String LokasiDT;
    private String Dasar;
    private String noSurat;
    private Date tglDasar;
    private String Permasalahan;
    private ArrayList<TunDinasTingkat> tingkat;
    
    public TunDinas(    
            String idTundinas,
            String LokasiDT,
            String Dasar,
            String noSurat,
            Date tglDasar,
            String Permasalahan
            )
    {        
        this.idTundinas = idTundinas;
        this.LokasiDT = LokasiDT;
        this.Dasar = Dasar;
        this.noSurat = noSurat;
        this.tglDasar = tglDasar;
        this.Permasalahan = Permasalahan;        
        this.tingkat = new ArrayList<>();
    }
    
    public String getidTundinas(){
        return this.idTundinas;
    }
    public String getLokasiDT(){
        return this.LokasiDT;
    }
    public String getDasar(){
        return this.Dasar;
    }
    public String getnoSurat(){
        return this.noSurat;
    }
    public Date gettglDasar(){
        return this.tglDasar;
    }
    public String getPermasalahan(){
        return this.Permasalahan;
    }
    
    public ArrayList<TunDinasTingkat> getTingkat(){
        return this.tingkat;
    }
    
    public void setTingkat(ArrayList<TunDinasTingkat> tingkat){
        this.tingkat = tingkat;
    }
    
    public void addTingkat(TunDinasTingkat tingkat){
        this.tingkat.add(tingkat);
    }
}
