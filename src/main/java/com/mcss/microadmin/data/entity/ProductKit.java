/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT_KIT", catalog = "DB", schema = "PUBLIC")
public class ProductKit implements Serializable {

    private static final long serialVersionUID = 1L;

    protected ProductKitPK productKitPK;
    private Product kit;
    private Product product;
    private BigDecimal quantity;

    public ProductKit() {
    }

    public ProductKit(ProductKitPK productKitPK) {
        this.productKitPK = productKitPK;
    }

    public ProductKit(ProductKitPK productKitPK, BigDecimal quantity) {
        this.productKitPK = productKitPK;
        this.quantity = quantity;
    }

    public ProductKit(int kit, int product) {
        this.productKitPK = new ProductKitPK(kit, product);
    }

    @EmbeddedId
    public ProductKitPK getProductKitPK() {
        return productKitPK;
    }

    public void setProductKitPK(ProductKitPK productKitPK) {
        this.productKitPK = productKitPK;
    }

   

    @JoinColumn(name = "product", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne()
    public Product getKit() {
        return kit;
    }

    public void setKit(Product kit) {
        this.kit = kit;
    }

    @JoinColumn(name = "kit", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productKitPK != null ? productKitPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductKit)) {
            return false;
        }
        ProductKit other = (ProductKit) object;
        if ((this.productKitPK == null && other.productKitPK != null) || (this.productKitPK != null && !this.productKitPK.equals(other.productKitPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mcss.microadmin.data.entity.ProductKit[ productKitPK=" + productKitPK + " ]";
    }

}
