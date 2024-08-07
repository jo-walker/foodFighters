/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

// import Utilities.NewsletterAlert;
import DAO.ConsumerDAO;
import DAO.ConsumerDAOImpl;
import Newsletter.Subscriber;
import java.io.Serializable;

/**
 *
 * @author Andrea Visani 041104651 visa0004@algonquinlive.com
 */
public class ConsumerDTO extends User implements Serializable, Subscriber{
    private int customerID; //jo added
    private int userID; //jo added
    private String firstName;
    private String lastName;
    private String location;
    private String phone; //note: its called mobile in db schema
    private boolean isVeg; //jo added

    public boolean isIsSubscribed() {
        return isSubscribed;
    }

    public void setIsSubscribed(boolean isSubscribed) {
        this.isSubscribed = isSubscribed;
    }

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
    public ConsumerDTO(int customerID, int userID, String firstName, String lastName, String mobile, boolean isVeg) {
        this.customerID = customerID;
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = mobile;
        this.isVeg = isVeg;
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
    
    public boolean getVeg() {
        return isVeg; 
    }
    
    public void setDietType(boolean isVeg) {
        this.isVeg = isVeg; 
    }   

    @Override
    public void update(NewsletterDTO notification) {
        
        ConsumerDAO cons = new ConsumerDAOImpl();
        
        cons.receiveNotification(this.customerID, notification);
    
    }
    
}
