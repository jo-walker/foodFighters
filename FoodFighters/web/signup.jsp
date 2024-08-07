<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <style>
        body {
            background-color: #FDF5E6;
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .container {
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.2);
            padding: 20px;
            width: 100%;
            max-width: 600px;
            box-sizing: border-box;
            text-align: center;
        }
        h2 {
            color: #333;
            margin-bottom: 20px;
        }
        form {
            display: flex;
            flex-direction: column;
            align-items: stretch; /* Ensure children stretch to full width */
        }
        .form-group {
            margin-bottom: 15px;
            text-align: left;
        }
        label {
            font-weight: bold;
            display: block;
            margin-bottom: 5px;
        }
        input[type="text"], input[type="password"], input[type="email"], select {
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            width: 100%; /* Full width */
            box-sizing: border-box; /* Include padding and border in the width */
        }
        input[type="checkbox"] {
            margin-right: 5px;
        }
        input[type="submit"], button {
            background-color: #4CAF50;
            color: #ffffff;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s ease;
            margin-top: 10px;
            margin-bottom: 10px;
            width: 100%; /* Full width */
        }
        input[type="submit"]:hover, button:hover {
            background-color: #45a049;
        }
        .back-button {
            background-color: #f44336; /* Red color for the back button */
        }
        .back-button:hover {
            background-color: #e53935;
        }
        .conditional-fields {
            display: none; /* Hide all conditional fields by default */
        }
    </style>
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
<body onload="showFields()">
    <div class="container">
        <h2>Sign Up</h2>
        <form action="AddUserServlet" method="POST">
            <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" required>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>
            </div>
            <div class="form-group">
                <label for="role">Role:</label>
                <select id="role" name="role" onchange="showFields()" required>
                    <option value="1">Consumer</option>
                    <option value="2">Retailer</option>
                    <option value="3">Charity Organization</option>
                </select>
            </div>

            <div id="consumerFields" class="conditional-fields">
                <div class="form-group">
                    <label for="firstName">First Name:</label>
                    <input type="text" id="firstName" name="firstName">
                </div>
                <div class="form-group">
                    <label for="lastName">Last Name:</label>
                    <input type="text" id="lastName" name="lastName">
                </div>
                <div class="form-group">
                    <label for="location">Location:</label>
                    <input type="text" id="location" name="location">
                </div>
                <div class="form-group">
                    <label for="phone">Phone:</label>
                    <input type="text" id="phone" name="phone">
                </div>
                <div class="form-group">
                    <label for="dietType">Diet Type:</label>
                    <input type="checkbox" id="dietType" name="dietType" value="true"> Vegetarian
                </div>
            </div>

            <div id="retailerFields" class="conditional-fields">
                <div class="form-group">
                    <label for="retailerName">Retailer Name:</label>
                    <input type="text" id="retailerName" name="retailerName">
                </div>
            </div>
            
            <div id="charityFields" class="conditional-fields">
                <div class="form-group">
                    <label for="charityName">Charity Organization Name:</label>
                    <input type="text" id="charityName" name="charityName">
                </div>
            </div>
            
            <input type="submit" value="Sign Up">
        </form>
        <button class="back-button" onclick="goBack()">Back</button>
    </div>
</body>
</html>
