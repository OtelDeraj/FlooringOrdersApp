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
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

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

    public int getDisplayPreference() {
        io.print("1. Display Single");
        io.print("2. Display Multiple ");

        return io.readInt("Please select one of the options above: ", 1, 2);
    }

    public FMOrder createNewOrder(List<FMProduct> productList, List<FMTax> stateList) {
        FMOrder toReturn = new FMOrder();
        FMProduct pDetails = new FMProduct();
        toReturn.setDate(getFutureDate("Please enter the desired date of service: "));
        String name = "";
        while (name.isEmpty()) {
            name = io.readString("Please enter a name for the order: ");
        }
        toReturn.setCustomerName(name);
        displayAllStates(stateList);
        toReturn.setStateAbv(getUsersStateTaxChoice(io.readString("Plese enter a state "
                + "abbreviation from the list above: "), stateList));
        displayAllProducts(productList);
        String material = null;
        while (material == null) {
            material = getUsersMaterialChoice(io.readString("Please enter a material "
                    + "from the list above: "), productList);
        }
        pDetails.setMaterial(material);
        toReturn.setProduct(pDetails);
        toReturn.setArea(io.readBigDecimal("Please enter the square footage of the order."
                + " Sq Footage must be 100ft or greater", new BigDecimal("100"), new BigDecimal(Integer.MAX_VALUE)));

        return toReturn;
    }

    public LocalDate getOrderDate() {
        return io.readDate("Please enter the date of the order: ");
    }

    public int getOrderNum() {
        return io.readInt("Please enter order number: ");
    }

    public FMOrder editOrderDetails(FMOrder toEdit, List<FMProduct> productList, List<FMTax> stateList) {

        toEdit.setCustomerName(io.editString("Enter a new Customer Name or press Enter to keep "
                + "[" + toEdit.getCustomerName() + "]: ", toEdit.getCustomerName()));
        displayAllStates(stateList);
        String abv = null;
        while (abv == null) {
            abv = getUsersStateTaxChoice(io.editString("Enter a new State abbreviation or press Enter to keep "
                    + "[" + toEdit.getTaxRate().getStateAbv() + "]: ", toEdit.getTaxRate().getStateAbv()), stateList);
        }
        toEdit.setStateAbv(abv);
        displayAllProducts(productList);
        String material = null;
        while (material == null) {
            material = getUsersMaterialChoice(io.editString("Enter a new product material or press Enter to keep "
                    + "[" + toEdit.getProduct().getMaterial() + "]: ", toEdit.getProduct().getMaterial()), productList);
        }
        toEdit.getProduct().setMaterial(material);
        toEdit.setArea(io.editBigDecimal("Enter a new area in sq feet for the order or press Enter to keep "
                + "[" + toEdit.getArea() + "]: ", toEdit.getArea(), new BigDecimal("100"), new BigDecimal(Integer.MAX_VALUE)));

        return toEdit;
    }

    private void displayAllProducts(List<FMProduct> allProducts) {
        io.print("Material, Cost Per Sq Foot, Labor Cost Per Sq Foot: ");
        for (FMProduct p : allProducts) {
            io.print(p.getMaterial() + ", " + p.getCostPerSqFt() + ", " + p.getLaborCostPerSqFt());
        }
    }

    private void displayAllStates(List<FMTax> allStates) {
        io.print("State Abv, State Name, State Tax Rate: ");
        for (FMTax t : allStates) {
            io.print(t.getStateAbv() + ", " + t.getStateName() + ", " + t.getStateTaxRate());
        }
    }

    public void displayOrder(FMOrder order) {
        io.print("Order Date: " + order.getDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        io.print("Customer Name: " + order.getCustomerName());
        io.print("State: " + order.getTaxRate().getStateAbv());
        io.print("State Tax Rate: " + order.getTaxRate().getStateTaxRate());
        io.print("Material: " + order.getProduct().getMaterial());
        io.print("Area: " + order.getArea());
        io.print("Material Cost: " + order.getMaterialCost());
        io.print("Labor Cost: " + order.getLaborCost());
        io.print("Sales Tax: " + order.getSalesTax());
        io.print("Total: " + order.getTotalCost());
    }

    public FMOrder displayForAddConfirmation(FMOrder order) {
        FMOrder toReturn = null;
        displayOrder(order);
        String confirm = null;
        boolean validAnswer = false;
        while (!validAnswer) {
            confirm = io.readString("Confirm order? Type Yes or No.");
            if (confirm.equalsIgnoreCase("yes")) {
                toReturn = order;
                validAnswer = true;
            } else if (confirm.equalsIgnoreCase("no")) {
                validAnswer = true;
            }
        }
        return toReturn;
    }

    public FMOrder displayForRemovalConfirmation(FMOrder order) {
        FMOrder toReturn = null;
        displayOrder(order);
        String confirm = null;
        boolean validAnswer = false;
        while (!validAnswer) {
            confirm = io.readString("Remove Order? Type Yes or No.");
            if (confirm.equalsIgnoreCase("yes")) {
                toReturn = order;
                validAnswer = true;
            } else if (confirm.equalsIgnoreCase("no")) {
                validAnswer = true;
            }
        }
        return toReturn;
    }
    
    public FMOrder displayForEditConfirmation(FMOrder order) {
        FMOrder toReturn = null;
        displayOrder(order);
        String confirm = null;
        boolean validAnswer = false;
        while (!validAnswer) {
            confirm = io.readString("Confirm Edit? Type Yes or No.");
            if (confirm.equalsIgnoreCase("yes")) {
                toReturn = order;
                validAnswer = true;
            } else if (confirm.equalsIgnoreCase("no")) {
                validAnswer = true;
            }
        }
        return toReturn;
    }

    public void displayAllOrdersByDate(List<FMOrder> allOrders) {
        io.print("Order #, Customer Name, State, Material, Area, Total");
        for (FMOrder o : allOrders) {
            io.print("#" + o.getOrderNum() + " :: " + o.getCustomerName() + " :: "
                    + o.getTaxRate().getStateAbv() + " :: " + o.getProduct().getMaterial() + " :: "
                    + o.getArea() + " :: " + o.getTotalCost());
        }
    }

    public void displayExitMessage() {
        io.print("THANK YOU FOR CHOOSING FLOOR MASTERS LTD. \n"
                + "FOR ALL YOUR FLOORING NEEDS, FLOOR MASTERS IS THERE!");
    }

    public void displayErrorMessage(String message) {
        io.print(message);
    }

    private String getUsersStateTaxChoice(String input, List<FMTax> stateList) {
        String stateAbv = null;
        List<FMTax> taxList = stateList.stream().filter(t -> t.getStateAbv().equalsIgnoreCase(input)).collect(Collectors.toList());
        if (taxList.size() > 0) {
            stateAbv = taxList.get(0).getStateAbv();
        }
        return stateAbv;
    }

    private String getUsersMaterialChoice(String input, List<FMProduct> productList) {
        String material = null;

        List<FMProduct> products = productList.stream().filter(p -> p.getMaterial().equalsIgnoreCase(input)).collect(Collectors.toList());
        if (products.size() > 0) {
            material = products.get(0).getMaterial();
        }

        return material;

    }

    private LocalDate getFutureDate(String prompt) {
        boolean validInput = false;
        LocalDate toCheck = null;
        while (!validInput) {
            toCheck = io.readDate(prompt);
            if (toCheck.compareTo(LocalDate.now()) >= 0) {
                validInput = true;
            }
        }
        return toCheck;
    }

}
