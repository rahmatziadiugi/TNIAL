/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;

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
    private String idStatusTingkat;
    private String kdTIngkat;
    private Date tglStatus;
    
    public TunDinas(    
            String idTundinas,
            String LokasiDT,
            String Dasar,
            String noSurat,
            Date tglDasar,
            String Permasalahan,
            String idStatusTingkat,
            String kdTIngkat,
            Date tglStatus
            )
    {        
        this.idTundinas = idTundinas;
        this.LokasiDT = LokasiDT;
        this.Dasar = Dasar;
        this.noSurat = noSurat;
        this.tglDasar = tglDasar;
        this.Permasalahan = Permasalahan;
        this.idStatusTingkat = idStatusTingkat;
        this.kdTIngkat = kdTIngkat;
        this.tglStatus = tglStatus;
        
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
    public String getidStatusTingkat(){
        return this.idStatusTingkat;
    }
    public String getkdTIngkat(){
        return this.kdTIngkat;
    }
    public Date gettglStatus(){
        return this.tglStatus;
    }
}
