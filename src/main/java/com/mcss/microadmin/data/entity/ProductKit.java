/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mcss.microadmin.data.converter.KitItemsConverter;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT_KIT", catalog = "DB", schema = "PUBLIC")
public class ProductKit implements Serializable {

    private static final long serialVersionUID = 1L;

    private Product product;
    private List<ItemKit> items;

    public ProductKit() {
    }

    @Id
    @JsonIgnore
    @JoinColumn(name = "product", nullable = false, referencedColumnName = "id")
    @OneToOne()
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Convert(converter = KitItemsConverter.class)
    @Column(name = "PRODUCTS", columnDefinition = "text")
    public List<ItemKit> getItems() {
        return items;
    }

    public void setItems(List<ItemKit> items) {
        this.items = items;
    }

}
