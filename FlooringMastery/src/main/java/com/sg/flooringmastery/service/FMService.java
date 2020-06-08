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
import com.sg.flooringmastery.exceptions.InvalidOrderDateException;
import com.sg.flooringmastery.exceptions.OrderDaoException;
import com.sg.flooringmastery.exceptions.ProductDaoException;
import com.sg.flooringmastery.exceptions.TaxDaoException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Isaia
 */
public class FMService {
    ODao od;
    PDao pd;
    TDao td;
    
    public FMService(ODao od, PDao pd, TDao td){
       this.od = od;
       this.pd = pd;
       this.td = td;
    }
    
    
    
    public List<FMOrder> getOrdersForDate(LocalDate date) throws OrderDaoException, InvalidOrderDateException{
        return od.getOrdersForDate(date);
    }
    
    public List<FMTax> getAllStates() throws TaxDaoException{
        return td.getAllStates();
    }
    
    public List<FMProduct> getAllProducts() throws ProductDaoException{
        return pd.getAllProducts();
    }
    
    //Add

    // this method ends the add order feature path if the order passed is null
    public void confirmAddOrder(FMOrder toAdd) throws OrderDaoException, TaxDaoException, InvalidOrderDateException {
        if(toAdd != null){// order !null calls addOrder, continuing the feature path
            addOrder(toAdd);
        } 
    }
    // pass through method to the orderDao to add order
    private void addOrder(FMOrder toAdd) throws OrderDaoException, TaxDaoException, InvalidOrderDateException{
        od.addOrder(toAdd);
    }
    
    //Remove
    //this method will end the removal feature path without removing the file if 
    // passed order is null
    public void confirmRemoval(FMOrder toRemove) throws OrderDaoException, InvalidOrderDateException {
        if(toRemove != null){
            removeOrder(toRemove);
        }
    }
//    public void removeOrder(FMOrder toRemove)
    private void removeOrder(FMOrder toRemove) throws OrderDaoException, InvalidOrderDateException{  
        od.removeOrder(toRemove);
    }
    
    //Edit
    
    public FMOrder getOrder(LocalDate date, int orderNum) throws OrderDaoException, InvalidOrderDateException{
        return od.getOrder(date, orderNum);
    }
    
    public void confirmEditOrder(FMOrder toEdit) throws OrderDaoException, TaxDaoException, ProductDaoException, InvalidOrderDateException{
        if(toEdit != null){
            editOrder(toEdit);
        }
    }
    
    private void editOrder(FMOrder toEdit) throws OrderDaoException, TaxDaoException, ProductDaoException, InvalidOrderDateException{
        od.editOrder(toEdit);
    }
    
    
    // this method builds a new order object after calculating taxes and totals
    // it is used in the add and edit paths
    public FMOrder calculateOrderDetails(FMOrder rawOrder) throws TaxDaoException, ProductDaoException {
        FMProduct product = pd.getProductByName(rawOrder.getProduct().getMaterial());
        FMTax taxRate = td.getTaxByStateAbv(rawOrder.getStateAbv());
        
        return new FMOrder(rawOrder, product, taxRate);
    }

    

    

    
}
