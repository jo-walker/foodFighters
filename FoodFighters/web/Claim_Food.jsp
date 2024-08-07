<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="DTO.ProductDTO" %>
<%@ page import="BusinessLogic.ProductRetailerDAO" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Claim Surplus Products</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Css/organization_style.css">
</head>
<body>
    <div class="container">
        <h1>Available Surplus Products</h1>
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
                    ProductRetailerDAO dao = new ProductRetailerDAO();
                    List<ProductDTO> surplusProducts = dao.getSurplusProducts();
                    for (ProductDTO product : surplusProducts) {
                %>
                <tr>
                    <td><%= product.getId() %></td>
                    <td><%= product.getName() %></td>
                    <td><%= String.format("%.2f", product.getPrice()) %></td>
                    <td><%= product.getQuantity() %></td>
                    <td><%= product.getExpiryDate() %></td>
                    <td>
                        <form action="${pageContext.request.contextPath}/ClaimProductServlet" method="post">
                            <input type="hidden" name="productId" value="<%= product.getId() %>">
                            <input type="hidden" name="charityOrgID" value="<%= session.getAttribute("charityOrgID") %>">
                            <input type="number" name="quantity" min="1" max="<%= product.getQuantity() %>" required>
                            <input type="submit" value="Claim">
                        </form>
                    </td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>
