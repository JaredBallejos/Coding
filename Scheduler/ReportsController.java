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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
public class ReportsController implements Initializable {
public static ObservableList<AppointmentData> oblist = FXCollections.observableArrayList();
public static ObservableList<userData> oblist1 = FXCollections.observableArrayList();

@FXML private TableView<AppointmentData> appointmentView1;
@FXML private TableView<AppointmentData> appointmentView11;
@FXML private TableView<AppointmentData> appointmentView111;
@FXML private TableView<userData> userTableView;
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

@FXML private TableColumn<AppointmentData, Integer> appointmentId11;
@FXML private TableColumn<AppointmentData, Integer> customerId11;
@FXML private TableColumn<AppointmentData, Integer> userId11;
@FXML private TableColumn<AppointmentData, String> title11;
@FXML private TableColumn<AppointmentData, String> description11;
@FXML private TableColumn<AppointmentData, String> location11;
@FXML private TableColumn<AppointmentData, String> contact11;
@FXML private TableColumn<AppointmentData, String> type11;
@FXML private TableColumn<AppointmentData, String> Url11;
@FXML private TableColumn<AppointmentData, String> start11;
@FXML private TableColumn<AppointmentData, String> end11;
@FXML private TableColumn<AppointmentData, String> createDate11;
@FXML private TableColumn<AppointmentData, String> createdBy11;
@FXML private TableColumn<AppointmentData, String> lastUpdate11;
@FXML private TableColumn<AppointmentData, String> lastUpdateBy11;

@FXML private TableColumn<AppointmentData, Integer> appointmentId111;
@FXML private TableColumn<AppointmentData, Integer> customerId111;
@FXML private TableColumn<AppointmentData, Integer> userId111;
@FXML private TableColumn<AppointmentData, String> title111;
@FXML private TableColumn<AppointmentData, String> description111;
@FXML private TableColumn<AppointmentData, String> location111;
@FXML private TableColumn<AppointmentData, String> contact111;
@FXML private TableColumn<AppointmentData, String> type111;
@FXML private TableColumn<AppointmentData, String> Url111;
@FXML private TableColumn<AppointmentData, String> start111;
@FXML private TableColumn<AppointmentData, String> end111;
@FXML private TableColumn<AppointmentData, String> createDate111;
@FXML private TableColumn<AppointmentData, String> createdBy111;
@FXML private TableColumn<AppointmentData, String> lastUpdate111;
@FXML private TableColumn<AppointmentData, String> lastUpdateBy111;



@FXML private TableColumn<userData, Integer> userIdColumn;
@FXML private TableColumn<userData, Integer> userNameColumn;
@FXML private AnchorPane Anchor;
public static  String ctType;
public static int ApptID;
public static int ApptCustID;
public static int ApptUserID;
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
@FXML private TextField ConsultantText;
@FXML private TextField LocationText;
public static String MonthValue; 
    /**
     * Initializes the controller class.
     */
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
        appointmentView1.setItems(populateTable1());
        
        appointmentId11.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        customerId11.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        userId11.setCellValueFactory(new PropertyValueFactory<>("userId"));
        title11.setCellValueFactory(new PropertyValueFactory<>("title"));
        description11.setCellValueFactory(new PropertyValueFactory<>("description"));
        location11.setCellValueFactory(new PropertyValueFactory<>("location"));
        contact11.setCellValueFactory(new PropertyValueFactory<>("contact"));
        type11.setCellValueFactory(new PropertyValueFactory<>("type"));
        Url11.setCellValueFactory(new PropertyValueFactory<>("url"));
        start11.setCellValueFactory(new PropertyValueFactory<>("start"));
        end11.setCellValueFactory(new PropertyValueFactory<>("end"));
        createDate11.setCellValueFactory(new PropertyValueFactory<>("createDate"));
        createdBy11.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
        lastUpdate11.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));
        lastUpdateBy11.setCellValueFactory(new PropertyValueFactory<>("lastUpdateBy"));
        appointmentView11.setItems(populateTable1());
        
        appointmentId111.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        customerId111.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        userId111.setCellValueFactory(new PropertyValueFactory<>("userId"));
        title111.setCellValueFactory(new PropertyValueFactory<>("title"));
        description111.setCellValueFactory(new PropertyValueFactory<>("description"));
        location111.setCellValueFactory(new PropertyValueFactory<>("location"));
        contact111.setCellValueFactory(new PropertyValueFactory<>("contact"));
        type111.setCellValueFactory(new PropertyValueFactory<>("type"));
        Url111.setCellValueFactory(new PropertyValueFactory<>("url"));
        start111.setCellValueFactory(new PropertyValueFactory<>("start"));
        end111.setCellValueFactory(new PropertyValueFactory<>("end"));
        createDate111.setCellValueFactory(new PropertyValueFactory<>("createDate"));
        createdBy111.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
        lastUpdate111.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));
        lastUpdateBy111.setCellValueFactory(new PropertyValueFactory<>("lastUpdateBy"));
        appointmentView111.setItems(populateTable1());

        
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        userTableView.setItems(populateUser());
        
        
        MonthComboBox.getItems().removeAll(MonthComboBox.getItems());
        MonthComboBox.getItems().addAll("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12");
        
        

    }
    private void changeScene(ActionEvent event, String fxml) throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(loader);
        Stage stageParts = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageParts.setScene(scene);
        stageParts.show();
    }
