/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import Model.TunDinas;
import Model.TunDinasTingkat;
import Model.TunDinasProses;
import java.awt.Desktop;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Someone
 */
public class ControlGenerateReport {
    private String triwulan, tahun;
    private Database.DB4SQLServer db = new Database.DB4SQLServer();
    private Connection con = null;
    private PreparedStatement st = null;
    private ResultSet rs = null;
    
    private ArrayList<TunDinas> kasus = new ArrayList<>();    
    
    
    public ControlGenerateReport(){
        
    }
    
    public void getDataTriwulan(String triwulan, String tahun){
        //i: triwulan berapa
        this.triwulan = triwulan;
        this.tahun = tahun;
        //note:triwulan berdasarkan tanggal prosesnya
        
        //get data from db
        //SELECT * FROM TABLE WHERE ID IN (id1, id2, ..., idn)
        //SELECT id FROM things WHERE MONTH(happened_at) = 1 AND YEAR(happened_at) = 2009
        
        String[] bln = getListBulan(triwulan);
        try {
            Connection con = DriverManager.getConnection(db.getURL());
            PreparedStatement st = con.prepareStatement("SELECT" +
                    " bankum_tundinas.idTundinas AS [bankum_tundinas idTundinas]" +
                    " ,bankum_tundinas.lokasiDT" +
                    " ,bankum_tundinas.Dasar" +
                    " ,bankum_tundinas.Permasalahan" +
                    " ,bankum_tundinastingkat.kdTingkat AS [bankum_tundinastingkat kdTingkat]" +
                    " ,bankum_tundinastingkat.Keterangan" +
                    " ,bankum_tundinasproses.tgl" +
                    " ,bankum_tundinasproses.proses" +
                    " ,bankum_status.ketStatus" +
                    " ,bankum_jenistingkat.ketTingkat" +
                    " FROM bankum_tundinas " +
                    "LEFT OUTER JOIN bankum_tundinastingkat on bankum_tundinas.idTundinas=bankum_tundinastingkat.idTundinas " +
                    "LEFT OUTER JOIN bankum_tundinasproses on bankum_tundinastingkat.idR=bankum_tundinasproses.idR " +
                    "LEFT OUTER JOIN bankum_status on bankum_tundinastingkat.idStatus=bankum_status.idStatus " +
                    "LEFT OUTER JOIN bankum_statustingkat on bankum_tundinasproses.id_status_tingkat=bankum_statustingkat.id_status_tingkat " +
                    "LEFT OUTER JOIN bankum_jenistingkat on bankum_tundinastingkat.kdTingkat=bankum_jenistingkat.kdTingkat "
                    + "WHERE "
                    + "MONTH(tgl) IN ("+bln[0]+", "+bln[1]+", "+bln[2]+") "
                    + " AND YEAR(tgl) = ("+tahun+") "
                    + " ORDER BY bankum_tundinas.idTundinas DESC, bankum_tundinastingkat.kdTingkat ASC, bankum_tundinasproses.tgl ASC");
            
            ResultSet rs = st.executeQuery();
            
            String idKasus="-", idTingkat="-";
//            rs.beforeFirst();
            int n=-1, m=-1;
            while(rs.next()){
                if(!idKasus.equals(rs.getString("bankum_tundinas idTundinas"))){                    
                    idKasus = rs.getString("bankum_tundinas idTundinas");
                    kasus.add(new TunDinas(
                            idKasus, 
                            rs.getString("lokasiDT"), 
                            rs.getString("Dasar"), 
                            null, 
                            null, 
                            rs.getString("Permasalahan")));
                    n++;
                    m=-1;
                    idTingkat="-";
                }
                //tambah tingkat
                if(!idTingkat.equals(rs.getString("bankum_tundinastingkat kdTingkat"))){
                    idTingkat = rs.getString("bankum_tundinastingkat kdTingkat");
                    kasus.get(n).addTingkat(new TunDinasTingkat(
                        0, 
                        null, 
                        rs.getString("ketTingkat"), 
                        rs.getString("ketStatus"), 
                        rs.getString("Keterangan"), 
                        null));
                    m++;
                }
                //tambah proses
                kasus.get(n).getTingkat().
                        get(m).addProses(new TunDinasProses(
                    0, 0, 
                    rs.getString("proses"), 
                    null, 
                    rs.getDate("tgl")));
            }                
            rs.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Gagal!");
            ex.printStackTrace();
        }   finally {
                try { rs.close(); } catch (Exception e) { /* ignored */ }
                try { st.close(); } catch (Exception e) { /* ignored */ }
                try { con.close(); } catch (Exception e) { /* ignored */ }
        }
    }
    
    public String[] getListBulan(String tri){
        String[] temp = null;
        
        switch(tri){
            case "I":
                temp = new String[] {"1", "2", "3"};
                break;
            case "II":
                temp = new String[] {"4", "5", "6"};
                break;
            case "III":
                temp = new String[] {"7", "8", "9"};
                break;
            case "IV":
                temp = new String[] {"10", "11", "12"};
                break;
        }
        
        return temp;
    }
    
