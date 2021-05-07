/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.model;

import com.mcss.microadmin.Constants;
import com.mcss.microadmin.data.dao.CustomerDAO;
import com.mcss.microadmin.data.entity.Customer;
import com.mcss.microadmin.data.filter.CustomerFilter;
import com.ispc.slibrary.dto.Response;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerModelImpl implements CustomerModel {

    @Autowired
    CustomerDAO customerDAO;
    private static final int BOTH = 0;

    @Override
    public Response getCustomers(CustomerFilter filter) {
        Response response = Response.getInstance();
        response.addField(Constants.DATA, this.customerDAO.findByFilter(filter));
        response.addField(Constants.COUNT, this.customerDAO.count(filter));
        return response;
    }

    @Override
    @Transactional
    public Response save(Customer customer) {
        if (customer.getId().equals(0)) {
            customer.setActive(Boolean.TRUE);
            customer.setEditable(Boolean.TRUE);
        }
        this.customerDAO.save(customer);
        Response response = Response.getInstance();
        response.addField(Constants.CUSTOMER, customer);
        return response;
    }

    @Override
    public Response findActives() {
        Response response = Response.getInstance();
        response.addField(Constants.DATA, this.customerDAO.findByActive(Boolean.TRUE));
        return response;
    }
    
}
