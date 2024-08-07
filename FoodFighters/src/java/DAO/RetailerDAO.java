/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import DTO.ProductDTO;
import DTO.RetailerDTO;

/**
 * Interface for retailerDAO
 * @author Andrea Visani 041104651 visa0004@algonquinlive.com
 */
public interface RetailerDAO {
    
    /**
     * Adds a retailer and returns its ID
     * @param retailer
     * @return the retailer ID
     */
    int addRetailer(RetailerDTO retailer);

}
