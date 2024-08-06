<%@ page import="DAO.ProductDAOImpl"%>
<%@ page import="DAO.ProductDAO"%>
<%@ page import="BusinessLogic.ConsumersBusinessLogic"%>
<%@ page import="DTO.ProductDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Consumer Dashboard</title>
    <link rel="stylesheet" type="text/css" href="./Css/consumer.css">
</head>
<body>
    <div class="header">
        <div class="greeting">
            <%
                // Retrieve username from session
                String username = (String) session.getAttribute("firstName");
            %>
            Hi <%= username %>
        </div>
        <a href="LogoutServlet" class="logout-button">Logout</a>
    </div>

    <h1>Consumer Dashboard</h1>

    <div class="surplus-container">
    
    </div>
    
    <div class="container">
        <%
            // Instantiate the business logic class
            ConsumersBusinessLogic consumerLogic = new ConsumersBusinessLogic();
            ProductDAO producty = new ProductDAOImpl(); 
            // Get the list of all products
            Integer isVeg = (Integer) session.getAttribute("isVeg");
                    
            List<ProductDTO> products = producty.getProductsByVegStatus(isVeg); 
            
            if (products != null && !products.isEmpty()) {
                for (ProductDTO product : products) {
                    // Generate the image file name based on product name
                    String imageName = product.getName().toLowerCase().replaceAll("\\s+", "") + ".png";
                    String imageUrl = "../assets/images/" + imageName;
        %>
        <div class="card">
            <img src="<%= imageUrl %>" alt="<%= imageUrl %>">
<!--             //<img src="../assets/images/chicken.png" alt="alt">-->
            <div class="card-content">
                <h2><%= product.getName() %></h2>
                <p>Price: $<%= product.getPrice() %></p>
                <p>Expiry Date: <%= product.getExpiryDate() %></p>
                <form action="AddToCartServlet" method="post">
                    <input type="hidden" name="productId" value="<%= product.getId() %>">
                    <button type="submit" class="add-to-cart">Add to Cart</button>
                </form>
            </div>
        </div>
        <%
                }
            } else {
        %>
        <p style="text-align: center; width: 100%; font-size: 18px; color: #666;">No products available.</p>
        <%
            }
        %>
    </div>
</body>
</html>
