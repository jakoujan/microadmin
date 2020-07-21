/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.model;

import com.ispc.slibrary.dto.Response;
import com.mcss.microadmin.data.entity.Brand;
import com.mcss.microadmin.data.filter.BrandFilter;

/**
 *
 * @author edgar
 */
public interface BrandModel {

    public Response save(Brand brand);

    public Response delete(Brand brand);

    public Response getBrands(BrandFilter filter);
    
}
