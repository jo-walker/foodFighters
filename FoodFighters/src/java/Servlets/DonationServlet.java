/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import BusinessLogic.OrganizationBusinessLogic;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "DonationServlet", urlPatterns = {"/DonationServlet"})
public class DonationServlet extends HttpServlet {

    private OrganizationBusinessLogic organizationLogic;

    @Override
    public void init() throws ServletException {
        try {
            organizationLogic = new OrganizationBusinessLogic();
        } catch (Exception e) {
            throw new ServletException("Initialization failed in DonationServlet", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int charityOrgID = Integer.parseInt(request.getParameter("charityOrgID"));
            String productName = request.getParameter("productName");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            java.sql.Date expiryDate = java.sql.Date.valueOf(request.getParameter("expiryDate"));

            // Process donation
            organizationLogic.donateProduct(charityOrgID, productName, quantity, expiryDate);

            request.setAttribute("message", "Product donated successfully!");
            request.getRequestDispatcher("organizationDashboard.jsp").forward(request, response);

        }catch (SQLException ex) {
            Logger.getLogger(DonationServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("error", "SQL error occurred.");
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(DonationServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("error", "An error occurred.");
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Donation Servlet";
    }
}
