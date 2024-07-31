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
    public void addProduct(ProductDTO product) throws SQLException {
        validator.validateProduct(product);
        productDAO.addProduct(product);
    }

    /**
     * Updates a product's quantity.
     * @param product The product with the updated quantity.
     * @throws SQLException If a database access error occurs.
     */
    public void updateQty(ProductDTO product) throws SQLException {
        validator.validateProductQuantity(product);
        productDAO.updateProductQuantity(product);
    }

    /**
     * Marks a product as surplus.
     * @param product The product to mark as surplus.
     * @throws SQLException If a database access error occurs.
     */
    public void listSurplusProduct(ProductDTO product) throws SQLException {
        validator.validateSurplusProduct(product);
        productDAO.setSurplus(product);
    }

    /**
     * Validates and adds a retailer to the database.
     * @param retailer The retailer to add.
     * @return The ID of the added retailer.
     */
    public int addRetailer(RetailerDTO retailer) {
        // validator.validateRetailer(retailer); // Uncomment if validation logic is implemented
        return retailerDAO.addRetailer(retailer);
    }
}