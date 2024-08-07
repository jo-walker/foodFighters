package Utilities;

import DTO.ConsumerDTO;
import DTO.OrganizationDTO;
import DTO.ProductDTO;
import DTO.RetailerDTO;
import Utilities.Exception.ValidationException;
import java.util.Date;
import java.util.regex.Pattern;

public class Validator {

    private String cleanField(String value) {
        return value == null ? null : value.trim();
    }

    public void validateLogin(String username, String password) throws ValidationException {
        if (username == null || username.isEmpty()) {
            throw new ValidationException("Username cannot be empty");
        }
        if (password == null || password.isEmpty()) {
            throw new ValidationException("Password cannot be empty");
        }
    }

    public void validateProduct(ProductDTO product) throws ValidationException {
        if (product == null) {
            throw new ValidationException("Product cannot be null");
        }
        if (product.getQuantity() < 0) {
            throw new ValidationException("Product quantity cannot be negative");
        }
        if (product.getExpiryDate().before(new Date())) {
            throw new ValidationException("Expiry date cannot be in the past");
        }
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new ValidationException("Product name cannot be empty");
        }
    }

    public void validateProductQuantity(ProductDTO product) throws ValidationException {
        if (product == null) {
            throw new ValidationException("Product cannot be null");
        }
        if (product.getQuantity() < 0) {
            throw new ValidationException("Product quantity cannot be negative");
        }
    }

    public void validateSurplusProduct(ProductDTO product) throws ValidationException {
        if (product == null) {
            throw new ValidationException("Product cannot be null");
        }
        if (product.isSurplus() && product.getQuantity() <= 0) {
            throw new ValidationException("Cannot mark a product with zero or negative quantity as surplus");
        }
    }

    public void validateOrganization(OrganizationDTO organization) throws ValidationException {
        if (organization == null) {
            throw new ValidationException("Organization cannot be null");
        }
        if (organization.getName() == null || organization.getName().trim().isEmpty()) {
            throw new ValidationException("Organization name cannot be empty");
        }
        if (!isValidEmail(organization.getEmail())) {
            throw new ValidationException("Invalid email format");
        }
        if (organization.getPassword() == null || organization.getPassword().isEmpty()) {
            throw new ValidationException("Password cannot be empty");
        }
    }

    public void validateRetailer(RetailerDTO retailer) throws ValidationException {
        if (retailer == null) {
            throw new ValidationException("Retailer cannot be null");
        }
        if (retailer.getName() == null || retailer.getName().trim().isEmpty()) {
            throw new ValidationException("Retailer name cannot be empty");
        }
        if (!isValidEmail(retailer.getEmail())) {
            throw new ValidationException("Invalid email format");
        }
        if (retailer.getPassword() == null || retailer.getPassword().isEmpty()) {
            throw new ValidationException("Password cannot be empty");
        }
    }

    public void validateConsumer(ConsumerDTO consumer) throws ValidationException {
        if (consumer == null) {
            throw new ValidationException("Consumer cannot be null");
        }
        if (consumer.getFirstName() == null || consumer.getFirstName().isEmpty()) {
            throw new ValidationException("First name cannot be empty");
        }
        if (consumer.getLastName() == null || consumer.getLastName().isEmpty()) {
            throw new ValidationException("Last name cannot be empty");
        }
        if (!isValidEmail(consumer.getEmail())) {
            throw new ValidationException("Invalid email format");
        }
        if (consumer.getPhone() == null || consumer.getPhone().isEmpty()) {
            throw new ValidationException("Phone number cannot be empty");
        }
        if (consumer.getPassword() == null || consumer.getPassword().isEmpty()) {
            throw new ValidationException("Password cannot be empty");
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
}