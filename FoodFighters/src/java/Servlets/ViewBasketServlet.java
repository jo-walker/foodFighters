/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import DTO.BasketItem;
import java.io.IOException;
import java.io.PrintWriter;
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
public class ViewBasketServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get user session
        HttpSession session = request.getSession();

        // Retrieve the basket from the session
        Map<Integer, BasketItem> basket = (Map<Integer, BasketItem>) session.getAttribute("basket");

        // Set response content type
        response.setContentType("application/json");

        // Create a PrintWriter to write the response
        PrintWriter out = response.getWriter();

        // Start JSON output
        out.print("{");
        if (basket != null && !basket.isEmpty()) {
            boolean first = true;
            for (Map.Entry<Integer, BasketItem> entry : basket.entrySet()) {
                if (!first) {
                    out.print(",");
                }
                first = false;

                BasketItem item = entry.getValue();
                out.print("\"" + entry.getKey() + "\":");
                out.print("{");
                out.print("\"productName\":\"" + item.getProductName() + "\",");
                out.print("\"quantity\":" + item.getQuantity());
                out.print("}");
            }
        }
        out.print("}");

        // Close the PrintWriter
        out.flush();
        out.close();
    }
}
