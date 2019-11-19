package View_Controller;

import Model.InHouse;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.collections.FXCollections;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.collections.ObservableList;
import java.util.Optional;
import java.io.IOException;
import static Model.Inventory.deleteProduct;
import static Model.Inventory.deletePart;
import static Model.Inventory.getAllProducts;
import static Model.Inventory.getAllParts;
import Model.Inventory;
import static Model.Inventory.associatedParts;
import static Model.Inventory.getCountPartId;
import static Model.Inventory.lookupPart;
import Model.Outsourced;
import Model.Product;
import Model.Part;

/**
 * FXML Controller class
 *
 * @author JBallejos
 */

public class MainScreenController {

    @FXML
    private TextField ProductLookup;
    @FXML
    private TableColumn<Product, Double> CostProd;
    @FXML
    private TableColumn<Product, Integer> ProdInv;
    @FXML
    private TableColumn<Product, String> NameProduct;
    @FXML
    private TableColumn<Product, Integer> IDProduct;
    @FXML
    private TableView<Product> tableProduct;
    @FXML
    private TextField PartLookup;
    @FXML
    private TableColumn<Part, Double> CostPart;
    @FXML
    private TableColumn<Part, Integer> PartInv;
    @FXML
    private TableColumn<Part, String> NamePart;
    @FXML
    private TableColumn<Part, Integer> IDPart;
    @FXML
    private TableView<Part> tablePart;
    
