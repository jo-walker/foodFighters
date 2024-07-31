/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.RetailerDTO;
import Utilities.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author Andrea Visani 041104651 visa0004@algonquinlive.com
 */
public class RetailerDAOImpl implements RetailerDAO {

    @Override
    public int addRetailer(RetailerDTO retailer) {
        
        
        
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DataSource.getConnection();

            //RetailerID, it is generated by Database
            pstmt = con.prepareStatement("INSERT INTO user (username, password, email, userRole) "
                    + "VALUES(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            // Statement.RETURN_GENERATED_KEYS makes generated keys accessible through getGenratedKeys()
            pstmt.setString(1, retailer.getUsername());
            pstmt.setString(2, retailer.getPassword());
            pstmt.setString(3, retailer.getEmail());
            pstmt.setInt(4, retailer.getRole());
            pstmt.executeUpdate();

            // Retrieve the generated keys
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            int userID = 0;
            if (generatedKeys.next()) {
                userID = generatedKeys.getInt(1);
            }

            // Use the retrieved key in the second PreparedStatement
            pstmt = con.prepareStatement(
                "INSERT INTO retailer (userID, retailerName) "
                + "VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, userID);
            pstmt.setString(2, retailer.getName());
            pstmt.executeUpdate();
            
            // Retrieve the generated keys
            generatedKeys = pstmt.getGeneratedKeys();
            int retailerID = 0;
            if (generatedKeys.next()) {
                retailerID = generatedKeys.getInt(1);
            }
            return retailerID;

        }
             catch (SQLException e) {
            e.printStackTrace();
            // Optionally, you can throw a custom exception here
            throw new RuntimeException("Error adding retailer", e);
        }
<<<<<<< HEAD
    
    }

=======
    }
>>>>>>> origin/AndreaVisani
}
