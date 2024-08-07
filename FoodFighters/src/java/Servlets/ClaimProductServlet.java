import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import BusinessLogic.OrganizationBusinessLogic;
import DTO.ProductDTO;

@WebServlet("/ClaimProductServlet")
public class ClaimProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Integer charityOrgID = (Integer) session.getAttribute("charityOrgID");
        OrganizationBusinessLogic businessLogic = new OrganizationBusinessLogic();

        // Retrieve selected products from the form
        String[] productIds = request.getParameterValues("productsToClaim");

        String message;

        if (productIds != null && productIds.length > 0) {
            try {
                // Process each selected product for claiming
                for (String productId : productIds) {
                    // Adjust this to call the actual method that processes claiming
                    businessLogic.claimFood();
                }
                message = "Products successfully claimed.";
            } catch (SQLException e) {
                e.printStackTrace();
                message = "An error occurred while claiming products.";
            }
        } else {
            message = "No products selected for claiming.";
        }

        // Forward to the claimproduct.jsp with a message
        request.setAttribute("message", message);
        request.getRequestDispatcher("claimProduct.jsp").forward(request, response);
    }
}
