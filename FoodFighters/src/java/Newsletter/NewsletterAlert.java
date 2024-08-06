/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Newsletter;

import DTO.ConsumerDTO;
import DTO.ProductDTO;
import DTO.RetailerDTO;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a newsletter alert class
 * @author Andrea Visani 041104651 visa0004@algonquinlive.com
 */
public class NewsletterAlert {
//    private static List<ConsumerDTO> mailingList;
//
//    /**
//     * sends a notification that the product is marked as Surplus
//     * @param product 
//     */
//    public void sendNotification(ProductDTO product, RetailerDTO retailer) {
//        String notification = product.getName() + " from " + retailer.getName() +" is now on discount";
//        for(ConsumerDTO consumer : mailingList){
//            // ADD LOGIC FOR SENDING THE NOTIFICATION BASED ON THE DIET AND PRODUCT TYPE 
//            consumer.addNotification(notification);
//        }
//    }

        // List of observers
    private List<Subscriber> subscribers = new ArrayList<>();

    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void notifyObservers(ProductDTO product, RetailerDTO retailer) {
        String notification = product.getName() + " from " + retailer.getName() +" is now on discount";
        for (Subscriber subscriber : subscribers) {
            subscriber.update(notification);
        }
    }
}
