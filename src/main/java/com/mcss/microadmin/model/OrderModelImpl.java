/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.model;

import com.ispc.slibrary.dto.Response;
import com.mcss.microadmin.Constants;
import com.mcss.microadmin.data.dao.OrderDAO;
import com.mcss.microadmin.data.dao.ProductDAO;
import com.mcss.microadmin.data.entity.Order;
import com.mcss.microadmin.data.entity.ProductOrder;
import com.mcss.microadmin.data.filter.OrderFilter;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderModelImpl implements OrderModel {

    @Autowired
    OrderDAO orderDAO;
    
    @Autowired
    ProductDAO productDAO;

    @Override
    @Transactional
    public Response save(Order order) {
        Response response = Response.getInstance();
        order.getProducts().forEach((ProductOrder product)->{
            product.setOrder(order);
            product.setProduct(this.productDAO.findById(product.getProduct().getId()).get());
        });
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
