/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.io.File;
import jxl.Sheet;
import jxl.Workbook;
import java.io.File;
import java.io.FileInputStream;
import jxl.Cell;

/**
 *
 * @author Someone
 */
public class DB4Excel {
    private int[] rows;
    private int[] cols;
    private File f;
    private Sheet[] s;
    private Workbook wb;

    public void getDBpengguna() throws Exception {
        f = new File("./DB/Diskum/pengguna.xls");
        wb = Workbook.getWorkbook(f);        
        setExcelInfo();
    }
    
    public void getDBdiskumal() throws Exception {
        f = new File("./DB/Diskum/diskumal.xls");
        wb = Workbook.getWorkbook(f);        
        setExcelInfo();
    }
    
    public void setExcelInfo(){
        int n = wb.getNumberOfSheets();
        s = new Sheet[n];
        cols = new int[n];
        rows = new int[n];
        for(int i=0; i<n; i++){
            s[i] = wb.getSheet(0);
            rows[i] = s[i].getRows();
            cols[i] = s[i].getColumns();
        }
    }
    
//    public boolean FindWord(String word) {
//        boolean ada = false;
//        for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < cols; j++) {
//                Cell c = s.getCell(j, i);
//                if (c.getContents().equals(word)) {
//                    ada = true;
//                    break;
//                }
//            }
//        }
//        return ada;
//    }
//
//    public int FindCol(String word) {
//        int x = 0, y = 0;
//        for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < cols; j++) {
//                Cell c = s.getCell(j, i);
//                if (c.getContents().equals(word)) {
//                    x=i;
//                    y=j;
//                    break;
//                }
//            }
//        }
//        return y;
//    }
}
