/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.view;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SALES_REPORT_VEW", schema = "PUBLIC", catalog = "DB")
public class SaleReportView implements Serializable {

    private Integer id;
    private Date saleDate;
    private BigDecimal totalAmount;
    private BigDecimal quantity;
    private Integer orderComand;
    private Integer cashier;
    private String cashierName;

    public SaleReportView() {
    }

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "SALE_DATE")
    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    @Column(name = "TOTAL_AMOUNT")
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Column(name = "QUANTITY")
    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    @Column(name = "ORDER_COMAND")
    public Integer getOrderComand() {
        return orderComand;
    }

    public void setOrderComand(Integer orderComand) {
        this.orderComand = orderComand;
    }
    
    @Column(name = "CASHIER")
    public Integer getCashier() {
        return cashier;
    }

    public void setCashier(Integer cashier) {
        this.cashier = cashier;
    }
    
    @Column(name = "CASHIER_NAME")
    public String getCashierName() {
        return cashierName;
    }

    public void setCashierName(String cashierName) {
        this.cashierName = cashierName;
    }
   

  
    
    
}
