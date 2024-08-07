package DAO;

import DTO.OrganizationDTO;
import DTO.ProductDTO;
import Utilities.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrganizationDAOImpl implements OrganizationDAO {

    @Override
public int addOrganization(OrganizationDTO organization) throws SQLException {
    Connection con = null;
    PreparedStatement pstmt = null;
    int generatedId = -1; // Default value indicating failure

    try {
        con = DataSource.getConnection();
        con.setAutoCommit(false); // Start transaction

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
        String sqlInsertOrganization = "INSERT INTO CharityOrg (userID, charityOrgName) VALUES (?, ?)";
        pstmt = con.prepareStatement(sqlInsertOrganization, PreparedStatement.RETURN_GENERATED_KEYS);
        pstmt.setInt(1, generatedId);
        pstmt.setString(2, organization.getName());
        pstmt.executeUpdate();

        // Retrieve the generated organization ID
        rs = pstmt.getGeneratedKeys();
        if (rs.next()) {
            generatedId = rs.getInt(1);
        }

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
        if (pstmt != null) pstmt.close();
        if (con != null) con.close();
    }

    return generatedId;
}

@Override
    public void donateProduct(ProductDTO product, int charityOrgID) throws SQLException {
    String charityProductDonationQuery = "INSERT INTO CharityProductDonation (charityOrgID, productID, quantity, isSurplus) VALUES (?, ?, ?, ?)";

    try (Connection connection = DataSource.getConnection()) {
        connection.setAutoCommit(false);
        
        // Insert product if it does not exist
        String productQuery = "INSERT INTO Product (productName, price, isVeggie) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE productName = VALUES(productName), price = VALUES(price), isVeggie = VALUES(isVeggie)";
        
        try (PreparedStatement productStmt = connection.prepareStatement(productQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
            productStmt.setString(1, product.getName());
            productStmt.setDouble(2, product.getPrice());
            productStmt.setBoolean(3, product.isVeggie());
            productStmt.executeUpdate();
            
            try (ResultSet generatedKeys = productStmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int productId = generatedKeys.getInt(1);
                    
                    // Insert into CharityProductDonation table
                    try (PreparedStatement charityProductDonationStmt = connection.prepareStatement(charityProductDonationQuery)) {
                        charityProductDonationStmt.setInt(1, charityOrgID);
                        charityProductDonationStmt.setInt(2, productId);
                        charityProductDonationStmt.setInt(3, product.getQuantity());
                        charityProductDonationStmt.setBoolean(4, product.isSurplus());
                        charityProductDonationStmt.executeUpdate();
                    }
                }
            }
        }
        connection.commit();
    }
    }


  @Override
public List<ProductDTO> claimFood() throws SQLException {
    Connection con = null;
    PreparedStatement preparedStatement = null;
    List<ProductDTO> products = new ArrayList<>();

    String selectQuery = "SELECT p.productID, p.productName, p.price, p.isVeggie, pr.retailerID, pr.productQuantity, pr.expiryDate, pr.isSurplus " +
                         "FROM Product p JOIN ProductRetailer pr ON p.productID = pr.productID " +
                         "WHERE pr.isSurplus = true AND pr.productQuantity > 0";
    
    String updateQuery = "UPDATE ProductRetailer SET productQuantity = productQuantity - ? WHERE productID = ? AND retailerID = ?";

    try {
        con = DataSource.getConnection();
        
        // Fetch surplus products with available quantity
        preparedStatement = con.prepareStatement(selectQuery);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                products.add(new ProductDTO(
                    resultSet.getInt("productID"),
                    resultSet.getString("productName"),
                    resultSet.getInt("productQuantity"),
                    resultSet.getDate("expiryDate"),
                    resultSet.getBoolean("isSurplus"),
                    resultSet.getInt("retailerID"),
                    resultSet.getDouble("price"),
                    resultSet.getBoolean("isVeggie")
                ));
            }
        }
        
        // Update quantities after claiming
        preparedStatement = con.prepareStatement(updateQuery);
        for (ProductDTO product : products) {
            preparedStatement.setInt(1, product.getQuantity()); // Assuming the quantity to be claimed is the entire available quantity
            preparedStatement.setInt(2, product.getId());
            preparedStatement.setInt(3, product.getRetailerID());
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
        
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Close resources
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (con != null) {
            con.close();
        }
    }

    return products;
}
}