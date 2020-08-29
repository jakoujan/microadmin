/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.controller;

import com.mcss.microadmin.data.entity.Supplier;
import com.mcss.microadmin.data.filter.SupplierFilter;
import com.mcss.microadmin.model.SupplierModel;
import com.ispc.slibrary.dto.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/suppliers/")
public class SupplierController {

    @Autowired
    SupplierModel supplierModel;

    @PostMapping(value = "")
    public Response getSuppliers(@RequestBody SupplierFilter filter) {
        return this.supplierModel.getSuppliers(filter);
    }

    @PostMapping(value = "save")
    public Response save(@RequestBody Supplier supplier) {
        return this.supplierModel.save(supplier);
    }
}
