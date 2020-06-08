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
public class ProductDaoImplTest {
    
    PDao toTest = new ProductDaoImpl("testProducts.txt");
    
    public ProductDaoImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws IOException {
        Path testPath = Paths.get("TestData", "testProducts.txt");
        Path seedPath = Paths.get("TestData", "seedProducts.txt");
        
        File testFile = testPath.toFile();
        
        testFile.delete();
        Files.copy(seedPath, testPath);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of convertLineToProduct method, of class ProductDaoImpl.
     */
    @Test
    public void testConvertLineToProduct() {
    }

    /**
     * Test of getAllProducts method, of class ProductDaoImpl.
     */
    @Test
    public void testGetAllProducts() {
    }

    /**
     * Test of getProductByName method, of class ProductDaoImpl.
     */
    @Test
    public void testGetProductByName() {
    }
    
}
