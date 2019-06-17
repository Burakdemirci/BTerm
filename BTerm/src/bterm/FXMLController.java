package bterm;
/**
 *
 * @author Burak Demirci
 */
import gnu.io.CommPortIdentifier;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import bterm.communication.SerialConnection;
import bterm.lineChart.BTermLineChart;
import bterm.MultiButtonsController;
import bterm.SettingController;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import javafx.scene.layout.AnchorPane;
import java.util.Date;
public class FXMLController implements Initializable {
    
    public SerialConnection serial = new SerialConnection();
    public String parity="NONE";
    public String data = "8";
    public String stop = "1";
    public String flowControl="NONE";
   
    @FXML
    private ComboBox<String>port = new ComboBox();
    @FXML
    private TextField speed = new TextField (); 
    @FXML
    private TextArea textArea = new TextArea();
    @FXML
    private Button serial_btn = new Button();
    @FXML
    private TextField input= new TextField();   
    @FXML
    private TextField date= new TextField(); 
    @FXML
    private TextField epochT= new TextField(); 
    @FXML
    private TextField directoy_show = new TextField();
    @FXML
    private Button excel_btn = new Button();
    @FXML
    private Button liveChart = new Button();
    @FXML
    private Button multi_1 = new Button();
    @FXML
    private Button multi_2 = new Button();
    @FXML
    private Button multi_3 = new Button();
    @FXML
    private Button multi_4 = new Button();
    @FXML
    private Button multi_5 = new Button();
    @FXML
    private Button multi_6 = new Button();
    @FXML
    private Button multi_7 = new Button();
    @FXML
    private Button multi_8 = new Button();
    @FXML
    private Button multi_9 = new Button();
    @FXML
    private Button multi_10 = new Button();
    @FXML
    private Button multi_11 = new Button();
    @FXML
    private Button multi_12 = new Button();
    @FXML
    private Button multi_13 = new Button();
    @FXML
    private Button multi_14 = new Button();
    @FXML
    private Button multi_15 = new Button();
    @FXML
    private Button multi_16 = new Button();
    @FXML
    private Button multi_17 = new Button();
    @FXML
    private Button multi_18 = new Button();
    @FXML
    private TextField system_call_text1 = new TextField();
   
    public String m1Command="\n";
    public String m2Command="\n";
    public String m3Command="\n";
    public String m4Command="\n";
    public String m5Command="\n";
    public String m6Command="\n";
    public String m7Command="\n";
    public String m8Command="\n";
    public String m9Command="\n";
    public String m10Command="\n";
    public String m11Command="\n";
    public String m12Command="\n";
    public String m13Command="\n";
    public String m14Command="\n";
    public String m15Command="\n";
    public String m16Command="\n";
    public String m17Command="\n";
    public String m18Command="\n";
    
    public String mName="";
    public String mCommand="\n";
    public boolean setPress = false;
    
    private static int excelFlag=0;
    private boolean connected = false;
    private static String strr;
    @FXML
    private AnchorPane anchor;
    @FXML
    private Label PortL;
    @FXML
    private Button select_btn;
    @FXML
    private Button run_1;
    @FXML
    private Button setting_btn;
    
   

