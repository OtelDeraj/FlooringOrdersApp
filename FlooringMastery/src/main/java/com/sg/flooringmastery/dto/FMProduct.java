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
public class FMProduct {
    private String material;
    private BigDecimal costPerSqFt;
    private BigDecimal laborCostPerSqFt;

    /**
     * @return the material
     */
    public String getMaterial() {
        return material;
    }

    /**
     * @param material the material to set
     */
    public void setMaterial(String material) {
        this.material = material;
    }

    /**
     * @return the costPerSqFt
     */
    public BigDecimal getCostPerSqFt() {
        return costPerSqFt;
    }

    /**
     * @param costPerSqFt the costPerSqFt to set
     */
    public void setCostPerSqFt(BigDecimal costPerSqFt) {
        this.costPerSqFt = costPerSqFt;
    }

    /**
     * @return the laborCostPerSqFt
     */
    public BigDecimal getLaborCostPerSqFt() {
        return laborCostPerSqFt;
    }

    /**
     * @param laborCostPerSqFt the laborCostPerSqFt to set
     */
    public void setLaborCostPerSqFt(BigDecimal laborCostPerSqFt) {
        this.laborCostPerSqFt = laborCostPerSqFt;
    }
}
