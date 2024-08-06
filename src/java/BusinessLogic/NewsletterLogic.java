package BusinessLogic;

import DAO.NewsletterDAOImpl;
import DTO.NewsletterDTO;

public class NewsletterLogic {
    private final NewsletterDAOImpl newsletterDAO = new NewsletterDAOImpl();

    public NewsletterDTO addMessage(String productName, int retailerID) {
        return newsletterDAO.addMessage(productName, retailerID);
    }

    public void notifyObservers(NewsletterDTO notification) {
        // Implement the logic to notify customers
        // For example, sending an email or updating a notifications table
    }
}