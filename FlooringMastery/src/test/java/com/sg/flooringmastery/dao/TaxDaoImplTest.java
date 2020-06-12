/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.FMTax;
import com.sg.flooringmastery.exceptions.InvalidInputException;
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
public class TaxDaoImplTest {

    TDao td = new TaxDaoImpl("TestData/testTaxes.txt");

    public TaxDaoImplTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() throws IOException {
        Path testPath = Paths.get("TestData", "testTaxes.txt");
        Path seedPath = Paths.get("TestData", "seedTaxes.txt");

        File testFile = testPath.toFile();

        testFile.delete();
        Files.copy(seedPath, testPath);
    }

    @AfterEach
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
        } catch (InvalidInputException ex) {
            fail("Should not have thrown InvalidInputException in golden path.");
        }
    }

    @Test
    public void testGetTaxByStateAbvBadAbv() {
        try {
            FMTax testTax = td.getTaxByStateAbv("NY");
            fail("Should have thrown InvalidInputException.");
        } catch (TaxDaoException ex) {
            fail("Should not have thrown TaxDaoException.");
        } catch (InvalidInputException ex) {
            
        }
    }

}
