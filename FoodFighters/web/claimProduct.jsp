<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="DTO.ProductDTO" %>
<%@ page import="BusinessLogic.OrganizationBusinessLogic" %>
<%@ page import="javax.servlet.http.HttpSession"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Claim Products</title>
    <link rel="stylesheet" type="text/css" href="./Css/organization_style.css">
    <style>
        /* Add your CSS styling here */
    </style>
</head>
<body>
    <%
        Integer charityOrgID = (Integer) session.getAttribute("charityOrgID");
        OrganizationBusinessLogic businessLogic = new OrganizationBusinessLogic();
        List<ProductDTO> products = businessLogic.claimFood(); // List products available for claiming

        // Retrieve and display message if present
        String message = (String) request.getAttribute("message");
    %>
    <div class="container">
        <h1>Claim Surplus Products</h1>
        
        <% if (message != null) { %>
        <div class="message">
            <p><%= message %></p>
        </div>
        <% } %>
        
        <form action="ClaimProductServlet" method="post">
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Expiry Date</th>
                        <th>Claim</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (ProductDTO product : products) {
                            if (product.getQuantity() > 0 && product.isSurplus()) { // Ensure only surplus products with quantity are shown
                    %>
                    <tr>
                        <td><%= product.getId() %></td>
                        <td><%= product.getName() %></td>
                        <td><%= String.format("%.2f", product.getPrice()) %></td>
                        <td><%= product.getQuantity() %></td>
                        <td><%= product.getExpiryDate() %></td>
                        <td>
                            <input type="checkbox" name="productsToClaim" value="<%= product.getId() %>">
                        </td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </tbody>
            </table>
            <button type="submit">Claim Selected Products</button>
        </form>
    </div>
</body>
</html>
