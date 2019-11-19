/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcmain;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


/**
 *
 * @author JBallejos
 */
public class AppointmentData {
    private SimpleIntegerProperty appointmentId;
    private SimpleIntegerProperty customerId;
    private SimpleIntegerProperty userId;
    private SimpleStringProperty title;
    private SimpleStringProperty description;
    private SimpleStringProperty location;
    private SimpleStringProperty contact;
    private SimpleStringProperty type;
    private SimpleStringProperty url;
    private SimpleStringProperty start;
    private SimpleStringProperty end;
    private SimpleStringProperty createDate;
    private SimpleStringProperty createdBy;
    private SimpleStringProperty lastUpdate;
    private SimpleStringProperty lastUpdateBy;

    

    public AppointmentData(int appointmentId, int customerId, int userId, String title, String description, String location, String contact, String type, String url, String start, String end, String createDate, String createdBy, String lastUpdate, String lastUpdateBy){
        this.appointmentId = new SimpleIntegerProperty(appointmentId);
        this.customerId = new SimpleIntegerProperty(customerId);
        this.userId = new SimpleIntegerProperty(userId);
        this.title = new SimpleStringProperty(title);
        this.description = new SimpleStringProperty(description);
        this.location = new SimpleStringProperty(location);
        this.contact = new SimpleStringProperty(contact);
        this.type = new SimpleStringProperty(type);
        this.url = new SimpleStringProperty(url);
        this.start = new SimpleStringProperty(start);
        this.end = new SimpleStringProperty(end);
        this.createDate = new SimpleStringProperty(createDate);
        this.createdBy = new SimpleStringProperty(createdBy);
        this.lastUpdate = new SimpleStringProperty(lastUpdate);
        this.lastUpdateBy = new SimpleStringProperty(lastUpdateBy);
    }


    public int getAppointmentId() {
        return appointmentId.get();
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = new SimpleIntegerProperty(appointmentId);
    }

    public int getCustomerId() {
        return customerId.get();
    }

    public void setCustomerId(int customerId) {
        this.customerId = new SimpleIntegerProperty(customerId);
    }

    public int getUserId() {
        return userId.get();
    }

    public void setUserId(int userId) {
        this.userId = new SimpleIntegerProperty(userId);
    }
    
    public String getTitle() {
        return title.get();
    }
    public void setTitle(String title) {
        this.title = new SimpleStringProperty(title);
    }
    public String getDescription() {
        return description.get();
    }
    public void setDescription(String description) {
        this.description = new SimpleStringProperty(description);
    }
    
    public String getLocation() {
        return location.get();
    }
    public void setLocation(String location) {
        this.location = new SimpleStringProperty(location);
    }
    
    public String getContact() {
        return contact.get();
    }
    public void setContact(String contact) {
        this.contact = new SimpleStringProperty(contact);
    }
    
    public String getType() {
        return type.get();
    }
    public void setType(String type) {
        this.type = new SimpleStringProperty(type);
    }
    
    public String getUrl() {
        return url.get();
    }
    public void setUrl(String url) {
        this.url = new SimpleStringProperty(url);
    }
    
    public String getStart() {
        return start.get();
    }
    public void setStart(String start) {
        this.start = new SimpleStringProperty(start);
    }
    
    public String getEnd() {
        return end.get();
    }
    public void setEnd(String end) {
        this.end = new SimpleStringProperty(end);
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
