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
@javax.persistence.Table(name = "CASH_ACTION", schema = "PUBLIC", catalog = "DB")
public class CashAction implements Serializable {

    private Integer id;
    private Date action_date;
    private User cashier;
    private BigDecimal initial_amount;
    private BigDecimal final_amount;
    private Integer action_status;
    private Boolean active;
 
    public CashAction() {
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

    @Column(name = "ACTION_DATE")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getAction_date() {
        return action_date;
    }

    public void setAction_date(Date action_date) {
        this.action_date = action_date;
    }


    @JoinColumn(name = "CASHIER", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    public User getCashier() {
        return cashier;
    }

    public void setCashier(User cashier) {
        this.cashier = cashier;
    }

    @Column(name = "INITIAL_AMOUNT", length = 8, precision = 2)
    public BigDecimal getInitial_amount() {
        return initial_amount;
    }

    public void setInitial_amount(BigDecimal initial_amount) {
        this.initial_amount = initial_amount;
    }
    
    @Column(name = "FINAL_AMOUNT", length = 8, precision = 2)
    public BigDecimal getFinal_amount() {
        return final_amount;
    }

    public void setFinal_amount(BigDecimal final_amount) {
        this.final_amount = final_amount;
    }

    @Column(name = "ACTION_STATUS")
    public Integer getAction_status() {
        return action_status;
    }

    public void setAction_status(Integer action_status) {
        this.action_status = action_status;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
    
    

}
