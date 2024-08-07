package Servlets;

import BusinessLogic.RetailersBusinessLogic;
import DTO.ProductDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Andrea Visani 041104651 visa0004@algonquinlive.com
 */
@WebServlet("/RetailerDashboardServlet")
public class RetailerDashboardServlet extends HttpServlet {

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
        HttpSession session = request.getSession(false);
        Integer retailerID = (Integer) session.getAttribute("retailerID");

        if (retailerID == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Retailer ID is missing in session.");
            return;
        }

        RetailersBusinessLogic retailerLogic = new RetailersBusinessLogic();
        List<ProductDTO> products;

        try {
            if (request.getParameter("sortByPrice") != null) {
                products = retailerLogic.getProductsByRetailerIDSortedByPrice(retailerID);
            } else if (request.getParameter("sortByExpiryDate") != null) {
                products = retailerLogic.getProductsByRetailerIDSortedByExpiryDate(retailerID);
            } else {
                products = retailerLogic.getProductsByRetailerID(retailerID);
            }

            request.setAttribute("products", products);
            request.getRequestDispatcher("/retailerDashboard.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(RetailerDashboardServlet.class.getName()).log(Level.SEVERE, "SQL Error", ex);
            request.setAttribute("error", "SQL error occurred: " + ex.getMessage());
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(RetailerDashboardServlet.class.getName()).log(Level.SEVERE, "General Error", ex);
            request.setAttribute("error", "An error occurred: " + ex.getMessage());
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
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
        return "Retailer Dashboard Servlet";
    }// </editor-fold>

}
