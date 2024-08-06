<%@ page import="DTO.OrganizationDTO" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Charity Organization Dashboard</title>
    <style>
        /* General styling */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            color: #333;
            background-color: #f4f4f4;
        }

        .container {
            width: 90%;
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
        }

        header {
            background-color: #007bff;
            color: #fff;
            padding: 10px 0;
        }

        header h1 {
            margin: 0;
            font-size: 24px;
        }

        nav ul {
            list-style: none;
            padding: 0;
            margin: 0;
        }

        nav ul li {
            display: inline;
            margin-right: 15px;
        }

        nav ul li a {
            color: #fff;
            text-decoration: none;
            font-weight: bold;
        }

        nav ul li a:hover {
            text-decoration: underline;
        }

        main {
            padding: 20px 0;
        }

        .card {
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            padding: 20px;
            margin-bottom: 20px;
        }

        .card h2 {
            margin-top: 0;
            font-size: 20px;
        }

        .card p {
            margin: 10px 0;
        }

        .card ul {
            list-style: none;
            padding: 0;
            margin: 0;
        }

        .card ul li {
            margin-bottom: 10px;
        }

        .btn {
            display: inline-block;
            padding: 10px 15px;
            color: #fff;
            background-color: #007bff;
            text-decoration: none;
            border-radius: 4px;
        }

        .btn:hover {
            background-color: #0056b3;
        }

        footer {
            background-color: #007bff;
            color: #fff;
            padding: 10px 0;
            text-align: center;
        }

        footer p {
            margin: 0;
        }
    </style>
</head>
<body>
    <header>
        <div class="container">
            <h1>Charity Organization Dashboard</h1>
            <nav>
                <ul>
                    <li><a href="orgProfile.jsp">Profile</a></li>
                    <li><a href="orgDonations.jsp">Donations</a></li>
                    <li><a href="logout.jsp">Logout</a></li>
                </ul>
            </nav>
        </div>
    </header>
    
    <main>
        <div class="container">
            <section class="org-details">
                <h2>Organization Details</h2>
                <div class="card">
                    <p><strong>Organization Name:</strong> ${organization.charityOrgName}</p>
                    <p><strong>Email:</strong> ${organization.email}</p>
                    <p><strong>Role:</strong> ${organization.role == 3 ? 'Charity Organization' : 'Unknown'}</p>
                </div>
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
