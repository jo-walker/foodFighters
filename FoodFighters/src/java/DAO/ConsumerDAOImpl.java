package DAO;

import DTO.ConsumerDTO;
import DTO.ProductDTO;
import Utilities.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ConsumerDAOImpl implements ConsumerDAO {
    private Connection connection;

    public ConsumerDAOImpl() {
        // Initialize connection
        this.connection = DataSource.getConnection();
    }

    @Override
    public void addConsumer(ConsumerDTO consumer) throws SQLException {
        // Assuming consumer ID is auto-generated and not included in the insert
        String sql = "INSERT INTO Customer (firstName, lastName, location, phone, dietType) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, consumer.getFirstName());
            statement.setString(2, consumer.getLastName());
            statement.setString(3, consumer.getLocation());
            statement.setString(4, consumer.getPhone());
            statement.setString(5, consumer.getDietType());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating consumer failed, no rows affected.");
            }

            try (var generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    consumer.setId(generatedKeys.getInt(1)); // Set the generated ID
                } else {
                    throw new SQLException("Creating consumer failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error adding consumer", e);
        }
    }

    @Override
    public void subscribeToAlert(ConsumerDTO consumer, String alertType) throws SQLException {
        // Implementation here
    }

    public void addPurchase(ConsumerDTO consumer, ProductDTO product) throws SQLException {
        // Implementation here
    }

    @Override
    public void purchaseItem(ConsumerDTO consumer, ProductDTO product) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
