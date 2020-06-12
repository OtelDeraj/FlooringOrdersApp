/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.ODao;
import com.sg.flooringmastery.dao.PDao;
import com.sg.flooringmastery.dao.TDao;
import com.sg.flooringmastery.dto.FMOrder;
import com.sg.flooringmastery.dto.FMProduct;
import com.sg.flooringmastery.dto.FMTax;
import com.sg.flooringmastery.exceptions.InvalidInputException;
import com.sg.flooringmastery.exceptions.InvalidOrderDateException;
import com.sg.flooringmastery.exceptions.NullArgumentException;
import com.sg.flooringmastery.exceptions.OrderDaoException;
import com.sg.flooringmastery.exceptions.ProductDaoException;
import com.sg.flooringmastery.exceptions.TaxDaoException;
import java.math.BigDecimal;
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
    public void addOrder(FMOrder toAdd) throws OrderDaoException, TaxDaoException, InvalidOrderDateException {
        od.addOrder(toAdd);
    }
    // pass through method to the orderDao to add order

    
    //Remove
    //this method will end the removal feature path without removing the file if 
    // passed order is null
    public void removeOrder(FMOrder toRemove) throws OrderDaoException, InvalidOrderDateException, InvalidInputException {
        od.removeOrder(toRemove);
    }

    
    //Edit
    
    public FMOrder getOrder(LocalDate date, int orderNum) throws OrderDaoException, InvalidOrderDateException, InvalidInputException{
        return od.getOrder(date, orderNum);
    }
    
    public void editOrder(FMOrder toEdit) throws OrderDaoException, TaxDaoException, ProductDaoException, InvalidOrderDateException, NullArgumentException, InvalidInputException{
        od.editOrder(toEdit);

    }
    

    
    
    // this method builds a new order object after calculating taxes and totals
    // it is used in the add and edit paths
    public FMOrder calculateOrderDetails(FMOrder rawOrder) throws TaxDaoException, ProductDaoException, InvalidInputException, InvalidOrderDateException {
        validateOrderDetails(rawOrder);
        FMProduct product = pd.getProductByName(rawOrder.getProduct().getMaterial());
        FMTax taxRate = td.getTaxByStateAbv(rawOrder.getStateAbv());
        
        return new FMOrder(rawOrder, product, taxRate);
    }
    
    
    // --- Validatiions ---
    
    private void validateOrderDetails(FMOrder toCheck) throws InvalidInputException, InvalidOrderDateException{
        
        validateFutureDate(toCheck.getDate());
        validateCustomerName(toCheck.getCustomerName());
        validateMaterial(toCheck.getProduct().getMaterial());
        validateArea(toCheck.getArea());
        validateState(toCheck.getStateAbv());
        
    }

    private void validateCustomerName(String customerName) throws InvalidInputException {
        if(customerName.isBlank()){
            throw new InvalidInputException("Customer name cannot be empty.");
        } else if(customerName.contains(",")){
            throw new InvalidInputException("Illegal character (,) cannot be present in name.");
        }
    }

    private void validateMaterial(String material) throws InvalidInputException {
        if(material.isBlank()){
            throw new InvalidInputException("Material choice cannot be blank.");
        }
    }

    private void validateArea(BigDecimal area) throws InvalidInputException {
        if(area.toString().isBlank()){
            throw new InvalidInputException("Area value must be given.");
        } else if(area.compareTo(new BigDecimal("100")) == -1){
            throw new InvalidInputException("All orders must have an area greater than 100.");
        }
    }

    private void validateState(String stateAbv) throws InvalidInputException {
        if(stateAbv.isBlank()){
            throw new InvalidInputException("State abbreviation cannot be blank.");
        }
    }

    private void validateFutureDate(LocalDate date) throws InvalidOrderDateException {
        if (date.compareTo(LocalDate.now()) < 0) {
                throw new InvalidOrderDateException("Order dates cannot be made for past dates.");
            }
    }

    

    

    
}
