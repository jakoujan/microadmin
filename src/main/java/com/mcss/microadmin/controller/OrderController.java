/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.controller;

import com.ispc.slibrary.dto.Response;
import com.mcss.microadmin.data.entity.Order;
import com.mcss.microadmin.data.filter.OrderViewFilter;
import com.mcss.microadmin.model.OrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/orders")
public class OrderController {

    @Autowired
    OrderModel orderModel;

    @PostMapping(value = "")
    public Response orders(@RequestBody OrderViewFilter filter) {
        return this.orderModel.getOrders(filter);
    }

    @PostMapping(value = "/save")
    public Response save(@RequestBody Order order) {
        return this.orderModel.save(order);
    }

    @GetMapping(value = "/order")
    public Response order(@RequestParam("id") Integer id) {
        return this.orderModel.getOrder(id);
    }

    @PostMapping(value = "/delete")
    public Response delete(@RequestBody Order order) {
        return this.orderModel.delete(order);
    }

    @GetMapping(value = "/elaboration/products")
    public Response productElaboration(@RequestParam("status") Integer status, @RequestParam(name = "section", required = false) Integer section) {
        return this.orderModel.productElaboration(status, section);
    }

    @GetMapping(value = "/elaboration/products/done")
    public Response productElaborationDone(@RequestParam("id") Integer id) {
        return this.orderModel.productElaborationDone(id);
    }
}
