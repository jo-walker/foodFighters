package Servlets;

import BusinessLogic.ConsumersBusinessLogic;
import BusinessLogic.RetailersBusinessLogic;
import BusinessLogic.OrganizationBusinessLogic;
import DTO.ConsumerDTO;
import DTO.RetailerDTO;
import DTO.OrganizationDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AddUserServlet", urlPatterns = {"/AddUserServlet"})
public class AddUserServlet extends HttpServlet {

    private ConsumersBusinessLogic consumerLogic;
    private RetailersBusinessLogic retailerLogic;
    private OrganizationBusinessLogic charityLogic;

    @Override
    public void init() throws ServletException {
        super.init();
        // Initialize Business Logic with appropriate DAO instances
        consumerLogic = new ConsumersBusinessLogic(); 
        retailerLogic = new RetailersBusinessLogic(); 
        charityLogic = new OrganizationBusinessLogic(); 
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        int role = Integer.parseInt(request.getParameter("role"));

        if (role == 1) {  // Consumer
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String location = request.getParameter("location");
            String phone = request.getParameter("phone");
            String dietType = request.getParameter("dietType");

            ConsumerDTO consumer = new ConsumerDTO();
            consumer.setUsername(username);
            consumer.setPassword(password);
            consumer.setEmail(email);
            consumer.setRole(role);
            consumer.setFirstName(firstName);
            consumer.setLastName(lastName);
            consumer.setLocation(location);
            consumer.setPhone(phone);
            consumer.setDietType(dietType);

            try {
                consumerLogic.addConsumer(consumer);
                request.setAttribute("message", "Consumer added successfully!");
                request.getRequestDispatcher("consumerDashboard.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(AddUserServlet.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("error", "Failed to add consumer.");
                request.getRequestDispatcher("errorPage.jsp").forward(request, response);
            }

        } else if (role == 2) {  // Retailer
            String retailerName = request.getParameter("retailerName");

            RetailerDTO retailer = new RetailerDTO();
            retailer.setUsername(username);
            retailer.setPassword(password);
            retailer.setEmail(email);
            retailer.setRole(role);
            retailer.setName(retailerName);

            retailerLogic.addRetailer(retailer);
            request.setAttribute("message", "Retailer added successfully!");
            request.getRequestDispatcher("retailerDashboard.jsp").forward(request, response);

        } else if (role == 3) {  // Charity Organization
            String charityName = request.getParameter("charityName");

            OrganizationDTO charity = new OrganizationDTO();
            // Set other necessary fields if required
            charityLogic.addOrganization(charity);
            request.setAttribute("message", "Charity organization added successfully!");
            request.getRequestDispatcher("charityDashboard.jsp").forward(request, response);

        } else {
            request.setAttribute("error", "Invalid role specified.");
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AddUserServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("error", "SQL error occurred.");
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AddUserServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("error", "SQL error occurred.");
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Add User Servlet";
    }
}
