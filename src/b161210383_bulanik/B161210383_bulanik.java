/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package b161210383_bulanik;

import java.io.File;
import java.io.IOException;
import static java.lang.Math.abs;
import java.net.URISyntaxException;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;

/**
 *
 * @author gizemsaritas b161210383
 */
  /**
     * @param args the command line arguments
     */
public class B161210383_bulanik {
    public static double hatalar=0;
    private String inputFile;
    public void setInputFile(String inputFile){
        this.inputFile=inputFile;
    }
    public void read() throws IOException, URISyntaxException{
        double stability,error,sign,wind,magnitude,visibilty,auto;
        File inputWorkbook =new File(inputFile);
        Workbook w;
        try{
            w=Workbook.getWorkbook(inputWorkbook);
            Sheet sheet=w.getSheet(0);
            double hata=0;
            for(int j=0;j<sheet.getRows();j++){
                int columb=0;
                jxl.Cell cell=sheet.getCell(columb,j);
                System.out.println(j+1+". Veri");
                auto=Double.parseDouble(cell.getContents());
                System.out.println("auto:"+auto);
                columb++;
                cell=sheet.getCell(columb,j);
                stability=Double.parseDouble(cell.getContents());
                System.out.println("stability:"+stability);
                columb++;
                cell=sheet.getCell(columb,j);
                error=Double.parseDouble(cell.getContents());
                System.out.println("error:"+error);
                columb++;
                cell=sheet.getCell(columb,j);
                sign=Double.parseDouble(cell.getContents());
                System.out.println("sign:"+sign);
                columb++;
                cell=sheet.getCell(columb,j);
                wind=Double.parseDouble(cell.getContents());
                System.out.println("wind:"+wind);
                columb++;
                cell=sheet.getCell(columb,j);
                magnitude=Double.parseDouble(cell.getContents());
                System.out.println("magnitude:"+magnitude);
                columb++;
                cell=sheet.getCell(columb,j);
                visibilty=Double.parseDouble(cell.getContents());
                System.out.println("visibilty:"+visibilty);
                System.out.println("");
                shuttleLandingControl sControl=new shuttleLandingControl(stability, error, sign, wind, magnitude, visibilty);
                double bulunan=sControl.auto();
                hata=(abs(auto-bulunan)/auto)*100/15;
                hatalar+=hata;
                //JFuzzyChart.get().chart(sControl.getModel());
            }
        }catch(BiffException e){
            e.printStackTrace();
        }
    }
     public static void main(String[] args) throws URISyntaxException, IOException {
         B161210383_bulanik test=new B161210383_bulanik();
         test.setInputFile("../b161210383/src/b161210383/excel.xls");
         test.read();
         System.out.print("Hata oranı"+hatalar);
    }
     
}
