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
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public List<ProductDTO> getProductsByRetailerID(int retailerID) throws SQLException{
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
     * Validates and adds a retailer to the database
     * @param retailer 
     */
    public int addRetailer(RetailerDTO retailer) {
        //validator.validateRetailer(retailer);
        return retailerDAO.addRetailer(retailer);  
    }
    
    public void deleteProduct(int productID){
        try {
            productDAO.deleteProduct(productID);
        } catch (SQLException ex) {
            Logger.getLogger(RetailersBusinessLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<ProductDTO> getProductsByRetailerIDSortedByPrice(int retailerID) throws SQLException{
        return productDAO.getProductsByRetailerIDSortedByPrice(retailerID);
    } 
    
    public List<ProductDTO> getProductsByRetailerIDSortedByExpiryDate(int retailerID) throws SQLException{
        return productDAO.getProductsByRetailerIDSortedByExpiryDate(retailerID);
    } 
}

