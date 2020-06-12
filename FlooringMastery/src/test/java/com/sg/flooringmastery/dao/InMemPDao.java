/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.FMProduct;
import com.sg.flooringmastery.exceptions.ProductDaoException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Isaia
 */
public class InMemPDao implements PDao{
    
    List<FMProduct> allProducts = new ArrayList<>();
    
    
    public InMemPDao(){
        FMProduct product1 = new FMProduct("carpet", new BigDecimal("2.25"), new BigDecimal("2.10"));
        FMProduct product2 = new FMProduct("tile", new BigDecimal("3.50"), new BigDecimal("4.15"));
        allProducts.add(product1);
        allProducts.add(product2);
    }

    @Override
    public List<FMProduct> getAllProducts() throws ProductDaoException {
        return allProducts;
    }

    @Override
    public FMProduct getProductByName(String name) throws ProductDaoException {
        FMProduct toReturn = null;
        List<FMProduct> allProducts = getAllProducts();
        for(FMProduct p : allProducts){
            if(p.getMaterial().equalsIgnoreCase(name)){
                toReturn = p;
                break;
            }
        }
        return toReturn;
    }
    
}
