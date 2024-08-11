/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;
import DTO.ConsumerDTO; 
import DTO.NewsletterDTO;
import DTO.ProductDTO;
import Newsletter.Subscriber;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Anugrah Rai 041099878 rai00049@algonquinlive.com
 */
public interface ConsumerDAO {
    void addConsumer(ConsumerDTO consumer) throws SQLException;
    void purchaseItem(ConsumerDTO consumer, ProductDTO product) throws SQLException;
    
    /**
     * Subscribes an user to the newsletter
     * @param consumerID
     * @throws SQLException 
     * @author Andrea Visani 041104651 visa0004@algonquinlive.com
     */
    void subscribeToAlert(int consumerID) throws SQLException;
    /**
     * Unsubscribes an user to the newsletter
     * @param consumerID
     * @throws SQLException 
     * @author Andrea Visani 041104651 visa0004@algonquinlive.com
     */
    void unsubscribeToAlert(int consumerID) throws SQLException;

    /**
     * return all the subscribed consumers
     * @return All the subscribed consumers
     * @throws SQLException 
     * @author Andrea Visani 041104651 visa0004@algonquinlive.com
     */
    public List<Subscriber> getAllSubscribedConsumers() throws SQLException;

    /**
     * Pairs an user with a notification in the database
     * @param id the user id
     * @param notification the notification
     * @author Andrea Visani 041104651 visa0004@algonquinlive.com
     */
    public void receiveNotification(int id, NewsletterDTO notification);

    /**
     * Checks if a user is marked as subscribed in the database
     * @param customerID the customerID
     * @return true if subscribed, false otherwise.
     * @author Andrea Visani 041104651 visa0004@algonquinlive.com
     */
    public boolean isUserSubscribed(int customerID);
}