/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcmain;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import static jdbcmain.DBConnection.conn;


/**
 *
 * @author JBallejos
 */
public class FXMLDocumentController implements Initializable {
    @FXML private TableView<Data1> customerView;
    @FXML private TableView<Data2> addressView;
    @FXML private TableColumn<Data1, Integer> customerId;
    @FXML public TableColumn<Data1, String> customerName;
    @FXML public TableColumn<Data1, Integer> addressId; 
    @FXML public TableColumn<Data1, Integer> active;
    @FXML public TableColumn<Data1, String> createDate;
    @FXML public TableColumn<Data1, String> createdBy;
    @FXML public TableColumn<Data1, String> lastUpdate1;
    @FXML public TableColumn<Data1, String> lastUpdateBy1;
    
    @FXML private TableColumn<Data2, Integer> addressId1;
    @FXML private TableColumn<Data2, String> address;
    @FXML private TableColumn<Data2, String> address2;
    @FXML private TableColumn<Data2, Integer> cityId;
    @FXML private TableColumn<Data2, String> postalCode;
    @FXML private TableColumn<Data2, String> phone;
    @FXML private TableColumn<Data2, String> createDate1;
    @FXML private TableColumn<Data2, String> createdBy1;
    @FXML private TableColumn<Data2, String> lastUpdate;
    @FXML private TableColumn<Data2, String> lastUpdateBy;
    
    @FXML private AnchorPane Anchor;
    
    public static  int update = 0;
    public static  int addrUpdate = 0;
    
    public static  int CustId;
    public static  String CustName;
    public static  int CustAddressId;
    public static  int CustActive;
    public static  String CustCreateDate;
    public static  String CustCreatedBy;
    public static  String CustLastUpdate;
    public static  String CustLastUpdateBy;
    
    public static  int AddrId;
    public static  String AddrAddr;
    public static  String AddrAddr2;
    public static  int AddrCityId;
    public static  String AddrPostalCode;
    public static  String AddrPhone;
    public static  String AddrCreateDate;
    public static  String AddrCreatedBy;
    public static  String AddrLastUpdate;
    public static  String AddrLastUpdateBy;
    public static ArrayList<Integer> results;
    
    public static ObservableList<Data1> oblist = FXCollections.observableArrayList();
    public static ObservableList<Data2> oblist1 = FXCollections.observableArrayList();
    
