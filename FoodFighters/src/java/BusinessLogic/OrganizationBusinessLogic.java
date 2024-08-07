package BusinessLogic;

import DAO.OrganizationDAO;
import DAO.OrganizationDAOImpl;
import DAO.ProductDAO;
import DAO.ProductDAOImpl;
import DTO.OrganizationDTO;
import DTO.ProductDTO;
import java.sql.SQLException;
import java.util.List;

/**
 * Business logic for managing charity organizations.
 * Provides methods to interact with OrganizationDAO.
 */
public class OrganizationBusinessLogic {

    private OrganizationDAO organizationDAO;
    private ProductDAO productDAO;

    public OrganizationBusinessLogic() {
        this.organizationDAO = new OrganizationDAOImpl();
        this.productDAO = new ProductDAOImpl();
    }

    /**
     * Adds an organization to the database.
     * @param organization The organization to be added.
     */
    public int addOrganization(OrganizationDTO organization) throws SQLException {
           return organizationDAO.addOrganization(organization);
    }
    
    public List<ProductDTO> getProductsByCharityOrgID(int charityID) throws SQLException{
        return productDAO.getProductsByCharityOrgID(charityID);
    }

    /**
     * Handles product donation.
     * @param product
     * @param charityOrgID The ID of the charity organization.
     * @param productName The name of the product being donated.
     * @param quantity The quantity of the product.
     * @param expiryDate The expiry date of the product.
     * @param isVeggie Indicates if the product is vegetarian.
     * @throws SQLException If an SQL error occurs.
     */
    public void donateProduct(ProductDTO product, int charityOrgID) throws SQLException {
        if (organizationDAO == null) {
            throw new IllegalStateException("OrganizationDAO is not initialized.");
        }
        organizationDAO.donateProduct(product, charityOrgID);
    }
    
    public List<ProductDTO> claimFood() throws SQLException{
        if (organizationDAO== null){
            throw new IllegalStateException("OrganizationDAO is not initialized.");
        }
        return organizationDAO.claimFood();
    }
    
}
