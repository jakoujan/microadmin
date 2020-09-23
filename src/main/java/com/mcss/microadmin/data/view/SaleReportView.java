/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.view;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SALES_REPORT_VEW", schema = "PUBLIC", catalog = "DB")
public class SaleReportView implements Serializable {

    private int id;
    private Date sale_date;
    private BigDecimal total_amount;
    private BigDecimal quantity;
    
    public SaleReportView() {
    }

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    @Column(name = "SALE_DATE")
    public Date getSale_date() {
        return sale_date;
    }

    public void setSale_date(Date sale_date) {
        this.sale_date = sale_date;
    }
    
    @Column(name = "TOTAL_AMOUNT")
    public BigDecimal getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(BigDecimal total_amount) {
        this.total_amount = total_amount;
    }
    
    @Column(name = "QUANTITY")
    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }
    
    @Column(name = "ORDER_COMAND")
    public Integer getOrder_comand() {
        return order_comand;
    }

    public void setOrder_comand(Integer order_comand) {
        this.order_comand = order_comand;
    }
    private Integer order_comand;




  
    
    
}
