/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.model;

import com.ispc.slibrary.dto.Response;
import com.mcss.microadmin.data.entity.Stock;
import com.mcss.microadmin.data.filter.StockFilter;

/**
 *
 * @author edgar
 */
public interface StockModel {

    public Response save(Stock stock);

    public Response delete(Stock stock);

    public Response getStocks(StockFilter filter);

    public Response getInitialStocks(StockFilter filter);
    
}
