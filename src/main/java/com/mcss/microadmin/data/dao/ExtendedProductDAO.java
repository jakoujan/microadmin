/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.dao;

import com.mcss.microadmin.data.filter.ProductViewFilter;
import com.mcss.microadmin.data.view.ProductView;

/**
 *
 * @author edgar
 */
public interface ExtendedProductDAO {

    public Iterable<ProductView> findByFilter(ProductViewFilter filter);

    public Long count(ProductViewFilter filter);
}
