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
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Isaia
 */
public class OrderDaoImplTest {

    ODao od = new OrderDaoImpl("TestData");
    LocalDate date = LocalDate.of(2020, 9, 8);

    public OrderDaoImplTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() throws IOException {
        Path testPath = Paths.get("TestData", "Orders_09082020.txt");
        Path seedPath = Paths.get("TestData", "seedOrders.txt");

        File testFile = testPath.toFile();

        testFile.delete();
        Files.copy(seedPath, testPath);
    }

    @AfterEach
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

            assertEquals(1, testOrder.getOrderNum());
            assertEquals("Ada Lovelace", testOrder.getCustomerName());
            assertEquals("CA", testOrder.getTaxRate().getStateAbv());
            assertEquals(new BigDecimal("25.00"), testOrder.getTaxRate().getStateTaxRate());
            assertEquals("Tile", testOrder.getProduct().getMaterial());
            assertEquals(new BigDecimal("249.00"), testOrder.getArea());
            assertEquals(new BigDecimal("3.50"), testOrder.getProduct().getCostPerSqFt());
            assertEquals(new BigDecimal("4.15"), testOrder.getProduct().getLaborCostPerSqFt());
            assertEquals(new BigDecimal("871.50"), testOrder.getMaterialCost());
            assertEquals(new BigDecimal("1033.35"), testOrder.getLaborCost());
            assertEquals(new BigDecimal("476.21"), testOrder.getSalesTax());
            assertEquals(new BigDecimal("2381.06"), testOrder.getTotalCost());

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
            od.getOrder(date, 99);

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
            assertEquals(3, toCheck.getOrderNum());
            assertEquals(toAdd.getCustomerName(), toCheck.getCustomerName());
            assertEquals(toAdd.getTaxRate().getStateAbv(), toCheck.getTaxRate().getStateAbv());
            assertEquals(toAdd.getTaxRate().getStateTaxRate(), toCheck.getTaxRate().getStateTaxRate());
            assertEquals(toAdd.getProduct().getMaterial(), toCheck.getProduct().getMaterial());
            assertEquals(toAdd.getProduct().getCostPerSqFt(), toCheck.getProduct().getCostPerSqFt());
            assertEquals(toAdd.getProduct().getLaborCostPerSqFt(), toCheck.getProduct().getLaborCostPerSqFt());
            assertEquals(toAdd.getMaterialCost(), toCheck.getMaterialCost());
            assertEquals(toAdd.getLaborCost(), toCheck.getLaborCost());
            assertEquals(toAdd.getSalesTax(), toCheck.getSalesTax());
            assertEquals(toAdd.getTotalCost(), toCheck.getTotalCost());
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
            FMOrder rawOrder = od.getOrder(date, 2);
            BigDecimal alteredArea = rawOrder.getArea().add(new BigDecimal("10"));
            rawOrder.setArea(alteredArea);
            FMOrder toEdit = new FMOrder(rawOrder, rawOrder.getProduct(), rawOrder.getTaxRate());
            od.editOrder(toEdit);
            FMOrder toCheck = od.getOrder(date, 2);
            assertEquals(toEdit.getOrderNum(), toCheck.getOrderNum());
            assertEquals(toEdit.getCustomerName(), toCheck.getCustomerName());
            assertEquals(toEdit.getTaxRate().getStateAbv(), toCheck.getTaxRate().getStateAbv());
            assertEquals(toEdit.getTaxRate().getStateTaxRate(), toCheck.getTaxRate().getStateTaxRate());
            assertEquals(toEdit.getProduct().getMaterial(), toCheck.getProduct().getMaterial());
            assertEquals(toEdit.getArea(), toCheck.getArea());
            assertEquals(toEdit.getProduct().getCostPerSqFt(), toCheck.getProduct().getCostPerSqFt() );
            assertEquals(toEdit.getProduct().getLaborCostPerSqFt(), toCheck.getProduct().getLaborCostPerSqFt());
            assertEquals(toEdit.getMaterialCost(), toCheck.getMaterialCost());
            assertEquals(toEdit.getLaborCost(), toCheck.getLaborCost());
            assertEquals(toEdit.getSalesTax(), toCheck.getSalesTax());
            assertEquals(toEdit.getTotalCost(), toCheck.getTotalCost());

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
            toEdit.setOrderNum(99);
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
    public void testRemoveOrderNoFile() {
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
    public void testRemoveOrderNullDate() {
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
    public void testRemoveOrderBadOrderNum() {
        try {
            FMOrder toRemove = od.getOrder(date, 2);
            toRemove.setOrderNum(99);
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
