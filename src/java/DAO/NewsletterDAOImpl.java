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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void addMessage(String productName, int retailerID) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        String retailerName = "";

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

            // Insert into the notifications
            pstmt = con.prepareStatement("INSERT INTO Notifications (text) VALUES(?)");
            pstmt.setString(1, message);
            pstmt.executeUpdate();

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
    }

}
