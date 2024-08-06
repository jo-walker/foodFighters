/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;


import Utilities.DataSource;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Andrea Visani 041104651 visa0004@algonquinlive.com
 */
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
                if (userRole == 1) {
                   rs.close();
                   ps.close();

                   // Prepare SQL query to get additional customer information (if needed)
                   // Example: Fetching customer details (adjust the query as necessary)
                   String sql2 = "SELECT customerID, firstName, lastName, isVegetarian FROM Customer WHERE userID = ?";
                   ps = con.prepareStatement(sql2);
                   ps.setInt(1, userID);
                   rs = ps.executeQuery();

                   if (rs.next()) {
                       int customerID = rs.getInt("customerID");
                       String firstName = rs.getString("firstName");
                       String lastName = rs.getString("lastName");
                       int isVeg = rs.getInt("isVegetarian"); 

                       // Create a session and set the customer-related attributes
                       HttpSession session = request.getSession();
                       session.setAttribute("userID", userID);
                       session.setAttribute("customerID", customerID);
                       session.setAttribute("firstName", firstName);
                       session.setAttribute("lastName", lastName);
                       session.setAttribute("lastName", lastName);
                       session.setAttribute("isVeg", isVeg);

                       // Redirect to customer dashboard
                       response.sendRedirect("ConsumerDashboard.jsp");
                       return; // Ensure no further code is executed
                   } else {
                       response.sendRedirect("login.jsp?error=Customer details not found");
                       return;
                   }//

                } else if (userRole == 2) {
                    // Close previous PreparedStatement and ResultSet
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
                        return; // Ensure no further code is executed
                    } else {
                        response.sendRedirect("login.jsp?error=Retailer not found");
                        return;
                    }

                } else if (userRole == 3) {
                    // For charity role, redirect to charity dashboard

                } else {
                    response.sendRedirect("login.jsp?error=Unknown user role");
                    return;
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


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
