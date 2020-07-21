package com.mcss.microadmin.model;

import com.mcss.microadmin.data.entity.Customer;
import com.mcss.microadmin.data.filter.CustomerFilter;
import com.ispc.slibrary.dto.Response;

public interface CustomerModel {

    public Response getCustomers(CustomerFilter filter);

    public Response save(Customer customer);

    public Response findActives();

}
