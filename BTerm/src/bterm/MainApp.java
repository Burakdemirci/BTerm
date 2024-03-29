package bterm;


/**
 *
 * @author Burak Demirci
 */
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
 
public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("Fxmls/Starter.fxml")); 
        Scene scene = new Scene(root);  
        stage.setTitle("BTerm");
        stage.setScene(scene);
        stage.show();   
    }
   
    @Override
    public void stop() throws Exception {
        Platform.exit();
        System.exit(0);
    }
    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
