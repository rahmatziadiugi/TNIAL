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
    public int rows;
    public int cols;
    private File f;
    public Sheet s;
    public Workbook wb;

    public DB4Excel() throws Exception {
        f = new File("./DB/Diskum/diskumal.xls");
        wb = Workbook.getWorkbook(f);
        s = wb.getSheet(0);
        rows = s.getRows();
        cols = s.getColumns();
    }

    
    public boolean FindWord(String word) {
        boolean ada = false;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Cell c = s.getCell(j, i);
                if (c.getContents().equals(word)) {
                    ada = true;
                    break;
                }
            }
        }
        return ada;
    }

    public int FindCol(String word) {
        int x = 0, y = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Cell c = s.getCell(j, i);
                if (c.getContents().equals(word)) {
                    x=i;
                    y=j;
                    break;
                }
            }
        }
        return y;
    }
}
