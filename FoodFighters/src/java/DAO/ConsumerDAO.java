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
    void subscribeToAlert(int consumerID) throws SQLException;

    public List<Subscriber> getAllSubscribedConsumers() throws SQLException;

    public void receiveNotification(int id, NewsletterDTO notification);
}