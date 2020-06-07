/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.controller;

import com.sg.flooringmastery.exceptions.OrderDaoException;
import com.sg.flooringmastery.exceptions.TaxDaoException;
import com.sg.flooringmastery.service.FMService;
import com.sg.flooringmastery.ui.FMView;

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
    //TODO change dao delimiters from :: to ,

    public void run() {
        boolean keepGoing = true;
        while (keepGoing) {
            // double check to see if any information should be displayed above menu
            try {
                switch (view.getMenuSelection()) {
                    case 1: // this path should display all orders after prompting for a date from user
                        view.displayAllOrdersByDate(serv.getOrdersForDate(view.getOrderDate()));
                        break;
                    case 2: // prompt the user for relevant info to add an order to the program
                        serv.confirmAddOrder(view.displayFinalOrderForConfirmation(serv.calculateOrderDetails(
                                view.createNewOrder(serv.getAllProducts(), serv.getAllStates()))));
                        break;
                    case 3: // edit an existing order, search by date and then order num
                        serv.editOrder(view.editOrderDetails(serv.getOrder(view.getOrderDate(),
                                view.getOrderNum()), serv.getAllProducts(), serv.getAllStates()));
                        break;
                    case 4: // remove existing order, search by date and then order num
                        serv.confirmRemoval(view.displayOrderForRemoval(serv.getOrder(view.getOrderDate(), view.getOrderNum())));
                        break;
                    case 5:
                        view.displayExitMessage();
                        keepGoing = false;
                        break;
                }
            } catch (OrderDaoException | TaxDaoException ex) {
                view.displayErrorMessage(ex.getMessage());
            }
        }
    }

}
