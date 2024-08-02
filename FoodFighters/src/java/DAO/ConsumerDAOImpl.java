package DAO;

import DTO.ConsumerDTO;
import DTO.ProductDTO;
import Utilities.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConsumerDAOImpl implements ConsumerDAO {
    private Connection connection;

    public ConsumerDAOImpl() {
        // Initialize connection
        this.connection = DataSource.getConnection();
    }

        @Override
        public void addConsumer(ConsumerDTO customer) throws SQLException {
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
            pstmt.setString(1, customer.getUsername());
            pstmt.setString(2, customer.getPassword());
            pstmt.setString(3, customer.getEmail());
            pstmt.setInt(4, customer.getRole());
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

            // Insert into Customer table using the generated user ID
            pstmt = con.prepareStatement(
                "INSERT INTO Customer (userID, firstName, lastName, mobile, isVegetarian) VALUES(?, ?, ?, ?, ?)", 
                Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, userID);
            pstmt.setString(2, customer.getFirstName());
            pstmt.setString(3, customer.getLastName());
            pstmt.setString(4, customer.getPhone());
            pstmt.setBoolean(5, customer.getVeg()); // Mapping the isVeg field
            pstmt.executeUpdate();

            generatedKeys = pstmt.getGeneratedKeys();
            int customerID = 0;
            if (generatedKeys.next()) {
                customerID = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating customer failed, no ID obtained.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error adding customer", e);
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

    @Override
    public void subscribeToAlert(ConsumerDTO consumer, String alertType) throws SQLException {
        String sql = "INSERT INTO Subscription (consumerID, alertType) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, consumer.getCustomerID());
            statement.setString(2, alertType);

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Subscribing to alert failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new SQLException("Error subscribing to alert", e);
        }
    }

    @Override
    public void purchaseItem(ConsumerDTO consumer, ProductDTO product) throws SQLException {
        String sql = "INSERT INTO Purchase (consumerID, productID, purchaseDate) VALUES (?, ?, CURRENT_DATE)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, consumer.getCustomerID());
            statement.setInt(2, product.getId());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Adding purchase failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new SQLException("Error adding purchase", e);
        }
    }
}
