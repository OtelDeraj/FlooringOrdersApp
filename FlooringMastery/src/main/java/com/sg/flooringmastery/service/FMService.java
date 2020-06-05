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
import com.sg.flooringmastery.exceptions.OrderDaoException;
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
    
    
    
    public List<FMOrder> getOrdersForDate(LocalDate date) throws OrderDaoException{
        return od.getOrdersForDate(date);
    }
    
    public List<FMTax> getAllStates() throws TaxDaoException{
        return td.getAllStates();
    }
    
    public List<FMProduct> getAllProducts(){
        return pd.getAllProducts();
    }
    
    //Add
    public FMOrder calculateOrderDetails(FMOrder rawOrder) throws TaxDaoException {
        FMProduct product = pd.getProductByName(rawOrder.getProduct().getMaterial());
        FMOrder toReturn = new FMOrder(rawOrder.getDate(),
        rawOrder.getCustomerName(), rawOrder.getState(), td.getTaxByStateAbv(rawOrder.getState()),
        product.getMaterial(), rawOrder.getArea(), product.getCostPerSqFt(),
        product.getLaborCostPerSqFt());
        return toReturn;
    }
    
    
    // this method ends the add order feature path if the order passed is null
    public void confirmOrder(FMOrder order) throws OrderDaoException {
        if(order != null){// order !null calls addOrder, continuing the feature path
            addOrder(order);
        } 
    }
    // pass through method to the orderDao to add order
    private void addOrder(FMOrder toAdd) throws OrderDaoException{
        od.addOrder(toAdd);
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
    
    

    

    
}
