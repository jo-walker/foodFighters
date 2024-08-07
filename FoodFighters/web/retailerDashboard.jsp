<%-- 
    Document   : retailerDashboard.jsp
    Created on : Jul. 25, 2024, 5:59:50 p.m.
    Author     : Andrea Visani 041104651 visa0004@algonquinlive.com
    Description: This file contains the code for the Retailer Dashboard page.
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="DTO.ProductDTO" %>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@page import="java.util.Date" %>
<%@page import="java.util.concurrent.TimeUnit" %>

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
            <!-- SORT BY EXPIRY DATE -->
            <form action="${pageContext.request.contextPath}/RetailerDashboardServlet" method="get" style="display:inline;">
                <button type="submit" name="sortByExpiryDate">Sort by Expiry Date</button>
            </form>
            <!-- SORT BY PRICE -->
            <form action="${pageContext.request.contextPath}/RetailerDashboardServlet" method="get" style="display:inline;">
                <button type="submit" name="sortByPrice">Sort by Price</button>
            </form>
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
                    List<ProductDTO> products = (List<ProductDTO>) request.getAttribute("products");
                    if (products == null || products.isEmpty()) {
                        out.println("<tr><td colspan='8'>No products found for this retailer.</td></tr>");
                    } else {
                        Date currentDate = new Date();

                        for (ProductDTO product : products) {
                        
                        //CALCULATE THE DIFFERENCE BETWEEN CURREND DATE AND EXPIRY DATE
                            Date expiryDate = product.getExpiryDate();
                            long diffInMillies = expiryDate.getTime() - currentDate.getTime();
                            long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                            
                            // IF IS LESS THAN 7 DAYS, APPLY A NEW BG COLOR
                            String rowStyle = "";
                            if (diffInDays < 7) {
                                rowStyle = "style='background-color: orange;'";
                            }
                %>
                <!-- APPLY BG COLOR rowStyle TO ALL ROWS ELEMENTS -->
                <tr <%= rowStyle %>>
                    <td <%= rowStyle %>><%= product.getId() %></td>
                    <td <%= rowStyle %>><%= product.getName() %></td>
                    <td <%= rowStyle %>><%= String.format("%.2f", product.getPrice()) %></td>
                    <td <%= rowStyle %>><%= product.getQuantity() %></td>
                    <td <%= rowStyle %>><%= product.getExpiryDate() %></td>
                    <td <%= rowStyle %>><%= product.isSurplus() %></td>
                    <td <%= rowStyle %>>
                        <button class="edit_button" onclick="location.href='editProduct.jsp?id=<%= product.getId() %>'">Edit</button>
                    </td>
                    <td <%= rowStyle %>>
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
