/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Oscar
 */
@Embeddable
public class ProductOrderPK implements Serializable {

    private int order;
    private int product;

    public ProductOrderPK() {
    }

    public ProductOrderPK(int order, int product) {
        this.order = order;
        this.product = product;
    }

    @Basic(optional = false)
    @Column(nullable = false)
    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Basic(optional = false)
    @Column(nullable = false)
    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) order;
        hash += (int) product;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductOrderPK)) {
            return false;
        }
        ProductOrderPK other = (ProductOrderPK) object;
        if (this.order != other.order) {
            return false;
        }
        if (this.product != other.product) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mcss.microadmin.data.entity.ProductOrderPK[ order=" + order + ", product=" + product + " ]";
    }

}
