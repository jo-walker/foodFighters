/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BusinessLogic;

import DAO.ConsumerDAO;
import DAO.ConsumerDAOImpl;
import DAO.NewsletterDAO;
import DAO.NewsletterDAOImpl;
import DTO.NewsletterDTO;
import Newsletter.NewsletterAlert;
import java.sql.SQLException;
import java.util.List;

/**
 * Logic for the newsletter
 * @author Andrea Visani 041104651 visa0004@algonquinlive.com
 */
public class NewsletterLogic {
    
    private NewsletterDAO newsDAO= new NewsletterDAOImpl();
    private NewsletterAlert newsletter = new NewsletterAlert();
    private ConsumerDAO consumerDAO = new ConsumerDAOImpl();
    
    /**
     * Adds a notification message to the message pool
     * @param productName the product name
     * @param retailerID the retailer id
     * @return The message in form of NewsletterDTO
     */
    public NewsletterDTO addMessage(String productName, int retailerID){
        return newsDAO.addMessage(productName, retailerID);
    }
    
    /**
     * Sends a notification to the observer
     * @param notification the message
     * @throws SQLException 
     */
    public void notifyObservers(NewsletterDTO notification) throws SQLException{
        newsletter.notifyObservers(notification);
    }
    
    /**
     * Retrieves all the message for a specific user, most recent on top
     * @param userID
     * @return 
     */
    public List<NewsletterDTO> getMessagesByUserIDSortedDESC(int userID){
        return newsDAO.getMessagesByUserIDSortedDESC(userID);
    }
    
    /**
     * Checks if an user is sbuscribed
     * @param customerID The customerID of the user
     * @return true if subscribed, false otherwise
     */
    public boolean isUserSubscribed(int customerID){
        return consumerDAO.isUserSubscribed(customerID);
    }
    
}
