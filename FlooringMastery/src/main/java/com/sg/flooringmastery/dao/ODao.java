/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.FMOrder;
import com.sg.flooringmastery.exceptions.OrderDaoException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Isaia
 */
public interface ODao {
        
    List<FMOrder> getOrdersForDate(LocalDate date) throws OrderDaoException;
    
    FMOrder getOrder(LocalDate date, int orderNum) throws OrderDaoException;
        
    FMOrder addOrder(FMOrder toAdd) throws OrderDaoException;
    
    void editOrder(FMOrder selectedOrder) throws OrderDaoException;
    
    void removeOrder(FMOrder toRemove) throws OrderDaoException;
    
}
