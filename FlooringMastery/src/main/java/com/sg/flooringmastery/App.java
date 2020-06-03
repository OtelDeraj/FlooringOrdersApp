/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery;

import com.sg.flooringmastery.controller.FMController;
import com.sg.flooringmastery.service.FMService;
import com.sg.flooringmastery.ui.FMView;

/**
 *
 * @author Isaia
 */
public class App {

    public static void main(String[] args) {
        FMView view = new FMView();
        FMService serv = new FMService();
        FMController controller = new FMController(view, serv);
        controller.run();
    }

}
