/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.dao;

import com.mcss.microadmin.data.entity.Order;
import com.mcss.microadmin.data.filter.OrderFilter;
import com.mcss.microadmin.data.filter.OrderViewFilter;
import java.util.List;

/**
 *
 * @author oscardanielrangelmartinez
 */
public interface ExtendedOrderDAO {
    
    public List<Order> findByFilter(OrderViewFilter filter);
    
    public Long count(OrderViewFilter filter);
    
}
