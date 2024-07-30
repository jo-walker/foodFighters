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
    
    
}
