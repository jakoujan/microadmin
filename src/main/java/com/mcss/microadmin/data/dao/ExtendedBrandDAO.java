/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.dao;

import com.mcss.microadmin.data.entity.Brand;
import com.mcss.microadmin.data.filter.BrandFilter;

/**
 *
 * @author edgar
 */
interface ExtendedBrandDAO {
    public Iterable<Brand> findByFilter(BrandFilter filter);
    
    public Long count(BrandFilter filter);
}
