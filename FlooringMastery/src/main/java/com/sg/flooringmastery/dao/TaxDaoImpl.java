/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.FMTax;
import com.sg.flooringmastery.exceptions.TaxDaoException;
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
public class TaxDaoImpl implements TDao {
    
    String PATH;
    
    public TaxDaoImpl(String path){
        this.PATH = path;
    }

    @Override
    public List<FMTax> getAllStates() throws TaxDaoException {
        List<FMTax> allStates = new ArrayList<>();
        
        try{
            Scanner scnFile = new Scanner( new BufferedReader( new FileReader(PATH)));
            
            scnFile.nextLine();
            while(scnFile.hasNextLine()){
                String row = scnFile.nextLine();
                FMTax state = convertLineToTax(row);
                
                allStates.add(state);
            }
            scnFile.close();
            
        } catch(FileNotFoundException ex){
            throw new TaxDaoException("Could not find file" + PATH + ".", ex);
        }
        return allStates;
    }
    
    @Override
    public FMTax getTaxByStateAbv(String abv) throws TaxDaoException {
        FMTax toReturn = null;
        List<FMTax> allStates = getAllStates();
        for(FMTax t : allStates) {
            if(t.getStateAbv().equalsIgnoreCase(abv)) {
                toReturn = t;
                break;
            }
        }
        return toReturn;
    }

    private FMTax convertLineToTax(String row) {
        String[] cells = row.split(",");
        
        String abv = cells[0];
        String stateName = cells[1];
        BigDecimal taxRate = new BigDecimal(cells[2]);
        
        FMTax state = new FMTax(abv, stateName, taxRate);
        
        return state;
    }
    
}
