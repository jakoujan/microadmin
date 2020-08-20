/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author oscardanielrangelmartinez
 */
@Entity
@Table(name = "PRODUCT_ORDER", catalog = "DB", schema = "PUBLIC")
public class ProductOrder implements Serializable {
    
    protected ProductOrderPK productOrderPK;
    private Order order;
    private Product product;
    private BigDecimal quantity;

    public ProductOrder() {
        this.productOrderPK = new ProductOrderPK();
    }
    
    
    @EmbeddedId
    public ProductOrderPK getProductOrderPK() {
        return productOrderPK;
    }

    public void setProductOrderPK(ProductOrderPK productOrderPK) {
        this.productOrderPK = productOrderPK;
    }
    
    @JoinColumn(name = "order", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
    
    @JoinColumn(name = "product",  referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
    @Column(name = "QUANTITY", length = 8, precision = 2)
    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }
    
    
    
}
