/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.entity;

import java.io.Serializable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author oscardanielrangelmartinez
 */
public class RetailSale implements Serializable {
    
    private Retail retail;
    private Sales sale;
    
    @JoinColumn(name = "RETAIL", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    public Retail getRetail() {
        return retail;
    }

    public void setRetail(Retail retail) {
        this.retail = retail;
    }
    
    @JoinColumn(name = "SALE", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    public Sales getSale() {
        return sale;
    }

    public void setSale(Sales sale) {
        this.sale = sale;
    }
    
    
    
}
