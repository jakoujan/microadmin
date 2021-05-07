/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.dao;

import com.mcss.microadmin.data.entity.Customer;
import com.mcss.microadmin.data.filter.CustomerFilter;
import java.util.List;

/**
 *
 * @author edgar
 */
public interface ExtendedCustomerDAO {

    public List<Customer> findByFilter(CustomerFilter filter);

    public Long count(CustomerFilter filter);
}
