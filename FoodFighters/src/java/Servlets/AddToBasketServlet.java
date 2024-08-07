package Servlets;

import DTO.BasketItem;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddToBasketServlet
 */
public class AddToBasketServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        int productId = Integer.parseInt(request.getParameter("productId"));
        String productName = request.getParameter("productName");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int retailerID = Integer.parseInt(request.getParameter("retailerID")); // Get retailerID from the form

        Map<Integer, BasketItem> basket = (Map<Integer, BasketItem>) session.getAttribute("basket");
        if (basket == null) {
            basket = new HashMap<>();
        }

        if (basket.containsKey(productId)) {
            BasketItem item = basket.get(productId);
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            basket.put(productId, new BasketItem(productId, productName, quantity, retailerID));
        }

        session.setAttribute("basket", basket);
        response.sendRedirect("ConsumerDashboard.jsp");
    }
}
