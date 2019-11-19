package Software1;


import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import View_Controller.MainScreenController;


/**
 *
 * @author JBallejos
 */
public class Software1 extends Application {
    
    private AnchorPane Anchor;
    private Stage displayStage;
    
    @Override
    public void start(Stage stage) throws IOException {
        this.displayStage = stage;
        stage.setTitle("Inventory Mangement System"); 
        displayMainScreen(stage);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public void displayMainScreen(Stage stage) throws IOException {
        FXMLLoader loadScreen = new FXMLLoader();
        loadScreen.setLocation(Software1.class.getResource("/View_Controller/MainScreen.fxml"));
        this.Anchor = (AnchorPane) loadScreen.load();  

        
        MainScreenController mainController = loadScreen.getController(); 
        mainController.startMain(stage);
        mainController.setTestParts();
        mainController.setTestProducts();
    }
}
