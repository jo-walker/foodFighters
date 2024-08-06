package Servlets;

import BusinessLogic.NewsletterLogic;
import BusinessLogic.RetailersBusinessLogic;
import DTO.ProductDTO;
import DTO.NewsletterDTO;
import Utilities.Exception.ValidationException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CreateProductServlet")
public class CreateProductServlet extends HttpServlet {

    private final NewsletterLogic newsletterLogic = new NewsletterLogic();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Integer retailerID = (Integer) session.getAttribute("retailerID");

        if (retailerID == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Retailer ID is missing in session.");
            return;
        }

        try {
            String name = request.getParameter("productName");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            Date expiryDate = java.sql.Date.valueOf(request.getParameter("expiryDate"));
            boolean isSurplus = request.getParameter("isSurplus") != null;
            double price = Double.parseDouble(request.getParameter("price"));
            boolean isVeggie = request.getParameter("isVeggie") != null;

            ProductDTO product = new ProductDTO(name, quantity, expiryDate, isSurplus, retailerID, price, isVeggie);

            RetailersBusinessLogic logic = new RetailersBusinessLogic();
            logic.addProduct(product);

            if (product.isSurplus()) {
                NewsletterDTO notification = newsletterLogic.addMessage(product.getName(), product.getRetailerID());
                newsletterLogic.notifyObservers(notification);
            }

            response.sendRedirect("RetailerDashboardServlet");
        } catch (SQLException ex) {
            Logger.getLogger(CreateProductServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error while adding product.");
        } catch (NumberFormatException ex) {
            Logger.getLogger(CreateProductServlet.class.getName()).log(Level.SEVERE, "Invalid input", ex);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input.");
        } catch (ValidationException ex) {
            Logger.getLogger(CreateProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}