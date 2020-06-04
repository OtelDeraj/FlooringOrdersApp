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
import com.sg.flooringmastery.dto.FMProduct;
import com.sg.flooringmastery.dto.FMTax;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Isaia
 */
public class FMService {
    ODao od = new OrderDaoImpl("Orders_" + LocalDate.now() + ".txt"); // TODO: remove the hard coding here, just using it for some end of day tests
    PDao pd = new ProductDaoImpl("Products.txt");
    TDao td = new TaxDaoImpl("Taxes.txt");
    
    public FMService(){
        
    }
    
    public List<FMOrder> getAllOrders(){
        return od.getAllOrders();
    }
    
    public List<FMOrder> getOrdersForDate(LocalDate date){
        return od.getOrdersForDate(date);
    }
    
    
    
    //Add
    
    public FMOrder addOrder(FMOrder toAdd){
        throw new UnsupportedOperationException();
    }
    
    //Remove
    
//    public void removeOrder(FMOrder toRemove)
    public void removeOrder(LocalDate date, int orderNum){  
        throw new UnsupportedOperationException();
    }
    
    //Edit
    public void editOrder(FMOrder toEdit){
        throw new UnsupportedOperationException();
    }
    
    public List<FMTax> getAllStates(){
        throw new UnsupportedOperationException();
    }
    
    public List<FMProduct> getAllProducts(){
        throw new UnsupportedOperationException();
    }
}
