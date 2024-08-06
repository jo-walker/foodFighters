/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BusinessLogic;

import DAO.OrganizationDAO;
import DAO.OrganizationDAOImpl;
import DTO.OrganizationDTO;
import java.sql.SQLException;

/**
 * Business logic for managing charity organizations.
 * Provides methods to interact with OrganizationDAO.
 */
public class OrganizationBusinessLogic {

    private OrganizationDAO organizationDAO;

    public OrganizationBusinessLogic() {
        this.organizationDAO = new OrganizationDAOImpl();
    }

    /**
     * Adds an organization to the database.
     * @param organization The organization to be added.
     */
    public void addOrganization(OrganizationDTO organization) {
        // Directly delegate to DAO without validation
        organizationDAO.addOrganization(organization);
    }

    /**
     * Handles product donation.
     * @param charityOrgID The ID of the charity organization.
     * @param productName The name of the product being donated.
     * @param quantity The quantity of the product.
     * @param expiryDate The expiry date of the product.
     * @throws SQLException If an SQL error occurs.
     */
    public void donateProduct(int charityOrgID, String productName, int quantity, java.sql.Date expiryDate) throws SQLException {
        organizationDAO.donateProduct(charityOrgID, productName, quantity, expiryDate);
    }
}





