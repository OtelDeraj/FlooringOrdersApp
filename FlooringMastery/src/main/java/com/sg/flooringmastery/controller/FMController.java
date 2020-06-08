/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.controller;

import com.sg.flooringmastery.dto.FMOrder;
import com.sg.flooringmastery.exceptions.InvalidInputException;
import com.sg.flooringmastery.exceptions.InvalidOrderDateException;
import com.sg.flooringmastery.exceptions.NullArgumentException;
import com.sg.flooringmastery.exceptions.OrderDaoException;
import com.sg.flooringmastery.exceptions.ProductDaoException;
import com.sg.flooringmastery.exceptions.TaxDaoException;
import com.sg.flooringmastery.service.FMService;
import com.sg.flooringmastery.ui.FMView;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Isaia
 */
public class FMController {

    private final FMView view;
    private final FMService serv;

    public FMController(FMView view, FMService serv) {
        this.view = view;
        this.serv = serv;
    }
    

    public void run() {
        boolean keepGoing = true;
        while (keepGoing) {
            
            try {
                switch (view.getMenuSelection()) {
                    case 1: // this path should display all orders after prompting for a date from user
                        view.displayAllOrdersByDate(
                                serv.getOrdersForDate(
                                        view.getOrderDate()));
                        break;
                    case 2: // prompt the user for relevant info to add an order to the program
                        FMOrder toAdd = view.displayForAddConfirmation(
                                serv.calculateOrderDetails(
                                        view.createNewOrder(serv.getAllProducts(), serv.getAllStates())));
                        if (toAdd != null) {
                            serv.addOrder(toAdd);
                        }
                        break;
                    case 3: // edit an existing order, search by date and then order num
                        FMOrder toEdit = view.displayForEditConfirmation(
                                serv.calculateOrderDetails(
                                        view.editOrderDetails(
                                                serv.getOrder(
                                                        view.getOrderDate(),view.getOrderNum()), serv.getAllProducts(), serv.getAllStates())));
                        if (toEdit != null) {
                            serv.editOrder(toEdit);
                        }
                        break;
                    case 4: // remove existing order, search by date and then order num
                        FMOrder toRemove = view.displayForRemovalConfirmation(
                                serv.getOrder(view.getOrderDate(), view.getOrderNum()));
                        if (toRemove != null) {
                            serv.removeOrder(toRemove);
                        }
                        break;
                    case 5:
                        view.displayExitMessage();
                        keepGoing = false;
                        break;
                }
            } catch (OrderDaoException | TaxDaoException | ProductDaoException 
                    | InvalidOrderDateException | NullArgumentException | InvalidInputException ex) {
                view.displayErrorMessage(ex.getMessage());
            }
        }
    } 

}
