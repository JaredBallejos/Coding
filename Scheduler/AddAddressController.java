/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcmain;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.sql.Timestamp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import static jdbcmain.DBConnection.conn;
/**
 *
 * @author JBallejos
 */
public class AddAddressController implements Initializable {
    
    @FXML private AnchorPane Anchor2;
    @FXML private TextField addressIdField;
    @FXML private TextField addressField;
    @FXML private TextField address2Field;
    @FXML private TextField cityIdField;
    @FXML private TextField postalCodeField;
    @FXML private TextField phone1;
    @FXML private TextField phone2;
    @FXML private TextField phone3;
    @FXML private TextField YYYY;
    @FXML private TextField MM;
    @FXML private TextField DD;
    @FXML private TextField HH;
    @FXML private TextField mm;
    @FXML private TextField CreatedByField;
    @FXML private TextField LastUpdateByField;
    @FXML private TableView<cityData> cityTableView;
    @FXML private TableColumn<cityData, Integer> cityIdColumn;
    @FXML private TableColumn<cityData, String> cityColumn;
    public static ObservableList<cityData> oblist1 = FXCollections.observableArrayList();
    @FXML private Button Back;
    @Override
    public void initialize(URL url, ResourceBundle rb){
       //An anonymous inner class instead of a lambda expression would take up 3 or 4 more lines of code.
       Back.setOnAction((event) -> {
       FXMLDocumentController.addrUpdate = 0;
       try{
       changeScene(event, "DatabaseModifier.fxml");
       }
       catch(Exception e){   
       }
       });
        cityIdColumn.setCellValueFactory(new PropertyValueFactory<>("cityId"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        cityTableView.setItems(populateCity());
       DefaultText();
    }
    private void changeScene(ActionEvent event, String fxml) throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(loader);
        Stage stageParts = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageParts.setScene(scene);
        stageParts.show();
    }
    @FXML
    public void DefaultText(){
       if(FXMLDocumentController.addrUpdate != 0){
       addressIdField.setText(String.valueOf(FXMLDocumentController.AddrId));
        addressField.setText(String.valueOf(FXMLDocumentController.AddrAddr));
        address2Field.setText(String.valueOf(FXMLDocumentController.AddrAddr2));
        cityIdField.setText(String.valueOf(FXMLDocumentController.AddrCityId));
        postalCodeField.setText(String.valueOf(FXMLDocumentController.AddrPostalCode));
        phone1.setText(String.valueOf(FXMLDocumentController.AddrPhone.substring(0,3)));
        phone2.setText(String.valueOf(FXMLDocumentController.AddrPhone.substring(4,7)));
        phone3.setText(String.valueOf(FXMLDocumentController.AddrPhone.substring(8,12)));
        YYYY.setText(String.valueOf(FXMLDocumentController.AddrCreateDate.substring(0, 4)));
        MM.setText(String.valueOf(FXMLDocumentController.AddrCreateDate.substring(5, 7)));
        DD.setText(String.valueOf(FXMLDocumentController.AddrCreateDate.substring(8, 10)));
        HH.setText(String.valueOf(FXMLDocumentController.AddrCreateDate.substring(11, 13)));
        mm.setText(String.valueOf(FXMLDocumentController.AddrCreateDate.substring(14, 16)));
        addressIdField.setDisable(true);
        CreatedByField.setText(String.valueOf(FXMLDocumentController.AddrCreatedBy));
        LastUpdateByField.setText(String.valueOf(FXMLDocumentController.AddrLastUpdateBy));
       }
    }
    public void checkIfNumber(KeyEvent event) {
        KeyCode code = event.getCode();
        if(!(code.isDigitKey()) && !(code.isModifierKey()) && !(code.isNavigationKey())&& !(code == KeyCode.CLEAR) && !(code == KeyCode.BACK_SPACE) && !(code == KeyCode.DELETE)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("FORMAT WRONG!");
            alert.setHeaderText("Input must be a number");
            alert.setContentText("Please Review All Entered Data");
            alert.showAndWait();
        
        }
    }
    public static ObservableList<cityData> populateCity(){
    oblist1.clear();
    
    try {
        Statement statement1 = DBConnection.getConnection().createStatement();
        ResultSet rs =  statement1.executeQuery("select cityId, city from city;");
        while(rs.next()){
                cityData CityData = new cityData(
                rs.getInt("cityId"),
                rs.getString("city"));
                oblist1.add(CityData);       
        }
        statement1.close();
        return oblist1;
        } catch (SQLException ex) {
                Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
                return null;
        }
}
    
    public int aID = 0;
    @FXML
    public void EnterHandler(ActionEvent event) throws IOException {
        try{
        Statement statement;
        Statement statement1;
        int ID = 0;
        int CITYID = 0;
        String IDEntered = addressIdField.getText();
            ID = Integer.parseInt(IDEntered);
            String addressEntered = addressField.getText();
            String address2Entered = address2Field.getText();
            String cityIdEntered = cityIdField.getText();
            
            CITYID = Integer.parseInt(cityIdEntered);
            
            String postalCodeEntered = postalCodeField.getText();
            String phoneEntered = phone1.getText() + "-" + phone2.getText() + "-" + phone3.getText();
            String createDateEntered = YYYY.getText() + "-" + MM.getText() + "-" + DD.getText() + " " + HH.getText() + ":" + mm.getText() + ":00";
            String createdByEntered = CreatedByField.getText();
            String lastUpdateByEntered = LastUpdateByField.getText();
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            int years = Integer.parseInt(YYYY.getText());
            int months = Integer.parseInt(MM.getText());
            int days = Integer.parseInt(DD.getText());
            int hours = Integer.parseInt(HH.getText());
            int minutes = Integer.parseInt(mm.getText());
        
        if(FXMLDocumentController.addrUpdate != 0){
            try {
                statement1 = conn.createStatement();
                String queryPart = "update address set ";
                statement1.executeUpdate(queryPart + " address = '" + addressEntered + "', address2 = '" + address2Entered + "', cityId =  " + CITYID + ", postalCode = '" + postalCodeEntered + "', phone = '" + phoneEntered + "', createDate = '" + createDateEntered + "', createdBy = '" + createdByEntered + "', lastUpdate = '" + timestamp + "', lastUpdateBy = '" + lastUpdateByEntered + "' where addressId = " + ID + ";");
                FXMLDocumentController.addrUpdate = 0;
                changeScene(event, "DatabaseModifier.fxml");
            } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Invalid Data!");
                alert.setHeaderText("Please check all data.");
                alert.setContentText("City ID entered must exist in the city table.  A date might be out of bounds or incorrect.");
                alert.showAndWait();
          }
        }
        else{
            try {
            statement = conn.createStatement();
            String queryPart1 = "insert into address values(";
            statement.executeUpdate(queryPart1 + ID + ", '" + addressEntered + "', '" + address2Entered + "', " + CITYID + ", '" + postalCodeEntered + "', '" + phoneEntered + "', '" + timestamp + "', '" + createdByEntered + "', '" + timestamp + "', '" + lastUpdateByEntered + "');");
            changeScene(event, "DatabaseModifier.fxml");
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Invalid Data!");
                alert.setHeaderText("Please check all data.");
                alert.setContentText("Address ID cannot be in the address table.  City ID entered must exist in the city table.  A date might be out of bounds or incorrect.");
                alert.showAndWait();
          }
         
        }
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Invalid Data!");
                alert.setHeaderText("Please check all data.");
                alert.setContentText("There was either an empty field or box, or invalid data was entered.");
                alert.showAndWait();    
            }
        
        
    }
}
