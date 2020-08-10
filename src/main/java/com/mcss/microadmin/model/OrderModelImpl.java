/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.model;

import com.ispc.slibrary.dto.Response;
import com.mcss.microadmin.Constants;
import com.mcss.microadmin.data.Status;
import com.mcss.microadmin.data.dao.OrderDAO;
import com.mcss.microadmin.data.entity.Order;
import com.mcss.microadmin.data.filter.OrderFilter;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderModelImpl implements OrderModel {

    @Autowired
    OrderDAO orderDAO;

    @Override
    @Transactional
    public Response save(Order order) {
        Response response = Response.getInstance();
        this.orderDAO.save(order);
        response.setMessage("La orden se ha generado con exito");
        response.addField(Constants.ENTITY, order);
        return response;
    }

    @Override
    @Transactional
    public Response delete(Order order) {
        Response response = Response.getInstance();
        //order.setActive(Status.INACTIVE);
        this.orderDAO.delete(order);
        response.setMessage("La orden se ha elimnado");
        response.addField(Constants.ENTITY, order);
        return response;
    }

    @Override
    public Response getOrders(OrderFilter filter) {
        Response response = Response.getInstance();
        response.addField(Constants.DATA, this.orderDAO.findByFilter(filter));
        response.addField(Constants.COUNT, this.orderDAO.count(filter));
        return response;
    }

}
