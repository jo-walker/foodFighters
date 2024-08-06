/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import DTO.OrganizationDTO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Andrea Visani 041104651 visa0004@algonquinlive.com
 */
public interface OrganizationDAO {
//    public List<SurplusProductDTO> getSurplusProducts(int charityOrgID) throws SQLException;
    void addOrganization(OrganizationDTO charityOrg);
     void donateProduct(int charityOrgID, String productName, int quantity, java.sql.Date expiryDate) throws SQLException;
}