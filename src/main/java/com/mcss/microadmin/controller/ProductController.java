/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.controller;

import com.ispc.slibrary.dto.Response;
import com.mcss.microadmin.data.entity.Product;
import com.mcss.microadmin.data.filter.ProductViewFilter;
import com.mcss.microadmin.data.filter.ProductViewFilter;
import com.mcss.microadmin.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/products")
public class ProductController {

    @Autowired
    ProductModel productModel;
    
    @PostMapping(value = "")
    public Response products(@RequestBody ProductViewFilter filter) {
        return this.productModel.getProducts(filter);
    }

    @PostMapping(value = "/save")
    public Response save(@RequestBody Product product) {
        return this.productModel.save(product);
    }
    
    @GetMapping(value = "/product")
    public Response product(@RequestParam("id") Integer id) {
        return this.productModel.getProduct(id);
    }
    
    @PostMapping(value = "/delete")
    public Response delete(@RequestBody Product product) {
        return this.productModel.delete(product);
    }
}
