package Servlets;

import DAO.ProductDAO;
import DAO.ProductDAOImpl;
import DTO.BasketItem;
import Utilities.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Map<Integer, BasketItem> basket = (Map<Integer, BasketItem>) session.getAttribute("basket");
        
        if (basket == null || basket.isEmpty()) {
            response.sendRedirect("ConsumerDashboard.jsp");
            return;
        }

        ProductDAO productDAO = new ProductDAOImpl();

        Connection con = null;
        PreparedStatement preparedStatement = null;

        try {
            con = DataSource.getConnection();
            con.setAutoCommit(false);

            String updateQuery = "UPDATE ProductRetailer SET productQuantity = productQuantity - ? WHERE productID = ? AND retailerID = ?";
            preparedStatement = con.prepareStatement(updateQuery);

            for (BasketItem item : basket.values()) {
                preparedStatement.setInt(1, item.getQuantity());
                preparedStatement.setInt(2, item.getProductId());
                preparedStatement.setInt(3, item.getRetailerID());
                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();
            con.commit();

            session.removeAttribute("basket");

        } catch (SQLException e) {
            e.printStackTrace();
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            response.sendRedirect("error.jsp");
            return;
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        response.sendRedirect("ConsumerDashboard.jsp");
    }
}
