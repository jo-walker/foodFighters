package Servlets;

import BusinessLogic.NewsletterLogic;
import BusinessLogic.RetailersBusinessLogic;
import DTO.ProductDTO;
import DAO.RetailerDAOImpl;
import DTO.NewsletterDTO;
import Utilities.Exception.ValidationException;
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

/**
 * Servlet that handles the product creation
 * @author Andrea Visani 041104651 visa0004@algonquinlive.com
 */
@WebServlet("/CreateProductServlet")
public class CreateProductServlet extends HttpServlet {
    
    /** the newsletter logic */
    private NewsletterLogic newsletterLogic = new NewsletterLogic();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Integer retailerID = (Integer) session.getAttribute("retailerID");

        if (retailerID == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Retailer ID is missing in session.");
            return;
        }

        String name = request.getParameter("productName");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        java.util.Date expiryDate = new java.util.Date(java.sql.Date.valueOf(request.getParameter("expiryDate")).getTime());
        boolean isSurplus = request.getParameter("isSurplus") != null;
        double price = Double.parseDouble(request.getParameter("price"));
        boolean isVeggie = request.getParameter("isVeggie") != null;
        
        ProductDTO product = new ProductDTO(name, quantity, expiryDate, isSurplus, retailerID, price, isVeggie);

        RetailersBusinessLogic logic = new RetailersBusinessLogic();
        try {
            logic.addProduct(product);
            if (product.isSurplus() == true){
                NewsletterDTO notification = newsletterLogic.addMessage(product.getName(), product.getRetailerID());
                newsletterLogic.notifyObservers(notification);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CreateProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ValidationException ex) {
            Logger.getLogger(CreateProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        //NOW SEND TO SERVLET INSTEAD OF JSP, SO IT CAN HANDLE SORTING
        response.sendRedirect("RetailerDashboardServlet");
    }
}
