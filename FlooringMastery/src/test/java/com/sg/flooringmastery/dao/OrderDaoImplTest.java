/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.FMOrder;
import com.sg.flooringmastery.exceptions.InvalidOrderDateException;
import com.sg.flooringmastery.exceptions.OrderDaoException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Isaia
 */
public class OrderDaoImplTest {
    
    ODao toTest = new OrderDaoImpl(Paths.get("TestData", "testOrders.txt").toString());
    
    public OrderDaoImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws IOException {
        Path testPath = Paths.get("TestData", "testOrders.txt");
        Path seedPath = Paths.get("TestData", "seedOrders.txt");
        
        File testFile = testPath.toFile();
        
        testFile.delete();
        Files.copy(seedPath, testPath);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getOrdersForDate method, of class OrderDaoImpl.
     */
    @Test
    public void testGetOrdersForDateGoldenPath() throws OrderDaoException, InvalidOrderDateException {
        List<FMOrder> allOrders = toTest.getOrdersForDate(LocalDate.now());
        
        
    }

    /**
     * Test of getOrder method, of class OrderDaoImpl.
     */
    @Test
    public void testGetOrder() {
    }

    /**
     * Test of addOrder method, of class OrderDaoImpl.
     */
    @Test
    public void testAddOrder() throws Exception {
    }

    /**
     * Test of editOrder method, of class OrderDaoImpl.
     */
    @Test
    public void testEditOrder() throws Exception {
    }

    /**
     * Test of removeOrder method, of class OrderDaoImpl.
     */
    @Test
    public void testRemoveOrder() throws Exception {
    }
    
}
