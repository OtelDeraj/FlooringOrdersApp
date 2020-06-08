/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.FMOrder;
import com.sg.flooringmastery.exceptions.InvalidOrderDateException;
import com.sg.flooringmastery.exceptions.OrderDaoException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Isaia
 */
public interface ODao {
        
    List<FMOrder> getOrdersForDate(LocalDate date) throws OrderDaoException, InvalidOrderDateException;
    
    FMOrder getOrder(LocalDate date, int orderNum) throws OrderDaoException, InvalidOrderDateException;
        
    void addOrder(FMOrder toAdd) throws OrderDaoException, InvalidOrderDateException;
    
    void editOrder(FMOrder selectedOrder) throws OrderDaoException, InvalidOrderDateException;
    
    void removeOrder(FMOrder toRemove) throws OrderDaoException, InvalidOrderDateException;
    
}
