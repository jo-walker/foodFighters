/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import BusinessLogic.ConsumersBusinessLogic;
import BusinessLogic.RetailersBusinessLogic;
import BusinessLogic.OrganizationBusinessLogic;
import DTO.ConsumerDTO;
import DTO.RetailerDTO;
import DTO.OrganizationDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AddUserServlet", urlPatterns = {"/AddUserServlet"})
public class AddUserServlet extends HttpServlet {

    private RetailersBusinessLogic retailerLogic = new RetailersBusinessLogic();
    private ConsumersBusinessLogic consumerLogic = new ConsumersBusinessLogic();
    private OrganizationBusinessLogic charityLogic = new OrganizationBusinessLogic();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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

            consumerLogic.addConsumer(consumer);

        } else if (role == 2) {  // Retailer
            String retailerName = request.getParameter("retailerName");

            RetailerDTO retailer = new RetailerDTO();
            retailer.setUsername(username);
            retailer.setPassword(password);
            retailer.setEmail(email);
            retailer.setRole(role);
            retailer.setName(retailerName);

            retailerLogic.addRetailer(retailer);

        } else if (role == 3) {  // Charity Organization
            String charityName = request.getParameter("charityName");

            OrganizationDTO charity = new OrganizationDTO();
            //charity.setUsername(username);
            //charity.setPassword(password);
            //charity.setEmail(email);
            //charity.setRole(role);
            //charity.setName(charityName);

            charityLogic.addOrganization(charity);
        }

        request.setAttribute("message", "Sign up successful!");
        request.getRequestDispatcher("confirmation.jsp").forward(request, response);
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
