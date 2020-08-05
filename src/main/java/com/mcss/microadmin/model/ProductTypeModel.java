/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.model;

import com.ispc.slibrary.dto.Response;
import com.mcss.microadmin.data.entity.ProductType;
import com.mcss.microadmin.data.filter.ProductTypeFilter;

/**
 *
 * @author edgar
 */
public interface ProductTypeModel {

    public Response save(ProductType productType);

    public Response delete(ProductType productType);

    public Response getProductTypes(ProductTypeFilter filter);
    
}
