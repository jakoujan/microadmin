/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.model;

import com.ispc.slibrary.dto.Response;
import com.mcss.microadmin.data.entity.Order;
import com.mcss.microadmin.data.entity.Sale;
import com.mcss.microadmin.data.filter.SaleReportViewFilter;
//import com.mcss.microadmin.data.filter.SaleFilter;
//import com.mcss.microadmin.data.filter.SaleViewFilter;

/**
 *
 * @author edgar
 */
public interface SaleModel {

    public Response save(Sale sale);

    public Response delete(Sale sale);

    public Response getSales(SaleReportViewFilter filter);
    
    public Response getSale(Integer id);
    
    public Boolean createSaleFromOrder(Order order);
    
}
