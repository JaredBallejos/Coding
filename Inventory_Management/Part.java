
package Model;

import javafx.beans.property.StringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.DoubleProperty;

/**
 *
 * @author JBallejos
 */
public abstract class Part {
    protected DoubleProperty price;
    protected IntegerProperty id;
    protected StringProperty name;
    protected IntegerProperty min;
    protected IntegerProperty max;
    protected IntegerProperty stock;
    
    public double getPrice() {
        return this.price.get();
    }
    public void setPrice(double price) {
        this.price.set(price);
    }
    
    public DoubleProperty priceProperty() {
        return price;
    }
    public int getId() {
        return this.id.get();
    }
    public void setId(int id) {
        this.id.set(id);
    }
    public IntegerProperty IdProperty() {
        return id;
    }
    public String getName() {
        return this.name.get();
    }
    public void setName(String name) {
        this.name.set(name);
    }
    public StringProperty nameProperty() {
        return name;
    }
    public int getMin() {
        return this.min.get();
    }
    public void setMin(int min) {
        this.min.set(min);
    }
    public IntegerProperty minProperty() {
        return min;
    }
    public int getMax() {
        return this.max.get();
    }
    public void setMax(int max) {
        this.max.set(max);
    }
    public IntegerProperty maxProperty() {
        return max;
    }
    public int getStock() {
        return this.stock.get();
    }
    public void setStock(int stock) {
        this.stock.set(stock);
    }
    public IntegerProperty stockProperty() {
        return stock;
    }
    
    
    
    
    
    
}