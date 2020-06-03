/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.FMProduct;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Isaia
 */
public class ProductDaoImpl implements PDao {

    String PATH = "Products.txt";

    public FMProduct convertLineToProduct(String row) {
        String[] cells = row.split("::");

        String material = cells[0];
        BigDecimal costPerSqFt = new BigDecimal(cells[1]);
        BigDecimal laborCostPerSqFt = new BigDecimal(cells[2]);

        FMProduct product = new FMProduct(material, costPerSqFt, laborCostPerSqFt);

        return product;

    }

    @Override
    public List<FMProduct> getAllProducts() {
        List<FMProduct> allProducts = new ArrayList<>();

        try {
            Scanner scnFile = new Scanner(new BufferedReader(new FileReader(PATH)));
            scnFile.nextLine();
            while (scnFile.hasNextLine()) {
                String row = scnFile.nextLine();
                FMProduct product = convertLineToProduct(row);

                allProducts.add(product);
            }
            scnFile.close();
        } catch (FileNotFoundException ex) {
            
        }
        return allProducts;
    }

    @Override
    public FMProduct getProductByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void editProduct(FMProduct selectedProduct) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
