/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OrderSaleId implements Serializable {

    private int order;
    private int sale;

    public OrderSaleId() {
    }

    public OrderSaleId(int order, int sale) {
        this.order = order;
        this.sale = sale;
    }

    @Column(name = "ORDER", nullable = false)
    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Column(name = "SALE", nullable = false)
    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

}
