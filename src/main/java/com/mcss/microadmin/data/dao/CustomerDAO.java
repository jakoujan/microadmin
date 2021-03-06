/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.dao;

import com.mcss.microadmin.data.entity.Customer;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author edgar
 */
public interface CustomerDAO  extends CrudRepository<Customer, Integer>, ExtendedCustomerDAO {

    public List<Customer> findByActive(Boolean active);
    
}
