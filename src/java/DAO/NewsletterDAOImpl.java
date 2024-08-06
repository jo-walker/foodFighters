/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.NewsletterDTO;
import DTO.ProductDTO;
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

/**
 *
 * @author Andrea Visani 041104651 visa0004@algonquinlive.com
 */
public class NewsletterDAOImpl implements NewsletterDAO {
    

    @Override
    public List<NewsletterDTO> getMessagesByUserIDSortedDESC(int userID) {
        
        Connection con = null;
        PreparedStatement preparedStatement = null;
        
        List<NewsletterDTO> messages = new ArrayList<>();
        String query = "SELECT n.text " +
                       "FROM Notifications n JOIN CustomerNotification c ON c.notificationID = n.notificationID WHERE c.customerID = ?";

        try {
            
            con = DataSource.getConnection();
            preparedStatement = con.prepareStatement(query);
            
            preparedStatement.setInt(1, userID);
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    NewsletterDTO message = new NewsletterDTO();
                    message.setNotification(resultSet.getString("text"));         
                    messages.add(message);
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return messages;
    
    
    }

    @Override
    public NewsletterDTO addMessage(String productName, int retailerID) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        String retailerName = "";
        NewsletterDTO notification = new NewsletterDTO();

        try {
            con = DataSource.getConnection();

            // Retrieve retailerName based on retailerID
            String query = "SELECT retailerName FROM Retailer WHERE retailerID = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, retailerID);
            resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                retailerName = resultSet.getString("retailerName");
            }

            // Create the message
            String message = productName + " from " + retailerName + " is now on discount";
            notification.setNotification(message);

            // Insert into the notifications
            String insertQuery = "INSERT INTO Notifications (text) VALUES(?)";
            pstmt = con.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, message);
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int notificationId = generatedKeys.getInt(1);
                    notification.setId(notificationId);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error adding notification", e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return notification;
    }

}