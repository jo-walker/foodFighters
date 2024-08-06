/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import DAO.OrganizationDAO;
import DAO.OrganizationDAOImpl;
import DTO.OrganizationDTO;

@WebServlet("/orgDashboard")
public class OrganizationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Example of fetching organization ID from request parameter
        int orgId = Integer.parseInt(request.getParameter("orgId"));

        // DAO instance to get organization details
        OrganizationDAO orgDAO = new OrganizationDAOImpl();
        OrganizationDTO organization = orgDAO.getOrganizationById(orgId);

        // Set the organization DTO as a request attribute
        request.setAttribute("organization", organization);

        // Forward to JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("orgDashboard.jsp");
        dispatcher.forward(request, response);
    }
}
