
package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.IntegerProperty;

/**
 *
 * @author JBallejos
 */
public class InHouse extends Part {
    private final IntegerProperty machineId;
    
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        this.machineId = new SimpleIntegerProperty(machineId);
        this.max = new SimpleIntegerProperty(max);
        this.min = new SimpleIntegerProperty(min);
        this.stock = new SimpleIntegerProperty(stock);
        this.price = new SimpleDoubleProperty(price);
        this.name = new SimpleStringProperty(name);
        this.id = new SimpleIntegerProperty(id);
    }

    public InHouse() {
        this.machineId = new SimpleIntegerProperty(0);
        this.max = new SimpleIntegerProperty(0);
        this.min = new SimpleIntegerProperty(0);
        this.stock = new SimpleIntegerProperty(0);
        this.price = new SimpleDoubleProperty(0);
        this.name = new SimpleStringProperty("");
        this.id = new SimpleIntegerProperty(0);
    }
    
    public IntegerProperty machineIdProperty(){
        return machineId;
    }
    
    

    public void setMachineId(int machineId) {
        this.machineId.set(machineId);
    }
    
    public int getMachineId() {
        return this.machineId.get();
    }
   
    
}