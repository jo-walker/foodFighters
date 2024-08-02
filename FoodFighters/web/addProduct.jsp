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
  <link rel="stylesheet" type="text/css" href="./Css/retailer_style.css">
</head>
<body bgcolor="#FDF5E6">
  <center>
    <h2>Create a New Product</h2>
    <form action="${pageContext.request.contextPath}/CreateProductServlet" method="POST">
      <input type="hidden" name="retailerID" value="<%= session.getAttribute("retailerID") %>">
        Product Name:
      <input type="text" name="productName" required><br><br>
      Quantity:
      <input type="number" name="quantity" required><br><br>
      Expiry Date:
      <input type="date" name="expiryDate" required><br><br>
      Is Surplus:
      <input type="checkbox" name="isSurplus"><br><br>
      Price:
      <input type="number" name="price" step="0.01" required><br><br>
      Vegetarian compatible:
      <input type="checkbox" name="isVeggie"><br><br>
      
      <div class="form-buttons">
        <input type="submit" value="Create Product">
        <button type="button" onclick="location.href='retailerDashboard.jsp'">Back to Dashboard</button>
      </div>
    </form>
  </center>
</body>
</html>
