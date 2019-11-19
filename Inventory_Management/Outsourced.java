
package Model;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
/**
 *
 * @author JBallejos
 */
public class Outsourced extends Part {
    private final StringProperty companyName;

    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        this.id = new SimpleIntegerProperty(id);
        this.max = new SimpleIntegerProperty(max);
        this.min = new SimpleIntegerProperty(min);
        this.stock = new SimpleIntegerProperty(stock);
        this.price = new SimpleDoubleProperty(price);
        this.name = new SimpleStringProperty(name);
        this.companyName = new SimpleStringProperty(companyName);
    }

    public Outsourced() {
        this.id = new SimpleIntegerProperty(0);
        this.max = new SimpleIntegerProperty(0);
        this.min = new SimpleIntegerProperty(0);
        this.stock = new SimpleIntegerProperty(0);
        this.price = new SimpleDoubleProperty(0);
        this.name = new SimpleStringProperty("");
        this.companyName = new SimpleStringProperty("");
    }

    public String getCompanyName() {
        return this.companyName.get();
    }

    public void setCompanyName(String companyName) {
        this.companyName.set(companyName);
    }
    
    public StringProperty companyNameProperty() {
            return companyName;
    }
}