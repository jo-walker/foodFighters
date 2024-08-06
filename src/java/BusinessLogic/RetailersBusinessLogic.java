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
 * Business logic for retailers, including managing products and retailers.
 */
public class RetailersBusinessLogic {
    private RetailerDAO retailerDAO;
    private Validator validator;
    private ProductDAO productDAO;
    
    /**
     * Constructor to initialize DAOs and Validator.
     */
    public RetailersBusinessLogic() {
        retailerDAO = new RetailerDAOImpl();
        validator = new Validator();
        productDAO = new ProductDAOImpl();
    }

    /**
     * Retrieves products by retailer ID.
     * @param retailerID The retailer ID.
     * @return A list of products associated with the retailer.
     * @throws SQLException If a database access error occurs.
     */
    public List<ProductDTO> getProductsByRetailerID(int retailerID) throws SQLException {
        return productDAO.getProductsByRetailerID(retailerID);
    }
    
    /**
     * Adds a product to the database.
     * @param product The product to add to the database.
     * @throws SQLException If a database access error occurs.
     */
    public void addProduct(ProductDTO product) throws SQLException, ValidationException {
        validator.validateProduct(product);
        productDAO.addProduct(product);
    }
    

    /**
     * Updates a product's quantity
     * @param product 
     * @throws java.sql.SQLException 
     */
//    public void updateQty(ProductDTO product) throws SQLException {
//        productDAO.updateProductQuantity(product);
//        // Implementation
//    }

    /**
     * Marks a product as surplus
     * @param product 
     * @throws java.sql.SQLException 
     */
//    public void listSurplusProduct(ProductDTO product) throws SQLException {
//        productDAO.setSurplus(product);
//        // Implementation
//    }

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

