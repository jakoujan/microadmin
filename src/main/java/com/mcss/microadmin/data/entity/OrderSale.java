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
public class OrderSale implements Serializable {
    
    private Order order;
    private Sales sale;
    
    
    @JoinColumn(name = "ORDER", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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
