/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;
import DTO.ConsumerDTO; 
import DTO.ProductDTO;
import java.sql.SQLException;

/**
 *
 * @author Anugrah Rai 041099878 rai00049@algonquinlive.com
 */
public interface ConsumerDAO {
    void addConsumer(ConsumerDTO consumer) throws SQLException;
    void purchaseItem(ConsumerDTO consumer, ProductDTO product) throws SQLException;
    void subscribeToAlert(ConsumerDTO consumer, String alertType) throws SQLException;
}