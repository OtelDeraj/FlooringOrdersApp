/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dto;

import java.math.BigDecimal;

/**
 *
 * @author Isaia
 */
public class FMTax {
    private String stateAbv;
    private String stateName;
    private BigDecimal stateTaxRate;

    public FMTax(String abv, String name, BigDecimal tax){
        this.stateAbv = abv;
        this.stateName = name;
        this.stateTaxRate = tax;
    }    

    public FMTax(String stateAbv, BigDecimal taxRate) {
        this.stateAbv = stateAbv;
        this.stateTaxRate = taxRate;
    }
    
    /**
     * @return the stateAbv
     */
    public String getStateAbv() {
        return stateAbv;
    }

    /**
     * @param stateAbv the stateAbv to set
     */
    public void setStateAbv(String stateAbv) {
        this.stateAbv = stateAbv;
    }

    /**
     * @return the stateName
     */
    public String getStateName() {
        return stateName;
    }

    /**
     * @param stateName the stateName to set
     */
    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    /**
     * @return the stateTaxRate
     */
    public BigDecimal getStateTaxRate() {
        return stateTaxRate;
    }

    /**
     * @param stateTaxRate the stateTaxRate to set
     */
    public void setStateTaxRate(BigDecimal stateTaxRate) {
        this.stateTaxRate = stateTaxRate;
    }
}
