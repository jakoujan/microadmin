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
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author oscardanielrangelmartinez
 */
public class Sales implements Serializable{
   
    private Integer id;
    private Date sale_date;
    private BigDecimal total_amount;
    private BigDecimal quantity;

    public Sales(Date sale_date, BigDecimal total_amount, BigDecimal quantity) {
        this.sale_date = sale_date;
        this.total_amount = total_amount;
        this.quantity = quantity;
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
    
    @Column(name = "SALE_DATE")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getSale_date() {
        return sale_date;
    }

    public void setSale_date(Date sale_date) {
        this.sale_date = sale_date;
    }
    
    @Column(name = "TOTAL_AMOUNT", length = 8, precision = 2)
    public BigDecimal getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(BigDecimal total_amount) {
        this.total_amount = total_amount;
    }
    
    @Column(name = "QUANTITY", length = 8, precision = 2)
    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }
    
    
    
}
