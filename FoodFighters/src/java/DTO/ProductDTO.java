package DTO;

import Utilities.DietType;
import java.util.Date;

/**
 *
 * @author Baasanlkham Gurvantamir 041129783 gurv0003@algonquinlive.com
 */
public class ProductDTO {  
    
    private int id;
    private String name;
    private int quantity;
    private Date expiryDate;
    private boolean surplus;
    private int retailerID;
    private double price;
    private boolean isVeggie;

    // Constructors, getters, and setters
    public ProductDTO(int id, String name, int quantity, Date expiryDate, boolean surplus, int retailerID, double price, boolean isVeggie) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
        this.surplus = surplus;
        this.retailerID = retailerID;
        this.price = price;
        this.isVeggie = isVeggie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public boolean isSurplus() {
        return surplus;
    }

    public void setSurplus(boolean surplus) {
        this.surplus = surplus;
    }

    public int getRetailerID() {
        return retailerID;
    }

    public void setRetailerID(int retailerID) {
        this.retailerID = retailerID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isVeggie() {
        return isVeggie;
    }

    public void setVeggie(boolean isVeggie) {
        this.isVeggie = isVeggie;
    }
}