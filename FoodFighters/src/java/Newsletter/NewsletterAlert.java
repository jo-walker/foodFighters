/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Newsletter;

import BusinessLogic.ConsumersBusinessLogic;
import DTO.ConsumerDTO;
import DTO.NewsletterDTO;
import DTO.ProductDTO;
import DTO.RetailerDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a newsletter alert class
 * @author Andrea Visani 041104651 visa0004@algonquinlive.com
 */
public class NewsletterAlert {

    private ConsumersBusinessLogic consumerLogic = new ConsumersBusinessLogic();

        // List of observers, Actually USERS?
    private List<Subscriber> subscribers = new ArrayList<>();

    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    public ConsumersBusinessLogic getConsumerLogic() {
        return consumerLogic;
    }

    public void setConsumerLogic(ConsumersBusinessLogic consumerLogic) {
        this.consumerLogic = consumerLogic;
    }

    public List<Subscriber> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<Subscriber> subscribers) {
        this.subscribers = subscribers;
    }

    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void notifyObservers(NewsletterDTO notification) throws SQLException {
        subscribers = consumerLogic.getAllSubscribedConsumers();
        for (Subscriber subscriber : subscribers) {
            subscriber.update(notification);
        }
    }
}
