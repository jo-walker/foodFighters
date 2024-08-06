/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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
 *
 * @author Owner
 */
public class AddToBasketServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get user session
        HttpSession session = request.getSession();
        
        // Retrieve form data
        int productId = Integer.parseInt(request.getParameter("productId"));
        String productName = request.getParameter("productName");
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        // Get or initialize the basket
        Map<Integer, BasketItem> basket = (Map<Integer, BasketItem>) session.getAttribute("basket");
        if (basket == null) {
            basket = new HashMap<>();
        }

        // Add or update the item in the basket
        if (basket.containsKey(productId)) {
            BasketItem item = basket.get(productId);
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            basket.put(productId, new BasketItem(productId, productName, quantity));
        }

        // Save basket back to session
        session.setAttribute("basket", basket);

        // Redirect or forward to another page (e.g., a confirmation page or back to product list)
//        response.sendRedirect("ConsumerDashboard.jsp"); // or whatever page you want to redirect to
    }

}