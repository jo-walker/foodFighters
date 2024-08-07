/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import BusinessLogic.OrganizationBusinessLogic;
import DTO.ProductDTO;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/DonateProductServlet")
public class DonateProductServlet extends HttpServlet {

    private OrganizationBusinessLogic organizationLogic = new OrganizationBusinessLogic();

    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    HttpSession session = request.getSession(false);
    Integer charityOrgID = (Integer) session.getAttribute("charityOrgID");

    if (charityOrgID == null) {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Charity Organization ID is missing in session.");
        return;
    }

    String name = request.getParameter("productName");
    int quantity = Integer.parseInt(request.getParameter("quantity"));
    java.util.Date expiryDate = new java.util.Date(java.sql.Date.valueOf(request.getParameter("expiryDate")).getTime());
    boolean isVeggie = request.getParameter("isVeggie") != null;

    ProductDTO product = new ProductDTO(name, quantity, expiryDate, true, charityOrgID, 0.0, isVeggie);

    try {
        organizationLogic.donateProduct(product, charityOrgID);
    } catch (SQLException ex) {
        Logger.getLogger(DonateProductServlet.class.getName()).log(Level.SEVERE, null, ex);
    }

    response.sendRedirect("OrganizationDashboardServlet");
}


}

