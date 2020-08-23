/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mcss.microadmin.data.converter.KitItemsConverter;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT_KIT", catalog = "DB", schema = "PUBLIC")
public class ProductKit implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Product product;
    private Set<ItemKit> items;

    public ProductKit() {
    }

    @Id
    @Column(name = "PRODUCT", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonIgnore
    @JoinColumn(name = "product", nullable = false)
    @OneToOne()
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Convert(converter = KitItemsConverter.class)
    @Column(name = "PRODUCTS", columnDefinition = "text")
    public Set<ItemKit> getItems() {
        return items;
    }

    public void setItems(Set<ItemKit> items) {
        this.items = items;
    }

}
