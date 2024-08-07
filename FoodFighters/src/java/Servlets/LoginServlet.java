package Servlets;

import Utilities.DataSource;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Andrea Visani 041104651 visa0004@algonquinlive.com
 */
public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Get a connection from the DataSource
            con = DataSource.getConnection();

            // Prepare SQL query to check the username and password
            String sql = "SELECT userRole, userID FROM user WHERE username = ? AND password = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();

            if (rs.next()) {
                // User found, get their role and userID
                int userRole = rs.getInt("userRole");
                int userID = rs.getInt("userID");

                // Redirect based on user role
                if (userRole == 1) { // Consumer
                    rs.close();
                    ps.close();

<<<<<<< HEAD
                    // Prepare SQL query to get additional consumer information
                    String sql2 = "SELECT customerID, firstName, lastName FROM Customer WHERE userID = ?";
                    ps = con.prepareStatement(sql2);
                    ps.setInt(1, userID);
                    rs = ps.executeQuery();
=======
                   // Prepare SQL query to get additional customer information (if needed)
                   // Example: Fetching customer details (adjust the query as necessary)
                   String sql2 = "SELECT customerID, firstName, lastName, isVegetarian FROM Customer WHERE userID = ?";
                   ps = con.prepareStatement(sql2);
                   ps.setInt(1, userID);
                   rs = ps.executeQuery();
>>>>>>> 3822dcdff5e7a16f286fb2c60830fda4a8f01470

                    if (rs.next()) {
                        int customerID = rs.getInt("customerID");
                        String firstName = rs.getString("firstName");
                        String lastName = rs.getString("lastName");

<<<<<<< HEAD
                        // Create a session and set the customer-related attributes
                        HttpSession session = request.getSession();
                        session.setAttribute("userID", userID);
                        session.setAttribute("customerID", customerID);
                        session.setAttribute("firstName", firstName);
                        session.setAttribute("lastName", lastName);
=======
                       int isVeg = rs.getInt("isVegetarian");

                       // Create a session and set the customer-related attributes
                       HttpSession session = request.getSession();
                       session.setAttribute("userID", userID);
                       session.setAttribute("customerID", customerID);
                       session.setAttribute("firstName", firstName);
                       session.setAttribute("lastName", lastName);
                       session.setAttribute("lastName", lastName);
                       session.setAttribute("isVeg", isVeg);
>>>>>>> 3822dcdff5e7a16f286fb2c60830fda4a8f01470

                        // Redirect to customer dashboard
                        response.sendRedirect("ConsumerDashboard.jsp");
                    } else {
                        response.sendRedirect("login.jsp?error=Customer details not found");
                    }
                } else if (userRole == 2) { // Retailer
                    rs.close();
                    ps.close();

                    // Prepare SQL query to get the retailerID using userID
                    String sql2 = "SELECT retailerID FROM retailer WHERE userID = ?";
                    ps = con.prepareStatement(sql2);
                    ps.setInt(1, userID);
                    rs = ps.executeQuery();

                    if (rs.next()) {
                        int retailerID = rs.getInt("retailerID");
                        // Create a session and set the retailerID as an attribute
                        HttpSession session = request.getSession();
                        session.setAttribute("retailerID", retailerID);
                        response.sendRedirect("RetailerDashboardServlet");
<<<<<<< HEAD
=======
                        return;
>>>>>>> 3822dcdff5e7a16f286fb2c60830fda4a8f01470
                    } else {
                        response.sendRedirect("login.jsp?error=Retailer not found");
                    }
                } else if (userRole == 3) { // Charity Organization
                    rs.close();
                    ps.close();

                    // Prepare SQL query to get the charityOrgID using userID
                    String sql2 = "SELECT charityOrgID, charityOrgName FROM CharityOrg WHERE userID = ?";
                    ps = con.prepareStatement(sql2);
                    ps.setInt(1, userID);
                    rs = ps.executeQuery();

                    if (rs.next()) {
                        int charityOrgID = rs.getInt("charityOrgID");
                        String charityOrgName = rs.getString("charityOrgName");

                        // Create a session and set the charityOrg-related attributes
                        HttpSession session = request.getSession();
                        session.setAttribute("charityOrgID", charityOrgID);
                        session.setAttribute("charityOrgName", charityOrgName);
                        response.sendRedirect("organizationDashboard.jsp");
                    } else {
                        response.sendRedirect("login.jsp?error=Charity organization not found");
                    }
                } else {
                    response.sendRedirect("login.jsp?error=Unknown user role");
                }
            } else {
                // User not found or wrong credentials
                response.sendRedirect("login.jsp?error=Invalid username or password");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            response.sendRedirect("login.jsp?error=Database error");
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
