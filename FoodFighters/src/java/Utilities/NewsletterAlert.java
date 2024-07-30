/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities;

import DTO.ConsumerDTO;
import DTO.ProductDTO;
import java.util.List;

/**
 *
 * @author Andrea Visani 041104651 visa0004@algonquinlive.com
 */
public class NewsletterAlert {
    private static List<ConsumerDTO> mailingList;

    /**
     * sends a notification that the product is marked as Surplus
     * @param product 
     */
    public void sendNotification(ProductDTO product) {
        String notification = product.getName() + " now on discount";
        for(ConsumerDTO consumer : mailingList){
            // ADD LOGIC FOR SENDING THE NOTIFICATION BASED ON THE DIE AND PRODUCT TYPE (SUITABLE FOR "HALAL", "HINDU", ETC..)
            consumer.addNotification(notification);
        }
    }

    /**
     * Adds a new consumer to the mailing list
     * @param consumer 
     */
    public static void addConsumer(ConsumerDTO consumer) {
        mailingList.add(consumer);
    }
    
    /**
     * Removes an existing consumer from the mailing list
     * @param consumer 
     */
    public static void removeConsumer(ConsumerDTO consumer) {
        mailingList.remove(consumer);
    }
}
