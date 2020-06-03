/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.FMOrder;
import java.util.List;

/**
 *
 * @author Isaia
 */
public interface ODao {
    
    List<FMOrder> getAllOrders();
    
    FMOrder getOrderByNumber(int orderNum);
    
    FMOrder getOrderByName(String name);
    
    void editOrder(FMOrder selectedOrder);
    
}
