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
import Utilities.Exception.ValidationException;
import Utilities.Validator;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents all the operations that can be performed by a retailer.
 * @author Andrea Visani 041104651 visa0004@algonquinlive.com
 */
public class RetailersBusinessLogic {
    /** A retailerDAO to access the data */
    private RetailerDAO retailerDAO = null;
    /** Validator to validate the operations */
    private Validator validator = null;
    /** ProductDAO to access the data*/
    private ProductDAO productDAO = null;
    
    public RetailersBusinessLogic(){
        retailerDAO = new RetailerDAOImpl();
        validator = new Validator();
        productDAO = new ProductDAOImpl();
    }

    /**
     * Rerurns all the products associated with the given retailerID by using the DAO
     * @param retailerID the retailer ID
     * @return all the products associated with the given retailerID
     * @throws SQLException 
     */
    public List<ProductDTO> getProductsByRetailerID(int retailerID) throws SQLException{
        return productDAO.getProductsByRetailerID(retailerID);
    }
    
    /**
     * Interacts with a productDAO to add a product to the database
     * @param product The product to add to the database
     * @throws java.sql.SQLException
     */
    public void addProduct(ProductDTO product) throws SQLException, ValidationException {
        validator.validateProduct(product);
        productDAO.addProduct(product);
        // Implementation
    }
    

    /**
     * Validates and adds a retailer to the database by using the DAO
     * @param retailer 
     */
    public int addRetailer(RetailerDTO retailer) {
        //validator.validateRetailer(retailer);
        return retailerDAO.addRetailer(retailer);  
    }
    
    /**
     * Deletes a product based on an ID by using the DAO
     * @param productID 
     */
    public void deleteProduct(int productID){
        try {
            productDAO.deleteProduct(productID);
        } catch (SQLException ex) {
            Logger.getLogger(RetailersBusinessLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Returns all the products associated with the given retailerID with sorting logic by price,  by using the DAO
     * @param retailerID the retailer ID
     * @return all the products associated with the given retailerID, sorted
     * @throws SQLException 
     */
    public List<ProductDTO> getProductsByRetailerIDSortedByPrice(int retailerID) throws SQLException{
        return productDAO.getProductsByRetailerIDSortedByPrice(retailerID);
    } 
    
    /**
     * Returns all the products associated with the given retailerID with sorting logic by date,  by using the DAO
     * @param retailerID the retailer ID
     * @return all the products associated with the given retailerID, sorted
     * @throws SQLException 
     */
    public List<ProductDTO> getProductsByRetailerIDSortedByExpiryDate(int retailerID) throws SQLException{
        return productDAO.getProductsByRetailerIDSortedByExpiryDate(retailerID);
    } 
}

