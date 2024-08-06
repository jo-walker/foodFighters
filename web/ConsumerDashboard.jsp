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
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        h1 {
            background-color: #4CAF50;
            color: white;
            padding: 20px;
            text-align: center;
            margin: 0;
        }

        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
        }

        .greeting {
            font-size: 18px;
        }

        .logout-button {
            padding: 10px 20px;
            background-color: #f44336;
            border: none;
            border-radius: 5px;
            color: white;
            font-size: 16px;
            cursor: pointer;
            text-decoration: none;
        }

        .logout-button:hover {
            background-color: #e53935;
        }

        .container {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            padding: 20px;
        }

        .card {
            border: 1px solid #ddd;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 300px;
            margin: 15px;
            background-color: #fff;
            overflow: hidden;
            transition: transform 0.3s, box-shadow 0.3s;
        }

        .card:hover {
            transform: translateY(-5px);
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.2);
        }

        .card img {
            width: 100%;
            height: 200px;
            object-fit: cover;
        }

        .card-content {
            padding: 15px;
            text-align: center;
        }

        .card-content h2 {
            font-size: 1.5em;
            margin: 0 0 10px;
            color: #333;
        }

        .card-content p {
            margin: 5px 0;
            color: #555;
        }

        .card-content .add-to-cart {
            margin-top: 15px;
            padding: 10px 20px;
            background-color: #4CAF50;
            border: none;
            border-radius: 5px;
            color: white;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .card-content .add-to-cart:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="header">
        <div class="greeting">
            <%
                // Retrieve username from session
                String username = (session != null) ? (String) session.getAttribute("username") : "Guest";
            %>
            Hi <%= username %>
        </div>
        <a href="LogoutServlet" class="logout-button">Logout</a>
    </div>

    <h1>Consumer Dashboard</h1>

    <div class="container">
        <%
            // Instantiate the business logic class
            ConsumersBusinessLogic consumerLogic = new ConsumersBusinessLogic();
            ProductDAO producty = new ProductDAOImpl(); 
            // Get the list of all products
            List<ProductDTO> products = producty.getAllProducts();
            
            if (products != null && !products.isEmpty()) {
                for (ProductDTO product : products) {
                    // Generate the image file name based on product name
                    String imageName = product.getName().toLowerCase().replaceAll("\\s+", "") + ".png";
                    String imageUrl = "/images/" + imageName;
        %>
        <div class="card">
            <img src="<%= imageUrl %>" alt="<%= product.getName() %>">
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