    public void generateNow(){        
        try {            
            Document doc  = new Document(PageSize.A4.rotate()); //842 x 595
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("results/Report.pdf"));
            doc.open();            
            //header
            PdfContentByte cb = writer.getDirectContent();
            BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            cb.saveState();
            
            cb.beginText();
            cb.moveText(10, 575); //x,y
            cb.setFontAndSize(bf, 12);            
            cb.showText("MARKAS BESAR ANGKATAN LAUT");
            cb.endText();
            
            cb.beginText();
            cb.moveText(30, 565); //x,y
            cb.setFontAndSize(bf, 12);            
            cb.showText("DINAS PEMBINAAN HUKUM");
            cb.endText();
            
            cb.beginText();
            cb.moveText(400, 555); //x,y
            cb.setFontAndSize(bf, 12);            
            cb.showText("PERKARA DINAS");
            cb.endText();
            
            cb.beginText();
            cb.moveText(300, 540); //x,y
            cb.setFontAndSize(bf, 12);            
            cb.showText("BANKUM PERKARA TATA USAHA NEGARA TA. "+String.valueOf(this.tahun));
            cb.endText();
            
            cb.beginText();
            cb.moveText(415, 525); //x,y
            cb.setFontAndSize(bf, 12);            
            cb.showText("TRIWULAN "+String.valueOf(this.triwulan));
            cb.endText();
            
            cb.restoreState();
            
            //tabel
            doc.add(new Paragraph("\n\n\n"));
            doc.add(createFirstTable(kasus));                        
                        
            doc.close();
            
            if (Desktop.isDesktopSupported()) {
                try {
                    File myFile = new File("results/Report.pdf");
                    Desktop.getDesktop().open(myFile);
                } catch (Exception ex) {
                    // no application registered for PDFs
                }
            }
            
        } catch (Exception ex) {
            Logger.getLogger(ControlGenerateReport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public PdfPTable createFirstTable(ArrayList<TunDinas> kasus) {
    	// a table with n columns        
        PdfPTable table = new PdfPTable(7);
        try {
            table.setWidths(new int[]{ 1, 5, 3, 5, 4, 3, 3});
            table.setWidthPercentage(100);
        } catch (DocumentException ex) {
            Logger.getLogger(ControlGenerateReport.class.getName()).log(Level.SEVERE, null, ex);
        }
        // the cell object
        PdfPCell cell;
        
        //Column header       
        try {
            BaseFont bf;
            bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.EMBEDDED);
            Font font = new Font(bf);
            font.setColor(new BaseColor(0x00, 0x00, 0x00));
            font.setStyle(Font.BOLD);
            cell = new PdfPCell(new Phrase("NO.", font)); table.addCell(CellStuff(cell));
            cell = new PdfPCell(new Phrase("LOKASI DAN DATA TANAH", font)); table.addCell(CellStuff(cell));
            cell = new PdfPCell(new Phrase("DASAR", font)); table.addCell(CellStuff(cell));
            cell = new PdfPCell(new Phrase("PERMASALAHAN", font)); table.addCell(CellStuff(cell));
            cell = new PdfPCell(new Phrase("STATUS", font)); table.addCell(CellStuff(cell));
            cell = new PdfPCell(new Phrase("PROSES", font)); table.addCell(CellStuff(cell));
            cell = new PdfPCell(new Phrase("KETERANGAN", font)); table.addCell(CellStuff(cell));
        } catch (Exception ex) {
            Logger.getLogger(ControlGenerateReport.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(kasus.size()<1){
            cell = new PdfPCell(new Phrase("TIDAK ADA DATA DITEMUKAN!")); 
            cell.setColspan(7);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
        }
        
        //isi tabel
        int n = 1;
        int rkasus;
        int rtingkat;
        String isicell;
        Paragraph paragraf;
        for(TunDinas i : kasus){
            // row num
            rkasus = 0;
            for(int x=0; x<i.getTingkat().size(); x++){
                rkasus += i.getTingkat().get(x).getProses().size();                
            }
            
            cell = new PdfPCell(new Phrase(String.valueOf(n)));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setRowspan(rkasus);
            table.addCell(cell);
            
            //col 1,2,3
            cell = new PdfPCell(new Paragraph(i.getLokasiDT())); //lokasi data tanah
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setRowspan(rkasus);
            table.addCell(cell);
            cell = new PdfPCell(new Paragraph(i.getDasar())); //dasar
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setRowspan(rkasus);
            table.addCell(cell);
            cell = new PdfPCell(new Paragraph(i.getPermasalahan())); //permasalahan
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setRowspan(rkasus);
            table.addCell(cell);
            
            for(TunDinasTingkat j : i.getTingkat()){
                rtingkat = j.getProses().size();
                isicell = 
                        j.getKdTIngkat() + ": " + 
                        j.getKetStatus(); //status
                paragraf = new Paragraph(isicell);
                cell = new PdfPCell(paragraf);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setRowspan(rtingkat);
                table.addCell(cell);
                
                TunDinasProses proses = j.getProses().get(0);
                isicell = 
                        proses.getDate().toString() + ": " + 
                        proses.getProses(); //proses
                paragraf = new Paragraph(isicell);
                table.addCell(paragraf);
                
                cell = new PdfPCell(new Paragraph(j.getKet())); //keterangan
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setRowspan(rtingkat);
                table.addCell(cell);
                
                if(rtingkat>1){
                    for(int k=1; k<rtingkat; k++){
                        proses = j.getProses().get(k);
                        isicell = 
                                proses.getDate().toString() + ": " + 
                                proses.getProses(); //proses                        
                        paragraf = new Paragraph(isicell);
                        table.addCell(paragraf);
                    }
                }
            }
            
            n++;
        }        
        
        
        return table;
    }
    
    public PdfPCell CellStuff(PdfPCell cell) {
        cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
        cell.setBackgroundColor(new BaseColor(0x00, 0xFF, 0xFF));
        return cell;
    }
}
