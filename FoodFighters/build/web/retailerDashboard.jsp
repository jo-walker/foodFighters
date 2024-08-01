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
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
    </style>
</head>
<body>
    <%
        session = request.getSession(false);
        Integer retailerID = (Integer) session.getAttribute("retailerID"); //LOGIC FOR GETTING THE ID FROM THE SESSION
    %>
    <h1>Retailer Dashboard - ID: <%= retailerID %></h1>
    <button onclick="location.href='addProduct.jsp'">Add Product</button>
    <button onclick="location.href='LogoutServlet'">Logout</button>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Expiry Date</th>
                <th>Is Surplus</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <%
                RetailersBusinessLogic retailerLogic = new RetailersBusinessLogic();
                List<ProductDTO> products = retailerLogic.getProductsByRetailerID(retailerID);
                if (products == null || products.isEmpty()) {
                    out.println("<tr><td colspan='7'>No products found for this retailer.</td></tr>");
                } else {
                    for (ProductDTO product : products) {

                    // ADDED LINE FOR LOGGING AND DEBUGGING...
                    System.out.println("==============Product ID: " + product.getId() + ", Name: " + product.getName() + "==============");

            %>
            <tr>
                <td><%= product.getId() %></td>
                <td><%= product.getName() %></td>
                <td><%= product.getPrice() %></td>
                <td><%= product.getQuantity() %></td>
                <td><%= product.getExpiryDate()%></td>
                <td><%= product.isSurplus() %></td>
                <td>
                    <button onclick="location.href='editProduct.jsp?id=<%= product.getId() %>'">Edit</button>
                </td>
            </tr>
            <%
                    }
                }
            %>
        </tbody>
    </table>
</body>
</html>
