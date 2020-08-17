/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.model;

import com.ispc.slibrary.dto.Response;
import com.mcss.microadmin.Constants;
import com.mcss.microadmin.data.Status;
import com.mcss.microadmin.data.dao.ProductTypeDAO;
import com.mcss.microadmin.data.entity.ProductType;
import com.mcss.microadmin.data.filter.ProductTypeFilter;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductTypeModelImpl implements ProductTypeModel {

    @Autowired
    ProductTypeDAO productTypeDAO;

    @Override
    @Transactional
    public Response save(ProductType productType) {
        Response response = Response.getInstance();
        
        if(productType.getId() == null) {
            productType.setActive(Boolean.TRUE);
        }
        
        this.productTypeDAO.save(productType);
        response.setMessage("El tipo de producto se ha guardado con exito");
        response.addField(Constants.ENTITY, productType);
        return response;
    }

    @Override
    @Transactional
    public Response delete(ProductType productType) {
        Response response = Response.getInstance();
        productType.setActive(Status.INACTIVE);
        this.productTypeDAO.save(productType);
        response.setMessage("El tipo de producto se ha elimnado");
        response.addField(Constants.ENTITY, productType);
        return response;
    }

    @Override
    public Response getProductTypes(ProductTypeFilter filter) {
        Response response = Response.getInstance();
        response.addField(Constants.DATA, this.productTypeDAO.findByFilter(filter));
        response.addField(Constants.COUNT, this.productTypeDAO.count(filter));
        return response;
    }

}
