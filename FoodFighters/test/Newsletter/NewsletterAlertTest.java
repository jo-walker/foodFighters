package Newsletter;
import DTO.NewsletterDTO;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

/**
 * Unit tests for the NewsletterAlert class.
 * 
 * @author Andrea Visani 041104651 visa0004@algonquinlive.com
 */
public class NewsletterAlertTest {

    private NewsletterAlert instance;

    @Before
    public void setUp() {
        instance = new NewsletterAlert();
    }

    /**
     * Test of subscribe method, of class NewsletterAlert.
     */
    @Test
    public void testSubscribe() {
        System.out.println("subscribe");
        TestSubscriber subscriber1 = new TestSubscriber();
        TestSubscriber subscriber2 = new TestSubscriber();

        instance.subscribe(subscriber1);
        instance.subscribe(subscriber2);

        List<Subscriber> subscribers = instance.getSubscribers();  // Assuming this method exists
        assertEquals(2, subscribers.size());
        assertTrue(subscribers.contains(subscriber1));
        assertTrue(subscribers.contains(subscriber2));
    }

    /**
     * Test of unsubscribe method, of class NewsletterAlert.
     */
    @Test
    public void testUnsubscribe() {
        System.out.println("unsubscribe");
        TestSubscriber subscriber1 = new TestSubscriber();
        TestSubscriber subscriber2 = new TestSubscriber();

        instance.subscribe(subscriber1);
        instance.subscribe(subscriber2);
        instance.unsubscribe(subscriber1);

        List<Subscriber> subscribers = instance.getSubscribers();  // Assuming this method exists
        assertEquals(1, subscribers.size());
        assertFalse(subscribers.contains(subscriber1));
        assertTrue(subscribers.contains(subscriber2));
    }

    /**
     * A simple implementation of the Subscriber interface for testing purposes.
     */
    private class TestSubscriber implements Subscriber {
        private NewsletterDTO receivedNotification;

        @Override
        public void update(NewsletterDTO notification) {
            this.receivedNotification = notification;
        }

        public NewsletterDTO getReceivedNotification() {
            return receivedNotification;
        }
    }
}
