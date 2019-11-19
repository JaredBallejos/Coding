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
public class AddCustomer implements Initializable {
    @FXML private AnchorPane Anchor2;
    @FXML private TextField IdField;
    @FXML private TextField NameField;
    @FXML private TextField AddressIdField;
    @FXML private TextField ActiveField;
    @FXML private TextField YYYY;
    @FXML private TextField MM;
    @FXML private TextField DD;
    @FXML private TextField HH;
    @FXML private TextField mm;
    @FXML private TextField CreatedByField;
    @FXML private TextField LastUpdateByField;
    @FXML private TableView<addressData> addressTableView;
    @FXML private TableColumn<addressData, Integer> addressIdColumn;
    @FXML private TableColumn<addressData, String> addressColumn;
    public static ObservableList<addressData> oblist1 = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        addressIdColumn.setCellValueFactory(new PropertyValueFactory<>("addressId"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        addressTableView.setItems(populateAddress());
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
       if(FXMLDocumentController.update != 0){
        IdField.setText(String.valueOf(FXMLDocumentController.CustId));
        IdField.setDisable(true);
        NameField.setText(String.valueOf(FXMLDocumentController.CustName));
        AddressIdField.setText(String.valueOf(FXMLDocumentController.CustAddressId));
        ActiveField.setText(String.valueOf(FXMLDocumentController.CustActive));
        YYYY.setText(String.valueOf(FXMLDocumentController.CustCreateDate.substring(0, 4)));
        MM.setText(String.valueOf(FXMLDocumentController.CustCreateDate.substring(5, 7)));
        DD.setText(String.valueOf(FXMLDocumentController.CustCreateDate.substring(8, 10)));
        HH.setText(String.valueOf(FXMLDocumentController.CustCreateDate.substring(11, 13)));
        mm.setText(String.valueOf(FXMLDocumentController.CustCreateDate.substring(14, 16)));
        CreatedByField.setText(String.valueOf(FXMLDocumentController.CustCreatedBy));
        LastUpdateByField.setText(String.valueOf(FXMLDocumentController.CustLastUpdateBy));
       }
    }
    public static ObservableList<addressData> populateAddress(){
    oblist1.clear();
    
    try {
        Statement statement1 = DBConnection.getConnection().createStatement();
        ResultSet rs =  statement1.executeQuery("select addressId, address from address;");
        while(rs.next()){
                addressData AddressData = new addressData(
                rs.getInt("addressId"),
                rs.getString("address"));
                oblist1.add(AddressData);       
        }
        statement1.close();
        return oblist1;
        } catch (SQLException ex) {
                Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
                return null;
        }
    }  
    
    @FXML
    public void BackHandler(ActionEvent event) throws IOException {
        FXMLDocumentController.update = 0;
        changeScene(event, "DatabaseModifier.fxml");
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
    public int aID = 0;
    @FXML
    public void EnterHandler(ActionEvent event) throws IOException {
        try{
        Statement statement;
        Statement statement1;
            String IDEntered = IdField.getText();
            int ID = Integer.parseInt(IDEntered);
            String nameEntered = NameField.getText();
            String addressIdEntered = AddressIdField.getText();
            int ADDRESSID = Integer.parseInt(addressIdEntered);
            String activeEntered = ActiveField.getText();
            int ACTIVE = Integer.parseInt(activeEntered);
            String createDateEntered = YYYY.getText() + "-" + MM.getText() + "-" + DD.getText() + " " + HH.getText() + ":" + mm.getText() + ":00";
            String createdByEntered = CreatedByField.getText();
            String lastUpdateByEntered = LastUpdateByField.getText();
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            int years = Integer.parseInt(YYYY.getText());
            int months = Integer.parseInt(MM.getText());
            int days = Integer.parseInt(DD.getText());
            int hours = Integer.parseInt(HH.getText());
            int minutes = Integer.parseInt(mm.getText());
        
        if(FXMLDocumentController.update != 0){
            try {
            statement = conn.createStatement();
                statement1 = conn.createStatement();
                String queryPart = "update customer set ";
                statement1.executeUpdate(queryPart + "customerName = '" + nameEntered + "', addressId = " + ADDRESSID + ", active = " + ACTIVE + ", createDate = '" + createDateEntered + "', createdBy = '" + createdByEntered + "', lastUpdate = '" + timestamp + "', lastUpdateBy = '" + lastUpdateByEntered + "' where customerId = " + ID + ";");
                FXMLDocumentController.update = 0;
                changeScene(event, "DatabaseModifier.fxml");
            } catch (SQLException ex) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Invalid Data!");
                alert.setHeaderText("Please check all data.");
                alert.setContentText("Address ID entered must exist in the address table.  A date might be out of bounds or incorrect.");
                alert.showAndWait();
            }
        }
        else{
            try {
            statement = conn.createStatement();
            String queryPart1 = "insert into customer values(";
            statement.executeUpdate(queryPart1 + ID + ", '" + nameEntered + "', " + ADDRESSID + ", " + ACTIVE + ", '" + createDateEntered + "', '" + createdByEntered + "', '" + timestamp + "', '" + lastUpdateByEntered + "');");
            changeScene(event, "DatabaseModifier.fxml");
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Invalid Data!");
                alert.setHeaderText("Please check all data.");
                alert.setContentText("Customer ID cannot be in the customer table.  Address ID entered must exist in the address table.  A date might be out of bounds or incorrect.");
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
