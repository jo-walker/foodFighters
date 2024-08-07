<%-- 
    Document   : error page
    Created on : Aug. 7, 2024, 7:56 a.m.
    Author     : Baasanlkham Gurvantamir
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Error!</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
        }
        .container {
            text-align: center;
            padding: 50px;
        }
        .message {
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            display: inline-block;
        }
        .message h1 {
            color: #d9534f;
        }
        .message p {
            color: #333;
        }
        .message a {
            color: #337ab7;
            text-decoration: none;
        }
        .message a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="message">
            <h1>Oopsie! Something went wrong.</h1>
            <p>We're sorry, but an error occurred while processing your request.</p>
            <p><%= request.getAttribute("error") %></p>
            <p><a href="<%= request.getContextPath() %>/../index.html">Go to Home Page</a></p>
        </div>
    </div>
</body>
</html>