    /*----------------------------------------------------------------------*/
    

    
    @FXML
    public void SystemCallButton_1(ActionEvent event)throws IOException{
        if(!system_call_text1.getText().isEmpty()){
            System.out.println(system_call_text1.getText().toString());
            Runtime runtime = Runtime.getRuntime();
            Process process = runtime.exec(system_call_text1.getText());
        }
    }
    
       
    @FXML
    public void Settings(ActionEvent event) throws IOException{
        
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("Fxmls/Setting.fxml"));
        try{
            Loader.load();
        }catch(IOException e){
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE,null,e);
        }
        SettingController settingController = Loader.getController();
        boolean dsr=serial.getDsr()!=0,dtr=serial.getDtr()!=0;
        settingController.setValues(data,stop, parity, flowControl,dsr,dtr);
        
        Parent p = Loader.getRoot();
        settingController.passObj(this);
        Stage stage = new Stage();
        stage.setTitle("BTerm Serial Settings");
        stage.setScene(new Scene(p));
        stage.showAndWait();
               
    }
        
    /**
     *  connect the serial port 
     * @param event 
     */
    @FXML
    public void handleSerialButon(ActionEvent event) {
        
        if(!connected){
            String speedStr = speed.getText();
            try {
                
                connected = serial.connect(port.getValue(), speedStr, data,
                        stop, parity, flowControl, textArea);

                if (connected) {
                    textArea.appendText("Connected.\n");
                    excel_btn.setDisable(false);
                    liveChart.setDisable(false);
                    input.setDisable(false);
                    serial_btn.setText("Disconnect");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            textArea.appendText("Disconnected.\n");
            connected = serial.disConnect();
            excel_btn.setDisable(true);
            liveChart.setDisable(true);
            input.setDisable(true);
            serial_btn.setText("Connect");
        }
    }
    /**
     *  initialize the application start value
     * @param url
     * @param rb 
     */    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        port.setValue("NULL");
        /*--------------------*/        
        directoy_show.setText(System.getProperty("user.home")); 
        /*Set text field and textArea font and colour*/
        textArea.setStyle("-fx-background-color: purple; -fx-text-inner-color: green;");
        textArea.setFont(Font.font(null, FontWeight.BOLD, 12));
        input.setStyle("-fx-text-inner-color: blue;");
        input.setFont(Font.font(null, FontWeight.BOLD, 17)); 
        
        LocalDate localDate = LocalDate.now();
        date.setText(DateTimeFormatter.ofPattern("dd/MM/yyy").format(localDate));
        /*------------------------------------*/
        liveChart.setDisable(true);
        excel_btn.setDisable(true);
        input.setDisable(true);
        /*-----------------------------------*/
        if(listSerialPorts()< 1)
        {
            textArea.setText("Warning !!\nThere is no available serial port\n");
            /*Wait until serial port plugin */
        }
        SetAllListener();
    }   

    
    public void SetAllListener(){
         
        multi_1.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                MouseButton button = event.getButton();
                if (button == MouseButton.PRIMARY) {
                    input.setText(m1Command);
             
                } else if (button == MouseButton.SECONDARY) {
                    
                    try {
                        Multi_FXMLoader(multi_1,m1Command);
                        if(setPress){
                            m1Command = mCommand;
                            setPress = false;
                        }
                        mCommand ="\n";
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }    
                }
            }
        });
        multi_2.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                MouseButton button = event.getButton();
                if (button == MouseButton.PRIMARY) {
                    input.setText(m2Command);
                } else if (button == MouseButton.SECONDARY) {
                    
                    try {
                        Multi_FXMLoader(multi_2,m2Command);
                        if(setPress){
                            m2Command = mCommand;
                            setPress = false;
                        }
                        mCommand ="\n";
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }    
                }
            }
        });
        multi_3.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                MouseButton button = event.getButton();
                if (button == MouseButton.PRIMARY) {
                    input.setText(m3Command);
                } else if (button == MouseButton.SECONDARY) {
                    
                    try {
                        Multi_FXMLoader(multi_3,m3Command);
                        if(setPress){
                            m3Command = mCommand;
                            setPress = false;
                        }
                        mCommand ="\n";
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }    
                }
            }
        });
        multi_4.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                MouseButton button = event.getButton();
                if (button == MouseButton.PRIMARY) {
                    input.setText(m4Command);
                } else if (button == MouseButton.SECONDARY) {
                    
                    try {
                        Multi_FXMLoader(multi_4,m4Command);
                        if(setPress){
                            m4Command = mCommand;
                            setPress = false;
                        }
                        mCommand ="\n";
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }    
                }
            }
        });
        multi_5.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                MouseButton button = event.getButton();
                if (button == MouseButton.PRIMARY) {
                    input.setText(m5Command);
                } else if (button == MouseButton.SECONDARY) {
                    
                    try {
                        Multi_FXMLoader(multi_5,m5Command);
                        if(setPress){
                            m5Command = mCommand;
                            setPress = false;
                        }
                        mCommand ="\n";
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }    
                }
            }
        });
        multi_6.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                MouseButton button = event.getButton();
                if (button == MouseButton.PRIMARY) {
                    input.setText(m6Command);
                } else if (button == MouseButton.SECONDARY) {
                    
                    try {
                        Multi_FXMLoader(multi_6,m6Command);
                        if(setPress){
                            m6Command = mCommand;
                            setPress = false;
                        }
                        mCommand ="\n";
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }    
                }
            }
        });
        multi_7.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                MouseButton button = event.getButton();
                if (button == MouseButton.PRIMARY) {
                    input.setText(m7Command);
                } else if (button == MouseButton.SECONDARY) {
                    
                    try {
                        Multi_FXMLoader(multi_7,m7Command);
                        if(setPress){
                            m7Command = mCommand;
                            setPress = false;
                        }
                        mCommand ="\n";
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }    
                }
            }
        });
        multi_8.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                MouseButton button = event.getButton();
                if (button == MouseButton.PRIMARY) {
                    input.setText(m8Command);
                } else if (button == MouseButton.SECONDARY) {
                    
                    try {
                        Multi_FXMLoader(multi_8,m8Command);
                        if(setPress){
                            m8Command = mCommand;
                            setPress = false;
                        }
                        mCommand ="\n";
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }    
                }
            }
        });
        multi_9.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                MouseButton button = event.getButton();
                if (button == MouseButton.PRIMARY) {
                    input.setText(m9Command);
                } else if (button == MouseButton.SECONDARY) {
                    
                    try {
                        Multi_FXMLoader(multi_9,m9Command);
                        if(setPress){
                            m9Command = mCommand;
                            setPress = false;
                        }
                        mCommand ="\n";
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }    
                }
            }
        });
        multi_10.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                MouseButton button = event.getButton();
                if (button == MouseButton.PRIMARY) {
                    input.setText(m10Command);
                } else if (button == MouseButton.SECONDARY) {
                    
                    try {
                        Multi_FXMLoader(multi_10,m10Command);
                        if(setPress){
                            m10Command = mCommand;
                            setPress = false;
                        }
                        mCommand ="\n";
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }    
                }
            }
        });
        multi_11.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                MouseButton button = event.getButton();
                if (button == MouseButton.PRIMARY) {
                    input.setText(m11Command);
                } else if (button == MouseButton.SECONDARY) {
                    
                    try {
                        Multi_FXMLoader(multi_11,m11Command);
                        if(setPress){
                            m11Command = mCommand;
                            setPress = false;
                        }
                        mCommand ="\n";
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }    
                }
            }
        });
        multi_12.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                MouseButton button = event.getButton();
                if (button == MouseButton.PRIMARY) {
                    input.setText(m12Command);
                } else if (button == MouseButton.SECONDARY) {
                    
                    try {
                        Multi_FXMLoader(multi_12,m12Command);
                        if(setPress){
                            m12Command = mCommand;
                            setPress = false;
                        }
                        mCommand ="\n";
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }    
                }
            }
        });
        multi_13.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                MouseButton button = event.getButton();
                if (button == MouseButton.PRIMARY) {
                    input.setText(m13Command);
                } else if (button == MouseButton.SECONDARY) {
                    
                    try {
                        Multi_FXMLoader(multi_13,m13Command);
                        if(setPress){
                            m13Command = mCommand;
                            setPress = false;
                        }
                        mCommand ="\n";
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }    
                }
            }
        });
        multi_14.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                MouseButton button = event.getButton();
                if (button == MouseButton.PRIMARY) {
                    input.setText(m14Command);
                } else if (button == MouseButton.SECONDARY) {
                    
                    try {
                        Multi_FXMLoader(multi_14,m14Command);
                        if(setPress){
                            m14Command = mCommand;
                            setPress = false;
                        }
                        mCommand ="\n";
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }    
                }
            }
        });
        multi_15.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                MouseButton button = event.getButton();
                if (button == MouseButton.PRIMARY) {
                    input.setText(m15Command);
                } else if (button == MouseButton.SECONDARY) {
                    
                    try {
                        Multi_FXMLoader(multi_15,m15Command);
                        if(setPress){
                            m15Command = mCommand;
                            setPress = false;
                        }
                        mCommand ="\n";
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }    
                }
            }
        });
        multi_16.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                MouseButton button = event.getButton();
                if (button == MouseButton.PRIMARY) {
                    input.setText(m16Command);
                } else if (button == MouseButton.SECONDARY) {
                    
                    try {
                        Multi_FXMLoader(multi_16,m16Command);
                        if(setPress){
                            m16Command = mCommand;
                            setPress = false;
                        }
                        mCommand ="\n";
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }    
                }
            }
        });
        multi_17.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                MouseButton button = event.getButton();
                if (button == MouseButton.PRIMARY) {
                    input.setText(m17Command);
                } else if (button == MouseButton.SECONDARY) {
                    
                    try {
                        Multi_FXMLoader(multi_17,m17Command);
                        if(setPress){
                            m17Command = mCommand;
                            setPress = false;
                        }
                        mCommand ="\n";
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }    
                }
            }
        });
        multi_18.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                MouseButton button = event.getButton();
                if (button == MouseButton.PRIMARY) {
                    input.setText(m18Command);
                } else if (button == MouseButton.SECONDARY) {
                    
                    try {
                        Multi_FXMLoader(multi_18,m18Command);
                        if(setPress){
                            m18Command = mCommand;
                            setPress = false;
                        }
                        mCommand ="\n";
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }    
                }
            }
        });
 
        input.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                serial.seialWrite(input.getText());
                textArea.appendText("@BTerm > " + input.getText() + "\n");
                input.setText("");
            }
        });
    }
   
    /**
     * FXML loader for multi buttons
     * @param name multi button id
     * @throws IOException 
     */
    public void Multi_FXMLoader(Button multiBtn,String multiCommand) throws IOException{
       
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("Fxmls/MultiButtons.fxml"));
        try{
            Loader.load();
        }catch(IOException e){
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE,null,e);
        }
        MultiButtonsController mltBtn = Loader.getController();
        mltBtn.passData(multiBtn.getText(),multiCommand);
        Parent p = Loader.getRoot();
        mltBtn.passObj(this);
        Stage stage = new Stage();
        stage.setScene(new Scene(p));
        stage.showAndWait();
        if(!mName.isEmpty() && setPress){
            multiBtn.setText(mName);
            mName="";
        }
    }
            
    
    /*---------------------------------------------------------------------*/
    /**
     * Finding available ports
     * @return port list
     */
    public int listSerialPorts(){
        Enumeration ports = CommPortIdentifier.getPortIdentifiers();
        int availablePort=0;
        port.getItems().removeAll(port.getItems());
        while (ports.hasMoreElements()) {
            CommPortIdentifier portV = (CommPortIdentifier) ports.nextElement();
            if (portV.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                port.getItems().add(portV.getName());
                port.setValue(portV.getName());
                availablePort++;
            }
        }
        return availablePort;
    }       
    /**
    *  Live Chart buton event
    * @param event 
    */
    @FXML
    public void liveChartButn(ActionEvent event) throws Exception {
        serial.seialWrite("-excel");
        serial.setExcelFlag(1);
        BTermLineChart chart= new BTermLineChart(serial);
        serial.drawChart(chart);
        textArea.appendText("@BTerm > "+"-excel\n");
    }
    /**
     *  Write data to the excel file
     * @param event 
     */
    @FXML
    public void handleExcel(ActionEvent event){
        if(excelFlag==0){
            serial.excelOperation(directoy_show.getText());
            serial.setExcelFlag(1);
            serial.seialWrite("-excel");
            textArea.appendText("@BTerm > "+"-excel\n");
            excelFlag=1;
        }else
            serial.setExcelFlag(0);
        
    }
    
    /**
     *  Choosing excel file directory
     * @param event 
     */
    @FXML
    public void choseDirectory(ActionEvent event){
    
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Select Directory");
        chooser.setInitialDirectory(new File(directoy_show.getText()));
        Stage child = new Stage();        
        File dir = chooser.showDialog(child);
        if (dir != null) {
            String selectedDir = dir.toString();
            directoy_show.setText(dir.getPath());
        }     
    }
    

    @FXML
    private void CalculateEpoch(MouseEvent event) {
        Date today = Calendar.getInstance().getTime();
        // Constructs a SimpleDateFormat using the given pattern
        SimpleDateFormat crunchifyFormat = new SimpleDateFormat("MMM dd yyyy HH:mm:ss.SSS zzz");

        // format() formats a Date into a date/time string.
        String currentTime = crunchifyFormat.format(today);
        try {

            // parse() parses text from the beginning of the given string to produce a date.
            Date date = crunchifyFormat.parse(currentTime);

            // getTime() returns the number of milliseconds since January 1, 1970, 00:00:00 GMT represented by this Date object.
            Long epochTime = date.getTime();
            epochT.setText(epochTime.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

   

    @FXML
    private void PortSearch(MouseEvent event) {  
       System.out.println("SEARCHING PORT");
       listSerialPorts();
    }
}
