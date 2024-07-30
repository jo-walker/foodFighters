package Servlets;

import BusinessLogic.RetailersBusinessLogic;
import DTO.ProductDTO;
import DAO.RetailerDAOImpl;
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

@WebServlet("/CreateProductServlet")
public class CreateProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer retailerID = (Integer) session.getAttribute("retailerID");

        if (retailerID == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Retailer ID is missing in session.");
            return;
        }

        String name = request.getParameter("productName");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        Date expiryDate = Date.valueOf(request.getParameter("expiryDate"));
        boolean isSurplus = request.getParameter("isSurplus") != null;

        ProductDTO product = new ProductDTO();
        product.setName(name);
        product.setQuantity(quantity);
        product.setExpiryDate(expiryDate);
        product.setSurplus(isSurplus);
        product.setRetailerID(retailerID);

        RetailersBusinessLogic logic = new RetailersBusinessLogic();
        try {
            logic.addProduct(product);
        } catch (SQLException ex) {
            Logger.getLogger(CreateProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        response.sendRedirect("retailerDashboard.jsp");
    }
}
