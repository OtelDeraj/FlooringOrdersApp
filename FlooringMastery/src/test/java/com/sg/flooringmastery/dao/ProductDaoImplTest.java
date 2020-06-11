/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.FMProduct;
import com.sg.flooringmastery.exceptions.ProductDaoException;
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
public class ProductDaoImplTest {
    
    PDao pd = new ProductDaoImpl("testProducts.txt");
    
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
     * Test of getAllProducts method, of class ProductDaoImpl.
     */
    @Test
    public void testGetAllProductsGoldenPath() {
        try {
            List<FMProduct> allProducts = pd.getAllProducts();
            FMProduct first = allProducts.get(0);
            FMProduct last = allProducts.get(allProducts.size() - 1);
            assertEquals("Carpet", first.getMaterial());
            assertEquals("Wood", last.getMaterial());
        } catch (ProductDaoException ex) {
            fail("Should not hit ProductDaoException in golden path");
        }
    }

    /**
     * Test of getProductByName method, of class ProductDaoImpl.
     */
    @Test
    public void testGetProductByNameGoldenPath() {
        try {
            FMProduct testProduct = pd.getProductByName("Tile");
            assertEquals(new BigDecimal("4.15"), testProduct.getLaborCostPerSqFt());
        } catch (ProductDaoException ex) {
            fail("Should not hit ProductDaoException in golden path");
        }
    }
    
    @Test
    public void testGetProductByNameBadName(){
        try {
            FMProduct testProduct = pd.getProductByName("Plastic");
            fail("Should have hit ProductDaoException");
        } catch (ProductDaoException ex) {

        }
    }
    
}
