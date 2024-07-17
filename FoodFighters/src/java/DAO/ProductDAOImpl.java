/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ProductDTO;
import Utilities.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andrea Visani 041104651 visa0004@algonquinlive.com
 */
public class ProductDAOImpl implements ProductDAO{

    @Override
    public List<ProductDTO> getAllProducts() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<ProductDTO> getProductByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ProductDTO getProductByID(Integer productID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void addProduct(ProductDTO product) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DataSource.getConnection();

            // Insert product name into Products table
            pstmt = con.prepareStatement(
                    "INSERT INTO Products (productName) VALUES (?)");
            pstmt.setString(1, product.getName());
            pstmt.executeUpdate();

            // Insert product details into ProductRetailer table
            pstmt = con.prepareStatement(
                    "INSERT INTO ProductRetailer (productID, retailerID, quantity, expiryDate, isSurplus) "
                    + "VALUES (?, ?, ?, ?, ?)");
            pstmt.setInt(1, product.getId());
            pstmt.setInt(2, product.getRetailerID());
            pstmt.setInt(3, product.getQuantity());
            pstmt.setDate(4, new java.sql.Date(product.getExpiryDate().getTime()));
            pstmt.setBoolean(5, product.isSurplus());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }

                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public void updateProduct(ProductDTO product) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteProduct(ProductDTO product) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
