<%-- 
    Document   : addProduct
    Created on : Jul. 24, 2024, 11:29:03 a.m.
    Author     : Andrea Visani 041104651 visa0004@algonquinlive.com
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>Create New Product</title>
</head>
<body bgcolor="#FDF5E6">
  <center>
    <h2>Create a New Product</h2>
    <form action="http://localhost:8080/FoodFighters/CreateProductServlet" method="POST">
      Product Name:
      <input type="text" name="productName" required><br><br>
      Quantity:
      <input type="number" name="quantity" required><br><br>
      Expiry Date:
      <input type="date" name="expiryDate" required><br><br>
      Is Surplus:
      <input type="checkbox" name="isSurplus"><br><br>
      <input type="submit" value="Create Product">
    </form>
  </center>
</body>
</html>
