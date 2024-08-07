<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="DTO.ProductDTO" %>
<%@ page import="BusinessLogic.OrganizationBusinessLogic" %>
<%@ page import="javax.servlet.http.HttpSession"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Charity Organization</title>
    <link rel="stylesheet" type="text/css" href="./Css/organization_style.css">
    <script>
        function confirmDeletion(donationId) {
            var confirmation = confirm("Are you sure you want to delete this donation?");
            if (confirmation) {
                window.location.href = 'DeleteDonationServlet?id=' + donationId;
            }
        }
    </script>
    <style>
    body {
    font-family: Arial, sans-serif;
    background-color: #FDF5E6;
    margin: 0;
    padding: 0;
}

.container {
    width: 90%;
    max-width: 1200px;
    margin: auto;
    padding: 20px;
    background-color: #fff;
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

h1 {
    color: #333;
    margin-bottom: 20px;
}

.buttons {
    margin-bottom: 20px;
    text-align: center;
}

.btn {
    background-color: #007BFF;
    border: none;
    color: #fff;
    padding: 10px 20px;
    font-size: 16px;
    border-radius: 5px;
    cursor: pointer;
    text-decoration: none;
    margin: 0 10px;
    transition: background-color 0.3s;
}

.btn:hover {
    background-color: #0056b3;
}

.logout-button {
    background-color: #dc3545;
}

.logout-button:hover {
    background-color: #c82333;
}

table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 20px;
}

table th, table td {
    border: 1px solid #ddd;
    padding: 10px;
    text-align: center;
}

table th {
    background-color: #007BFF;
    color: white;
}

table tr:nth-child(even) {
    background-color: #f2f2f2;
}

.delete-button {
    background-color: #dc3545;
    border: none;
    color: white;
    padding: 5px 10px;
    font-size: 14px;
    border-radius: 5px;
    cursor: pointer;
}

.delete-button:hover {
    background-color: #c82333;
}

</style>
</head>
<body>
    
    <%
        session = request.getSession(false);
        Integer charityOrgID = (Integer) session.getAttribute("charityOrgID");
        String charityOrgName = (String) session.getAttribute("charityOrgName");
    %>
    <div class="container">
        <h1>Welcome <%= charityOrgName %></h1>
        <div class="buttons">
            <!-- VIEW DONATIONS -->
            <form action="${pageContext.request.contextPath}/OrganizationDashboardServlet" method="get" style="display:inline;">
                <button type="submit">View Donations</button>
            </form>
            <button onclick="location.href='donateProduct.jsp'">Add Donation</button>
            <button onclick="location.href='LogoutServlet'">Logout</button>
        </div>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Donation Date</th>
                    <th>Is Surplus</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <%
                    OrganizationBusinessLogic organizationLogic = new OrganizationBusinessLogic();
                    List<ProductDTO> donations = (List<ProductDTO>) request.getAttribute("donations");
                    if (donations == null || donations.isEmpty()) {
                        out.println("<tr><td colspan='7'>No donations found for this organization.</td></tr>");
                    } else {
                        for (ProductDTO product : donations) {
                %>
                <tr>
                    <td><%= product.getId() %></td>
                    <td><%= product.getName() %></td>
                    <td><%= String.format("%.2f", product.getPrice()) %></td>
                    <td><%= product.getQuantity() %></td>
                    <td><%= product.getExpiryDate() %></td>
                    <td><%= product.isSurplus() %></td>
                    <td>
                        <button onclick="confirmDeletion(<%= product.getId() %>)">Delete</button>
                    </td>
                </tr>
                <%
                        }
                    }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>