public static ObservableList<AppointmentData> populateTable1Month(String month){
    oblist.clear();
    if(!(month.equals("00"))){
    try { 
        Statement statement = DBConnection.getConnection().createStatement();
        Statement statement1 = DBConnection.getConnection().createStatement(); 
        ResultSet rs =  statement.executeQuery("select * from appointment where start like '%-" + month + "-%';");
        ResultSet rs1 =  statement1.executeQuery("select count(distinct type) from appointment where start like '%-" + month + "-%';");
        System.out.println("hello");
        while(rs1.next()){
           rs1.getInt("count(distinct type)");
           ctType = rs1.getString("count(distinct type)");
        }
        statement1.close();
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
                rs.getString("start"),
                rs.getString("end"),
                rs.getString("createDate"),
                rs.getString("createdBy"),
                rs.getString("lastUpdate"),
                rs.getString("lastUpdateBy"));
                oblist.add(appointmentData);
                
                
        }
        
        statement.close();
        return oblist;
        } catch (SQLException ex) {
                Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
                return null;
        }
    }else{
        populateTable1();
        return null;
    }
}

public static ObservableList<AppointmentData> populateTable1UserId(String uID){
    oblist.clear();
    int ConsID = Integer.parseInt(uID);
    if(!(uID.equals("00"))){
    try { 
        Statement statement = DBConnection.getConnection().createStatement();
        Statement statement1 = DBConnection.getConnection().createStatement(); 
        ResultSet rs =  statement.executeQuery("select * from appointment where userId = " + ConsID + ";");
        
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
                rs.getString("start"),
                rs.getString("end"),
                rs.getString("createDate"),
                rs.getString("createdBy"),
                rs.getString("lastUpdate"),
                rs.getString("lastUpdateBy"));
                oblist.add(appointmentData);       
        }
        
        statement.close();
        return oblist;
        } catch (SQLException ex) {
                Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
                return null;
        }
    }else{
        populateTable1();
        return null;
    }
}

public static ObservableList<AppointmentData> populateTable1Location(String loc){
    oblist.clear();
    if(!(loc.equals("00"))){
    try { 
        Statement statement = DBConnection.getConnection().createStatement();
        Statement statement1 = DBConnection.getConnection().createStatement(); 
        ResultSet rs =  statement.executeQuery("select * from appointment where location = '" + loc + "';");
        
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
                rs.getString("start"),
                rs.getString("end"),
                rs.getString("createDate"),
                rs.getString("createdBy"),
                rs.getString("lastUpdate"),
                rs.getString("lastUpdateBy"));
                oblist.add(appointmentData);       
        }
        
        statement.close();
        return oblist;
        } catch (SQLException ex) {
                Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
                return null;
        }
    }else{
        populateTable1();
        return null;
    }
}

public static ObservableList<userData> populateUser(){
    oblist1.clear();
    
    try {
        Statement statement1 = DBConnection.getConnection().createStatement();
        ResultSet rs =  statement1.executeQuery("select userId, userName from user;");
        while(rs.next()){
                userData UserData = new userData(
                rs.getInt("userId"),
                rs.getString("userName"));
                oblist1.add(UserData);       
        }
        statement1.close();
        return oblist1;
        } catch (SQLException ex) {
                Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
                return null;
        }
}    

@FXML private Label TypeCount;
public void ChoiceHandlerAppt(ActionEvent event) throws IOException{
    String value = (String) MonthComboBox.getValue();
    if (value != null){
        populateTable1Month(value);
        TypeCount.setText(ctType);
    }
}

public void UserHandler(ActionEvent event) throws IOException{
    String value = ConsultantText.getText();
    if (value != null){
        populateTable1UserId(value);
    }
}

public void LocationHandler(ActionEvent event) throws IOException{
    String value = LocationText.getText();
    if (value != null){
        populateTable1Location(value);
    }
}

public static ObservableList<AppointmentData> populateTable1(){
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
                rs.getString("start"),
                rs.getString("end"),
                rs.getString("createDate"),
                rs.getString("createdBy"),
                rs.getString("lastUpdate"),
                rs.getString("lastUpdateBy"));
                oblist.add(appointmentData);
                
        } 
        statement.close();
        return oblist;
        } catch (SQLException ex) {
                Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
                return null;
        }
    }
    public void BackHandler(ActionEvent event) throws IOException {
        changeScene(event, "DatabaseModifier.fxml");
    }
    
}
