<%-- 
    Document   : retailerDashboard
    Created on : Jul. 25, 2024, 5:59:50 p.m.
    Author     : Andrea Visani 041104651 visa0004@algonquinlive.com
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="DTO.ProductDTO" %>
<%@ page import="BusinessLogic.RetailersBusinessLogic" %>
<%@ page import="javax.servlet.http.HttpSession"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Retailer Dashboard</title>
    <link rel="stylesheet" type="text/css" href="./Css/retailer_style.css">
    <script>
        function confirmDeletion(productId) {
            var confirmation = confirm("Are you sure you want to delete this product?");
            if (confirmation) {
                window.location.href = 'DeleteProductServlet?id=' + productId;
            }
        }
    </script>
</head>
<body>
    <%
        session = request.getSession(false);
        Integer retailerID = (Integer) session.getAttribute("retailerID");
    %>
    <div class="container">
        <h1>Retailer Dashboard - ID: <%= retailerID %></h1>
        <div class="buttons">
            <button onclick="location.href='addProduct.jsp'">Add Product</button>
            <button onclick="location.href='LogoutServlet'">Logout</button>
        </div>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Expiry Date</th>
                    <th>Is Surplus</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <%
                    RetailersBusinessLogic retailerLogic = new RetailersBusinessLogic();
                    List<ProductDTO> products = retailerLogic.getProductsByRetailerID(retailerID);
                    if (products == null || products.isEmpty()) {
                        out.println("<tr><td colspan='8'>No products found for this retailer.</td></tr>");
                    } else {
                        for (ProductDTO product : products) {
                %>
                <tr>
                    <td><%= product.getId() %></td>
                    <td><%= product.getName() %></td>
                    <td><%= String.format("%.2f", product.getPrice()) %></td>
                    <td><%= product.getQuantity() %></td>
                    <td><%= product.getExpiryDate()%></td>
                    <td><%= product.isSurplus() %></td>
                    <td>
                        <button class="edit_button" onclick="location.href='editProduct.jsp?id=<%= product.getId() %>'">Edit</button>
                    </td>
                    <td>
                        <button onclick="confirmDeletion(<%= product.getId() %>)">Delete</button>
                    </td>
                </tr>
                <%
                        }
                    }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>
