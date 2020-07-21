/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.controller;

import com.ispc.slibrary.dto.Response;
import com.mcss.microadmin.data.entity.Brand;
import com.mcss.microadmin.data.filter.BrandFilter;
import com.mcss.microadmin.model.BrandModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/brands")
public class BrandController {

    @Autowired
    BrandModel brandModel;
    
    @PostMapping(value = "")
    public Response brands(@RequestBody BrandFilter filter) {
        return this.brandModel.getBrands(filter);
    }

    @PostMapping(value = "/save")
    public Response save(@RequestBody Brand brand) {
        return this.brandModel.save(brand);
    }
    
    @PostMapping(value = "/delete")
    public Response delete(@RequestBody Brand brand) {
        return this.brandModel.delete(brand);
    }
}
