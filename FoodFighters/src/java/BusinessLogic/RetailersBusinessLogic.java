/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BusinessLogic;

import DAO.ProductDAO;
import DAO.ProductDAOImpl;
import DAO.RetailerDAO;
import DAO.RetailerDAOImpl;
import DTO.ProductDTO;
import DTO.RetailerDTO;
import Utilities.Validator;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Andrea Visani 041104651 visa0004@algonquinlive.com
 */
public class RetailersBusinessLogic {
    private RetailerDAO retailerDAO = null;
    private Validator validator = null;
    private ProductDAO productDAO = null;
    
    public RetailersBusinessLogic(){
        retailerDAO = new RetailerDAOImpl();
        validator = new Validator();
        productDAO = new ProductDAOImpl();
    }

    public List<ProductDTO> getProductsByRetailerID(int retailerID){
        return productDAO.getProductsByRetailerID(retailerID);
    }
    
    /**
     * Interacts with a productDAO to add a product to the database
     * @param product The product to add to the database
     * @throws java.sql.SQLException
     */
    public void addProduct(ProductDTO product) throws SQLException {
        validator.validateProduct(product);
        productDAO.addProduct(product);
        // Implementation
    }

    /**
     * Updates a product's quantity
     * @param product 
     * @throws java.sql.SQLException 
     */
    public void updateQty(ProductDTO product) throws SQLException {
        productDAO.updateProductQuantity(product);
        // Implementation
    }

    /**
     * Marks a product as surplus
     * @param product 
     * @throws java.sql.SQLException 
     */
    public void listSurplusProduct(ProductDTO product) throws SQLException {
        productDAO.setSurplus(product);
        // Implementation
    }

    /**
     * Validates and adds a retailer to the database
     * @param retailer 
     */
    public int addRetailer(RetailerDTO retailer) {
        //validator.validateRetailer(retailer);
        return retailerDAO.addRetailer(retailer);  
    }
}

