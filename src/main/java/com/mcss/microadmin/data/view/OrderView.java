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
@Table(name = "ORDER_VIEW", schema = "PUBLIC", catalog = "DB")
public class OrderView implements Serializable {

    private Integer id;
    private String table;
    private String responsible;
    private BigDecimal totalAmount;
    private Integer status;
    private String orderNameStatus;

    public OrderView() {
    }

    public OrderView(String table, String responsible) {
        this.table = table;
        this.responsible = responsible;
    }

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "TABLE_NAME")
    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    @Column(name = "RESPONSIBLE")
    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    @Column(name = "TOTAL_AMOUNT")
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Column(name = "STATUS")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOrderNameStatus() {
        return orderNameStatus;
    }

    public void setOrderNameStatus(String orderNameStatus) {
        this.orderNameStatus = orderNameStatus;
    }
    
    

}
