package DAO;

import DTO.RetailerDTO;
import Utilities.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class RetailerDAOImpl implements RetailerDAO {

    @Override
    public int addRetailer(RetailerDTO retailer) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet generatedKeys = null;

        try {
            // Obtain a connection
            con = DataSource.getConnection();

            // Insert into user table and retrieve the generated user ID
            pstmt = con.prepareStatement(
                "INSERT INTO user (username, password, email, userRole) VALUES(?, ?, ?, ?)", 
                Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, retailer.getUsername());
            pstmt.setString(2, retailer.getPassword());
            pstmt.setString(3, retailer.getEmail());
            pstmt.setInt(4, retailer.getRole());
            pstmt.executeUpdate();

            generatedKeys = pstmt.getGeneratedKeys();
            int userID = 0;
            if (generatedKeys.next()) {
                userID = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }

            // Close the previous PreparedStatement and ResultSet
            pstmt.close();
            generatedKeys.close();

            // Insert into retailer table using the generated user ID
            pstmt = con.prepareStatement(
                "INSERT INTO retailer (userID, retailerName) VALUES(?, ?)", 
                Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, userID);
            pstmt.setString(2, retailer.getName());
            pstmt.executeUpdate();

            generatedKeys = pstmt.getGeneratedKeys();
            int retailerID = 0;
            if (generatedKeys.next()) {
                retailerID = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating retailer failed, no ID obtained.");
            }
            return retailerID;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error adding retailer", e);
        } finally {
            // Ensure all resources are closed in the finally block to prevent resource leaks
            try {
                if (generatedKeys != null) generatedKeys.close();
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
