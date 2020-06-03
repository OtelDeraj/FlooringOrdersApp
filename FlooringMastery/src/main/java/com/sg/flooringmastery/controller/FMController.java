/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.controller;

import com.sg.flooringmastery.service.FMService;
import com.sg.flooringmastery.ui.FMView;

/**
 *
 * @author Isaia
 */
public class FMController {
    
    private final FMView view;
    private final FMService serv;
    
    public FMController(FMView view, FMService serv){
        this.view = view;
        this.serv = serv;
    }
    
    
    public void run(){
        boolean keepGoing = true;
        while(keepGoing){
            // double check to see if any information should be displayed above menu
            // try statement here eventually
            switch(view.getMenuSelection()){
                case 1:
                    view.displayAllOrders(serv.getAllOrders());
                    break;
                case 2:
                    view.io.print("ADD AN ORDER FEATURE NOT YET IMPLEMENTED");
                    break;
                case 3:
                    view.io.print("EDIT AN ORDER FEATURE NOT YET IMPLEMENTED");
                    break;
                case 4:
                    view.io.print("REMOVE AN ORDER FEATURE NOT YET IMPLEMENTED");
                    break;
                case 5:
                    view.displayExitMessage();
                    keepGoing = false;
                    break;
            }
            
        }
    }
    
}
