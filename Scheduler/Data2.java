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
public class Data2 {
    private SimpleIntegerProperty addressId;
    private SimpleStringProperty address;
    private SimpleStringProperty address2;
    private SimpleIntegerProperty cityId;
    private SimpleStringProperty postalCode;
    private SimpleStringProperty phone;
    private SimpleStringProperty createDate;
    private SimpleStringProperty createdBy;
    private SimpleStringProperty lastUpdate;
    private SimpleStringProperty lastUpdateBy;

    

    public Data2(int addressId, String address, String address2, int cityId, String postalCode, String phone, String createDate, String createdBy, String lastUpdate, String lastUpdateBy){
        this.addressId = new SimpleIntegerProperty(addressId);
        this.address = new SimpleStringProperty(address);
        this.address2 = new SimpleStringProperty(address2);
        this.cityId = new SimpleIntegerProperty(cityId);
        this.postalCode = new SimpleStringProperty(postalCode);
        this.phone = new SimpleStringProperty(phone);
        this.createDate = new SimpleStringProperty(createDate);
        this.createdBy = new SimpleStringProperty(createdBy);
        this.lastUpdate = new SimpleStringProperty(lastUpdate);
        this.lastUpdateBy = new SimpleStringProperty(lastUpdateBy);
    }


    public int getAddressId() {
        return addressId.get();
    }

    public void setAddressId(int addressId) {
        this.addressId = new SimpleIntegerProperty(addressId);
    }

    public String getAddress() {
        return address.get();
    }

    public void setAddress(String address) {
        this.address = new SimpleStringProperty(address);
    }

    public String getAddress2() {
        return address2.get();
    }

    public void setAddress2(String address2) {
        this.address2 = new SimpleStringProperty(address2);
    }

    public int getCityId() {
        return cityId.get();
    }

    public void setCityId(int cityId) {
        this.cityId = new SimpleIntegerProperty(cityId);
    }
    public String getPostalCode() {
        return postalCode.get();
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = new SimpleStringProperty(postalCode);
    }
    public String getPhone() {
        return phone.get();
    }
    public void setPhone(String phone) {
        this.phone = new SimpleStringProperty(phone);
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
