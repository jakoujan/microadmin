/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.controller;

import com.ispc.slibrary.dto.Response;
import com.mcss.microadmin.data.entity.Order;
import com.mcss.microadmin.data.filter.OrderFilter;
import com.mcss.microadmin.data.filter.OrderViewFilter;
import com.mcss.microadmin.data.filter.SaleReportViewFilter;
import com.mcss.microadmin.model.OrderModel;
import com.mcss.microadmin.model.SaleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/reports")
public class ReportsController {

    @Autowired
    SaleModel saleModel;
    
    @PostMapping(value = "")
    public Response reportSales(@RequestBody SaleReportViewFilter filter) {
        return this.saleModel.getSales(filter);
    }
    
    @GetMapping(value = "/sale")
    public Response sale(@RequestParam("id") Integer id) {
        return this.saleModel.getSale(id);
    }
    
}
