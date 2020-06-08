/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    
    TDao toTest = new TaxDaoImpl("testTaxes.txt");
    
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
    public void testGetAllStates() {
    }

    /**
     * Test of getTaxByStateAbv method, of class TaxDaoImpl.
     */
    @Test
    public void testGetTaxByStateAbv() {
    }
    
}
