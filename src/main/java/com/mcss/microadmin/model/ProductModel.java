/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.model;

import com.ispc.slibrary.dto.Response;
import com.mcss.microadmin.data.entity.Product;
import com.mcss.microadmin.data.filter.ProductViewFilter;

/**
 *
 * @author edgar
 */
public interface ProductModel {

    public Response save(Product product);

    public Response delete(Product product);

    public Response getProducts(ProductViewFilter filter);

    public Response getProduct(Integer id);
    
}
