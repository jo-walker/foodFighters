<%-- 
    Document   : retailerDashboard
    Created on : Jul. 25, 2024, 5:59:50 p.m.
    Author     : Andrea Visani 041104651 visa0004@algonquinlive.com
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="DTO.ProductDTO" %>
<%@ page import="BusinessLogic.RetailersBusinessLogic" %>
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
    <h1>Retailer Dashboard</h1>
    <button onclick="location.href='addProduct.jsp'">Add Product</button>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Description</th>
                <th>Price</th>
                <th>Quantity</th>
            </tr>
        </thead>

        </tbody>
    </table>
</body>
</html>
