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
 *
 * @author Andrea Visani 041104651 visa0004@algonquinlive.com
 */
public class OrganizationBusinessLogic {
    private OrganizationDAO organization = null;
    private Validator validator;

    public OrganizationBusinessLogic() {
        organization = new OrganizationDAOImpl();
        validator = new Validator();
    }
    
    

    public void claimFood() {
        // Implementation
    }

    public void addOrganization(OrganizationDTO organ) {
        validator.validateOrganization(organ);
        organization.addOrganization();
    }
}

