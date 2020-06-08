/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Isaia
 */
public class FMServiceTest {
    
    FMService serv;
    
    public FMServiceTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        serv = ctx.getBean("serv", FMService.class);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getOrdersForDate method, of class FMService.
     */
    @Test
    public void testGetOrdersForDate() throws Exception {
    }

    /**
     * Test of getAllStates method, of class FMService.
     */
    @Test
    public void testGetAllStates() throws Exception {
    }

    /**
     * Test of getAllProducts method, of class FMService.
     */
    @Test
    public void testGetAllProducts() {
    }

    /**
     * Test of confirmAddOrder method, of class FMService.
     */
    @Test
    public void testConfirmAddOrder() throws Exception {
    }

    /**
     * Test of confirmRemoval method, of class FMService.
     */
    @Test
    public void testConfirmRemoval() throws Exception {
    }

    /**
     * Test of getOrder method, of class FMService.
     */
    @Test
    public void testGetOrder() throws Exception {
    }

    /**
     * Test of editOrder method, of class FMService.
     */
    @Test
    public void testEditOrder() throws Exception {
    }

    /**
     * Test of calculateOrderDetails method, of class FMService.
     */
    @Test
    public void testCalculateOrderDetails() throws Exception {
    }
    
}
