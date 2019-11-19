
package Model;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.DoubleProperty;

/**
 *
 * @author JBallejos
 */
public class Product {
    private ObservableList<Part> productParts = FXCollections.observableArrayList();
    private final IntegerProperty max;
    private final IntegerProperty min;
    private final IntegerProperty stock;
    private final DoubleProperty price;
    private final StringProperty name;
    private final IntegerProperty id;
    
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.max = new SimpleIntegerProperty(max);
        this.min = new SimpleIntegerProperty(min);
        this.stock = new SimpleIntegerProperty(stock);
        this.price = new SimpleDoubleProperty(price);
        this.name = new SimpleStringProperty(name);
        this.id = new SimpleIntegerProperty(id);
    }

    public Product() {
        this.max = new SimpleIntegerProperty(0);
        this.min = new SimpleIntegerProperty(0);
        this.stock = new SimpleIntegerProperty(0);
        this.price = new SimpleDoubleProperty(0);
        this.name = new SimpleStringProperty("");
        this.id = new SimpleIntegerProperty(0);
    }
     public void setMax(int max) {
        this.max.set(max);
    }

    public int getMax() {
        return this.max.get();
    }
    
    public IntegerProperty productmaxProperty() {
        return max;
    }
    public void setMin(int min) {
        this.min.set(min);
    }
    
    public int getMin() {
        return this.min.get();
    }

    public IntegerProperty productminProperty() {
        return min;
    }
    public void setStock(int stock) {
        this.stock.set(stock);
    }

    public int getStock() {
        return this.stock.get();
    }

    public IntegerProperty productstockProperty() {
        return stock;
    }
    public void setPrice(double price) {
        this.price.set(price);
    }

    public double getPrice() {
        return this.price.get();
    }

    public DoubleProperty productpriceProperty() {
        return price;
    }
     public void setName(String name) {
        this.name.set(name);
    }

    public String getName() {
        return this.name.get();
    }

    public StringProperty productnameProperty() {
        return name;
    }
    public void setId(int id) {
        this.id.set(id);
    }

    public int getId() {
        return this.id.get();
    }

    public IntegerProperty productIdProperty() {
        return id;
    }
    public void addAssociatedPart(Part part){
    
    }
    
    public void deleteAssociatedPart(Part associatedPart){
    
    }
    
    public void setAllAssociatedParts(ObservableList<Part> associatedParts) {
        productParts = associatedParts;
    }
    
    public ObservableList<Part> getAllAssociatedParts() {
        return productParts;
    }
    public int getProductPartsCount() {
        return productParts.size();
    }
    

}