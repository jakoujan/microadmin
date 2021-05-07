/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.controller;

import com.ispc.slibrary.dto.Response;
import com.mcss.microadmin.data.filter.SaleReportViewFilter;
import com.mcss.microadmin.model.SaleReportModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/reports")
public class ReportsController {

    @Autowired
    SaleReportModel saleReportModel;

    @PostMapping(value = "cashcount")
    public Response reportSales(@RequestBody SaleReportViewFilter filter) {
        return this.saleReportModel.getSaleReports(filter);
    }

    /*@GetMapping(value = "/sale")
    public Response sale(@RequestParam("id") Integer id) {
        return this.saleReportModel.getSale(id);
    }*/
}
