/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.FMTax;
import com.sg.flooringmastery.exceptions.TaxDaoException;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
public class TaxDaoImplTest {

    TDao td = new TaxDaoImpl("testTaxes.txt");

    public TaxDaoImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws IOException {
        Path testPath = Paths.get("TestData", "testTaxes.txt");
        Path seedPath = Paths.get("TestData", "seedTaxes.txt");

        File testFile = testPath.toFile();

        testFile.delete();
        Files.copy(seedPath, testPath);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getAllStates method, of class TaxDaoImpl.
     */
    @Test
    public void testGetAllStatesGoldenPath() {
        try {
            List<FMTax> allStates = td.getAllStates();
            FMTax first = allStates.get(0);
            FMTax last = allStates.get(allStates.size() - 1);
            assertEquals("Texas", first.getStateName());
            assertEquals("California", last.getStateName());

        } catch (TaxDaoException ex) {
            fail("Should not throw TaxDaoException");
        }
    }

    /**
     * Test of getTaxByStateAbv method, of class TaxDaoImpl.
     */
    @Test
    public void testGetTaxByStateAbvGoldenPath() {
        try {
            FMTax testTax = td.getTaxByStateAbv("TX");
            assertEquals(new BigDecimal("4.45"), testTax.getStateTaxRate());
        } catch (TaxDaoException ex) {
            fail("Should not throw TaxDaoException in golden path.");
        }
    }

    @Test
    public void testGetTaxByStateAbvBadAbv() {
        try {
            FMTax testTax = td.getTaxByStateAbv("NY");
            fail("Should have thrown TaxDaoException.");
        } catch (TaxDaoException ex) {
            
        }
    }

}
