package UtilitiesTest;

import DTO.ConsumerDTO;
import DTO.OrganizationDTO;
import DTO.ProductDTO;
import DTO.RetailerDTO;
import Utilities.Exception.ValidationException;
import Utilities.Validator;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.fail;

public class ValidatorTest {

    private final Validator validator = new Validator();

    @Test
    public void testValidateLogin() {
        try {
            validator.validateLogin("username", "password");
        } catch (ValidationException e) {
            fail("ValidationException should not be thrown");
        }

        try {
            validator.validateLogin("", "password");
            fail("ValidationException should be thrown");
        } catch (ValidationException e) {
            // Expected exception
        }

        try {
            validator.validateLogin("username", "");
            fail("ValidationException should be thrown");
        } catch (ValidationException e) {
            // Expected exception
        }
    }

    @Test
    public void testValidateProduct() {
        ProductDTO product = new ProductDTO();
        product.setName("Sample Product");
        product.setQuantity(10);
        product.setExpiryDate(new Date(System.currentTimeMillis() + 10000)); // Future date

        try {
            validator.validateProduct(product);
        } catch (ValidationException e) {
            fail("ValidationException should not be thrown");
        }

        product.setQuantity(-1);
        try {
            validator.validateProduct(product);
            fail("ValidationException should be thrown");
        } catch (ValidationException e) {
            // Expected exception
        }

        product.setQuantity(10);
        product.setExpiryDate(new Date(System.currentTimeMillis() - 10000)); // Past date
        try {
            validator.validateProduct(product);
            fail("ValidationException should be thrown");
        } catch (ValidationException e) {
            // Expected exception
        }

        product.setExpiryDate(new Date(System.currentTimeMillis() + 10000)); // Future date
        product.setName("");
        try {
            validator.validateProduct(product);
            fail("ValidationException should be thrown");
        } catch (ValidationException e) {
            // Expected exception
        }
    }

    @Test
    public void testValidateOrganization() {
        OrganizationDTO organization = new OrganizationDTO();
        organization.setName("Organization Name");
        organization.setEmail("email@example.com");
        organization.setPassword("password");

        try {
            validator.validateOrganization(organization);
        } catch (ValidationException e) {
            fail("ValidationException should not be thrown");
        }

        organization.setName("");
        try {
            validator.validateOrganization(organization);
            fail("ValidationException should be thrown");
        } catch (ValidationException e) {
            // Expected exception
        }

        organization.setName("Organization Name");
        organization.setEmail("invalid-email");
        try {
            validator.validateOrganization(organization);
            fail("ValidationException should be thrown");
        } catch (ValidationException e) {
            // Expected exception
        }

        organization.setEmail("email@example.com");
        organization.setPassword("");
        try {
            validator.validateOrganization(organization);
            fail("ValidationException should be thrown");
        } catch (ValidationException e) {
            // Expected exception
        }
    }

    @Test
    public void testValidateRetailer() {
        RetailerDTO retailer = new RetailerDTO();
        retailer.setName("Retailer Name");
        retailer.setEmail("email@example.com");
        retailer.setPassword("password");

        try {
            validator.validateRetailer(retailer);
        } catch (ValidationException e) {
            fail("ValidationException should not be thrown");
        }

        retailer.setName("");
        try {
            validator.validateRetailer(retailer);
            fail("ValidationException should be thrown");
        } catch (ValidationException e) {
            // Expected exception
        }

        retailer.setName("Retailer Name");
        retailer.setEmail("invalid-email");
        try {
            validator.validateRetailer(retailer);
            fail("ValidationException should be thrown");
        } catch (ValidationException e) {
            // Expected exception
        }

        retailer.setEmail("email@example.com");
        retailer.setPassword("");
        try {
            validator.validateRetailer(retailer);
            fail("ValidationException should be thrown");
        } catch (ValidationException e) {
            // Expected exception
        }
    }

    @Test
    public void testValidateConsumer() {
        ConsumerDTO consumer = new ConsumerDTO();
        consumer.setFirstName("First Name");
        consumer.setLastName("Last Name");
        consumer.setEmail("email@example.com");
        consumer.setPhone("1234567890");
        consumer.setPassword("password");

        try {
            validator.validateConsumer(consumer);
        } catch (ValidationException e) {
            fail("ValidationException should not be thrown");
        }

        consumer.setFirstName("");
        try {
            validator.validateConsumer(consumer);
            fail("ValidationException should be thrown");
        } catch (ValidationException e) {
            // Expected exception
        }

        consumer.setFirstName("First Name");
        consumer.setLastName("");
        try {
            validator.validateConsumer(consumer);
            fail("ValidationException should be thrown");
        } catch (ValidationException e) {
            // Expected exception
        }

        consumer.setLastName("Last Name");
        consumer.setEmail("invalid-email");
        try {
            validator.validateConsumer(consumer);
            fail("ValidationException should be thrown");
        } catch (ValidationException e) {
            // Expected exception
        }

        consumer.setEmail("email@example.com");
        consumer.setPhone("");
        try {
            validator.validateConsumer(consumer);
            fail("ValidationException should be thrown");
        } catch (ValidationException e) {
            // Expected exception
        }

        consumer.setPhone("1234567890");
        consumer.setPassword("");
        try {
            validator.validateConsumer(consumer);
            fail("ValidationException should be thrown");
        } catch (ValidationException e) {
            // Expected exception
        }
    }
}