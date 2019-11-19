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
public class userData {
    private SimpleIntegerProperty userId;
    private SimpleStringProperty userName;

    

    public userData(int userId, String userName){
        this.userId = new SimpleIntegerProperty(userId);
        this.userName = new SimpleStringProperty(userName);
    }


    public int getUserId() {
        return userId.get();
    }

    public void setUserId(int userId) {
        this.userId = new SimpleIntegerProperty(userId);
    }

    public String getUserName() {
        return userName.get();
    }

    public void setUserName(String userName) {
        this.userName = new SimpleStringProperty(userName);
    }
}
