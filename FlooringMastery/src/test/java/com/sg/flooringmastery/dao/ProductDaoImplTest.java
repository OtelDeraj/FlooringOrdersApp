/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.FMProduct;
import com.sg.flooringmastery.exceptions.InvalidInputException;
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
public class ProductDaoImplTest {
    
    PDao pd = new ProductDaoImpl("TestData/testProducts.txt");
    
    public ProductDaoImplTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() throws IOException {
        Path testPath = Paths.get("TestData", "testProducts.txt");
        Path seedPath = Paths.get("TestData", "seedProducts.txt");
        
        File testFile = testPath.toFile();
        
        testFile.delete();
        Files.copy(seedPath, testPath);
    }
    
    @AfterEach
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
        } catch (InvalidInputException ex) {
            fail("Should not hit InvalidInputException in golden path");
        }
    }
    
    @Test
    public void testGetProductByNameBadName(){
        try {
            FMProduct testProduct = pd.getProductByName("Plastic");
            fail("Should have hit InvalidInputException");
        } catch (InvalidInputException ex) {

        } catch (ProductDaoException ex) {
            fail("Should not hit ProductDaoException in Bad Name check");
        }
    }
    
}
