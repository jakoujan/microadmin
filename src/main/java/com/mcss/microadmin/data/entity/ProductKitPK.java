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
 * @author edgar
 */
@Embeddable
public class ProductKitPK implements Serializable {

    private int kit;
    private int product;

    public ProductKitPK() {
    }

    public ProductKitPK(int kit, int product) {
        this.kit = kit;
        this.product = product;
    }

    @Basic(optional = false)
    @Column(nullable = false)
    public int getKit() {
        return kit;
    }

    public void setKit(int kit) {
        this.kit = kit;
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
        hash += (int) kit;
        hash += (int) product;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductKitPK)) {
            return false;
        }
        ProductKitPK other = (ProductKitPK) object;
        if (this.kit != other.kit) {
            return false;
        }
        if (this.product != other.product) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mcss.microadmin.data.entity.ProductKitPK[ kit=" + kit + ", product=" + product + " ]";
    }

}
