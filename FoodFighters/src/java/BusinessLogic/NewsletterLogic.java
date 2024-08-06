/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BusinessLogic;

import DAO.NewsletterDAO;
import DAO.NewsletterDAOImpl;

/**
 *
 * @author Andrea Visani 041104651 visa0004@algonquinlive.com
 */
public class NewsletterLogic {
    
    private NewsletterDAO newsDAO= new NewsletterDAOImpl();
    
    public void addMessage(String productName, int retailerID){
        newsDAO.addMessage(productName, retailerID);
    }
    
}
