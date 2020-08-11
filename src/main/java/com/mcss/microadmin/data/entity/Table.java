/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;

/**
 *
 * @author oscardanielrangelmartinez
 */
@Entity
@javax.persistence.Table(name = "TABLE_CHAIR", schema = "PUBLIC", catalog = "DB")
public class Table implements Serializable {
    
    private Integer id;
    private String name;
    private Integer places;
    private Boolean active;

    public Table(String name, Integer places, Boolean active) {
        this.name = name;
        this.places = places;
        this.active = active;
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
    
    @Column(name = "NAME", length = 120)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name = "PLACES", nullable = false)
    public Integer getPlaces() {
        return places;
    }

    public void setPlaces(Integer places) {
        this.places = places;
    }
    
    @Column(name = "ACTIVE", length = 1)
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
    
    
    
    
    
}