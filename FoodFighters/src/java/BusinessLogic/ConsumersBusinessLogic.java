/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BusinessLogic;

import DAO.ConsumerDAO;
import DAO.ProductDAO;
import DTO.ConsumerDTO;
import DTO.ProductDTO;
import Utilities.Validator;
import java.util.List;

/**
 *
 * @author Andrea Visani 041104651 visa0004@algonquinlive.com
 */
public class ConsumersBusinessLogic {
    private ConsumerDAO consumer;
    private Validator validator;
    private ProductDAO product;

    public void purchaseProduct() {
        
    }

    public void addConsumer(ConsumerDTO consumer) {
        // Implementation
    }
    
    public void subscribeAlert(){
        
    }
    
    public List<ProductDTO> displayAllProducts() {
        
        return product.getAllProducts();
        
    }
}
