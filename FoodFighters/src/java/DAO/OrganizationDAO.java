/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import DTO.OrganizationDTO;
import DTO.ProductDTO;
import java.sql.SQLException;
import java.util.List;

public interface OrganizationDAO {
    public int addOrganization(OrganizationDTO organization) throws SQLException;
    void donateProduct(ProductDTO product, int charityOrgID) throws SQLException;
    public List<ProductDTO> claimFood() throws SQLException;
}

