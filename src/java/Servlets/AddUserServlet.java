package Servlets;

import BusinessLogic.ConsumersBusinessLogic;
import BusinessLogic.RetailersBusinessLogic;
import BusinessLogic.OrganizationBusinessLogic;
import DTO.ConsumerDTO;
import DTO.RetailerDTO;
import DTO.OrganizationDTO;
import Utilities.Validator;
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
    private Validator validator;

    @Override
    public void init() throws ServletException {
        try {
            // Initialize Business Logic with appropriate DAO instances
            consumerLogic = new ConsumersBusinessLogic();
            retailerLogic = new RetailersBusinessLogic();
            charityLogic = new OrganizationBusinessLogic();
            validator = new Validator();
        } catch (Exception e) {
            throw new ServletException("Initialization failed in AddUserServlet", e);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            int role = Integer.parseInt(request.getParameter("role"));

            if (role == 1) {   // Consumer
                
            ConsumerDTO consumer = new ConsumerDTO();
            consumer.setUsername(request.getParameter("username"));
            consumer.setPassword(request.getParameter("password"));
            consumer.setEmail(request.getParameter("email"));
            consumer.setRole(Integer.parseInt(request.getParameter("role")));
            consumer.setFirstName(request.getParameter("firstName"));
            consumer.setLastName(request.getParameter("lastName"));
            consumer.setLocation(request.getParameter("location"));
            consumer.setPhone(request.getParameter("phone"));
            consumer.setDietType(Boolean.parseBoolean(request.getParameter("dietType")));

            validator.validateConsumer(consumer);
            consumerLogic.addConsumer(consumer);
            request.setAttribute("message", "Consumer added successfully!");
            request.getRequestDispatcher("ConsumerDashboard.jsp").forward(request, response);

            } else if (role == 2) {  // Retailer
                RetailerDTO retailer = new RetailerDTO();
                retailer.setUsername(username);
                retailer.setPassword(password);
                retailer.setEmail(email);
                retailer.setRole(role);
                retailer.setName(request.getParameter("retailerName"));

                validator.validateRetailer(retailer);
                int retailerId = retailerLogic.addRetailer(retailer);
                request.setAttribute("message", "Sign up successful!");
                request.getSession().setAttribute("retailerID", retailerId);
                request.getRequestDispatcher("retailerDashboard.jsp").forward(request, response);

            } else if (role == 3) {  // Charity Organization
                OrganizationDTO charity = new OrganizationDTO();
                charity.setUsername(username);
                charity.setPassword(password);
                charity.setEmail(email);
                charity.setRole(role);
                charity.setName(request.getParameter("charityName"));

                validator.validateOrganization(charity);
                charityLogic.addOrganization(charity);
                request.setAttribute("message", "Charity organization added successfully!");
                request.getRequestDispatcher("charityDashboard.jsp").forward(request, response);

            } else {
                request.setAttribute("error", "Invalid role specified.");
                request.getRequestDispatcher("errorPage.jsp").forward(request, response);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AddUserServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("error", "SQL error occurred.");
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(AddUserServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("error", "An Mula k bhayo yeta.");
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Add User Servlet";
    }
}
