/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.controller;

import com.ispc.slibrary.dto.Response;
import com.mcss.microadmin.data.entity.Table;
import com.mcss.microadmin.data.filter.TableFilter;
import com.mcss.microadmin.model.TableModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/tables")
public class TableController {

    @Autowired
    TableModel tableModel;
    
    @PostMapping(value = "")
    public Response tables(@RequestBody TableFilter filter) {
        return this.tableModel.getTables(filter);
    }

    @PostMapping(value = "/save")
    public Response save(@RequestBody Table table) {
        return this.tableModel.save(table);
    }
    
    @PostMapping(value = "/delete")
    public Response delete(@RequestBody Table table) {
        return this.tableModel.delete(table);
    }
}
