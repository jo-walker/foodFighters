<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Donate a Product</title>
  <link rel="stylesheet" type="text/css" href="./Css/donate_style.css">
</head>
<body>
  <div class="container">
    <h2>Donate a Product</h2>
    <form action="${pageContext.request.contextPath}/DonateProductServlet" method="POST">
      <input type="hidden" name="charityOrgID" value="<%= session.getAttribute("charityOrgID") %>">
      
      <div class="form-group">
        <label for="productName">Product Name:</label>
        <input type="text" id="productName" name="productName" required>
      </div>

      <div class="form-group">
        <label for="quantity">Quantity:</label>
        <input type="number" id="quantity" name="quantity" required>
      </div>

      <div class="form-group">
        <label for="expiryDate">Expiry Date:</label>
        <input type="date" id="expiryDate" name="expiryDate" required>
      </div>

      <div class="form-group">
        <label for="isVeggie">Is Vegetarian:</label>
        <input type="checkbox" id="isVeggie" name="isVeggie">
      </div>

      <div class="form-buttons">
        <input type="submit" value="Donate Product" class="submit-button">
        <button type="button" onclick="document.getElementById('backForm').submit();" class="back-button">Back to Dashboard</button>
      </div>
    </form>

    <!-- FORM TO NAVIGATE BACK TO ORGANIZATION DASHBOARD -->
    <form id="backForm" action="${pageContext.request.contextPath}/OrganizationDashboardServlet" method="get" style="display:none;">
      <input type="hidden" name="charityOrgID" value="<%= session.getAttribute("charityOrgID") %>">
    </form>
  </div>
</body>
</html>
