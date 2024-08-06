<%@ page import="DTO.OrganizationDTO" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Charity Organization Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Css/organization_style.css">
</head>
<body>
    <header>
        <div class="container">
            <h1>Charity Organization Dashboard</h1>
            <nav>
                <ul>
                    <li><a href="orgProfile.jsp">Profile</a></li>
                    <li><a href="orgDonations.jsp">Donations</a></li>
                    <li><a href="LogoutServlet">Logout</a></li>
                </ul>
            </nav>
        </div>
    </header>
    
    <main>
        <div class="container">
            <section class="org-details">
                <h2>Organization Details</h2>
                <div class="card">
                    <p><strong>Organization Name:</strong> ${organization.name}</p>
                    <p><strong>Email:</strong> ${organization.email}</p>
                    <p><strong>Role:</strong> ${organization.role == 3 ? 'Charity Organization' : 'Unknown'}</p>
                </div>
            </section>
                <section>
        <h2>Donate Product</h2>
        <form action="DonationServlet" method="post">
            <input type="hidden" name="charityOrgID" value="${charityOrgID}">
            
            <label for="productName">Product Name:</label>
            <input type="text" id="productName" name="productName" required>

            <label for="quantity">Quantity:</label>
            <input type="number" id="quantity" name="quantity" required>

            <label for="expiryDate">Expiry Date:</label>
            <input type="date" id="expiryDate" name="expiryDate" required>

            <button type="submit">Donate Product</button>
        </form>
    </section>

            <section class="actions">
                <h2>Actions</h2>
                <div class="card">
                    <ul>
                        <li><a href="updateDetails.jsp" class="btn">Update Details</a></li>
                        <li><a href="viewDonations.jsp" class="btn">View Donations</a></li>
                    </ul>
                </div>
            </section>
        </div>
    </main>
    
    <footer>
        <div class="container">
            <p>&copy; 2024 Charity Organization Dashboard. All rights reserved.</p>
        </div>
    </footer>
</body>
</html>
