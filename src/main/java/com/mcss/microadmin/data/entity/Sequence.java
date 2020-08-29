/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "SEQUENCES", schema = "PUBLIC", catalog = "DB"
)
public class Sequence implements java.io.Serializable {

    private String id;
    private String description;
    private Integer sequence;

    public Sequence() {
    }

    @Id
    @Column(name = "ID", unique = true, nullable = false, length = 25)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "DESCRIPTION", nullable = false, length = 120)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "SEQUENCE", nullable = false)
    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

}
