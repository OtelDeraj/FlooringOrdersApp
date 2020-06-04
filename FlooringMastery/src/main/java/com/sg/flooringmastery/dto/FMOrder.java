/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author Isaia
 */
public class FMOrder {
    
    
    //problems FMOrder properties
    //date in past      (addOrder service layer) 1
    //null              (add & edit service & dao layer) 4
    private LocalDate date; // TODO: be sure to figure out how dates fit into all of this.
    //bad orderNum      (editOrder service/dao) 2
    private int orderNum;
    //customerName null ()
    private String customerName;
    private FMTax taxRate;
    private FMProduct product;
    private BigDecimal area;
    private BigDecimal materialCost = (getProduct().getCostPerSqFt()
            .multiply(area));
    private BigDecimal laborCost = (getProduct().getLaborCostPerSqFt()
            .multiply(area));
    private BigDecimal salesTax = (materialCost.add(laborCost))
            .multiply(getTaxRate().getStateTaxRate().divide(new BigDecimal("100")));
    private BigDecimal totalCost = materialCost.add(laborCost.add(salesTax));

    
    public FMOrder(){
        
    }
    
    public FMOrder( int orderNum, String customerName, String state, BigDecimal taxRate,
            String productType, BigDecimal area, BigDecimal costPerSqFt, BigDecimal laborCostPerSqFt,
            BigDecimal materialCost, BigDecimal laborCost, BigDecimal tax, BigDecimal total) {
        this.orderNum = orderNum;
        this.customerName = customerName;
        this.taxRate.setStateName(state);
        this.taxRate.setStateTaxRate(taxRate);
        this.product.setMaterial(productType);
        this.area = area;
        this.product.setCostPerSqFt(costPerSqFt);
        this.product.setLaborCostPerSqFt(laborCostPerSqFt);
        this.materialCost = materialCost;
        this.laborCost = laborCost;
        this.salesTax = tax;
        this.totalCost = total;
    }

    /**
     * @return the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    /**
     * @return the orderNum
     */
    public int getOrderNum() {
        return orderNum;
    }

    /**
     * @param orderNum the orderNum to set
     */
    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * @return the customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName the customerName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return the taxRate
     */
    public FMTax getTaxRate() {
        return taxRate;
    }

    /**
     * @param taxRate the taxRate to set
     */
    public void setTaxRate(FMTax taxRate) {
        this.taxRate = taxRate;
    }

    /**
     * @return the product
     */
    public FMProduct getProduct() {
        return product;
    }

    /**
     * @param product the product to set
     */
    public void setProduct(FMProduct product) {
        this.product = product;
    }

    /**
     * @return the area
     */
    public BigDecimal getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(BigDecimal area) {
        this.area = area;
    }

    /**
     * @return the materialCost
     */
    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    /**
     * @return the laborCost
     */
    public BigDecimal getLaborCost() {
        return laborCost;
    }

    /**
     * @return the tax
     */
    public BigDecimal getSalesTax() {
        return salesTax;
    }

    /**
     * @return the totalCost
     */
    public BigDecimal getTotalCost() {
        return totalCost;
    }

    
}
