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
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import static jdbcmain.DBConnection.conn;


/**
 * FXML Controller class
 *
 * @author JBallejos
 */
public class AppointmentController implements Initializable {
public static ObservableList<AppointmentData> oblist = FXCollections.observableArrayList();


@FXML private TableView<AppointmentData> appointmentView1;
@FXML private TableColumn<AppointmentData, Integer> appointmentId1;
@FXML private TableColumn<AppointmentData, Integer> customerId1;
@FXML private TableColumn<AppointmentData, Integer> userId1;
@FXML private TableColumn<AppointmentData, String> title1;
@FXML private TableColumn<AppointmentData, String> description1;
@FXML private TableColumn<AppointmentData, String> location1;
@FXML private TableColumn<AppointmentData, String> contact1;
@FXML private TableColumn<AppointmentData, String> type1;
@FXML private TableColumn<AppointmentData, String> Url1;
@FXML private TableColumn<AppointmentData, String> start1;
@FXML private TableColumn<AppointmentData, String> end1;
@FXML private TableColumn<AppointmentData, String> createDate1;
@FXML private TableColumn<AppointmentData, String> createdBy1;
@FXML private TableColumn<AppointmentData, String> lastUpdate1;
@FXML private TableColumn<AppointmentData, String> lastUpdateBy1;
@FXML private AnchorPane Anchor;
@FXML private Button appointmentBack;
public static  int Apptupdate = 0;
public static int ApptID;
public static int ApptCustID;
public static String ApptTitle;
public static String ApptDescription;
public static String ApptLocation;
public static String ApptContact;
public static String ApptType;
public static String ApptUrl;
public static String ApptStart;
public static String ApptEnd;
public static String ApptCreateDate;
public static String ApptCreatedBy;
public static String ApptLastUpdateBy;

@FXML private ComboBox MonthComboBox;
@FXML private TextField WeekField;
public static String MonthValue; 
    /**
     * Initializes the controller class.
     */
    private void changeScene(ActionEvent event, String fxml) throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(loader);
        Stage stageParts = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageParts.setScene(scene);
        stageParts.show();
    }
    
    public static String convertTimeZone(String date){
        String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.[S]";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        ZoneId from = ZoneId.of("America/Chicago"); //Source timezone
        ZoneId to = ZoneId.systemDefault(); //Target timezone
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter); 
                  //Current time
        //Zoned date time at source timezone
        ZonedDateTime currentISTime = dateTime.atZone(from); 
        //Zoned date time at target timezone
        ZonedDateTime currentETime = currentISTime.withZoneSameInstant(to);
        return formatter.format(currentETime);
    }
    
    public void DeleteHandler2(ActionEvent event) throws IOException {
        Statement statement;
        try {
            statement = conn.createStatement();
            AppointmentData selectedItem = appointmentView1.getSelectionModel().getSelectedItem();
            ApptID = selectedItem.getAppointmentId();
            statement.executeUpdate("delete from appointment where appointmentId = " + ApptID);
            appointmentView1.getItems().remove(selectedItem);
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void UpdateHandler2(ActionEvent event) throws IOException {
        AppointmentData selectedItem1 = appointmentView1.getSelectionModel().getSelectedItem();
        ApptID = selectedItem1.getAppointmentId();
        ApptCustID = selectedItem1.getCustomerId();
        ApptTitle = selectedItem1.getTitle();
        ApptDescription = selectedItem1.getDescription();
        ApptLocation = selectedItem1.getLocation();
        ApptContact = selectedItem1.getContact();
        ApptType = selectedItem1.getType();
        ApptUrl = selectedItem1.getUrl();
        ApptStart = selectedItem1.getStart();
        ApptEnd = selectedItem1.getEnd();
        ApptCreateDate = selectedItem1.getCreateDate();
        ApptCreatedBy = selectedItem1.getCreatedBy();
        ApptLastUpdateBy = selectedItem1.getLastUpdateBy();
        Apptupdate = 1;
        changeScene(event, "AddAppointment.fxml");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        appointmentId1.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        customerId1.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        userId1.setCellValueFactory(new PropertyValueFactory<>("userId"));
        title1.setCellValueFactory(new PropertyValueFactory<>("title"));
        description1.setCellValueFactory(new PropertyValueFactory<>("description"));
        location1.setCellValueFactory(new PropertyValueFactory<>("location"));
        contact1.setCellValueFactory(new PropertyValueFactory<>("contact"));
        type1.setCellValueFactory(new PropertyValueFactory<>("type"));
        Url1.setCellValueFactory(new PropertyValueFactory<>("url"));
        start1.setCellValueFactory(new PropertyValueFactory<>("start"));
        end1.setCellValueFactory(new PropertyValueFactory<>("end"));
        createDate1.setCellValueFactory(new PropertyValueFactory<>("createDate"));
        createdBy1.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
        lastUpdate1.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));
        lastUpdateBy1.setCellValueFactory(new PropertyValueFactory<>("lastUpdateBy"));
        appointmentView1.setItems(populateTable());
        
        MonthComboBox.getItems().removeAll(MonthComboBox.getItems());
        MonthComboBox.getItems().addAll("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12");
    }
    
