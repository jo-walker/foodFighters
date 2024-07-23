<%-- 
    Document   : addRetailer.jsp
    Created on : Jul. 23, 2024, 4:25:49 p.m.
    Author     : Andrea Visani 041104651 visa0004@algonquinlive.com
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>Add Retailer</title>
</head>
<body bgcolor="#FDF5E6">
  <center>
    <h2>Add a Retailer</h2>
    <form action="http://localhost:8080/FoodFighters/AddRetailerServlet" method="POST">
      Username:
      <input type="text" name="username" required><br><br>
      Password:
      <input type="password" name="password" required><br><br>
      Email:
      <input type="email" name="email" required><br><br>
      Role:
      <input type="number" name="role" required><br><br>
      Retailer Name:
      <input type="text" name="retailerName" required><br><br>
      <input type="submit" value="Add Retailer">
    </form>
  </center>
</body>
</html>
