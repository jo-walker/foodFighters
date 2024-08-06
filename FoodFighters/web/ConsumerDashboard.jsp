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
     <script>
        function openModal(productId, productName) {
            document.getElementById('modalProductId').value = productId;
            document.getElementById('modalProductName').value = productName;
            document.getElementById('myModal').style.display = 'block';
        }

        function closeModal() {
            document.getElementById('myModal').style.display = 'none';
        }

        function changeQuantity(change) {
            var quantityInput = document.getElementById('quantityInput');
            var currentQuantity = parseInt(quantityInput.value, 10);
            var newQuantity = currentQuantity + change;
            if (newQuantity > 0) {
                quantityInput.value = newQuantity;
            }
        }

        window.onclick = function(event) {
            if (event.target == document.getElementById('myModal')) {
                closeModal();
            }
        }
    </script>
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
    <h2> Surplus Items </h2>
     <div class="surplus-container">
        <%
            // Instantiate the business logic class
            ConsumersBusinessLogic consumerLogic = new ConsumersBusinessLogic();
            ProductDAO producty = new ProductDAOImpl(); 
            // Get the list of all products
            List<ProductDTO> products = producty.getSurplusProducts(); 
            
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
                <button onclick="openModal('<%= product.getId() %>', '<%= product.getName() %>')">Add to basket</button>
            </div>
        </div>
        <%
                }
            } else {
        %>
        <p style="text-align: center; width: 100%; font-size: 18px; color: #666;">No surplus products available.</p>
        <%
            }
        %>
    </div>
    <h2> Products for you: </h2>
    <div class="container">
        <%
            
           
            // Get the list of all products
            Integer isVeg = (Integer) session.getAttribute("isVeg");
                    
            List<ProductDTO> allproducts = producty.getProductsByVegStatus(isVeg); 
            
            if (allproducts != null && !allproducts.isEmpty()) {
                for (ProductDTO allproduct : allproducts) {
                    // Generate the image file name based on product name
                    String imageName = allproduct.getName().toLowerCase().replaceAll("\\s+", "") + ".png";
                    String imageUrl = "../assets/images/" + imageName;
        %>
        <div class="card">
        <img src="<%= imageUrl %>" alt="<%= imageUrl %>">
        <div class="card-content">
            <h2><%= allproduct.getName() %></h2>
            <p>Price: $<%= allproduct.getPrice() %></p>
            <p>Expiry Date: <%= allproduct.getExpiryDate() %></p>
            <button onclick="openModal('<%= allproduct.getId() %>', '<%= allproduct.getName() %>')">Add to basket</button>
        </div>
    </div>

    <!-- The Modal -->
    <div id="myModal" class="modal">
    <div class="modal-content">
        <span class="close" onclick="closeModal()">&times;</span>
        <h2>Add Product to Basket</h2>
        
        <form id="basketForm" action="AddToBasketServlet" method="post">
            <input type="hidden" name="productId" id="modalProductId">
            <input type="hidden" name="productName" id="modalProductName">
            <div class="quantity-controls">
                <button type="button" onclick="changeQuantity(-1)">-</button>
                <input type="text" id="quantityInput" name="quantity" value="1" readonly>
                <button type="button" onclick="changeQuantity(1)">+</button>
            </div>
            <button type="submit">Add to basket</button>
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
