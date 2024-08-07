package DAO;

import DTO.ConsumerDTO;
import DTO.NewsletterDTO;
import DTO.ProductDTO;
import Newsletter.Subscriber;
import Utilities.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsumerDAOImpl implements ConsumerDAO {
    private Connection connection;

    public ConsumerDAOImpl() {
        // Initialize connection
        this.connection = DataSource.getConnection();
    }

    /**
     *
     * @param customer
     * @throws SQLException
     */
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

            // Retrieve generated user ID
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
                    pstmt.setBoolean(5, customer.getVeg());
                    pstmt.executeUpdate();
                    System.out.println("this is working");
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
    public void subscribeToAlert(int consumerID) throws SQLException {
        String query = "UPDATE user JOIN Customer ON user.userID = Customer.userID SET user.isSubscribed = TRUE WHERE Customer.customerID = ?";
       
        try (Connection connection = DataSource.getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement productStmt = connection.prepareStatement(query)) {
                productStmt.setInt(1, consumerID);

                productStmt.executeUpdate();

            }
            connection.commit();
        }
    }
    
    @Override
    public void unsubscribeToAlert(int consumerID) throws SQLException {
        String query = "UPDATE user JOIN Customer ON user.userID = Customer.userID SET user.isSubscribed = FALSE WHERE Customer.customerID = ?";
       
        try (Connection connection = DataSource.getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement productStmt = connection.prepareStatement(query)) {
                productStmt.setInt(1, consumerID);

                productStmt.executeUpdate();

            }
            connection.commit();
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

    @Override
    public List<Subscriber> getAllSubscribedConsumers() throws SQLException {
        
        Connection con = null;
        PreparedStatement preparedStatement = null;
        
        List<Subscriber> subscribedConsumers = new ArrayList<>();
        String query = "SELECT c.customerID, c.isVegetarian FROM Customer c JOIN user u ON c.userID = u.userID WHERE u.isSubscribed = TRUE";

        try {
            con = DataSource.getConnection();
            preparedStatement = con.prepareStatement(query);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("customerID");
                    boolean vege = resultSet.getBoolean("isVegetarian");
                    ConsumerDTO cons = new ConsumerDTO();
                    cons.setCustomerID(id);
                    cons.setDietType(vege);
                    
                    subscribedConsumers.add(cons);

                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return subscribedConsumers;
    }

    @Override
    public void receiveNotification(int id, NewsletterDTO notification) {
        String sql = "INSERT INTO CustomerNotification (customerID, notificationID) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.setInt(2, notification.getId());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Subscribing to add the message, no rows affected.");
            }

        }   
        catch (SQLException ex) {
                Logger.getLogger(ConsumerDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @Override
    public boolean isUserSubscribed(int customerID) {
         String query = "SELECT u.isSubscribed " +
                       "FROM user u " +
                       "JOIN Customer c ON u.userID = c.userID " +
                       "WHERE c.customerID = ?";

        try (Connection conn = DataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            // Set the parameter for the customerID
            stmt.setInt(1, customerID);
            
            // Execute the query and get the result
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Return the boolean value of isSubscribed
                    return rs.getBoolean("isSubscribed");
                }
            }
        } catch (SQLException e) {
            // Handle any SQL exceptions
            e.printStackTrace();
        }
        
        // Return false if no result is found or in case of an error
        return false;
    }
    

      
}
