/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dto.FMOrder;
import com.sg.flooringmastery.dto.FMProduct;
import com.sg.flooringmastery.exceptions.InvalidInputException;
import com.sg.flooringmastery.exceptions.InvalidOrderDateException;
import com.sg.flooringmastery.exceptions.ProductDaoException;
import com.sg.flooringmastery.exceptions.TaxDaoException;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Isaia
 */
public class FMServiceTest {

    FMService serv;
    LocalDate date = LocalDate.of(2962, 12, 8);

    public FMServiceTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        serv = ctx.getBean("serv", FMService.class);
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of calculateOrderDetails method, of class FMService.
     */
    @Test
    public void testCalculateOrderDetailsGoldenPath() {
        try {
            FMOrder toCalc = new FMOrder();
            FMProduct product = new FMProduct();
            toCalc.setStateAbv("CA");
            toCalc.setCustomerName("Ron Swanson");
            product.setMaterial("tile");
            toCalc.setArea(new BigDecimal("249"));
            toCalc.setProduct(product);
            toCalc.setDate(date);
            FMOrder finalOrder = serv.calculateOrderDetails(toCalc);
            assertEquals(new BigDecimal("2381.06"), finalOrder.getTotalCost());
        } catch (TaxDaoException ex) {
            fail("Should not throw TaxDaoException in golden path.");
        } catch (ProductDaoException ex) {
            fail("Should not throw ProductDaoException in golden path.");
        } catch (InvalidInputException ex) {
            fail("Should not throw InvalidInputException in golden path.");
        } catch (InvalidOrderDateException ex) {
            fail("Should not throw InvalidOrderDateException in golden path.");
        }

    }

    @Test
    public void testCalculateOrderDetailsCustomerNameIsBlank() throws InvalidInputException {
        try {
            FMOrder toCalc = new FMOrder();
            FMProduct product = new FMProduct();
            toCalc.setStateAbv("CA");
            toCalc.setCustomerName("");
            product.setMaterial("tile");
            toCalc.setArea(new BigDecimal("249"));
            toCalc.setProduct(product);
            toCalc.setDate(date);

            FMOrder finalOrder = serv.calculateOrderDetails(toCalc);
            fail("Should have thrown InvalidInputException.");
        } catch (TaxDaoException ex) {
            fail("Should not throw TaxDaoException");
        } catch (ProductDaoException ex) {
            fail("Should not throw ProductDaoException");
        } catch (InvalidInputException ex) {

        } catch (InvalidOrderDateException ex) {
            fail("Should not throw InvalidOrderDateException");
        }
    }

    @Test
    public void testCalculateOrderDetailsCustomerNameContainsComma() throws InvalidInputException {
        try {
            FMOrder toCalc = new FMOrder();
            FMProduct product = new FMProduct();
            toCalc.setStateAbv("CA");
            toCalc.setCustomerName("Ron, Swanson");
            product.setMaterial("tile");
            toCalc.setArea(new BigDecimal("249"));
            toCalc.setProduct(product);
            toCalc.setDate(date);
            FMOrder finalOrder = serv.calculateOrderDetails(toCalc);
            fail("Should have thrown InvalidInputException.");
        } catch (TaxDaoException ex) {
            fail("Should not throw TaxDaoException");
        } catch (ProductDaoException ex) {
            fail("Should not throw ProductDaoException");
        } catch (InvalidInputException ex) {

        } catch (InvalidOrderDateException ex) {
            fail("Should not throw InvalidOrderDateException");
        }
    }

    @Test
    public void testCalculateOrderDetailsInvalidMaterial() throws InvalidInputException {
        try {
            FMOrder toCalc = new FMOrder();
            FMProduct product = new FMProduct();
            toCalc.setStateAbv("CA");
            toCalc.setCustomerName("Ron Swanson");
            product.setMaterial("plastic");
            toCalc.setArea(new BigDecimal("249"));
            toCalc.setProduct(product);
            toCalc.setDate(date);

            FMOrder finalOrder = serv.calculateOrderDetails(toCalc);
            fail("Should have thrown InvalidInputException.");
        } catch (TaxDaoException ex) {
            fail("Should not throw TaxDaoException");
        } catch (ProductDaoException ex) {
            fail("Should not throw ProductDaoException");
        } catch (InvalidInputException ex) {

        } catch (InvalidOrderDateException ex) {
            fail("Should not throw InvalidOrderDateException");
        }
    }

    @Test
    public void testCalculateOrderDetailsInvalidArea() throws InvalidInputException {
        try {
            FMOrder toCalc = new FMOrder();
            FMProduct product = new FMProduct();
            toCalc.setStateAbv("CA");
            toCalc.setCustomerName("Ron Swanson");
            product.setMaterial("tile");
            toCalc.setArea(new BigDecimal("99"));
            toCalc.setProduct(product);
            toCalc.setDate(date);

            FMOrder finalOrder = serv.calculateOrderDetails(toCalc);
            fail("Should have thrown InvalidInputException.");
        } catch (TaxDaoException ex) {
            fail("Should not throw TaxDaoException");
        } catch (ProductDaoException ex) {
            fail("Should not throw ProductDaoException");
        } catch (InvalidInputException ex) {

        } catch (InvalidOrderDateException ex) {
            fail("Should not throw InvalidOrderDateException");
        }
    }

    @Test
    public void testCalculateOrderDetailsInvalidState() throws InvalidInputException {
        try {
            FMOrder toCalc = new FMOrder();
            FMProduct product = new FMProduct();
            toCalc.setStateAbv("WV");
            toCalc.setCustomerName("Ron Swanson");
            product.setMaterial("tile");
            toCalc.setArea(new BigDecimal("249"));
            toCalc.setProduct(product);
            toCalc.setDate(date);

            FMOrder finalOrder = serv.calculateOrderDetails(toCalc);
            fail("Should have thrown InvalidInputException.");
        } catch (TaxDaoException ex) {
            fail("Should not throw TaxDaoException");
        } catch (ProductDaoException ex) {
            fail("Should not throw ProductDaoException");
        } catch (InvalidInputException ex) {

        } catch (InvalidOrderDateException ex) {
            fail("Should not throw InvalidOrderDateException");
        }
    }

    @Test
    public void testCalculateOrderDetailsFutureDate() throws InvalidOrderDateException {
        try {
            FMOrder toCalc = new FMOrder();
            FMProduct product = new FMProduct();
            toCalc.setStateAbv("CA");
            toCalc.setCustomerName("Ron Swanson");
            product.setMaterial("tile");
            toCalc.setArea(new BigDecimal("249"));
            toCalc.setProduct(product);
            toCalc.setDate(LocalDate.of(1996, 12, 8));
            FMOrder finalOrder = serv.calculateOrderDetails(toCalc);
            fail("Should throw InvalidOrderDateException");
        } catch (TaxDaoException ex) {
            fail("Should not throw TaxDaoException");
        } catch (ProductDaoException ex) {
            fail("Should not throw ProductDaoException");
        } catch (InvalidInputException ex) {
            fail("Should not throw InvalidInputException");
        } catch (InvalidOrderDateException ex) {
            
        }
    }
}
