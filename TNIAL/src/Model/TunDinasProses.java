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
public class TunDinasProses {
    private long idRtun;
    private long idR;
    private String proses;
    private String idStatusTingkat;
    private Date tgl;
    
    public TunDinasProses(
            long idRtun,
            long idR,
            String proses,
            String idStatusTingkat,
            Date tgl
    ){
        this.idRtun = idRtun;
        this.idR = idR;
        this.proses = proses;
        this.idStatusTingkat = idStatusTingkat;
        this.tgl = tgl;
    }
    
    public String getProses(){
        return this.proses;
    }
    
    public String getIdStatusTingkat(){
        return this.idStatusTingkat;
    }
    
    public Date getDate(){
        return this.tgl;
    }
}
