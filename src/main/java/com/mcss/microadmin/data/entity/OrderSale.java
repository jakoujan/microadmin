/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.entity;

import java.io.Serializable;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@javax.persistence.Table(name = "ORDER_SALE")
public class OrderSale implements Serializable {

    private OrderSaleId id;
    private Order order;
    private Sale sale;

    public OrderSale() {
    }

    public OrderSale(Order order, Sale sale) {
        this.order = order;
        this.sale = sale;
    }

    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "order", column = @Column(name = "ORDER_COMAND", nullable = false)),
        @AttributeOverride(name = "sale", column = @Column(name = "SALE", nullable = false))})
    public OrderSaleId getId() {
        return id;
    }

    public void setId(OrderSaleId id) {
        this.id = id;
    }

    @JoinColumn(name = "ORDER_COMAND", nullable = false, insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @JoinColumn(name = "SALE", nullable = false, insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

}
