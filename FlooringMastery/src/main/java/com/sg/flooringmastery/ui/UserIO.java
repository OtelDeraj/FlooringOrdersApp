/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.ui;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author Isaia
 */
public interface UserIO { // basic IO interface

    void print(String msg);

    double readDouble(String prompt);

    double readDouble(String prompt, double min, double max);

    float readFloat(String prompt);

    float readFloat(String prompt, float min, float max);

    int readInt(String prompt);

    int readInt(String prompt, int min, int max);

    long readLong(String prompt);

    long readLong(String prompt, long min, long max);

    String readString(String prompt);

    String editString(String prompt, String originalValue);
    
    BigDecimal readBigDecimal(String prompt);
    
    BigDecimal readBigDecimal(String prompt, BigDecimal min, BigDecimal max);
    
    BigDecimal editBigDecimal(String prompt, BigDecimal originalNumber, BigDecimal min, BigDecimal max);
    
    LocalDate readDate(String prompt);
    
    LocalDate editDate(String prompt, LocalDate original);
}
