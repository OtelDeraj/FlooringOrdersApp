/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.FMTax;
import com.sg.flooringmastery.exceptions.TaxDaoException;
import java.math.BigDecimal;
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
public class TaxDaoImplTest {

    TDao toTest = new TaxDaoImpl("Taxes.txt");

    public TaxDaoImplTest() {
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
     * Test of getAllStates method, of class TaxDaoImpl.
     */
    @Test
    public void testGetAllStatesGoldendPath() throws TaxDaoException {
        List<FMTax> taxes = toTest.getAllStates();

        assertEquals(4, taxes.size());

        assertEquals("TX", taxes.get(0).getStateAbv());
        assertEquals(new BigDecimal("4.45"), taxes.get(0).getStateTaxRate());

        assertEquals("CA", taxes.get(3).getStateAbv());
        assertEquals(new BigDecimal("25.00"), taxes.get(3).getStateTaxRate());
    }

    @Test
    public void testGetAllStatesBadFile() {
        TDao badPathDao = new TaxDaoImpl("fakeFile.txt");

        try {
            badPathDao.getAllStates();
            fail("Did not hit exception in testGetAllStateBadFile()");

        } catch (TaxDaoException ex) {

        }
    }

    /**
     * Test of getTaxByStateAbv method, of class TaxDaoImpl.
     */
    @Test
    public void testGetTaxByStateAbvGoldenPath() throws TaxDaoException {

//        FMTax retrieved = toTest.getTaxByStateAbv("TX");

//        assertEquals("TX", retrieved.getStateAbv());
//        assertEquals(new BigDecimal("4.45"), retrieved.getStateTaxRate());

    }

    @Test
    public void testGetTaxByStateAbvBadAbv() throws TaxDaoException {

//        FMTax retrieved = toTest.getTaxByStateAbv("ZZ");
//
//        assertNull(retrieved);
    }

}
