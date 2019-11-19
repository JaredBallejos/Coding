
package View_Controller;

import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Label;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import java.util.Optional;
import java.io.IOException;
import static View_Controller.MainScreenController.grabIndex;
import Model.Product;
import Model.Part;
import Model.Inventory;

/**
 * FXML Controller class
 *
 * @author JBallejos
 */
public class ProductScreenController {

    @FXML
    private Label productLabel;
    @FXML
    private TableColumn<Part, Double> Cost;
    @FXML
    private TableColumn<Part, Integer> Inv;
    @FXML
    private TableColumn<Part, String> Name;
    @FXML
    private TableColumn<Part, Integer> Id;
    @FXML
    private TableView<Part> Parts;
    @FXML
    private TextField Search;
    @FXML
    private TableColumn<Part, Double> partCost;
    @FXML
    private TableColumn<Part, Integer> partInv;
    @FXML
    private TableColumn<Part, String> partName;
    @FXML
    private TableColumn<Part, Integer> partId;
    @FXML
    private TableView<Part> partNew;
    @FXML
    private TextField productInv;
    @FXML
    private TextField productMin;
    @FXML
    private TextField productMax;
    @FXML
    private TextField productCost;
    @FXML
    private TextField productName;
    @FXML
    private TextField productId;
    
    private ObservableList<Part> currentParts = FXCollections.observableArrayList();
    
    public void updatePartTableView() {
        partNew.setItems(Inventory.getAllParts());
    }

    private void updateProductPartTableView() {
        Parts.setItems(currentParts);
    }
    
    public void initialize() {
        partName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        partCost.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        partInv.setCellValueFactory(cellData -> cellData.getValue().stockProperty().asObject());
        partId.setCellValueFactory(cellData -> cellData.getValue().IdProperty().asObject());
        
        Name.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        Cost.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        Inv.setCellValueFactory(cellData -> cellData.getValue().stockProperty().asObject());
        Id.setCellValueFactory(cellData -> cellData.getValue().IdProperty().asObject());
        
        updatePartTableView();
        updateProductPartTableView();
    }

    
    @FXML
    void addPart(ActionEvent event) {
        
        Part part = partNew.getSelectionModel().getSelectedItem();
        currentParts.add(part);
        updatePartTableView();
        
    }
    
    @FXML
    void searchPart(ActionEvent event) {
        String partSearch = Search.getText();
        ObservableList retrievedParts = Inventory.lookupPart(partSearch);
        try{
            Part searchedPart = Inventory.lookupPart(Integer.parseInt(partSearch));
            ObservableList<Part> partSearchList = FXCollections.observableArrayList();
            partSearchList.add(searchedPart);
            Parts.setItems(partSearchList);
            }
            catch (NumberFormatException e)
            {
            Parts.setItems(retrievedParts);
            }
    }

    @FXML
    void cancelProduct(ActionEvent event) throws IOException {
        Alert cancelAlert = new Alert(Alert.AlertType.CONFIRMATION);
        cancelAlert.initModality(Modality.NONE);
        cancelAlert.setTitle("Cancel Editing");
        cancelAlert.setHeaderText("Do you want to cancel editing?");
        Optional<ButtonType> oK = cancelAlert.showAndWait();
        if (oK.get() == ButtonType.OK) {
            Parent loader = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
            Scene scene = new Scene(loader);
            Stage productStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            productStage.setScene(scene);
            productStage.show();
        }
    }

    @FXML
    void deletePart(ActionEvent event) {
        Part part = Parts.getSelectionModel().getSelectedItem();
        
        if(part != null) {
            Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteAlert.initModality(Modality.NONE);
            deleteAlert.setTitle("Remove part");
            deleteAlert.setHeaderText("Do you want to delete this part?");
            Optional<ButtonType> oK = deleteAlert.showAndWait();

            if (oK.get() == ButtonType.OK) {
                currentParts.remove(part);
                updateProductPartTableView();
            }    
        
        }
        
    }
    

    
    public Boolean isProductValid(String name, String min, String max, String inv, String price) {
        String error = "";
        Integer minm = Integer.valueOf(min), maxm = Integer.valueOf(max);
        Boolean valid = null;
        if(minm != null && maxm != null && minm > maxm) {
            error += ("Maximum must be greater than Minimum\n");
            Alert validAlert = new Alert(Alert.AlertType.ERROR);
            validAlert.setHeaderText("Error");
            validAlert.setContentText(error);
            validAlert.showAndWait();
            valid = false;
        } else {
            valid = true;
        }
        
        return valid;
    }

    @FXML
    void saveProduct(ActionEvent event) throws IOException {
            String ID = this.productId.getText();
            String name = this.productName.getText(); 
            String min = this.productMin.getText();
            String max = this.productMax.getText();
            String inv = this.productInv.getText();
            String cost = this.productCost.getText();
        if(isProductValid(
            name, 
            min,
            max,
            inv,
            cost
        )) {
            Product product = new Product();
            product.setName(name);
            product.setStock(Integer.parseInt(inv));
            product.setMin(Integer.parseInt(min));
            product.setMax(Integer.parseInt(max));
            product.setPrice(Double.parseDouble(cost));
            
            product.setAllAssociatedParts(currentParts);
            
            if(ID.length() == 0) {
                product.setId(Inventory.getCountProductId());
                Inventory.addProduct(product);
            } else {
                product.setId(Integer.parseInt(ID));
                Inventory.updateProduct(grabIndex(), product);
            }
            
            Parent loader = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
            Scene scene = new Scene(loader);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }
    }

    

    void addProduct() {
        this.productLabel.setText("Add Product");
    }
    
    void modifyProduct(Product product) {
        this.productLabel.setText("Modify Product");
        
        productId.setText(Integer.toString(product.getId()));
        productName.setText(product.getName());
        productInv.setText(Integer.toString(product.getStock()));
        productCost.setText(Double.toString(product.getPrice()));
        productMin.setText(Integer.toString(product.getMin()));
        productMax.setText(Integer.toString(product.getMax()));
        
        currentParts = product.getAllAssociatedParts();
        updateProductPartTableView();
    }
    
    
}