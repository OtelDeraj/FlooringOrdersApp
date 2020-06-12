/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.FMTax;
import com.sg.flooringmastery.exceptions.InvalidInputException;
import com.sg.flooringmastery.exceptions.TaxDaoException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Isaia
 */
public class InMemTDao implements TDao{
    
    List<FMTax> allStates = new ArrayList<>();
    
    
    public InMemTDao(){
        FMTax tax1 = new FMTax("TX", "Texas", new BigDecimal("4.45"));
        FMTax tax2 = new FMTax("CA", "California", new BigDecimal("25.00"));
        allStates.add(tax1);
        allStates.add(tax2);
    }

    @Override
    public List<FMTax> getAllStates() throws TaxDaoException {
       return allStates;
    }

    @Override
    public FMTax getTaxByStateAbv(String abv) throws TaxDaoException, InvalidInputException {
        FMTax toReturn = null;
        List<FMTax> allStates = getAllStates();
        for(FMTax t : allStates){
            if(t.getStateAbv().equalsIgnoreCase(abv)){
                toReturn = t;
                break;
            }
        }
        if(toReturn == null){
            throw new InvalidInputException("Product choice does not exist.");
        }
        return toReturn;
    }
    
}
