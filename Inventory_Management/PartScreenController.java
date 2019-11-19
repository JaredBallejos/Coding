
package View_Controller;

import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TextField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Label;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import java.util.Optional;
import java.io.IOException;
import static View_Controller.MainScreenController.grabIndex;
import Model.Part;
import Model.Outsourced;
import Model.Inventory;
import Model.InHouse;

/**
 * FXML Controller class
 *
 * @author JBallejos
 */

public class PartScreenController {

    @FXML
    private ToggleGroup outsourceOrInHouse;
    @FXML
    private Label partLabel;
    @FXML
    private RadioButton outsourced;
    @FXML
    private RadioButton inhouse;
    @FXML
    private TextField Max;
    @FXML
    private TextField Min;
    @FXML
    private TextField Inv;
    @FXML
    private TextField Company;
    @FXML
    private TextField machineId;
    @FXML
    private TextField Cost;
    @FXML
    private TextField Name;
    @FXML
    private TextField Id;
    
    @FXML
    void outsourceOrInHouse() {
        if (this.outsourceOrInHouse.getSelectedToggle().equals(this.inhouse)){
            this.Company.setDisable(true);
            this.Company.setEditable(false);
            this.Company.setText("");
            
            this.machineId.setDisable(false);
            this.machineId.setEditable(true);
        } else if (this.outsourceOrInHouse.getSelectedToggle().equals(this.outsourced)) {
            this.machineId.setDisable(true);
            this.machineId.setEditable(false);
            this.machineId.setText("");
            
            this.Company.setDisable(false);
            this.Company.setEditable(true);
          
         }
    }
    
    @FXML
    void savePart(ActionEvent event) throws IOException {
        String partID = this.Id.getText();
        String partNm = this.Name.getText();
        String partIn = this.Inv.getText();
        String partPrice = this.Cost.getText();
        String partMinm = this.Min.getText();
        String partMaxm = this.Max.getText();
        String machineID = this.machineId.getText();
        String partCo = this.Company.getText();
        if(isPartValid(
            partNm, 
            partMinm,
            partMaxm,
            partIn,
            partPrice,
            partCo,
            machineID
        )) {
            if (this.outsourceOrInHouse.getSelectedToggle().equals(this.inhouse)){
                InHouse part = new InHouse();
                part.setMachineId(Integer.parseInt(machineID));
                part.setName(partNm);
                part.setStock(Integer.parseInt(partIn));
                part.setMin(Integer.parseInt(partMinm));
                part.setMax(Integer.parseInt(partMaxm));
                part.setPrice(Double.parseDouble(partPrice));
                
                if(partID.isEmpty()) {
                    part.setId(Inventory.getCountPartId());
                    Inventory.addPart(part);
                } else {
                    part.setId(Integer.parseInt(partID));
                    Inventory.updatePart(grabIndex(), part);
                }
            } else {
                Outsourced part = new Outsourced();
                part.setCompanyName(partCo);
                part.setName(partNm);
                part.setStock(Integer.parseInt(partIn));
                part.setMin(Integer.parseInt(partMinm));
                part.setMax(Integer.parseInt(partMaxm));
                part.setPrice(Double.parseDouble(partPrice));
                
                if(partID.isEmpty()) {
                    part.setId(Inventory.getCountPartId());
                    Inventory.addPart(part);
                } else {
                    part.setId(Integer.parseInt(partID));
                    Inventory.updatePart(grabIndex(), part);
                }
            }
           
            Parent loader = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
            Scene scene = new Scene(loader);
            Stage partStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            partStage.setScene(scene);
            partStage.show();
        }
    }

    
    void addPart() {
        this.partLabel.setText("Add Part");
        
        this.inhouse.setToggleGroup(this.outsourceOrInHouse);
        this.outsourced.setToggleGroup(this.outsourceOrInHouse);
    }
    
    void modifyPart(Part part) {
        this.partLabel.setText("Modify Part");

        this.inhouse.setToggleGroup(this.outsourceOrInHouse);
        this.outsourced.setToggleGroup(this.outsourceOrInHouse);
        
        Id.setText(Integer.toString(part.getId()));
        Name.setText(part.getName());
        Inv.setText(Integer.toString(part.getStock()));
        Cost.setText(Double.toString(part.getPrice()));
        Min.setText(Integer.toString(part.getMin()));
        Max.setText(Integer.toString(part.getMax()));
        
        if (part instanceof InHouse) {
            inhouse.selectedProperty().set(true);
            machineId.setText(Integer.toString(((InHouse) part).getMachineId()));
        } else {
            outsourced.selectedProperty().set(true);
            Company.setText(((Outsourced) part).getCompanyName());
        }
        outsourceOrInHouse();
    }
    
    @FXML
    void partEditCancel(ActionEvent event) throws IOException {
        Alert cancelAlert = new Alert(Alert.AlertType.CONFIRMATION);
        cancelAlert.initModality(Modality.NONE);
        cancelAlert.setHeaderText("Cancel editing part?");
        Optional<ButtonType> oK = cancelAlert.showAndWait();
        
        if (oK.get() == ButtonType.OK) {
            Parent loader = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
            Scene scene = new Scene(loader);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }
    }
    
    
    public Boolean isPartValid(String name, String min, String max, String inv, String price, String company, String machine) {
        String error = "";
        Integer minm = Integer.valueOf(min), maxm = Integer.valueOf(max);
        Boolean valid = null;
        if(minm != null && maxm != null && minm > maxm) {
            error += ("Maximum must be greater than Minimum\n");
            Alert validAlert = new Alert(Alert.AlertType.ERROR);
            validAlert.setTitle("Part Validation Error");
            validAlert.setHeaderText("Error");
            validAlert.setContentText(error);
            validAlert.showAndWait();
            valid = false;
        } else {
            valid = true;
        }
        return valid;
    } 
    
}
    
        
     
