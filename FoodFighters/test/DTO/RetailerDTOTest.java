/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package DTO;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Andrea Visani 041104651 visa0004@algonquinlive.com
 */
public class RetailerDTOTest {
    
    /**
     * Test of getName and setName methods, of class RetailerDTO.
     */
    @Test
    public void testGetAndSetName() {
        System.out.println("getName and setName");
        RetailerDTO instance = new RetailerDTO();
        String expResult = "Andrea";
        instance.setName(expResult);
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId and setId methods, of class RetailerDTO.
     */
    @Test
    public void testGetAndSetId() {
        System.out.println("getId and setId");
        RetailerDTO instance = new RetailerDTO();
        int expResult = 123;
        instance.setId(expResult);
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUsername and setUsername methods, of class RetailerDTO.
     */
    @Test
    public void testGetAndSetUsername() {
        System.out.println("getUsername and setUsername");
        RetailerDTO instance = new RetailerDTO();
        String expResult = "testUser";
        instance.setUsername(expResult);
        String result = instance.getUsername();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPassword and setPassword methods, of class RetailerDTO.
     */
    @Test
    public void testGetAndSetPassword() {
        System.out.println("getPassword and setPassword");
        RetailerDTO instance = new RetailerDTO();
        String expResult = "password123";
        instance.setPassword(expResult);
        String result = instance.getPassword();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEmail and setEmail methods, of class RetailerDTO.
     */
    @Test
    public void testGetAndSetEmail() {
        System.out.println("getEmail and setEmail");
        RetailerDTO instance = new RetailerDTO();
        String expResult = "test@example.com";
        instance.setEmail(expResult);
        String result = instance.getEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRole and setRole methods, of class RetailerDTO.
     */
    @Test
    public void testGetAndSetRole() {
        System.out.println("getRole and setRole");
        RetailerDTO instance = new RetailerDTO();
        int expResult = 2;
        instance.setRole(expResult);
        int result = instance.getRole();
        assertEquals(expResult, result);
    }
}
