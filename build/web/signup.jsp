<%-- 
    Document   : signup
    Created on : Jul. 24, 2024, 11:36:08 a.m.
    Author     : Andrea Visani 041104651 visa0004@algonquinlive.com
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>Sign Up</title>
  <script>
    function showFields() {
      var role = document.getElementById("role").value;
      document.getElementById("retailerFields").style.display = (role == 2) ? "block" : "none";
      document.getElementById("charityFields").style.display = (role == 3) ? "block" : "none";
      document.getElementById("consumerFields").style.display = (role == 1) ? "block" : "none";
    }

    function goBack() {
      window.history.back();
    }
  </script>
</head>
<body bgcolor="#FDF5E6" onload="showFields()">
  <center>
    <h2>Sign Up</h2>
    <form action="AddUserServlet" method="POST">
      Username:
      <input type="text" name="username" required><br><br>
      Password:
      <input type="password" name="password" required><br><br>
      Email:
      <input type="email" name="email" required><br><br>
      Role:
      <select id="role" name="role" onchange="showFields()" required>
        <option value="1">Consumer</option>
        <option value="2">Retailer</option>
        <option value="3">Charity Organization</option>
      </select><br><br>
      
      <div id="consumerFields" style="display:none;">
        First Name:
        <input type="text" name="firstName"><br><br>
        Last Name:
        <input type="text" name="lastName"><br><br>
        Location:
        <input type="text" name="location"><br><br>
        Phone:
        <input type="text" name="phone"><br><br>
        Diet Type:
        <input type="checkbox" name="dietType" value="true"> Vegetarian<br><br>

      </div>

      <div id="retailerFields" style="display:none;">
        Retailer Name:
        <input type="text" name="retailerName"><br><br>
      </div>
      
      <div id="charityFields" style="display:none;">
        Charity Organization Name:
        <input type="text" name="charityName"><br><br>
      </div>
      
      <input type="submit" value="Sign Up"><br><br>
    </form>
    <button onclick="goBack()">Back</button>
  </center>
</body>
</html>



