<%@page import="DTO.NewsletterDTO"%>
<%@page import="BusinessLogic.NewsletterLogic"%>
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
            document.getElementById('modalProductId').value = productId;
            document.getElementById('modalProductName').value = productName;
            document.getElementById('modalRetailerID').value = retailerID;
            document.getElementById('myModal').style.display = 'block';
        }

        function confirmDeletion(newsletterID) {
            var confirmation = confirm("Are you sure you want to delete this message?");
            if (confirmation) {
                window.location.href = 'DeleteMessageServlet?id=' + newsletterID;
            }
        }

        function closeModal() {
            document.getElementById('myModal').style.display = 'none';
        }

        function changeQuantity(amount) {
            var quantityInput = document.getElementById('quantityInput');
            var currentQuantity = parseInt(quantityInput.value, 10) || 0;
            var newQuantity = currentQuantity + amount;

            if (newQuantity < 1) {
                newQuantity = 1;
            }

            quantityInput.value = newQuantity;
        }

        function showBasket() {
            var xhr = new XMLHttpRequest();
            xhr.open('GET', 'ViewBasketServlet', true);
            xhr.onload = function () {
                if (xhr.status === 200) {
                    var basket = JSON.parse(xhr.responseText);
                    var basketItems = document.getElementById('basketItems');
                    basketItems.innerHTML = '';

                    if (Object.keys(basket).length > 0) {
                        for (var key in basket) {
                            var item = basket[key];
                            basketItems.innerHTML += '<p>Product Name: ' + item.productName + ', Quantity: ' + item.quantity + '</p>';
                        }
                    } else {
                        basketItems.innerHTML = '<p>Your basket is empty.</p>';
                    }
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

        window.onclick = function(event) {
            if (event.target == document.getElementById('myModal')) {
                closeModal();
            } else if (event.target == document.getElementById('basketModal')) {
                closeBasketModal();
            }
        }
    </script>
</head>
<body>
    <div class="header">
        <div class="greeting">
            <%
                session = request.getSession(false);
                Integer customerID = (Integer) session.getAttribute("customerID");
                String username = (String) session.getAttribute("firstName");
            %>
            Hi <%= username %>
        </div>
        <a href="LogoutServlet" class="logout-button">Logout</a>

        <!-- ANDREA: ADDED SUBSCRIBE -->
        <form action="SubscribeServlet" method="post">
            <input type="hidden" name="consumerID" value="<%= customerID %>">
            <button type="submit" class="subscribe-button">Subscribe</button>
        </form>
            <!-- ANDREA: ADDED UNSUBSCRIBE -->
        <form action="UnsubscribeUserServlet" method="post">
            <input type="hidden" name="consumerID" value="<%= customerID %>">
            <button type="submit" class="unsubscribe-button">Unsubscribe</button>
        </form>
    </div>

    <h1>Consumer Dashboard</h1>
    
    <form action="ViewBasketServlet" method="get" onsubmit="showBasket(); return false;">
        <button type="submit">View Basket</button>
    </form>

    <div id="basketModal" class="modal">
        <div class="modal-content">
            <span class="close" onclick="closeBasketModal()">&times;</span>
            <h2>Basket Contents</h2>
            <div id="basketItems"></div>
            <button id="checkoutButton" onclick="checkoutBasket()">Checkout</button>
        </div>
    </div>

    <h2> Surplus Items: </h2>
    <div class="surplus-container">
        <%
            ConsumersBusinessLogic consumerLogic = new ConsumersBusinessLogic();
            ProductDAO producty = new ProductDAOImpl(); 
            List<ProductDTO> products = producty.getSurplusProducts();

            if (products != null && !products.isEmpty()) {
                for (ProductDTO product : products) {
                    String imageName = product.getName().toLowerCase().replaceAll("\\s+", "") + ".png";
                    String imageUrl = "../assets/images/" + imageName;
        %>
        <div class="card">
            <img src="<%= imageUrl %>" alt="<%= imageUrl %>">
            <div class="card-content">
                <h2><%= product.getName() %></h2>
                <p>Price: $<%= product.getPrice() %></p>
                <p>Expiry Date: <%= product.getExpiryDate() %></p>
                <button onclick="openModal('<%= product.getId() %>', '<%= product.getName() %>', '<%= product.getRetailerID() %>')">Add to basket</button>
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
            Integer isVeg = (Integer) session.getAttribute("isVeg");
            List<ProductDTO> allproducts = producty.getProductsByVegStatus(isVeg); 

            if (allproducts != null && !allproducts.isEmpty()) {
                for (ProductDTO allproduct : allproducts) {
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

    <div id="myModal" class="modal">
        <div class="modal-content">
            <span class="close" onclick="closeModal()">&times;</span>
            <h2>Add Product to Basket</h2>
            <form id="basketForm" action="AddToBasketServlet" method="post">
                <input type="hidden" name="productId" id="modalProductId">
                <input type="hidden" name="productName" id="modalProductName">
                <input type="hidden" name="retailerID" id="modalRetailerID">
                <div class="quantity-controls">
                    <button type="button" onclick="changeQuantity(-1)">-</button>
                    <input type="text" id="quantityInput" name="quantity" value="1" readonly>
                    <button type="button" onclick="changeQuantity(1)">+</button>
                </div>
                <button type="submit">Add to basket</button>
            </form>
        </div>
    </div>

    <div class="container">
        <table>
            <thead>
                <tr>
                    <th>Message</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <%
                    NewsletterLogic newsletterLogic = new NewsletterLogic();
                    List<NewsletterDTO> messages = newsletterLogic.getMessagesByUserIDSortedDESC(customerID);

                    if (messages == null || messages.isEmpty()) {
                        out.println("<tr><td colspan='3'>No notifications to display.</td></tr>");
                    } else {
                        for (NewsletterDTO message : messages) {
                %>
                <tr>
                    <td><%= message.getNotification() %></td>
                    <td><button onclick="confirmDeletion(<%= message.getId() %>)">Delete</button></td>
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
