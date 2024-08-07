/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import DAO.ProductDAO;
import DAO.ProductDAOImpl;
import DTO.ProductDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Andrea Visani 041104651 visa0004@algonquinlive.com
 */
public class UpdateProductServ extends HttpServlet {

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
        ProductDTO product = new ProductDTO();
        
        product.setId(Integer.parseInt(request.getParameter("id")));
        product.setName(request.getParameter("name"));
        product.setPrice(Double.parseDouble(request.getParameter("price")));
        product.setQuantity(Integer.parseInt(request.getParameter("quantity")));
        product.setSurplus(request.getParameter("surplus") != null);
        product.setExpiryDate(new java.util.Date(java.sql.Date.valueOf(request.getParameter("expiryDate")).getTime()));
        product.setVeggie(request.getParameter("isVeggie") != null);
        product.setRetailerID(Integer.parseInt(request.getParameter("retailerID")));

        ProductDAO productDAO = new ProductDAOImpl();
        try {
            productDAO.updateProduct(product);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateProductServ.class.getName()).log(Level.SEVERE, null, ex);
        }

        response.sendRedirect("RetailerDashboardServlet");
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