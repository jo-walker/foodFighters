package DAO;

import DTO.OrganizationDTO;
import Utilities.DataSource;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrganizationDAOImpl implements OrganizationDAO {

    @Override
    public void addOrganization(OrganizationDTO organization) {
        Connection con = null;
        PreparedStatement pstmt = null;
        int generatedId = -1; // Default value indicating failure

        try {
            con = DataSource.getConnection();
             // Start transaction

            // Insert into user table
            String sqlInsertUser = "INSERT INTO user (username, password, email, userRole) VALUES (?, ?, ?, ?)";
            pstmt = con.prepareStatement(sqlInsertUser, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, organization.getUsername());
            pstmt.setString(2, organization.getPassword());
            pstmt.setString(3, organization.getEmail());
            pstmt.setInt(4, organization.getRole());
            pstmt.executeUpdate();

            // Retrieve the generated user ID
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                generatedId = rs.getInt(1);
            }

            // Insert into organization table
            String sqlInsertOrganization = "INSERT INTO organization (userID, organizationName) VALUES (?, ?)";
            pstmt = con.prepareStatement(sqlInsertOrganization);
            pstmt.setInt(1, generatedId);
            pstmt.setString(2, organization.getName());
            pstmt.executeUpdate();

            con.commit(); // Commit transaction
        } catch (SQLException e) {
            e.printStackTrace();
            if (con != null) {
                try {
                    con.rollback(); // Rollback transaction in case of error
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        
    }



//    @Override
//    public List<SurplusProductDTO> getSurplusProducts(int charityOrgID) throws Object {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }

    @Override
public void donateProduct(int charityOrgID, String productName, int quantity, java.sql.Date expiryDate) throws SQLException {
    Connection con = null;
    PreparedStatement pstmt = null;
    int productID = -1; // Default value indicating failure

    try {
        con = DataSource.getConnection();
        con.setAutoCommit(false); // Start transaction

        // Check if the product exists
        String sqlSelectProduct = "SELECT productID FROM Product WHERE productName = ?";
        pstmt = con.prepareStatement(sqlSelectProduct);
        pstmt.setString(1, productName);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            // Product exists
            productID = rs.getInt("productID");
        } else {
            // Product does not exist, insert it
            String sqlInsertProduct = "INSERT INTO Product (productName, price) VALUES (?, ?)";
            pstmt = con.prepareStatement(sqlInsertProduct, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, productName);
            pstmt.setInt(2, 0); // Set default price or modify to get from input
            pstmt.executeUpdate();

            // Retrieve the generated product ID
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                productID = rs.getInt(1);
            } else {
                throw new SQLException("Failed to obtain product ID.");
            }
        }

        // Insert into ProductRetailer table
        String sqlInsertProductRetailer = "INSERT INTO ProductRetailer (productID, retailerID, productQuantity, expiryDate, isSurplus) VALUES (?, ?, ?, ?, ?)";
        pstmt = con.prepareStatement(sqlInsertProductRetailer);
        pstmt.setInt(1, productID);
        pstmt.setInt(2, charityOrgID); // Assuming retailerID is the same as charityOrgID here
        pstmt.setInt(3, quantity);
        pstmt.setDate(4, expiryDate);
        pstmt.setBoolean(5, true); // Set isSurplus to true for donations
        pstmt.executeUpdate();

        con.commit(); // Commit transaction
    } catch (SQLException e) {
        e.printStackTrace();
        if (con != null) {
            try {
                con.rollback(); // Rollback transaction in case of error
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        }
    } finally {
        try {
            if (pstmt != null) pstmt.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

}
