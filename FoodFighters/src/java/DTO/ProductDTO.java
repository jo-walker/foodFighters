package DTO;

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
    private int price;
    private String dietType;

    // Constructors, getters, and setters
    // Constructor
    public ProductDTO(int id, String name, int quantity, Date expiryDate, boolean surplus, int retailerID, int price, String dietType) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
        this.surplus = surplus;
        this.retailerID = retailerID;
        this.price = price;
        this.dietType=dietType;
    }

    public ProductDTO() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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

    public void setSurplus(boolean isSurplus) {
        this.surplus = isSurplus;
    }

    public int getRetailerID() {
        return retailerID;
    }

    public void setRetailerID(int retailerID) {
        this.retailerID = retailerID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public String getDietType(){
        return dietType;
    }
    public void setDietType(String dietType){
        this.dietType=dietType;
    }
}
