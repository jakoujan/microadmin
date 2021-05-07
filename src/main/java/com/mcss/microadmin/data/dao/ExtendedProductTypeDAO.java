/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.dao;

import com.mcss.microadmin.data.entity.ProductType;
import com.mcss.microadmin.data.filter.ProductTypeFilter;
import java.util.List;

/**
 *
 * @author oscardanielrangelmartinez
 */
public interface ExtendedProductTypeDAO {
    
    public List<ProductType> findByFilter(ProductTypeFilter filter);
    
    public Long count(ProductTypeFilter filter);
    
}
