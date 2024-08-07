package DTO;

public class BasketItem {
    private int productId;
    private String productName;
    private int quantity;
    private int retailerID; // Add retailerID field

    public BasketItem(int productId, String productName, int quantity, int retailerID) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.retailerID = retailerID; // Initialize retailerID
    }

    // Getters and setters
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getRetailerID() {
        return retailerID;
    }

    public void setRetailerID(int retailerID) {
        this.retailerID = retailerID;
    }
}
