/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author oscardanielrangelmartinez
 */
@Entity
@javax.persistence.Table(name = "BRAND", schema = "PUBLIC", catalog = "DB")
public class Order implements Serializable {
 
    private Integer id;
    private Date order_date;
    private String responsible;
    private User waiter;
    private User cashier;
    private Table table;
    private PaymentMethod payment_method;
    private SaleStatus status;
    private BigDecimal total_amount;

    public Order(Date order_date, String responsible, User waiter, User cashier, Table table, PaymentMethod payment_method, SaleStatus status, BigDecimal total_amount) {
        this.order_date = order_date;
        this.responsible = responsible;
        this.waiter = waiter;
        this.cashier = cashier;
        this.table = table;
        this.payment_method = payment_method;
        this.status = status;
        this.total_amount = total_amount;
    }
    
    
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    @Column(name = "ORDER_DATE")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }
    
    @Column(name = "RESPONSIBLE", length = 120)
    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }
    
    @JoinColumn(name = "WAITER", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    public User getWaiter() {
        return waiter;
    }

    public void setWaiter(User waiter) {
        this.waiter = waiter;
    }
    
    @JoinColumn(name = "CASHIER", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    public User getCashier() {
        return cashier;
    }

    public void setCashier(User cashier) {
        this.cashier = cashier;
    }
    
    @JoinColumn(name = "TABLE", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }
    
    @JoinColumn(name = "PAYMENT_METHOD", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    public PaymentMethod getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(PaymentMethod payment_method) {
        this.payment_method = payment_method;
    }
    
    @JoinColumn(name = "STATUS", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    public SaleStatus getStatus() {
        return status;
    }

    public void setStatus(SaleStatus status) {
        this.status = status;
    }
    
    @Column(name = "TOTAL_AMOUNT", length = 8, precision = 2)
    public BigDecimal getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(BigDecimal total_amount) {
        this.total_amount = total_amount;
    }
    
    
    
}
