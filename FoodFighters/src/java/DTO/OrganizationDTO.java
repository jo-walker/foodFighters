/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.io.Serializable;

/**
 *
 * @author Andrea Visani 041104651 visa0004@algonquinlive.com
 */

public class OrganizationDTO extends User {
    private int charityOrgID;
    private int userID;
    private String username;
    private String password;
    private String email;
    private int role;  
    private String name;

    // Getters and Setters
    public int getCharityOrgID() {
        return charityOrgID;
    }

    public void setCharityOrgID(int charityOrgID) {
        this.charityOrgID = charityOrgID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}