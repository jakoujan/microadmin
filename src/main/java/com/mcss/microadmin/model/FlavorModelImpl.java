/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.model;

import com.ispc.slibrary.dto.Response;
import com.mcss.microadmin.Constants;
import com.mcss.microadmin.data.Status;
import com.mcss.microadmin.data.dao.FlavorDAO;
import com.mcss.microadmin.data.entity.Flavor;
import com.mcss.microadmin.data.filter.FlavorFilter;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FlavorModelImpl implements FlavorModel {

    @Autowired
    FlavorDAO flavorDAO;

    @Override
    @Transactional
    public Response save(Flavor flavor) {
        Response response = Response.getInstance();
        this.flavorDAO.save(flavor);
        response.setMessage("El sabor se ha guardado con exito");
        response.addField(Constants.ENTITY, flavor);
        return response;
    }

    @Override
    @Transactional
    public Response delete(Flavor flavor) {
        Response response = Response.getInstance();
        flavor.setActive(Status.INACTIVE);
        this.flavorDAO.save(flavor);
        response.setMessage("El sabor se ha elimnado");
        response.addField(Constants.ENTITY, flavor);
        return response;
    }

    @Override
    public Response getFlavors(FlavorFilter filter) {
        Response response = Response.getInstance();
        response.addField(Constants.DATA, this.flavorDAO.findByFilter(filter));
        response.addField(Constants.COUNT, this.flavorDAO.count(filter));
        return response;
    }

}
