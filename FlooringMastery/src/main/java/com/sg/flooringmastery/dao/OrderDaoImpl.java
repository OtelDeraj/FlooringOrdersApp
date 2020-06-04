/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.FMOrder;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Isaia
 */
public class OrderDaoImpl implements ODao {
    
    String PATH;
    
    public OrderDaoImpl(String path) {
        this.PATH = path;
    }
    
    private void writeFile(List<FMOrder> toWrite) {
        try {
            PrintWriter write = new PrintWriter(new FileWriter(PATH));
            
            write.println("OrderNumber,CustomerName,State,TaxRate,ProductType,"
                    + "Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,"
                    + "LaborCost,Tax,Total");
            for (FMOrder o : toWrite) {
                String line = convertOrderToLine(o);
                write.println(line);
            }
            
            write.flush();
            write.close();
        } catch (IOException ex) {
            
        }
    }
    
    private String convertOrderToLine(FMOrder o) {
        return o.getOrderNum() + "::"
                + o.getCustomerName() + "::"
                + o.getTaxRate().getStateName() + "::"
                + o.getTaxRate().getStateTaxRate() + "::"
                + o.getProduct().getMaterial() + "::"
                + o.getArea() + "::"
                + o.getProduct().getCostPerSqFt() + "::"
                + o.getProduct().getLaborCostPerSqFt() + "::"
                + o.getMaterialCost() + "::"
                + o.getSalesTax() + "::"
                + o.getTotalCost();
    }
    
    private FMOrder convertLineToOrder(String row) {
        
        String[] cells = row.split("::");
        
        
        int orderNum = Integer.parseInt(cells[0]);
        String customerName = cells[1];
        String state = cells[2];
        BigDecimal taxRate = new BigDecimal(cells[3]);
        String productType = cells[4];
        BigDecimal area = new BigDecimal(cells[5]);
        BigDecimal costPerSqFt = new BigDecimal(cells[6]);
        BigDecimal laborCostPerSqFt = new BigDecimal(cells[7]);
        BigDecimal materialCost = new BigDecimal(cells[8]);
        BigDecimal laborCost = new BigDecimal(cells[9]);
        BigDecimal tax = new BigDecimal(cells[10]);
        BigDecimal total = new BigDecimal(cells[11]);
        
        FMOrder order = new FMOrder(orderNum, customerName, state, taxRate, productType,
                area, costPerSqFt, laborCostPerSqFt, materialCost, laborCost, tax, total);
        
        return order;
    }
    
    @Override
    public List<FMOrder> getAllOrders() {
        List<FMOrder> allOrders = new ArrayList<>();
        
        try {
            Scanner scnFile = new Scanner(new BufferedReader(new FileReader(PATH)));
            scnFile.nextLine();
            while (scnFile.hasNextLine()) {
                String row = scnFile.nextLine();
                FMOrder order = convertLineToOrder(row);
                
                allOrders.add(order);
            }
            scnFile.close();
            
        } catch (FileNotFoundException ex) {
            
        }
        return allOrders;
    }
    
    
    @Override
    public FMOrder getOrderByName(String name) {
        FMOrder toReturn = null;
        List<FMOrder> allOrders = getAllOrders();
        for (FMOrder o : allOrders) {
            if (o.getCustomerName().equalsIgnoreCase(name)) {
                toReturn = o;
                break;
            }
        }
        return toReturn;
    }
    
    @Override
    public void editOrder(FMOrder selectedOrder) {
        List<FMOrder> allOrders = getAllOrders();
        int index = -1;
        for (int i = 0; i < allOrders.size(); i++) {
            FMOrder toCheck = allOrders.get(i);
            
            if (toCheck.getOrderNum() == selectedOrder.getOrderNum()
                    || toCheck.getCustomerName().equalsIgnoreCase(selectedOrder.getCustomerName())) {
                index = i;
                break;
            }            
        }
        allOrders.set(index, selectedOrder);
        
        writeFile(allOrders);
    }

    @Override
    public List<FMOrder> getOrdersForDate(LocalDate date) {
        return null; // will not be null after this method is implemented
        
    }
    
    @Override
    public FMOrder getOrder( LocalDate date, int orderNum){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public FMOrder addOrder(FMOrder toAdd) {
        List<FMOrder> allOrders = getAllOrders();
        
        int maxOrderNum = 0;
        
        for(FMOrder o : allOrders){
            if(o.getOrderNum() > maxOrderNum){
                maxOrderNum = o.getOrderNum();
            }
        }
        toAdd.setOrderNum(maxOrderNum + 1);
        
        allOrders.add(toAdd);
        writeFile(allOrders);
        
        return toAdd;
    }
    
}
