<%-- 
    Document   : editProduct
    Created on : Jul. 30, 2024, 5:50:41 p.m.
    Author     : Andrea Visani 041104651 visa0004@algonquinlive.com
--%>

<%@page import="DAO.ProductDAOImpl"%>
<%@page import="DAO.ProductDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="DTO.ProductDTO" %>
<%@ page import="BusinessLogic.RetailersBusinessLogic" %>
<%
    ProductDAO productDAO = new ProductDAOImpl();
    int productId = Integer.parseInt(request.getParameter("id"));
    ProductDTO product = productDAO.getProductByID(productId);
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Product</title>
</head>
<body>
    <h1>Edit Product</h1>
    <form action="UpdateProductServlet" method="post">
        <input type="hidden" name="id" value="<%= product.getId() %>">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="<%= product.getName() %>"><br><br>
        <label for="price">Price:</label>
        <input type="text" id="price" name="price" value="<%= product.getPrice() %>"><br><br>
        <label for="quantity">Quantity:</label>
        <input type="text" id="quantity" name="quantity" value="<%= product.getQuantity() %>"><br><br>
        <label for="surplus">Is Surplus:</label>
        <input type="checkbox" id="surplus" name="surplus" <%= product.isSurplus() ? "checked" : "" %>><br><br>
        <button type="submit">Update</button>
        <button type="button" onclick="location.href='retailerDashboard.jsp'">Back</button>
    </form>
</body>
</html>
