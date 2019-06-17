/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bterm;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Computer
 */
public class MultiButtonsController implements Initializable {

    @FXML
    private TextField multi_Name;
    @FXML
    private TextField multi_Command;
    @FXML
    private Button setButton;
    
    private FXMLController root;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void setClicked(MouseEvent event) {
        root.mCommand = multi_Command.getText();
        root.mName = multi_Name.getText();
        root.setPress = true;
        Stage stage = (Stage) setButton.getScene().getWindow();
        stage.close();
    }

    void passObj(FXMLController aThis) {
        root=aThis;
    }

    void passData(String text, String multiCommand) {
        multi_Name.setText(text);
        multi_Command.setText(multiCommand);
    }
    
}
