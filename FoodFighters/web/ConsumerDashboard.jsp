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
    function openModal(productId, productName, retailerID) {
        // Set the values in the modal form
        document.getElementById('modalProductId').value = productId;
        document.getElementById('modalProductName').value = productName;
        document.getElementById('modalRetailerID').value = retailerID;

        // Display the modal
        document.getElementById('myModal').style.display = 'block';
    }

    function closeModal() {
        document.getElementById('myModal').style.display = 'none';
    }

    function changeQuantity(amount) {
        var quantityInput = document.getElementById('quantityInput');
        var currentQuantity = parseInt(quantityInput.value) || 0;
        var newQuantity = currentQuantity + amount;

        // Ensure quantity is not less than 1
        if (newQuantity < 1) {
            newQuantity = 1;
        }

        quantityInput.value = newQuantity;
    }
    </script>
    
            <script>
        function showBasket() {
            // Create a new XMLHttpRequest
            var xhr = new XMLHttpRequest();
            xhr.open('GET', 'ViewBasketServlet', true);
            xhr.onload = function () {
                if (xhr.status === 200) {
                    var basket = JSON.parse(xhr.responseText);
                    var basketItems = document.getElementById('basketItems');
                    basketItems.innerHTML = ''; // Clear previous items

                    if (Object.keys(basket).length > 0) {
                        // Loop through basket items and display them
                        for (var key in basket) {
                            var item = basket[key];
                            basketItems.innerHTML += '<p>Product Name: ' + item.productName + ', Quantity: ' + item.quantity + '</p>';
                        }
                    } else {
                        basketItems.innerHTML = '<p>Your basket is empty.</p>';
                    }
                    // Display the modal
                    document.getElementById('basketModal').style.display = 'block';
                }
            };
            xhr.send();
        }

        function closeBasketModal() {
            document.getElementById('basketModal').style.display = 'none';
        }
        
        function checkoutBasket() {
        window.location.href = 'CheckoutServlet';
        }

        // Close the modal when clicking outside of the modal content
        window.onclick = function(event) {
            if (event.target == document.getElementById('basketModal')) {
                document.getElementById('basketModal').style.display = 'none';
            }
        };
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
    
    <form action="ViewBasketServlet" method="get" onsubmit="showBasket(); return false;">
    <button type="submit">View Basket</button>
    </form>

    <!-- Modal HTML -->
    <div id="basketModal" class="modal">
        <div class="modal-content">
            <span class="close" onclick="closeBasketModal()">&times;</span>
            <h2>Basket Contents</h2>
            <div id="basketItems">
                <!-- Basket items will be inserted here -->
            </div>
            <button id="checkoutButton" onclick="checkoutBasket()">Checkout</button>
        </div>
    </div>
    
    <h2> Surplus Items: </h2>
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
            <div class="card-content">
                <h2><%= product.getName() %></h2>
                <p>Price: $<%= product.getPrice() %></p>
                <p>Expiry Date: <%= product.getExpiryDate() %></p>
                <button onclick="openModal('<%= product.getId() %>', '<%= product.getName() %>','<%= product.getRetailerID() %>')">Add to basket</button>
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
                    <button onclick="openModal('<%= allproduct.getId() %>', '<%= allproduct.getName() %>', '<%= allproduct.getRetailerID() %>')">Add to basket</button>
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
    
     <!-- The Modal -->
        <div id="myModal" class="modal">
            <div class="modal-content">
                <span class="close" onclick="closeModal()">&times;</span>
                <h2>Add Product to Basket</h2>
                <form id="basketForm" action="AddToBasket" method="post">
                    <input type="hidden" name="productId" id="modalProductId">
                    <input type="hidden" name="productName" id="modalProductName">
                    <input type="hidden" name="retailerID" id="modalRetailerID"> <!-- Ensure retailerID field is present -->
                    <div class="quantity-controls">
                        <button type="button" onclick="changeQuantity(-1)">-</button>
                        <input type="text" id="quantityInput" name="quantity" value="1" readonly>
                        <button type="button" onclick="changeQuantity(1)">+</button>
                    </div>
                    <button type="submit">Add to basket</button>
                </form>
            </div>
        </div>

</body>
</html>