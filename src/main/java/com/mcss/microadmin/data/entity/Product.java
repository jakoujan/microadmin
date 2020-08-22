/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT", schema = "PUBLIC", catalog = "DB")
public class Product implements Serializable {

    private Integer id;
    private String barcode;
    private Brand brand;
    private Unit unit;
    private String description;
    private String longDescription;
    private Section section;
    private TaxType taxType;
    private BigDecimal retailPrice;
    private BigDecimal supplierPrice;
    private BigDecimal promoPrice;
    private boolean promotion;
    private BigDecimal minimumStock;
    private boolean active;
    private ProductType type;
    private Set<ProductKit> kits;
    private Set<ProductKit> kitProducts;

    public Product() {
    }

    public Product(String barcode, Brand brand, Unit unit, String description, String longDescription, Section section, TaxType taxType, BigDecimal retailPrice, BigDecimal supplierPrice, BigDecimal promoPrice, boolean promotion, BigDecimal minimumStock, boolean active, ProductType type, Flavor flavor) {
        this.barcode = barcode;
        this.brand = brand;
        this.unit = unit;
        this.description = description;
        this.longDescription = longDescription;
        this.section = section;
        this.taxType = taxType;
        this.retailPrice = retailPrice;
        this.supplierPrice = supplierPrice;
        this.promoPrice = promoPrice;
        this.promotion = promotion;
        this.minimumStock = minimumStock;
        this.active = active;
        this.type = type;
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

    @Column(name = "BARCODE", length = 60, unique = true)
    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    @JoinColumn(name = "BRAND", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @JoinColumn(name = "UNIT", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    @Column(name = "DESCRIPTION", length = 120)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "LONG_DESCRIPTION")
    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    @JoinColumn(name = "SECTION", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    @JoinColumn(name = "TAX_TYPE", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    public TaxType getTaxType() {
        return taxType;
    }

    public void setTaxType(TaxType taxType) {
        this.taxType = taxType;
    }

    @Column(name = "RETAIL_PRICE", length = 8, precision = 2)
    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    @Column(name = "SUPPLIER_PRICE", length = 8, precision = 2)
    public BigDecimal getSupplierPrice() {
        return supplierPrice;
    }

    public void setSupplierPrice(BigDecimal supplierPrice) {
        this.supplierPrice = supplierPrice;
    }

    @Column(name = "PROMO_PRICE", length = 8, precision = 2)
    public BigDecimal getPromoPrice() {
        return promoPrice;
    }

    public void setPromoPrice(BigDecimal promoPrice) {
        this.promoPrice = promoPrice;
    }

    @Column(name = "PROMOTION")
    public boolean isPromotion() {
        return promotion;
    }

    public void setPromotion(boolean promotion) {
        this.promotion = promotion;
    }

    @Column(name = "MINIMUM_STOCK")
    public BigDecimal getMinimumStock() {
        return minimumStock;
    }

    public void setMinimumStock(BigDecimal minimumStock) {
        this.minimumStock = minimumStock;
    }

    @Column(name = "ACTIVE")
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @JoinColumn(name = "TYPE", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "kit", orphanRemoval = true)
    public Set<ProductKit> getKits() {
        return kits;
    }

    public void setKits(Set<ProductKit> kits) {
        this.kits = kits;
    }
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product", orphanRemoval = true)
    public Set<ProductKit> getKitProducts() {
        return kitProducts;
    }

    public void setKitProducts(Set<ProductKit> kitProducts) {
        this.kitProducts = kitProducts;
    }

}
