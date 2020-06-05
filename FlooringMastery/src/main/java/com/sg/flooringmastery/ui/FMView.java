/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.ui;

import com.sg.flooringmastery.dto.FMOrder;
import com.sg.flooringmastery.dto.FMProduct;
import com.sg.flooringmastery.dto.FMTax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Isaia
 */
public class FMView {

    public UserIO io = new UserIOConsoleImpl();

    // TODO: remember to remove this public tag on UserIO after all paths are implemented  <------ VERY IMPORTANT
    public int getMenuSelection() {
        io.print("::: FLOORING MENU ::: ");
        io.print("1. Display Orders");
        io.print("2. Add an Order");
        io.print("3. Edit an Order");
        io.print("4. Remove an Order");
        io.print("5. Quit");

        return io.readInt("Please select one of the options above: ", 1, 5);
    }

    public FMOrder createNewOrder(List<FMProduct> productList, List<FMTax> stateList) {
        FMOrder toReturn = new FMOrder();
        FMProduct pDetails = new FMProduct();
        toReturn.setDate(io.readDate("Please enter the desired date of service: "));
        toReturn.setCustomerName(io.readString("Please enter a name for the order: "));
        displayAllStates(stateList);
        toReturn.setState(io.readString("Please enter your state abbreviation: "));
        displayAllProducts(productList);
        pDetails.setMaterial(io.readString("What material would you like "));
        toReturn.setProduct(pDetails);
        toReturn.setArea(io.readBigDecimal("Please enter the square footage of the order."
                + " Sq Footage must be 100ft or greater", new BigDecimal("100"), new BigDecimal(Integer.MAX_VALUE)));

        return toReturn;
    }

    public LocalDate getOrderDate() {
        return io.readDate("Please enter the date of the order: ");
    }

    private void displayAllProducts(List<FMProduct> allProducts) {
        io.print("Material, Cost Per Sq Foot, Labor Cost Per Sq Foot: ");
        for (FMProduct p : allProducts) {
            io.print(p.getMaterial() + ", " + p.getCostPerSqFt() + ", " + p.getLaborCostPerSqFt());
        }
    }
    
    private void displayAllStates(List<FMTax> allStates){
        io.print("State Abv, State Name, State Tax Rate: ");
        for(FMTax t : allStates){
            io.print(t.getStateAbv() + ", " + t.getStateName() + ", " + t.getStateTaxRate());
        }
    }

    public FMOrder displayFinalOrderForConfirmation(FMOrder order) {
        FMOrder toReturn = null;
        io.print("Order Date: " + order.getDate());
        io.print("Customer Name: " + order.getCustomerName());
        io.print("State: " + order.getTaxRate().getStateAbv());
        io.print("State Tax Rate: " + order.getTaxRate().getStateTaxRate());
        io.print("Material: " + order.getProduct().getMaterial());
        io.print("Area: " + order.getArea());
        io.print("Material Cost: " + order.getMaterialCost());
        io.print("Labor Cost: " + order.getLaborCost());
        io.print("Sales Tax: " + order.getSalesTax());
        io.print("Total: " + order.getTotalCost());
        String confirm = null;
        boolean validAnswer = false;
        while(!validAnswer){
            confirm = io.readString("Confirm order. Type Yes or No.");
            if(confirm.equalsIgnoreCase("yes")){
                toReturn = order;
                validAnswer = true;
            } else if(confirm.equalsIgnoreCase("no")){
                validAnswer = true;
            }
        }
        return toReturn;
    }

    public void displayOrdersByDate(List<FMOrder> allOrders) {
        io.print("Order #, Customer Name");
        for (FMOrder o : allOrders) {
            io.print("#" + o.getOrderNum() + " " + o.getCustomerName());
        }
    }

    public void displayExitMessage() {
        io.print("THANK YOU FOR CHOOSING FLOOR MASTERS LTD. \n"
                + "FOR ALL YOUR FLOORING NEEDS, FLOOR MASTERS IS THERE!");
    }

    public void displayErrorMessage(String message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
