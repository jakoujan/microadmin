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
public class Retail implements Serializable {
    
    private Integer id;
    private Date retail_date;
    private User cashier;
    private PaymentMethod payment_method;
    private SaleStatus status;
    private BigDecimal total_amount;

    public Retail(Date retail_date, User cashier, PaymentMethod payment_method, SaleStatus status, BigDecimal total_amount) {
        this.retail_date = retail_date;
        this.cashier = cashier;
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
    
    @Column(name = "RETAIL_DATE")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getRetail_date() {
        return retail_date;
    }

    public void setRetail_date(Date retail_date) {
        this.retail_date = retail_date;
    }
    
    @JoinColumn(name = "CASHIER", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    public User getCashier() {
        return cashier;
    }

    public void setCashier(User cashier) {
        this.cashier = cashier;
    }
    
    @JoinColumn(name = "PAYMENT_METHOD", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    public PaymentMethod getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(PaymentMethod payment_method) {
        this.payment_method = payment_method;
    }

    public SaleStatus getStatus() {
        return status;
    }
    
    @JoinColumn(name = "STATUS", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
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
