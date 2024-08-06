/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import DTO.OrganizationDTO;

/**
 *
 * @author Andrea Visani 041104651 visa0004@algonquinlive.com
 */
public interface OrganizationDAO {
    void claimFood();
    void addOrganization(OrganizationDTO charityOrg);
    public OrganizationDTO getOrganizationById(int orgId);
}