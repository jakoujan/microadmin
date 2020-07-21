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
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author edgar
 */
@Entity
@Table(name = "STOCK", schema = "PUBLIC", catalog = "DB")
public class Stock implements Serializable {

    private Integer id;
    private Product product;
    private Store store;
    private BigDecimal currentStock;
    private Date lastAdded;

    public Stock() {
    }

    public Stock(Product product, Store store, BigDecimal currentStock, Date lastAdded) {
        this.product = product;
        this.store = store;
        this.currentStock = currentStock;
        this.lastAdded = lastAdded;
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

    @JoinColumn(name = "PRODUCT", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @JoinColumn(name = "STORE", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @Column(name = "CURRENT_STOCK")
    public BigDecimal getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(BigDecimal currentStock) {
        this.currentStock = currentStock;
    }

    @Column(name = "LAST_ADDED")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getLastAdded() {
        return lastAdded;
    }

    public void setLastAdded(Date lastAdded) {
        this.lastAdded = lastAdded;
    }

}
