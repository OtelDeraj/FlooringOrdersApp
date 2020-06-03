/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.ui;

import com.sg.flooringmastery.dto.FMOrder;
import java.util.List;

/**
 *
 * @author Isaia
 */
public class FMView {
    
    public UserIO io = new UserIOConsoleImpl();
    // TODO: remember to remove this public tag on UserIO after all paths are implemented  <------ VERY IMPORTANT
    public int getMenuSelection(){
        io.print("::: FLOORING MENU ::: ");
        io.print("1. Display Orders");
        io.print("2. Add an Order");
        io.print("3. Edit an Order");
        io.print("4. Remove an Order");
        io.print("5. Quit");
        
        return io.readInt("Please select one of the options above: ", 1, 5);
    }
    
    public void displayAllOrders(List<FMOrder> allOrders){
        io.print("Order #, Customer Name");
        for(FMOrder o : allOrders){
            io.print("#" + o.getOrderNum() + " " + o.getCustomerName());
        }
    }

    public void displayExitMessage() {
        io.print("THANK YOU FOR CHOOSING FLOOR MASTERS LTD. \n"
                + "FOR ALL YOUR FLOORING NEEDS, FLOOR MASTERS IS THERE!");
    }
        
    
}
