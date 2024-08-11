/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BusinessLogic;

import DAO.NewsletterDAO;
import DAO.NewsletterDAOImpl;
import DTO.NewsletterDTO;
import Newsletter.NewsletterAlert;
import java.sql.SQLException;

/**
 *
 * @author Andrea Visani 041104651 visa0004@algonquinlive.com
 */
public class NewsletterLogic {
    
    private NewsletterDAO newsDAO= new NewsletterDAOImpl();
    private NewsletterAlert newsletter = new NewsletterAlert();
    
    public NewsletterDTO addMessage(String productName, int retailerID){
        return newsDAO.addMessage(productName, retailerID);
    }
    
    public void notifyObservers(NewsletterDTO notification) throws SQLException{
        newsletter.notifyObservers(notification);
    }
    
}