    private void changeScene(ActionEvent event, String fxml) throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(loader);
        Stage stageParts = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageParts.setScene(scene);
        stageParts.show();
    }
    
    public static String convertTimeZone(String date){
        String correct_Format = "yyyy-MM-dd HH:mm:ss.[S]";
        DateTimeFormatter format = DateTimeFormatter.ofPattern(correct_Format);
        ZoneId from = ZoneId.of("America/Chicago"); //Source
        ZoneId to = ZoneId.systemDefault(); //Target
        LocalDateTime dateTime = LocalDateTime.parse(date, format); 
        
        ZonedDateTime time = dateTime.atZone(from); 
        
        ZonedDateTime time1 = time.withZoneSameInstant(to);
        return format.format(time1);
    }
    
    public static ObservableList<Data1> populateTable(){
    oblist.clear();
    try { 
        Statement statement = DBConnection.getConnection().createStatement();    
        ResultSet rs =  statement.executeQuery("select * from customer");
        
        while(rs.next()){
                Data1 newCustomer = new Data1(
                rs.getInt("customerId"),
                rs.getString("customerName"),
                rs.getInt("addressId"),
                rs.getInt("active"),
                convertTimeZone(rs.getString("createDate")),
                rs.getString("createdBy"),
                convertTimeZone(rs.getString("lastUpdate")),
                rs.getString("lastUpdateBy"));
                oblist.add(newCustomer);
                
        } 
        statement.close();
        return oblist;
        } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                return null;
        }
    }
    public static ObservableList<Data2> populateTable1(){
    oblist1.clear();
    try { 
        Statement statement1 = DBConnection.getConnection().createStatement();    
        ResultSet rs1 =  statement1.executeQuery("select * from address");
        
        while(rs1.next()){
                Data2 newCustomer1 = new Data2(
                rs1.getInt("addressId"),
                rs1.getString("address"),
                rs1.getString("address2"),
                rs1.getInt("cityId"),
                rs1.getString("postalCode"),
                rs1.getString("phone"),
                convertTimeZone(rs1.getString("createDate")),
                rs1.getString("createdBy"),
                convertTimeZone(rs1.getString("lastUpdate")),
                rs1.getString("lastUpdateBy"));
                oblist1.add(newCustomer1);
                
        } 
        statement1.close();
        return oblist1;
        } catch (SQLException e) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, e);
                return null;
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        customerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        customerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        addressId.setCellValueFactory(new PropertyValueFactory<>("addressId"));
        active.setCellValueFactory(new PropertyValueFactory<>("active"));
        createDate.setCellValueFactory(new PropertyValueFactory<>("createDate"));
        createdBy.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
        lastUpdate1.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));
        lastUpdateBy1.setCellValueFactory(new PropertyValueFactory<>("lastUpdateBy"));
        customerView.setItems(populateTable());
        
        addressId1.setCellValueFactory(new PropertyValueFactory<>("addressId"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        address2.setCellValueFactory(new PropertyValueFactory<>("address2"));
        cityId.setCellValueFactory(new PropertyValueFactory<>("cityId"));
        postalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        createDate1.setCellValueFactory(new PropertyValueFactory<>("createDate"));
        createdBy1.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
        lastUpdate.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));
        lastUpdateBy.setCellValueFactory(new PropertyValueFactory<>("lastUpdateBy"));
        addressView.setItems(populateTable1());
        Statement statement4;
        int difference = 16;
        Timestamp timestamp1 = new Timestamp(System.currentTimeMillis());
        PreparedStatement stmt = null;
        String createTable = "create table A (a1 int);";
        Statement statement;
        Statement stmt1;
        try {
            System.out.println(timestamp1);
            stmt = conn.prepareStatement(createTable);
            stmt.executeUpdate();
            statement = conn.createStatement();
            String Insert = "insert A(a1) select timestampdiff(MINUTE, '" + timestamp1 + "', start) AS A from appointment where timestampdiff(MINUTE, '" + timestamp1 + "', start) >= 0;";
            statement.executeUpdate(Insert);
            statement4 = DBConnection.getConnection().createStatement();
            ResultSet rs2 =  statement4.executeQuery("select min(a1) from A;");
            if(rs2.next()){
            difference = (rs2.getInt(1));
            }
            System.out.println(difference);
            if(difference <= 15){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Appointment in 15 minutes");
                alert.setHeaderText("Appointment in 15 minutes");
                alert.setContentText("Appointment in 15 minutes");
                alert.showAndWait();
                
            }
            stmt1 = conn.createStatement();
            String sql = "DROP TABLE A;";
            stmt1.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    public void AppointmentHandler(ActionEvent event) throws IOException {
        changeScene(event, "Appointment.fxml");
    }
    public void ReportsHandler(ActionEvent event) throws IOException {
        changeScene(event, "Reports.fxml");
    }
    
    public void AddHandler(ActionEvent event) throws IOException {
        changeScene(event, "AddCustomer.fxml");
    }
    public void AddHandler1(ActionEvent event) throws IOException {
        changeScene(event, "AddAddress.fxml");
    }
    public void DeleteHandler(ActionEvent event) throws IOException {
        Statement statement;
        try {
            statement = conn.createStatement();
            Data1 selectedItem = customerView.getSelectionModel().getSelectedItem();
            CustId = selectedItem.getCustomerId();
            statement.executeUpdate("delete from customer where customerId = " + CustId);
            customerView.getItems().remove(selectedItem);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void DeleteHandler1(ActionEvent event) throws IOException {
        Statement statement;
        try {
            statement = conn.createStatement();
            Data2 selectedItem = addressView.getSelectionModel().getSelectedItem();
            int AddrId = selectedItem.getAddressId();
            statement.executeUpdate("delete from address where addressId = " + AddrId);
            addressView.getItems().remove(selectedItem);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void UpdateHandler(ActionEvent event) throws IOException {
        Data1 selectedItem1 = customerView.getSelectionModel().getSelectedItem();
        CustId = selectedItem1.getCustomerId();
        CustName = selectedItem1.getCustomerName();
        CustAddressId = selectedItem1.getAddressId();
        CustActive = selectedItem1.getActive();
        CustCreateDate = selectedItem1.getCreateDate();
        CustCreatedBy = selectedItem1.getCreatedBy();
        CustLastUpdate = selectedItem1.getLastUpdate();
        CustLastUpdateBy = selectedItem1.getLastUpdateBy();
        update = 1;
        changeScene(event, "AddCustomer.fxml");
    }
    public void UpdateHandler1(ActionEvent event) throws IOException {
        Data2 selectedItem1 = addressView.getSelectionModel().getSelectedItem();
        AddrId = selectedItem1.getAddressId();
        AddrAddr = selectedItem1.getAddress();
        AddrAddr2 = selectedItem1.getAddress2();
        AddrCityId = selectedItem1.getCityId();
        AddrPostalCode = selectedItem1.getPostalCode();
        AddrPhone = selectedItem1.getPhone();
        AddrCreateDate = selectedItem1.getCreateDate();
        AddrCreatedBy = selectedItem1.getCreatedBy();
        AddrLastUpdate = selectedItem1.getLastUpdate();
        AddrLastUpdateBy = selectedItem1.getLastUpdateBy();
        addrUpdate = 1;
        changeScene(event, "AddAddress.fxml");
    }   
    
        
        
    
    
    
    
    
}
