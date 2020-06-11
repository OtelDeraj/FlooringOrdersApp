/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.FMOrder;
import com.sg.flooringmastery.dto.FMProduct;
import com.sg.flooringmastery.dto.FMTax;
import com.sg.flooringmastery.exceptions.InvalidInputException;
import com.sg.flooringmastery.exceptions.InvalidOrderDateException;
import com.sg.flooringmastery.exceptions.OrderDaoException;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    ODao od = new OrderDaoImpl("TestData");
    LocalDate date = LocalDate.of(2020, 9, 8);

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
        Path testPath = Paths.get("TestData", "Orders_09082020.txt");
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
    public void testGetOrdersForDateGoldenPath() {
        try {
            List<FMOrder> allOrders = od.getOrdersForDate(LocalDate.of(2020, 9, 8));

            assertEquals(2, allOrders.size());
        } catch (OrderDaoException ex) {
            fail("Should not hit OrderDaoException in golden path.");
        } catch (InvalidOrderDateException ex) {
            fail("Should not hit InvalidOrderDate in golden path");
        }

    }

    public void testGetOrdersForDateNoFile() {
        try {
            od.getOrdersForDate(LocalDate.of(2020, 9, 7));

            fail("Should have thrown InvalidOrderDateException.");
        } catch (OrderDaoException ex) {
            fail("Should not hit OrderDaoException in no file check.");
        } catch (InvalidOrderDateException ex) {

        }
    }

    public void testGetOrdersForDateNullDate() {
        try {
            od.getOrdersForDate(null);

            fail("Should have thrown OrderDaoException.");
        } catch (OrderDaoException ex) {

        } catch (InvalidOrderDateException ex) {
            fail("Should not hit InvalidOrderDateException in Null Date check");
        }
    }

    /**
     * Test of getOrder method, of class OrderDaoImpl.
     */
    @Test
    public void testGetOrderGoldenPath() {
        try {
            FMOrder testOrder = od.getOrder(LocalDate.of(2020, 9, 8), 1);

            assertEquals("Ada Lovelace", testOrder.getCustomerName());

        } catch (OrderDaoException ex) {
            fail("Hit OrderDaoExcepion in golden path.");
        } catch (InvalidOrderDateException ex) {
            fail("Hit InvalidOrderDateException in golden path.");
        } catch (InvalidInputException ex) {
            fail("Hit InvalidInputException in golden path.");
        }
    }

    @Test
    public void testGetOrderNoFile() {
        try {
            od.getOrder(LocalDate.of(2020, 9, 7), 1);

            fail("Should have thrown InvalidOrderExeption.");
        } catch (OrderDaoException ex) {
            fail("Should not hit OrderDaoException in no file check.");
        } catch (InvalidOrderDateException ex) {

        } catch (InvalidInputException ex) {
            fail("Should not hit InvalidInputException in no file check.");
        }
    }

    @Test
    public void testGetOrderNullDate() {
        try {
            od.getOrder(null, 1);

            fail("Should have thrown OrderDaoException.");
        } catch (OrderDaoException ex) {

        } catch (InvalidOrderDateException ex) {
            fail("Should not hit InvalidOrderDateException in Null Date check");
        } catch (InvalidInputException ex) {
            fail("Should not hit InvalidInputException in no file check.");
        }
    }

    @Test
    public void testGetOrderBadOrderNum() {
        try {
            od.getOrder(date, -1);

            fail("Should have thrown InvalidInputException.");
        } catch (OrderDaoException ex) {
            fail("Should not hit OrderDaoException in Null Date check");
        } catch (InvalidOrderDateException ex) {
            fail("Should not hit InvalidInputException in no file check.");
        } catch (InvalidInputException ex) {

        }
    }

    /**
     * Test of addOrder method, of class OrderDaoImpl.
     */
    @Test
    public void testAddOrderGoldenPath() {
        try {
            FMOrder rawOrder = new FMOrder();
            rawOrder.setDate(date);
            rawOrder.setCustomerName("Martha Stewart");
            rawOrder.setArea(new BigDecimal("100.00"));
            FMProduct product = new FMProduct("Marble", new BigDecimal("8.50"), new BigDecimal("9.25"));
            FMTax tax = new FMTax("MN", "Minnesota", new BigDecimal("7.50"));
            FMOrder toAdd = new FMOrder(rawOrder, product, tax);

            od.addOrder(toAdd);
            FMOrder toCheck = od.getOrder(date, 3);
            assertEquals("Martha Stewart", toCheck.getCustomerName());
        } catch (OrderDaoException ex) {
            fail("should not throw OrderDaoException in golden path");
        } catch (InvalidOrderDateException ex) {
            fail("should not throw InvalidOrderDateException in goldent path");
        } catch (InvalidInputException ex) {
            fail("should not throw InvalidInputException in golden path");
        }

    }

    @Test
    public void testAddOrderNullDate() {
        try {
            FMOrder rawOrder = new FMOrder();
            rawOrder.setDate(null);
            rawOrder.setCustomerName("Martha Stewart");
            rawOrder.setArea(new BigDecimal("100.00"));
            FMProduct product = new FMProduct("Marble", new BigDecimal("8.50"), new BigDecimal("9.25"));
            FMTax tax = new FMTax("MN", "Minnesota", new BigDecimal("7.50"));
            FMOrder toAdd = new FMOrder(rawOrder, product, tax);

            od.addOrder(toAdd);
            fail("Should have thrown OrderDaoException. Hmmmmmmm...");
        } catch (OrderDaoException ex) {

        } catch (InvalidOrderDateException ex) {
            fail("Should not throw InvalidOrderDateException in NullDate check");
        }
    }

    /**
     * Test of editOrder method, of class OrderDaoImpl.
     */
    @Test
    public void testEditOrderGoldenPath() {
        try {
            FMOrder toEdit = od.getOrder(date, 2);
            BigDecimal chonk = toEdit.getArea().add(new BigDecimal("10"));
            toEdit.setArea(chonk);
            od.editOrder(toEdit);
            FMOrder toCheck = od.getOrder(date, 2);
            assertEquals(chonk, toCheck.getArea());
        } catch (OrderDaoException ex) {
            fail("Should not hit OrderDaoException in golden path");
        } catch (InvalidOrderDateException ex) {
            fail("Should not hit InvalidOrderDateException in golden path");
        } catch (InvalidInputException ex) {
            fail("Should not hit InvalidInputException in golden path");
        }

    }

    @Test
    public void testEditOrderNoFile() {
        try {
            FMOrder toEdit = od.getOrder(date, 2);
            BigDecimal chonk = toEdit.getArea().add(new BigDecimal("10"));
            toEdit.setArea(chonk);
            toEdit.setDate(LocalDate.of(2020, 2, 7));
            od.editOrder(toEdit);
            fail("Should have hit InvalidOrderDateException");
        } catch (OrderDaoException ex) {
            fail("Should not hit OrderDaoException");
        } catch (InvalidOrderDateException ex) {

        } catch (InvalidInputException ex) {
            fail("Should not hit InvalidInputException");
        }
    }

    @Test
    public void testEditOrderNullDate() {
        try {
            FMOrder toEdit = od.getOrder(date, 2);
            BigDecimal chonk = toEdit.getArea().add(new BigDecimal("10"));
            toEdit.setArea(chonk);
            toEdit.setDate(null);
            od.editOrder(toEdit);
            fail("Should have it OrderDaoException");
        } catch (OrderDaoException ex) {
            
        } catch (InvalidOrderDateException ex) {
            fail("Should not hit InvalidOrderDateException");
        } catch (InvalidInputException ex) {
            fail("Should not hit InvalidInputException");
        }
    }

    @Test
    public void testEditOrderBadOrderNum() {
        try {
            FMOrder toEdit = od.getOrder(date, 2);
            BigDecimal chonk = toEdit.getArea().add(new BigDecimal("10"));
            toEdit.setArea(chonk);
            toEdit.setOrderNum(-1);
            od.editOrder(toEdit);
            fail("should have hit InvalidInputException");
        } catch (OrderDaoException ex) {
            fail("Should not hit OrderDaoException");
        } catch (InvalidOrderDateException ex) {
            fail("Should not hit InvalidOrderDateException");
        } catch (InvalidInputException ex) {
            
        }
    }

    /**
     * Test of removeOrder method, of class OrderDaoImpl.
     */
    @Test
    public void testRemoveOrderGoldenPath() {
        try {
            FMOrder toRemove = od.getOrder(date, 2);
            od.removeOrder(toRemove);
            List<FMOrder> allOrders = od.getOrdersForDate(date);
            for (FMOrder o : allOrders) {
                if (o.getOrderNum() == 2) {
                    fail("Order was not removed. Sadness.");
                }
            }
        } catch (OrderDaoException ex) {
            fail("Should not hit OrderDaoException in golden path");
        } catch (InvalidOrderDateException ex) {
            fail("Should not hit InvalidOrderDateException in golden path");
        } catch (InvalidInputException ex) {
            fail("Should not hit InvalidInputException in golden path");
        }

    }
    
    @Test
    public void testRemoveOrderNoFile(){
        try {
            FMOrder toRemove = od.getOrder(date, 2);
            toRemove.setDate(LocalDate.of(2020, 2, 3));
            od.removeOrder(toRemove);
            fail("Should have hit InvalidOrderDateException");
        } catch (OrderDaoException ex) {
            fail("Should not hit OrderDaoException in golden path");
        } catch (InvalidOrderDateException ex) {

        } catch (InvalidInputException ex) {
            fail("Should not hit InvalidInputException in golden path");
        }
    }
    
     @Test
    public void testRemoveOrderNullDate(){
        try {
            FMOrder toRemove = od.getOrder(date, 2);
            toRemove.setDate(null);
            od.removeOrder(toRemove);
            fail("Should have hit OrderDaoException");
        } catch (OrderDaoException ex) {

        } catch (InvalidOrderDateException ex) {
            fail("Should not hit InvalidOrderDateException in golden path");
        } catch (InvalidInputException ex) {
            fail("Should not hit InvalidInputException in golden path");
        }
    }
    
     @Test
    public void testRemoveOrderBadOrderNum(){
        try {
            FMOrder toRemove = od.getOrder(date, 2);
            toRemove.setOrderNum(-1);
            od.removeOrder(toRemove);
            fail("Should have hit InvalidInputException");
        } catch (OrderDaoException ex) {
            fail("Should not hit OrderDaoException in golden path");
        } catch (InvalidOrderDateException ex) {
            fail("Should not hit InvalidOrderDateException in golden path");
        } catch (InvalidInputException ex) {
            
        }
    }

}
