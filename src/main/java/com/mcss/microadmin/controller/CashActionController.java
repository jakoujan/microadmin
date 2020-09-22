/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.controller;

import com.ispc.slibrary.dto.Response;
import com.mcss.microadmin.data.entity.CashAction;
import com.mcss.microadmin.data.filter.CashActionFilter;
import com.mcss.microadmin.model.CashActionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/cashActions")
public class CashActionController {

    @Autowired
    CashActionModel cashActionModel;
    
    @PostMapping(value = "")
    public Response cashActions(@RequestBody CashActionFilter filter) {
        return this.cashActionModel.getCashActions(filter);
    }
    
    @GetMapping(value = "/cashAction")
    public Response order(@RequestParam("id") Integer id) {
        return this.cashActionModel.getCashAction(id);
    }

    @PostMapping(value = "/save")
    public Response save(@RequestBody CashAction cashAction) {
        return this.cashActionModel.save(cashAction);
    }
    
    @PostMapping(value = "/delete")
    public Response delete(@RequestBody CashAction cashAction) {
        return this.cashActionModel.delete(cashAction);
    }
}