<%-- 
    Document   : login
    Created on : Jul. 24, 2024, 11:30:32 a.m.
    Author     : Andrea Visani 041104651 visa0004@algonquinlive.com
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>Login</title>
</head>
<body bgcolor="#FDF5E6">
  <center>
    <h2>Login</h2>
    <form action="LoginServlet" method="POST">
      Username:
      <input type="text" name="username" required><br><br>
      Password:
      <input type="password" name="password" required><br><br>
      <input type="submit" value="Login">
    </form>
  </center>
</body>
</html>

