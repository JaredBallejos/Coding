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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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

public class AddAppointmentController implements Initializable {
    @FXML private AnchorPane Anchor2;
    @FXML private TextField appointmentId;
    @FXML private TextField customerId;
    @FXML private TextField title;
    @FXML private TextField description;
    @FXML private TextField location;
    @FXML private TextField contact;
    @FXML private TextField type;
    @FXML private TextField url;
    @FXML private TextField createdBy;
    @FXML private TextField lastUpdateBy;
    @FXML private ComboBox time;
    @FXML private TextField YYYY;
    @FXML private TextField MM;
    @FXML private TextField DD;
    @FXML private TextField HH;
    @FXML private TextField mm;
    @FXML private TextField YYYY1;
    @FXML private TextField MM1;
    @FXML private TextField DD1;
    @FXML private Button Back;
    @FXML private TableView<customerData> customerTableView;
    @FXML private TableColumn<customerData, Integer> customerIdColumn;
    @FXML private TableColumn<customerData, String> customerNameColumn;
    public static ObservableList<customerData> oblist1 = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle rb){
       DefaultText();
       // Instead of using an anonymous inner class, this lambda expression makes the code more readable.
       Back.setOnAction((event) -> {
       AppointmentController.Apptupdate = 0;
       try{
       changeScene(event, "Appointment.fxml");
       }
       catch(Exception e){   
       }
       });
       
       time.getItems().removeAll(time.getItems());
       time.getItems().addAll("9:00 AM", "10:00 AM", "11:00 AM", "12:00 PM", "1:00 PM", "2:00 PM", "3:00 PM", "4:00 PM");
       customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerTableView.setItems(populateCustomer());
    }
    public static ObservableList<customerData> populateCustomer(){
    oblist1.clear();
    
    try {
        Statement statement1 = DBConnection.getConnection().createStatement();
        ResultSet rs =  statement1.executeQuery("select customerId, customerName from customer;");
        while(rs.next()){
                customerData CustomerData = new customerData(
                rs.getInt("customerId"),
                rs.getString("customerName"));
                oblist1.add(CustomerData);       
        }
        statement1.close();
        return oblist1;
        } catch (SQLException ex) {
                Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
                return null;
        }
    } 
    private void changeScene(ActionEvent event, String fxml) throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(loader);
        Stage stageParts = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageParts.setScene(scene);
        stageParts.show();
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
    
    
    
    @FXML
    public void DefaultText(){
       if(AppointmentController.Apptupdate != 0){
        appointmentId.setText(String.valueOf(AppointmentController.ApptID));
        customerId.setText(String.valueOf(AppointmentController.ApptCustID));
        title.setText(String.valueOf(AppointmentController.ApptTitle));
        description.setText(String.valueOf(AppointmentController.ApptDescription));
        location.setText(String.valueOf(AppointmentController.ApptLocation));
        contact.setText(String.valueOf(AppointmentController.ApptContact));
        type.setText(String.valueOf(AppointmentController.ApptType));
        url.setText(String.valueOf(AppointmentController.ApptUrl));
        YYYY.setText(String.valueOf(AppointmentController.ApptCreateDate.substring(0, 4)));
        MM.setText(String.valueOf(AppointmentController.ApptCreateDate.substring(5, 7)));
        DD.setText(String.valueOf(AppointmentController.ApptCreateDate.substring(8, 10)));
        HH.setText(String.valueOf(AppointmentController.ApptCreateDate.substring(11, 13)));
        mm.setText(String.valueOf(AppointmentController.ApptCreateDate.substring(14, 16)));
        YYYY1.setText(String.valueOf(AppointmentController.ApptStart.substring(0, 4)));
        MM1.setText(String.valueOf(AppointmentController.ApptStart.substring(5, 7)));
        DD1.setText(String.valueOf(AppointmentController.ApptStart.substring(8, 10)));
        appointmentId.setDisable(true);
        createdBy.setText(String.valueOf(AppointmentController.ApptCreatedBy));
        lastUpdateBy.setText(String.valueOf(AppointmentController.ApptLastUpdateBy));

       }
    }
    
    public int aID = 0;

   public String starttime(String start){
        String value = "0";
        switch (start) {
            case "9:00 AM":
                value = "09:00:00";
                break;
            case "10:00 AM":
                value = "10:00:00";
                break;
            case "11:00 AM":
                value = "11:00:00";
                break;
            case "12:00 PM":
                value = "12:00:00";
                break;
            case "1:00 PM":
                value = "13:00:00";
                break;
            case "2:00 PM":
                value = "14:00:00";
                break;
            case "3:00 PM":
                value = "15:00:00";
                break;
            case "4:00 PM":
                value = "16:00:00";
                break;
            default:
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("EMPTY FIELD!");
                alert.setHeaderText("A text field or combobox is empty");
                alert.setContentText("Please Fill Each Text Field!");
                alert.showAndWait();
                break;
        }
        return value;
    }
    public String endtime(String start){
        String value = "0";
        switch (start) {
            case "9:00 AM":
                value = "10:00:00";
                break;
            case "10:00 AM":
                value = "11:00:00";
                break;
            case "11:00 AM":
                value = "12:00:00";
                break;
            case "12:00 PM":
                value = "13:00:00";
                break;
            case "1:00 PM":
                value = "14:00:00";
                break;
            case "2:00 PM":
                value = "15:00:00";
                break;
            case "3:00 PM":
                value = "16:00:00";
                break;
            case "4:00 PM":
                value = "17:00:00";
                break;
            default:
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("EMPTY FIELD!");
                alert.setHeaderText("A text field or combobox is empty");
                alert.setContentText("Please Fill Each Text Field!");
                alert.showAndWait();
                break;
        }
        return value;
    }

    @FXML
    public void BackHandler(ActionEvent event) throws IOException {
        
    }
    @FXML
    public void EnterHandler(ActionEvent event) throws IOException {
        
        
        Statement statement;
        Statement statement1;
        Statement statement5;
        try{
            String IDEntered = appointmentId.getText();
            int ID = Integer.parseInt(IDEntered);
            String custEntered = customerId.getText();
            int CUSTID = Integer.parseInt(custEntered);
            String titleEntered = title.getText();
            String descriptionEntered = description.getText();
            String locationEntered = location.getText();
            String contactEntered = contact.getText();
            String typeEntered = type.getText();
            String urlEntered = url.getText();
            String startEnt = (String) time.getValue();
            String startEntered = YYYY1.getText() + "-" + MM1.getText() + "-" + DD1.getText() + " " + starttime(startEnt);
            String endEntered = YYYY1.getText() + "-" + MM1.getText() + "-" + DD1.getText() + " " + endtime(startEnt);
            String createDateEntered = YYYY.getText() + "-" + MM.getText() + "-" + DD.getText() + " " + HH.getText() + ":" + mm.getText() + ":00";
            String createdByEntered = createdBy.getText();
            String lastUpdateByEntered = lastUpdateBy.getText();
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String string  = dateFormat.format(new Date());
            System.out.println(string);
            int years = Integer.parseInt(YYYY.getText());
            int months = Integer.parseInt(MM.getText());
            int days = Integer.parseInt(DD.getText());
            int years1 = Integer.parseInt(YYYY1.getText());
            int months1 = Integer.parseInt(MM1.getText());
            int days1 = Integer.parseInt(DD1.getText());
            int hours = Integer.parseInt(HH.getText());
            int minutes = Integer.parseInt(mm.getText());
    
        try {
            statement5 = conn.createStatement();
            int APPTID = -1;
            ArrayList<Integer> array = new ArrayList<Integer>();
            ResultSet rs4 = statement5.executeQuery("SELECT * FROM appointment WHERE start = '" + startEntered + "' AND location = '" + locationEntered + "';");
            if(rs4.next()) {
                APPTID = rs4.getInt("appointmentId");
                array.add(APPTID);
            }
                if(array.size() >= 1) {
                    if (array.size() == 1 && APPTID == ID){
                        array.clear();
                    }
                    else{
                        array.clear();
                        throw new ArithmeticException("/ by zero");
                    }
            }   else {
                    statement5.close();  
                }
            }
            catch (SQLException ex) {
            Logger.getLogger(AddAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        if(AppointmentController.Apptupdate != 0){
            try {
                
                statement1 = conn.createStatement();
                String queryPart = "update appointment set ";
                statement1.executeUpdate(queryPart + "customerId = " + CUSTID + ", title = '" + titleEntered + "', description = '" + descriptionEntered + "', location = '" + locationEntered + "', contact = '" + contactEntered + "', type = '" + typeEntered + "', url = '" + urlEntered + "', start = '" + startEntered + "', end = '" + endEntered + "', createDate = '" + createDateEntered + "', createdBy = '" + createdByEntered + "', lastUpdate = '" + timestamp + "', lastUpdateBy = '" + lastUpdateByEntered + "' where appointmentId = " + ID + ";");
                AppointmentController.Apptupdate = 0;
                changeScene(event, "Appointment.fxml");
            } catch (SQLException ex) {
                Logger.getLogger(AddAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            
            try {
            statement = conn.createStatement();
            String queryPart1 = "insert into appointment values(";
            statement.executeUpdate(queryPart1 + ID + ", " + CUSTID + ", (select userId from user where userName = '" + LoginScreenController.Username + "' and password = '" + LoginScreenController.Password + "'), '" + titleEntered + "', '" + descriptionEntered + "', '" + locationEntered + "', '" + contactEntered + "', '" + typeEntered + "', '" + urlEntered + "', '" + startEntered + "', '" + endEntered + "', '" + createDateEntered + "', '" + createdByEntered + "', '" + timestamp + "', '" + lastUpdateByEntered + "');");
            changeScene(event, "Appointment.fxml");
        } catch (SQLException ex) {
            Logger.getLogger(AddAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        }catch(ArithmeticException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Overlapping Error");
                alert.setHeaderText("Overlapping Appointments");
                alert.setContentText("No 2 appointments can take place at the same time and location");
                alert.showAndWait();
        }catch(Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Invalid Data!");
                alert.setHeaderText("Please check all data.");
                alert.setContentText("There was either an empty field or box, or invalid data was entered.");
                alert.showAndWait();
            }   
        }
        
    
}