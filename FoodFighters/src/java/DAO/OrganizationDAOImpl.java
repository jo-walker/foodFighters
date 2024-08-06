package DAO;

import DTO.OrganizationDTO;
import Utilities.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
            String sqlInsertOrganization = "INSERT INTO CharityOrg (userID, charityOrgName) VALUES (?, ?)";
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

    @Override
    public void claimFood() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public OrganizationDTO getOrganizationById(int orgId) {
    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    OrganizationDTO organization = null;
    
    try {
        con = DataSource.getConnection();
        
        String sql = "SELECT u.username, u.password, u.email, u.userRole, c.charityOrgName " +
                     "FROM user u " +
                     "JOIN CharityOrg c ON u.userID = c.userID " +
                     "WHERE c.charityOrgID = ?";
        pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, orgId);
        rs = pstmt.executeQuery();
        
        if (rs.next()) {
            organization = new OrganizationDTO();
            organization.setUserID(orgId);
            organization.setUsername(rs.getString("username"));
            organization.setPassword(rs.getString("password"));
            organization.setEmail(rs.getString("email"));
            organization.setRole(rs.getInt("userRole"));
            organization.setName(rs.getString("charityOrgName"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return organization;
}

}
