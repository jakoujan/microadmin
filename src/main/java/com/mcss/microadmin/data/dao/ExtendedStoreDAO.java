/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.dao;

import com.mcss.microadmin.data.entity.Store;
import com.mcss.microadmin.data.filter.StoreFilter;

/**
 *
 * @author edgar
 */
interface ExtendedStoreDAO {

    public Iterable<Store> findByFilter(StoreFilter filter);

    public Long count(StoreFilter filter);
}
