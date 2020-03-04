/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package b161210383_bulanik;
import java.io.File;
import java.net.URISyntaxException;
import net.sourceforge.jFuzzyLogic.FIS;

/**
 *
 * @author gizemsaritas b161210383
 */
public class shuttleLandingControl {
    private final double stability;
    private final double error;   
    private final double sign;   
    private final double wind;   
    private final double magnitude;
    private final double visibilty;     
    private FIS fis;
    public shuttleLandingControl(double stability, double error,double sign,double wind,double magnitude,double visibilty) throws URISyntaxException{
            this.stability = stability;
            this.error = error;
            this.sign = sign;
            this.wind = wind;
            this.magnitude = magnitude;
            this.visibilty = visibilty;
            
            File dosya = new File(getClass().getResource("model.fcl").toURI());
            fis = FIS.load(dosya.getPath(),true);
            fis.setVariable("stability",stability);
            fis.setVariable("error", error);
            fis.setVariable("sign", sign);
            fis.setVariable("wind", wind);
            fis.setVariable("magnitude", magnitude);
            fis.setVariable("visibilty", visibilty);
            fis.evaluate();
        }
        @Override
        public String toString(){
            String output =  "stability: "+ stability 
                           +"\n\rerror: "+ error
                           +"\n\rsign: "+ sign
                           +"\n\rwind: "+ wind
                           +"\n\rmagnitude: "+ magnitude
                           +"\n\rvisibilty: "+ visibilty;
            output += "\n\rauto/noauto: "+fis.getVariable("auto").getValue();
            return output;
        }
    FIS getModel() {
      return fis;
    } 
        public double auto(){
            return fis.getVariable("auto").getValue();
        }
}
