/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import DTO.NewsletterDTO;
import java.util.List;

/**
 * interface for NewsletterDAO
 * @author Andrea Visani 041104651 visa0004@algonquinlive.com
 */
public interface NewsletterDAO {
    
    /**
     * returns all the messages for a specific user based on its ID
     * @param userID the ID
     * @return all the messages for a specific user
     */
    List<NewsletterDTO> getMessagesByUserIDSortedDESC(int userID);
    
    /**
     * Adds a message to the database specifying the products on discount and the retailer name based on its ID
     * @param productName the products name
     * @param retailerID the retailerID
     * @return the created NewsletterDTO
     */
    NewsletterDTO addMessage(String productName, int retailerID);
    
    /**
     * Deletes a message - userID pair from the CustomerNotification tables
     * @param messageID the messageID
     */
    void deleteMessage(int messageID);
  
}
