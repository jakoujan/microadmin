/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.model;

import com.ispc.slibrary.dto.Response;
import com.mcss.microadmin.data.entity.Order;
import com.mcss.microadmin.data.filter.OrderViewFilter;

/**
 *
 * @author edgar
 */
public interface OrderModel {

    public Response save(Order order);

    public Response delete(Order order);

    public Response getOrders(OrderViewFilter filter);
    
    public Response getOrder(Integer id);

    public Response productElaboration(Integer status);

    public Response productElaborationDone(Integer status);

    public Response productElaboration(Integer status, Integer section);
    
}
