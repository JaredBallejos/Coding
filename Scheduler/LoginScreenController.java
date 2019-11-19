/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcmain;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import static jdbcmain.DBConnection.conn;

/**
 * FXML Controller class
 *
 * @author JBallejos
 */
public class LoginScreenController implements Initializable {
    @FXML private AnchorPane LoginAnchor;
    @FXML private TextField Location;
    @FXML private Label UserLoginLabel;
    @FXML private Label UsernameLabel;
    @FXML private Label PasswordLabel;
    @FXML private Label YourLocationLabel;
    @FXML private Button Enter;
    @FXML private TextField UsernameField;
    @FXML private TextField PasswordField;
    public static String Username;
    public static String Password;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LocationHandler();
        Locale l = Locale.getDefault();
        UserLoginLabel.setText(java.util.ResourceBundle.getBundle("jdbcmain/LoginScreenController", l).getString("USER LOGIN"));
        UsernameLabel.setText(java.util.ResourceBundle.getBundle("jdbcmain/LoginScreenController", l).getString("USERNAME"));
        PasswordLabel.setText(java.util.ResourceBundle.getBundle("jdbcmain/LoginScreenController", l).getString("PASSWORD"));
        YourLocationLabel.setText(java.util.ResourceBundle.getBundle("jdbcmain/LoginScreenController", l).getString("YOUR LOCATION:"));
        Enter.setText(java.util.ResourceBundle.getBundle("jdbcmain/LoginScreenController", l).getString("ENTER"));
        Location.setDisable(true);
    }
    private void changeScene(ActionEvent event) throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("DatabaseModifier.fxml"));
        Scene scene = new Scene(loader);
        Stage stageParts = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageParts.setScene(scene);
        stageParts.show();
    }
    @FXML
    public void EnterHandler(ActionEvent event) throws IOException {
        Statement statement2;
        String passwordEntered = PasswordField.getText();
        String usernameEntered = UsernameField.getText();
        try {
            statement2 = conn.createStatement();
            ResultSet rs1 =  statement2.executeQuery("select * from user where userName = '" + usernameEntered + "' AND password = '" +  passwordEntered + "';");
            rs1.last();
            int numRows = rs1.getRow();
            if(numRows ==1){
               Username = UsernameField.getText();
               Password = PasswordField.getText();
               Timestamp timestamp = new Timestamp(System.currentTimeMillis());
               String fileContent = usernameEntered + "     " + timestamp.toString();
               PrintWriter writer = new PrintWriter(new FileOutputStream(new File("LoginInfo.txt"), true));
               writer.append("\n");
               writer.append(fileContent);
               writer.close();
               changeScene(event);
               
            }
            else{
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle(java.util.ResourceBundle.getBundle("jdbcmain/LoginScreenController").getString("ERROR!"));
                alert.setHeaderText(java.util.ResourceBundle.getBundle("jdbcmain/LoginScreenController").getString("LOGIN ERROR!"));
                alert.setContentText(java.util.ResourceBundle.getBundle("jdbcmain/LoginScreenController").getString("THE USERNAME AND PASSWORD DID NOT MATCH."));

                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private static Locale locale = Locale.getDefault();
    @FXML
    public void LocationHandler() {
        Location.setText(locale.toString());
        
    }
    
    
    
}
