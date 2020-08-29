/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.model;

import com.mcss.microadmin.Constants;
import com.mcss.microadmin.data.dao.SupplierDAO;
import com.mcss.microadmin.data.entity.Supplier;
import com.mcss.microadmin.data.filter.SupplierFilter;
import com.ispc.slibrary.dto.Response;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SupplierModelImpl implements SupplierModel {

    @Autowired
    SupplierDAO supplierDAO;
    private static final int BOTH = 0;

    @Override
    public Response getSuppliers(SupplierFilter filter) {
        Response response = Response.getInstance();
        response.addField(Constants.DATA, this.supplierDAO.findByFilter(filter));
        response.addField(Constants.COUNT, this.supplierDAO.count(filter));
        return response;
    }

    @Override
    @Transactional
    public Response save(Supplier supplier) {
        if (supplier.getId().equals(0)) {
            supplier.setActive(Boolean.TRUE);
        }
        this.supplierDAO.save(supplier);
        Response response = Response.getInstance();
        response.addField(Constants.CUSTOMER, supplier);
        return response;
    }

    @Override
    public Response findActives() {
        Response response = Response.getInstance();
        response.addField(Constants.DATA, this.supplierDAO.findByActive(Boolean.TRUE));
        return response;
    }
}
