/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery;

import com.sg.flooringmastery.controller.FMController;
import com.sg.flooringmastery.dao.ODao;
import com.sg.flooringmastery.dao.OrderDaoImpl;
import com.sg.flooringmastery.dao.PDao;
import com.sg.flooringmastery.dao.ProductDaoImpl;
import com.sg.flooringmastery.dao.TDao;
import com.sg.flooringmastery.dao.TaxDaoImpl;
import com.sg.flooringmastery.service.FMService;
import com.sg.flooringmastery.ui.FMView;
import java.time.LocalDate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Isaia
 */
public class App {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        
        FMController controller = ctx.getBean("controller", FMController.class);
        controller.run();
        
    }

}
