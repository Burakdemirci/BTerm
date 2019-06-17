
package bterm.lineChart;

import java.util.ArrayList;
import bterm.communication.SerialConnection;

/**
 *
 * @author Burak Demirci
 */
public class BTermLineChart {
    
    public SerialConnection fx ;
    
    BTermLineChart(){}

    public BTermLineChart(SerialConnection serial) {
      fx=serial; 
    }
    public ArrayList<String> setValtoChart(){
        ArrayList<String> data=new ArrayList<>();
        data= fx.setValtoChart();
        return data;
    }
    
    
    public void setHeads(ArrayList<String> heads) throws Exception{
        
        lineChart lineC = new lineChart(heads);
    }

}