public static ObservableList<AppointmentData> populateTableMonth(String month){
    oblist.clear();
    if(!(month.equals("00"))){
    try { 
        Statement statement = DBConnection.getConnection().createStatement();    
        ResultSet rs =  statement.executeQuery("select * from appointment where start like '%-" + month + "-%';");
        
        while(rs.next()){
                AppointmentData appointmentData = new AppointmentData(
                rs.getInt("appointmentId"),
                rs.getInt("customerId"),
                rs.getInt("userId"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getString("location"),
                rs.getString("contact"),
                rs.getString("type"),
                rs.getString("url"),
                convertTimeZone(rs.getString("start")),
                convertTimeZone(rs.getString("end")),
                convertTimeZone(rs.getString("createDate")),
                rs.getString("createdBy"),
                rs.getString("lastUpdate"),
                rs.getString("lastUpdateBy"));
                oblist.add(appointmentData);
                
        }
        statement.close();
        return oblist;
        } catch (SQLException ex) {
                Logger.getLogger(AppointmentController.class.getName()).log(Level.SEVERE, null, ex);
                return null;
        }
    }else{
        populateTable();
        return null;
    }
}
public static ObservableList<AppointmentData> populateTableWeek(String week){
    oblist.clear();
    int weekno = Integer.parseInt(week);
    try { 
        Statement statement = DBConnection.getConnection().createStatement();    
        ResultSet rs =  statement.executeQuery("select * from appointment where week(start,0) = " + weekno + ";");
        
        while(rs.next()){
                AppointmentData appointmentData = new AppointmentData(
                rs.getInt("appointmentId"),
                rs.getInt("customerId"),
                rs.getInt("userId"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getString("location"),
                rs.getString("contact"),
                rs.getString("type"),
                rs.getString("url"),
                convertTimeZone(rs.getString("start")),
                convertTimeZone(rs.getString("end")),
                convertTimeZone(rs.getString("createDate")),
                rs.getString("createdBy"),
                convertTimeZone(rs.getString("lastUpdate")),
                rs.getString("lastUpdateBy"));
                oblist.add(appointmentData);
                
        } 
        statement.close();
        return oblist;
        } catch (SQLException ex) {
                Logger.getLogger(AppointmentController.class.getName()).log(Level.SEVERE, null, ex);
                return null;
        }
}
public void ChoiceHandler(ActionEvent event) throws IOException{
    String value = (String) MonthComboBox.getValue();
    if (value != null){
        populateTableMonth(value);
        
    }
}
public void AddHandler2(ActionEvent event) throws IOException {
        changeScene(event, "AddAppointment.fxml");
    }


public void ChoiceHandler1(ActionEvent event) throws IOException{
    String weekNumber = WeekField.getText();
    populateTableWeek(weekNumber);
    
}
public static ObservableList<AppointmentData> populateTable(){
    oblist.clear();
    try { 
        Statement statement = DBConnection.getConnection().createStatement();    
        ResultSet rs =  statement.executeQuery("select * from appointment");
        
        while(rs.next()){
                AppointmentData appointmentData = new AppointmentData(
                rs.getInt("appointmentId"),
                rs.getInt("customerId"),
                rs.getInt("userId"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getString("location"),
                rs.getString("contact"),
                rs.getString("type"),
                rs.getString("url"),
                convertTimeZone(rs.getString("start")),
                convertTimeZone(rs.getString("end")),
                convertTimeZone(rs.getString("createDate")),
                rs.getString("createdBy"),
                convertTimeZone(rs.getString("lastUpdate")),
                rs.getString("lastUpdateBy"));
                oblist.add(appointmentData);
                
        } 
        statement.close();
        return oblist;
        } catch (SQLException ex) {
                Logger.getLogger(AppointmentController.class.getName()).log(Level.SEVERE, null, ex);
                return null;
        }
    }
    public void BackHandler(ActionEvent event) throws IOException {
        changeScene(event, "DatabaseModifier.fxml");
        
    }
    
    
    
}
