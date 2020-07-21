/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.model;

import com.ispc.slibrary.dto.Response;
import com.mcss.microadmin.Constants;
import com.mcss.microadmin.data.Status;
import com.mcss.microadmin.data.dao.BrandDAO;
import com.mcss.microadmin.data.entity.Brand;
import com.mcss.microadmin.data.filter.BrandFilter;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BrandModelImpl implements BrandModel {

    @Autowired
    BrandDAO brandDAO;

    @Override
    @Transactional
    public Response save(Brand brand) {
        Response response = Response.getInstance();
        this.brandDAO.save(brand);
        response.setMessage("La marca se ha guardado con exito");
        response.addField(Constants.ENTITY, brand);
        return response;
    }

    @Override
    @Transactional
    public Response delete(Brand brand) {
        Response response = Response.getInstance();
        brand.setActive(Status.INACTIVE);
        this.brandDAO.save(brand);
        response.setMessage("La marca se ha elimnado");
        response.addField(Constants.ENTITY, brand);
        return response;
    }

    @Override
    public Response getBrands(BrandFilter filter) {
        Response response = Response.getInstance();
        response.addField(Constants.DATA, this.brandDAO.findByFilter(filter));
        response.addField(Constants.COUNT, this.brandDAO.count(filter));
        return response;
    }

}
