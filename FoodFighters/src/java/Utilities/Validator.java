/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities;

import DTO.ConsumerDTO;
import DTO.OrganizationDTO;
import DTO.ProductDTO;
import DTO.RetailerDTO;
import Utilities.Exception.ValidationException;
import java.util.Date;

/**
 *
 * @author Andrea Visani 041104651 visa0004@algonquinlive.com
 */
public class Validator {
    private void cleanField() {
        // Implementation
    }

    public void validateLogin() {
        // Implementation
    }

    public void validateProduct(ProductDTO product) throws ValidationException {
        // Implement validation logic for product
        if (product.getQuantity() < 0) {
            throw new ValidationException("Product quantity cannot be negative");
        }
        if (product.getExpiryDate().before(new Date())) {
            throw new ValidationException("Expiry date cannot be in the past");
        }
        // Add more validations as needed
    }
    public void validateProductQuantity(ProductDTO product) throws ValidationException {
        // Implement quantity-specific validation
        if (product.getQuantity() < 0) {
            throw new ValidationException("Product quantity cannot be negative");
        }
    }
    
    public void validateSurplusProduct(ProductDTO product) throws ValidationException {
        // Implement surplus-specific validation
        if (product.isSurplus() && product.getQuantity() <= 0) {
            throw new ValidationException("Cannot mark a product with zero or negative quantity as surplus");
        }
    }
    public void validateOrganization(OrganizationDTO organization) {
        // Implementation
    }

    public void validateRetailer(RetailerDTO retailer) {
        // Implementation
    }

    public void validateConsumer(ConsumerDTO consumer) {
        // Implementation
    }
}
