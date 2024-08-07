/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import DTO.ProductDTO;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author Baasanlkham Gurvantamir 041129783 gurv0003@algonquinlive.com
 */
public interface ProductDAO {
            
    void addProduct(ProductDTO product) throws SQLException;
    ProductDTO getProductByID(int productID) throws SQLException;
    List<ProductDTO> getProductByName(String name) throws SQLException;
    List<ProductDTO> getAllProducts() throws SQLException;
    void updateProduct(ProductDTO product) throws SQLException;
    void deleteProduct(int productID) throws SQLException;
    
    /**
     * retrieves a list of productsDTO based on a retailerID
     * @param retailerID the retailer ID
     * @return  a list of productsDTO belonging to a specific retailer
     * @throws SQLException 
     * @author Andrea Visani 041104651 visa0004@algonquinlive.com
     */
    List<ProductDTO> getProductsByRetailerID(int retailerID) throws SQLException;
    
    /**
     * retrieves a list of productsDTO based on a retailerID, sorted by price.
     * Uses a static boolean to keep track of the sorting logic (ASC or DESC)
     * @param retailerID
     * @return a list of productsDTO based on a retailerID, sorted by price
     * @throws SQLException 
     * @author Andrea Visani 041104651 visa0004@algonquinlive.com
     */
    List<ProductDTO> getProductsByRetailerIDSortedByPrice(int retailerID) throws SQLException;
    
    /**
     * retrieves a list of productsDTO based on a retailerID, sorted by ExpiryDate. 
     * Uses a static boolean to keep track of the sorting logic (ASC or DESC)
     * @param retailerID
     * @return a list of productsDTO based on a retailerID, sorted by ExpiryDate
     * @throws SQLException
     * @author Andrea Visani 041104651 visa0004@algonquinlive.com
     */
    List<ProductDTO> getProductsByRetailerIDSortedByExpiryDate(int retailerID) throws SQLException;
}
