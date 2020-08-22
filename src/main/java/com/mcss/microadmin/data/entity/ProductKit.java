/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT_KIT", catalog = "DB", schema = "PUBLIC")
public class ProductKit implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private Product kit;
    private Product product;
    private BigDecimal quantity;

    public ProductKit() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

   
    @JsonIgnore
    @JoinColumn(name = "product", referencedColumnName = "ID", nullable = false)
    @ManyToOne()
    public Product getKit() {
        return kit;
    }

    public void setKit(Product kit) {
        this.kit = kit;
    }

    @JoinColumn(name = "kit", referencedColumnName = "ID", nullable = false)
    @ManyToOne()
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
    @Basic(optional = false)
    @Column(name = "QUANTITY" ,nullable = false, precision = 8, scale = 2)
    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    } 
}
