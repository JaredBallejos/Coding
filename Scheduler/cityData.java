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
public class cityData {
    private SimpleIntegerProperty cityId;
    private SimpleStringProperty city;

    

    public cityData(int cityId, String city){
        this.cityId = new SimpleIntegerProperty(cityId);
        this.city = new SimpleStringProperty(city);
    }


    public int getCityId() {
        return cityId.get();
    }

    public void setUserId(int cityId) {
        this.cityId = new SimpleIntegerProperty(cityId);
    }

    public String getCity() {
        return city.get();
    }

    public void setCity(String city) {
        this.city = new SimpleStringProperty(city);
    }
}
