///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
// */
//package Servlets;
//
//import DAO.ProductDAO;
//import DAO.ProductDAOImpl;
//import DTO.BasketItem;
//import Utilities.DataSource;
//import com.sun.jdi.connect.spi.Connection;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.Map;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
///**
// *
// * @author Owner
// */
//public class CheckOutServlet extends HttpServlet {
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        Map<Integer, BasketItem> basket = (Map<Integer, BasketItem>) session.getAttribute("basket");
//        
//        if (basket == null || basket.isEmpty()) {
//            response.sendRedirect("ConsumerDashboard.jsp");
//            return;
//        }
//
//        ProductDAO productDAO = new ProductDAOImpl();
//
//        Connection con = null;
//        PreparedStatement preparedStatement = null;
//
//        try {
//            con = (Connection) DataSource.getConnection();
//            con.setAutoCommit(false);
//
//            String updateQuery = "UPDATE ProductRetailer SET productQuantity = productQuantity - ? WHERE productID = ? AND retailerID = ?";
//            preparedStatement = con.prepareStatement(updateQuery);
//
//            for (BasketItem item : basket.values()) {
//                preparedStatement.setInt(1, item.getQuantity());
//                preparedStatement.setInt(2, item.getProductId());
//                preparedStatement.setInt(3, item.getRetailerID());
//                preparedStatement.addBatch();
//            }
//
//            preparedStatement.executeBatch();
//            con.commit();
//
//            session.removeAttribute("basket");
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            if (con != null) {
//                try {
//                    con.rollback();
//                } catch (SQLException rollbackEx) {
//                    rollbackEx.printStackTrace();
//                }
//            }
//            response.sendRedirect("error.jsp");
//            return;
//        } finally {
//            if (preparedStatement != null) {
//                try {
//                    preparedStatement.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (con != null) {
//                try {
//                    con.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        response.sendRedirect("ConsumerDashboard.jsp");
//    }
//
//    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
//    /**
//     * Handles the HTTP <code>GET</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Handles the HTTP <code>POST</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Returns a short description of the servlet.
//     *
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
//
//}
