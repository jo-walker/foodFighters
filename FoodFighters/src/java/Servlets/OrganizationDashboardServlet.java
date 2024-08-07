package Servlets;

import BusinessLogic.OrganizationBusinessLogic;
import DTO.ProductDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/OrganizationDashboardServlet")
public class OrganizationDashboardServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(false);
        Integer charityOrgID = (Integer) session.getAttribute("charityOrgID");

        if (charityOrgID == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Charity Organization ID is missing in session.");
            return;
        }

        OrganizationBusinessLogic organizationLogic = new OrganizationBusinessLogic();
        List<ProductDTO> donations = organizationLogic.getProductsByCharityOrgID(charityOrgID);

        request.setAttribute("donations", donations);
        try {
            request.getRequestDispatcher("/organizationDashboard.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception to the console
            throw new ServletException("Error forwarding to JSP", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(OrganizationDashboardServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(OrganizationDashboardServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet to manage donations for charity organizations";
    }
}