    private static int index = 1;
    public static int grabIndex() {
        return index;
    }
    @FXML
    public void displayPartsScreen(ActionEvent event) throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("PartScreen.fxml"));
        Scene scene = new Scene(loader);
        Stage stageParts = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageParts.setScene(scene);
        stageParts.show();
    }
    @FXML
    public void displayPartsScreen(ActionEvent event, Part thisPart) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("PartScreen.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stageParts = (Stage) ((Node) event.getSource()).getScene().getWindow();
        PartScreenController controller = loader.getController();
        controller.modifyPart(thisPart);
        stageParts.setScene(scene);
        stageParts.show();
    }   
    
    @FXML
    public void displayProductScreen(ActionEvent event) throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("ProductScreen.fxml"));
        Scene scene = new Scene(loader);
        Stage stageProducts = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageProducts.setScene(scene);
        stageProducts.show();
    }
    @FXML
    public void displayProductScreen(ActionEvent event, Product thisProduct) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ProductScreen.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stageProducts = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ProductScreenController controller = loader.getController();
        controller.modifyProduct(thisProduct);
        stageProducts.setScene(scene);
        stageProducts.show();
    }
    
    
    public void startMain(Stage stage) throws IOException {
        index = 1;
        Parent partParent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene scenePart = new Scene(partParent);
        stage.setScene(scenePart);
        stage.show(); 
        
        fillPartsTable();
        fillProductsTable();
    }
    
    public void initialize() {
        CostPart.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        PartInv.setCellValueFactory(cellData -> cellData.getValue().stockProperty().asObject());
        NamePart.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        IDPart.setCellValueFactory(cellData -> cellData.getValue().IdProperty().asObject());
        
        IDProduct.setCellValueFactory(cellData -> cellData.getValue().productIdProperty().asObject());
        NameProduct.setCellValueFactory(cellData -> cellData.getValue().productnameProperty());
        ProdInv.setCellValueFactory(cellData -> cellData.getValue().productstockProperty().asObject());
        CostProd.setCellValueFactory(cellData -> cellData.getValue().productpriceProperty().asObject());
        
        fillPartsTable();
        fillProductsTable();
    }
    public void fillProductsTable() {
        ObservableList<Product> productItems = getAllProducts();
        tableProduct.setItems(productItems);
    }
    public void fillPartsTable() {
        tablePart.setItems(getAllParts());
    }
    @FXML
    void lookupProductHandler(ActionEvent event) {
        String product = ProductLookup.getText();
        ObservableList retrievedProducts = Inventory.lookupProduct(product);
        try{
            Product searchedProduct = Inventory.lookupProduct(Integer.parseInt(product));
            ObservableList<Product> productSearchList = FXCollections.observableArrayList();
            productSearchList.add(searchedProduct);
            tableProduct.setItems(productSearchList);
            }
            catch (NumberFormatException e)
            {
            tableProduct.setItems(retrievedProducts);
            }
    }
    @FXML
    void lookupPartHandler(ActionEvent event) {
        String part = PartLookup.getText();
        ObservableList retrievedParts = Inventory.lookupPart(part);
            try{
            Part searchedPart = Inventory.lookupPart(Integer.parseInt(part));
            ObservableList<Part> partSearchList = FXCollections.observableArrayList();
            partSearchList.add(searchedPart);
            tablePart.setItems(partSearchList);
            }
            catch (NumberFormatException e)
            {
            tablePart.setItems(retrievedParts);
            }
    }
    
    @FXML
    void closeProgram(ActionEvent event) {
        Alert closeAlert = new Alert(Alert.AlertType.CONFIRMATION);
        closeAlert.setHeaderText("Do you want to exit?");
        closeAlert.setTitle("Exit?");
        closeAlert.initModality(Modality.NONE);
        Optional<ButtonType> oK = closeAlert.showAndWait();
        if (oK.get() == ButtonType.OK) {
            System.exit(0);
        }
    }
    @FXML
    void productsDelete(ActionEvent event) {
        Product selectedProduct = tableProduct.getSelectionModel().getSelectedItem();
        if (associatedParts(selectedProduct)) {
            Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteAlert.setHeaderText("Delete " + selectedProduct.getName() + "?");
            deleteAlert.setTitle("Delete Product?");
            Optional<ButtonType> oK = deleteAlert.showAndWait();
            if (oK.get() == ButtonType.OK) {
                System.out.println("Changes applied.");
                deleteProduct(selectedProduct);
            }
            else {
                deleteAlert.close();        
            }
        }else{Alert deleteAlert1 = new Alert(Alert.AlertType.ERROR);
                deleteAlert1.setTitle("Product Parts Error");
                deleteAlert1.setHeaderText("Cannot delete a product with parts attached"); 
                deleteAlert1.showAndWait();
        }
    }
    @FXML
    void partsDelete(ActionEvent event) {
        Part selectedPart = tablePart.getSelectionModel().getSelectedItem();
        if (selectedPart != null) {
            Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteAlert.setHeaderText("Delete " + selectedPart.getName() + "?");
            deleteAlert.setTitle("Delete Part?");
            Optional<ButtonType> oK = deleteAlert.showAndWait();
            if (oK.get() == ButtonType.OK) {
                deletePart(selectedPart);
            } else {
                deleteAlert.close();
            }
        }
    }
    @FXML
    void updateProduct(ActionEvent event) throws IOException {
        Product thisProduct = tableProduct.getSelectionModel().getSelectedItem();
        index = getAllProducts().indexOf(thisProduct);

        if(thisProduct != null) {
            displayProductScreen(event, thisProduct);    
        } else {
            Alert updateAlert = new Alert(Alert.AlertType.ERROR);
            updateAlert.showAndWait();
            updateAlert.setHeaderText("Please choose a product");
            updateAlert.setTitle("No Product selected");
            
            
            
        }
    }
    @FXML
    void updatePart(ActionEvent event) throws IOException {
        Part thisPart = tablePart.getSelectionModel().getSelectedItem();
        index = getAllParts().indexOf(thisPart);

        if(thisPart != null) {
            displayPartsScreen(event, thisPart);    
        } else {
            Alert updateAlert = new Alert(Alert.AlertType.ERROR);
            updateAlert.showAndWait();
            updateAlert.setHeaderText("Please choose a part");
            updateAlert.setTitle("No Part selected");
            updateAlert.initModality(Modality.APPLICATION_MODAL);  
        }
    }
    @FXML
    void addProduct(ActionEvent event) throws IOException {
        displayProductScreen(event);
    }
    public void testProducts() {
        int uniqueProductId = Inventory.getCountProductId();
        ObservableList<Part> partList0 = Inventory.lookupPart("abc"); //adds part
        Product ABC = new Product(uniqueProductId, "ABC", 10.5, 25, 1, 50);
        ABC.setAllAssociatedParts(partList0);
        Inventory.addProduct(ABC);
        ObservableList<Part> partList1 = Inventory.lookupPart("def"); //adds part
        Product DEF = new Product(Inventory.getCountProductId(), "DEF", 10.4, 22, 10, 30);
        DEF.setAllAssociatedParts(partList1);
        Inventory.addProduct(DEF);
        ObservableList<Part> partList2 = Inventory.lookupPart("ghi"); //adds part
        Product GHI = new Product(Inventory.getCountProductId(), "GHI", 10.3, 12, 5, 20);
        GHI.setAllAssociatedParts(partList2);
        Inventory.addProduct(GHI);
    }
    public void setTestProducts(){
        testProducts();
    }
   @FXML
    void addPart(ActionEvent event) throws IOException {
        displayPartsScreen(event);
    }
    public void testParts() {
    int uniquePartId = Inventory.getCountPartId();
    Inventory.addPart(new InHouse(uniquePartId, "abc", 5.5, 64, 54, 74, 123));
    Inventory.addPart(new Outsourced(getCountPartId(), "def", 5.3, 54, 44, 64, "def"));
    Inventory.addPart(new Outsourced(getCountPartId(), "ghi", 5.4, 24, 14, 34, "abc"));
    Inventory.addPart(new InHouse(getCountPartId(), "jkl", 5.5, 65, 54, 74, 456));
    }
    public void setTestParts(){
        testParts();
    }
    
    
}