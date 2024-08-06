/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BusinessLogic;

import DAO.OrganizationDAO;
import DAO.OrganizationDAOImpl;
import DTO.OrganizationDTO;
import Utilities.Validator;

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
     * 
     */
    public void addOrganization(OrganizationDTO organization) {
        // Directly delegate to DAO without validation
         organizationDAO.addOrganization(organization);
    }
}




