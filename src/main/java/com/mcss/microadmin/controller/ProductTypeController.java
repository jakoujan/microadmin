/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.controller;

import com.ispc.slibrary.dto.Response;
import com.mcss.microadmin.data.entity.ProductType;
import com.mcss.microadmin.data.filter.ProductTypeFilter;
import com.mcss.microadmin.model.ProductTypeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/product/types")
public class ProductTypeController {

    @Autowired
    ProductTypeModel productTypeModel;
    
    @PostMapping(value = "")
    public Response productTypes(@RequestBody ProductTypeFilter filter) {
        return this.productTypeModel.getProductTypes(filter);
    }

    @PostMapping(value = "/save")
    public Response save(@RequestBody ProductType productType) {
        return this.productTypeModel.save(productType);
    }
    
    @PostMapping(value = "/delete")
    public Response delete(@RequestBody ProductType productType) {
        return this.productTypeModel.delete(productType);
    }
}
