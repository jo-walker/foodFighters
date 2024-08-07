package Servlets;

import BusinessLogic.OrganizationBusinessLogic;
import DTO.OrganizationDTO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AddOrganizationServlet", urlPatterns = {"/AddOrganizationServlet"})
public class AddOrganizationServlet extends HttpServlet {

    private OrganizationBusinessLogic organizationLogic = new OrganizationBusinessLogic();

    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    
    // Retrieve parameters from the request
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String email = request.getParameter("email");
    String organizationName = request.getParameter("organizationName");
    
    // Define role for organization (Assuming role 3 is for organizations)
    int role = 3;

    // Create OrganizationDTO object
    OrganizationDTO organization = new OrganizationDTO();
    organization.setUsername(username);
    organization.setPassword(password);
    organization.setEmail(email);
    organization.setRole(role);
    organization.setName(organizationName);

    try {
        // Add organization and retrieve organizationID
        int charityOrgID = organizationLogic.addOrganization(organization);

        // Set organizationID in session
        HttpSession session = request.getSession();
        session.setAttribute("charityOrgID", charityOrgID);

        // Set success message and forward to JSP
        request.setAttribute("message", "Sign up successful!");
        request.getRequestDispatcher("organizationDashboard.jsp").forward(request, response);
    } catch (SQLException ex) {
        ex.printStackTrace();
        // Handle exception (e.g., log error, show error message)
        request.setAttribute("error", "An error occurred while processing your request. Please try again.");
        request.getRequestDispatcher("error.jsp").forward(request, response);
    }
}



    @Override
    public String getServletInfo() {
        return "Servlet to add a new organization";
    }
}
