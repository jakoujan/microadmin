/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.dao;

import com.mcss.microadmin.data.entity.Stock;
import com.mcss.microadmin.data.filter.StockFilter;

/**
 *
 * @author edgar
 */
interface ExtendedStockDAO {

    public Iterable<Stock> findByFilter(StockFilter filter);

    public Long count(StockFilter filter);
}
