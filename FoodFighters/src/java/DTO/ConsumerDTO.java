/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

// import Utilities.NewsletterAlert;
import java.io.Serializable;

/**
 *
 * @author Andrea Visani 041104651 visa0004@algonquinlive.com
 */
public class ConsumerDTO extends User implements Serializable{
    private int customerID; //jo added
    private int userID; //jo added
    private String firstName;
    private String lastName;
    private String location;
    private String phone; //note: its called mobile in db schema
    private String dietPreference; //jo added

    // observer pattern
//    public void subscribeAlert() {
//        NewsletterAlert.addConsumer(this);
//    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
    
    public ConsumerDTO(){
        
    }
    // Constructors, getters, and setters
    public ConsumerDTO(int customerID, int userID, String firstName, String lastName, String mobile, String dietPreference) {
        this.customerID = customerID;
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = mobile;
        this.dietPreference = dietPreference;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDietPreference() {
        return dietPreference;
    }

    public void setDietPreference(String dietPreference) {
        this.dietPreference = dietPreference;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
    
    public String getDietType() {
        return dietPreference; 
    }
    
    public void setDietType() {
        this.dietPreference = dietPreference; 
    }
    
    
    
}
