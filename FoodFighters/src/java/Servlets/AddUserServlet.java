package Servlets;

import BusinessLogic.ConsumersBusinessLogic;
import BusinessLogic.RetailersBusinessLogic;
import BusinessLogic.OrganizationBusinessLogic;
import DTO.ConsumerDTO;
import DTO.RetailerDTO;
import DTO.OrganizationDTO;
import Utilities.Exception.ValidationException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Holds the logic for adding a customer
 * @author Andrea Visani 041104651 visa0004@algonquinlive.com
 */
@WebServlet(name = "AddUserServlet", urlPatterns = {"/AddUserServlet"})
public class AddUserServlet extends HttpServlet {

    private ConsumersBusinessLogic consumerLogic;
    private RetailersBusinessLogic retailerLogic;
    private OrganizationBusinessLogic charityLogic;

    @Override
    public void init() throws ServletException {
        try {
            // Initialize Business Logic with appropriate DAO instances
            consumerLogic = new ConsumersBusinessLogic();
            retailerLogic = new RetailersBusinessLogic();
            charityLogic = new OrganizationBusinessLogic();
        } catch (Exception e) {
            throw new ServletException("Initialization failed in AddUserServlet", e);
        }
    }

    /**
     * Processes the request and based on role type redirects to the proper dashboard
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
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

            consumerLogic.addConsumer(consumer);
            request.setAttribute("message", "Consumer added successfully!");
        
            boolean isVeg = consumer.getVeg();
            int isVegg = isVeg ? 1 : 0;
            request.getSession().setAttribute("isVeg", isVegg); // making sure same datatype is entered into the method
            request.getSession().setAttribute("firstName", consumer.getFirstName());
            request.getRequestDispatcher("ConsumerDashboard.jsp").forward(request, response);

            } 
            
            else if (role == 2) {  
                //RETAILER
                String retailerName = request.getParameter("retailerName");

                RetailerDTO retailer = new RetailerDTO();
                retailer.setUsername(username);
                retailer.setPassword(password);
                retailer.setEmail(email);
                retailer.setRole(role);
                retailer.setName(retailerName);

                int retailerId = retailerLogic.addRetailer(retailer);
                request.setAttribute("message", "Sign up successful!");
                request.getSession().setAttribute("retailerID", retailerId);
                request.getRequestDispatcher("retailerDashboard.jsp").forward(request, response);

            } 
            
            else if (role == 3) {  // Charity Organization
                String charityName = request.getParameter("charityName");

                OrganizationDTO charity = new OrganizationDTO();
                charity.setUsername(username);
                charity.setPassword(password);
                charity.setEmail(email);
                charity.setRole(role);
                charity.setName(charityName);
                
                
                int charityOrgID = charityLogic.addOrganization(charity);
                request.setAttribute("message", "Charity organization added successfully!");
                request.getSession().setAttribute("charityOrgID", charityOrgID);
                request.getRequestDispatcher("organizationDashboard.jsp").forward(request, response);
                

            } else {
                request.setAttribute("error", "Invalid role specified.");
                request.getRequestDispatcher("errorPage.jsp").forward(request, response);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AddUserServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("error", "SQL error occurred: " + ex.getMessage());
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        } catch (ValidationException ex) {
            Logger.getLogger(AddUserServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("error", "Validation error occurred: " + ex.getMessage());
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(AddUserServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("error", "An error occurred: " + ex.getMessage());
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
