/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import DTO.NewsletterDTO;
import java.util.List;

/**
 *
 * @author Andrea Visani 041104651 visa0004@algonquinlive.com
 */
public interface NewsletterDAO {
    
   List<NewsletterDTO> getMessagesByUserIDSortedDESC(int userID);
   NewsletterDTO addMessage(String productName, int retailerID);
   void deleteMessage(int messageID);
  
}
