/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bterm;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Computer
 */
public class SettingController implements Initializable {

    @FXML
    private AnchorPane anchor_Setting;
    @FXML
    private ComboBox<String> parity = new ComboBox();
    @FXML
    private ComboBox<String> stop = new ComboBox();
    @FXML
    private ComboBox<String> data = new ComboBox();
    @FXML
    private ComboBox<String> flowControl=new ComboBox();
    @FXML
    private Button dsr_btn= new Button();
    @FXML
    private Button dtr_btn= new Button();
    
    private FXMLController root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*Add combox to value*/
        parity.getItems().addAll("NONE","ODD","EVEN","MARK","SPACE");
       
        /*---------------------*/
        data.getItems().addAll("8","7","6","5");
        
        /*--------------------*/
        stop.getItems().addAll("1","2","1.5");
        
        /*-------------------*/
        flowControl.getItems().addAll("NONE","HARDWARE");
       
        /*-------------------*/
    }    

    @FXML
    private void handleDSRButon(ActionEvent event) {
        
        if(root.serial.getDsr()==0){
            dsr_btn.setStyle("-fx-font-weight: bold; -fx-text-fill: #00ff00");
            root.serial.controlSerial(3);
        }else{
            dsr_btn.setStyle("");
            root.serial.controlSerial(4);
        }    
    }

    @FXML
    private void handleDTRButon(ActionEvent event) {
        
        if (root.serial.getDtr() == 0){
            dtr_btn.setStyle("-fx-font-weight: bold; -fx-text-fill: #00ff00");
            root.serial.controlSerial(1);
        } else {
            dtr_btn.setStyle("");
            root.serial.controlSerial(2);
        }
    }

    void passObj(FXMLController aThis) {
        root=aThis;
    }

    void setValues(String datav, String stopv, String parityv, String flowControlv, boolean dsrv, boolean dtrv) {
        parity.setValue(parityv);
        data.setValue(datav);
        stop.setValue(stopv);
        flowControl.setValue(flowControlv);
        if(dsrv)
            dsr_btn.setStyle("-fx-font-weight: bold; -fx-text-fill: #00ff00");
        if(dtrv)
            dtr_btn.setStyle("-fx-font-weight: bold; -fx-text-fill: #00ff00");
    }

    @FXML
    private void ParityChanged(ActionEvent event) {
       root.parity = parity.getValue();
    }

    @FXML
    private void StopChanged(ActionEvent event) {
        root.stop = stop.getValue();
    }

    @FXML
    private void DataChanged(ActionEvent event) {
        root.data = data.getValue();
    }

    @FXML
    private void flowControlChanged(ActionEvent event) {
        root.flowControl = flowControl.getValue();
    }

    
}
