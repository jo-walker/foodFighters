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

    /**
     * Interacts with a productDAO to add a product to the database
     * @param product The product to add to the database
     */
    public void addProduct(ProductDTO product) {
        validator.validateProduct(product);
        productDAO.addProduct(product);
        // Implementation
    }

    /**
     * Updates a product's quantity
     * @param product 
     */
    public void updateQty(ProductDTO product) {
        productDAO.updateProduct(product);
        // Implementation
    }

    /**
     * Marks a product as surplus
     * @param product 
     */
    public void listSurplusProduct(ProductDTO product) {
        productDAO.updateProduct(product);
        // Implementation
    }

    /**
     * Validates and adds a retailer to the databse
     * @param retailer 
     */
    public void addRetailer(RetailerDTO retailer) {
        validator.validateRetailer(retailer);
        retailerDAO.addRetalier(retailer);  
    }
}

