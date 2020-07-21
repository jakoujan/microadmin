/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.controller;

import com.mcss.microadmin.data.entity.Customer;
import com.mcss.microadmin.data.filter.CustomerFilter;
import com.mcss.microadmin.model.CustomerModel;
import com.ispc.slibrary.dto.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/customers/")
public class CustomerController {

    @Autowired
    CustomerModel customerModel;

    @PostMapping(value = "")
    public Response getCustomers(@RequestBody CustomerFilter filter) {
        return this.customerModel.getCustomers(filter);
    }

    @PostMapping(value = "save")
    public Response save(@RequestBody Customer customer) {
        return this.customerModel.save(customer);
    }
}
