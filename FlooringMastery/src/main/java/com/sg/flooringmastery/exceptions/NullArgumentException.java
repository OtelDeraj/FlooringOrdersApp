/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.exceptions;

/**
 *
 * @author Isaia
 */
public class NullArgumentException extends Exception {
    
    public NullArgumentException(String message){
        super(message);
    }
    
    public NullArgumentException(String message, Throwable cause){
        super(message, cause);
    }
}
