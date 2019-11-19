/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcmain;

import java.io.IOException;
import java.util.Locale;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author JBallejos
 */
public class JDBCMain extends Application {
    private Parent root;
    private static Stage stage;
    
    @Override
    public void start(Stage stage) throws Exception {
        
        stage.setTitle("Scheduling Application");
        root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            DBConnection.makeConnection();
            launch(args);
            DBConnection.closeConnection();
            
        
}
    
}
