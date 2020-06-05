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

/**
 *
 * @author Isaia
 */
public interface TDao {
    
    
    List<FMTax> getAllStates() throws TaxDaoException;
    
    BigDecimal getTaxByStateAbv(String abv) throws TaxDaoException;
    
    
}
