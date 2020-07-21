/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.controller;

import com.ispc.slibrary.dto.Response;
import com.mcss.microadmin.data.entity.Stock;
import com.mcss.microadmin.data.filter.StockFilter;
import com.mcss.microadmin.model.StockModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/stocks")
public class StockController {

    @Autowired
    StockModel stockModel;
    
    @PostMapping(value = "")
    public Response stocks(@RequestBody StockFilter filter) {
        return this.stockModel.getStocks(filter);
    }

    @PostMapping(value = "/save")
    public Response save(@RequestBody Stock stock) {
        return this.stockModel.save(stock);
    }
    
    @PostMapping(value = "/delete")
    public Response delete(@RequestBody Stock stock) {
        return this.stockModel.delete(stock);
    }
    
    @PostMapping(value = "/initial")
    public Response initial(@RequestBody StockFilter filter) {
        return this.stockModel.getInitialStocks(filter);
    }
    
    
}
