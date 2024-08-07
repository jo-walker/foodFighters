package BusinessLogic;

import DAO.ConsumerDAO;
import DAO.ConsumerDAOImpl;
import DAO.ProductDAO;
import DAO.ProductDAOImpl;
import DTO.ConsumerDTO;
import DTO.ProductDTO;
import Utilities.Validator;
import java.sql.SQLException;
import java.util.List;

///**
// * Business logic for handling consumer-related operations.
// * Author: Anugrah 
// * 
// 

public class ConsumersBusinessLogic {
    private ConsumerDAO consumerDAOImpl;
    private Validator validator;
    private ProductDAO productDAO;

    // Constructor to initialize DAOs and validator
    public ConsumersBusinessLogic() {
        this.consumerDAOImpl = new ConsumerDAOImpl();
        this.validator = new Validator();
        this.productDAO = new ProductDAOImpl();
    }

    // Method to handle product purchase logic
    public void purchaseProduct(ConsumerDTO consumer, ProductDTO product) throws SQLException {
            validator.validateProduct(product); 
            // Assuming ConsumerDAO has a method to add purchase details
            consumerDAOImpl.purchaseItem(consumer, product);
    }

    // Method to add a new consumer
    public void addConsumer(ConsumerDTO consumer) throws SQLException {
            validator.validateConsumer(consumer); 
            consumerDAOImpl.addConsumer(consumer);
    }

    // Method to subscribe consumer to alerts
    public void subscribeAlert(ConsumerDTO consumer, String alertType) throws SQLException {
            validator.validateConsumer(consumer); 
            consumerDAOImpl.subscribeToAlert(consumer, alertType);
    }

    // Method to display all products
    public List<ProductDTO> displayAllProducts() throws SQLException {
        return productDAO.getAllProducts();
    }
}
