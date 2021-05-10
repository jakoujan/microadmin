/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.view;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCTS_PREPARATION", schema = "PUBLIC", catalog = "DB")
public class ProductPreparation implements Serializable {

    private Integer id;
    private BigDecimal quantity;
    private String description;
    private String table;
    private String comment;
    private Integer status;
    private Integer section;

    public ProductPreparation() {
    }

    public ProductPreparation(BigDecimal quantity, String description, String table, String comment, Integer status) {
        this.quantity = quantity;
        this.description = description;
        this.table = table;
        this.comment = comment;
        this.status = status;
    }

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "QUANTITY")
    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "TABLE_NAME")
    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    @Column(name = "COMMENT")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Column(name = "STATUS")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column(name = "SECTION")
    public Integer getSection() {
        return section;
    }

    public void setSection(Integer section) {
        this.section = section;
    }
    
    

}
