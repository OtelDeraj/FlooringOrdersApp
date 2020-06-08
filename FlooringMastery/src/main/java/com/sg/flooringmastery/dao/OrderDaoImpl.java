/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.FMOrder;
import com.sg.flooringmastery.exceptions.InvalidOrderDateException;
import com.sg.flooringmastery.exceptions.OrderDaoException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Isaia
 */
public class OrderDaoImpl implements ODao {

    String DIRECTORY;

    final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMddyyyy");

    public OrderDaoImpl(String directory) {
        this.DIRECTORY = directory;
    }

    private void writeFile(List<FMOrder> toWrite, LocalDate date) throws OrderDaoException {
        String path = convertDateToPath(date);
        try {
            PrintWriter write = new PrintWriter(new FileWriter(path));

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
            throw new OrderDaoException("Could not write to " + path + " file: ", ex);
        }
    }

    private String convertOrderToLine(FMOrder o) {
        return o.getOrderNum() + "," 
                + o.getCustomerName() + ","
                + o.getTaxRate().getStateAbv() + ","
                + o.getTaxRate().getStateTaxRate() + ","
                + o.getProduct().getMaterial() + ","
                + o.getArea() + ","
                + o.getProduct().getCostPerSqFt() + ","
                + o.getProduct().getLaborCostPerSqFt() + ","
                + o.getMaterialCost() + ","
                + o.getLaborCost() + ","
                + o.getSalesTax() + ","
                + o.getTotalCost();
    }

    private FMOrder convertLineToOrder(String row) {

        String[] cells = row.split(",");

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
    public List<FMOrder> getOrdersForDate(LocalDate date) throws InvalidOrderDateException {
        String path = convertDateToPath(date);
        List<FMOrder> allOrders = new ArrayList<>();
        try {
            Scanner scnFile = new Scanner(new BufferedReader(new FileReader(path)));
            scnFile.nextLine();
            while (scnFile.hasNextLine()) {
                String row = scnFile.nextLine();
                FMOrder order = convertLineToOrder(row);

                allOrders.add(order);
            }
            scnFile.close();

        } catch (FileNotFoundException ex) {
            throw new InvalidOrderDateException("Given Date of Order does not exist.", ex);
        }
        return allOrders;
    }



    @Override
    public FMOrder getOrder(LocalDate date, int orderNum) throws InvalidOrderDateException {

        List<FMOrder> allOrders = getOrdersForDate(date);

        FMOrder toReturn = null;

        for (FMOrder o : allOrders) {
            if (o.getOrderNum() == orderNum) {
                toReturn = o;
                break;
            }
        }
        toReturn.setDate(date);
        return toReturn;
    }

    @Override
    public void addOrder(FMOrder toAdd) throws OrderDaoException, InvalidOrderDateException {
        List<FMOrder> allOrders = getOrdersForDate(toAdd.getDate());

        int maxOrderNum = 0;

        for (FMOrder o : allOrders) {
            if (o.getOrderNum() > maxOrderNum) {
                maxOrderNum = o.getOrderNum();
            }
        }
        toAdd.setOrderNum(maxOrderNum + 1);

        allOrders.add(toAdd);
        writeFile(allOrders, toAdd.getDate());
    }
    
    @Override
    public void editOrder(FMOrder selectedOrder) throws OrderDaoException, InvalidOrderDateException {
        List<FMOrder> allOrders = getOrdersForDate(selectedOrder.getDate());
        int index = -1;
        for (int i = 0; i < allOrders.size(); i++) {
            FMOrder toCheck = allOrders.get(i);

            if (toCheck.getOrderNum() == selectedOrder.getOrderNum()) {
                index = i;
                break;
            }
        }
        allOrders.set(index, selectedOrder);

        writeFile(allOrders, selectedOrder.getDate());
    }
    
    @Override
    public void removeOrder(FMOrder toRemove) throws OrderDaoException, InvalidOrderDateException {
        List<FMOrder> allOrders = getOrdersForDate(toRemove.getDate());
        
        int index = -1;
        
        for(int i = 0; i < allOrders.size(); i++){
            FMOrder toCheck = allOrders.get(i);
            if(toCheck.getOrderNum() == toRemove.getOrderNum()){
                index = i;
                break;
            }
        }
        allOrders.remove(index);
        writeFile(allOrders, toRemove.getDate());
    }

    private String convertDateToPath(LocalDate date) {

        String fileName = "Orders_" + date.format(FORMATTER) + ".txt";
        Path filePath = Paths.get(DIRECTORY, fileName);
        return filePath.toString();
    }

}
