/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.ODao;
import com.sg.flooringmastery.dao.OrderDaoImpl;
import com.sg.flooringmastery.dao.PDao;
import com.sg.flooringmastery.dao.ProductDaoImpl;
import com.sg.flooringmastery.dao.TDao;
import com.sg.flooringmastery.dao.TaxDaoImpl;
import com.sg.flooringmastery.dto.FMOrder;
import java.util.List;

/**
 *
 * @author Isaia
 */
public class FMService {
    ODao od = new OrderDaoImpl("Orders_06022013.txt"); // TODO: remove the hard coding here, just using it for some end of day tests
    PDao pd = new ProductDaoImpl();
    TDao td = new TaxDaoImpl();
    
    public FMService(){
        
    }
    
    public List<FMOrder> getAllOrders(){
        return od.getAllOrders();
    }
    
    
    
}
