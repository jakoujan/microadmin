/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.dao;

import com.mcss.microadmin.data.entity.Supplier;
import com.mcss.microadmin.data.filter.SupplierFilter;
import java.util.List;

/**
 *
 * @author edgar
 */
public interface ExtendedSupplierDAO {

    public List<Supplier> findByFilter(SupplierFilter filter);

    public Long count(SupplierFilter filter);
}
