/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package messengerapp.messengerbackendv1.Entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Lüku
 */

@Entity
@Table
public class AppUser {
    
    @Id
    @SequenceGenerator(
        name = "appuser_sequence",
        sequenceName = "appuser_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "appuser_sequence"
    )
    private Integer appUserId;    
    private String appUserName;
    private String appUserPassword;
    
    
    //---------------------------------------------
    // constructors, getters & setters, toString
    

    public AppUser() {
    }

    public AppUser(Integer appUserId, String appUserName, String appUserPassword) {
        this.appUserId = appUserId;
        this.appUserName = appUserName;
        this.appUserPassword = appUserPassword;
    }

    public AppUser(String appUserName, String appUserPassword) {
        this.appUserName = appUserName;
        this.appUserPassword = appUserPassword;
    }

    public Integer getUserId() {
        return appUserId;
    }

    public void setUserId(Integer userId) {
        this.appUserId = userId;
    }

    public String getUserName() {
        return appUserName;
    }

    public void setUserName(String userName) {
        this.appUserName = userName;
    }

    public String getUserPassword() {
        return appUserPassword;
    }

    public void setUserPassword(String userPassword) {
        this.appUserPassword = userPassword;
    }

    @Override
    public String toString() {
        return "AppUser{" + "appUserId=" + appUserId + ", appUserName=" + appUserName + ", appUserPassword=" + appUserPassword + '}';
    }

    
     
}
