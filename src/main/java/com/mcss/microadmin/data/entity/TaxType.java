/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author edgar
 */
@Entity
@Table(name = "TAX_TYPE", schema = "PUBLIC", catalog = "DB")
public class TaxType implements Serializable {

    private Integer id;
    private String taxtType;
    private String taxtTypeDescription;
    private BigDecimal percentage;
    private boolean active;

    public TaxType() {
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

    @Column(name = "TAX_TYPE", length = 60)
    public String getTaxtType() {
        return taxtType;
    }

    public void setTaxtType(String taxtType) {
        this.taxtType = taxtType;
    }

    @Column(name = "TAX_TYPE_DESCRIPTION", length = 60)
    public String getTaxtTypeDescription() {
        return taxtTypeDescription;
    }

    public void setTaxtTypeDescription(String taxtTypeDescription) {
        this.taxtTypeDescription = taxtTypeDescription;
    }

    @Column(name = "PERCENTAGE")
    public BigDecimal getPercentage() {
        return percentage;
    }

    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }

    @Column(name = "ACTIVE")
    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
