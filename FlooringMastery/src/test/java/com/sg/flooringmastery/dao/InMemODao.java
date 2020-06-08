/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.FMOrder;
import com.sg.flooringmastery.exceptions.OrderDaoException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Isaia
 */
public class InMemODao implements ODao{
    
    List<FMOrder> allOrders = new ArrayList<>();
    
    
    public InMemODao(){
        FMOrder order1 = new FMOrder(1, "Ada", "CA", new BigDecimal("25.00"), "tile",
                new BigDecimal("249.00"), new BigDecimal("3.50"), new BigDecimal("4.15"),
                new BigDecimal("871.50"), new BigDecimal("1033.35"), new BigDecimal("476.21"),
                new BigDecimal("2381.06"));
        
        FMOrder order2 = new FMOrder(2, "Charles", "WA", new BigDecimal("9.25"), "wood",
                new BigDecimal("250.00"), new BigDecimal("5.15"), new BigDecimal("4.75"),
                new BigDecimal("1287.50"), new BigDecimal("1187.50"), new BigDecimal("228.94"),
                new BigDecimal("2703.94"));
        
        allOrders.add(order1);
        allOrders.add(order2);
    }


    @Override
    public List<FMOrder> getOrdersForDate(LocalDate date) throws OrderDaoException {
        return allOrders;
    }

    @Override
    public FMOrder getOrder(LocalDate date, int orderNum) throws OrderDaoException {
        FMOrder toReturn = null;
        for(FMOrder o : allOrders){
            if(o.getOrderNum() == orderNum){
                toReturn = o;
            }
        }
        return toReturn;
    }

    @Override
    public void addOrder(FMOrder toAdd) throws OrderDaoException {
        int maxOrderNum = 0;
        for(FMOrder o: allOrders){
            if(o.getOrderNum() > maxOrderNum){
                maxOrderNum = o.getOrderNum();
            }
        }
        toAdd.setOrderNum(maxOrderNum + 1);
        allOrders.add(toAdd);
        
    }

    @Override
    public void editOrder(FMOrder selectedOrder) throws OrderDaoException {
        int index = -1;
        for(int i = 0; i < allOrders.size(); i++){
            FMOrder o = allOrders.get(i);
            if(o.getOrderNum() == selectedOrder.getOrderNum()){
                index = i;
                break;
            }
        }
        allOrders.set(index, selectedOrder);
    }

    @Override
    public void removeOrder(FMOrder toRemove) throws OrderDaoException {
        int index = -1;
        for(int i = 0; i < allOrders.size(); i++){
            FMOrder o = allOrders.get(i);
            if(o.getOrderNum() == toRemove.getOrderNum()){
                index = i;
                break;
            }
        }
        allOrders.remove(index);
    }
    
}
