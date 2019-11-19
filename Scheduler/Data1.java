/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcmain;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


/**
 *
 * @author JBallejos
 */
public class Data1 {
    private SimpleIntegerProperty customerId;
    private SimpleStringProperty customerName;
    private SimpleIntegerProperty addressId;
    private SimpleIntegerProperty active;
    private SimpleStringProperty createDate;
    private SimpleStringProperty createdBy;
    private SimpleStringProperty lastUpdate;
    private SimpleStringProperty lastUpdateBy;

    

    public Data1(int customerId, String customerName, int addressId, int active, String createDate, String createdBy, String lastUpdate, String lastUpdateBy){
        this.customerId = new SimpleIntegerProperty(customerId);
        this.customerName = new SimpleStringProperty(customerName);
        this.addressId = new SimpleIntegerProperty(addressId);
        this.active = new SimpleIntegerProperty(active);
        this.createDate = new SimpleStringProperty(createDate);
        this.createdBy = new SimpleStringProperty(createdBy);
        this.lastUpdate = new SimpleStringProperty(lastUpdate);
        this.lastUpdateBy = new SimpleStringProperty(lastUpdateBy);
    }


    public int getCustomerId() {
        return customerId.get();
    }

    public void setCustomerId(int customerId) {
        this.customerId = new SimpleIntegerProperty(customerId);
    }

    public String getCustomerName() {
        return customerName.get();
    }

    public void setCustomerName(String customerName) {
        this.customerName = new SimpleStringProperty(customerName);
    }

    public int getAddressId() {
        return addressId.get();
    }

    public void setAddressId(int addressId) {
        this.addressId = new SimpleIntegerProperty(addressId);
    }

    public int getActive() {
        return active.get();
    }

    public void setActive(int active) {
        this.active = new SimpleIntegerProperty(active);
    }

    public String getCreateDate() {
        return createDate.get();
    }

    public void setCreateDate(String createDate) {
        this.createDate = new SimpleStringProperty(createDate);
    }

    public String getCreatedBy() {
        return createdBy.get();
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = new SimpleStringProperty(createdBy);
    }

    public String getLastUpdate() {
        return lastUpdate.get();
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = new SimpleStringProperty(lastUpdate);
    }

    public String getLastUpdateBy() {
        return lastUpdateBy.get();
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = new SimpleStringProperty(lastUpdateBy);
    }
    
    
}
